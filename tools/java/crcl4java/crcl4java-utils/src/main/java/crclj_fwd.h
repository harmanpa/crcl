#ifndef crclj_fwd_h
#define crclj_fwd_h


namespace crclj {
    
    namespace java {
            namespace lang {
                class Object;
            } // end namespace lang;
    }// end namespace java 
    
   

    
    namespace crcl{
        namespace base{
            class DataThingType;
            class CRCLCommandType;
            class MiddleCommandType;
            class EnableSensorType;
            class SensorStatusType;
            class ForceTorqueSensorStatusType;
            class SetLengthUnitsType;
            class RotSpeedType;
            class RotSpeedRelativeType;
            class InitCanonType;
        } // end namespace base
    } // end namespace crcl

    namespace java{
        namespace lang{
            class Enum;
        } // end namespace lang
    } // end namespace java

    namespace crcl{
        namespace base{
            class AngleUnitEnumType;
            class CRCLProgramType;
            class SetMotionCoordinationType;
            class ConfigureJointReportsType;
            class RunProgramType;
            class GuardsStatusesType;
            class ParameterSettingType;
            class PoseType;
            class TransSpeedType;
            class TransSpeedRelativeType;
            class SetIntermediatePoseToleranceType;
            class OnOffSensorStatusType;
            class StopConditionEnumType;
            class RotSpeedAbsoluteType;
            class GuardLimitEnumType;
            class OpenToolChangerType;
            class TorqueUnitEnumType;
            class MessageType;
            class JointDetailsType;
            class JointStatusType;
            class PoseStatusType;
            class CRCLCommandInstanceType;
            class EnableRobotParameterStatusType;
            class VectorType;
            class JointStatusesType;
            class CommandStateEnumType;
            class SetRobotParametersType;
            class JointLimitType;
            class GripperStatusType;
            class ParallelGripperStatusType;
            class JointSpeedAccelType;
            class JointForceTorqueType;
            class SettingsStatusType;
            class PointType;
            class DwellType;
            class RotAccelType;
            class RotAccelRelativeType;
            class DisableSensorType;
            class TwistType;
            class SetTransAccelType;
            class CRCLStatusType;
            class PoseToleranceType;
            class CloseToolChangerType;
            class EndCanonType;
            class ThreeFingerGripperStatusType;
            class ActuateJointsType;
            class SetRotSpeedType;
            class DisableGripperType;
            class MoveToType;
            class EnableGripperType;
            class WrenchType;
            class CountSensorStatusType;
            class ForceUnitEnumType;
            class MoveScrewType;
            class SetForceUnitsType;
            class TransSpeedAbsoluteType;
            class RotAccelAbsoluteType;
            class GuardType;
            class DisableRobotParameterStatusType;
            class SetTransSpeedType;
            class ScalarSensorStatusType;
            class CommandStatusType;
            class SetRotAccelType;
            class SetAngleUnitsType;
            class SensorStatusesType;
            class LengthUnitEnumType;
            class ConfigureStatusReportType;
            class VacuumGripperStatusType;
            class SetTorqueUnitsType;
            class SetEndEffectorParametersType;
            class TransAccelType;
            class MoveThroughToType;
            class ActuateJointType;
            class TransAccelRelativeType;
            class SetEndEffectorType;
            class TransAccelAbsoluteType;
            class GetStatusType;
            class ConfigureJointReportType;
            class SetEndPoseToleranceType;
            class PoseAndSetType;
            class StopMotionType;
            class DataThingType;
            class CRCLCommandType;
            class MiddleCommandType;
            class MessageType;
        } // end namespace base

        namespace utils{
            class CRCLCommandWrapper;
        } // end namespace utils
    } // end namespace crcl

    namespace java{
        namespace util{
            class List;
        } // end namespace util

        namespace lang{
            class Number;
            class Double;
            class Integer;
            class Boolean;
            class Long;
        } // end namespace lang
    } // end namespace java

    namespace crcl{
        namespace utils{
            class CRCLCommandWrapperCRCLCommandWrapperConsumer;
        } // end namespace utils

        namespace base{
            class CRCLProgramType;
        } // end namespace base
    } // end namespace crcl

    
    
    // end namespace crclj
    }
    
    // end #ifndef crclj_fwd_h
    #endif
    
