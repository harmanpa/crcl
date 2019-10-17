// Never include this file (crclj1.h) directly. include crclj.h instead.


// start_segment_index = 10
// start_segment_index = 20
// segment_index = 1
// classesSegList=[class crcl.base.RotAccelAbsoluteType, class crcl.base.GuardType, class crcl.base.MoveToType, class crcl.base.GuardLimitEnumType, class crcl.base.RunProgramType, class crcl.base.ForceTorqueSensorStatusType, class crcl.base.SetAngleUnitsType, class crcl.base.CloseToolChangerType, class crcl.base.EndCanonType, class crcl.base.GripperStatusType]

namespace crclj {


// class_index = 0 clss=class crcl.base.RotAccelAbsoluteType

    namespace crcl{
        namespace base{
            
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


    // class_index = 1 clss=class crcl.base.GuardType

            
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


    // class_index = 2 clss=class crcl.base.MoveToType

            
            class MoveToType : public MiddleCommandType {
            public:
                MoveToType(jobject _jthis, bool copy);
                MoveToType(const MoveToType &);
                static MoveToType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                MoveToType();
                ~MoveToType();
                jboolean isMoveStraight();
                void setMoveStraight(jboolean boolean_0);
                PoseType getEndPosition();
                void setEndPosition(const PoseType & poseType_0);
            }; // end class MoveToType


    // class_index = 3 clss=class crcl.base.GuardLimitEnumType

            
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


    // class_index = 4 clss=class crcl.base.RunProgramType

            
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


    // class_index = 5 clss=class crcl.base.ForceTorqueSensorStatusType

            
            class ForceTorqueSensorStatusType : public SensorStatusType {
            public:
                ForceTorqueSensorStatusType(jobject _jthis, bool copy);
                ForceTorqueSensorStatusType(const ForceTorqueSensorStatusType &);
                static ForceTorqueSensorStatusType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                ForceTorqueSensorStatusType();
                ~ForceTorqueSensorStatusType();
                jdouble getFx();
                void setFx(jdouble double_0);
                jdouble getFy();
                void setFy(jdouble double_0);
                jdouble getFz();
                void setFz(jdouble double_0);
                jdouble getTx();
                void setTx(jdouble double_0);
                jdouble getTy();
                void setTy(jdouble double_0);
                jdouble getTz();
                void setTz(jdouble double_0);
            }; // end class ForceTorqueSensorStatusType


    // class_index = 6 clss=class crcl.base.SetAngleUnitsType

            
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


    // class_index = 7 clss=class crcl.base.CloseToolChangerType

            
            class CloseToolChangerType : public MiddleCommandType {
            public:
                CloseToolChangerType(jobject _jthis, bool copy);
                CloseToolChangerType(const CloseToolChangerType &);
                static CloseToolChangerType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                CloseToolChangerType();
                ~CloseToolChangerType();
            }; // end class CloseToolChangerType


    // class_index = 8 clss=class crcl.base.EndCanonType

            
            class EndCanonType : public CRCLCommandType {
            public:
                EndCanonType(jobject _jthis, bool copy);
                EndCanonType(const EndCanonType &);
                static EndCanonType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                EndCanonType();
                ~EndCanonType();
            }; // end class EndCanonType


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

