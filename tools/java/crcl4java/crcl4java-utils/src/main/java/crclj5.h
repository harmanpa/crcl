// Never include this file (crclj5.h) directly. include crclj.h instead.


// start_segment_index = 50
// start_segment_index = 60
// segment_index = 5
// classesSegList=[class crcl.base.PoseToleranceType, class crcl.base.ParameterSettingType, class crcl.base.TorqueUnitEnumType, class crcl.base.SetRotAccelType, class crcl.base.ActuateJointType, class crcl.base.TransSpeedAbsoluteType, class crcl.base.OnOffSensorStatusType, class crcl.base.DisableGripperType, class crcl.base.SetMotionCoordinationType, class crcl.base.TwistType]

namespace crclj {


// class_index = 0 clss=class crcl.base.PoseToleranceType

    namespace crcl{
        namespace base{
            
            class PoseToleranceType : public DataThingType {
            public:
                PoseToleranceType(jobject _jthis, bool copy);
                PoseToleranceType(const PoseToleranceType &);
                static PoseToleranceType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                PoseToleranceType();
                ~PoseToleranceType();
                ::crclj::java::lang::Double getXPointTolerance();
                void setXPointTolerance(const ::crclj::java::lang::Double & double_0);
                ::crclj::java::lang::Double getYPointTolerance();
                void setYPointTolerance(const ::crclj::java::lang::Double & double_0);
                ::crclj::java::lang::Double getZPointTolerance();
                void setZPointTolerance(const ::crclj::java::lang::Double & double_0);
                ::crclj::java::lang::Double getXAxisTolerance();
                void setXAxisTolerance(const ::crclj::java::lang::Double & double_0);
                ::crclj::java::lang::Double getZAxisTolerance();
                void setZAxisTolerance(const ::crclj::java::lang::Double & double_0);
            }; // end class PoseToleranceType


    // class_index = 1 clss=class crcl.base.ParameterSettingType

            
            class ParameterSettingType : public DataThingType {
            public:
                ParameterSettingType(jobject _jthis, bool copy);
                ParameterSettingType(const ParameterSettingType &);
                static ParameterSettingType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                ParameterSettingType();
                ~ParameterSettingType();
                jstring getParameterName();
                void setParameterName(jstring string_0);
                void setParameterName(const char * easyArg_0);
                jstring getParameterValue();
                void setParameterValue(jstring string_0);
                void setParameterValue(const char * easyArg_0);
            }; // end class ParameterSettingType


    // class_index = 2 clss=class crcl.base.TorqueUnitEnumType

            
            class TorqueUnitEnumType : public ::crclj::java::lang::Enum {
            public:
                TorqueUnitEnumType(jobject _jthis, bool copy);
                TorqueUnitEnumType(const TorqueUnitEnumType &);
                static TorqueUnitEnumType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                protected:
                TorqueUnitEnumType();
                public:
                ~TorqueUnitEnumType();
                static TorqueUnitEnumType getNEWTON_METER();
                static TorqueUnitEnumType getFOOT_POUND();
                jstring value();
                static jobjectArray values();
                static TorqueUnitEnumType valueOf(jstring string_0);
                static TorqueUnitEnumType fromValue(jstring string_0);
            }; // end class TorqueUnitEnumType


    // class_index = 3 clss=class crcl.base.SetRotAccelType

            
            class SetRotAccelType : public MiddleCommandType {
            public:
                SetRotAccelType(jobject _jthis, bool copy);
                SetRotAccelType(const SetRotAccelType &);
                static SetRotAccelType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                SetRotAccelType();
                ~SetRotAccelType();
                RotAccelType getRotAccel();
                void setRotAccel(const RotAccelType & rotAccelType_0);
            }; // end class SetRotAccelType


    // class_index = 4 clss=class crcl.base.ActuateJointType

            
            class ActuateJointType : public DataThingType {
            public:
                ActuateJointType(jobject _jthis, bool copy);
                ActuateJointType(const ActuateJointType &);
                static ActuateJointType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                ActuateJointType();
                ~ActuateJointType();
                jint getJointNumber();
                void setJointNumber(jint int_0);
                jdouble getJointPosition();
                void setJointPosition(jdouble double_0);
                JointDetailsType getJointDetails();
                void setJointDetails(const JointDetailsType & jointDetailsType_0);
            }; // end class ActuateJointType


    // class_index = 5 clss=class crcl.base.TransSpeedAbsoluteType

            
            class TransSpeedAbsoluteType : public TransSpeedType {
            public:
                TransSpeedAbsoluteType(jobject _jthis, bool copy);
                TransSpeedAbsoluteType(const TransSpeedAbsoluteType &);
                static TransSpeedAbsoluteType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                TransSpeedAbsoluteType();
                ~TransSpeedAbsoluteType();
                jdouble getSetting();
                void setSetting(jdouble double_0);
            }; // end class TransSpeedAbsoluteType


    // class_index = 6 clss=class crcl.base.OnOffSensorStatusType

            
            class OnOffSensorStatusType : public SensorStatusType {
            public:
                OnOffSensorStatusType(jobject _jthis, bool copy);
                OnOffSensorStatusType(const OnOffSensorStatusType &);
                static OnOffSensorStatusType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                OnOffSensorStatusType();
                ~OnOffSensorStatusType();
                jboolean isOn();
                void setOn(jboolean boolean_0);
            }; // end class OnOffSensorStatusType


    // class_index = 7 clss=class crcl.base.DisableGripperType

            
            class DisableGripperType : public MiddleCommandType {
            public:
                DisableGripperType(jobject _jthis, bool copy);
                DisableGripperType(const DisableGripperType &);
                static DisableGripperType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                DisableGripperType();
                ~DisableGripperType();
                jstring getGripperName();
                void setGripperName(jstring string_0);
                void setGripperName(const char * easyArg_0);
            }; // end class DisableGripperType


    // class_index = 8 clss=class crcl.base.SetMotionCoordinationType

            
            class SetMotionCoordinationType : public MiddleCommandType {
            public:
                SetMotionCoordinationType(jobject _jthis, bool copy);
                SetMotionCoordinationType(const SetMotionCoordinationType &);
                static SetMotionCoordinationType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                SetMotionCoordinationType();
                ~SetMotionCoordinationType();
                jboolean isCoordinated();
                void setCoordinated(jboolean boolean_0);
            }; // end class SetMotionCoordinationType


    // class_index = 9 clss=class crcl.base.TwistType

            
            class TwistType : public DataThingType {
            public:
                TwistType(jobject _jthis, bool copy);
                TwistType(const TwistType &);
                static TwistType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                TwistType();
                ~TwistType();
                VectorType getLinearVelocity();
                void setLinearVelocity(const VectorType & vectorType_0);
                VectorType getAngularVelocity();
                void setAngularVelocity(const VectorType & vectorType_0);
            }; // end class TwistType
        } // end namespace base
    } // end namespace crcl



}; // end namespace crclj

