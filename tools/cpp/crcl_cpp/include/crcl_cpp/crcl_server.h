#ifndef CRCL_SERVER_H
#define CRCL_SERVER_H

#include <vector>
#include <tf/transform_datatypes.h>
#include "xml_parser_generator/xmlSchemaInstance.hh"
#include "crcl_cpp/CRCLStatusClasses.hh"

class CRCLStatus
{
public:
  CRCLStatus(void);
  ~CRCLStatus(void);
  int setJointPosition(int number, double position);
  int setXYZ(double x, double y, double z);
  int setVec(double xx, double xy, double xz, double zx, double zy, double zz);
  int print(char *buffer, size_t len);

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
  CRCLServer(void);
  ~CRCLServer(void);

  int getServer(int);
  int getConnection(void);
  int readCommand(void);
  CRCLCommandType *parseCommand(void);
  int writeStatus(void);
  void *reportStatus(void);
  void quit(void);

  CRCLStatus status;

  /* override these with your own */
  int HandleActuateJointsType(ActuateJointsType *cmd);
  int HandleCloseToolChangerType(CloseToolChangerType *cmd);
  int HandleConfigureJointReportsType(ConfigureJointReportsType *cmd);
  int HandleDwellType(DwellType *cmd);
  int HandleGetStatusType(GetStatusType *cmd);
  int HandleMessageType(MessageType *cmd);
  int HandleMoveScrewType(MoveScrewType *cmd);
  int HandleMoveThroughToType(MoveThroughToType *cmd);
  int HandleMoveToType(MoveToType *cmd);
  int HandleOpenToolChangerType(OpenToolChangerType *cmd);
  int HandleRunProgramType(RunProgramType *cmd);
  int HandleSetAngleUnitsType(SetAngleUnitsType *cmd);
  int HandleSetEndEffectorParametersType(SetEndEffectorParametersType *cmd);
  int HandleSetEndEffectorType(SetEndEffectorType *cmd);
  int HandleSetEndPoseToleranceType(SetEndPoseToleranceType *cmd);
  int HandleSetForceUnitsType(SetForceUnitsType *cmd);
  int HandleSetIntermediatePoseToleranceType(SetIntermediatePoseToleranceType *cmd);
  int HandleSetLengthUnitsType(SetLengthUnitsType *cmd);
  int HandleSetMotionCoordinationType(SetMotionCoordinationType *cmd);
  int HandleSetRobotParametersType(SetRobotParametersType *cmd);
  int HandleSetRotAccelType(SetRotAccelType *cmd);
  int HandleSetRotSpeedType(SetRotSpeedType *cmd);
  int HandleSetTorqueUnitsType(SetTorqueUnitsType *cmd);
  int HandleSetTransAccelType(SetTransAccelType *cmd);
  int HandleSetTransSpeedType(SetTransSpeedType *cmd);
  int HandleStopMotionType(StopMotionType *cmd);

private:

  int port;			/* the socket port to serve */
  int server_fd;		/* the served socket file descriptor */
  int client_fd;		/* the connected client file descriptor */
};

#endif	/* CRCL_SERVER_H */
