// Never include this file (crclj08.h) directly. include crclj.h instead.


// start_segment_index = 80
// start_segment_index = 90
// segment_index = 8
// classesSegList=[class crcl.base.SetEndEffectorParametersType, class crcl.base.TransAccelType, class crcl.base.MoveThroughToType, class crcl.base.ActuateJointType, class crcl.base.TransAccelRelativeType, class crcl.base.SetEndEffectorType, class crcl.base.TransAccelAbsoluteType, class crcl.base.GetStatusType, class crcl.base.ConfigureJointReportType, class crcl.base.SetEndPoseToleranceType]

namespace crclj {


// class_index = 0 clss=class crcl.base.SetEndEffectorParametersType

    namespace crcl{
        namespace base{
            
            class SetEndEffectorParametersType : public MiddleCommandType {
            public:
                SetEndEffectorParametersType(jobject _jthis, bool copy);
                SetEndEffectorParametersType(const SetEndEffectorParametersType &);
                static SetEndEffectorParametersType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                SetEndEffectorParametersType();
                ~SetEndEffectorParametersType();
                ::crclj::java::util::List getParameterSetting();
            }; // end class SetEndEffectorParametersType


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


    // class_index = 2 clss=class crcl.base.MoveThroughToType

            
            class MoveThroughToType : public MiddleCommandType {
            public:
                MoveThroughToType(jobject _jthis, bool copy);
                MoveThroughToType(const MoveThroughToType &);
                static MoveThroughToType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                MoveThroughToType();
                ~MoveThroughToType();
                jboolean isMoveStraight();
                void setMoveStraight(jboolean boolean_0);
                ::crclj::java::util::List getWaypoint();
                jint getNumPositions();
                void setNumPositions(jint int_0);
            }; // end class MoveThroughToType


    // class_index = 3 clss=class crcl.base.ActuateJointType

            
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


    // class_index = 4 clss=class crcl.base.TransAccelRelativeType

            
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


    // class_index = 5 clss=class crcl.base.SetEndEffectorType

            
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


    // class_index = 6 clss=class crcl.base.TransAccelAbsoluteType

            
            class TransAccelAbsoluteType : public TransAccelType {
            public:
                TransAccelAbsoluteType(jobject _jthis, bool copy);
                TransAccelAbsoluteType(const TransAccelAbsoluteType &);
                static TransAccelAbsoluteType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                TransAccelAbsoluteType();
                ~TransAccelAbsoluteType();
                jdouble getSetting();
                void setSetting(jdouble double_0);
            }; // end class TransAccelAbsoluteType


    // class_index = 7 clss=class crcl.base.GetStatusType

            
            class GetStatusType : public MiddleCommandType {
            public:
                GetStatusType(jobject _jthis, bool copy);
                GetStatusType(const GetStatusType &);
                static GetStatusType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                GetStatusType();
                ~GetStatusType();
            }; // end class GetStatusType


    // class_index = 8 clss=class crcl.base.ConfigureJointReportType

            
            class ConfigureJointReportType : public DataThingType {
            public:
                ConfigureJointReportType(jobject _jthis, bool copy);
                ConfigureJointReportType(const ConfigureJointReportType &);
                static ConfigureJointReportType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                ConfigureJointReportType();
                ~ConfigureJointReportType();
                jboolean isReportPosition();
                void setReportPosition(jboolean boolean_0);
                jboolean isReportTorqueOrForce();
                void setReportTorqueOrForce(jboolean boolean_0);
                jboolean isReportVelocity();
                void setReportVelocity(jboolean boolean_0);
                jint getJointNumber();
                void setJointNumber(jint int_0);
            }; // end class ConfigureJointReportType


    // class_index = 9 clss=class crcl.base.SetEndPoseToleranceType

            
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
        } // end namespace base
    } // end namespace crcl



}; // end namespace crclj

