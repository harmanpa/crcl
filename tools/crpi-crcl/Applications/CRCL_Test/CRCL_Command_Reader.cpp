#include "CRCLCommandInstance.hxx"
#include "CRCLCommands.hxx"

#include <stdlib.h>
#include <math.h>
#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include "ulapi.h"

using namespace std;

int main (int argc, char* argv[])
{
  int option;
  bool validate = false;

  ulapi_opterr = 0;
  for (;;) {
    option = ulapi_getopt(argc, argv, ":v");
    if (option == -1)
      break;

    switch (option) {
    case 'v':
      validate = true;
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

  try {
    xml_schema::flags flags = 0;
    if (! validate) flags = flags | xml_schema::flags::dont_validate;
    auto_ptr<CRCLCommandInstanceType> cmd(CRCLCommandInstance(cin, flags));
    CRCLCommandType &ct = cmd->CRCLCommand();
    cout << "CommandID " << ct.CommandID() << endl;

    if (DwellType *dct = dynamic_cast<DwellType *>(&ct)) {
      cout << "DwellType " << dct->DwellTime() << endl;
    } else if (GetStatusType *dct = dynamic_cast<GetStatusType *>(&ct)) {
      cout << "GetStatusType" << endl;
    } else if (SetEndEffectorType *dct = dynamic_cast<SetEndEffectorType *>(&ct)) {
      cout << "Setting " << dct->Setting() << endl;
    } else if (GetStatusType *dct = dynamic_cast<GetStatusType *>(&ct)) {
      cout << "GetStatusType" << endl;
    } else if (ConfigureJointReportsType *dct = dynamic_cast<ConfigureJointReportsType *>(&ct)) {
      cout << "ConfigureJointReportsType" << endl;
      cout << "  ResetAll: " << (dct->ResetAll() ? "true" : "false") << endl;
      ConfigureJointReportsType::ConfigureJointReport_iterator i;
      for (i = dct->ConfigureJointReport().begin();
	   i != dct->ConfigureJointReport().end();
	   i++) {
	cout << "  Joint " << i->JointNumber() << ":" << endl;
	cout << "    ReportPosition:      " << (i->ReportPosition() ? "true" : "false") << endl;
	cout << "    ReportTorqueOrForce: " << (i->ReportTorqueOrForce() ? "true" : "false") << endl;
	cout << "    ReportVelocity:      " << (i->ReportVelocity() ? "true" : "false") << endl;
      }
    } else if (ConfigureStatusReportType *dct = dynamic_cast<ConfigureStatusReportType *>(&ct)) {
      cout << "ConfigureStatusReportType" << endl;
      cout << dct->ReportJointStatuses() << endl;
      cout << dct->ReportPoseStatus() << endl;
      cout << dct->ReportGripperStatus() << endl;
      cout << dct->ReportSettingsStatus() << endl;
    } else if (MoveThroughToType *dct = dynamic_cast<MoveThroughToType *>(&ct)) {
      double x, y, z, xi, xj, xk, zi, zj, zk;
      MoveThroughToType::Waypoint_sequence &waypoints = dct->Waypoint();
      cout << "MoveThroughTo: " << endl;
      for (int i = 0; i < waypoints.size(); i++) {
	x = waypoints[i].Point().X();
	y = waypoints[i].Point().Y();
	z = waypoints[i].Point().Z();
	xi = waypoints[i].XAxis().I();
	xj = waypoints[i].XAxis().J();
	xk = waypoints[i].XAxis().K();
	zi = waypoints[i].ZAxis().I();
	zj = waypoints[i].ZAxis().J();
	zk = waypoints[i].ZAxis().K();
	cout << "  " << x << " " << y << " " << z << endl;
	// try for PoseAndSetType
	PoseAndSetType *pt = dynamic_cast<PoseAndSetType *>(&(waypoints[i]));
	if (NULL != pt) {
	  if (pt->TransSpeed().present()) {
	    TransSpeedAbsoluteType *tsa = dynamic_cast<TransSpeedAbsoluteType *>(&pt->TransSpeed().get());
	    cout << "TransSpeed " << tsa->Setting() << endl;
	  }
	}
      }
    } else if (SetTransAccelType *dct = dynamic_cast<SetTransAccelType *>(&ct)) {
      cout << "SetTransAccel" << endl;
      TransAccelAbsoluteType *ddct = dynamic_cast<TransAccelAbsoluteType *>(&dct->TransAccel());
      if (NULL != ddct) {
	cout << "TransAccel " << ddct->Setting() << endl;
      }
    } else if (SetTransSpeedType *dct = dynamic_cast<SetTransSpeedType *>(&ct)) {
      cout << "SetTransSpeed" << endl;
      if (TransSpeedAbsoluteType *ddct = dynamic_cast<TransSpeedAbsoluteType *>(&dct->TransSpeed())) {
	cout << "TransSpeedAbsolute " << ddct->Setting() << endl;
      } else if (TransSpeedRelativeType *ddct = dynamic_cast<TransSpeedRelativeType *>(&dct->TransSpeed())) {
	cout << "TransSpeedRelative " << ddct->Fraction() << endl;
      }
    } else if (StopMotionType *dct = dynamic_cast<StopMotionType *>(&ct)) {
      cout << "StopMotion " << dct->StopCondition() << endl;
    } else if (SetRobotParametersType *dct = dynamic_cast<SetRobotParametersType *>(&ct)) {
      cout << "SetRobotParameters:" << endl;
      SetRobotParametersType::ParameterSetting_sequence& ddct = dct->ParameterSetting();
      for (int i = 0; i < ddct.size(); i++) {
	cout << ddct[i].ParameterName() << " = " << ddct[i].ParameterValue() << endl;
      }
    } else {
      cout << "Not handled" << endl;
    }
  } catch (const xml_schema::exception& e) {
    cerr << e << endl;
    return 1;
  }

  return 0;
}


