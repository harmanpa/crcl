// Never include this file (crclj8.h) directly. include crclj.h instead.


// start_segment_index = 80
// start_segment_index = 90
// segment_index = 8
// classesSegList=[class crcl.base.SetEndPoseToleranceType, class crcl.base.RotAccelRelativeType, class crcl.base.DisableSensorType, class crcl.base.ConfigureJointReportsType, class crcl.base.CommandStatusType, class crcl.base.MessageType, class crcl.base.TransAccelRelativeType, class crcl.base.SetEndEffectorType, class crcl.base.RotAccelAbsoluteType, class crcl.base.GuardType]

namespace crclj {


// class_index = 0 clss=class crcl.base.SetEndPoseToleranceType

    namespace crcl{
        namespace base{
            
            class SetEndPoseToleranceType : public MiddleCommandType {
            public:
                SetEndPoseToleranceType(jobject _jthis, bool copy);
                SetEndPoseToleranceType(const SetEndPoseToleranceType &);
                static SetEndPoseToleranceType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                SetEndPoseToleranceType();
                ~SetEndPoseToleranceType();
                PoseToleranceType getTolerance();
                void setTolerance(const PoseToleranceType & poseToleranceType_0);
            }; // end class SetEndPoseToleranceType


    // class_index = 1 clss=class crcl.base.RotAccelRelativeType

            
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


    // class_index = 2 clss=class crcl.base.DisableSensorType

            
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


    // class_index = 3 clss=class crcl.base.ConfigureJointReportsType

            
            class ConfigureJointReportsType : public MiddleCommandType {
            public:
                ConfigureJointReportsType(jobject _jthis, bool copy);
                ConfigureJointReportsType(const ConfigureJointReportsType &);
                static ConfigureJointReportsType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                ConfigureJointReportsType();
                ~ConfigureJointReportsType();
                jboolean isResetAll();
                void setResetAll(jboolean boolean_0);
                ::crclj::java::util::List getConfigureJointReport();
            }; // end class ConfigureJointReportsType


    // class_index = 4 clss=class crcl.base.CommandStatusType

            
            class CommandStatusType : public DataThingType {
            public:
                CommandStatusType(jobject _jthis, bool copy);
                CommandStatusType(const CommandStatusType &);
                static CommandStatusType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                CommandStatusType();
                ~CommandStatusType();
                jlong getCommandID();
                void setCommandID(jlong long_0);
                jstring getProgramFile();
                void setProgramFile(jstring string_0);
                void setProgramFile(const char * easyArg_0);
                ::crclj::java::lang::Integer getProgramIndex();
                void setProgramIndex(const ::crclj::java::lang::Integer & integer_0);
                ::crclj::java::lang::Integer getProgramLength();
                void setProgramLength(const ::crclj::java::lang::Integer & integer_0);
                jlong getStatusID();
                void setStatusID(jlong long_0);
                CommandStateEnumType getCommandState();
                void setCommandState(const CommandStateEnumType & commandStateEnumType_0);
                jstring getStateDescription();
                void setStateDescription(jstring string_0);
                void setStateDescription(const char * easyArg_0);
                ::crclj::java::lang::Integer getOverridePercent();
                void setOverridePercent(const ::crclj::java::lang::Integer & integer_0);
            }; // end class CommandStatusType


    // class_index = 5 clss=class crcl.base.MessageType

            
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


    // class_index = 6 clss=class crcl.base.TransAccelRelativeType

            
            class TransAccelRelativeType : public TransAccelType {
            public:
                TransAccelRelativeType(jobject _jthis, bool copy);
                TransAccelRelativeType(const TransAccelRelativeType &);
                static TransAccelRelativeType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                TransAccelRelativeType();
                ~TransAccelRelativeType();
                jdouble getFraction();
                void setFraction(jdouble double_0);
            }; // end class TransAccelRelativeType


    // class_index = 7 clss=class crcl.base.SetEndEffectorType

            
            class SetEndEffectorType : public MiddleCommandType {
            public:
                SetEndEffectorType(jobject _jthis, bool copy);
                SetEndEffectorType(const SetEndEffectorType &);
                static SetEndEffectorType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                SetEndEffectorType();
                ~SetEndEffectorType();
                jdouble getSetting();
                void setSetting(jdouble double_0);
            }; // end class SetEndEffectorType


    // class_index = 8 clss=class crcl.base.RotAccelAbsoluteType

            
            class RotAccelAbsoluteType : public RotAccelType {
            public:
                RotAccelAbsoluteType(jobject _jthis, bool copy);
                RotAccelAbsoluteType(const RotAccelAbsoluteType &);
                static RotAccelAbsoluteType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                RotAccelAbsoluteType();
                ~RotAccelAbsoluteType();
                jdouble getSetting();
                void setSetting(jdouble double_0);
            }; // end class RotAccelAbsoluteType


    // class_index = 9 clss=class crcl.base.GuardType

            
            class GuardType : public DataThingType {
            public:
                GuardType(jobject _jthis, bool copy);
                GuardType(const GuardType &);
                static GuardType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                GuardType();
                ~GuardType();
                jstring getSensorID();
                void setSensorID(jstring string_0);
                void setSensorID(const char * easyArg_0);
                jstring getSubField();
                void setSubField(jstring string_0);
                void setSubField(const char * easyArg_0);
                GuardLimitEnumType getLimitType();
                void setLimitType(const GuardLimitEnumType & guardLimitEnumType_0);
                ::crclj::java::lang::Double getLimitValue();
                void setLimitValue(const ::crclj::java::lang::Double & double_0);
                ::crclj::java::lang::Long getRecheckTimeMicroSeconds();
                void setRecheckTimeMicroSeconds(const ::crclj::java::lang::Long & long_0);
                ::crclj::java::lang::Long getCheckCount();
                void setCheckCount(const ::crclj::java::lang::Long & long_0);
                ::crclj::java::lang::Long getLastCheckTime();
                void setLastCheckTime(const ::crclj::java::lang::Long & long_0);
                ::crclj::java::lang::Double getLastCheckValue();
                void setLastCheckValue(const ::crclj::java::lang::Double & double_0);
            }; // end class GuardType
        } // end namespace base
    } // end namespace crcl



}; // end namespace crclj

