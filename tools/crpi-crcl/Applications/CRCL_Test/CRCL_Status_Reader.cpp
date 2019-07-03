#include "DataPrimitives.hxx"
#include "CRCLStatus.hxx"

#include <stdlib.h>
#include <math.h>
#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include <map>
#include "ulapi.h"

using namespace std;

string lengthUnits(const SettingsStatusType::LengthUnitName_optional& a)
{
  stringstream ss;

  ss << a;

  return ss.str();
}

string angleUnits(const SettingsStatusType::AngleUnitName_optional& a)
{
  stringstream ss;

  ss << a;

  return ss.str();
}

bool holdingObject(const GripperStatusType::HoldingObject_optional& a)
{
  stringstream ss;

  ss << a;

  return (ss.str() != string("0"));
}
  
struct icmp {
  bool operator() (int a, int b) const { return a < b; }
};

struct robotPose {
  double x, y, z;
  double xrot, yrot, zrot;
};

double jointPosition(const JointStatusType::JointPosition_optional& a)
{
  stringstream ss;

  ss << a;

  return strtod(ss.str().c_str(), NULL);
}

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
    auto_ptr<CRCLStatusType> me(CRCLStatus(cin, flags));

    // CommandStatus is required, so it's a field, not a pointer,
    // and no need to test for NULL
    int commandID, statusID;
    map<int, double, icmp> joints;

    commandID = me->CommandStatus().CommandID();
    statusID = me->CommandStatus().StatusID();
    cout << "Command,Status IDs = " << commandID << "," << statusID << endl;

    if (NULL != me->JointStatuses()) {
      JointStatusesType::JointStatus_const_iterator i;
      for (i = me->JointStatuses()->JointStatus().begin();
	   i != me->JointStatuses()->JointStatus().end();
	   i++) {
	joints[i->JointNumber()] = jointPosition(i->JointPosition());
      }
    }

    for (map<int, double, icmp>::iterator i = joints.begin(); i != joints.end(); i++) {
      cout << "Joints[" << i->first << "] = " << i->second << endl;
    }

    if (NULL != me->PoseStatus()) {
      double x, y, z;
      double m11 /* , m12 */ , m13;
      double m21 /* , m22 */ , m23;
      double m31, m32, m33;
      double r, p, w;

      x = me->PoseStatus()->Pose().Point().X();
      y = me->PoseStatus()->Pose().Point().Y();
      z = me->PoseStatus()->Pose().Point().Z();

      m11 = me->PoseStatus()->Pose().XAxis().I();
      m21 = me->PoseStatus()->Pose().XAxis().J();
      m31 = me->PoseStatus()->Pose().XAxis().K();

      m13 = me->PoseStatus()->Pose().ZAxis().I();
      m23 = me->PoseStatus()->Pose().ZAxis().J();
      m33 = me->PoseStatus()->Pose().ZAxis().K();

      // y = z cross x
      // m12 = m23*m31 - m33*m21;
      // m22 = m33*m11 - m13*m31;
      m32 = m13*m21 - m23*m11;

      // rot mat to rpy
      r = atan2(m32, m33);
      p = atan2(-m31, sqrt(m11*m11 + m21*m21));
      w = atan2(m21, m11);

      cout << "Pose: " << x << ", " << y << ", " << z << " / " << r << ", " << p << ", " << w << endl;
    }

    if (NULL != me->GripperStatus()) {
      cout << "GripperName: " << me->GripperStatus()->GripperName() << endl;
      cout << "HoldingObject: " << (holdingObject(me->GripperStatus()->HoldingObject()) ? "true" : "false") << endl;
      if (ParallelGripperStatusType *ddct = dynamic_cast<ParallelGripperStatusType *>(&me->GripperStatus().get())) {
	cout << "ParallelGripper: Separation: " << ddct->Separation() << endl;
      } else if (VacuumGripperStatusType *ddct = dynamic_cast<VacuumGripperStatusType *>(&me->GripperStatus().get())) {
	cout << "VacuumGripper: IsPowered: " << ddct->IsPowered() << endl;
      }
    }
  
    if (NULL != me->SettingsStatus()) {
      // length units would be "meter", "millimeter", or "inch",
      // with "<not present" reported by the lengthUnits() function
      cout << "LengthUnits: " << lengthUnits(me->SettingsStatus()->LengthUnitName()) << endl;
      cout << "AngleUnits: " << angleUnits(me->SettingsStatus()->AngleUnitName()) << endl;
    }

  } catch (const xml_schema::exception& e) {
    cerr << e << endl;
    return 1;
  }

  return 0;
}


