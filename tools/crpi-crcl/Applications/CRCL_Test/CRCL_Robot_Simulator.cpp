/*!
  @defgroup CRCL_ROBOT_SIMULATOR Simulator for a CRCL robot

  The CRCL_Robot_Simulator application runs a simple CRCL server that
  responds to robot control messages and replies to status
  requests. Requests to move the robot will results in the simulated
  pose being changed after a few seconds, but no real physical
  simulation is done. The intent of this simulator is to validate that
  messages are being handled properly by the CRPI-to-CRCL client.

  Command line arguments: 

  ```
  -p <port>    : TCP port to serve
  -t <seconds> : period to update status
  -a           : send status automatically
  -r <robot file> : path to the robot configuration file
  -s <schema>  : client's path to the status schema
  -v           : validate messages to command schema
  -d           : turn debug printing on
  -?           : print this help
  ```
  Command-line arguments override the settings in the robot configuration file. 
*/

#include "DataPrimitives.hxx"
#include "CRCLCommandInstance.hxx"
#include "CRCLCommands.hxx"
#include "CRCLStatus.hxx"
#include "URDF_CRPI.hxx"

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <math.h>
#include <float.h>
#include <iostream>
#include <fstream>
#include <sstream>
#include <string>

#include "CRCLCommon.h"
#include "ulapi.h"

using namespace std;

enum {ROBOT_XML_FILE_NAME_LEN = 256};
#define ROBOT_XML_FILE_DEFAULT "robot.xml"

enum {SCHEMA_LOCATION_LEN = 256};
#define SCHEMA_LOCATION_DEFAULT "CRCLStatus.xsd";
static char schema_location[SCHEMA_LOCATION_LEN] = SCHEMA_LOCATION_DEFAULT;

/*! The global debug flag, set with the -d option on the command line */
static bool debug = false;

static string crclCommandPrefix()
{
  string a("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<CRCLCommandInstance xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"");
  string b(schema_location);
  string c("\">\n");

  return a + b + c;
}

static string crclCommandSuffix()
{
  string a("</CRCLCommandInstance>");

  return a;
}

static CommandStateEnumType CRCLCommandStateToXMLCommandState(CRCLCommandState cs)
{
  switch (cs) {
  case CRCL_Done: return CommandStateEnumType::CRCL_Done; break;
  case CRCL_Working: return CommandStateEnumType::CRCL_Working; break;
  case CRCL_Ready: return CommandStateEnumType::CRCL_Ready; break;
  default: return CommandStateEnumType::CRCL_Error; break;
  }
  return CommandStateEnumType::CRCL_Error;
}

static CRCLUnit xmlLengthUnittoCRCLUnit(const SetLengthUnitsType::UnitName_type& a)
{
  switch (a) {
  case LengthUnitEnumType::meter: return CRCL_Unit_Meter; break;
  case LengthUnitEnumType::millimeter: return CRCL_Unit_Millimeter; break;
  case LengthUnitEnumType::inch: return CRCL_Unit_Inch; break;
  default: return CRCL_Unit_None; break;
  }

  return CRCL_Unit_None;
}

static CRCLUnit xmlAngleUnitToCRCLUnit(const SetAngleUnitsType::UnitName_type& a)
{
  switch (a) {
  case AngleUnitEnumType::degree: return CRCL_Unit_Degree; break;
  case AngleUnitEnumType::radian: return CRCL_Unit_Radian; break;
  default: return CRCL_Unit_None; break;
  }

  return CRCL_Unit_None;
}

static string CRCLLengthToString(CRCLUnit a)
{
  switch (a) {
  case CRCL_Unit_Meter: return "meter"; break;
  case CRCL_Unit_Millimeter: return "millimeter"; break;
  case CRCL_Unit_Inch: return "inch"; break;
  default: return ""; break;
  }

  return "";
}

static string CRCLAngleToString(CRCLUnit a)
{
  switch (a) {
  case CRCL_Unit_Degree: return "degree"; break;
  case CRCL_Unit_Radian: return "radian"; break;
  default: return ""; break;
  }

  return "";
}

static LengthUnitEnumType CRCLLengthToXMLLength(CRCLUnit a)
{
  switch (a) {
  case CRCL_Unit_Meter: return LengthUnitEnumType::meter; break;
  case CRCL_Unit_Millimeter: return LengthUnitEnumType::millimeter; break;
  case CRCL_Unit_Inch: return LengthUnitEnumType::inch; break;
  default: return LengthUnitEnumType::meter; break;
  }

  return LengthUnitEnumType::meter;
}

static AngleUnitEnumType CRCLAngleToXMLAngle(CRCLUnit a)
{
  switch (a) {
  case CRCL_Unit_Radian: return AngleUnitEnumType::radian; break;
  case CRCL_Unit_Degree: return AngleUnitEnumType::degree; break;
  default: return AngleUnitEnumType::radian; break;
  }

  return AngleUnitEnumType::radian;
}

static CRCLJointType xmlJointTypeToCrclJointType(const joint::type_type &a)
{
  if (a == "prismatic") return CRCL_Joint_Type_Prismatic;
  else if (a == "revolute") return CRCL_Joint_Type_Revolute;

  return CRCL_Joint_Type_None;
}

// convenience functions that uses return value vs pointer arg, good for macros

static CRCLJointType getJointType(CRCLRobotModel *rm, int index)
{
  CRCLJointType type;

  if (0 != rm->getJointType(&type, index)) return CRCL_Joint_Type_None;
  return type;
}

static double getJointPosition(CRCLRobotModel *rm, int index)
{
  double joint;

  if (0 != rm->getJointPosition(&joint, index)) return 0;
  return joint;
}

/*
  The simulator kinematics is derived from the one in the robot
  configuration file, where the type of joint (prismatic, revolute) is
  not known until run time. Our trivial kinematics model maps joints
  to Cartesian values as follows:

  The mapping is joints 1..6 <-> X Y Z R P W, respectively

  Prismatic joints are scaled 2:1 to Cartesian values
  Revolute joints are scaled -2:1 to Cartesian values
*/

static int inverseKins(CRCLRobotModel *rm)
{
  double x, y, z, xi, xj, xk, zi, zj, zk;
  double r, p, w;
  
  if (NULL == rm) return -1;
  if (CRCLRobotModel::JOINT_NUM < 6) return -1;

  rm->getPose(&x, &y, &z, &xi, &xj, &xk, &zi, &zj, &zk);
  IJKtoRPY(xi, xj, xk, zi, zj, zk, &r, &p, &w);

#define SETIT(ix,c) if (CRCL_Joint_Type_Prismatic == getJointType(rm, (ix))) rm->setJointPosition(2.0*(c), (ix)); else rm->setJointPosition(-2.0*(c), (ix))

  SETIT(0,x);
  SETIT(1,y);
  SETIT(2,z);
  SETIT(3,r);
  SETIT(4,p);
  SETIT(5,w);

#undef SETIT
  
  return 0;
}

// update the robot model so that the pose matches the joints
static int forwardKins(CRCLRobotModel *rm)
{
  double joint[CRCLRobotModel::JOINT_NUM];
  double x, y, z;
  double r, p, w;
  double xi, xj, xk, zi, zj, zk;
  
  if (NULL == rm) return -1;
  if (CRCLRobotModel::JOINT_NUM < 6) return -1;

  rm->getJointPositions(joint, sizeof(joint)/sizeof(*joint));

#define SETIT(c,ix) if (CRCL_Joint_Type_Prismatic == getJointType(rm, (ix))) (c) = 0.5*joint[(ix)]; else (c) == -0.5*joint[(ix)]

  SETIT(x,0);
  SETIT(y,1);
  SETIT(z,2);
  SETIT(r,3);
  SETIT(p,4);
  SETIT(w,5);

#undef SETIT
  
  RPYtoIJK(r, p, w, &xi, &xj, &xk, &zi, &zj, &zk);
  rm->setPose(x, y, z, xi, xj, xk, zi, zj, zk);

  return 0;
}

static string formatCRCLStatusType(CRCLRobotModel *rm, SettingsReport *sr)
{
  int commandID, statusID;
  CRCLCommandState commandState;
  double position[CRCLRobotModel::JOINT_NUM];
  double velocity[CRCLRobotModel::JOINT_NUM];
  double effort[CRCLRobotModel::JOINT_NUM];
  double x, y, z, xi, xj, xk, zi, zj, zk;
  string gripperName;
  double gripperValue;
  bool holdingObject;
  CRCLUnit lengthUnit;
  CRCLUnit angleUnit;
  double transSpeed;
  double rotSpeed;

  if (NULL == rm) return string("");

  rm->getCommandID(&commandID);
  rm->getStatusID(&statusID);
  rm->getCommandState(&commandState);
  
  CRCLStatusType stat(CommandStatusType(commandID, statusID, CRCLCommandStateToXMLCommandState(commandState)));

  if (sr->reportJointStatuses()) {
    rm->getJointPositions(position, sizeof(position)/sizeof(*position));
    rm->getJointVelocities(velocity, sizeof(velocity)/sizeof(*velocity));
    rm->getJointEfforts(effort, sizeof(effort)/sizeof(*effort));
    /*
      Joint numbering in the CRCL XML schema begins at 1.
    */
    JointStatusesType::JointStatus_sequence jsq;
    for (int i = 0; i < CRCLRobotModel::JOINT_NUM; i++) {
      bool sawJointStatus;
      JointStatusType jst(i+1);

      sawJointStatus = false;
      if (sr->jointReport[i].reportPosition()) {
	jst.JointPosition(position[i]);
	sawJointStatus = true;
      }
      if (sr->jointReport[i].reportTorqueOrForce()) {
	jst.JointTorqueOrForce(effort[i]);
	sawJointStatus = true;
      }
      if (sr->jointReport[i].reportVelocity()) {
	jst.JointVelocity(velocity[i]);
	sawJointStatus = true;
      }
      if (sawJointStatus) {
	jsq.push_back(jst);
      }
    } // for (joints)
    if (jsq.size() > 0) {
      JointStatusesType jsst;
      jsst.JointStatus(jsq);
      stat.JointStatuses(jsst);
    }
  }
  
  if (sr->reportPoseStatus()) {
    rm->getPose(&x, &y, &z, &xi, &xj, &xk, &zi, &zj, &zk);
    stat.PoseStatus(PoseStatusType(PoseType(PointType(x,y,z), VectorType(xi,xj,xk), VectorType(zi,zj,zk))));
  }

  rm->getGripperName(gripperName);
  if ((sr->reportGripperStatus()) && (gripperName != "")) {
    /*!
      In this simulator, if the gripper name starts with "Vacuum" it's
      a vacuum gripper, else it's a parallel-jaw gripper
     */
    rm->getGripperValue(&gripperValue);
    rm->getHoldingObject(&holdingObject);
    if (0 == strncmp(gripperName.c_str(), "Vacuum", 6)) {
      VacuumGripperStatusType gr(gripperName, gripperValue);
      gr.HoldingObject() = holdingObject;
      gr.IsPowered() = holdingObject;
      stat.GripperStatus(gr);
    } else {
      ParallelGripperStatusType gr(gripperName, gripperValue);
      gr.HoldingObject() = holdingObject;
      gr.Separation() = (holdingObject ? 0 : 1);
      stat.GripperStatus(gr);
    }
  }
  
  if (sr->reportSettingsStatus()) {
    bool any = false;
    /*!
      Note: the selection of which SettingsStatus elements to include
      is up to the server, since they are all declared as optional in
      the schema. The approach here is to include those settings that
      correspond to any "Set" messages we've received, e.g., report
      the length units if we've received a SetLengthUnits message.
     */
    SettingsStatusType st;
    if (sr->reportLengthUnits()) {
      rm->getLengthUnit(&lengthUnit);
      st.LengthUnitName(CRCLLengthToXMLLength(lengthUnit));
      any = true;
    }
    if (sr->reportAngleUnits()) {
      rm->getAngleUnit(&angleUnit);
      st.AngleUnitName(CRCLAngleToXMLAngle(angleUnit));
      any = true;
    }
    if (sr->reportTransSpeed()) {
      rm->getTransSpeed(&transSpeed);
      st.TransSpeedAbsolute(transSpeed);
      any = true;
    }
    if (sr->reportRotSpeed()) {
      rm->getRotSpeed(&rotSpeed);
      st.RotSpeedAbsolute(rotSpeed);
      any = true;
    }
    if (any) stat.SettingsStatus(st);
  }

  ostringstream os;
  xml_schema::namespace_infomap m = xml_schema::namespace_infomap();
  m[""].schema = schema_location;
  CRCLStatus(os, stat, m);
  return os.str();
}

enum {BUFFERLEN = 4096};

typedef struct {
  ulapi_task_struct *reader_task;
  ulapi_integer client_id;
  CRCLRobotModel *robotModel;
  SettingsReport *settingsReport;
  bool validate;
} reader_args;

void reader_code(void *args)
{
  ulapi_task_struct *reader_task;
  ulapi_integer client_id;
  CRCLRobotModel *robotModel;
  SettingsReport *settingsReport;
  bool validate;
  bool inited = false;
  ulapi_integer nchars;
  enum {INBUF_LEN = 4096};
  char inbuf[INBUF_LEN];
  ulapi_integer ret;

  reader_task = ((reader_args *) args)->reader_task;
  client_id = ((reader_args *) args)->client_id;
  robotModel = ((reader_args *) args)->robotModel;
  settingsReport = ((reader_args *) args)->settingsReport;
  validate = ((reader_args *) args)->validate;
  free(args);

  xml_schema::flags flags = 0;
  if (! validate) flags = flags | xml_schema::flags::dont_validate;

  for (;;) {
    nchars = ulapi_socket_read(client_id, inbuf, sizeof(inbuf));
    if ((-1 == nchars) || (0 == nchars)) {
      break;
    }
    inbuf[nchars] = 0;

    robotModel->lock();

    if (robotModel->expTimer()) {
      CRCLCommandState commandState;
      robotModel->getCommandState(&commandState);
      if (commandState == CRCL_Working) {
	robotModel->setCommandState(CRCL_Done);
      }
    }

    try {
      istringstream is(inbuf);
      auto_ptr<CRCLCommandInstanceType> cmd(CRCLCommandInstance(is, flags));
      CRCLCommandType &ct = cmd->CRCLCommand();

      if (! inited) {
	if (InitCanonType *dct = dynamic_cast<InitCanonType *>(&ct)) {
	  robotModel->setCommandID(dct->CommandID());
	  robotModel->setCommandState(CRCL_Done);
	  inited = true;
	  if (debug) cout << "InitCanon" << endl;
	} else {
	  // other commands are prohibited in the non-inited state
	  robotModel->setCommandID(0);
	  robotModel->setCommandState(CRCL_Error);
	  cerr << "Not inited; need an InitCanonType command" << endl;
	}
	robotModel->unlock();
	continue;
      }

      if (GetStatusType *dct = dynamic_cast<GetStatusType *>(&ct)) {
	string s = formatCRCLStatusType(robotModel, settingsReport);
	int statusID;
	CRCLCommandState commandState;
	robotModel->getStatusID(&statusID);
	statusID++;
	robotModel->setStatusID(statusID);
	// don't set the command ID, leave it for the current command
	if (robotModel->expTimer()) {
	  robotModel->getCommandState(&commandState);
	  if (commandState == CRCL_Working) {
	    robotModel->setCommandState(CRCL_Done);
	  }
	}
	if (debug) cout << "GetStatus" << endl;
	ret = ulapi_socket_write(client_id, s.c_str(), s.length());
	if (0 >= ret) break;
      } else if (InitCanonType *dct = dynamic_cast<InitCanonType *>(&ct)) {
	robotModel->setCommandID(dct->CommandID());
	robotModel->setCommandState(CRCL_Done);
	if (debug) cout << "InitCanon" << endl;
	inited = true;
      } else if (EndCanonType *dct = dynamic_cast<EndCanonType *>(&ct)) {
	robotModel->setCommandID(dct->CommandID());
	if (debug) cout << "EndCanon" << endl;
	inited = false;
	robotModel->setCommandState(CRCL_Done);
      } else if (SetTransSpeedType *dct = dynamic_cast<SetTransSpeedType *>(&ct)) {
	robotModel->setCommandID(dct->CommandID());
	if (debug) cout << "SetTransSpeed" << endl;
	if (TransSpeedAbsoluteType *ddct = dynamic_cast<TransSpeedAbsoluteType *>(&dct->TransSpeed())) {
	  robotModel->setTransSpeed(ddct->Setting());
	  settingsReport->reportTransSpeed() = true;
	  if (debug) cout << "TransSpeedAbsolute " << ddct->Setting() << endl;
	} else if (TransSpeedRelativeType *ddct = dynamic_cast<TransSpeedRelativeType *>(&dct->TransSpeed())) {
	  if (debug) cout << "TransSpeedRelative " << ddct->Fraction() << endl;
	}
	robotModel->setCommandState(CRCL_Done);
      } else if (SetRotSpeedType *dct = dynamic_cast<SetRotSpeedType *>(&ct)) {
	robotModel->setCommandID(dct->CommandID());
	if (debug) cout << "SetRotSpeed" << endl;
	if (RotSpeedAbsoluteType *ddct = dynamic_cast<RotSpeedAbsoluteType *>(&dct->RotSpeed())) {
	  robotModel->setRotSpeed(ddct->Setting());
	  settingsReport->reportRotSpeed() = true;
	  if (debug) cout << "RotSpeedAbsolute " << ddct->Setting() << endl;
	} else if (RotSpeedRelativeType *ddct = dynamic_cast<RotSpeedRelativeType *>(&dct->RotSpeed())) {
	  if (debug) cout << "RotSpeedRelative " << ddct->Fraction() << endl;
	}
	robotModel->setCommandState(CRCL_Done);
      } else if (MoveToType *dct = dynamic_cast<MoveToType *>(&ct)) {
	robotModel->setCommandID(dct->CommandID());
	// this will convert from user units to robot units
	robotModel->setPose(dct->EndPosition().Point().X(),
			   dct->EndPosition().Point().Y(),
			   dct->EndPosition().Point().Z(),
			   dct->EndPosition().XAxis().I(),
			   dct->EndPosition().XAxis().J(),
			   dct->EndPosition().XAxis().K(),
			   dct->EndPosition().ZAxis().I(),
			   dct->EndPosition().ZAxis().J(),
			   dct->EndPosition().ZAxis().K());
	inverseKins(robotModel); // set the joints to match
	robotModel->setCommandState(CRCL_Working);
	robotModel->setTimer(ulapi_time() + 3);
	if (debug) cout << "MoveTo" << endl;
      } else if (MoveThroughToType *dct = dynamic_cast<MoveThroughToType *>(&ct)) {
	robotModel->setCommandID(dct->CommandID());
	if (debug) cout << "MoveThroughTo: " << endl;
	double x, y, z, xi, xj, xk, zi, zj, zk;
	MoveThroughToType::Waypoint_sequence &waypoints = dct->Waypoint();
	for (int i = 0; i < waypoints.size(); i++) {
	  // overwrite pose for writing of last one to robot model
	  x = waypoints[i].Point().X();
	  y = waypoints[i].Point().Y();
	  z = waypoints[i].Point().Z();
	  xi = waypoints[i].XAxis().I();
	  xj = waypoints[i].XAxis().J();
	  xk = waypoints[i].XAxis().K();
	  zi = waypoints[i].ZAxis().I();
	  zj = waypoints[i].ZAxis().J();
	  zk = waypoints[i].ZAxis().K();
	  if (debug ) cout << "  " << x << " " << y << " " << z << endl;
	  // try for PoseAndSetType
	  PoseAndSetType *pt = dynamic_cast<PoseAndSetType *>(&(waypoints[i]));
	  if (NULL != pt) {
	    if (pt->TransSpeed().present()) {
	      TransSpeedAbsoluteType *ppt = dynamic_cast<TransSpeedAbsoluteType *>(&pt->TransSpeed().get());
	      if (debug) cout << "TransSpeed " << ppt->Setting() << endl;
	    }
	    if (pt->TransAccel().present()) {
	      TransAccelAbsoluteType *ppt = dynamic_cast<TransAccelAbsoluteType *>(&pt->TransAccel().get());
	      if (debug) cout << "TransAccel " << ppt->Setting() << endl;
	    }
	    if (pt->Tolerance().present()) {
	      PoseToleranceType *ppt = dynamic_cast<PoseToleranceType *>(&pt->Tolerance().get());
	      if (debug) cout << "Tolerance X " << ppt->XPointTolerance() << endl;
	    }
	  }
	}
	robotModel->setPose(x, y, z, xi, xj, xk, zi, zj, zk);
	inverseKins(robotModel);
	robotModel->setCommandState(CRCL_Working);
	robotModel->setTimer(ulapi_time() + 3);
      } else if (ActuateJointsType *dct = dynamic_cast<ActuateJointsType *>(&ct)) {
	robotModel->setCommandID(dct->CommandID());
	ActuateJointsType::ActuateJoint_sequence &aj = dct->ActuateJoint();
	if (debug) cout << "ActuateJoints:";
	for (int i = 0; i < aj.size(); i++) {
	  robotModel->setJointPosition(aj[i].JointPosition(), aj[i].JointNumber()-1);
	  if (debug) cout << " " << aj[i].JointPosition();
	  if (JointSpeedAccelType *ddct = dynamic_cast<JointSpeedAccelType *>(&aj[i].JointDetails())) {
	    robotModel->setJointVelocity(ddct->JointSpeed().get(), aj[i].JointNumber()-1);
	    if (debug) cout << "/" << ddct->JointSpeed().get();
	  } else if (JointForceTorqueType *ddct = dynamic_cast<JointForceTorqueType *>(&aj[i].JointDetails())) {
	    robotModel->setJointEffort(ddct->Setting().get(), aj[i].JointNumber()-1);
	    if (debug) cout << "/" << ddct->Setting().get();
	  }
	}
	if (debug) cout << endl;
	robotModel->setCommandState(CRCL_Done);
      } else if (SetEndEffectorType *dct = dynamic_cast<SetEndEffectorType *>(&ct)) {
	double v = dct->Setting();
	robotModel->setCommandID(dct->CommandID());
	robotModel->setGripperValue(v);
	if (v > (1.0 - FLT_EPSILON)) robotModel->setHoldingObject(true);
	else robotModel->setHoldingObject(false);
	robotModel->setCommandState(CRCL_Done);
	if (debug) cout << "SetEndEffector " << v << endl;
      } else if (CloseToolChangerType *dct = dynamic_cast<CloseToolChangerType *>(&ct)) {
	robotModel->setCommandID(dct->CommandID());
	if (dct->Name().present()) robotModel->setGripperName(dct->Name().get());
	robotModel->setCommandState(CRCL_Done);
	if (debug) cout << "CloseToolChanger: " << dct->Name().get() << endl;
      } else if (OpenToolChangerType *dct = dynamic_cast<OpenToolChangerType *>(&ct)) {
	robotModel->setCommandID(dct->CommandID());
	robotModel->setGripperName("");
	robotModel->setCommandState(CRCL_Done);
	if (debug) cout << "OpenToolChanger" << endl;
      } else if (MessageType *dct = dynamic_cast<MessageType *>(&ct)) {
	robotModel->setCommandID(dct->CommandID());
	robotModel->setCommandState(CRCL_Done);
	cout << "Message: " << dct->Message() << endl;
      } else if (SetLengthUnitsType *dct = dynamic_cast<SetLengthUnitsType *>(&ct)) {
	robotModel->setCommandID(dct->CommandID());
	robotModel->setLengthUnit(xmlLengthUnittoCRCLUnit(dct->UnitName()));
	settingsReport->reportLengthUnits() = true;
	robotModel->setCommandState(CRCL_Done);
	if (debug) cout << "SetLengthUnits: " << dct->UnitName() << endl;
      } else if (SetAngleUnitsType *dct = dynamic_cast<SetAngleUnitsType *>(&ct)) {
	robotModel->setCommandID(dct->CommandID());
	robotModel->setAngleUnit(xmlAngleUnitToCRCLUnit(dct->UnitName()));
	settingsReport->reportAngleUnits() = true;
	robotModel->setCommandState(CRCL_Done);
	if (debug) cout << "SetAngleUnits: " << dct->UnitName() << endl;
      } else if (ConfigureJointReportsType *dct = dynamic_cast<ConfigureJointReportsType *>(&ct)) {
	robotModel->setCommandID(dct->CommandID());
	ConfigureJointReportsType::ConfigureJointReport_sequence &cjr = dct->ConfigureJointReport();
	for (int i = 0; i < cjr.size(); i++) {
	  int j = cjr[i].JointNumber()-1;
	  if (j >= 0 && j < CRCLRobotModel::JOINT_NUM) {
	    settingsReport->jointReport[j].reportPosition() = cjr[i].ReportPosition();
	    settingsReport->jointReport[j].reportTorqueOrForce() = cjr[i].ReportTorqueOrForce();
	    settingsReport->jointReport[j].reportVelocity() = cjr[i].ReportVelocity();
	  }
	  if (debug) {
	    cout << "  Joint " << cjr[i].JointNumber() << ":" << endl;
	    cout << "    ReportPosition:      " << (cjr[i].ReportPosition() ? "true" : "false") << endl;
	    cout << "    ReportTorqueOrForce: " << (cjr[i].ReportTorqueOrForce() ? "true" : "false") << endl;
	    cout << "    ReportVelocity:      " << (cjr[i].ReportVelocity() ? "true" : "false") << endl;
	  }
	}
	robotModel->setCommandState(CRCL_Done);
      } else if (ConfigureStatusReportType *dct = dynamic_cast<ConfigureStatusReportType *>(&ct)) {
	robotModel->setCommandID(dct->CommandID());
	settingsReport->reportJointStatuses() = dct->ReportJointStatuses();
	settingsReport->reportPoseStatus() = dct->ReportPoseStatus();
	settingsReport->reportGripperStatus() = dct->ReportGripperStatus();
	settingsReport->reportSettingsStatus() = dct->ReportSettingsStatus();
	robotModel->setCommandState(CRCL_Done);
	if (debug) {
	  cout << "ConfigureStatusReportType" << endl;
	  cout << dct->ReportJointStatuses() << endl;
	  cout << dct->ReportPoseStatus() << endl;
	  cout << dct->ReportGripperStatus() << endl;
	  cout << dct->ReportSettingsStatus() << endl;
	}
      } else if (SetTransAccelType *dct = dynamic_cast<SetTransAccelType *>(&ct)) {
	robotModel->setCommandID(dct->CommandID());
	if (debug) cout << "SetTransAccel" << endl;
	if (TransAccelAbsoluteType *ddct = dynamic_cast<TransAccelAbsoluteType *>(&dct->TransAccel())) {
	  if (debug) cout << "TransAccelAbsolute " << ddct->Setting() << endl;
	} else if (TransAccelRelativeType *ddct = dynamic_cast<TransAccelRelativeType *>(&dct->TransAccel())) {
	  if (debug) cout << "TransAccelRelative " << ddct->Fraction() << endl;
	}
	robotModel->setCommandState(CRCL_Done);
      } else if (StopMotionType *dct = dynamic_cast<StopMotionType *>(&ct)) {
	robotModel->setCommandID(dct->CommandID());
	robotModel->setCommandState(CRCL_Done);
	if (debug) cout << "StopMotion " << dct->StopCondition() << endl;
      } else if (SetEndPoseToleranceType *dct = dynamic_cast<SetEndPoseToleranceType *>(&ct)) {
	robotModel->setCommandID(dct->CommandID());
	robotModel->setCommandState(CRCL_Done);
	if (debug) cout << "SetEndPoseTolerance " << dct->Tolerance().XPointTolerance() << endl;
      } else if (SetIntermediatePoseToleranceType *dct = dynamic_cast<SetIntermediatePoseToleranceType *>(&ct)) {
	robotModel->setCommandID(dct->CommandID());
	robotModel->setCommandState(CRCL_Done);
	if (debug) cout << "SetIntermediatePoseTolerance " << dct->Tolerance().XPointTolerance() << endl;
      } else if (SetRobotParametersType *dct = dynamic_cast<SetRobotParametersType *>(&ct)) {
	robotModel->setCommandID(dct->CommandID());
	if (debug) cout << "SetRobotParameters:" << endl;
	SetRobotParametersType::ParameterSetting_sequence& ddct = dct->ParameterSetting();
	for (size_t i = 0; i < ddct.size(); i++) {
	  if (debug) cout << "  " << ddct[i].ParameterName() << " = " << ddct[i].ParameterValue() << endl;
	}
	robotModel->setCommandState(CRCL_Done);
      } else {
	// no way to get the command ID, so set it to zero
	robotModel->setCommandID(0);
	// command not recognized
	robotModel->setCommandState(CRCL_Error);
	if (debug) {
	  fprintf(stderr, "command not recognized: %s\n", inbuf);
	}
      }
    } catch (const xml_schema::exception& e) {
      robotModel->setCommandState(CRCL_Error);
      cerr << "XML parsing error:" << endl;
      cerr << e << endl;
      if (debug) cerr << inbuf << endl;
    }
    robotModel->unlock();

  } // for (;;)

  ulapi_socket_close(client_id);

  printf("reader task done, closed %d\n", client_id);

  return;
}

static void printHelp(void)
{
  printf("Arguments:\n");
  printf("-p <port>    : TCP port to serve\n");
  printf("-t <seconds> : period to update status\n");
  printf("-r <robot file> : path to the robot configuration file\n");
  printf("-s <schema>  : client's path to the status schema\n");
  printf("-v           : validate messages to command schema\n");
  printf("-d           : turn debug printing on\n");
  printf("-?           : print this help\n");
}

int main(int argc, char *argv[])
{
  int option;
  int port = -1;
  double period = 1;
  char robot_xml_file_name[ROBOT_XML_FILE_NAME_LEN] = "robot.xml";
  bool arg_schema_location = false;
  bool validate = false;
  ulapi_integer socket_id;
  ulapi_integer client_id;
  enum {CONNECTION_ADDR_LEN = 256};
  char connection_addr[CONNECTION_ADDR_LEN];
  ulapi_integer connection_port;
  ulapi_task_struct *reader_task;
  reader_args *reader_args_ptr;
  CRCLRobotModel robotModel;
  SettingsReport *settingsReport;
  double d1;
  int i1;

  ulapi_opterr = 0;
  for (;;) {
    option = ulapi_getopt(argc, argv, ":p:t:r:s:vd?");
    if (option == -1)
      break;

    switch (option) {
    case 'p':
      if (1 != sscanf(ulapi_optarg, "%i", &i1)) {
	fprintf(stderr, "bad port value for -%c: %s\n", option, ulapi_optarg);
	return 1;
      }
      port = i1;
      break;

    case 't':
      if (1 != sscanf(ulapi_optarg, "%lf", &d1)) {
	fprintf(stderr, "bad period value for -%c: %s\n", option, ulapi_optarg);
	return 1;
      }
      period = d1;
      break;

    case 'r':
      strncpy(robot_xml_file_name, ulapi_optarg, sizeof(robot_xml_file_name));
      robot_xml_file_name[sizeof(robot_xml_file_name) - 1] = 0;
      break;

    case 's':
      strncpy(schema_location, ulapi_optarg, sizeof(schema_location));
      schema_location[sizeof(schema_location) - 1] = 0;
      arg_schema_location = true;
      break;

    case 'v':
      validate = true;
      break;

    case 'd':
      debug = true;
      break;

    case '?':
      printHelp();
      return 0;
      break;

    case ':':
      fprintf(stderr, "missing value for -%c\n", ulapi_optopt);
      return 1;
      break;

    default:			/* '?' */
      fprintf(stderr, "unrecognized option -%c\n", ulapi_optopt);
      return 1;
      break;
    }
  }
  if (ulapi_optind < argc) {
    fprintf(stderr, "extra non-option characters: %s\n", argv[ulapi_optind]);
    return 1;
  }

  if (ULAPI_OK != ulapi_init()) {
    fprintf(stderr, "ulapi init error\n");
    return 1;
  }

  if (debug) ulapi_set_debug(ULAPI_DEBUG_ALL);

  srand(time(NULL));
  robotModel.setStatusID(rand());

  filebuf fb;
  if (! fb.open(robot_xml_file_name, ios::in)) {
    fprintf(stderr, "can't read robot configuration file %s\n", robot_xml_file_name);
    return 1;
  }

  xml_schema::flags flags = 0;
  if (! validate) flags = flags | xml_schema::flags::dont_validate;
  
  try {
    istream is(&fb);
    typedef auto_ptr<robot> arobot;
    arobot myrobot(robot_(is, flags));
    robot::joint_sequence &js = myrobot->joint();
    if (NULL != myrobot->Debug()) {
      debug = (myrobot->Debug() ? true : false);
    }
    if (NULL != myrobot->CRCL_Robot()) {
      if (! arg_schema_location) {
	if (NULL != myrobot->CRCL_Robot()->Status_Schema()) {
	  strncpy(schema_location, myrobot->CRCL_Robot()->Status_Schema().get().c_str(), sizeof(schema_location)-1);
	  schema_location[sizeof(schema_location)-1] = 0;
	  if (debug) cout << "CRCL_Robot: Status_Schema: " << schema_location << endl;
	}
      }
    }
    for (size_t i = 0; i < js.size(); i++) {
      robotModel.setJointType(xmlJointTypeToCrclJointType(js[i].type()), i);
    }
    if (port <= 0) {
      port = myrobot->TCP_IP().Port();
    }
    if (debug) {
      robotModel.print();
    }
  } catch (const xml_schema::exception& e) {
    cerr << e << endl;
    return 1;
  }
  
  socket_id = ulapi_socket_get_server_id(port);
  if (socket_id < 0) {
    fprintf(stderr, "can't serve port %d\n", (int) port);
    ulapi_exit();
    return 1;
  }
  if (debug) printf("serving port %d\n", (int) port);
  
  for (;;) {
    printf("waiting for client connection...\n");
    client_id = ulapi_socket_get_connection_id(socket_id);
    if (client_id < 0) {
      break;
    }
    ulapi_getpeername(client_id, connection_addr, sizeof(connection_addr), &connection_port);
    printf("got one on fd %d from %s on port %d\n", client_id, connection_addr, connection_port);

    settingsReport = new SettingsReport;

    reader_task = ulapi_task_new();
    reader_args_ptr = (reader_args *) malloc(sizeof(reader_args));
    reader_args_ptr->reader_task = reader_task;
    reader_args_ptr->client_id = client_id;
    reader_args_ptr->robotModel = &robotModel;
    reader_args_ptr->settingsReport = settingsReport;
    reader_args_ptr->validate = validate;
    ulapi_task_start(reader_task, reader_code, reader_args_ptr, ulapi_prio_lowest(), 0);
  }

  return 0;
}
