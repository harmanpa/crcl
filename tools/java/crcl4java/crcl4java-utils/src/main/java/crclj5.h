// Never include this file (crclj5.h) directly. include crclj.h instead.


// start_segment_index = 50
// start_segment_index = 60
// segment_index = 5
// classesSegList=[class crcl.base.PoseToleranceType, class crcl.base.OpenToolChangerType, class crcl.base.ScalarSensorStatusType, class crcl.base.MoveThroughToType, class crcl.base.ActuateJointType, class crcl.base.JointDetailsType, class crcl.base.TransAccelAbsoluteType, class crcl.base.GetStatusType, class crcl.base.CRCLCommandInstanceType, class crcl.base.SetEndPoseToleranceType]

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


    // class_index = 1 clss=class crcl.base.OpenToolChangerType

            
            class OpenToolChangerType : public MiddleCommandType {
            public:
                OpenToolChangerType(jobject _jthis, bool copy);
                OpenToolChangerType(const OpenToolChangerType &);
                static OpenToolChangerType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                OpenToolChangerType();
                ~OpenToolChangerType();
            }; // end class OpenToolChangerType


    // class_index = 2 clss=class crcl.base.ScalarSensorStatusType

            
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


    // class_index = 3 clss=class crcl.base.MoveThroughToType

            
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


    // class_index = 5 clss=class crcl.base.JointDetailsType

            
            class JointDetailsType : public DataThingType {
            public:
                JointDetailsType(jobject _jthis, bool copy);
                JointDetailsType(const JointDetailsType &);
                static JointDetailsType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                JointDetailsType();
                ~JointDetailsType();
            }; // end class JointDetailsType


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


    // class_index = 8 clss=class crcl.base.CRCLCommandInstanceType

            
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

