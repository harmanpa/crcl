// Never include this file (crclj7.h) directly. include crclj.h instead.


// start_segment_index = 70
// start_segment_index = 80
// segment_index = 7
// classesSegList=[class crcl.base.CommandStatusType, class crcl.base.SetRotAccelType, class crcl.base.ParallelGripperStatusType, class crcl.base.JointSpeedAccelType, class crcl.base.JointForceTorqueType, class crcl.base.ConfigureJointReportType, class crcl.base.EnableRobotParameterStatusType, class crcl.base.VectorType, class crcl.base.JointStatusesType, class crcl.base.CommandStateEnumType]

namespace crclj {


// class_index = 0 clss=class crcl.base.CommandStatusType

    namespace crcl{
        namespace base{
            
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


    // class_index = 1 clss=class crcl.base.SetRotAccelType

            
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


    // class_index = 2 clss=class crcl.base.ParallelGripperStatusType

            
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


    // class_index = 3 clss=class crcl.base.JointSpeedAccelType

            
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


    // class_index = 4 clss=class crcl.base.JointForceTorqueType

            
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


    // class_index = 5 clss=class crcl.base.ConfigureJointReportType

            
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


    // class_index = 6 clss=class crcl.base.EnableRobotParameterStatusType

            
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


    // class_index = 7 clss=class crcl.base.VectorType

            
            class VectorType : public DataThingType {
            public:
                VectorType(jobject _jthis, bool copy);
                VectorType(const VectorType &);
                static VectorType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                VectorType();
                ~VectorType();
                void setI(jdouble double_0);
                jdouble getJ();
                jdouble getI();
                void setJ(jdouble double_0);
                jdouble getK();
                void setK(jdouble double_0);
            }; // end class VectorType


    // class_index = 8 clss=class crcl.base.JointStatusesType

            
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


    // class_index = 9 clss=class crcl.base.CommandStateEnumType

            
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
        } // end namespace base
    } // end namespace crcl



}; // end namespace crclj

