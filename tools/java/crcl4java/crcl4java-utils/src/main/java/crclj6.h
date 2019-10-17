// Never include this file (crclj6.h) directly. include crclj.h instead.


// start_segment_index = 60
// start_segment_index = 70
// segment_index = 6
// classesSegList=[class crcl.base.SetLengthUnitsType, class crcl.base.SensorStatusesType, class crcl.base.LengthUnitEnumType, class crcl.base.SettingsStatusType, class crcl.base.CRCLProgramType, class crcl.base.SetRotSpeedType, class crcl.base.StopConditionEnumType, class crcl.base.TwistType, class crcl.base.SetTransSpeedType, class crcl.base.EnableSensorType]

namespace crclj {


// class_index = 0 clss=class crcl.base.SetLengthUnitsType

    namespace crcl{
        namespace base{
            
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


    // class_index = 1 clss=class crcl.base.SensorStatusesType

            
            class SensorStatusesType : public DataThingType {
            public:
                SensorStatusesType(jobject _jthis, bool copy);
                SensorStatusesType(const SensorStatusesType &);
                static SensorStatusesType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                SensorStatusesType();
                ~SensorStatusesType();
                ::crclj::java::util::List getOnOffSensorStatus();
                ::crclj::java::util::List getScalarSensorStatus();
                ::crclj::java::util::List getCountSensorStatus();
                ::crclj::java::util::List getForceTorqueSensorStatus();
            }; // end class SensorStatusesType


    // class_index = 2 clss=class crcl.base.LengthUnitEnumType

            
            class LengthUnitEnumType : public ::crclj::java::lang::Enum {
            public:
                LengthUnitEnumType(jobject _jthis, bool copy);
                LengthUnitEnumType(const LengthUnitEnumType &);
                static LengthUnitEnumType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                protected:
                LengthUnitEnumType();
                public:
                ~LengthUnitEnumType();
                static LengthUnitEnumType getMETER();
                static LengthUnitEnumType getMILLIMETER();
                static LengthUnitEnumType getINCH();
                jstring value();
                static jobjectArray values();
                static LengthUnitEnumType valueOf(jstring string_0);
                static LengthUnitEnumType fromValue(jstring string_0);
            }; // end class LengthUnitEnumType


    // class_index = 3 clss=class crcl.base.SettingsStatusType

            
            class SettingsStatusType : public DataThingType {
            public:
                SettingsStatusType(jobject _jthis, bool copy);
                SettingsStatusType(const SettingsStatusType &);
                static SettingsStatusType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                SettingsStatusType();
                ~SettingsStatusType();
                AngleUnitEnumType getAngleUnitName();
                void setAngleUnitName(const AngleUnitEnumType & angleUnitEnumType_0);
                ::crclj::java::util::List getEndEffectorParameterSetting();
                ::crclj::java::lang::Double getEndEffectorSetting();
                void setEndEffectorSetting(const ::crclj::java::lang::Double & double_0);
                ForceUnitEnumType getForceUnitName();
                void setForceUnitName(const ForceUnitEnumType & forceUnitEnumType_0);
                ::crclj::java::util::List getJointLimits();
                PoseToleranceType getIntermediatePoseTolerance();
                void setIntermediatePoseTolerance(const PoseToleranceType & poseToleranceType_0);
                LengthUnitEnumType getLengthUnitName();
                void setLengthUnitName(const LengthUnitEnumType & lengthUnitEnumType_0);
                PointType getMaxCartesianLimit();
                void setMaxCartesianLimit(const PointType & pointType_0);
                PointType getMinCartesianLimit();
                void setMinCartesianLimit(const PointType & pointType_0);
                ::crclj::java::lang::Boolean isMotionCoordinated();
                void setMotionCoordinated(const ::crclj::java::lang::Boolean & boolean_0);
                PoseToleranceType getPoseTolerance();
                void setPoseTolerance(const PoseToleranceType & poseToleranceType_0);
                ::crclj::java::util::List getRobotParameterSetting();
                RotAccelAbsoluteType getRotAccelAbsolute();
                void setRotAccelAbsolute(const RotAccelAbsoluteType & rotAccelAbsoluteType_0);
                RotAccelRelativeType getRotAccelRelative();
                void setRotAccelRelative(const RotAccelRelativeType & rotAccelRelativeType_0);
                RotSpeedAbsoluteType getRotSpeedAbsolute();
                void setRotSpeedAbsolute(const RotSpeedAbsoluteType & rotSpeedAbsoluteType_0);
                RotSpeedRelativeType getRotSpeedRelative();
                void setRotSpeedRelative(const RotSpeedRelativeType & rotSpeedRelativeType_0);
                TorqueUnitEnumType getTorqueUnitName();
                void setTorqueUnitName(const TorqueUnitEnumType & torqueUnitEnumType_0);
                TransAccelAbsoluteType getTransAccelAbsolute();
                void setTransAccelAbsolute(const TransAccelAbsoluteType & transAccelAbsoluteType_0);
                TransAccelRelativeType getTransAccelRelative();
                void setTransAccelRelative(const TransAccelRelativeType & transAccelRelativeType_0);
                TransSpeedAbsoluteType getTransSpeedAbsolute();
                void setTransSpeedAbsolute(const TransSpeedAbsoluteType & transSpeedAbsoluteType_0);
                TransSpeedRelativeType getTransSpeedRelative();
                void setTransSpeedRelative(const TransSpeedRelativeType & transSpeedRelativeType_0);
            }; // end class SettingsStatusType


    // class_index = 4 clss=class crcl.base.CRCLProgramType

            
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


    // class_index = 5 clss=class crcl.base.SetRotSpeedType

            
            class SetRotSpeedType : public MiddleCommandType {
            public:
                SetRotSpeedType(jobject _jthis, bool copy);
                SetRotSpeedType(const SetRotSpeedType &);
                static SetRotSpeedType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                SetRotSpeedType();
                ~SetRotSpeedType();
                RotSpeedType getRotSpeed();
                void setRotSpeed(const RotSpeedType & rotSpeedType_0);
            }; // end class SetRotSpeedType


    // class_index = 6 clss=class crcl.base.StopConditionEnumType

            
            class StopConditionEnumType : public ::crclj::java::lang::Enum {
            public:
                StopConditionEnumType(jobject _jthis, bool copy);
                StopConditionEnumType(const StopConditionEnumType &);
                static StopConditionEnumType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                protected:
                StopConditionEnumType();
                public:
                ~StopConditionEnumType();
                static StopConditionEnumType getIMMEDIATE();
                static StopConditionEnumType getFAST();
                static StopConditionEnumType getNORMAL();
                jstring value();
                static jobjectArray values();
                static StopConditionEnumType valueOf(jstring string_0);
                static StopConditionEnumType fromValue(jstring string_0);
            }; // end class StopConditionEnumType


    // class_index = 7 clss=class crcl.base.TwistType

            
            class TwistType : public DataThingType {
            public:
                TwistType(jobject _jthis, bool copy);
                TwistType(const TwistType &);
                static TwistType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                TwistType();
                ~TwistType();
                VectorType getLinearVelocity();
                void setLinearVelocity(const VectorType & vectorType_0);
                VectorType getAngularVelocity();
                void setAngularVelocity(const VectorType & vectorType_0);
            }; // end class TwistType


    // class_index = 8 clss=class crcl.base.SetTransSpeedType

            
            class SetTransSpeedType : public MiddleCommandType {
            public:
                SetTransSpeedType(jobject _jthis, bool copy);
                SetTransSpeedType(const SetTransSpeedType &);
                static SetTransSpeedType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                SetTransSpeedType();
                ~SetTransSpeedType();
                TransSpeedType getTransSpeed();
                void setTransSpeed(const TransSpeedType & transSpeedType_0);
            }; // end class SetTransSpeedType


    // class_index = 9 clss=class crcl.base.EnableSensorType

            
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
        } // end namespace base
    } // end namespace crcl



}; // end namespace crclj

