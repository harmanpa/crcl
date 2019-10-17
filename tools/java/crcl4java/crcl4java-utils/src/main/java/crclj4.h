// Never include this file (crclj4.h) directly. include crclj.h instead.


// start_segment_index = 40
// start_segment_index = 50
// segment_index = 4
// classesSegList=[class crcl.base.StopMotionType, class crcl.base.SetRobotParametersType, class crcl.base.JointLimitType, class crcl.base.GuardsStatusesType, class crcl.base.RotSpeedRelativeType, class crcl.base.InitCanonType, class crcl.base.TransSpeedType, class crcl.base.ActuateJointsType, class crcl.base.RotAccelRelativeType, class crcl.base.DisableSensorType]

namespace crclj {


// class_index = 0 clss=class crcl.base.StopMotionType

    namespace crcl{
        namespace base{
            
            class StopMotionType : public MiddleCommandType {
            public:
                StopMotionType(jobject _jthis, bool copy);
                StopMotionType(const StopMotionType &);
                static StopMotionType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                StopMotionType();
                ~StopMotionType();
                StopConditionEnumType getStopCondition();
                void setStopCondition(const StopConditionEnumType & stopConditionEnumType_0);
            }; // end class StopMotionType


    // class_index = 1 clss=class crcl.base.SetRobotParametersType

            
            class SetRobotParametersType : public MiddleCommandType {
            public:
                SetRobotParametersType(jobject _jthis, bool copy);
                SetRobotParametersType(const SetRobotParametersType &);
                static SetRobotParametersType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                SetRobotParametersType();
                ~SetRobotParametersType();
                ::crclj::java::util::List getParameterSetting();
            }; // end class SetRobotParametersType


    // class_index = 2 clss=class crcl.base.JointLimitType

            
            class JointLimitType : public DataThingType {
            public:
                JointLimitType(jobject _jthis, bool copy);
                JointLimitType(const JointLimitType &);
                static JointLimitType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                JointLimitType();
                ~JointLimitType();
                jint getJointNumber();
                void setJointNumber(jint int_0);
                ::crclj::java::lang::Double getJointMinPosition();
                void setJointMinPosition(const ::crclj::java::lang::Double & double_0);
                ::crclj::java::lang::Double getJointMaxPosition();
                void setJointMaxPosition(const ::crclj::java::lang::Double & double_0);
                ::crclj::java::lang::Double getJointMaxTorqueOrForce();
                void setJointMaxTorqueOrForce(const ::crclj::java::lang::Double & double_0);
                ::crclj::java::lang::Double getJointMaxVelocity();
                void setJointMaxVelocity(const ::crclj::java::lang::Double & double_0);
            }; // end class JointLimitType


    // class_index = 3 clss=class crcl.base.GuardsStatusesType

            
            class GuardsStatusesType : public DataThingType {
            public:
                GuardsStatusesType(jobject _jthis, bool copy);
                GuardsStatusesType(const GuardsStatusesType &);
                static GuardsStatusesType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                GuardsStatusesType();
                ~GuardsStatusesType();
                ::crclj::java::util::List getGuard();
                jint getTriggerCount();
                void setTriggerCount(jint int_0);
                jlong getTriggerStopTimeMicros();
                void setTriggerStopTimeMicros(jlong long_0);
                ::crclj::java::lang::Double getTriggerValue();
                void setTriggerValue(const ::crclj::java::lang::Double & double_0);
                PoseType getTriggerPose();
                void setTriggerPose(const PoseType & poseType_0);
            }; // end class GuardsStatusesType


    // class_index = 4 clss=class crcl.base.RotSpeedRelativeType

            
            class RotSpeedRelativeType : public RotSpeedType {
            public:
                RotSpeedRelativeType(jobject _jthis, bool copy);
                RotSpeedRelativeType(const RotSpeedRelativeType &);
                static RotSpeedRelativeType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                RotSpeedRelativeType();
                ~RotSpeedRelativeType();
                jdouble getFraction();
                void setFraction(jdouble double_0);
            }; // end class RotSpeedRelativeType


    // class_index = 5 clss=class crcl.base.InitCanonType

            
            class InitCanonType : public CRCLCommandType {
            public:
                InitCanonType(jobject _jthis, bool copy);
                InitCanonType(const InitCanonType &);
                static InitCanonType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                InitCanonType();
                ~InitCanonType();
            }; // end class InitCanonType


    // class_index = 6 clss=class crcl.base.TransSpeedType

            
            class TransSpeedType : public DataThingType {
            public:
                TransSpeedType(jobject _jthis, bool copy);
                TransSpeedType(const TransSpeedType &);
                static TransSpeedType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                TransSpeedType();
                ~TransSpeedType();
            }; // end class TransSpeedType


    // class_index = 7 clss=class crcl.base.ActuateJointsType

            
            class ActuateJointsType : public MiddleCommandType {
            public:
                ActuateJointsType(jobject _jthis, bool copy);
                ActuateJointsType(const ActuateJointsType &);
                static ActuateJointsType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                ActuateJointsType();
                ~ActuateJointsType();
                ::crclj::java::util::List getActuateJoint();
            }; // end class ActuateJointsType


    // class_index = 8 clss=class crcl.base.RotAccelRelativeType

            
            class RotAccelRelativeType : public RotAccelType {
            public:
                RotAccelRelativeType(jobject _jthis, bool copy);
                RotAccelRelativeType(const RotAccelRelativeType &);
                static RotAccelRelativeType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                RotAccelRelativeType();
                ~RotAccelRelativeType();
                jdouble getFraction();
                void setFraction(jdouble double_0);
            }; // end class RotAccelRelativeType


    // class_index = 9 clss=class crcl.base.DisableSensorType

            
            class DisableSensorType : public MiddleCommandType {
            public:
                DisableSensorType(jobject _jthis, bool copy);
                DisableSensorType(const DisableSensorType &);
                static DisableSensorType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                DisableSensorType();
                ~DisableSensorType();
                jstring getSensorID();
                void setSensorID(jstring string_0);
                void setSensorID(const char * easyArg_0);
            }; // end class DisableSensorType
        } // end namespace base
    } // end namespace crcl



}; // end namespace crclj

