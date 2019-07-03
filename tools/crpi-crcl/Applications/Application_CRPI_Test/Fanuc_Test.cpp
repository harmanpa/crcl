#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include "crpi_robot.h"
#include "crpi_crcl_robot.h"
#include "ulapi.h"

using namespace std;
using namespace crpi_robot;

enum {ROBOT_XML_FILE_NAME_LEN = 256};
#define ROBOT_XML_FILE_DEFAULT "robot.xml"

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

  robotPose A1 = makePose(530, -148, -155, -3.14, 0, 0);
  robotPose A2 = makePose(529, -69, -155, -3.14, 0, 0);
  robotPose A3 = makePose(625, -109, -155, -3.14, 0, 0);

  robotPose B1 = makePose(530, 36, -155, -3.14, 0, 0);
  robotPose B2 = makePose(531, 118, -155, -3.14, 0, 0);
  robotPose B3 = makePose(625, 77, -155, -3.14, 0, 0);

  robotPose C1 = makePose(331, -145, -155, -3.14, 0, 0);
  robotPose C2 = makePose(331, -69, -155, -3.14, 0, 0);
  robotPose C3 = makePose(406, -146, -155, -3.14, 0, 0);
  robotPose C4 = makePose(407, -69, -155, -3.14, 0, 0);

  robotPose D1 = makePose(395, 53, -155, -3.14, 0, 0);
  robotPose D2 = makePose(395, 162, -155, -3.14, 0, 0);

  arm.SetLengthUnits("millimeter");
  arm.SetAngleUnits("radian");
  
#define Z_OFFSET 40
#define OPEN 100
#define CLOSED 0
  
#define PICK(P)					\
  pose = poseUp = P;				\
  poseUp.z += Z_OFFSET;				\
  arm.MoveStraightTo(poseUp);			\
  arm.SetTool(OPEN);				\
  arm.MoveStraightTo(pose);			\
  arm.SetTool(CLOSED);				\
  arm.MoveStraightTo(poseUp)

#define PLACE(P)				\
  pose = poseUp = P;				\
  poseUp.z += Z_OFFSET;				\
  arm.MoveStraightTo(poseUp);			\
  arm.MoveStraightTo(pose);			\
  arm.SetTool(OPEN);				\
  arm.MoveStraightTo(poseUp)

  for (;;) {
    PICK(A3);
    PLACE(D1);
    PICK(B3);
    PLACE(D2);
    PICK(A1);
    PLACE(C1);
    PICK(A2);
    PLACE(C2);
    PICK(B1);
    PLACE(C3);
    PICK(B2);
    PLACE(C4);

    PICK(C4);
    PLACE(B2);
    PICK(C3);
    PLACE(B1);
    PICK(C2);
    PLACE(A2);
    PICK(C1);
    PLACE(A1);
    PICK(D2);
    PLACE(B3);
    PICK(D1);
    PLACE(A3);
  }

  ulapi_exit();

  return 0;
}
