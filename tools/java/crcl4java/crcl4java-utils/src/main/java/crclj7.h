// Never include this file (crclj7.h) directly. include crclj.h instead.


// start_segment_index = 70
// start_segment_index = 80
// segment_index = 7
// classesSegList=[class crcl.base.SetTorqueUnitsType, class crcl.base.SetEndEffectorParametersType, class crcl.base.SetTransSpeedType, class crcl.base.ScalarSensorStatusType, class crcl.base.MoveThroughToType, class crcl.base.ParallelGripperStatusType, class crcl.base.JointSpeedAccelType, class crcl.base.GuardedMoveToType, class crcl.base.PoseStatusType, class crcl.base.ConfigureJointReportType]

namespace crclj {


// class_index = 0 clss=class crcl.base.SetTorqueUnitsType

    namespace crcl{
        namespace base{
            
            class SetTorqueUnitsType : public MiddleCommandType {
            public:
                SetTorqueUnitsType(jobject _jthis, bool copy);
                SetTorqueUnitsType(const SetTorqueUnitsType &);
                static SetTorqueUnitsType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                SetTorqueUnitsType();
                ~SetTorqueUnitsType();
                TorqueUnitEnumType getUnitName();
                void setUnitName(const TorqueUnitEnumType & torqueUnitEnumType_0);
            }; // end class SetTorqueUnitsType


    // class_index = 1 clss=class crcl.base.SetEndEffectorParametersType

            
            class SetEndEffectorParametersType : public MiddleCommandType {
            public:
                SetEndEffectorParametersType(jobject _jthis, bool copy);
                SetEndEffectorParametersType(const SetEndEffectorParametersType &);
                static SetEndEffectorParametersType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                SetEndEffectorParametersType();
                ~SetEndEffectorParametersType();
                ::crclj::java::util::List getParameterSetting();
            }; // end class SetEndEffectorParametersType


    // class_index = 2 clss=class crcl.base.SetTransSpeedType

            
            class SetTransSpeedType : public MiddleCommandType {
            public:
                SetTransSpeedType(jobject _jthis, bool copy);
                SetTransSpeedType(const SetTransSpeedType &);
                static SetTransSpeedType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                SetTransSpeedType();
                ~SetTransSpeedType();
                TransSpeedType getTransSpeed();
                void setTransSpeed(const TransSpeedType & transSpeedType_0);
            }; // end class SetTransSpeedType


    // class_index = 3 clss=class crcl.base.ScalarSensorStatusType

            
            class ScalarSensorStatusType : public SensorStatusType {
            public:
                ScalarSensorStatusType(jobject _jthis, bool copy);
                ScalarSensorStatusType(const ScalarSensorStatusType &);
                static ScalarSensorStatusType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                ScalarSensorStatusType();
                ~ScalarSensorStatusType();
                jdouble getScalarValue();
                void setScalarValue(jdouble double_0);
            }; // end class ScalarSensorStatusType


    // class_index = 4 clss=class crcl.base.MoveThroughToType

            
            class MoveThroughToType : public MiddleCommandType {
            public:
                MoveThroughToType(jobject _jthis, bool copy);
                MoveThroughToType(const MoveThroughToType &);
                static MoveThroughToType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                MoveThroughToType();
                ~MoveThroughToType();
                jboolean isMoveStraight();
                void setMoveStraight(jboolean boolean_0);
                ::crclj::java::util::List getWaypoint();
                jint getNumPositions();
                void setNumPositions(jint int_0);
            }; // end class MoveThroughToType


    // class_index = 5 clss=class crcl.base.ParallelGripperStatusType

            
            class ParallelGripperStatusType : public GripperStatusType {
            public:
                ParallelGripperStatusType(jobject _jthis, bool copy);
                ParallelGripperStatusType(const ParallelGripperStatusType &);
                static ParallelGripperStatusType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                ParallelGripperStatusType();
                ~ParallelGripperStatusType();
                jdouble getSeparation();
                void setSeparation(jdouble double_0);
            }; // end class ParallelGripperStatusType


    // class_index = 6 clss=class crcl.base.JointSpeedAccelType

            
            class JointSpeedAccelType : public JointDetailsType {
            public:
                JointSpeedAccelType(jobject _jthis, bool copy);
                JointSpeedAccelType(const JointSpeedAccelType &);
                static JointSpeedAccelType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                JointSpeedAccelType();
                ~JointSpeedAccelType();
                ::crclj::java::lang::Double getJointSpeed();
                void setJointSpeed(const ::crclj::java::lang::Double & double_0);
                ::crclj::java::lang::Double getJointAccel();
                void setJointAccel(const ::crclj::java::lang::Double & double_0);
            }; // end class JointSpeedAccelType


    // class_index = 7 clss=class crcl.base.GuardedMoveToType

            
            class GuardedMoveToType : public MiddleCommandType {
            public:
                GuardedMoveToType(jobject _jthis, bool copy);
                GuardedMoveToType(const GuardedMoveToType &);
                static GuardedMoveToType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                GuardedMoveToType();
                ~GuardedMoveToType();
                PoseType getEndPosition();
                void setEndPosition(const PoseType & poseType_0);
                jboolean isMoveStraight();
                void setMoveStraight(jboolean boolean_0);
            }; // end class GuardedMoveToType


    // class_index = 8 clss=class crcl.base.PoseStatusType

            
            class PoseStatusType : public DataThingType {
            public:
                PoseStatusType(jobject _jthis, bool copy);
                PoseStatusType(const PoseStatusType &);
                static PoseStatusType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                PoseStatusType();
                ~PoseStatusType();
                PoseType getPose();
                void setPose(const PoseType & poseType_0);
                TwistType getTwist();
                void setTwist(const TwistType & twistType_0);
                WrenchType getWrench();
                void setWrench(const WrenchType & wrenchType_0);
                jstring getConfiguration();
                void setConfiguration(jstring string_0);
                void setConfiguration(const char * easyArg_0);
            }; // end class PoseStatusType


    // class_index = 9 clss=class crcl.base.ConfigureJointReportType

            
            class ConfigureJointReportType : public DataThingType {
            public:
                ConfigureJointReportType(jobject _jthis, bool copy);
                ConfigureJointReportType(const ConfigureJointReportType &);
                static ConfigureJointReportType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                ConfigureJointReportType();
                ~ConfigureJointReportType();
                jint getJointNumber();
                void setJointNumber(jint int_0);
                jboolean isReportPosition();
                void setReportPosition(jboolean boolean_0);
                jboolean isReportTorqueOrForce();
                void setReportTorqueOrForce(jboolean boolean_0);
                jboolean isReportVelocity();
                void setReportVelocity(jboolean boolean_0);
            }; // end class ConfigureJointReportType
        } // end namespace base
    } // end namespace crcl



}; // end namespace crclj

