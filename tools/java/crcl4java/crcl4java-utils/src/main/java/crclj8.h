// Never include this file (crclj8.h) directly. include crclj.h instead.


// start_segment_index = 80
// start_segment_index = 90
// segment_index = 8
// classesSegList=[class crcl.base.ConfigureJointReportsType, class crcl.base.SetTransAccelType, class crcl.base.CRCLStatusType, class crcl.base.ParameterSettingType, class crcl.base.TransSpeedRelativeType, class crcl.base.SetIntermediatePoseToleranceType, class crcl.base.TransSpeedAbsoluteType, class crcl.base.DisableGripperType, class crcl.base.EnableGripperType, class crcl.base.WrenchType]

namespace crclj {


// class_index = 0 clss=class crcl.base.ConfigureJointReportsType

    namespace crcl{
        namespace base{
            
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


    // class_index = 1 clss=class crcl.base.SetTransAccelType

            
            class SetTransAccelType : public MiddleCommandType {
            public:
                SetTransAccelType(jobject _jthis, bool copy);
                SetTransAccelType(const SetTransAccelType &);
                static SetTransAccelType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                SetTransAccelType();
                ~SetTransAccelType();
                TransAccelType getTransAccel();
                void setTransAccel(const TransAccelType & transAccelType_0);
            }; // end class SetTransAccelType


    // class_index = 2 clss=class crcl.base.CRCLStatusType

            
            class CRCLStatusType : public DataThingType {
            public:
                CRCLStatusType(jobject _jthis, bool copy);
                CRCLStatusType(const CRCLStatusType &);
                static CRCLStatusType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                CRCLStatusType();
                ~CRCLStatusType();
                CommandStatusType getCommandStatus();
                void setCommandStatus(const CommandStatusType & commandStatusType_0);
                JointStatusesType getJointStatuses();
                void setJointStatuses(const JointStatusesType & jointStatusesType_0);
                PoseStatusType getPoseStatus();
                void setPoseStatus(const PoseStatusType & poseStatusType_0);
                GripperStatusType getGripperStatus();
                void setGripperStatus(const GripperStatusType & gripperStatusType_0);
                SettingsStatusType getSettingsStatus();
                void setSettingsStatus(const SettingsStatusType & settingsStatusType_0);
                SensorStatusesType getSensorStatuses();
                void setSensorStatuses(const SensorStatusesType & sensorStatusesType_0);
                GuardsStatusesType getGuardsStatuses();
                void setGuardsStatuses(const GuardsStatusesType & guardsStatusesType_0);
            }; // end class CRCLStatusType


    // class_index = 3 clss=class crcl.base.ParameterSettingType

            
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


    // class_index = 4 clss=class crcl.base.TransSpeedRelativeType

            
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


    // class_index = 5 clss=class crcl.base.SetIntermediatePoseToleranceType

            
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


    // class_index = 6 clss=class crcl.base.TransSpeedAbsoluteType

            
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


    // class_index = 8 clss=class crcl.base.EnableGripperType

            
            class EnableGripperType : public MiddleCommandType {
            public:
                EnableGripperType(jobject _jthis, bool copy);
                EnableGripperType(const EnableGripperType &);
                static EnableGripperType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                EnableGripperType();
                ~EnableGripperType();
                jstring getGripperName();
                void setGripperName(jstring string_0);
                void setGripperName(const char * easyArg_0);
                ::crclj::java::util::List getGripperOption();
            }; // end class EnableGripperType


    // class_index = 9 clss=class crcl.base.WrenchType

            
            class WrenchType : public DataThingType {
            public:
                WrenchType(jobject _jthis, bool copy);
                WrenchType(const WrenchType &);
                static WrenchType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                WrenchType();
                ~WrenchType();
                VectorType getForce();
                void setForce(const VectorType & vectorType_0);
                VectorType getMoment();
                void setMoment(const VectorType & vectorType_0);
            }; // end class WrenchType
        } // end namespace base
    } // end namespace crcl



}; // end namespace crclj

