#include "DataPrimitives.hxx"
#include "CRCLStatus.hxx"
#include "URDF_CRPI.hxx"

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
#include <ctype.h>
#include <math.h>
#include <iostream>
#include <fstream>
#include <sstream>
#include <string>

#include "CRCLCommon.h"
#include "ulapi.h"

#ifdef HAVE_READLINE_READLINE_H
#include <readline/readline.h>	/* readline */
#include <readline/history.h>	/* using_history */
#endif

using namespace std;

enum {ROBOT_XML_FILE_NAME_LEN = 256};
#define ROBOT_XML_FILE_DEFAULT "robot.xml"

enum {SCHEMA_LOCATION_LEN = 256};
#define SCHEMA_LOCATION_DEFAULT "CRCLCommandInstance.xsd";
static char schema_location[SCHEMA_LOCATION_LEN] = SCHEMA_LOCATION_DEFAULT;

static bool debugStatus = false;
static bool debugCommand = false;

static CRCLRobotModel robotModel;

// utility functions for converting XML types to useful C++ types

static CRCLJointType xmlJointTypeToCRCLJointType(const joint::type_type a)
{
  if (a == "prismatic") return CRCL_Joint_Type_Prismatic;
  else if (a == "revolute") return CRCL_Joint_Type_Revolute;

  return CRCL_Joint_Type_None;
}

static CRCLUnit xmlLengthUnitToCRCLUnit(const SettingsStatusType::LengthUnitName_type a)
{
  switch (a) {
  case LengthUnitEnumType::meter: return CRCL_Unit_Meter; break;
  case LengthUnitEnumType::millimeter: return CRCL_Unit_Millimeter; break;
  case LengthUnitEnumType::inch: return CRCL_Unit_Inch; break;
  default: return CRCL_Unit_None; break;
  }

  return CRCL_Unit_None;
}

static CRCLUnit xmlAngleUnitToCRCLUnit(const SettingsStatusType::AngleUnitName_type a)
{
  switch (a) {
  case AngleUnitEnumType::degree: return CRCL_Unit_Degree; break;
  case AngleUnitEnumType::radian: return CRCL_Unit_Radian; break;
  default: return CRCL_Unit_None; break;
  }

  return CRCL_Unit_None;
}

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

/*
  This is our copy of the command ID we send. It will in general be
  different from the ones in other instances of client programs
  running. Normally multiple clients would not be running, since
  they'd be competing for control of the robot. Sometimes this is done
  for testing, and the users of each client will need to coordinate
  their control.
*/
static int localCommandID = 1;

static int sendInitCanon(ulapi_id socket_id)
{
  /* Looks like:

  <CRCLCommand xsi:type="InitCanonType">
    <CommandID>1</CommandID>

  */

  stringstream ss;
  string s;
  ulapi_integer ret;
  
  ss << crclCommandPrefix();
  ss << "  <CRCLCommand xsi:type=\"InitCanonType\">\n";
  ss << "    <CommandID>" << localCommandID++ << "</CommandID>\n";
  ss << "  </CRCLCommand>\n";
  ss << crclCommandSuffix();
  s = ss.str();

  ret = ulapi_socket_write(socket_id, s.c_str(), s.length());

  if (debugCommand) cout << ss.str() << endl;

  if (0 >= ret) return -1;
  return 0;
}

static int sendEndCanon(ulapi_id socket_id)
{
  /* Looks like:

  <CRCLCommand xsi:type="EndCanonType">
    <CommandID>1</CommandID>

  */

  stringstream ss;
  string s;
  ulapi_integer ret;
  
  ss << crclCommandPrefix();
  ss << "  <CRCLCommand xsi:type=\"EndCanonType\">\n";
  ss << "    <CommandID>" << localCommandID++ << "</CommandID>\n";
  ss << "  </CRCLCommand>\n";
  ss << crclCommandSuffix();
  s = ss.str();

  ret = ulapi_socket_write(socket_id, s.c_str(), s.length());

  if (debugCommand) cout << ss.str() << endl;

  if (0 >= ret) return -1;
  return 0;
}

static int sendGetStatus(ulapi_id socket_id)
{
  /* Looks like:

  <CRCLCommand xsi:type="GetStatusType">
    <CommandID>1</CommandID>

  */

  stringstream ss;
  string s;
  ulapi_integer ret;

  // don't increment the command ID, so that we can see the execution
  // progress of any prior real command
  
  ss << crclCommandPrefix();
  ss << "  <CRCLCommand xsi:type=\"GetStatusType\">\n";
  ss << "    <CommandID>" << localCommandID << "</CommandID>\n";
  ss << "  </CRCLCommand>\n";
  ss << crclCommandSuffix();
  s = ss.str();

  ret = ulapi_socket_write(socket_id, s.c_str(), s.length());

  if (debugCommand) cout << ss.str() << endl;

  if (0 >= ret) return -1;
  return 0;
}

static int sendMoveTo(ulapi_id socket_id, double x, double y, double z, double r, double p, double w)
{
  /* Looks like:

   <CRCLCommand xsi:type="MoveToType">
    <CommandID>2</CommandID>
    <MoveStraight>false</MoveStraight>
    <EndPosition>
      <Point>
        <X>2.5</X> <Y>1</Y> <Z>1</Z>
      </Point>
      <XAxis>
        <I>1</I> <J>0</J> <K>0</K>
      </XAxis>
      <ZAxis>
        <I>0</I> <J>0</J> <K>-1</K>
      </ZAxis>
    </EndPosition>
  </CRCLCommand>
  */

  stringstream ss;
  string s;
  double xi, xj, xk, zi, zj, zk;
  ulapi_integer ret;

  RPYtoIJK(r, p, w, &xi, &xj, &xk, &zi, &zj, &zk);

  ss << crclCommandPrefix();
  ss << "  <CRCLCommand xsi:type=\"MoveToType\">\n";
  ss << "    <CommandID>" << localCommandID++ << "</CommandID>\n";
  ss << "    <MoveStraight>true</MoveStraight>\n";
  ss << "    <EndPosition>\n";
  ss << "      <Point>\n";
  ss << "        <X>" << x << "</X> <Y>" << y << "</Y> <Z>" << z << "</Z>\n";
  ss << "      </Point>\n";
  ss << "      <XAxis>\n";
  ss << "        <I>" << xi << "</I> <J>" << xj << "</J> <K>" << xk << "</K>\n";
  ss << "      </XAxis>\n";
  ss << "      <ZAxis>\n";
  ss << "        <I>" << zi << "</I> <J>" << zj << "</J> <K>" << zk << "</K>\n";
  ss << "      </ZAxis>\n";
  ss << "    </EndPosition>\n";
  ss << "  </CRCLCommand>\n";
  ss << crclCommandSuffix();
  s = ss.str();

  ret = ulapi_socket_write(socket_id, s.c_str(), s.length());

  if (debugCommand) cout << ss.str() << endl;

  if (0 >= ret) return -1;
  return 0;
}

static int sendSetEndEffector(ulapi_id socket_id, double value)
{
  /* Looks like:

  <CRCLCommand xsi:type="SetEndEffectorType">
    <CommandID>23</CommandID>
    <Setting>0.75</Setting>
  </CRCLCommand>
  */

  stringstream ss;
  string s;
  ulapi_integer ret;

  ss << crclCommandPrefix();
  ss << "  <CRCLCommand xsi:type=\"SetEndEffectorType\">\n";
  ss << "    <CommandID>" << localCommandID++ << "</CommandID>\n";
  ss << "    <Setting>" << value << "</Setting>\n";
  ss << "  </CRCLCommand>\n";
  ss << crclCommandSuffix();
  s = ss.str();

  ret = ulapi_socket_write(socket_id, s.c_str(), s.length());

  if (debugCommand) cout << ss.str() << endl;

  if (0 >= ret) return -1;
  return 0;
}

static int sendCloseToolChanger(ulapi_id socket_id, const char *name)
{
  /* Looks like:

  <CRCLCommand xsi:type="CloseToolChangerType">
    <Name>gripper</Name>
    <CommandID>23</CommandID>
  </CRCLCommand>
  */

  stringstream ss;
  string s;
  ulapi_integer ret;

  if (0 == *name) return 1;
  
  ss << crclCommandPrefix();
  ss << "  <CRCLCommand xsi:type=\"CloseToolChangerType\">\n";
  ss << "    <Name>" << name << "</Name>\n";
  ss << "    <CommandID>" << localCommandID++ << "</CommandID>\n";
  ss << "  </CRCLCommand>\n";
  ss << crclCommandSuffix();
  s = ss.str();

  ret = ulapi_socket_write(socket_id, s.c_str(), s.length());

  if (debugCommand) cout << ss.str() << endl;

  if (0 >= ret) return -1;
  return 0;
}

static int sendOpenToolChanger(ulapi_id socket_id)
{
  /* Looks like:

  <CRCLCommand xsi:type="OpenToolChangerType">
    <CommandID>23</CommandID>
  </CRCLCommand>
  */

  stringstream ss;
  string s;
  ulapi_integer ret;

  ss << crclCommandPrefix();
  ss << "  <CRCLCommand xsi:type=\"OpenToolChangerType\">\n";
  ss << "    <CommandID>" << localCommandID++ << "</CommandID>\n";
  ss << "  </CRCLCommand>\n";
  ss << crclCommandSuffix();
  s = ss.str();

  ret = ulapi_socket_write(socket_id, s.c_str(), s.length());

  if (debugCommand) cout << ss.str() << endl;

  if (0 >= ret) return -1;
  return 0;
}

static int sendSetLengthUnits(ulapi_id socket_id, const char *name)
{
  /* Looks like:

  <CRCLCommand xsi:type="SetLengthUnitsType">
    <CommandID>23</CommandID>
    <UnitName>meter</UnitName>
  </CRCLCommand>
  */

  stringstream ss;
  string s;
  ulapi_integer ret;

  if (!strcmp(name, "meter")) s = "meter";
  else if (!strcmp(name, "millimeter")) s = "millimeter";
  else if (!strcmp(name, "inch")) s = "inch";
  else return -1; // bad unit name request
  
  ss << crclCommandPrefix();
  ss << "  <CRCLCommand xsi:type=\"SetLengthUnitsType\">\n";
  ss << "    <CommandID>" << localCommandID++ << "</CommandID>\n";
  ss << "    <UnitName>" << s << "</UnitName>\n";
  ss << "  </CRCLCommand>\n";
  ss << crclCommandSuffix();
  s = ss.str();

  ret = ulapi_socket_write(socket_id, s.c_str(), s.length());

  if (debugCommand) cout << ss.str() << endl;

  if (0 >= ret) return -1;
  return 0;
}

static int sendSetAngleUnits(ulapi_id socket_id, const char *name)
{
  /* Looks like:

  <CRCLCommand xsi:type="SetAngleUnitsType">
    <CommandID>23</CommandID>
    <UnitName>degree</UnitName>
  </CRCLCommand>
  */

  stringstream ss;
  string s;
  ulapi_integer ret;

  if (!strcmp(name, "degree")) s = "degree";
  else if (!strcmp(name, "radian")) s = "radian";
  else return -1; // bad unit name request
  
  ss << crclCommandPrefix();
  ss << "  <CRCLCommand xsi:type=\"SetAngleUnitsType\">\n";
  ss << "    <CommandID>" << localCommandID++ << "</CommandID>\n";
  ss << "    <UnitName>" << s << "</UnitName>\n";
  ss << "  </CRCLCommand>\n";
  ss << crclCommandSuffix();
  s = ss.str();

  ret = ulapi_socket_write(socket_id, s.c_str(), s.length());

  if (debugCommand) cout << ss.str() << endl;

  if (0 >= ret) return -1;
  return 0;
}

static int sendSetTransSpeed(ulapi_id socket_id, double speed)
{
  /* Looks like:
  <CRCLCommand xsi:type="SetTransSpeedType">
    <CommandID>123</CommandID>
    <TransSpeed xsi:type="TransSpeedAbsoluteType">
      <Setting>100.0</Setting>
    </TransSpeed>
  </CRCLCommand>
  */

  stringstream ss;
  string s;
  ulapi_integer ret;
  
  ss << crclCommandPrefix();
  ss << "  <CRCLCommand xsi:type=\"SetTransSpeedType\">\n";
  ss << "    <CommandID>" << localCommandID++ << "</CommandID>\n";
  ss << "    <TransSpeed xsi:type=\"TransSpeedAbsoluteType\">\n";
  ss << "      <Setting>" << speed << "</Setting>\n";
  ss << "    </TransSpeed>\n";
  ss << "  </CRCLCommand>\n";
  ss << crclCommandSuffix();
  s = ss.str();

  ret = ulapi_socket_write(socket_id, s.c_str(), s.length());

  if (debugCommand) cout << ss.str() << endl;

  if (0 >= ret) return -1;
  return 0;
}

static int sendSetRotSpeed(ulapi_id socket_id, double speed)
{
  /* Looks like:
  <CRCLCommand xsi:type="SetRotSpeedType">
    <CommandID>123</CommandID>
    <RotSpeed xsi:type="RotSpeedAbsoluteType">
      <Setting>100.0</Setting>
    </RotSpeed>
  </CRCLCommand>
  */

  stringstream ss;
  string s;
  ulapi_integer ret;
  
  ss << crclCommandPrefix();
  ss << "  <CRCLCommand xsi:type=\"SetRotSpeedType\">\n";
  ss << "    <CommandID>" << localCommandID++ << "</CommandID>\n";
  ss << "    <RotSpeed xsi:type=\"RotSpeedAbsoluteType\">\n";
  ss << "      <Setting>" << speed << "</Setting>\n";
  ss << "    </RotSpeed>\n";
  ss << "  </CRCLCommand>\n";
  ss << crclCommandSuffix();
  s = ss.str();

  ret = ulapi_socket_write(socket_id, s.c_str(), s.length());

  if (debugCommand) cout << ss.str() << endl;

  if (0 >= ret) return -1;
  return 0;
}

static int sendConfigureJointReports(ulapi_id socket_id, int joint, int pos, int tof, int vel)
{
  /* Looks like:

  <CRCLCommand xsi:type="ConfigureJointReportsType">
    <CommandID>31</CommandID>
    <ResetAll>false</ResetAll>
    <ConfigureJointReport>
      <JointNumber>1</JointNumber>
      <ReportPosition>true</ReportPosition>
      <ReportTorqueOrForce>false</ReportTorqueOrForce>
      <ReportVelocity>false</ReportVelocity>
    </ConfigureJointReport>
    <ConfigureJointReport>
      <JointNumber>6</JointNumber>
      <ReportPosition>false</ReportPosition>
      <ReportTorqueOrForce>false</ReportTorqueOrForce>
      <ReportVelocity>true</ReportVelocity>
    </ConfigureJointReport>
  </CRCLCommand>
  */

  stringstream ss;
  string s;
  ulapi_integer ret;

  ss << crclCommandPrefix();
  ss << "  <CRCLCommand xsi:type=\"ConfigureJointReportsType\">\n";
  ss << "    <CommandID>" << localCommandID++ << "</CommandID>\n";
  ss << "    <ResetAll>false</ResetAll>\n";
  ss << "    <ConfigureJointReport>\n";
  ss << "      <JointNumber>" << joint << "</JointNumber>\n";
  ss << "      <ReportPosition>" << (pos ? "true" : "false") << "</ReportPosition>\n";
  ss << "      <ReportTorqueOrForce>" << (tof ? "true" : "false") << "</ReportTorqueOrForce>\n";
  ss << "      <ReportVelocity>" << (vel ? "true" : "false") << "</ReportVelocity>\n";
  ss << "    </ConfigureJointReport>\n";
  ss << "  </CRCLCommand>\n";
  ss << crclCommandSuffix();
  s = ss.str();

  ret = ulapi_socket_write(socket_id, s.c_str(), s.length());

  if (debugCommand) cout << ss.str() << endl;

  if (0 >= ret) return -1;
  return 0;
}

static int sendConfigureStatusReport(ulapi_id socket_id, int joint, int pose, int gripper, int settings)
{
  /* Looks like:
  <CRCLCommand xsi:type="ConfigureStatusReportType">
    <CommandID>29</CommandID>
    <ReportJointStatuses>true</ReportJointStatuses>
    <ReportPoseStatus>false</ReportPoseStatus>
    <ReportGripperStatus>false</ReportGripperStatus>
    <ReportSettingsStatus>false</ReportSettingsStatus>
  </CRCLCommand>
  */

  stringstream ss;
  string s;
  ulapi_integer ret;

  ss << crclCommandPrefix();
  ss << "  <CRCLCommand xsi:type=\"ConfigureStatusReportType\">\n";
  ss << "    <CommandID>" << localCommandID++ << "</CommandID>\n";
  ss << "    <ReportJointStatuses>" << (joint ? "true" : "false") << "</ReportJointStatuses>\n";
  ss << "    <ReportPoseStatus>" << (pose ? "true" : "false") << "</ReportPoseStatus>\n";
  ss << "    <ReportGripperStatus>" << (gripper ? "true" : "false") << "</ReportGripperStatus>\n";
  ss << "    <ReportSettingsStatus>" << (settings ? "true" : "false") << "</ReportSettingsStatus>\n";
  ss << "  </CRCLCommand>\n";
  ss << crclCommandSuffix();
  s = ss.str();

  ret = ulapi_socket_write(socket_id, s.c_str(), s.length());

  if (debugCommand) cout << ss.str() << endl;

  if (0 >= ret) return -1;
  return 0;
}

static int sendActuateJoints(ulapi_id socket_id, double *pos, size_t size)
{
  /* Looks like:

  <CRCLCommand xsi:type="ActuateJointsType">
    <CommandID>31</CommandID>
    <ActuateJoint>
      <JointNumber>1</JointNumber>
      <JointPosition>1.23</JointPosition>
      <JointDetails xsi:type="JointSpeedAccelType">
	<Setting>2.34</Setting>
      </JointDetails>
    </ActuateJoint>
    ...
  </CRCLCommand>
  */

  stringstream ss;
  string s;
  double v;
  ulapi_integer ret;

  ss << crclCommandPrefix();
  ss << "  <CRCLCommand xsi:type=\"ActuateJointsType\">\n";
  ss << "    <CommandID>" << localCommandID++ << "</CommandID>\n";

  robotModel.lock();
  for (int i = 0; i < size; i++) {
    ss << "    <ActuateJoint>\n";
    ss << "      <JointNumber>" << (i+1) << "</JointNumber>\n";
    ss << "      <JointPosition>" << pos[i] << "</JointPosition>\n";
    ss << "      <JointDetails xsi:type=\"JointSpeedAccelType\">\n";
    robotModel.getJointVelocitySetting(&v, i);
    ss << "        <JointSpeed>" << v << "</JointSpeed>\n";
    ss << "      </JointDetails>\n";
    ss << "    </ActuateJoint>\n";
  }
  robotModel.unlock();

  ss << "  </CRCLCommand>\n";
  ss << crclCommandSuffix();
  s = ss.str();

  ret = ulapi_socket_write(socket_id, s.c_str(), s.length());

  if (debugCommand) cout << ss.str() << endl;

  if (0 >= ret) return -1;
  return 0;
}

typedef struct {
  ulapi_task_struct *client_reader_task;
  ulapi_integer socket_id;
  bool validate;
} client_reader_args;

static void client_reader_code(void *args)
{
  ulapi_task_struct *client_reader_task;
  ulapi_integer socket_id;
  bool validate;
  ulapi_integer nchars;
  enum {INBUF_LEN = 4096};
  char inbuf[INBUF_LEN];

  client_reader_task = ((client_reader_args *) args)->client_reader_task;
  socket_id = ((client_reader_args *) args)->socket_id;
  validate = ((client_reader_args *) args)->validate;

  for (;;) {
    nchars = ulapi_socket_read(socket_id, inbuf, sizeof(inbuf)-1);
    if (-1 == nchars) {
      fprintf(stderr, "connection closed\n");
      break;
    }
    if (0 == nchars) {
      fprintf(stderr, "end of file\n");
      break;
    }
    inbuf[nchars] = 0;

    if (debugStatus) cout << inbuf << endl;

    xml_schema::flags flags = 0;
    if (! validate) flags = flags | xml_schema::flags::dont_validate;
    
    robotModel.lock();
    try {
      istringstream is(inbuf);
      auto_ptr<CRCLStatusType> stat(CRCLStatus(is,flags));

      robotModel.setCommandID(stat->CommandStatus().CommandID());
      robotModel.setStatusID(stat->CommandStatus().StatusID());
      robotModel.setCommandState(stringToCRCLCommandState(stat->CommandStatus().CommandState()));

      // do units first, these affect the values of pose and joint data
      if (NULL != stat->SettingsStatus()) {
	if (stat->SettingsStatus()->LengthUnitName().present()) {
	  robotModel.setLengthUnit(xmlLengthUnitToCRCLUnit(stat->SettingsStatus()->LengthUnitName().get()));
	}
	if (stat->SettingsStatus()->AngleUnitName().present()) {
	  robotModel.setAngleUnit(xmlAngleUnitToCRCLUnit(stat->SettingsStatus()->AngleUnitName().get()));
	}
	if (stat->SettingsStatus()->TransSpeedAbsolute().present()) {
	  robotModel.setTransSpeed(stat->SettingsStatus()->TransSpeedAbsolute().get().Setting());
	}
	if (stat->SettingsStatus()->RotSpeedAbsolute().present()) {
	  robotModel.setRotSpeed(stat->SettingsStatus()->RotSpeedAbsolute().get().Setting());
	}
      }

      if (NULL != stat->JointStatuses()) {
	JointStatusesType::JointStatus_sequence &js = stat->JointStatuses()->JointStatus();
	for (int i = 0; i < js.size(); i++) {
	  if (i < CRCLRobotModel::JOINT_NUM) {
	    if (js[i].JointPosition().present()) robotModel.setJointPosition(js[i].JointPosition().get(), i);
	    if (js[i].JointTorqueOrForce().present()) robotModel.setJointEffort(js[i].JointTorqueOrForce().get(), i);
	    if (js[i].JointVelocity().present()) robotModel.setJointVelocity(js[i].JointVelocity().get(), i);
	  }
	}
      }

      if (NULL != stat->PoseStatus()) {
	robotModel.setPose(stat->PoseStatus()->Pose().Point().X(),
			   stat->PoseStatus()->Pose().Point().Y(),
			   stat->PoseStatus()->Pose().Point().Z(),
			   stat->PoseStatus()->Pose().XAxis().I(),
			   stat->PoseStatus()->Pose().XAxis().J(),
			   stat->PoseStatus()->Pose().XAxis().K(),
			   stat->PoseStatus()->Pose().ZAxis().I(),
			   stat->PoseStatus()->Pose().ZAxis().J(),
			   stat->PoseStatus()->Pose().ZAxis().K());
      }
      if (NULL != stat->GripperStatus()) {
	robotModel.setGripperName(stat->GripperStatus()->GripperName());
	if (ParallelGripperStatusType *ddct = dynamic_cast<ParallelGripperStatusType *>(&stat->GripperStatus().get())) {
	  robotModel.setGripperValue(ddct->Separation());
	} else if (VacuumGripperStatusType *ddct = dynamic_cast<VacuumGripperStatusType *>(&stat->GripperStatus().get())) {
	  robotModel.setGripperValue(ddct->IsPowered() ? 1.0 : 0.0);
	}
	if (stat->GripperStatus()->HoldingObject().present()) {
	  robotModel.setHoldingObject(stat->GripperStatus()->HoldingObject().get());
	}
      } else {
	robotModel.setGripperName("");
      }
    } catch (const xml_schema::exception& e) {
      cerr << e << endl;
    }
    robotModel.unlock();
    
  } // for (;;)

  ulapi_socket_close(socket_id);

  printf("client read thread done, closed %d\n", socket_id);

  ulapi_task_delete(client_reader_task);

  exit(1);
}

// find all of 'a' in 'b', and following chars of 'b' are space or 0
static int strwcmp(const char *a, const char *b)
{
  int i;

  i = strlen(a);
  if (0 != strncmp(a, b, i))
    return 1;
  if (0 == b[i] || isspace(b[i]))
    return 0;

  return 1;
}

static void printHelp()
{
  printf("Arguments:\n");
  printf("-p <port> : TCP port of server\n");
  printf("-h <host> : hostname of server\n");
  printf("-r <file> : robot configuration file\n");
  printf("-d        : turn debug printing on\n");
  printf("-s <schema> : server's path to command schema\n");
  printf("-h <host> : hostname of server\n");
  printf("-?        : print this help\n");
}

static void printCommands()
{
  printf("Interactive commands:\n");
  printf("?                  : print this help\n");
  printf("quit, q            : quit the application\n");
  printf("(enter)            : print robot status\n");
  printf("don, doff <target> : turn debug ON or OFF for <target>, one of:\n");
  printf("                     status, command\n");
  printf("init               : send an init command\n");
  printf("end                : send an end command\n");
  printf("status             : force a request for robot status\n");
  printf("move <x> <y> <z> <r> <p> <w> :\n  move to Cartesian coordinates\n");
  printf("actuate <joint positions ...>\n");
  printf("    send an actuate joint command for the joint positions\n");
  printf("tool 'off' | 'on <name>' | 'value <value>' :\n");
  printf("    take tool off, put tool <name> on, activate tool with <value>\n");
  printf("units 'length' <unit> | 'angle' <unit> :\n");
  printf("    set the length or angle units, one of:\n");
  printf("    meter | millimeter | inch , degree | radian\n");
  printf("report <flags, joints pose gripper settings> :\n");
  printf("    turn status reporting on or off for these\n");
  printf("jointreport <flags, position effort speed> :\n");
  printf("    turn joint reporting on or off for these\n");
}

int main(int argc, char *argv[])
{
  int option;
  int port = -1;
  enum {HOST_LEN = 256};
  char host[HOST_LEN] = "";
  char robot_xml_file_name[ROBOT_XML_FILE_NAME_LEN] = "robot.xml";
  bool arg_schema_location = false;
  bool validate = false;
  ulapi_integer socket_id;
  client_reader_args client_reader_args_inst;
  ulapi_task_struct *client_reader_task;
  const char prompt[] = "crcl> ";
#ifndef HAVE_READLINE_READLINE_H
  enum {BUFFER_LEN = 256};
  char buffer[BUFFER_LEN];
  int len;
#endif
  char *line;
  char *ptr;
  double d1, d2, d3, d4, d5, d6;
  int i1, i2, i3, i4;

  if (ULAPI_OK != ulapi_init()) {
    fprintf(stderr, "ulapi init error\n");
    return 1;
  }

  ulapi_opterr = 0;
  for (;;) {
    option = ulapi_getopt(argc, argv, ":p:h:r:s:vd?");
    if (option == -1)
      break;

    switch (option) {
    case 'p':
      if (1 != sscanf(ulapi_optarg, "%i", &port)) {
	fprintf(stderr, "bad port value for -%c: %s\n", option, ulapi_optarg);
	return 1;
      }	
      break;

    case 'h':
      strncpy(host, ulapi_optarg, sizeof(host));
      host[sizeof(host) - 1] = 0;
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
      debugStatus = true;
      debugCommand = true;
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

  ulapi_set_debug(ULAPI_DEBUG_ALL);

  filebuf fb;
  if (! fb.open(robot_xml_file_name, ios::in)) {
    fprintf(stderr, "can't read robot configuration file %s\n", robot_xml_file_name);
    return 1;
  }
  
  istream is(&fb);

  try {
    xml_schema::flags flags = 0;
    typedef auto_ptr<robot> arobot;
    if (! validate) flags = flags | xml_schema::flags::dont_validate;
    arobot myrobot(robot_(is, flags));
    robot::joint_sequence &js = myrobot->joint();
    if (NULL != myrobot->Debug()) {
      debugCommand = (myrobot->Debug() ? true : false);
      debugStatus = debugCommand;
    }
    for (int i = 0; i < js.size(); i++) {
      robotModel.setJointType(xmlJointTypeToCRCLJointType(js[i].type()), i);
    }
    if (NULL != myrobot->CRCL_Robot()) {
      if (! arg_schema_location) {
	if (NULL != myrobot->CRCL_Robot()->Command_Schema()) {
	  strncpy(schema_location, myrobot->CRCL_Robot()->Command_Schema().get().c_str(), sizeof(schema_location)-1);
	  schema_location[sizeof(schema_location)-1] = 0;
	  if (debugCommand) cout << "CRCL_Robot: Command_Schema: " << schema_location << endl;
	}
      }
    }
    if (port <= 0) {
      port = myrobot->TCP_IP().Port();
    }
    if (0 == *host) {
      strncpy(host, myrobot->TCP_IP().Address().c_str(), sizeof(host)-1);
      host[sizeof(host)-1] = 0;
    }
    if (debugStatus) {
      robotModel.print();
    }
  } catch (const xml_schema::exception& e) {
    cerr << e << endl;
    return 1;
  }

  // minimize the chance of our command ID colliding with other clients
  srand(time(NULL));
  localCommandID = rand();

  socket_id = ulapi_socket_get_client_id(port, host);
  if (socket_id < 0) {
    fprintf(stderr, "can't connect to %s on port %d\n", host, (int) port);
    ulapi_exit();
    return 1;
  } else {
    printf("connected to %s on port %d\n", host, (int) port);
  }

  client_reader_task = ulapi_task_new();
  client_reader_args_inst.client_reader_task = client_reader_task;
  client_reader_args_inst.socket_id = socket_id;
  client_reader_args_inst.validate = validate;
  ulapi_task_start(client_reader_task, client_reader_code, &client_reader_args_inst, ulapi_prio_lowest(), 0);

#ifdef HAVE_READLINE_READLINE_H
  using_history();
#endif

  while (!feof(stdin)) {
#ifdef HAVE_READLINE_READLINE_H
    line = readline(prompt);
    if (NULL == line) {
      break;
    }
    if (*line) {
      add_history(line);
    }
#else
    printf(prompt);
    fflush(stdout);
    if (NULL == fgets(buffer, sizeof(buffer)-1, stdin)) {
      break;
    }
    len = strlen(buffer);
    if (len > 0)
      buffer[len - 1] = 0;	// take off newline
    line = buffer;
#endif
    ptr = &line[strlen(line)] - 1;
    while (ptr > line && isspace(*ptr))
      *ptr-- = 0;		// zero trailing white space
    ptr = line;
    while (isspace(*ptr))
      ptr++;			// skip leading white space

#define SKIPOVER() while ((! isspace(*ptr)) && (0 != *ptr)) ptr++; while (isspace(*ptr)) ptr++;

    if (0 == *ptr) {		// blank line
      robotModel.lock();
      robotModel.print();
      robotModel.unlock();
    } else  if ((! strwcmp("q", ptr)) || (! strwcmp("quit", ptr))) {
      break;
    } else  if (! strwcmp("?", ptr)) {
      printCommands();
    } else if (! strwcmp("don", ptr)) {
      SKIPOVER();
      if (0 == *ptr) {
	debugCommand = debugStatus = true;
      } else if (! strwcmp("status", ptr)) {
	debugStatus = true;
      } else if (! strwcmp("command", ptr)) {
	debugCommand = true;
      } else {
	printf("need a debug target\n");
      }
    } else if (! strwcmp("doff", ptr)) {
      SKIPOVER();
      if (0 == *ptr) {
	debugCommand = debugStatus = false;
      } else 	if (! strwcmp("status", ptr)) {
	debugStatus = false;
      } else if (! strwcmp("command", ptr)) {
	debugCommand = false;
      } else {
	printf("need a debug target\n");
      }
    } else if (! strwcmp("init", ptr)) {
      sendInitCanon(socket_id);
    } else if (! strwcmp("end", ptr)) {
      sendEndCanon(socket_id);
    } else if (! strwcmp("status", ptr)) {
      sendGetStatus(socket_id);
    } else if (! strwcmp("move", ptr)) {
      if (6 == sscanf(ptr, "%*s %lf %lf %lf %lf %lf %lf",
		      &d1, &d2, &d3, &d4, &d5, &d6)) {
	sendMoveTo(socket_id, d1, d2, d3, d4, d5, d6);
      } else {
	printf("need six values, X Y Z R P W\n");
      }
    } else if (! strwcmp("actuate", ptr)) {
      double pos[CRCLRobotModel::JOINT_NUM];
      int num;
      for (num = 0; num < CRCLRobotModel::JOINT_NUM; num++) {
	SKIPOVER();
	if (0 == *ptr) break;	// no more joints to set
	if (1 != sscanf(ptr, "%lf", &d1)) {
	  printf("ignoring bad value for joint position\n");
	  break;		// bad value
	}
	pos[num] = d1;
      }
      if (num > 0) sendActuateJoints(socket_id, pos, num);
      else printf("need a sequence of joint positions\n");
    } else if (! strwcmp("tool", ptr)) {
      SKIPOVER();
      // "off", on <name>", "value <value>"
      if (! strwcmp("off", ptr)) {
	if (0 != sendOpenToolChanger(socket_id)) {
	  printf("can't take tool off\n");
	}
	continue;
      }
      if (! strwcmp("on", ptr)) {
	SKIPOVER();
	if (0 == *ptr) {
	  printf("need a tool name\n");
	} else if (0 != sendCloseToolChanger(socket_id, ptr)) {
	  printf("can't put tool %s on\n", ptr);
	}
      } else if (! strwcmp("value", ptr)) {
	if ((1 != sscanf(ptr, "%*s %lf", &d1)) || (0 != sendSetEndEffector(socket_id, d1))) {
	  printf("can't set tool value to %f\n", d1);
	}
      } else {
	printf("need 'off', 'on <name>', or 'value <value>'\n");
      }
    } else if (! strwcmp("units", ptr)) {
      SKIPOVER();
      if (! strwcmp("length", ptr)) {
	SKIPOVER();
	if ((0 == *ptr) || (0 != sendSetLengthUnits(socket_id, ptr))) {
	  printf("need a valid length unit: meter, millimeter or inch\n");
	}
      } else if (! strwcmp("angle", ptr)) {
	SKIPOVER();
	if ((0 == *ptr) || (0 != sendSetAngleUnits(socket_id, ptr))) {
	  printf("need a valid angle unit: degree or radian\n");
	}
      } else {
	printf("need 'length' or 'angle' and units\n");
      }
    } else if (! strwcmp("speed", ptr)) {
      SKIPOVER();
      if (! strwcmp("trans", ptr)) {
	SKIPOVER();
	if ((1 != sscanf(ptr, "%lf", &d1)) ||
	    (0 != sendSetTransSpeed(socket_id, d1))) {
	  printf("need a valid translational speed\n");
	}
      } else if (! strwcmp("rot", ptr)) {
	SKIPOVER();
	if ((1 != sscanf(ptr, "%lf", &d1)) ||
	    (0 != sendSetRotSpeed(socket_id, d1))) {
	  printf("need a valid rotational speed\n");
	}
      } else if (! strwcmp("joint", ptr)) {
	for (int i = 0; i < CRCLRobotModel::JOINT_NUM; i++) {
	  SKIPOVER();
	  if (0 == *ptr) break;	// no more joints to set
	  if (1 != sscanf(ptr, "%lf", &d1)) {
	    printf("ignoring bad value for joint speed\n");
	    break;		// bad value
	  }
	  robotModel.lock();
	  robotModel.setJointVelocitySetting(d1, i);
	  robotModel.unlock();
	}
      } else {
	printf("need 'trans' or 'rot' and speed, or 'joint' and speeds\n");
      }
    } else if (! strwcmp("report", ptr)) {
      if (4 == sscanf(ptr, "%*s %i %i %i %i", &i1, &i2, &i3, &i4)) {
	if (0 != sendConfigureStatusReport(socket_id, i1, i2, i3, i4)) {
	  printf("need a valid report request\n");
	}
      } else {
	printf("need x x x x for joint pose gripper settings\n");
      }
    } else if (! strwcmp("jointreport", ptr)) {
      if (4 == sscanf(ptr, "%*s %i %i %i %i", &i1, &i2, &i3, &i4)) {
	if (i1 <= 0 || i1 > CRCLRobotModel::JOINT_NUM) {
	  printf("need a joint 1..%d\n", (int) CRCLRobotModel::JOINT_NUM);
	  continue;
	}
	if (0 != sendConfigureJointReports(socket_id, i1, i2, i3, i4)) {
	  printf("need a valid joint report request\n");
	  continue;
	}
      } else {
	printf("need a joint report, <joint> <pos> <tof> <vel>\n");
      }
    } else {
      printf("?\n");
    }

#ifdef HAVE_READLINE_READLINE_H
    free(line);
#endif
  } // while (! feof(stdin))
  
  return 0;
}
