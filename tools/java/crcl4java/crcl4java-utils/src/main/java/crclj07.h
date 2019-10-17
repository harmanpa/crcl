// Never include this file (crclj07.h) directly. include crclj.h instead.


// start_segment_index = 70
// start_segment_index = 80
// segment_index = 7
// classesSegList=[class crcl.base.SetTransSpeedType, class crcl.base.ScalarSensorStatusType, class crcl.base.CommandStatusType, class crcl.base.SetRotAccelType, class crcl.base.SetAngleUnitsType, class crcl.base.SensorStatusesType, class crcl.base.LengthUnitEnumType, class crcl.base.ConfigureStatusReportType, class crcl.base.VacuumGripperStatusType, class crcl.base.SetTorqueUnitsType]

namespace crclj {


// class_index = 0 clss=class crcl.base.SetTransSpeedType

    namespace crcl{
        namespace base{
            
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


    // class_index = 1 clss=class crcl.base.ScalarSensorStatusType

            
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


    // class_index = 2 clss=class crcl.base.CommandStatusType

            
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


    // class_index = 4 clss=class crcl.base.SetAngleUnitsType

            
            class SetAngleUnitsType : public MiddleCommandType {
            public:
                SetAngleUnitsType(jobject _jthis, bool copy);
                SetAngleUnitsType(const SetAngleUnitsType &);
                static SetAngleUnitsType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                SetAngleUnitsType();
                ~SetAngleUnitsType();
                AngleUnitEnumType getUnitName();
                void setUnitName(const AngleUnitEnumType & angleUnitEnumType_0);
            }; // end class SetAngleUnitsType


    // class_index = 5 clss=class crcl.base.SensorStatusesType

            
            class SensorStatusesType : public DataThingType {
            public:
                SensorStatusesType(jobject _jthis, bool copy);
                SensorStatusesType(const SensorStatusesType &);
                static SensorStatusesType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                SensorStatusesType();
                ~SensorStatusesType();
                ::crclj::java::util::List getOnOffSensorStatus();
                ::crclj::java::util::List getScalarSensorStatus();
                ::crclj::java::util::List getCountSensorStatus();
                ::crclj::java::util::List getForceTorqueSensorStatus();
            }; // end class SensorStatusesType


    // class_index = 6 clss=class crcl.base.LengthUnitEnumType

            
            class LengthUnitEnumType : public ::crclj::java::lang::Enum {
            public:
                LengthUnitEnumType(jobject _jthis, bool copy);
                LengthUnitEnumType(const LengthUnitEnumType &);
                static LengthUnitEnumType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                protected:
                LengthUnitEnumType();
                public:
                ~LengthUnitEnumType();
                static LengthUnitEnumType getMETER();
                static LengthUnitEnumType getMILLIMETER();
                static LengthUnitEnumType getINCH();
                jstring value();
                static jobjectArray values();
                static LengthUnitEnumType valueOf(jstring string_0);
                static LengthUnitEnumType fromValue(jstring string_0);
            }; // end class LengthUnitEnumType


    // class_index = 7 clss=class crcl.base.ConfigureStatusReportType

            
            class ConfigureStatusReportType : public MiddleCommandType {
            public:
                ConfigureStatusReportType(jobject _jthis, bool copy);
                ConfigureStatusReportType(const ConfigureStatusReportType &);
                static ConfigureStatusReportType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                ConfigureStatusReportType();
                ~ConfigureStatusReportType();
                jboolean isReportJointStatuses();
                void setReportJointStatuses(jboolean boolean_0);
                jboolean isReportPoseStatus();
                void setReportPoseStatus(jboolean boolean_0);
                jboolean isReportGripperStatus();
                void setReportGripperStatus(jboolean boolean_0);
                jboolean isReportSettingsStatus();
                void setReportSettingsStatus(jboolean boolean_0);
                jboolean isReportSensorsStatus();
                void setReportSensorsStatus(jboolean boolean_0);
                jboolean isReportGuardsStatus();
                void setReportGuardsStatus(jboolean boolean_0);
            }; // end class ConfigureStatusReportType


    // class_index = 8 clss=class crcl.base.VacuumGripperStatusType

            
            class VacuumGripperStatusType : public GripperStatusType {
            public:
                VacuumGripperStatusType(jobject _jthis, bool copy);
                VacuumGripperStatusType(const VacuumGripperStatusType &);
                static VacuumGripperStatusType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                VacuumGripperStatusType();
                ~VacuumGripperStatusType();
                jboolean isIsPowered();
                void setIsPowered(jboolean boolean_0);
            }; // end class VacuumGripperStatusType


    // class_index = 9 clss=class crcl.base.SetTorqueUnitsType

            
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
        } // end namespace base
    } // end namespace crcl



}; // end namespace crclj

