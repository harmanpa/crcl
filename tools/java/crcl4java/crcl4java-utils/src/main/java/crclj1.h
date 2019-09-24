// Never include this file (crclj1.h) directly. include crclj.h instead.


// start_segment_index = 10
// start_segment_index = 20
// segment_index = 1
// classesSegList=[class crcl.base.JointDetailsType, class crcl.base.JointForceTorqueType, class crcl.base.ConfigureStatusReportType, class crcl.base.GripperStatusType, class crcl.base.VacuumGripperStatusType, class crcl.base.PoseType, class crcl.base.PoseAndSetType, class crcl.base.CommandStateEnumType, class crcl.base.SetRobotParametersType, class crcl.base.RunProgramType]

namespace crclj {


// class_index = 0 clss=class crcl.base.JointDetailsType

    namespace crcl{
        namespace base{
            
            class JointDetailsType : public DataThingType {
            public:
                JointDetailsType(jobject _jthis, bool copy);
                JointDetailsType(const JointDetailsType &);
                static JointDetailsType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                JointDetailsType();
                ~JointDetailsType();
            }; // end class JointDetailsType


    // class_index = 1 clss=class crcl.base.JointForceTorqueType

            
            class JointForceTorqueType : public JointDetailsType {
            public:
                JointForceTorqueType(jobject _jthis, bool copy);
                JointForceTorqueType(const JointForceTorqueType &);
                static JointForceTorqueType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                JointForceTorqueType();
                ~JointForceTorqueType();
                ::crclj::java::lang::Double getSetting();
                void setSetting(const ::crclj::java::lang::Double & double_0);
                ::crclj::java::lang::Double getChangeRate();
                void setChangeRate(const ::crclj::java::lang::Double & double_0);
            }; // end class JointForceTorqueType


    // class_index = 2 clss=class crcl.base.ConfigureStatusReportType

            
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


    // class_index = 3 clss=class crcl.base.GripperStatusType

            
            class GripperStatusType : public DataThingType {
            public:
                GripperStatusType(jobject _jthis, bool copy);
                GripperStatusType(const GripperStatusType &);
                static GripperStatusType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                GripperStatusType();
                ~GripperStatusType();
                jstring getGripperName();
                void setGripperName(jstring string_0);
                void setGripperName(const char * easyArg_0);
                ::crclj::java::util::List getGripperOption();
                ::crclj::java::lang::Boolean isHoldingObject();
                void setHoldingObject(const ::crclj::java::lang::Boolean & boolean_0);
            }; // end class GripperStatusType


    // class_index = 4 clss=class crcl.base.VacuumGripperStatusType

            
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


    // class_index = 5 clss=class crcl.base.PoseType

            
            class PoseType : public DataThingType {
            public:
                PoseType(jobject _jthis, bool copy);
                PoseType(const PoseType &);
                static PoseType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                PoseType();
                ~PoseType();
                PointType getPoint();
                void setPoint(const PointType & pointType_0);
                VectorType getXAxis();
                void setXAxis(const VectorType & vectorType_0);
                VectorType getZAxis();
                void setZAxis(const VectorType & vectorType_0);
            }; // end class PoseType


    // class_index = 6 clss=class crcl.base.PoseAndSetType

            
            class PoseAndSetType : public PoseType {
            public:
                PoseAndSetType(jobject _jthis, bool copy);
                PoseAndSetType(const PoseAndSetType &);
                static PoseAndSetType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                PoseAndSetType();
                ~PoseAndSetType();
                jboolean isCoordinated();
                void setCoordinated(jboolean boolean_0);
                TransSpeedType getTransSpeed();
                void setTransSpeed(const TransSpeedType & transSpeedType_0);
                RotSpeedType getRotSpeed();
                void setRotSpeed(const RotSpeedType & rotSpeedType_0);
                TransAccelType getTransAccel();
                void setTransAccel(const TransAccelType & transAccelType_0);
                RotAccelType getRotAccel();
                void setRotAccel(const RotAccelType & rotAccelType_0);
                PoseToleranceType getTolerance();
                void setTolerance(const PoseToleranceType & poseToleranceType_0);
            }; // end class PoseAndSetType


    // class_index = 7 clss=class crcl.base.CommandStateEnumType

            
            class CommandStateEnumType : public ::crclj::java::lang::Enum {
            public:
                CommandStateEnumType(jobject _jthis, bool copy);
                CommandStateEnumType(const CommandStateEnumType &);
                static CommandStateEnumType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                protected:
                CommandStateEnumType();
                public:
                ~CommandStateEnumType();
                static CommandStateEnumType getCRCL_DONE();
                static CommandStateEnumType getCRCL_ERROR();
                static CommandStateEnumType getCRCL_WORKING();
                static CommandStateEnumType getCRCL_READY();
                jstring value();
                static jobjectArray values();
                static CommandStateEnumType valueOf(jstring string_0);
                static CommandStateEnumType fromValue(jstring string_0);
            }; // end class CommandStateEnumType


    // class_index = 8 clss=class crcl.base.SetRobotParametersType

            
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


    // class_index = 9 clss=class crcl.base.RunProgramType

            
            class RunProgramType : public MiddleCommandType {
            public:
                RunProgramType(jobject _jthis, bool copy);
                RunProgramType(const RunProgramType &);
                static RunProgramType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                RunProgramType();
                ~RunProgramType();
                jstring getProgramText();
                void setProgramText(jstring string_0);
                void setProgramText(const char * easyArg_0);
            }; // end class RunProgramType
        } // end namespace base
    } // end namespace crcl



}; // end namespace crclj

