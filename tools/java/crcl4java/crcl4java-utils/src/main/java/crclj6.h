// Never include this file (crclj6.h) directly. include crclj.h instead.


// start_segment_index = 60
// start_segment_index = 70
// segment_index = 6
// classesSegList=[class crcl.base.GuardLimitEnumType, class crcl.base.ForceUnitEnumType, class crcl.base.MoveScrewType, class crcl.base.SetForceUnitsType, class crcl.base.JointStatusType, class crcl.base.TransAccelType, class crcl.base.TransAccelAbsoluteType, class crcl.base.GetStatusType, class crcl.base.CRCLCommandInstanceType, class crcl.base.EnableRobotParameterStatusType]

namespace crclj {


// class_index = 0 clss=class crcl.base.GuardLimitEnumType

    namespace crcl{
        namespace base{
            
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


    // class_index = 1 clss=class crcl.base.ForceUnitEnumType

            
            class ForceUnitEnumType : public ::crclj::java::lang::Enum {
            public:
                ForceUnitEnumType(jobject _jthis, bool copy);
                ForceUnitEnumType(const ForceUnitEnumType &);
                static ForceUnitEnumType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                protected:
                ForceUnitEnumType();
                public:
                ~ForceUnitEnumType();
                static ForceUnitEnumType getNEWTON();
                static ForceUnitEnumType getPOUND();
                static ForceUnitEnumType getOUNCE();
                jstring value();
                static jobjectArray values();
                static ForceUnitEnumType valueOf(jstring string_0);
                static ForceUnitEnumType fromValue(jstring string_0);
            }; // end class ForceUnitEnumType


    // class_index = 2 clss=class crcl.base.MoveScrewType

            
            class MoveScrewType : public MiddleCommandType {
            public:
                MoveScrewType(jobject _jthis, bool copy);
                MoveScrewType(const MoveScrewType &);
                static MoveScrewType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                MoveScrewType();
                ~MoveScrewType();
                PoseType getStartPosition();
                void setStartPosition(const PoseType & poseType_0);
                PointType getAxisPoint();
                void setAxisPoint(const PointType & pointType_0);
                ::crclj::java::lang::Double getAxialDistanceFree();
                void setAxialDistanceFree(const ::crclj::java::lang::Double & double_0);
                jdouble getAxialDistanceScrew();
                void setAxialDistanceScrew(jdouble double_0);
                jdouble getTurn();
                void setTurn(jdouble double_0);
            }; // end class MoveScrewType


    // class_index = 3 clss=class crcl.base.SetForceUnitsType

            
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


    // class_index = 4 clss=class crcl.base.JointStatusType

            
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


    // class_index = 5 clss=class crcl.base.TransAccelType

            
            class TransAccelType : public DataThingType {
            public:
                TransAccelType(jobject _jthis, bool copy);
                TransAccelType(const TransAccelType &);
                static TransAccelType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                TransAccelType();
                ~TransAccelType();
            }; // end class TransAccelType


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


    // class_index = 9 clss=class crcl.base.EnableRobotParameterStatusType

            
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
        } // end namespace base
    } // end namespace crcl



}; // end namespace crclj

