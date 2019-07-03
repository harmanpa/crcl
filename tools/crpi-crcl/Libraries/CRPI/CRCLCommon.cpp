#include <math.h>
#include <iostream>
#include <string>
#include "CRCLCommon.h"

int IJKtoRPY(double xi, double xj, double xk, double zi, double zj, double zk, double *r, double *p, double *w)
{
  double yk;
  // y = z cross x
  yk = zi*xj - zj*xi;

  // rot mat to rpy
  *r = atan2(yk, zk);
  *p = atan2(-xk, sqrt(xi*xi + xj*xj));
  *w = atan2(xj, xi);

  return 0;
}

int RPYtoIJK(double r, double p, double w, double *xi, double *xj, double *xk, double *zi, double *zj, double *zk)
{
  double sa, ca, sb, cb, sg, cg; // alpha,beta,gamma = yaw,pitch,roll

  sa = sin(w), ca = cos(w);
  sb = sin(p), cb = cos(p);
  sg = sin(r), cg = cos(r);

  *xi = ca*cb;
  *xj = sa*cb;
  *xk = -sb;

  *zi = ca*sb*cg + sa*sg;
  *zj = sa*sb*cg - ca*sg;
  *zk = cb*cg;

  return 0;
}

using namespace std;

CRCLCommandState stringToCRCLCommandState(string s)
{
  if (s == "CRCL_Done") return CRCL_Done;
  else if (s == "CRCL_Working") return CRCL_Working;
  else if (s == "CRCL_Ready") return CRCL_Ready;
  return CRCL_Error;
}

string CRCLCommandStateToString(CRCLCommandState s)
{
  switch (s) {
  case CRCL_Done: return "CRCL_Done"; break;
  case CRCL_Working: return "CRCL_Working"; break;
  case CRCL_Ready: return "CRCL_Ready"; break;
  default: return "CRCL_Error"; break;
  }
  return "CRCL_Error";
}

CRCLUnit stringToCRCLUnit(string s)
{
  if (s == "meter") return CRCL_Unit_Meter;
  else if (s == "millimeter") return CRCL_Unit_Millimeter;
  else if (s == "inch") return CRCL_Unit_Inch;
  else if (s == "degree") return CRCL_Unit_Degree;
  else if (s == "radian") return CRCL_Unit_Radian;
  return CRCL_Unit_None;
}

string CRCLUnitToString(CRCLUnit s)
{
  switch (s) {
  case CRCL_Unit_Meter: return "meter"; break;
  case CRCL_Unit_Millimeter: return "millimeter"; break;
  case CRCL_Unit_Inch: return "inch"; break;
  case CRCL_Unit_Degree: return "degree"; break;
  case CRCL_Unit_Radian: return "radian"; break;
  default: return "none"; break;
  }
  return "none";
}

string CRCLJointTypeToString(CRCLJointType s)
{
  switch (s) {
  case CRCL_Joint_Type_Prismatic: return "prismatic"; break;
  case CRCL_Joint_Type_Revolute: return "revolute"; break;
  default: return "none"; break;
  }
  return "none";
}

CRCLJointType stringToCRCLJointType(std::string s)
{
  if (s == "prismatic") return CRCL_Joint_Type_Prismatic;
  else if (s == "revolute") return CRCL_Joint_Type_Revolute;
  return CRCL_Joint_Type_None;
}

// this expects user units, and converts into robot units
int CRCLRobotModel::setPose(double x_, double y_, double z_,
			    double xi_, double xj_, double xk_,
			    double zi_, double zj_, double zk_)
{
  x = toRobotLength(x_), y = toRobotLength(y_), z = toRobotLength(z_);
  // this is a unit vector, no need to change units
  xi = xi_, xj = xj_, xk = xk_;
  zi = zi_, zj = zj_, zk = zk_;

  return 0;
}

// this converts robot units and returns user units
int CRCLRobotModel::getPose(double *x_, double *y_, double *z_,
			    double *xi_, double *xj_, double *xk_,
			    double *zi_, double *zj_, double *zk_)
{
  *x_ = toUserLength(x), *y_ = toUserLength(y), *z_ = toUserLength(z);
  // no need to change unit vector units
  *xi_ = xi, *xj_ = xj, *xk_ = xk;
  *zi_ = zi, *zj_ = zj, *zk_ = zk;

  return 0;
}

int CRCLRobotModel::setJointType(CRCLJointType type, int index)
{
  if (index < 0 || index >= JOINT_NUM) return -1;

  else jointType[index] = type;

  return 0;
}

int CRCLRobotModel::getJointType(CRCLJointType *type, int index)
{
  if (index < 0 || index >= JOINT_NUM) return -1;

  else *type = jointType[index];

  return 0;
}

int CRCLRobotModel::setJointUnit(CRCLUnit unit, int index)
{
  if (index < 0 || index >= JOINT_NUM) return -1;

  else jointUnit[index] = unit;

  return 0;
}

int CRCLRobotModel::getJointUnit(CRCLUnit *unit, int index)
{
  if (index < 0 || index >= JOINT_NUM) return -1;

  else *unit = jointUnit[index];

  return 0;
}

int CRCLRobotModel::setJointPosition(double j, int index)
{
  if (index < 0 || index >= JOINT_NUM) return -1;

  if (CRCL_Joint_Type_Prismatic == jointType[index])
    jointPosition[index] = toRobotLength(j);
  else if (CRCL_Joint_Type_Revolute == jointType[index])
    jointPosition[index] = toRobotAngle(j);
  else jointPosition[index] = j;

  return 0;
}

int CRCLRobotModel::setJointPositions(const double *j, size_t size)
{
  int max = size;
  int i;

  if (max > JOINT_NUM) max = JOINT_NUM;

  for (i = 0; i < max; i++) {
    setJointPosition(j[i], i);
  }

  return 0;
}

int CRCLRobotModel::getJointPosition(double *j, int index)
{
  if (index < 0 || index >= JOINT_NUM) return -1;
  
  if (CRCL_Joint_Type_Prismatic == jointType[index])
    *j = toUserLength(jointPosition[index]);
  else if (CRCL_Joint_Type_Revolute == jointType[index])
    *j = toUserAngle(jointPosition[index]);
  else *j = jointPosition[index];

  return 0;
}

int CRCLRobotModel::getJointPositions(double *j, size_t size)
{
  int max = size;
  int i;

  if (max > JOINT_NUM) max = JOINT_NUM;

  for (i = 0; i < max; i++) {
    getJointPosition(&j[i], i);
  }

  return 0;
}

int CRCLRobotModel::setJointVelocity(double velocity, int index)
{
  if (index < 0 || index >= JOINT_NUM) return -1;

  if (CRCL_Joint_Type_Prismatic == jointType[index])
    jointVelocity[index] = toRobotLength(velocity);
  else if (CRCL_Joint_Type_Revolute == jointType[index])
    jointVelocity[index] = toRobotAngle(velocity);
  else jointVelocity[index] = velocity;

  return 0;
}

int CRCLRobotModel::getJointVelocity(double *velocity, int index)
{
  if (index < 0 || index >= JOINT_NUM) return -1;
  
  if (CRCL_Joint_Type_Prismatic == jointType[index])
    *velocity = toUserLength(jointVelocity[index]);
  else if (CRCL_Joint_Type_Revolute == jointType[index])
    *velocity = toUserAngle(jointVelocity[index]);
  else *velocity = jointVelocity[index];

  return 0;
}

int CRCLRobotModel::getJointVelocities(double *velocitys, size_t size)
{
  int max = size;
  int i;

  if (max > JOINT_NUM) max = JOINT_NUM;

  for (i = 0; i < max; i++) {
    getJointVelocity(&velocitys[i], i);
  }

  return 0;
}

// FIXME -- update these to change between toUser, toRobot units

int CRCLRobotModel::setJointEffort(double effort, int index)
{
  if (index < 0 || index >= JOINT_NUM) return -1;

  else jointEffort[index] = effort;

  return 0;
}

int CRCLRobotModel::getJointEffort(double *effort, int index)
{
  if (index < 0 || index >= JOINT_NUM) return -1;

  else *effort = jointEffort[index];

  return 0;
}

int CRCLRobotModel::getJointEfforts(double *efforts, size_t size)
{
  int max = size;
  int i;

  if (max > JOINT_NUM) max = JOINT_NUM;

  for (i = 0; i < max; i++) {
    getJointEffort(&efforts[i], i);
  }

  return 0;
}

int CRCLRobotModel::setJointVelocitySetting(double vel, int index)
{
  if (index < 0 || index >= JOINT_NUM) return -1;

  if (CRCL_Joint_Type_Prismatic == jointType[index])
    jointVelocitySetting[index] = toRobotLength(vel);
  else if (CRCL_Joint_Type_Revolute == jointType[index])
    jointVelocitySetting[index] = toRobotAngle(vel);
  else jointVelocitySetting[index] = vel;

  return 0;
}

int CRCLRobotModel::getJointVelocitySetting(double *vel, int index)
{
  if (index < 0 || index >= JOINT_NUM) return -1;
  
  if (CRCL_Joint_Type_Prismatic == jointType[index])
    *vel = toUserLength(jointVelocitySetting[index]);
  else if (CRCL_Joint_Type_Revolute == jointType[index])
    *vel = toUserAngle(jointVelocitySetting[index]);
  else *vel = jointVelocitySetting[index];

  return 0;
}

int CRCLRobotModel::setTransSpeed(double v)
{
  if (v < 0) return -1;

  transSpeed = toRobotLength(v);

  return 0;
}

int CRCLRobotModel::getTransSpeed(double *v)
{
  if (NULL == v) return -1;
  
  *v = toUserLength(transSpeed);

  return 0;
}

int CRCLRobotModel::setRotSpeed(double v)
{
  if (v < 0) return -1;
  
  rotSpeed = toRobotAngle(v);

  return 0;
}

int CRCLRobotModel::getRotSpeed(double *v)
{
  if (NULL == v) return -1;
  
  *v = toUserAngle(rotSpeed);

  return 0;
}

int CRCLRobotModel::setCommandID(int commandID_)
{
  commandID = commandID_;

  return 0;
}

int CRCLRobotModel::getCommandID(int *commandID_)
{
  *commandID_ = commandID;

  return 0;
}

int CRCLRobotModel::setStatusID(int statusID_)
{
  statusID = statusID_;

  return 0;
}

int CRCLRobotModel::getStatusID(int *statusID_)
{
  *statusID_ = statusID;

  return 0;
}

int CRCLRobotModel::setCommandState(CRCLCommandState s)
{
  commandState = s;

  return 0;
}

int CRCLRobotModel::getCommandState(CRCLCommandState *s)
{
  *s = commandState;

  return 0;
}

int CRCLRobotModel::setGripperName(std::string s)
{
  gripperName = s;

  return 0;
}

int CRCLRobotModel::getGripperName(std::string &s)
{
  s = gripperName;

  return 0;
}

int CRCLRobotModel::setGripperValue(double v)
{
  if (v < 0) gripperValue = 0;
  else if (v > 1) gripperValue = 1;
  else gripperValue = v;

  return 0;
}

int CRCLRobotModel::getGripperValue(double *v)
{
  *v = gripperValue;

  return 0;
}

int CRCLRobotModel::setHoldingObject(bool b)
{
  holdingObject = b;

  return 0;
}

int CRCLRobotModel::getHoldingObject(bool *b)
{
  *b = holdingObject;

  return 0;
}
  
int CRCLRobotModel::setLengthUnit(CRCLUnit u)
{
  lengthUnit = u;

  return 0;
}

int CRCLRobotModel::getLengthUnit(CRCLUnit *u)
{
  *u = lengthUnit;

  return 0;
}
  
int CRCLRobotModel::setAngleUnit(CRCLUnit u)
{
  angleUnit = u;

  return 0;
}

int CRCLRobotModel::getAngleUnit(CRCLUnit *u)
{
  *u = angleUnit;

  return 0;
}

double CRCLRobotModel::toRobotLength(double u)
{
  switch (lengthUnit) {
  case CRCL_Unit_Millimeter: return u * 0.001; break;
  case CRCL_Unit_Inch: return u * 0.0254; break;
  default: return u; break;
  }
  return u;
}

double CRCLRobotModel::toUserLength(double r)
{
  switch (lengthUnit) {
  case CRCL_Unit_Millimeter: return r * 1000.0; break;
   case CRCL_Unit_Inch: return r * 39.3700787401574; break;
  default: return r; break;
  }
  return r;
}

double CRCLRobotModel::toRobotAngle(double u)
{
  switch (angleUnit) {
  case CRCL_Unit_Degree: return u * 0.017453292519943; break;
  default: return u; break;
  }
  return u;
}

double CRCLRobotModel::toUserAngle(double r)
{
  switch (angleUnit) {
  case CRCL_Unit_Degree: return r *  57.2957795130823; break;
  default: return r;
  }
  return r;
}  

void CRCLRobotModel::print()
{
  double r, p, w;

  IJKtoRPY(xi, xj, xk, zi, zj, zk, &r, &p, &w);
  // rpy will be in radians, the same as robot units
  
  cout << "Pose: " << toUserLength(x) << " " << toUserLength(y) << " " << toUserLength(z) << " / " << toUserAngle(r) << " " << toUserAngle(p) << " " << toUserAngle(w) << endl;

  cout << "Joints:" << endl;
  for (int i = 0; i < JOINT_NUM; i++) {
    if (CRCL_Joint_Type_Prismatic == jointType[i])
      cout << i << ": prismatic, position " << toUserLength(jointPosition[i]) << ", velocity " << toUserLength(jointVelocity[i]) << ", force " << toUserLength(jointEffort[i]) << endl;
    else if (CRCL_Joint_Type_Revolute == jointType[i])
      cout << i << ": revolute, position " << toUserAngle(jointPosition[i]) << ", velocity " << toUserAngle(jointVelocity[i]) << ", torque " << toUserAngle(jointEffort[i]) << endl;
    else
      cout << i << ": no type" << endl;
  }

  cout << "TransSpeed: " << toUserLength(transSpeed) << endl;
  cout << "RotSpeed: " << toUserAngle(rotSpeed) << endl;
  cout << "JointSpeeds: ";
  for (int i = 0; i < JOINT_NUM; i++) {
    if (CRCL_Joint_Type_Prismatic == jointType[i])
      cout << " " << toUserLength(jointVelocitySetting[i]);
    else if (CRCL_Joint_Type_Revolute == jointType[i])
      cout << " " << toUserAngle(jointVelocitySetting[i]);
    else
      cout << " " << jointVelocitySetting[i];
  }
  cout << endl;

  if (gripperName != "") {
    cout << "GripperName: " << gripperName << endl;
    cout << "GripperValue: " << gripperValue << endl;
    cout << "HoldingObject: " << (holdingObject ? "true" : "false") << endl;
  }

  cout << "Command,StatusID: " << commandID << "," << statusID << endl;
  cout << "CommandState: " << CRCLCommandStateToString(commandState) << endl;

  cout << "LengthUnit: " << CRCLUnitToString(lengthUnit) << endl;
  cout << "AngleUnit: " << CRCLUnitToString(angleUnit) << endl;
}

void SettingsReport::print()
{
  
  cout << "Reporting: ";
  cout << (_reportPoseStatus ? "Poses " : "");
  cout << (_reportGripperStatus ? "Grippers " : "");
  cout << (_reportSettingsStatus ? "Settings " : "");
  if (_reportJointStatuses) {
    cout << "Joints" << endl;
    for (int i = 0; i < CRCLRobotModel::JOINT_NUM; i++) {
      cout << " " << i+1;
      if (jointReport[i].reportPosition()) cout << " Position";
      if (jointReport[i].reportTorqueOrForce()) cout << " TorqueOrForce";
      if (jointReport[i].reportVelocity()) cout << " Velocity";
      cout << endl;
    }
  }
  cout << endl;
}
