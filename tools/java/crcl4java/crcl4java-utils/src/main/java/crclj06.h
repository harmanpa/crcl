// Never include this file (crclj06.h) directly. include crclj.h instead.


// start_segment_index = 60
// start_segment_index = 70
// segment_index = 6
// classesSegList=[class crcl.base.EnableGripperType, class crcl.base.WrenchType, class crcl.base.CountSensorStatusType, class crcl.base.ForceUnitEnumType, class crcl.base.MoveScrewType, class crcl.base.SetForceUnitsType, class crcl.base.TransSpeedAbsoluteType, class crcl.base.RotAccelAbsoluteType, class crcl.base.GuardType, class crcl.base.DisableRobotParameterStatusType]

namespace crclj {


// class_index = 0 clss=class crcl.base.EnableGripperType

    namespace crcl{
        namespace base{
            
            class EnableGripperType : public MiddleCommandType {
            public:
                EnableGripperType(jobject _jthis, bool copy);
                EnableGripperType(const EnableGripperType &);
                static EnableGripperType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                EnableGripperType();
                ~EnableGripperType();
                jstring getGripperName();
                void setGripperName(jstring string_0);
                void setGripperName(const char * easyArg_0);
                ::crclj::java::util::List getGripperOption();
            }; // end class EnableGripperType


    // class_index = 1 clss=class crcl.base.WrenchType

            
            class WrenchType : public DataThingType {
            public:
                WrenchType(jobject _jthis, bool copy);
                WrenchType(const WrenchType &);
                static WrenchType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                WrenchType();
                ~WrenchType();
                VectorType getForce();
                void setForce(const VectorType & vectorType_0);
                VectorType getMoment();
                void setMoment(const VectorType & vectorType_0);
            }; // end class WrenchType


    // class_index = 2 clss=class crcl.base.CountSensorStatusType

            
            class CountSensorStatusType : public SensorStatusType {
            public:
                CountSensorStatusType(jobject _jthis, bool copy);
                CountSensorStatusType(const CountSensorStatusType &);
                static CountSensorStatusType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                CountSensorStatusType();
                ~CountSensorStatusType();
                jint getCountValue();
                void setCountValue(jint int_0);
            }; // end class CountSensorStatusType


    // class_index = 3 clss=class crcl.base.ForceUnitEnumType

            
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


    // class_index = 4 clss=class crcl.base.MoveScrewType

            
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


    // class_index = 5 clss=class crcl.base.SetForceUnitsType

            
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


    // class_index = 6 clss=class crcl.base.TransSpeedAbsoluteType

            
            class TransSpeedAbsoluteType : public TransSpeedType {
            public:
                TransSpeedAbsoluteType(jobject _jthis, bool copy);
                TransSpeedAbsoluteType(const TransSpeedAbsoluteType &);
                static TransSpeedAbsoluteType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                TransSpeedAbsoluteType();
                ~TransSpeedAbsoluteType();
                jdouble getSetting();
                void setSetting(jdouble double_0);
            }; // end class TransSpeedAbsoluteType


    // class_index = 7 clss=class crcl.base.RotAccelAbsoluteType

            
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


    // class_index = 8 clss=class crcl.base.GuardType

            
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


    // class_index = 9 clss=class crcl.base.DisableRobotParameterStatusType

            
            class DisableRobotParameterStatusType : public MiddleCommandType {
            public:
                DisableRobotParameterStatusType(jobject _jthis, bool copy);
                DisableRobotParameterStatusType(const DisableRobotParameterStatusType &);
                static DisableRobotParameterStatusType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                DisableRobotParameterStatusType();
                ~DisableRobotParameterStatusType();
                jstring getRobotParameterName();
                void setRobotParameterName(jstring string_0);
                void setRobotParameterName(const char * easyArg_0);
            }; // end class DisableRobotParameterStatusType
        } // end namespace base
    } // end namespace crcl



}; // end namespace crclj

