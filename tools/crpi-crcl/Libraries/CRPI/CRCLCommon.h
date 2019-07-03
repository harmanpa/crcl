#ifndef CRCLCOMMON_H
#define CRCLCOMMON_H

#include <string>
#include "ulapi.h"

/*!
  @brief Converts CRCL's IJK unit vector format for orientations to
  roll-pitch-yaw

  @param xi,xj,xk,zi,zj,zk are the X and Z unit vector elements
  @param r,p,w are the converted roll, pitch, and yaw

  @return 0 on success, non-zero on failure
 */
extern int IJKtoRPY(double xi, double xj, double xk, double zi, double zj, double zk, double *r, double *p, double *w);


/*!
  @brief Converts roll-pitch-yaw into CRCL's IJK unit vector format

  @param r,p,w are the roll, pitch, and yaw
  @param xi,xj,xk,zi,zj,zk are the converted X and Z unit vector elements

  @return 0 on success, non-zero on failure.
 */
extern int RPYtoIJK(double r, double p, double w, double *xi, double *xj, double *xk, double *zi, double *zj, double *zk);

typedef enum {
  CRCL_Done = 1,
  CRCL_Error,
  CRCL_Working,
  CRCL_Ready,
} CRCLCommandState;

extern CRCLCommandState stringToCRCLCommandState(std::string s);

extern std::string CRCLCommandStateToString(CRCLCommandState s);

typedef enum {
  CRCL_Unit_None = 0,
  CRCL_Unit_Meter,
  CRCL_Unit_Millimeter,
  CRCL_Unit_Inch,
  CRCL_Unit_Radian,
  CRCL_Unit_Degree,
} CRCLUnit;

typedef enum {
  CRCL_Joint_Type_None = 0,
  CRCL_Joint_Type_Prismatic,
  CRCL_Joint_Type_Revolute,
} CRCLJointType;

extern CRCLUnit stringToCRCLUnit(std::string s);

extern std::string CRCLUnitToString(CRCLUnit s);

extern std::string CRCLJointTypeToString(CRCLJointType s);

extern CRCLJointType stringToCRCLJointType(std::string s);

class CRCLRobotModel {
  
public:

  CRCLRobotModel() {
    x = y = z = 0;
    xi = 1, xj = xk = 0;
    zi = zj = 0, zk = 1;
    for (int i = 0; i < JOINT_NUM; i++) {
      jointType[i] = CRCL_Joint_Type_None;
      jointUnit[i] = CRCL_Unit_None;
      jointPosition[i] = 0;
      jointVelocity[i] = 0;
      jointEffort[i] = 0;
      jointVelocitySetting[i] = 1;
    }
    transSpeed = 1;
    rotSpeed = 1;
    commandID = 0;
    statusID = 0;
    commandState = CRCL_Done;
    gripperName = "";
    gripperValue = 0;
    holdingObject = false;
    lengthUnit = CRCL_Unit_Meter;
    angleUnit = CRCL_Unit_Radian;
    isTimer = false;
    ulapi_mutex_init(&mutex, 1);
  }

  enum {JOINT_NUM = 8};

  int setPose(double x, double y, double z,
	      double xi, double xj, double xk,
	      double zi, double zj, double zk);
  int getPose(double *x, double *y, double *z,
	      double *xi, double *xj, double *xk,
	      double *zi, double *zj, double *zk);

  int setJointType(CRCLJointType type, int index);
  int getJointType(CRCLJointType *type, int index);

  int setJointUnit(CRCLUnit unit, int index);
  int getJointUnit(CRCLUnit *unit, int index);

  int setJointPosition(double pos, int index);
  int getJointPosition(double *pos, int index);

  int setJointPositions(const double *pos, size_t size);
  int getJointPositions(double *pos, size_t size);

  int setJointVelocity(double vel, int index);
  int getJointVelocity(double *vel, int index);
  int getJointVelocities(double *vel, size_t size);

  int setJointEffort(double eff, int index);
  int getJointEffort(double *eff, int index);
  int getJointEfforts(double *eff, size_t size);

  int setJointVelocitySetting(double vel, int index);
  int getJointVelocitySetting(double *vel, int index);

  int setTransSpeed(double v);
  int getTransSpeed(double *v);

  int setRotSpeed(double v);
  int getRotSpeed(double *v);

  int setCommandID(int commandID);
  int getCommandID(int *commandID);

  int setStatusID(int statusID);
  int getStatusID(int *statusID);

  int setCommandState(CRCLCommandState s);
  int getCommandState(CRCLCommandState *s);

  int setGripperName(std::string s);
  int getGripperName(std::string &s);

  int setGripperValue(double v);
  int getGripperValue(double *v);

  int setHoldingObject(bool b);
  int getHoldingObject(bool *b);
  
  int setLengthUnit(CRCLUnit u);
  int getLengthUnit(CRCLUnit *u);
  
  int setAngleUnit(CRCLUnit u);
  int getAngleUnit(CRCLUnit *u);

  double toRobotLength(double u);
  double toUserLength(double r);
  double toRobotAngle(double u);
  double toUserAngle(double r);
  
  void print();
  void lock() {ulapi_mutex_take(&mutex);}
  void unlock() {ulapi_mutex_give(&mutex);}
  void setTimer(double when) {expTime = when; isTimer = true;}
  bool expTimer() {if (isTimer && (ulapi_time() >= expTime)) return true; return false;}

private:
  
  double x, y, z;
  double xi, xj, xk;
  double zi, zj, zk;
  CRCLJointType jointType[JOINT_NUM];
  CRCLUnit jointUnit[JOINT_NUM];
  double jointPosition[JOINT_NUM];
  double jointVelocity[JOINT_NUM];
  double jointEffort[JOINT_NUM];
  double jointVelocitySetting[JOINT_NUM];
  double transSpeed;
  double rotSpeed;
  int commandID;
  int statusID;
  CRCLCommandState commandState;
  std::string gripperName;
  double gripperValue;
  bool holdingObject;
  /*
    Internally, lengths and angles are stored in meters and radians.
    When setting or getting lengths and angles in user units, the
    access functions do the conversions. 
  */
  CRCLUnit lengthUnit;
  CRCLUnit angleUnit;

  bool isTimer;			/* true if the timer is set */
  double expTime;		/* when timer will expire */
  ulapi_mutex_struct mutex;
};

class JointReport {
public:
  JointReport() {
    _reportPosition = true;
    _reportTorqueOrForce = true;
    _reportVelocity = true;
  }

  void reset() {_reportPosition = false, _reportTorqueOrForce = false, _reportVelocity = false;}

  bool &reportPosition() {return _reportPosition;}
  bool &reportTorqueOrForce() {return _reportTorqueOrForce;}
  bool &reportVelocity() {return _reportVelocity;}

private:
  bool _reportPosition;
  bool _reportTorqueOrForce;
  bool _reportVelocity;
};

class SettingsReport {

public:
  SettingsReport() {
    _reportJointStatuses = true;
    _reportPoseStatus = true;
    _reportGripperStatus = true;
    _reportSettingsStatus = true;

    _reportLengthUnits = false;
    _reportAngleUnits = false;
    _reportTransSpeed = false;
    _reportRotSpeed = false;
  }

  bool &reportJointStatuses() {return _reportJointStatuses;}
  bool &reportPoseStatus() {return _reportPoseStatus;}
  bool &reportGripperStatus() {return _reportGripperStatus;}
  bool &reportSettingsStatus() {return _reportSettingsStatus;}

  JointReport jointReport[CRCLRobotModel::JOINT_NUM];

  bool &reportLengthUnits() {return _reportLengthUnits;}
  bool &reportAngleUnits() {return _reportAngleUnits;}
  bool &reportTransSpeed() {return _reportTransSpeed;}
  bool &reportRotSpeed() {return _reportRotSpeed;}

  void print();

private:

  bool _reportJointStatuses;
  bool _reportPoseStatus;
  bool _reportGripperStatus;
  bool _reportSettingsStatus;

  bool _reportLengthUnits;
  bool _reportAngleUnits;
  bool _reportTransSpeed;
  bool _reportRotSpeed;
};

#endif	/* CRCLCOMMON_H */
