// Never include this file (crclj3.h) directly. include crclj.h instead.


// start_segment_index = 30
// start_segment_index = 40
// segment_index = 3
// classesSegList=[class crcl.base.SetForceUnitsType, class crcl.base.TransAccelType, class crcl.base.TransAccelRelativeType, class crcl.base.SetEndEffectorType, class crcl.base.JointStatusType, class crcl.base.PoseStatusType, class crcl.base.ConfigureStatusReportType, class crcl.base.VacuumGripperStatusType, class crcl.base.PoseType, class crcl.base.PoseAndSetType]

namespace crclj {


// class_index = 0 clss=class crcl.base.SetForceUnitsType

    namespace crcl{
        namespace base{
            
            class SetForceUnitsType : public MiddleCommandType {
            public:
                SetForceUnitsType(jobject _jthis, bool copy);
                SetForceUnitsType(const SetForceUnitsType &);
                static SetForceUnitsType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                SetForceUnitsType();
                ~SetForceUnitsType();
                ForceUnitEnumType getUnitName();
                void setUnitName(const ForceUnitEnumType & forceUnitEnumType_0);
            }; // end class SetForceUnitsType


    // class_index = 1 clss=class crcl.base.TransAccelType

            
            class TransAccelType : public DataThingType {
            public:
                TransAccelType(jobject _jthis, bool copy);
                TransAccelType(const TransAccelType &);
                static TransAccelType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                TransAccelType();
                ~TransAccelType();
            }; // end class TransAccelType


    // class_index = 2 clss=class crcl.base.TransAccelRelativeType

            
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


    // class_index = 3 clss=class crcl.base.SetEndEffectorType

            
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


    // class_index = 4 clss=class crcl.base.JointStatusType

            
            class JointStatusType : public DataThingType {
            public:
                JointStatusType(jobject _jthis, bool copy);
                JointStatusType(const JointStatusType &);
                static JointStatusType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                JointStatusType();
                ~JointStatusType();
                ::crclj::java::lang::Double getJointTorqueOrForce();
                jint getJointNumber();
                void setJointNumber(jint int_0);
                ::crclj::java::lang::Double getJointPosition();
                void setJointPosition(const ::crclj::java::lang::Double & double_0);
                void setJointTorqueOrForce(const ::crclj::java::lang::Double & double_0);
                ::crclj::java::lang::Double getJointVelocity();
                void setJointVelocity(const ::crclj::java::lang::Double & double_0);
            }; // end class JointStatusType


    // class_index = 5 clss=class crcl.base.PoseStatusType

            
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


    // class_index = 6 clss=class crcl.base.ConfigureStatusReportType

            
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


    // class_index = 7 clss=class crcl.base.VacuumGripperStatusType

            
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


    // class_index = 8 clss=class crcl.base.PoseType

            
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


    // class_index = 9 clss=class crcl.base.PoseAndSetType

            
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
        } // end namespace base
    } // end namespace crcl



}; // end namespace crclj

