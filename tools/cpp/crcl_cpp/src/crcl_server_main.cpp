#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>
#include <tf/transform_datatypes.h>
#include "crcl_cpp/CRCLProgramInstanceClasses.hh"
#include "crcl_cpp/CRCLStatusClasses.hh"
#include "crcl_cpp/crcl_server.h"
#include <stdio.h>
#include <math.h>
#include <go.h>

/*
  Given two points in one coordinate system {A}, and their associated
  coordinates in {B}, return the transform from {B} to {A}
*/

static tf::Transform twoPointTransform(const tf::Vector3 &a1, const tf::Vector3 &a2, const tf::Vector3 &b1, const tf::Vector3 &b2)
{
  double tha, thb, theta;

  // tha is the angle of the line segment from a1 to a2, in frame {A}
  tha = atan2(a2.getY() - a1.getY(), a2.getX() - a1.getX());
  // thb is the angle of the line segment from b1 to b2, in frame {B}
  thb = atan2(b2.getY() - b1.getY(), b2.getX() - b1.getX());
  // their difference is the rotation from {A} to {B}
  theta = thb - tha;
  // normalize it, [-pi .. pi]
  while (theta > M_PI) theta -= (M_PI + M_PI);
  while (theta < -M_PI) theta += (M_PI + M_PI);
  // make it a rotation matrix, where theta is rotation about Z
  tf::Matrix3x3 B_R_A;
  B_R_A.setRPY(0, 0, theta);

  // ac is the vector to the centroid of a1 and a2, in {A}
  tf::Vector3 ac((a1.getX() + a2.getX()) * 0.5, (a1.getY() + a2.getY()) * 0.5, 0);
  // bc is the vector to the centroid of b1 and b2, in {B}
  tf::Vector3 bc((b1.getX() + b2.getX()) * 0.5, (b1.getY() + b2.getY()) * 0.5, 0);
  // get the vector from {A} to {B} in frame {B} by rotating ac into {B}
  // and subtracting from bc
  tf::Vector3 B_P_A(bc - B_R_A * ac);

  // convert it to a complete transform
  return tf::Transform(B_R_A, B_P_A);
}

static double randf(void)
{
  return ((double) rand()) / ((double) RAND_MAX);
}

static void test_two_point_transform(void)
{
  for (int t = 0; t < 10; t++) {
    tf::Vector3 a1, a2, b1, b2;
    tf::Vector3 B_P_A;
    tf::Matrix3x3 B_R_A;
    tf::Transform B_T_A, B_T_A_out;
    double theta;

    // generate two random points in {A} frame
    a1.setValue(randf(), randf(), 0);
    a2.setValue(randf(), randf(), 0);

    // generate a random transform from {A} to {B}
    B_P_A.setValue(randf(), randf(), 0);
    theta = M_PI * (2.0 * randf() - 1.0);
    B_R_A.setRPY(0, 0, theta);
    B_T_A.setOrigin(B_P_A);
    B_T_A.setBasis(B_R_A);

    // transform two points to {B} frame
    b1 = B_T_A * a1;
    b2 = B_T_A * a2;

    B_T_A_out = twoPointTransform(a1, a2, b1, b2);

    printf("%f %f / %f %f\n", B_T_A.getOrigin().getX(), B_T_A.getOrigin().getY(), B_T_A_out.getOrigin().getX(), B_T_A_out.getOrigin().getY());
  }
}

static void test_two_point_transform_e(void)
{
  for (int t = 0; t < 10; t++) {
    tf::Vector3 a1, a2, b1, b2;
    tf::Vector3 ac, bc;
    tf::Vector3 B_P_A, B_P_A_out;
    tf::Matrix3x3 B_R_A, B_R_A_out;
    tf::Transform B_T_A;
    double theta, tha, thb, theta_out;

    // generate two random points in {A} frame
    a1.setValue(randf(), randf(), 0);
    a2.setValue(randf(), randf(), 0);

    // generate a random transform from {A} to {B}
    B_P_A.setValue(randf(), randf(), 0);
    theta = M_PI * (2.0 * randf() - 1.0);
    B_R_A.setRPY(0, 0, theta);
    B_T_A.setOrigin(B_P_A);
    B_T_A.setBasis(B_R_A);

    // transform two points to {B} frame
    b1 = B_T_A * a1;
    b2 = B_T_A * a2;

    tha = atan2(a2.getY() - a1.getY(), a2.getX() - a1.getX());
    thb = atan2(b2.getY() - b1.getY(), b2.getX() - b1.getX());
    theta_out = thb - tha;
    while (theta_out > M_PI) theta_out -= (M_PI + M_PI);
    while (theta_out < -M_PI) theta_out += (M_PI + M_PI);

    /* theta_out = B_theta_A, from A to B */

    printf("%f %f\n", theta, theta_out);

    B_R_A_out.setRPY(0, 0, theta_out);

    ac.setX((a1.getX() + a2.getX()) * 0.5);
    ac.setY((a1.getY() + a2.getY()) * 0.5);
    ac.setZ(0);

    bc.setX((b1.getX() + b2.getX()) * 0.5);
    bc.setY((b1.getY() + b2.getY()) * 0.5);
    bc.setZ(0);

    B_P_A_out = bc - B_R_A_out * ac;

    printf("%f %f / %f %f\n", B_P_A.getX(), B_P_A.getY(), B_P_A_out.getX(), B_P_A_out.getY());
  }
}

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

  status.setXYZ(x, y, z);
  status.setVec(xi, xj, xk, zi, zj, zk);
  status.setJointPosition(1, x);
  status.setJointPosition(2, y);
  status.setJointPosition(3, z);
  status.setJointPosition(4, r);
  status.setJointPosition(5, p);
  status.setJointPosition(6, w);

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

  test_two_point_transform();
  return 0;

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
