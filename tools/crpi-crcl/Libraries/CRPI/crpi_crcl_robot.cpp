#include "URDF_CRPI.hxx"
#include "DataPrimitives.hxx"
#include "CRCLStatus.hxx"
#include "crpi_crcl_robot.h"
#include "CRCLCommon.h"

#include <stdio.h>
#include <stdlib.h>
#include <stddef.h>
#include <time.h>
#include <math.h>
#include <string> 
#include <iostream> 
#include <fstream>
#include <sstream> 
#include "ulapi.h"

using namespace std;

static bool debug = false;

#define COMMAND_WAIT_POLL_DEFAULT 0.2
#define COMMAND_WAIT_TIME_DEFAULT 3.0
//*! seconds to poll during command wait
static double command_wait_poll = COMMAND_WAIT_POLL_DEFAULT;
//*! seconds to wait for command acknowledge
static double command_wait_time = COMMAND_WAIT_TIME_DEFAULT;

/*!
  @brief Define this to force the sending of CRCLGetStatusType commands.

  Do this when the server is configured to not periodically return
  status messages.
*/
#define SEND_STATUS_REQUESTS 1

static CRCLJointType xmlJointTypeToCRCLJointType(const joint::type_type &a)
{
  if (a == "prismatic") return CRCL_Joint_Type_Prismatic;
  else if (a == "revolute") return CRCL_Joint_Type_Revolute;

  return CRCL_Joint_Type_None;
}

// --- utility functions for building CRCL XML ---

enum {SCHEMA_LOCATION_LEN = 256};
#define SCHEMA_LOCATION_DEFAULT "schemas/CRCLCommandInstance.xsd";
static char schema_location[SCHEMA_LOCATION_LEN] = SCHEMA_LOCATION_DEFAULT;

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

static string buildConfigureJointReports(int commandID, int num)
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

  ss << crclCommandPrefix();
  ss << "  <CRCLCommand xsi:type=\"ConfigureJointReportsType\">\n";
  ss << "    <CommandID>" << commandID << "</CommandID>\n";
  ss << "    <ResetAll>false</ResetAll>\n";

  for (int i = 0; i < num; i++) {
    ss << "    <ConfigureJointReport>\n";
    ss << "      <JointNumber>" << (i+1) << "</JointNumber>\n";
    ss << "      <ReportPosition>true</ReportPosition>\n";
    ss << "      <ReportTorqueOrForce>true</ReportTorqueOrForce>\n";
    ss << "      <ReportVelocity>true</ReportVelocity>\n";
    ss << "    </ConfigureJointReport>\n";
  }
  
  ss << "  </CRCLCommand>\n";
  ss << crclCommandSuffix();
  return ss.str();
}

static string buildConfigureStatusReport(int commandID)
{
  /* Looks like:
  <CRCLCommand xsi:type="ConfigureStatusReportType">
    <CommandID>29</CommandID>
    <ReportJointStatuses>true</ReportJointStatuses>
    <ReportPoseStatus>true</ReportPoseStatus>
    <ReportGripperStatus>true</ReportGripperStatus>
    <ReportSettingsStatus>true</ReportSettingsStatus>
  </CRCLCommand>
  */

  stringstream ss;

  ss << crclCommandPrefix();
  ss << "  <CRCLCommand xsi:type=\"ConfigureStatusReportType\">\n";
  ss << "    <CommandID>" << commandID << "</CommandID>\n";
  ss << "    <ReportJointStatuses>true</ReportJointStatuses>\n";
  ss << "    <ReportPoseStatus>true</ReportPoseStatus>\n";
  ss << "    <ReportGripperStatus>true</ReportGripperStatus>\n";
  ss << "    <ReportSettingsStatus>true</ReportSettingsStatus>\n";
  ss << "  </CRCLCommand>\n";
  ss << crclCommandSuffix();

  return ss.str();
}

static string buildGetStatus()
{
  stringstream ss;

  ss << crclCommandPrefix();
  ss << "  <CRCLCommand xsi:type=\"GetStatusType\">\n";
  // fix the command ID at zero, it's ignored by CRCL server for this command
  ss << "    <CommandID>" << 0 << "</CommandID>\n";
  ss << "  </CRCLCommand>\n";
  ss << crclCommandSuffix();

  return ss.str();
}

static string buildInitCanon(int commandID)
{
  stringstream ss;

  ss << crclCommandPrefix();
  ss << "  <CRCLCommand xsi:type=\"InitCanonType\">\n";
  ss << "    <CommandID>" << commandID << "</CommandID>\n";
  ss << "  </CRCLCommand>\n";
  ss << crclCommandSuffix();

  return ss.str();
}

static string buildMoveTo(int commandID, robotPose pose, bool straight)
{
  stringstream ss;
  double x, y, z;
  double xi, xj, xk;;
  double zi, zj, zk;

  x = pose.x, y = pose.y, z = pose.z;
  RPYtoIJK(pose.xrot, pose.yrot, pose.zrot, &xi, &xj, &xk, &zi, &zj, &zk);

  ss << crclCommandPrefix();
  ss << "  <CRCLCommand xsi:type=\"MoveToType\">\n";
  ss << "    <CommandID>" << commandID << "</CommandID>\n";
  ss << "    <MoveStraight>" << (straight ? "true" : "false") << "</MoveStraight>\n";
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

  return ss.str();
}

static string buildMoveThroughTo(int commandID, robotPose *poses, int num,
				 robotPose *accels, robotPose *vels, robotPose*tols)
{
  stringstream ss;
  double x, y, z;
  double xi, xj, xk;;
  double zi, zj, zk;
  double d1, d2;
  bool straight = true;
  bool coordinated = true;

  /*
    Looks like:

    <CRCLCommand xsi:type="MoveThroughToType">
    <CommandID>71</CommandID>
    <MoveStraight>false</MoveStraight>
    <!-- waypoint with settings -->
    <Waypoint xsi:type="PoseAndSetType">
    <Point>
    <X>2.5</X> <Y>1</Y> <Z>1</Z>
    </Point>
    <XAxis>
    <I>1</I> <J>0</J> <K>0</K>
    </XAxis>
    <ZAxis>
    <I>0</I> <J>0</J> <K>-1</K>
    </ZAxis>
    <Coordinated>true</Coordinated>
    <TransSpeed xsi:type="TransSpeedAbsoluteType">
    <Setting>1.23</Setting>
    </TransSpeed>
    <RotSpeed xsi:type="RotSpeedAbsoluteType">
    <Setting>2.34</Setting>
    </RotSpeed>
    <TransAccel xsi:type="TransAccelAbsoluteType">
    <Setting>3.45</Setting>
    </TransAccel>
    <RotAccel xsi:type="RotAccelAbsoluteType">
    <Setting>4.56</Setting>
    </RotAccel>
    <Tolerance>
    <XPointTolerance>0.1</XPointTolerance>
    <YPointTolerance>0.2</YPointTolerance>
    <ZPointTolerance>0.3</ZPointTolerance>
    <XAxisTolerance>0.4</XAxisTolerance>
    <ZAxisTolerance>0.5</ZAxisTolerance>
    </Tolerance>
    </Waypoint>
    <!-- plain waypoint with no settings -->
    <Waypoint>
    <Point>
    <X>0.5</X> <Y>0</Y> <Z>2</Z>
    </Point>
    <XAxis>
    <I>1</I> <J>0</J> <K>0</K>
    </XAxis>
    <ZAxis>
    <I>0</I> <J>0</J> <K>-1</K>
    </ZAxis>
    </Waypoint>
    <NumPositions>2</NumPositions>
    </CRCLCommand>

  */

  ss << crclCommandPrefix();
  ss << "  <CRCLCommand xsi:type=\"MoveThroughToType\">\n";
  ss << "    <CommandID>" << commandID << "</CommandID>\n";
  ss << "    <MoveStraight>" << (straight ? "true" : "false") << "</MoveStraight>\n";

  for (int i = 0; i < num; i++) {
    x = poses[i].x, y = poses[i].y, z = poses[i].z;
    RPYtoIJK(poses[i].xrot, poses[i].yrot, poses[i].zrot, &xi, &xj, &xk, &zi, &zj, &zk);
    
    ss << "    <Waypoint xsi:type=\"PoseAndSetType\">\n";
    ss << "      <Point>\n";
    ss << "        <X>" << x << "</X> <Y>" << y << "</Y> <Z>" << z << "</Z>\n";
    ss << "      </Point>\n";
    ss << "      <XAxis>\n";
    ss << "        <I>" << xi << "</I> <J>" << xj << "</J> <K>" << xk << "</K>\n";
    ss << "      </XAxis>\n";
    ss << "      <ZAxis>\n";
    ss << "        <I>" << zi << "</I> <J>" << zj << "</J> <K>" << zk << "</K>\n";
    ss << "      </ZAxis>\n";
    ss << "      <Coordinated>" << (coordinated ? "true" : "false") << "</Coordinated>\n";

    if (NULL != vels) {
      d1 = sqrt(vels[i].x*vels[i].x + vels[i].y*vels[i].y + vels[i].z*vels[i].z);
      d2 = sqrt(vels[i].xrot*vels[i].xrot + vels[i].yrot*vels[i].yrot + vels[i].zrot*vels[i].zrot);
      ss << "      <TransSpeed xsi:type=\"TransSpeedAbsoluteType\">\n";
      ss << "        <Setting>" << d1 << "</Setting>\n";
      ss << "      </TransSpeed>\n";
      ss << "      <RotSpeed xsi:type=\"RotSpeedAbsoluteType\">";
      ss << "        <Setting>" << d2 << "</Setting>\n";
      ss << "      </RotSpeed>\n";
    }

    if (NULL != accels) {
      d1 = sqrt(accels[i].x*accels[i].x + accels[i].y*accels[i].y + accels[i].z*accels[i].z);
      d2 = sqrt(accels[i].xrot*accels[i].xrot + accels[i].yrot*accels[i].yrot + accels[i].zrot*accels[i].zrot);
      ss << "      <TransAccel xsi:type=\"TransAccelAbsoluteType\">\n";
      ss << "        <Setting>" << d1 << "</Setting>\n";
      ss << "      </TransAccel>\n";
      ss << "      <RotAccel xsi:type=\"RotAccelAbsoluteType\">\n";
      ss << "        <Setting>" << d2 << "</Setting>\n";
      ss << "      </RotAccel>\n";
    }

    if (NULL != tols) {
      d1 = sqrt(tols[i].x*tols[i].x + tols[i].y*tols[i].y + tols[i].z*tols[i].z);
      d2 = sqrt(tols[i].xrot*tols[i].xrot + tols[i].yrot*tols[i].yrot + tols[i].zrot*tols[i].zrot);
      ss << "      <Tolerance>\n";
      ss << "        <XPointTolerance>" << d1 << "</XPointTolerance>\n";
      ss << "        <YPointTolerance>" << d1 << "</YPointTolerance>\n";
      ss << "        <ZPointTolerance>" << d1 << "</ZPointTolerance>\n";
      ss << "        <XAxisTolerance>" << d2 << "</XAxisTolerance>\n";
      ss << "        <ZAxisTolerance>" << d2 << "</ZAxisTolerance>\n";
      ss << "      </Tolerance>\n";
    }

    ss << "    </Waypoint>\n";
  }
  ss << "    <NumPositions>" << num << "</NumPositions>\n";

  ss << "  </CRCLCommand>\n";
  ss << crclCommandSuffix();

  return ss.str();
}

static string buildMessage(int commandID, const char *message)
{
  stringstream ss;

  ss << crclCommandPrefix();
  ss << "  <CRCLCommand xsi:type=\"MessageType\">\n";
  ss << "    <CommandID>" << commandID << "</CommandID>\n";
  ss << "    <Message>" << message << "</Message>\n";
  ss << "  </CRCLCommand>\n";
  ss << crclCommandSuffix();

  return ss.str();
}

static string buildCloseToolChanger(int commandID, const char *name)
{
  stringstream ss;

  ss << crclCommandPrefix();
  ss << "  <CRCLCommand xsi:type=\"CloseToolChangerType\">\n";
  ss << "    <Name>" << name << "</Name>\n";
  ss << "    <CommandID>" << commandID << "</CommandID>\n";
  ss << "  </CRCLCommand>\n";
  ss << crclCommandSuffix();

  return ss.str();
}

static string buildActuateJoints(int commandID, robotAxes &ra, CRCLRobotModel *rm)
{
  int i;
  stringstream ss;

  /*
    Looks like: 

    <CRCLCommand xsi:type="ActuateJointsType">
    <CommandID>31</CommandID>
    <ActuateJoint>
    <JointNumber>1</JointNumber>
    <JointPosition>1.23</JointPosition>
    <JointDetails xsi:type="JointForceTorqueType">
    <!-- with no Setting -->
    </JointDetails>
    </ActuateJoint>
    <ActuateJoint>
    <JointNumber>3</JointNumber>
    <JointPosition>2.34</JointPosition>
    <JointDetails xsi:type="JointForceTorqueType">
    <!-- with no Setting -->
    </JointDetails>
    </ActuateJoint>
    </CRCLCommand>

  */

  ss << crclCommandPrefix();
  ss << "  <CRCLCommand xsi:type=\"ActuateJointsType\">\n";
  ss << "    <CommandID>" << commandID << "</CommandID>\n";

  rm->lock();
  for (i = 0; i < ra.axes; i++) {
    double d;
    rm->getJointVelocitySetting(&d, i);
    ss << "    <ActuateJoint>\n";
    ss << "      <JointNumber>" << i+1 << "</JointNumber>\n";
    ss << "      <JointPosition>" << ra.axis.at(i) << "</JointPosition>\n";
    ss << "      <JointDetails xsi:type=\"JointSpeedAccelType\">\n";
    ss << "        <JointSpeed>" << d << "</JointSpeed>\n";
    ss << "      </JointDetails>\n";
    ss << "    </ActuateJoint>\n";
  }
  rm->unlock();

  ss << "  </CRCLCommand>\n";
  ss << crclCommandSuffix();
  
  return ss.str();
}

static string buildSetEndEffector(int commandID, double percent)
{
  stringstream ss;

  ss << crclCommandPrefix();
  ss << "  <CRCLCommand xsi:type=\"SetEndEffectorType\">\n";
  ss << "    <CommandID>" << commandID << "</CommandID>\n";
  ss << "    <Setting>" << (0.01 * percent) << "</Setting>\n";
  ss << "  </CRCLCommand>\n";
  ss << crclCommandSuffix();

  return ss.str();
}

static string buildSetTransSpeed(int commandID, double speed)
{
  /*
    Looks like:

    <CRCLCommand xsi:type="SetTransSpeedType">
    <CommandID>123</CommandID>
    <TransSpeed xsi:type="TransSpeedAbsoluteType">
    <Setting>100.0</Setting>
    </TransSpeed>
    </CRCLCommand>

  */

  stringstream ss;

  ss << crclCommandPrefix();
  ss << "  <CRCLCommand xsi:type=\"SetTransSpeedType\">\n";
  ss << "    <CommandID>" << commandID << "</CommandID>\n";
  ss << "    <TransSpeed xsi:type=\"TransSpeedAbsoluteType\">\n";
  ss << "      <Setting>" << speed << "</Setting>\n";
  ss << "    </TransSpeed>\n";
  ss << "  </CRCLCommand>\n";
  ss << crclCommandSuffix();

  return ss.str();
}

static string buildSetTransAccel(int commandID, double accel)
{
  /*
    Looks like:

    <CRCLCommand xsi:type="SetTransAccelType">
    <CommandID>413</CommandID>
    <TransAccel xsi:type="TransAccelAbsoluteType">
    <Setting>100</Setting>
    </TransAccel>
    </CRCLCommand>

  */

  stringstream ss;

  ss << crclCommandPrefix();
  ss << "  <CRCLCommand xsi:type=\"SetTransAccelType\">\n";
  ss << "    <CommandID>" << commandID << "</CommandID>\n";
  ss << "    <TransAccel xsi:type=\"TransAccelAbsoluteType\">\n";
  ss << "      <Setting>" << accel << "</Setting>\n";
  ss << "    </TransAccel>\n";
  ss << "  </CRCLCommand>\n";
  ss << crclCommandSuffix();

  return ss.str();
}

static string buildSetAngleUnits(int commandID, CRCLUnit u)
{
  /*
    Looks like:

    <CRCLCommand xsi:type="SetAngleUnitsType">
    <CommandID>23</CommandID>
    <UnitName>degree</UnitName>
    </CRCLCommand>
  */

  stringstream ss;
  string s;

  if (CRCL_Unit_Degree == u) s = "degree";
  else if (CRCL_Unit_Radian == u) s = "radian";
  else return "";
  
  ss << crclCommandPrefix();
  ss << "  <CRCLCommand xsi:type=\"SetAngleUnitsType\">\n";
  ss << "    <CommandID>" << commandID << "</CommandID>\n";
  ss << "    <UnitName>" << s << "</UnitName>\n";
  ss << "  </CRCLCommand>\n";
  ss << crclCommandSuffix();

  return ss.str();
}

static string buildSetLengthUnits(int commandID, CRCLUnit u)
{
  /*
    Looks like:

    <CRCLCommand xsi:type="SetLengthUnitsType">
    <CommandID>23</CommandID>
    <UnitName>degree</UnitName>
    </CRCLCommand>
  */

  stringstream ss;
  string s;

  if (CRCL_Unit_Meter == u) s = "meter";
  else if (CRCL_Unit_Millimeter == u) s = "millimeter";
  else if (CRCL_Unit_Inch == u) s = "inch";
  else return "";
  
  ss << crclCommandPrefix();
  ss << "  <CRCLCommand xsi:type=\"SetLengthUnitsType\">\n";
  ss << "    <CommandID>" << commandID << "</CommandID>\n";
  ss << "    <UnitName>" << s << "</UnitName>\n";
  ss << "  </CRCLCommand>\n";
  ss << crclCommandSuffix();

  return ss.str();
}

static string buildStopMotion(int commandID)
{
  /*
    Looks like:

    <CRCLCommand xsi:type="StopMotionType">
    <CommandID>53</CommandID>
    <StopCondition>Normal</StopCondition>
    </CRCLCommand>

  */

  stringstream ss;
  
  ss << crclCommandPrefix();
  ss << "  <CRCLCommand xsi:type=\"StopMotionType\">\n";
  ss << "    <CommandID>" << commandID << "</CommandID>\n";
  ss << "    <StopCondition>Normal</StopCondition>\n";
  ss << "  </CRCLCommand>\n";
  ss << crclCommandSuffix();

  return ss.str();
}

static string buildSetEndPoseTolerance(int commandID, robotPose &tols)
{
  /*
    Looks like:

    <CRCLCommand xsi:type="SetEndPoseToleranceType">
    <CommandID>61</CommandID>
    <Tolerance>
    <XPointTolerance>0.1</XPointTolerance>
    <YPointTolerance>0.2</YPointTolerance>
    <ZPointTolerance>0.3</ZPointTolerance>
    <XAxisTolerance>0.4</XAxisTolerance>
    <ZAxisTolerance>0.5</ZAxisTolerance>
    </Tolerance>
    </CRCLCommand>

  */

  stringstream ss;
  double d1, d2;
  
  ss << crclCommandPrefix();
  ss << "  <CRCLCommand xsi:type=\"SetEndPoseToleranceType\">\n";
  ss << "    <CommandID>" << commandID << "</CommandID>\n";

  d1 = sqrt(tols.x*tols.x + tols.y*tols.y + tols.z*tols.z);
  d2 = sqrt(tols.xrot*tols.xrot + tols.yrot*tols.yrot + tols.zrot*tols.zrot);

  ss << "    <Tolerance>\n";
  ss << "      <XPointTolerance>" << d1 << "</XPointTolerance>\n";
  ss << "      <YPointTolerance>" << d1 << "</YPointTolerance>\n";
  ss << "      <ZPointTolerance>" << d1 << "</ZPointTolerance>\n";
  ss << "      <XAxisTolerance>" << d2 << "</XAxisTolerance>\n";
  ss << "      <ZAxisTolerance>" << d2 << "</ZAxisTolerance>\n";
  ss << "    </Tolerance>\n";
  ss << "  </CRCLCommand>\n";
  ss << crclCommandSuffix();

  return ss.str();
}

static string buildSetIntermediatePoseTolerance(int commandID, robotPose *tols)
{
  /*
    Looks like:

    <CRCLCommand xsi:type="SetIntermediatePoseToleranceType">
    <CommandID>61</CommandID>
    <Tolerance>
    <XPointTolerance>0.1</XPointTolerance>
    <YPointTolerance>0.2</YPointTolerance>
    <ZPointTolerance>0.3</ZPointTolerance>
    <XAxisTolerance>0.4</XAxisTolerance>
    <ZAxisTolerance>0.5</ZAxisTolerance>
    </Tolerance>
    </CRCLCommand>

  */

  stringstream ss;
  double d1, d2;
  
  ss << crclCommandPrefix();
  ss << "  <CRCLCommand xsi:type=\"SetIntermediatePoseToleranceType\">\n";
  ss << "    <CommandID>" << commandID << "</CommandID>\n";

  d1 = sqrt(tols->x*tols->x + tols->y*tols->y + tols->z*tols->z);
  d2 = sqrt(tols->xrot*tols->xrot + tols->yrot*tols->yrot + tols->zrot*tols->zrot);

  ss << "    <Tolerance>\n";
  ss << "      <XPointTolerance>" << d1 << "</XPointTolerance>\n";
  ss << "      <YPointTolerance>" << d1 << "</YPointTolerance>\n";
  ss << "      <ZPointTolerance>" << d1 << "</ZPointTolerance>\n";
  ss << "      <XAxisTolerance>" << d2 << "</XAxisTolerance>\n";
  ss << "      <ZAxisTolerance>" << d2 << "</ZAxisTolerance>\n";
  ss << "    </Tolerance>\n";
  ss << "  </CRCLCommand>\n";
  ss << crclCommandSuffix();

  return ss.str();
}

static string buildSetRobotParameters(int commandID, const char *name, const char *value)
{
  /*
    Looks like:

    <CRCLCommand xsi:type="SetRobotParametersType">
    <CommandID>23</CommandID>
    <ParameterSetting>
    <ParameterName>foo</ParameterName>
    <ParameterValue>bar</ParameterValue>
    </ParameterSetting>
    ...
    <ParameterSetting>
    <ParameterName>apple</ParameterName>
    <ParameterValue>orange</ParameterValue>
    </ParameterSetting>
    </CRCLCommand>

  */

  stringstream ss;
  
  ss << crclCommandPrefix();
  ss << "  <CRCLCommand xsi:type=\"SetRobotParametersType\">\n";
  ss << "    <CommandID>" << commandID << "</CommandID>\n";
  ss << "    <ParameterSetting>\n";
  ss << "      <ParameterName>" << name << "</ParameterName>\n";
  ss << "      <ParameterValue>" << value << "</ParameterValue>\n";
  ss << "    </ParameterSetting>\n";
  ss << "  </CRCLCommand>\n";
  ss << crclCommandSuffix();

  return ss.str();
}

// utility functions for converting between XML types and CRCL types

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

namespace crpi_robot
{
  void clientReadCode(void *arg)
  {
    CrpiCrcl *thisptr = reinterpret_cast<CrpiCrcl *>(arg);
    ulapi_integer socket_id;
    ulapi_task_struct *client_read_task_ptr;
    CRCLRobotModel *rm;
    ulapi_integer nchars;
    enum {BUFFERLEN = 4096};
    char inbuf[BUFFERLEN];

    if (debug) cout << "clientReadCode running" << endl;

    socket_id = thisptr->socket_id;
    client_read_task_ptr = thisptr->client_read_task_ptr;
    rm = &thisptr->robotModel;

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

      rm->lock();

      try {
	istringstream is(inbuf);
	auto_ptr<CRCLStatusType> stat(CRCLStatus(is, xml_schema::flags::dont_validate));
	
	rm->setCommandID(stat->CommandStatus().CommandID());
	rm->setStatusID(stat->CommandStatus().StatusID());
	rm->setCommandState(stringToCRCLCommandState(stat->CommandStatus().CommandState()));

	// do units first, these affect the values of pose and joint data
	if (NULL != stat->SettingsStatus()) {
	  if (stat->SettingsStatus()->LengthUnitName().present()) {
	    rm->setLengthUnit(xmlLengthUnitToCRCLUnit(stat->SettingsStatus()->LengthUnitName().get()));
	  }
	  if (stat->SettingsStatus()->AngleUnitName().present()) {
	    rm->setAngleUnit(xmlAngleUnitToCRCLUnit(stat->SettingsStatus()->AngleUnitName().get()));
	  }
	}

	if (NULL != stat->JointStatuses()) {
	  JointStatusesType::JointStatus_sequence &js = stat->JointStatuses()->JointStatus();
	  for (int i = 0; i < js.size(); i++) {
	    if (i < CRCLRobotModel::JOINT_NUM) {
	      if (js[i].JointPosition().present()) rm->setJointPosition(js[i].JointPosition().get(), i);
	      if (js[i].JointTorqueOrForce().present()) rm->setJointEffort(js[i].JointTorqueOrForce().get(), i);
	      if (js[i].JointVelocity().present()) rm->setJointVelocity(js[i].JointVelocity().get(), i);
	    }
	  }
	}

	if (NULL != stat->PoseStatus()) {
	  rm->setPose(stat->PoseStatus()->Pose().Point().X(),
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
	  rm->setGripperName(stat->GripperStatus()->GripperName());
	  if (stat->GripperStatus()->HoldingObject().present()) rm->setGripperValue(stat->GripperStatus()->HoldingObject().get());
	} else {
	  rm->setGripperName("");
	}
      } catch (const xml_schema::exception& e) {
	cerr << e << endl;
      }

      rm->unlock();
      
    } // for (;;)

    ulapi_socket_close(socket_id);
    ulapi_task_delete(client_read_task_ptr);

    printf("client read thread done, closed %d\n", socket_id);

    return;
  }

  LIBRARY_API CrpiCrcl::CrpiCrcl (CrpiRobotParams &params)
  {
    params_ = params;
    filebuf fb;
    int num_joints = 0;
    
    if (fb.open(params_.initPath, ios::in)) {
      istream is(&fb);
      try {
	istream is(&fb);
	typedef auto_ptr<robot> arobot;
	arobot myrobot(robot_(is, xml_schema::flags::dont_validate));
	robot::joint_sequence &js = myrobot->joint();
	if (NULL != myrobot->Debug()) {
	  debug = (myrobot->Debug() ? true : false);
	}
	if (NULL != myrobot->CRCL_Robot()) {
	  if (NULL != myrobot->CRCL_Robot()->Status_Request_Period()) {
	    command_wait_poll = myrobot->CRCL_Robot()->Status_Request_Period().get();
	    if (debug) cout << "CRCL_Robot: Status_Request Period: " << command_wait_poll << endl;
	  }
	  if (NULL != myrobot->CRCL_Robot()->Command_Schema()) {
	    strncpy(schema_location, myrobot->CRCL_Robot()->Command_Schema().get().c_str(), sizeof(schema_location)-1);
	    schema_location[sizeof(schema_location)-1] = 0;
	    if (debug) cout << "CRCL_Robot: Command_Schema: " << schema_location << endl;
	  }
	}
	num_joints = js.size();
	for (int i = 0; i < num_joints; i++) {
	  if (js[i].type() == "prismatic") {
	    SetJointType(i, PRISMATIC);
	  } else if (js[i].type() == "revolute") {
	    SetJointType(i, REVOLUTE);
	  } else {
	    SetJointType(i, REVOLUTE);
	  }
	  if (debug) {
	    cout << "Joint: " << js[i].name() << " " << js[i].type() << endl;
	  }
	}
      } catch (const xml_schema::exception& e) {
	cerr << e << endl;
      }
    } else {
      cerr << "Can't open initialization file " << params_.initPath << endl;
    }
    
    if (debug) {
      cout << "Robot IP address, port: " << params_.tcp_ip_addr << ", " << params.tcp_ip_port << endl;
    }

    if (debug) {
      std::vector<CrpiToolDef>::const_iterator itr;
      cout << "Tools: " << endl;
      for (itr = params_.tools.begin(); itr != params_.tools.end(); ++itr) {
	cout << "Tool " << itr->toolID << ": " << itr->toolName.c_str() << endl;
      }
    }

    ulapi_mutex_init(&socket_write_mutex, 1);
    socket_id = connectRobot(params.tcp_ip_port, params.tcp_ip_addr);
    client_read_task_ptr = NULL;
    startClientReadTask();
    
    maxSpeed_ = 1e6;
    maxAccel_ = 1e6;
    acceleration_ = 1;
    speed_ = 1;

    for (int t = 0; t < (sizeof(axialUnits_))/(sizeof(*axialUnits_)); t++) {
      axialUnits_[t] = RADIAN;
    }
    for (int t = 0; t < (sizeof(jointType_))/(sizeof(*jointType_)); t++) {
      jointType_[t] = REVOLUTE;
    }

    curTool = -1;

    srand(time(NULL));
    commandID = rand();

    int nchars;
    string s;
    CanonReturn ret;

    // send an InitCanonType command to get things started
    s = buildInitCanon(++commandID);
    ulapi_mutex_take(&socket_write_mutex);
    nchars = ulapi_socket_write(socket_id, s.c_str(), s.length());
    ulapi_mutex_give(&socket_write_mutex);
    if (nchars <= 0) {
      // FIXME -- need some way to flag failed startup; see IsValid()
      cerr << "CrpiCrcl(): InitCanon failed" << endl;
    }
    ret = WaitDone(commandID);
    if (CANON_SUCCESS != ret) {
      // Likewise
      cerr << "CrpiCrcl() failed" << endl;
    }

    // configure joint report for all joints
    s = buildConfigureJointReports(++commandID, num_joints);
    ulapi_mutex_take(&socket_write_mutex);
    nchars = ulapi_socket_write(socket_id, s.c_str(), s.length());
    ulapi_mutex_give(&socket_write_mutex);
    if (nchars <= 0) {
      cerr << "CrpiCrcl(): configure joint reports failed" << endl;
    }
    ret = WaitDone(commandID);
    if (CANON_SUCCESS != ret) {
      // Likewise
      cerr << "CrpiCrcl() failed" << endl;
    }

    // configure joint report for all joints
    s = buildConfigureStatusReport(++commandID);
    ulapi_mutex_take(&socket_write_mutex);
    nchars = ulapi_socket_write(socket_id, s.c_str(), s.length());
    ulapi_mutex_give(&socket_write_mutex);
    if (nchars <= 0) {
      cerr << "CrpiCrcl(): configure status report failed" << endl;
    }
    ret = WaitDone(commandID);
    if (CANON_SUCCESS != ret) {
      // Likewise
      cerr << "CrpiCrcl() failed" << endl;
    }
    
    if (debug) cout << "CrpiCrcl()" << endl;
  }

  LIBRARY_API CrpiCrcl::~CrpiCrcl ()
  {
    if (debug) cout << "~CrpiCrcl()" << endl;
  }

  /*! 
    @brief CRCL does not support Cartesian force/torque control.
  */
  LIBRARY_API CanonReturn CrpiCrcl::ApplyCartesianForceTorque (robotPose &robotForceTorque, vector<bool> activeAxes, vector<bool> manipulator)
  {
    return CANON_REJECT;
  }

  /*!
    @brief Not implemented in CRCL.
  */
  LIBRARY_API CanonReturn CrpiCrcl::ApplyJointTorque (robotAxes &robotJointTorque)
  {
    return CANON_REJECT;
  }

  /*!
    @brief Mapped to @c SetEndEffectorType command, with @a percent
    associated with @a Setting
  */
  LIBRARY_API CanonReturn CrpiCrcl::SetTool (double percent)
  {
    ulapi_integer nchars;
    string s = buildSetEndEffector(++commandID, percent);
    
    ulapi_mutex_take(&socket_write_mutex);
    nchars = ulapi_socket_write(socket_id, s.c_str(), s.length());
    ulapi_mutex_give(&socket_write_mutex);
    
    if (nchars <= 0) return CANON_FAILURE;
    return WaitDone(commandID);
  }

  /*!
    @brief Mapped to @c CloseToolChangerType command, with @a targetID
    associated with @a Name
  */
  LIBRARY_API CanonReturn CrpiCrcl::Couple (const char *targetID)
  {
    std::vector<CrpiToolDef>::const_iterator itr;
    int tool = 0;

    for (itr = params_.tools.begin(); itr != params_.tools.end(); ++itr, ++tool) {
      if (! strcmp(targetID, itr->toolName.c_str())) {
	break;
      }
    }
    if (itr == params_.tools.end()) {
      curTool = -1;
      return CANON_FAILURE;
    }

    curTool = tool;

    ulapi_integer nchars;
    string s = buildCloseToolChanger(++commandID, targetID);

    ulapi_mutex_take(&socket_write_mutex);
    nchars = ulapi_socket_write(socket_id, s.c_str(), s.length());
    ulapi_mutex_give(&socket_write_mutex);
    
    if (nchars <= 0) return CANON_FAILURE;
    return WaitDone(commandID);
  }

  /*!
    @brief Mapped to @c MessageType command, with @a message
    associated with @a Message
  */
  LIBRARY_API CanonReturn CrpiCrcl::Message (const char *message)
  {
    ulapi_integer nchars;
    string s = buildMessage(++commandID, message);
    
    ulapi_mutex_take(&socket_write_mutex);
    nchars = ulapi_socket_write(socket_id, s.c_str(), s.length());
    ulapi_mutex_give(&socket_write_mutex);

    if (nchars <= 0) return CANON_FAILURE;
    return WaitDone(commandID);
  }

  /*!
    @brief Mapped to @c MoveToType command, as a straight move, with
    @a pose associated with @a EndPosition
  */
  LIBRARY_API CanonReturn CrpiCrcl::MoveStraightTo (robotPose &pose)
  {
    ulapi_integer nchars;
    string s = buildMoveTo(++commandID, pose, true);

    ulapi_mutex_take(&socket_write_mutex);
    nchars = ulapi_socket_write(socket_id, s.c_str(), s.length());
    ulapi_mutex_give(&socket_write_mutex);

    if (nchars <= 0) return CANON_FAILURE;
    return WaitDone(commandID);
  }

  /*!
    @brief Mapped to @c MoveThroughToType command, with the poses
    set to @c PoseAndSetType
  */
  LIBRARY_API CanonReturn CrpiCrcl::MoveThroughTo (robotPose *poses,
						   int numPoses,
						   robotPose *accelerations,
						   robotPose *speeds,
						   robotPose *tolerances)
  {
    ulapi_integer nchars;
    string s = buildMoveThroughTo(++commandID, poses, numPoses, accelerations, speeds, tolerances);

    ulapi_mutex_take(&socket_write_mutex);
    nchars = ulapi_socket_write(socket_id, s.c_str(), s.length());
    ulapi_mutex_give(&socket_write_mutex);

    if (nchars <= 0) return CANON_FAILURE;
    return WaitDone(commandID);
  }

  /*!
    @brief Mapped to @c MoveToType command, not as a straight move, with
    @a pose associated with @a EndPosition
  */
  LIBRARY_API CanonReturn CrpiCrcl::MoveTo (robotPose &pose)
  {
    ulapi_integer nchars;
    string s = buildMoveTo(++commandID, pose, false);

    ulapi_mutex_take(&socket_write_mutex);
    nchars = ulapi_socket_write(socket_id, s.c_str(), s.length());
    ulapi_mutex_give(&socket_write_mutex);

    if (nchars <= 0) return CANON_FAILURE;
    return WaitDone(commandID);
  }

  /*!
    @brief Returns the robot axes from the internal robot model.

    These are updated on the receipt of a CRCL status message from the
    robot.
  */
  LIBRARY_API CanonReturn CrpiCrcl::GetRobotAxes (robotAxes *axes)
  {
    double d[CRCLRobotModel::JOINT_NUM];
    
    robotModel.lock();
    robotModel.getJointPositions(d, CRCLRobotModel::JOINT_NUM);
    robotModel.unlock();


    for (int i = 0; i < CRCLRobotModel::JOINT_NUM; i++) {
      axes->axis.at(i) = d[i];
    }

    return CANON_SUCCESS;
  }

  /*!
    @brief Cartesian forces are not implemented in CRCL.

    Applications that know the robot kinematics could determine the
    Cartesian force by running the joint efforts from @c
    GetRobotTorques through the inverse Jacobian transpose. This is
    not done here since the robot kinematics are not known to CRPI.
  */
  LIBRARY_API CanonReturn CrpiCrcl::GetRobotForces (robotPose *forces)
  {
    return CANON_REJECT;
  }

  /*!
    @brief Robot I/O is not implemented in CRCL.
  */
  LIBRARY_API CanonReturn CrpiCrcl::GetRobotIO (robotIO *io)
  {
    return CANON_REJECT;
  }

  /*!
    @brief Implemented by reporting from the internal robot model.

    This internal model is updated by periodic @c GetStatusType
    messages that return robot pose information in a @c CRCLStatusType
    message.
  */
  LIBRARY_API CanonReturn CrpiCrcl::GetRobotPose (robotPose *pose)
  {
    double x, y, z;
    double xi, xj, xk;
    double zi, zj, zk;
    double r, p, w;
    robotModel.lock();
    robotModel.getPose(&x, &y, &z, &xi, &xj, &xk, &zi, &zj, &zk);
    IJKtoRPY(xi, xj, xk, zi, zj, zk, &r, &p, &w);
    robotModel.unlock();
    pose->x = x, pose->y = y, pose->z = z;
    pose->xrot = r, pose->yrot = p, pose->zrot = w;
    
    return CANON_SUCCESS;
  }

  /*!
    @brief Cartesian speed is not implemented in CRCL.

    Applications that know the robot kinematics could determine the
    Cartesian speed by running the joint speeds from the robot axes
    version of @c GetRobotSpeed through the Jacobian.  This is not
    done here since the robot kinematics are not known to CRPI.
  */
  LIBRARY_API CanonReturn CrpiCrcl::GetRobotSpeed (robotPose *speed)
  {    
    return CANON_REJECT;
  }

  /*!
    @brief Implemented by reporting from the internal robot model.
  */
  LIBRARY_API CanonReturn CrpiCrcl::GetRobotSpeed (robotAxes *speed)
  {
    double d[CRCLRobotModel::JOINT_NUM];
    
    robotModel.lock();
    robotModel.getJointVelocities(d, CRCLRobotModel::JOINT_NUM);
    robotModel.unlock();

    for (int i = 0; i < CRCLRobotModel::JOINT_NUM; i++) {
      speed->axis.push_back(d[i]);
    }

    return CANON_SUCCESS;
  }

  /*!
    @brief Implemented by reporting from the internal robot model. 

    Returns joint effort, which in general could be torque or force if the
    axis is revolute or prismatic. CRPI assumes revolute; here we report
    the torque as the effort from the CRCL model.
  */
  LIBRARY_API CanonReturn CrpiCrcl::GetRobotTorques (robotAxes *torques)
  {
    double d[CRCLRobotModel::JOINT_NUM];
    
    robotModel.lock();
    robotModel.getJointEffort(d, CRCLRobotModel::JOINT_NUM);
    robotModel.unlock();

    for (int i = 0; i < CRCLRobotModel::JOINT_NUM; i++) {
      torques->axis.push_back(d[i]);
    }

    return CANON_SUCCESS;
  }

  /*!
    @brief Not implemented in CRCL.
  */
  LIBRARY_API CanonReturn CrpiCrcl::MoveAttractor (robotPose &pose)
  {
    return CANON_REJECT;
  }

  /*!
    @brief Mapped to @c ActuateJoints

    Move the axes to the specified target, using previously established
    axis speeds from @c SetAxialSpeeds.
  */
  LIBRARY_API CanonReturn CrpiCrcl::MoveToAxisTarget (robotAxes &axes)
  {
    ulapi_integer nchars;
    string s = buildActuateJoints(++commandID, axes, &robotModel);
    
    ulapi_mutex_take(&socket_write_mutex);
    nchars = ulapi_socket_write(socket_id, s.c_str(), s.length());
    ulapi_mutex_give(&socket_write_mutex);
    
    if (nchars <= 0) return CANON_FAILURE;
    return WaitDone(commandID);
  }

  /*!
    @brief Mapped to CRCL @c SetTransAccelType
  */
  LIBRARY_API CanonReturn CrpiCrcl::SetAbsoluteAcceleration (double acceleration)
  {
    ulapi_integer nchars;
    string s;
    CanonReturn ret;

    if (acceleration > maxAccel_ || acceleration < 0.0f) {
      return CANON_REJECT;
    }

    s = buildSetTransAccel(++commandID, acceleration);
    
    ulapi_mutex_take(&socket_write_mutex);
    nchars = ulapi_socket_write(socket_id, s.c_str(), s.length());
    ulapi_mutex_give(&socket_write_mutex);
    
    if (nchars <= 0) return CANON_FAILURE;
    ret =  WaitDone(commandID);

    if (CANON_SUCCESS == ret) {
      acceleration_ = acceleration;
    }

    return ret;
  }

  /*!
    @brief Mapped to @c SetTransSpeed
  */
  LIBRARY_API CanonReturn CrpiCrcl::SetAbsoluteSpeed (double speed)
  {
    ulapi_integer nchars;
    string s;
    CanonReturn ret;

    if (speed > maxSpeed_ || speed < 0.0f) {
      return CANON_REJECT;
    }

    s = buildSetTransSpeed(++commandID, speed);
    
    ulapi_mutex_take(&socket_write_mutex);
    nchars = ulapi_socket_write(socket_id, s.c_str(), s.length());
    ulapi_mutex_give(&socket_write_mutex);
    
    if (nchars <= 0) return CANON_FAILURE;
    ret =  WaitDone(commandID);

    if (CANON_SUCCESS == ret) {
      speed_ = speed;
    }

    return ret;
  }

  /*!
    @brief Mapped to CRCL @c SetAngleUnits
  */
  LIBRARY_API CanonReturn CrpiCrcl::SetAngleUnits (const char *unitName)
  {
    ulapi_integer nchars;
    string s;
    CRCLUnit angle;
    CanonReturn ret;

    if (! strcmp(unitName, "degree")) angle = CRCL_Unit_Degree;
    else if (! strcmp(unitName, "radian")) angle = CRCL_Unit_Radian;
    else return CANON_FAILURE;

    s = buildSetAngleUnits(++commandID, angle);
    if (s == "") return CANON_FAILURE;
    
    ulapi_mutex_take(&socket_write_mutex);
    nchars = ulapi_socket_write(socket_id, s.c_str(), s.length());
    ulapi_mutex_give(&socket_write_mutex);
    
    if (nchars <= 0) return CANON_FAILURE;
    ret = WaitDone(commandID);
    
    if (CANON_SUCCESS == ret) {
      robotModel.lock();
      robotModel.setAngleUnit(angle);
      robotModel.unlock();
    }

    return ret;
  }

  /*!
    @brief Sets internal parameters of the robot model

    The internal parameters will be applied during calls to
    @c MoveToAxisTarget.
  */
  LIBRARY_API CanonReturn CrpiCrcl::SetAxialSpeeds (double *speeds)
  {
    // FIXME -- need to pass in the array length as an argument
    robotModel.lock();
    for (int i = 0; i < CRCLRobotModel::JOINT_NUM; i++) {
      robotModel.setJointVelocitySetting(speeds[i], i);
    }
    robotModel.unlock();

    return CANON_SUCCESS;
  }
  
  /*!
    @brief Axis units are saved to the internal robot model
  */
  LIBRARY_API CanonReturn CrpiCrcl::SetAxialUnits (const char **unitNames)
  {
    // FIXME -- need to pass the length of the unitNames array as an arg
    CRCLJointType type;
    CRCLUnit unit;
    CanonReturn ret = CANON_SUCCESS;

    robotModel.lock();

    for (int i = 0; i < CRCLRobotModel::JOINT_NUM; i++) {

      robotModel.getJointType(&type, i);

      if (CRCL_Joint_Type_Prismatic == type) {
	if (! strcmp(unitNames[i], "meter")) unit = CRCL_Unit_Meter;
	else if (! strcmp(unitNames[i], "millimeter")) unit = CRCL_Unit_Millimeter;
	else if (! strcmp(unitNames[i], "mm")) unit = CRCL_Unit_Millimeter;
	else if (! strcmp(unitNames[i], "inch")) unit = CRCL_Unit_Inch;
	else unit = CRCL_Unit_None;
      } else if (CRCL_Joint_Type_Revolute == type) {
	if (! strcmp(unitNames[i], "degree")) unit = CRCL_Unit_Degree;
	else if (! strcmp(unitNames[i], "radian")) unit = CRCL_Unit_Radian;
	else unit = CRCL_Unit_None;
      }
      
      if (CRCL_Unit_None == unit) {
	ret = CANON_FAILURE;
      } else {
	robotModel.setJointUnit(unit, i);
      }
    } // for (unitNames)

    robotModel.unlock();
    
    return ret;
  }

  /*!
    @brief Mapped to CRCL @c SetEndPoseTolerance
  */
  LIBRARY_API CanonReturn CrpiCrcl::SetEndPoseTolerance (robotPose &tolerances)
  {
    ulapi_integer nchars;
    string s = buildSetEndPoseTolerance(++commandID, tolerances);
    
    ulapi_mutex_take(&socket_write_mutex);
    nchars = ulapi_socket_write(socket_id, s.c_str(), s.length());
    ulapi_mutex_give(&socket_write_mutex);
    
    if (nchars <= 0) return CANON_FAILURE;
    return WaitDone(commandID);
  }

  /*!
    @brief Mapped to CRCL @c SetIntermediatePoseTolerance
  */
  LIBRARY_API CanonReturn CrpiCrcl::SetIntermediatePoseTolerance (robotPose *tolerances)
  {
    // FIXME -- why is the tolerance a pointer here, and a reference above?
    ulapi_integer nchars;
    string s = buildSetIntermediatePoseTolerance(++commandID, tolerances);
    
    ulapi_mutex_take(&socket_write_mutex);
    nchars = ulapi_socket_write(socket_id, s.c_str(), s.length());
    ulapi_mutex_give(&socket_write_mutex);
    
    if (nchars <= 0) return CANON_FAILURE;
    return WaitDone(commandID);
  }

  /*!
    @brief Mapped to CRCL @c SetLengthUnits
  */
  LIBRARY_API CanonReturn CrpiCrcl::SetLengthUnits (const char *unitName)
  {
    ulapi_integer nchars;
    string s;
    CRCLUnit length;
    CanonReturn ret;

    if (! strcmp(unitName, "meter")) length = CRCL_Unit_Meter;
    else if (! strcmp(unitName, "millimeter")) length = CRCL_Unit_Millimeter;
    else if (! strcmp(unitName, "mm")) length = CRCL_Unit_Millimeter;
    else if (! strcmp(unitName, "inch")) length = CRCL_Unit_Inch;
    else return CANON_FAILURE;

    s = buildSetLengthUnits(++commandID, length);
    if (s == "") return CANON_FAILURE;
    
    ulapi_mutex_take(&socket_write_mutex);
    nchars = ulapi_socket_write(socket_id, s.c_str(), s.length());
    ulapi_mutex_give(&socket_write_mutex);
    
    if (nchars <= 0) return CANON_FAILURE;
    ret =  WaitDone(commandID);

    if (CANON_SUCCESS == ret) {
      robotModel.lock();
      robotModel.setLengthUnit(length);
      robotModel.unlock();
    }

    return ret;
  }

  /*!
    @brief Mapped to CRCL @c SetRobotParameters.

    CRCL has two types of parameter setting messages: @
    SetRobotParametersType for the robot, and @
    SetEndEffectorParametersType for end effectors. CRPI has one, and
    it is mapped to robot parameters.

    The void * @a paramVal is taken to be a char .*
  */
  LIBRARY_API CanonReturn CrpiCrcl::SetParameter (const char *paramName, void *paramVal)
  {
    ulapi_integer nchars;
    string s = buildSetRobotParameters(++commandID, paramName, (const char *) paramVal);

    ulapi_mutex_take(&socket_write_mutex);
    nchars = ulapi_socket_write(socket_id, s.c_str(), s.length());
    ulapi_mutex_give(&socket_write_mutex);

    if (nchars <= 0) return CANON_FAILURE;
    return WaitDone(commandID);
  }

  /*!
    @brief Mapped through a call to @c SetAbsoluteAcceleration

    CRCL doesn't have a SetRelativeAcceleration type, so we convert the
    @a percent to an absolute value by multiplying by the internal
    max acceleration.
  */
  LIBRARY_API CanonReturn CrpiCrcl::SetRelativeAcceleration (double percent)
  {
    return SetAbsoluteAcceleration(percent * maxAccel_);
  }

  /*!
    @brief Mapped through a call to @c SetAbsoluteSpeed

    CRCL doesn't have a SetRelativeSpeed type, so we convert the
    @a percent to an absolute value by multiplying by the internal
    max speed.
  */
  LIBRARY_API CanonReturn CrpiCrcl::SetRelativeSpeed (double percent)
  {
    return SetAbsoluteSpeed(percent * maxSpeed_);
  }

  /*!
    @brief Not implemented in CRCL
  */
  LIBRARY_API CanonReturn CrpiCrcl::SetRobotIO (robotIO &io)
  {
    return CANON_SUCCESS;
  }

  /*!
    @brief Not implemented in CRCL
  */
  LIBRARY_API CanonReturn CrpiCrcl::SetRobotDO (int dig_out, bool val)
  {
    return CANON_SUCCESS;
  }

  /*!
    @brief Mapped to CRCL @c StopMotion, with StopCondition Normal
  */
  LIBRARY_API CanonReturn CrpiCrcl::StopMotion (int condition)
  {
    ulapi_integer nchars;
    string s = buildStopMotion(++commandID);

    ulapi_mutex_take(&socket_write_mutex);
    nchars = ulapi_socket_write(socket_id, s.c_str(), s.length());
    ulapi_mutex_give(&socket_write_mutex);

    if (nchars <= 0) return CANON_FAILURE;
    return WaitDone(commandID);
  }

  /*!
    @brief Extension to CRPI to enable the distinction between
    prismatic and revolute joints
  */
  LIBRARY_API CanonReturn CrpiCrcl::SetJointType(int index, CanonJointType type)
  {
    if (index < 0 || index >= (sizeof(jointType_)/sizeof(*jointType_))) {
      return CANON_FAILURE;
    }

    robotModel.lock();
    switch (type) {
    case PRISMATIC:
      robotModel.setJointType(CRCL_Joint_Type_Prismatic, index);
      break;
    case REVOLUTE:
      robotModel.setJointType(CRCL_Joint_Type_Revolute, index);
      break;
    default:
      robotModel.setJointType(CRCL_Joint_Type_None, index);
      break;
    }
    robotModel.unlock();

    return CANON_SUCCESS;
  }

  /*!
    @brief Extension to CRPI to enable the distinction between
    prismatic and revolute joints
  */
  LIBRARY_API CanonReturn CrpiCrcl::GetJointType(int index, CanonJointType *type)
  {
    CRCLJointType type_;
    CanonReturn ret = CANON_SUCCESS;
    
    if (index < 0 || index >= (sizeof(jointType_)/sizeof(*jointType_))) {
      return CANON_FAILURE;
    }

    robotModel.lock();
    robotModel.getJointType(&type_, index);
    switch (type_) {
    case CRCL_Joint_Type_Prismatic:
      *type = PRISMATIC;
      break;
    case CRCL_Joint_Type_Revolute:
      *type = REVOLUTE;
      break;
    default:
      ret =  CANON_FAILURE;
      break;
    }
    robotModel.unlock();

    return ret;
  }
  
  // handler code
  
  LIBRARY_API ulapi_integer CrpiCrcl::connectRobot(ulapi_integer port, char *host)
  {
    ulapi_integer id;
    id = ulapi_socket_get_client_id(port, host);
    if (id < 0) {
      fprintf(stderr, "can't connect to %s on port %d\n", host, (int) port);
      return -1;
    } else {
      printf("connected to %s on port %d\n", host, (int) port);
    }
    return id;
  }
  
  LIBRARY_API void CrpiCrcl::disconnectRobot(ulapi_integer id)
  {
    ulapi_socket_close(id);
  }

  LIBRARY_API int CrpiCrcl::startClientReadTask()
  {
    if (NULL != client_read_task_ptr) {
      ulapi_task_delete(client_read_task_ptr);
    }
    client_read_task_ptr = ulapi_task_new();
    ulapi_task_start(client_read_task_ptr, clientReadCode, this, ulapi_prio_lowest(), 0);

	return 0;
  }

  LIBRARY_API CanonReturn CrpiCrcl::WaitDone(int commandID_, double seconds)
  {
    ulapi_real commTime;
    ulapi_real doneTime;
    bool waitForDone = false;
    int cid;
    CRCLCommandState cs;
    CanonReturn ret = CANON_FAILURE;

    if (seconds > 0) {
      waitForDone = true;
      doneTime = ulapi_time() + seconds;
    }

    commTime = ulapi_time() + command_wait_time;
    for (bool done = false; !done;) {
      ulapi_sleep(command_wait_poll);
#ifdef SEND_STATUS_REQUESTS
      // first make a status request
      string s = buildGetStatus();
      ulapi_mutex_take(&socket_write_mutex);
      ulapi_socket_write(socket_id, s.c_str(), s.length());
      ulapi_mutex_give(&socket_write_mutex);
#endif  // SEND_STATUS_REQUESTS
      robotModel.lock();
      robotModel.getCommandID(&cid);
      if (commandID_ == cid) {
	robotModel.getCommandState(&cs);
	switch (cs) {
	case CRCL_Done:
	case CRCL_Ready:
	  ret = CANON_SUCCESS;
	  done = true;
	  break;
	case CRCL_Error:
	  ret = CANON_FAILURE;
	  done = true;
	  break;
	default:		// CRCL_Working
	  if (waitForDone && (ulapi_time() > doneTime)) {
	    ret = CANON_FAILURE;
	    done = true;
	  } // else it's still working
	  break;
	} // switch (commandState)
      } else if (ulapi_time() > commTime) {
	// command didn't arrive
	ret = CANON_FAILURE;
	done = true;
      } // else command not received yet, keep waiting
      robotModel.unlock();
    } // for (!done)
    return ret;
  }

} // crpi_robot
