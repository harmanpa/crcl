/*!
  @defgroup CRPI_CRCL The CRPI to CRCL Interface

  These are the CRPI methods to be implemented by the CRCL server:

  - CrpiRobot (const char *initPath, bool bypass = false);
  - ~CrpiRobot ();
  - CanonReturn ApplyCartesianForceTorque (robotPose &robotForceTorque, vector<bool> activeAxes, vector<bool> manipulator);
  - CanonReturn ApplyJointTorque (robotAxes &robotJointTorque);
  - CanonReturn Couple (const char *targetID);
  - CanonReturn GetRobotAxes (robotAxes *axes);
  - CanonReturn GetRobotForces (robotPose *forces);
  - CanonReturn GetRobotIO (robotIO *io);
  - CanonReturn GetRobotPose (robotPose *pose);
  - CanonReturn GetRobotSpeed (robotPose *speed);
  - CanonReturn GetRobotSpeed (robotAxes *speed);
  - CanonReturn GetRobotTorques (robotAxes *torques);
  - CanonReturn Message (const char *message);
  - CanonReturn MoveAttractor (robotPose &pose);
  - CanonReturn MoveStraightTo (robotPose &pose);
  - CanonReturn MoveThroughTo (robotPose *poses, int numPoses, robotPose *accelerations = NULL, robotPose *speeds = NULL, robotPose *tolerances = NULL);
  - CanonReturn MoveTo (robotPose &pose);
  - CanonReturn MoveToAxisTarget (robotAxes &axes);
  - CanonReturn SetAbsoluteAcceleration (double acceleration);
  - CanonReturn SetAbsoluteSpeed (double speed);
  - CanonReturn SetAngleUnits (const char *unitName);
  - CanonReturn SetAxialSpeeds (double *speeds);
  - CanonReturn SetAxialUnits (const char **unitNames);
  - CanonReturn SetEndPoseTolerance (robotPose &tolerance);
  - CanonReturn SetIntermediatePoseTolerance (robotPose *tolerances);
  - CanonReturn SetLengthUnits (const char *unitName);
  - CanonReturn SetParameter (const char *paramName, void *paramVal);
  - CanonReturn SetRelativeAcceleration (double percent);
  - CanonReturn SetRelativeSpeed (double percent);
  - CanonReturn SetRobotIO (robotIO &io);
  - CanonReturn SetRobotDO (int dig_out, bool val);
  - CanonReturn SetTool (double percent);
  - CanonReturn StopMotion (int condition = 2);
  - CanonReturn SetJointType(int index, CanonJointType type);
  - CanonReturn GetJointType(int index, CanonJointType *type);
  - CanonReturn CrclXmlHandler (std::string &str);
  - CanonReturn CrclXmlResponse (char *str);
  - CanonReturn CrpiXmlHandler (std::string &str);
  - CanonReturn CrpiXmlResponse (char *str);
  - CanonReturn ToWorldMatrix(matrix & R_T_W);
  - CanonReturn ToSystemMatrix(const char *name, matrix &R_T_S);
  - CanonReturn ToWorld (robotPose *in, robotPose *out);
  - CanonReturn FromWorld (robotPose *in, robotPose *out);
  - CanonReturn ToSystem(const char *name, robotPose *in, robotPose *out);
  - CanonReturn FromSystem(const char *name, robotPose *in, robotPose *out);
  - CanonReturn UpdateWorldTransform (robotPose &newToSystem);
  - CanonReturn UpdateWorldTransform (matrix &newToSystem);
  - CanonReturn UpdateSystemTransform(const char *name, robotPose &newToWorld);
  - CanonReturn UpdateSystemTransform(const char *name, matrix &newToWorld);
  - CanonReturn SaveConfig (const char *file);
  - CanonReturn IsValid();

  These are the CRCL command types that can be sent: 

  - ActuateJointsType
  - CloseToolChangerType
  - ConfigureJointReportsType
  - DwellType
  - EndCanonType
  - GetStatusType
  - InitCanonType
  - JointDetailsType
  - JointForceTorqueType
  - JointSpeedAccelType
  - MessageType
  - MoveScrewType
  - MoveThroughToType
  - MoveToType
  - OpenToolChangerType
  - PoseAndSetType
  - RunProgramType
  - SetAngleUnitsType
  - SetEndEffectorParametersType
  - SetEndEffectorType
  - SetEndPoseToleranceType
  - SetForceUnitsType
  - SetIntermediatePoseToleranceType
  - SetLengthUnitsType
  - SetMotionCoordinationType
  - SetRobotParametersType
  - SetRotAccelType
  - SetRotSpeedType
  - SetTorqueUnitsType
  - SetTransAccelType
  - SetTransSpeedType
  - StopMotionType
  - ConfigureStatusReportType
 */

#ifndef CRPI_CRCL_ROBOT
#define CRPI_CRCL_ROBOT

#include "ulapi.h"
#include "crpi.h"
#include "CRCLCommon.h"

namespace crpi_robot
{
  void clientReadCode(void *arg);

  class LIBRARY_API CrpiCrcl
  {
  public:
    CrpiCrcl (CrpiRobotParams &params);
    ~CrpiCrcl ();
    CanonReturn ApplyCartesianForceTorque (robotPose &robotForceTorque, vector<bool> activeAxes, vector<bool> manipulator);
    CanonReturn ApplyJointTorque (robotAxes &robotJointTorque);
    CanonReturn Couple (const char *targetID);
    CanonReturn Message (const char *message);
    CanonReturn MoveStraightTo (robotPose &pose);
    CanonReturn MoveThroughTo (robotPose *poses,
                               int numPoses,
                               robotPose *accelerations = NULL,
                               robotPose *speeds = NULL,
                               robotPose *tolerances = NULL);
    CanonReturn MoveTo (robotPose &pose);
    CanonReturn GetRobotAxes (robotAxes *axes);
    CanonReturn GetRobotForces (robotPose *forces);
    CanonReturn GetRobotIO (robotIO *io);
    CanonReturn GetRobotPose (robotPose *pose);
    CanonReturn GetRobotSpeed (robotPose *speed);
    CanonReturn GetRobotSpeed (robotAxes *speed);
    CanonReturn GetRobotTorques (robotAxes *torques);
    CanonReturn MoveAttractor (robotPose &pose);
    CanonReturn MoveToAxisTarget (robotAxes &axes);
    CanonReturn SetAbsoluteAcceleration (double acceleration);
    CanonReturn SetAbsoluteSpeed (double speed);
    CanonReturn SetAngleUnits (const char *unitName);
    CanonReturn SetAxialSpeeds (double *speeds);
    CanonReturn SetAxialUnits (const char **unitNames);
    CanonReturn SetEndPoseTolerance (robotPose &tolerance);
    CanonReturn SetIntermediatePoseTolerance (robotPose *tolerances);
    CanonReturn SetLengthUnits (const char *unitName);
    CanonReturn SetParameter (const char *paramName, void *paramVal);
    CanonReturn SetRelativeAcceleration (double percent);
    CanonReturn SetRobotIO (robotIO &io);
    CanonReturn SetRobotDO (int dig_out, bool val);
    CanonReturn SetTool (double percent);
    CanonReturn SetRelativeSpeed (double percent);
    CanonReturn StopMotion (int condition = 2);

    /* extensions */
    CanonReturn SetJointType(int index, CanonJointType type);
    CanonReturn GetJointType(int index, CanonJointType *type);
      
    /* these need to be public, for access by non-class clientReadCode */
    ulapi_mutex_struct socket_write_mutex;
    ulapi_integer socket_id;
    ulapi_task_struct *client_read_task_ptr;
    CRCLRobotModel robotModel;

  private:
    CanonReturn WaitDone(int commandID_, double seconds = -1);
    CrpiRobotParams params_;
    
    double maxSpeed_;
    double maxAccel_;

    double acceleration_;
    double speed_;

    // Note: this assumes joints are revolute
    CanonAngleUnit axialUnits_[CRPI_AXES_MAX];
    CanonJointType jointType_[CRPI_AXES_MAX];
    int curTool;

    int commandID;		/* for CRCL commands */

    /* handler */

    ulapi_integer connectRobot(ulapi_integer port, char *host);
    void disconnectRobot(ulapi_integer id);
    int startClientReadTask();

  };				/* class CrpiCrcl */

} /* namespace crpi_robot */

#endif	/* CRPI_CRCL_ROBOT */
