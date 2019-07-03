#include <stdio.h>
#include <iostream>
#include "crpi_robot.h"
#include "crpi_crcl_robot.h"
#include "ulapi.h"

using namespace std;
using namespace crpi_robot;

enum {ROBOT_XML_FILE_NAME_LEN = 256};
#define ROBOT_XML_FILE_DEFAULT "robot.xml"

int main(int argc, char *argv[])
{
  int option;
  char robot_xml_file_name[ROBOT_XML_FILE_NAME_LEN] = ROBOT_XML_FILE_DEFAULT;
  const char *robot_xml_file_name_ptr = robot_xml_file_name;
  CanonReturn ret;
  
  ulapi_opterr = 0;
  for (;;) {
    option = ulapi_getopt(argc, argv, ":r:");
    if (option == -1)
      break;

    switch (option) {
    case 'r':
      strncpy(robot_xml_file_name, ulapi_optarg, sizeof(robot_xml_file_name));
      robot_xml_file_name[sizeof(robot_xml_file_name) - 1] = 0;
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

  ulapi_set_debug(ULAPI_DEBUG_ALL);

  CrpiRobot<CrpiCrcl> arm(robot_xml_file_name_ptr);
  if (CANON_SUCCESS != arm.IsValid()) {
    cerr << "Robot not initialized" << endl;
    return 1;
  }
  cout << "Robot initialized" << endl;

#define DIAGS(yes,sorry,no) \
  if (CANON_SUCCESS == ret)  cout << (yes) << endl; \
  else if (CANON_REJECT == ret) cout << (sorry) << endl; \
  else { \
    cout << (no) << endl; \
    return 1; \
  }

  ret = arm.Message("Message to operator");
  DIAGS("Message sent", "Message not implemented", "Message failed");
  
  ret = arm.SetAngleUnits("degree");
  DIAGS("Set angle units", "Set angle units not implemented", "Set angle units failed");
  
  ret = arm.SetLengthUnits("mm");
  DIAGS("Set length units", "Set length units not implemented", "Set length units failed");
  
  ret = arm.Couple("Para_grip_point");
  DIAGS("Coupled gripper", "Couple not implemented", "Can't couple gripper");
  
  ret = arm.SetAbsoluteAcceleration(12.3);
  DIAGS("Set acceleration", "Set acceleration not implemented", "Can't set acceleration");

  char serialNumber[] = "2981341";
  ret = arm.SetParameter("serialnumber", serialNumber);
  
  ret = arm.StopMotion();
  DIAGS("Stopped motion", "Stop motion not implemented", "Can't stop motion");
  
  robotPose curPose, newPose;
  ret = arm.GetRobotPose(&curPose);
  DIAGS("Got robot pose", "Get robot pose not implemented", "Can't get robot pose");
  curPose.print();

  newPose = curPose;
  newPose.z += 10.0;
  ret = arm.MoveStraightTo(newPose);
  DIAGS("Moved straight", "Move straight not implemented", "Can't move straight");

  ret = arm.GetRobotPose(&curPose);
  DIAGS("Got robot pose", "Get robot pose not implemented", "Can't get robot pose");
  curPose.print();

  robotAxes axes;
  ret = arm.GetRobotAxes(&axes);
  DIAGS("Got robot axes", "Get robot axes not implemented", "Can't get robot axes");
  axes.print();

  robotAxes torque(6);
  for (int i = 0; i < torque.axes; i++) {
    torque.axis.at(i) = i;
  }
  ret = arm.ApplyJointTorque(torque);
  DIAGS("Applied joint torque", "Apply joint torque not implemented", "Can't apply joint torque");

  robotPose endtols;
  endtols.x = 0.1;
  endtols.y = 0.2;
  endtols.z = 0.3;
  endtols.xrot = 0.4;
  endtols.yrot = 0.5;
  endtols.zrot = 0.6;
  ret = arm.SetEndPoseTolerance(endtols);
  DIAGS("Set end pose tolerance", "Set end pose tolerance not implemented", "Can't set end pose tolerance");

  #define WAYPOINTS 3
  robotPose poses[WAYPOINTS];
  robotPose accels[WAYPOINTS];
  robotPose vels[WAYPOINTS];
  robotPose tols[WAYPOINTS];
  for (int i = 0; i < sizeof(poses)/sizeof(*poses); i++) {
    poses[i].x = i+1;
    poses[i].y = i+2;
    poses[i].z = i+3;
    poses[i].xrot = 0.1*(i+1);
    poses[i].yrot = 0.1*(i+2);
    poses[i].zrot = 0.1*(i+3);
    accels[i].x = i+1;
    accels[i].y = i+2;
    accels[i].z = i+3;
    accels[i].xrot = 0.1*(i+1);
    accels[i].yrot = 0.1*(i+2);
    accels[i].zrot = 0.1*(i+3);
    vels[i].x = i+1;
    vels[i].y = i+2;
    vels[i].z = i+3;
    vels[i].xrot = 0.1*(i+1);
    vels[i].yrot = 0.1*(i+2);
    vels[i].zrot = 0.1*(i+3);
    tols[i].x = i+1;
    tols[i].y = i+2;
    tols[i].z = i+3;
    tols[i].xrot = 0.1*(i+1);
    tols[i].yrot = 0.1*(i+2);
    tols[i].zrot = 0.1*(i+3);
  }
  ret = arm.MoveThroughTo(poses, sizeof(poses)/sizeof(*poses), accels, vels, tols);
  DIAGS("Moved through poses", "Move through poses not implemented", "Can't move through poses");
  
  cout << "Complete" << endl;

  ulapi_exit();

  return 0;
}
