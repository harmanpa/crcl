// Never include this file (crclj02.h) directly. include crclj.h instead.


// start_segment_index = 20
// start_segment_index = 30
// segment_index = 2
// classesSegList=[class crcl.base.TransSpeedRelativeType, class crcl.base.SetIntermediatePoseToleranceType, class crcl.base.OnOffSensorStatusType, class crcl.base.StopConditionEnumType, class crcl.base.RotSpeedAbsoluteType, class crcl.base.GuardLimitEnumType, class crcl.base.OpenToolChangerType, class crcl.base.TorqueUnitEnumType, class crcl.base.MessageType, class crcl.base.JointDetailsType]

namespace crclj {


// class_index = 0 clss=class crcl.base.TransSpeedRelativeType

    namespace crcl{
        namespace base{
            
            class TransSpeedRelativeType : public TransSpeedType {
            public:
                TransSpeedRelativeType(jobject _jthis, bool copy);
                TransSpeedRelativeType(const TransSpeedRelativeType &);
                static TransSpeedRelativeType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                TransSpeedRelativeType();
                ~TransSpeedRelativeType();
                jdouble getFraction();
                void setFraction(jdouble double_0);
            }; // end class TransSpeedRelativeType


    // class_index = 1 clss=class crcl.base.SetIntermediatePoseToleranceType

            
            class SetIntermediatePoseToleranceType : public MiddleCommandType {
            public:
                SetIntermediatePoseToleranceType(jobject _jthis, bool copy);
                SetIntermediatePoseToleranceType(const SetIntermediatePoseToleranceType &);
                static SetIntermediatePoseToleranceType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                SetIntermediatePoseToleranceType();
                ~SetIntermediatePoseToleranceType();
                PoseToleranceType getTolerance();
                void setTolerance(const PoseToleranceType & poseToleranceType_0);
            }; // end class SetIntermediatePoseToleranceType


    // class_index = 2 clss=class crcl.base.OnOffSensorStatusType

            
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


    // class_index = 3 clss=class crcl.base.StopConditionEnumType

            
            class StopConditionEnumType : public ::crclj::java::lang::Enum {
            public:
                StopConditionEnumType(jobject _jthis, bool copy);
                StopConditionEnumType(const StopConditionEnumType &);
                static StopConditionEnumType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                protected:
                StopConditionEnumType();
                public:
                ~StopConditionEnumType();
                static StopConditionEnumType getIMMEDIATE();
                static StopConditionEnumType getFAST();
                static StopConditionEnumType getNORMAL();
                jstring value();
                static jobjectArray values();
                static StopConditionEnumType valueOf(jstring string_0);
                static StopConditionEnumType fromValue(jstring string_0);
            }; // end class StopConditionEnumType


    // class_index = 4 clss=class crcl.base.RotSpeedAbsoluteType

            
            class RotSpeedAbsoluteType : public RotSpeedType {
            public:
                RotSpeedAbsoluteType(jobject _jthis, bool copy);
                RotSpeedAbsoluteType(const RotSpeedAbsoluteType &);
                static RotSpeedAbsoluteType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                RotSpeedAbsoluteType();
                ~RotSpeedAbsoluteType();
                jdouble getSetting();
                void setSetting(jdouble double_0);
            }; // end class RotSpeedAbsoluteType


    // class_index = 5 clss=class crcl.base.GuardLimitEnumType

            
            class GuardLimitEnumType : public ::crclj::java::lang::Enum {
            public:
                GuardLimitEnumType(jobject _jthis, bool copy);
                GuardLimitEnumType(const GuardLimitEnumType &);
                static GuardLimitEnumType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                protected:
                GuardLimitEnumType();
                public:
                ~GuardLimitEnumType();
                static GuardLimitEnumType getOVER_MAX();
                static GuardLimitEnumType getUNDER_MIN();
                static GuardLimitEnumType getINCREASE_OVER_LIMIT();
                static GuardLimitEnumType getDECREASE_BEYOND_LIMIT();
                jstring value();
                static jobjectArray values();
                static GuardLimitEnumType valueOf(jstring string_0);
                static GuardLimitEnumType fromValue(jstring string_0);
            }; // end class GuardLimitEnumType


    // class_index = 6 clss=class crcl.base.OpenToolChangerType

            
            class OpenToolChangerType : public MiddleCommandType {
            public:
                OpenToolChangerType(jobject _jthis, bool copy);
                OpenToolChangerType(const OpenToolChangerType &);
                static OpenToolChangerType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                OpenToolChangerType();
                ~OpenToolChangerType();
            }; // end class OpenToolChangerType


    // class_index = 7 clss=class crcl.base.TorqueUnitEnumType

            
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


    // class_index = 8 clss=class crcl.base.MessageType

            
            class MessageType : public MiddleCommandType {
            public:
                MessageType(jobject _jthis, bool copy);
                MessageType(const MessageType &);
                static MessageType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                MessageType();
                ~MessageType();
                jstring getMessage();
                void setMessage(jstring string_0);
                void setMessage(const char * easyArg_0);
            }; // end class MessageType


    // class_index = 9 clss=class crcl.base.JointDetailsType

            
            class JointDetailsType : public DataThingType {
            public:
                JointDetailsType(jobject _jthis, bool copy);
                JointDetailsType(const JointDetailsType &);
                static JointDetailsType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                JointDetailsType();
                ~JointDetailsType();
            }; // end class JointDetailsType
        } // end namespace base
    } // end namespace crcl



}; // end namespace crclj

