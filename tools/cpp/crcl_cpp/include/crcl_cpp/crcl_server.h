#ifndef CRCL_SERVER_H
#define CRCL_SERVER_H

#include <vector>
#include <tf/transform_datatypes.h>
#include "xml_parser_generator/xmlSchemaInstance.hh"
#include "crcl_cpp/CRCLStatusClasses.hh"

class CRCLPose
{
public:
  CRCLPose(void) {} ;
  ~CRCLPose(void) {} ;
  int setXYZ(double x, double y, double z);
  int setRPY(double r, double p, double y);
  int setVec(double xx, double xy, double xz, double zx, double zy, double zz);

  int getXYZ(double *x, double *y, double *z);
  int getRPY(double *r, double *p, double *y);
  int getVec(double *xx, double *xy, double *xz, double *zx, double *zy, double *zz);

private:
  tf::Vector3 tran;
  tf::Matrix3x3 rot;
};

class CRCLJointModel
{
public:
  CRCLJointModel(void) { position = velocity = effort = 0; };
  ~CRCLJointModel(void) {};
  int setPosition(double value) { position = value; return 0; };
  int getPosition(double *value) { *value = position; return 0; };
  int setVelocity(double value) { velocity = value; return 0; };
  int getVelocity(double *value) { *value = velocity; return 0; };
  int setEffort(double value) { effort = value; return 0; };
  int getEffort(double *value) { *value = effort; return 0; };

private:
  double position;
  double velocity;
  double effort;
};

class CRCLRobotModel
{
public:
  CRCLRobotModel(void);
  ~CRCLRobotModel(void);
  int setJointNumber(int jointNumber);
  int getJointNumber(void);
  int setJointPosition(int index, double value);
  int getJointPosition(int index, double *value);

private:
  int jointNumber;
  CRCLPose pose;
  std::vector<CRCLJointModel> joints;
};

class CRCLStatus
{
public:
  CRCLStatus(void);
  ~CRCLStatus(void);
  int setJointNumber(int jointNumber);
  int setPose(double x, double y, double z, double r, double p, double w);
  void print(void);

private:
  int jointNumber;
  XmlVersion *versionIn;
  XmlHeaderForCRCLStatus *headerIn;
  CRCLStatusType *CRCLStatusIn;
  CRCLStatusFile *CRCLStatusFileIn;
  CommandStatusType *CommandStatusIn;
  JointStatusesType *JointStatusesIn;
  JointStatusType *JointStatus;
  PoseStatusType *PoseIn;
  ParallelGripperStatusType *GripperStatusIn;
};

class CRCLServer
{
public:
  CRCLServer(void) { port = -1; server_fd = -1; client_fd = -1; };
  ~CRCLServer(void) { if (-1 != client_fd) close(client_fd) ; if (-1 != server_fd) close(server_fd) ; };

  int setJointNumber(int);
  int getServer(int);
  int getConnection(void);
  int readCommand(void);
  CRCLCommandType *parseCommand(void);
  void printStatus(void);
  void quit(void);

  /* override these with your own */
  int HandleActuateJointsType(ActuateJointsType *) { return 0; };
  int HandleCloseToolChangerType(CloseToolChangerType *) { return 0; };
  int HandleConfigureJointReportsType(ConfigureJointReportsType *) { return 0; };
  int HandleDwellType(DwellType *) { return 0; };
  int HandleGetStatusType(GetStatusType *) { return 0; };
  int HandleMessageType(MessageType *) { return 0; };
  int HandleMoveScrewType(MoveScrewType *) { return 0; };
  int HandleMoveThroughToType(MoveThroughToType *) { return 0; };
  int HandleMoveToType(MoveToType *) { return 0; };
  int HandleOpenToolChangerType(OpenToolChangerType *) { return 0; };
  int HandleRunProgramType(RunProgramType *) { return 0; };
  int HandleSetAngleUnitsType(SetAngleUnitsType *) { return 0; };
  int HandleSetEndEffectorParametersType(SetEndEffectorParametersType *) { return 0; };
  int HandleSetEndEffectorType(SetEndEffectorType *) { return 0; };
  int HandleSetEndPoseToleranceType(SetEndPoseToleranceType *) { return 0; };
  int HandleSetForceUnitsType(SetForceUnitsType *) { return 0; };
  int HandleSetIntermediatePoseToleranceType(SetIntermediatePoseToleranceType *) { return 0; };
  int HandleSetLengthUnitsType(SetLengthUnitsType *) { return 0; };
  int HandleSetMotionCoordinationType(SetMotionCoordinationType *) { return 0; };
  int HandleSetRobotParametersType(SetRobotParametersType *) { return 0; };
  int HandleSetRotAccelType(SetRotAccelType *) { return 0; };
  int HandleSetRotSpeedType(SetRotSpeedType *) { return 0; };
  int HandleSetTorqueUnitsType(SetTorqueUnitsType *) { return 0; };
  int HandleSetTransAccelType(SetTransAccelType *) { return 0; };
  int HandleSetTransSpeedType(SetTransSpeedType *) { return 0; };
  int HandleStopMotionType(StopMotionType *) { return 0; };

private:
  int port;			/* the socket port to serve */
  int server_fd;		/* the served socket file descriptor */
  int client_fd;		/* the connected client file descriptor */

  CRCLRobotModel robotModel;
  CRCLStatus status;
};

#endif	/* CRCL_SERVER_H */
