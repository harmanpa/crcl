// Never include this file (crclj00.h) directly. include crclj.h instead.


// start_segment_index = 0
// start_segment_index = 10
// segment_index = 0
// classesSegList=[class crcl.base.DataThingType, class crcl.base.CRCLCommandType, class crcl.base.MiddleCommandType, class crcl.base.EnableSensorType, class crcl.base.SensorStatusType, class crcl.base.ForceTorqueSensorStatusType, class crcl.base.SetLengthUnitsType, class crcl.base.RotSpeedType, class crcl.base.RotSpeedRelativeType, class crcl.base.InitCanonType]

namespace crclj {


// class_index = 0 clss=class crcl.base.DataThingType

    namespace crcl{
        namespace base{
            
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


    // class_index = 1 clss=class crcl.base.CRCLCommandType

            
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


    // class_index = 2 clss=class crcl.base.MiddleCommandType

            
            class MiddleCommandType : public CRCLCommandType {
            public:
                MiddleCommandType(jobject _jthis, bool copy);
                MiddleCommandType(const MiddleCommandType &);
                static MiddleCommandType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                MiddleCommandType();
                ~MiddleCommandType();
            }; // end class MiddleCommandType


    // class_index = 3 clss=class crcl.base.EnableSensorType

            
            class EnableSensorType : public MiddleCommandType {
            public:
                EnableSensorType(jobject _jthis, bool copy);
                EnableSensorType(const EnableSensorType &);
                static EnableSensorType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                EnableSensorType();
                ~EnableSensorType();
                jstring getSensorID();
                void setSensorID(jstring string_0);
                void setSensorID(const char * easyArg_0);
                ::crclj::java::util::List getSensorOption();
            }; // end class EnableSensorType


    // class_index = 4 clss=class crcl.base.SensorStatusType

            
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


    // class_index = 6 clss=class crcl.base.SetLengthUnitsType

            
            class SetLengthUnitsType : public MiddleCommandType {
            public:
                SetLengthUnitsType(jobject _jthis, bool copy);
                SetLengthUnitsType(const SetLengthUnitsType &);
                static SetLengthUnitsType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                SetLengthUnitsType();
                ~SetLengthUnitsType();
                LengthUnitEnumType getUnitName();
                void setUnitName(const LengthUnitEnumType & lengthUnitEnumType_0);
            }; // end class SetLengthUnitsType


    // class_index = 7 clss=class crcl.base.RotSpeedType

            
            class RotSpeedType : public DataThingType {
            public:
                RotSpeedType(jobject _jthis, bool copy);
                RotSpeedType(const RotSpeedType &);
                static RotSpeedType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                RotSpeedType();
                ~RotSpeedType();
            }; // end class RotSpeedType


    // class_index = 8 clss=class crcl.base.RotSpeedRelativeType

            
            class RotSpeedRelativeType : public RotSpeedType {
            public:
                RotSpeedRelativeType(jobject _jthis, bool copy);
                RotSpeedRelativeType(const RotSpeedRelativeType &);
                static RotSpeedRelativeType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                RotSpeedRelativeType();
                ~RotSpeedRelativeType();
                jdouble getFraction();
                void setFraction(jdouble double_0);
            }; // end class RotSpeedRelativeType


    // class_index = 9 clss=class crcl.base.InitCanonType

            
            class InitCanonType : public CRCLCommandType {
            public:
                InitCanonType(jobject _jthis, bool copy);
                InitCanonType(const InitCanonType &);
                static InitCanonType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                InitCanonType();
                ~InitCanonType();
            }; // end class InitCanonType
        } // end namespace base
    } // end namespace crcl



}; // end namespace crclj

