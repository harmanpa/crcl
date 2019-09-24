// Never include this file (crclj4.h) directly. include crclj.h instead.


// start_segment_index = 40
// start_segment_index = 50
// segment_index = 4
// classesSegList=[class crcl.base.JointLimitType, class crcl.base.SetLengthUnitsType, class crcl.base.EndCanonType, class crcl.base.ThreeFingerGripperStatusType, class crcl.base.SettingsStatusType, class crcl.base.SetRotSpeedType, class crcl.base.StopConditionEnumType, class crcl.base.RotSpeedAbsoluteType, class crcl.base.SetTransAccelType, class crcl.base.CRCLStatusType]

namespace crclj {


// class_index = 0 clss=class crcl.base.JointLimitType

    namespace crcl{
        namespace base{
            
            class JointLimitType : public DataThingType {
            public:
                JointLimitType(jobject _jthis, bool copy);
                JointLimitType(const JointLimitType &);
                static JointLimitType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                JointLimitType();
                ~JointLimitType();
                jint getJointNumber();
                void setJointNumber(jint int_0);
                ::crclj::java::lang::Double getJointMinPosition();
                void setJointMinPosition(const ::crclj::java::lang::Double & double_0);
                ::crclj::java::lang::Double getJointMaxPosition();
                void setJointMaxPosition(const ::crclj::java::lang::Double & double_0);
                ::crclj::java::lang::Double getJointMaxTorqueOrForce();
                void setJointMaxTorqueOrForce(const ::crclj::java::lang::Double & double_0);
                ::crclj::java::lang::Double getJointMaxVelocity();
                void setJointMaxVelocity(const ::crclj::java::lang::Double & double_0);
            }; // end class JointLimitType


    // class_index = 1 clss=class crcl.base.SetLengthUnitsType

            
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


    // class_index = 2 clss=class crcl.base.EndCanonType

            
            class EndCanonType : public CRCLCommandType {
            public:
                EndCanonType(jobject _jthis, bool copy);
                EndCanonType(const EndCanonType &);
                static EndCanonType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                EndCanonType();
                ~EndCanonType();
            }; // end class EndCanonType


    // class_index = 3 clss=class crcl.base.ThreeFingerGripperStatusType

            
            class ThreeFingerGripperStatusType : public GripperStatusType {
            public:
                ThreeFingerGripperStatusType(jobject _jthis, bool copy);
                ThreeFingerGripperStatusType(const ThreeFingerGripperStatusType &);
                static ThreeFingerGripperStatusType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                ThreeFingerGripperStatusType();
                ~ThreeFingerGripperStatusType();
                ::crclj::java::lang::Double getFinger1Position();
                void setFinger1Position(const ::crclj::java::lang::Double & double_0);
                ::crclj::java::lang::Double getFinger2Position();
                void setFinger2Position(const ::crclj::java::lang::Double & double_0);
                ::crclj::java::lang::Double getFinger3Position();
                void setFinger3Position(const ::crclj::java::lang::Double & double_0);
                ::crclj::java::lang::Double getFinger1Force();
                void setFinger1Force(const ::crclj::java::lang::Double & double_0);
                ::crclj::java::lang::Double getFinger2Force();
                void setFinger2Force(const ::crclj::java::lang::Double & double_0);
                ::crclj::java::lang::Double getFinger3Force();
                void setFinger3Force(const ::crclj::java::lang::Double & double_0);
            }; // end class ThreeFingerGripperStatusType


    // class_index = 4 clss=class crcl.base.SettingsStatusType

            
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


    // class_index = 7 clss=class crcl.base.RotSpeedAbsoluteType

            
            class RotSpeedAbsoluteType : public RotSpeedType {
            public:
                RotSpeedAbsoluteType(jobject _jthis, bool copy);
                RotSpeedAbsoluteType(const RotSpeedAbsoluteType &);
                static RotSpeedAbsoluteType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                RotSpeedAbsoluteType();
                ~RotSpeedAbsoluteType();
                jdouble getSetting();
                void setSetting(jdouble double_0);
            }; // end class RotSpeedAbsoluteType


    // class_index = 8 clss=class crcl.base.SetTransAccelType

            
            class SetTransAccelType : public MiddleCommandType {
            public:
                SetTransAccelType(jobject _jthis, bool copy);
                SetTransAccelType(const SetTransAccelType &);
                static SetTransAccelType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                SetTransAccelType();
                ~SetTransAccelType();
                TransAccelType getTransAccel();
                void setTransAccel(const TransAccelType & transAccelType_0);
            }; // end class SetTransAccelType


    // class_index = 9 clss=class crcl.base.CRCLStatusType

            
            class CRCLStatusType : public DataThingType {
            public:
                CRCLStatusType(jobject _jthis, bool copy);
                CRCLStatusType(const CRCLStatusType &);
                static CRCLStatusType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                CRCLStatusType();
                ~CRCLStatusType();
                CommandStatusType getCommandStatus();
                void setCommandStatus(const CommandStatusType & commandStatusType_0);
                JointStatusesType getJointStatuses();
                void setJointStatuses(const JointStatusesType & jointStatusesType_0);
                PoseStatusType getPoseStatus();
                void setPoseStatus(const PoseStatusType & poseStatusType_0);
                GripperStatusType getGripperStatus();
                void setGripperStatus(const GripperStatusType & gripperStatusType_0);
                SettingsStatusType getSettingsStatus();
                void setSettingsStatus(const SettingsStatusType & settingsStatusType_0);
                SensorStatusesType getSensorStatuses();
                void setSensorStatuses(const SensorStatusesType & sensorStatusesType_0);
                GuardsStatusesType getGuardsStatuses();
                void setGuardsStatuses(const GuardsStatusesType & guardsStatusesType_0);
            }; // end class CRCLStatusType
        } // end namespace base
    } // end namespace crcl



}; // end namespace crclj

