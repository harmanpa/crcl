/*!
  This is a scratch test program specifically for a 7-DOF Motoman, serving
  as a sample for sending joint moves.
*/


#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <vector>
#include "crpi_robot.h"
#include "crpi_crcl_robot.h"
#include "ulapi.h"

using namespace std;
using namespace crpi_robot;

enum {ROBOT_XML_FILE_NAME_LEN = 256};
#define ROBOT_XML_FILE_DEFAULT "robot.xml"

static robotAxes makeAxes(double j0, double j1, double j2, double j3, double j4, double j5, double j6)
{
  robotAxes axes(7);

  axes.axis.at(0) = j0;
  axes.axis.at(1) = j1;
  axes.axis.at(2) = j2;
  axes.axis.at(3) = j3;
  axes.axis.at(4) = j4;
  axes.axis.at(5) = j5;
  axes.axis.at(6) = j6;

  return axes;
}

static void printAxes(robotAxes &axes)
{
  for (int i = 0; i < 7; i++) {
    printf("%f ", axes.axis.at(i));
  }
  printf("\n");
}

int main(int argc, char *argv[])
{
  int option;
  char robot_xml_file_name[ROBOT_XML_FILE_NAME_LEN] = ROBOT_XML_FILE_DEFAULT;
  const char *robot_xml_file_name_ptr = robot_xml_file_name;
  CanonReturn ret;
  robotPose pose, poseUp;

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
    fprintf(stderr, "Robot not initialized\n");
    return 1;
  }
  printf("Robot initialized\n");

  // the Motoman CRCL server only supports encoder units, and ignores requests
  // to change them, but we'll set them here anyway
  const char *axialUnits[] = {"degree", "degree", "degree", "degree", "degree", "degree", "degree"};
  arm.SetAxialUnits(axialUnits);

  // speed of about 100 is decent for the Motoman
  double speeds[] = {101, 102, 103, 104, 105, 106, 107};
  arm.SetAxialSpeeds(speeds);

  // some joint taught points, in encoder units due to the Motoman CRCL
  // server not supporting axial unit changing
  vector<robotAxes> points;
  robotAxes retAxes;
  points.push_back(makeAxes(54400, 8500, -116000, 0, -67000, -34000, 0));
  points.push_back(makeAxes(13300, -10000, -105000, 23200, -45400, -48000, -18000));
  points.push_back(makeAxes(115700, 24300, -84686, -68600, -84000, -19000, 1150));
      
  for (int i = 0; i < points.size(); i++) {
    arm.MoveToAxisTarget(points[i]);
    if (CANON_SUCCESS != arm.GetRobotAxes(&retAxes)) {
      fprintf(stderr, "Can't get robot axes\n");
      return 1;
    }
    printAxes(retAxes);
  }

#if 0
  // some Cartesian taught points
  
  robotPose Motoman_A1 = makePose(760, 127, -308, -180, 0, 0);
  robotPose Motoman_A2 = makePose(760, 236, -308, -180, 0, 0);
  robotPose Motoman_A3 = makePose(678, 143, -308, -180, 0, 0);
  robotPose Motoman_A4 = makePose(678, 222, -308, -180, 0, 0);

  robotPose Motoman_B1 = makePose(543, 127, -308, -180, 0, 0);
  robotPose Motoman_B2 = makePose(547, 236, -308, -180, 0, 0);
  robotPose Motoman_C1 = makePose(573, -67, -308, -180, 0, 0);

  robotPose Motoman_C2 = makePose(576, -12, -308, -180, 0, 0);
  robotPose Motoman_C3 = makePose(518, -67, -308, -180, 0, 0);
  robotPose Motoman_C4 = makePose(518, -12, -308, -180, 0, 0);

  // could do something with these, but for now we'll jus
  // test joint motion
#endif
  
  ulapi_exit();

  return 0;
}
