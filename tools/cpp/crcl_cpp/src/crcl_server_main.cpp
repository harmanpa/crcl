#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <tf/transform_datatypes.h>
#include "crcl_cpp/CRCLProgramInstanceClasses.hh"
#include "crcl_cpp/CRCLStatusClasses.hh"
#include "crcl_cpp/crcl_server.h"

class MyCRCLServer : public CRCLServer
{
public:
  int HandleMoveToType(MoveToType *cmd);
  int HandleSetEndEffectorType(SetEndEffectorType *cmd);
  int HandleSetLengthUnitsType(SetLengthUnitsType *cmd);
  int HandleSetAngleUnitsType(SetAngleUnitsType *cmd);
  int HandleSetTransSpeedType(SetTransSpeedType *cmd);
};

int MyCRCLServer::HandleMoveToType(MoveToType *cmd)
{
  double x, y, z;
  double xi, xj, xk;
  double zi, zj, zk;
  double r, p, w;

  x = cmd->EndPosition->Point->X->val;
  y = cmd->EndPosition->Point->Y->val;
  z = cmd->EndPosition->Point->Z->val;

  xi = cmd->EndPosition->XAxis->I->val;
  xj = cmd->EndPosition->XAxis->J->val;
  xk = cmd->EndPosition->XAxis->K->val;
  tf::Vector3 xaxis(xi, xj, xk);
  zi = cmd->EndPosition->ZAxis->I->val;
  zj = cmd->EndPosition->ZAxis->J->val;
  zk = cmd->EndPosition->ZAxis->K->val;
  tf::Vector3 zaxis(zi, zj, zk);
  tf::Vector3 yaxis = zaxis.cross(xaxis);
  tf::Matrix3x3 m(xi, yaxis.getX(), zi,
		  xj, yaxis.getY(), zj,
		  xk, yaxis.getZ(), zk);
  m.getRPY(r, p, w);

  robotModel.setPose(x, y, z, r, p, w);

  printf("MoveToType(%d) %f %f %f / %f %f %f\n",
	 cmd->CommandID->val,
	 x, y, z, r, p, w);

  return 0;
}

int MyCRCLServer::HandleSetEndEffectorType(SetEndEffectorType *cmd)
{
  printf("SetEndEffectorType(%d) %f\n",
	 cmd->CommandID->val,
	 cmd->Setting->val
    );
}

int MyCRCLServer::HandleSetLengthUnitsType(SetLengthUnitsType *cmd)
{
  // can be 'meter', 'millimeter', or 'inch'
  printf("SetLengthUnitsType(%d) %s\n",
	 cmd->CommandID->val,
	 cmd->UnitName->val.c_str()
    );
}

int MyCRCLServer::HandleSetAngleUnitsType(SetAngleUnitsType *cmd)
{
  // can be 'radian', or 'degree'
  printf("SetAngleUnitsType(%d) %s\n",
	 cmd->CommandID->val,
	 cmd->UnitName->val.c_str()
    );
}

int MyCRCLServer::HandleSetTransSpeedType(SetTransSpeedType *cmd)
{
  TransSpeedAbsoluteType *a;
  TransSpeedRelativeType *r;

  printf("SetTransSpeedType(%d) ",
	 cmd->CommandID->val
    );

  if (NULL != (a = dynamic_cast<TransSpeedAbsoluteType *>(cmd->TransSpeed))) {
    printf("absolute %f\n", a->Setting->val);
  } else if (NULL != (r = dynamic_cast<TransSpeedRelativeType *>(cmd->TransSpeed))) {
    printf("relative %f\n", r->Fraction->val);
  } else {
    printf("unknown\n");
  }
}

int main(int argc, char * argv[])
{
  MyCRCLServer me;
  CRCLCommandType *cmd;
  int retval;

  me.setJointNumber(2);

  me.getServer(1234);

  while (true) {
    printf("waiting for connection...\n");
    me.getConnection();
    printf("got a connection\n");

    while (true) {
      if (0 != me.readCommand()) break;
      cmd = me.parseCommand();
      if (NULL == cmd) continue;

#define DO_IT(TYPE)					\
      if (dynamic_cast<TYPE *>(cmd)) {			\
	me.Handle ## TYPE(dynamic_cast<TYPE *>(cmd));	\
	  continue;					\
      }
      DO_IT(ActuateJointsType);
      DO_IT(CloseToolChangerType);
      DO_IT(ConfigureJointReportsType);
      DO_IT(DwellType);
      DO_IT(GetStatusType);
      DO_IT(MessageType);
      DO_IT(MoveScrewType);
      DO_IT(MoveThroughToType);
      DO_IT(MoveToType);
      DO_IT(OpenToolChangerType);
      DO_IT(RunProgramType);
      DO_IT(SetAngleUnitsType);
      DO_IT(SetEndEffectorParametersType);
      DO_IT(SetEndEffectorType);
      DO_IT(SetEndPoseToleranceType);
      DO_IT(SetForceUnitsType);
      DO_IT(SetIntermediatePoseToleranceType);
      DO_IT(SetLengthUnitsType);
      DO_IT(SetMotionCoordinationType);
      DO_IT(SetRobotParametersType);
      DO_IT(SetRotAccelType);
      DO_IT(SetRotSpeedType);
      DO_IT(SetTorqueUnitsType);
      DO_IT(SetTransAccelType);
      DO_IT(SetTransSpeedType);
      DO_IT(StopMotionType);
      // else unknown type
      enum {BUFFERLEN = 1024};
      char outbuf[BUFFERLEN];
      size_t left, size = sizeof(outbuf);
      cmd->printSelf(outbuf, &left, &size);
      printf("what's this?\n");
      printf("%s\n", outbuf);
    } // while (true)
    // get another connection
  }   // while (true)

  me.quit();

  return retval == 0 ? 0 : 1;
}
