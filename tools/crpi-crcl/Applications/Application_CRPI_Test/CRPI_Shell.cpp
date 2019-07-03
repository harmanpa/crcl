/*!
  CRPI_Shell.cpp

  Interactive application for testing the CRPI interface.
*/
#include "URDF_CRPI.hxx"

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#include <time.h>
#include <iostream>
#include <fstream>
#include "crpi_robot.h"

#include "crpi_crcl_robot.h"

#include "ulapi.h"

using namespace std;
using namespace crpi_robot;

#ifdef HAVE_READLINE_READLINE_H
#include <readline/readline.h>	/* readline */
#include <readline/history.h>	/* using_history */
#endif

using namespace std;

enum {ROBOT_XML_FILE_NAME_LEN = 256};
#define ROBOT_XML_FILE_DEFAULT "robot.xml"

static bool debugStatus = false;
static bool debugCommand = false;

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
  printf("-r <file> : robot configuration file\n");
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
  printf("move <x> <y> <z> <r> <p> <w> :\n  move to Cartesian coordinates\n");
  printf("axes <joint positions ...>\n");
  printf("    move to joint positions\n");
  printf("tool 'off' | 'on <name>' | 'value <value>' :\n");
  printf("    take tool off, put tool <name> on, activate tool with <value>\n");
  printf("units 'length' <unit> | 'angle' <unit> :\n");
  printf("    set the length or angle units, one of:\n");
  printf("    meter | millimeter | inch , degree | radian\n");
}

int main(int argc, char *argv[])
{
  int option;
  char robot_xml_file_name[ROBOT_XML_FILE_NAME_LEN] = ROBOT_XML_FILE_DEFAULT;
  const char *robot_xml_file_name_ptr = robot_xml_file_name;
  CanonReturn ret;
  robotPose pose;
  robotAxes axes;
  double x, y, z;
  double xr, yr, zr;
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
    option = ulapi_getopt(argc, argv, ":r:d?");
    if (option == -1)
      break;

    switch (option) {
    case 'r':
      strncpy(robot_xml_file_name, ulapi_optarg, sizeof(robot_xml_file_name));
      robot_xml_file_name[sizeof(robot_xml_file_name) - 1] = 0;
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
  
  try {
    istream is(&fb);
    xml_schema::flags flags = xml_schema::flags::dont_validate;
    typedef auto_ptr<robot> arobot;
    arobot myrobot(robot_(is, flags));
    if (NULL != myrobot->Debug()) {
      debugCommand = (myrobot->Debug() ? true : false);
      debugStatus = debugCommand;
    }
    if (NULL != myrobot->CrpiType()) {
      if (debugCommand) cout << "CrpiRobot: " << myrobot->CrpiType() << endl;
    }
  } catch (const xml_schema::exception& e) {
    cerr << e << endl;
    return 1;
  }

  /*
    FIXME -- figure out how to instantiate the robot arm at runtime
    based on the configuration file entry for 'CrpiType'. Here we
    code it as a CRCL robot.
  */
  CrpiRobot<CrpiCrcl> arm(robot_xml_file_name_ptr);
  
  if (CANON_SUCCESS != arm.IsValid()) {
    cerr << "Robot not initialized" << endl;
    return 1;
  }
  cout << "Robot initialized" << endl;

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
    } else if (! strwcmp("units", ptr)) {
      SKIPOVER();
      if (! strwcmp("length", ptr)) {
	SKIPOVER();
	if ((0 == *ptr) || (CANON_SUCCESS != arm.SetLengthUnits(ptr))) {
	  printf("need a valid length unit: meter, millimeter or inch\n");
	}
      } else if (! strwcmp("angle", ptr)) {
	SKIPOVER();
	if ((0 == *ptr) || (CANON_SUCCESS != arm.SetAngleUnits(ptr))) {
	  printf("need a valid angle unit: degree or radian\n");
	}
      } else {
	printf("need 'length' or 'angle' and units\n");
      }
    } else if (! strwcmp("move", ptr)) {
      if (6 == sscanf(ptr, "%*s %lf %lf %lf %lf %lf %lf",
		      &d1, &d2, &d3, &d4, &d5, &d6)) {
	pose.x = d1, pose.y = d2, pose.z = d3, pose.xrot = d4, pose.yrot = d5, pose.zrot = d6;
	arm.MoveStraightTo(pose);
      } else {
	printf("need six values, X Y Z R P W\n");
      }
    } else if (! strwcmp("axes", ptr)) {
      int num;
      for (num = 0; num < CRPI_AXES_MAX; num++) {
	SKIPOVER();
	if (0 == *ptr) break;	// no more joints to set
	if (1 != sscanf(ptr, "%lf", &d1)) {
	  printf("ignoring bad value for joint position\n");
	  break;		// bad value
	}
	axes.axis.at(num) = d1;
      }
      arm.MoveToAxisTarget(axes);
    } else {
      printf("?\n");
    }

#ifdef HAVE_READLINE_READLINE_H
    free(line);
#endif
  } // while (! feof(stdin))
  
  return 0;
}
