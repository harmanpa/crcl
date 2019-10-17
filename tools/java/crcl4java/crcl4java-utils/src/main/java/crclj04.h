// Never include this file (crclj04.h) directly. include crclj.h instead.


// start_segment_index = 40
// start_segment_index = 50
// segment_index = 4
// classesSegList=[class crcl.base.ParallelGripperStatusType, class crcl.base.JointSpeedAccelType, class crcl.base.JointForceTorqueType, class crcl.base.SettingsStatusType, class crcl.base.PointType, class crcl.base.DwellType, class crcl.base.RotAccelType, class crcl.base.RotAccelRelativeType, class crcl.base.DisableSensorType, class crcl.base.TwistType]

namespace crclj {


// class_index = 0 clss=class crcl.base.ParallelGripperStatusType

    namespace crcl{
        namespace base{
            
            class ParallelGripperStatusType : public GripperStatusType {
            public:
                ParallelGripperStatusType(jobject _jthis, bool copy);
                ParallelGripperStatusType(const ParallelGripperStatusType &);
                static ParallelGripperStatusType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                ParallelGripperStatusType();
                ~ParallelGripperStatusType();
                jdouble getSeparation();
                void setSeparation(jdouble double_0);
            }; // end class ParallelGripperStatusType


    // class_index = 1 clss=class crcl.base.JointSpeedAccelType

            
            class JointSpeedAccelType : public JointDetailsType {
            public:
                JointSpeedAccelType(jobject _jthis, bool copy);
                JointSpeedAccelType(const JointSpeedAccelType &);
                static JointSpeedAccelType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                JointSpeedAccelType();
                ~JointSpeedAccelType();
                ::crclj::java::lang::Double getJointSpeed();
                void setJointSpeed(const ::crclj::java::lang::Double & double_0);
                ::crclj::java::lang::Double getJointAccel();
                void setJointAccel(const ::crclj::java::lang::Double & double_0);
            }; // end class JointSpeedAccelType


    // class_index = 2 clss=class crcl.base.JointForceTorqueType

            
            class JointForceTorqueType : public JointDetailsType {
            public:
                JointForceTorqueType(jobject _jthis, bool copy);
                JointForceTorqueType(const JointForceTorqueType &);
                static JointForceTorqueType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                JointForceTorqueType();
                ~JointForceTorqueType();
                ::crclj::java::lang::Double getSetting();
                void setSetting(const ::crclj::java::lang::Double & double_0);
                ::crclj::java::lang::Double getChangeRate();
                void setChangeRate(const ::crclj::java::lang::Double & double_0);
            }; // end class JointForceTorqueType


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


    // class_index = 4 clss=class crcl.base.PointType

            
            class PointType : public DataThingType {
            public:
                PointType(jobject _jthis, bool copy);
                PointType(const PointType &);
                static PointType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                PointType();
                ~PointType();
                jdouble getX();
                void setX(jdouble double_0);
                jdouble getY();
                void setY(jdouble double_0);
                jdouble getZ();
                void setZ(jdouble double_0);
            }; // end class PointType


    // class_index = 5 clss=class crcl.base.DwellType

            
            class DwellType : public MiddleCommandType {
            public:
                DwellType(jobject _jthis, bool copy);
                DwellType(const DwellType &);
                static DwellType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                DwellType();
                ~DwellType();
                jdouble getDwellTime();
                void setDwellTime(jdouble double_0);
            }; // end class DwellType


    // class_index = 6 clss=class crcl.base.RotAccelType

            
            class RotAccelType : public DataThingType {
            public:
                RotAccelType(jobject _jthis, bool copy);
                RotAccelType(const RotAccelType &);
                static RotAccelType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                RotAccelType();
                ~RotAccelType();
            }; // end class RotAccelType


    // class_index = 7 clss=class crcl.base.RotAccelRelativeType

            
            class RotAccelRelativeType : public RotAccelType {
            public:
                RotAccelRelativeType(jobject _jthis, bool copy);
                RotAccelRelativeType(const RotAccelRelativeType &);
                static RotAccelRelativeType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                RotAccelRelativeType();
                ~RotAccelRelativeType();
                jdouble getFraction();
                void setFraction(jdouble double_0);
            }; // end class RotAccelRelativeType


    // class_index = 8 clss=class crcl.base.DisableSensorType

            
            class DisableSensorType : public MiddleCommandType {
            public:
                DisableSensorType(jobject _jthis, bool copy);
                DisableSensorType(const DisableSensorType &);
                static DisableSensorType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                DisableSensorType();
                ~DisableSensorType();
                jstring getSensorID();
                void setSensorID(jstring string_0);
                void setSensorID(const char * easyArg_0);
            }; // end class DisableSensorType


    // class_index = 9 clss=class crcl.base.TwistType

            
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
        } // end namespace base
    } // end namespace crcl



}; // end namespace crclj

