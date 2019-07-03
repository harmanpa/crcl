#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <iostream>
#include "crpi_robot.h"
#include "crpi_crcl_robot.h"
#include "ulapi.h"

using namespace std;
using namespace crpi_robot;

enum {ROBOT_XML_FILE_NAME_LEN = 256};
#define ROBOT_XML_FILE_DEFAULT "robot.xml"

double random_v(double lo, double hi)
{
  double u = ((double) rand()) / ((double) RAND_MAX);
  u *= (hi - lo);
  u += lo;

  return u;
}

robotPose makePose(double x, double y, double z, double r, double p, double w)
{
  robotPose pose;

  pose.x = x, pose.y = y, pose.z = z;
  pose.xrot = r, pose.yrot = p, pose.zrot = w;

  return pose;
}

int main(int argc, char *argv[])
{
  int option;
  char robot_xml_file_name[ROBOT_XML_FILE_NAME_LEN] = ROBOT_XML_FILE_DEFAULT;
  const char *robot_xml_file_name_ptr = robot_xml_file_name;
  CanonReturn ret;
  robotPose startPose, newPose;
  double x, y, z;
  double xr, yr, zr;
  bool toggle = false;
  
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
  
  ret = arm.StopMotion();
  
  ret = arm.GetRobotPose(&startPose);
  if (CANON_SUCCESS != ret) {
    cerr << "Can't get robot pose" << endl;
    return 1;
  }
  startPose.print();

  srand(time(NULL));
  for (int i = 0; i < 20; i++) {
    newPose.x = startPose.x + random_v(-100, 100);
    newPose.y = startPose.y + random_v(-100, 100);
    newPose.z = startPose.z + random_v(-100, 100);
    newPose.xrot = startPose.xrot + random_v(-0.1, 0.1);
    newPose.yrot = startPose.yrot + random_v(-0.1, 0.1);
    newPose.zrot = startPose.zrot + random_v(-0.1, 0.1);
    ret = arm.MoveStraightTo(newPose);
    if (CANON_SUCCESS != ret) {
      cerr << "Can't move to new pose" << endl;
      break;
    }
    ret = arm.SetTool(toggle ? 100 : 0);
    if (CANON_SUCCESS != ret) {
      cerr << "Can't set tool" << endl;
      break;
    }
    toggle = !toggle;
    ret = arm.GetRobotPose(&newPose);
    if (CANON_SUCCESS != ret) {
      cerr << "Can't get robot pose" << endl;
      break;
    }
    newPose.print();
  }

  arm.MoveStraightTo(startPose);

  ulapi_exit();

  return 0;
}
