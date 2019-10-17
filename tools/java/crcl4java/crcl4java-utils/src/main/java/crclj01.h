// Never include this file (crclj01.h) directly. include crclj.h instead.


// start_segment_index = 10
// start_segment_index = 20
// segment_index = 1
// classesSegList=[class java.lang.Enum, class crcl.base.AngleUnitEnumType, class crcl.base.CRCLProgramType, class crcl.base.SetMotionCoordinationType, class crcl.base.ConfigureJointReportsType, class crcl.base.RunProgramType, class crcl.base.GuardsStatusesType, class crcl.base.ParameterSettingType, class crcl.base.PoseType, class crcl.base.TransSpeedType]

namespace crclj {


// class_index = 0 clss=class java.lang.Enum

    namespace java{
        namespace lang{
            
            class Enum : public Object {
            public:
                Enum(jobject _jthis, bool copy);
                Enum(const Enum &);
                static Enum cast(const Object &);
                static bool instanceof(const Object &);
                    
                protected:
                Enum() {};
                public:
                ~Enum();
                jstring name();
                jboolean equals(const Object & object_0);
                jstring toString();
                jint hashCode();
                jint compareTo(const Enum & enum_0);
                jint ordinal();
            }; // end class Enum
        } // end namespace lang
    } // end namespace java



    // class_index = 1 clss=class crcl.base.AngleUnitEnumType

    namespace crcl{
        namespace base{
            
            class AngleUnitEnumType : public ::crclj::java::lang::Enum {
            public:
                AngleUnitEnumType(jobject _jthis, bool copy);
                AngleUnitEnumType(const AngleUnitEnumType &);
                static AngleUnitEnumType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                protected:
                AngleUnitEnumType();
                public:
                ~AngleUnitEnumType();
                static AngleUnitEnumType getDEGREE();
                static AngleUnitEnumType getRADIAN();
                jstring value();
                static jobjectArray values();
                static AngleUnitEnumType valueOf(jstring string_0);
                static AngleUnitEnumType fromValue(jstring string_0);
            }; // end class AngleUnitEnumType


    // class_index = 2 clss=class crcl.base.CRCLProgramType

            
            class CRCLProgramType : public DataThingType {
            public:
                CRCLProgramType(jobject _jthis, bool copy);
                CRCLProgramType(const CRCLProgramType &);
                static CRCLProgramType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                CRCLProgramType();
                ~CRCLProgramType();
                InitCanonType getInitCanon();
                void setInitCanon(const InitCanonType & initCanonType_0);
                ::crclj::java::util::List getMiddleCommand();
                EndCanonType getEndCanon();
                void setEndCanon(const EndCanonType & endCanonType_0);
            }; // end class CRCLProgramType


    // class_index = 3 clss=class crcl.base.SetMotionCoordinationType

            
            class SetMotionCoordinationType : public MiddleCommandType {
            public:
                SetMotionCoordinationType(jobject _jthis, bool copy);
                SetMotionCoordinationType(const SetMotionCoordinationType &);
                static SetMotionCoordinationType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                SetMotionCoordinationType();
                ~SetMotionCoordinationType();
                jboolean isCoordinated();
                void setCoordinated(jboolean boolean_0);
            }; // end class SetMotionCoordinationType


    // class_index = 4 clss=class crcl.base.ConfigureJointReportsType

            
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


    // class_index = 5 clss=class crcl.base.RunProgramType

            
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


    // class_index = 6 clss=class crcl.base.GuardsStatusesType

            
            class GuardsStatusesType : public DataThingType {
            public:
                GuardsStatusesType(jobject _jthis, bool copy);
                GuardsStatusesType(const GuardsStatusesType &);
                static GuardsStatusesType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                GuardsStatusesType();
                ~GuardsStatusesType();
                ::crclj::java::util::List getGuard();
                jint getTriggerCount();
                void setTriggerCount(jint int_0);
                jlong getTriggerStopTimeMicros();
                void setTriggerStopTimeMicros(jlong long_0);
                ::crclj::java::lang::Double getTriggerValue();
                void setTriggerValue(const ::crclj::java::lang::Double & double_0);
                PoseType getTriggerPose();
                void setTriggerPose(const PoseType & poseType_0);
            }; // end class GuardsStatusesType


    // class_index = 7 clss=class crcl.base.ParameterSettingType

            
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


    // class_index = 8 clss=class crcl.base.PoseType

            
            class PoseType : public DataThingType {
            public:
                PoseType(jobject _jthis, bool copy);
                PoseType(const PoseType &);
                static PoseType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                PoseType();
                ~PoseType();
                void setPoint(const PointType & pointType_0);
                VectorType getXAxis();
                void setXAxis(const VectorType & vectorType_0);
                VectorType getZAxis();
                void setZAxis(const VectorType & vectorType_0);
                PointType getPoint();
            }; // end class PoseType


    // class_index = 9 clss=class crcl.base.TransSpeedType

            
            class TransSpeedType : public DataThingType {
            public:
                TransSpeedType(jobject _jthis, bool copy);
                TransSpeedType(const TransSpeedType &);
                static TransSpeedType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                TransSpeedType();
                ~TransSpeedType();
            }; // end class TransSpeedType
        } // end namespace base
    } // end namespace crcl



}; // end namespace crclj

