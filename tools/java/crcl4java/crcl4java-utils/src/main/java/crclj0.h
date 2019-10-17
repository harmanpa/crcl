// Never include this file (crclj0.h) directly. include crclj.h instead.


// start_segment_index = 0
// start_segment_index = 10
// segment_index = 0
// classesSegList=[class java.lang.Enum, class crcl.base.ForceUnitEnumType, class crcl.base.TorqueUnitEnumType, class crcl.base.DataThingType, class crcl.base.CRCLCommandType, class crcl.base.MiddleCommandType, class crcl.base.MessageType, class crcl.base.SensorStatusType, class crcl.base.OnOffSensorStatusType, class crcl.base.RotAccelType]

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



    // class_index = 1 clss=class crcl.base.ForceUnitEnumType

    namespace crcl{
        namespace base{
            
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


    // class_index = 2 clss=class crcl.base.TorqueUnitEnumType

            
            class TorqueUnitEnumType : public ::crclj::java::lang::Enum {
            public:
                TorqueUnitEnumType(jobject _jthis, bool copy);
                TorqueUnitEnumType(const TorqueUnitEnumType &);
                static TorqueUnitEnumType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                protected:
                TorqueUnitEnumType();
                public:
                ~TorqueUnitEnumType();
                static TorqueUnitEnumType getNEWTON_METER();
                static TorqueUnitEnumType getFOOT_POUND();
                jstring value();
                static jobjectArray values();
                static TorqueUnitEnumType valueOf(jstring string_0);
                static TorqueUnitEnumType fromValue(jstring string_0);
            }; // end class TorqueUnitEnumType


    // class_index = 3 clss=class crcl.base.DataThingType

            
            class DataThingType : public ::crclj::java::lang::Object {
            public:
                DataThingType(jobject _jthis, bool copy);
                DataThingType(const DataThingType &);
                static DataThingType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                DataThingType();
                ~DataThingType();
                jstring getName();
                void setName(jstring string_0);
                void setName(const char * easyArg_0);
            }; // end class DataThingType


    // class_index = 4 clss=class crcl.base.CRCLCommandType

            
            class CRCLCommandType : public DataThingType {
            public:
                CRCLCommandType(jobject _jthis, bool copy);
                CRCLCommandType(const CRCLCommandType &);
                static CRCLCommandType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                CRCLCommandType();
                ~CRCLCommandType();
                jlong getCommandID();
                void setCommandID(jlong long_0);
                ::crclj::java::util::List getGuard();
            }; // end class CRCLCommandType


    // class_index = 5 clss=class crcl.base.MiddleCommandType

            
            class MiddleCommandType : public CRCLCommandType {
            public:
                MiddleCommandType(jobject _jthis, bool copy);
                MiddleCommandType(const MiddleCommandType &);
                static MiddleCommandType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                MiddleCommandType();
                ~MiddleCommandType();
            }; // end class MiddleCommandType


    // class_index = 6 clss=class crcl.base.MessageType

            
            class MessageType : public MiddleCommandType {
            public:
                MessageType(jobject _jthis, bool copy);
                MessageType(const MessageType &);
                static MessageType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                MessageType();
                ~MessageType();
                jstring getMessage();
                void setMessage(jstring string_0);
                void setMessage(const char * easyArg_0);
            }; // end class MessageType


    // class_index = 7 clss=class crcl.base.SensorStatusType

            
            class SensorStatusType : public DataThingType {
            public:
                SensorStatusType(jobject _jthis, bool copy);
                SensorStatusType(const SensorStatusType &);
                static SensorStatusType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                SensorStatusType();
                ~SensorStatusType();
                jstring getSensorID();
                void setSensorID(jstring string_0);
                void setSensorID(const char * easyArg_0);
                jint getReadCount();
                void setReadCount(jint int_0);
                jlong getLastReadTime();
                void setLastReadTime(jlong long_0);
                ::crclj::java::util::List getSensorParameterSetting();
            }; // end class SensorStatusType


    // class_index = 8 clss=class crcl.base.OnOffSensorStatusType

            
            class OnOffSensorStatusType : public SensorStatusType {
            public:
                OnOffSensorStatusType(jobject _jthis, bool copy);
                OnOffSensorStatusType(const OnOffSensorStatusType &);
                static OnOffSensorStatusType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                OnOffSensorStatusType();
                ~OnOffSensorStatusType();
                jboolean isOn();
                void setOn(jboolean boolean_0);
            }; // end class OnOffSensorStatusType


    // class_index = 9 clss=class crcl.base.RotAccelType

            
            class RotAccelType : public DataThingType {
            public:
                RotAccelType(jobject _jthis, bool copy);
                RotAccelType(const RotAccelType &);
                static RotAccelType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                RotAccelType();
                ~RotAccelType();
            }; // end class RotAccelType
        } // end namespace base
    } // end namespace crcl



}; // end namespace crclj

