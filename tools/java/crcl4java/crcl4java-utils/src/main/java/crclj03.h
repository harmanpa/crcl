// Never include this file (crclj03.h) directly. include crclj.h instead.


// start_segment_index = 30
// start_segment_index = 40
// segment_index = 3
// classesSegList=[class crcl.base.JointStatusType, class crcl.base.PoseStatusType, class crcl.base.CRCLCommandInstanceType, class crcl.base.EnableRobotParameterStatusType, class crcl.base.VectorType, class crcl.base.JointStatusesType, class crcl.base.CommandStateEnumType, class crcl.base.SetRobotParametersType, class crcl.base.JointLimitType, class crcl.base.GripperStatusType]

namespace crclj {


// class_index = 0 clss=class crcl.base.JointStatusType

    namespace crcl{
        namespace base{
            
            class JointStatusType : public DataThingType {
            public:
                JointStatusType(jobject _jthis, bool copy);
                JointStatusType(const JointStatusType &);
                static JointStatusType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                JointStatusType();
                ~JointStatusType();
                jint getJointNumber();
                void setJointNumber(jint int_0);
                ::crclj::java::lang::Double getJointPosition();
                void setJointPosition(const ::crclj::java::lang::Double & double_0);
                ::crclj::java::lang::Double getJointTorqueOrForce();
                void setJointTorqueOrForce(const ::crclj::java::lang::Double & double_0);
                ::crclj::java::lang::Double getJointVelocity();
                void setJointVelocity(const ::crclj::java::lang::Double & double_0);
            }; // end class JointStatusType


    // class_index = 1 clss=class crcl.base.PoseStatusType

            
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


    // class_index = 2 clss=class crcl.base.CRCLCommandInstanceType

            
            class CRCLCommandInstanceType : public DataThingType {
            public:
                CRCLCommandInstanceType(jobject _jthis, bool copy);
                CRCLCommandInstanceType(const CRCLCommandInstanceType &);
                static CRCLCommandInstanceType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                CRCLCommandInstanceType();
                ~CRCLCommandInstanceType();
                CRCLCommandType getCRCLCommand();
                void setCRCLCommand(const CRCLCommandType & cRCLCommandType_0);
                jstring getProgramFile();
                void setProgramFile(jstring string_0);
                void setProgramFile(const char * easyArg_0);
                ::crclj::java::lang::Integer getProgramIndex();
                void setProgramIndex(const ::crclj::java::lang::Integer & integer_0);
                ::crclj::java::lang::Integer getProgramLength();
                void setProgramLength(const ::crclj::java::lang::Integer & integer_0);
            }; // end class CRCLCommandInstanceType


    // class_index = 3 clss=class crcl.base.EnableRobotParameterStatusType

            
            class EnableRobotParameterStatusType : public MiddleCommandType {
            public:
                EnableRobotParameterStatusType(jobject _jthis, bool copy);
                EnableRobotParameterStatusType(const EnableRobotParameterStatusType &);
                static EnableRobotParameterStatusType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                EnableRobotParameterStatusType();
                ~EnableRobotParameterStatusType();
                jstring getRobotParameterName();
                void setRobotParameterName(jstring string_0);
                void setRobotParameterName(const char * easyArg_0);
            }; // end class EnableRobotParameterStatusType


    // class_index = 4 clss=class crcl.base.VectorType

            
            class VectorType : public DataThingType {
            public:
                VectorType(jobject _jthis, bool copy);
                VectorType(const VectorType &);
                static VectorType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                VectorType();
                ~VectorType();
                void setK(jdouble double_0);
                jdouble getI();
                void setI(jdouble double_0);
                jdouble getJ();
                void setJ(jdouble double_0);
                jdouble getK();
            }; // end class VectorType


    // class_index = 5 clss=class crcl.base.JointStatusesType

            
            class JointStatusesType : public DataThingType {
            public:
                JointStatusesType(jobject _jthis, bool copy);
                JointStatusesType(const JointStatusesType &);
                static JointStatusesType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                JointStatusesType();
                ~JointStatusesType();
                ::crclj::java::util::List getJointStatus();
            }; // end class JointStatusesType


    // class_index = 6 clss=class crcl.base.CommandStateEnumType

            
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


    // class_index = 7 clss=class crcl.base.SetRobotParametersType

            
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


    // class_index = 8 clss=class crcl.base.JointLimitType

            
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


    // class_index = 9 clss=class crcl.base.GripperStatusType

            
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
        } // end namespace base
    } // end namespace crcl



}; // end namespace crclj

