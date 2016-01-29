#ifndef CRCL_SERVER_H
#define CRCL_SERVER_H

#include "xml_parser_generator/xmlSchemaInstance.hh"
#include "crcl_cpp/CRCLStatusClasses.hh"

class CRCLServer
{
public:
  CRCLServer(void) { port = -1; server_fd = -1; client_fd = -1; do_debug = false; };
  ~CRCLServer(void) { if (-1 != client_fd) close(client_fd) ; if (-1 != server_fd) close(server_fd) ; };

  int init(int);
  int getConnection(void);
  int readCommand(void);
  CRCLCommandType *parseCommand(void);
  void quit(void);
  void debug(bool);

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
  bool do_debug;
};

#define CRCLStatusDefaultJointNum 6

class CRCLStatus
{
public:
  CRCLStatus(int jointNum = CRCLStatusDefaultJointNum);
  ~CRCLStatus(void);
  int setPose(double x, double y, double z, double r, double p, double w);
  void print(void);

private:
  int jointNum;

  XmlVersion * versionIn;
  XmlHeaderForCRCLStatus * headerIn;
  CRCLStatusType * CRCLStatusIn;
  CRCLStatusFile * CRCLStatusFileIn;
  CommandStatusType * CommandStatusIn;
  JointStatusesType * JointStatusesIn;
  JointStatusType * JointStatus;
  PoseStatusType * PoseIn;
  ParallelGripperStatusType * GripperStatusIn;
};

#endif	/* CRCL_SERVER_H */
