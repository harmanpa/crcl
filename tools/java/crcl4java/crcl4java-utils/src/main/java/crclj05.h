// Never include this file (crclj05.h) directly. include crclj.h instead.


// start_segment_index = 50
// start_segment_index = 60
// segment_index = 5
// classesSegList=[class crcl.base.SetTransAccelType, class crcl.base.CRCLStatusType, class crcl.base.PoseToleranceType, class crcl.base.CloseToolChangerType, class crcl.base.EndCanonType, class crcl.base.ThreeFingerGripperStatusType, class crcl.base.ActuateJointsType, class crcl.base.SetRotSpeedType, class crcl.base.DisableGripperType, class crcl.base.MoveToType]

namespace crclj {


// class_index = 0 clss=class crcl.base.SetTransAccelType

    namespace crcl{
        namespace base{
            
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


    // class_index = 1 clss=class crcl.base.CRCLStatusType

            
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


    // class_index = 2 clss=class crcl.base.PoseToleranceType

            
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


    // class_index = 3 clss=class crcl.base.CloseToolChangerType

            
            class CloseToolChangerType : public MiddleCommandType {
            public:
                CloseToolChangerType(jobject _jthis, bool copy);
                CloseToolChangerType(const CloseToolChangerType &);
                static CloseToolChangerType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                CloseToolChangerType();
                ~CloseToolChangerType();
            }; // end class CloseToolChangerType


    // class_index = 4 clss=class crcl.base.EndCanonType

            
            class EndCanonType : public CRCLCommandType {
            public:
                EndCanonType(jobject _jthis, bool copy);
                EndCanonType(const EndCanonType &);
                static EndCanonType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                EndCanonType();
                ~EndCanonType();
            }; // end class EndCanonType


    // class_index = 5 clss=class crcl.base.ThreeFingerGripperStatusType

            
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


    // class_index = 6 clss=class crcl.base.ActuateJointsType

            
            class ActuateJointsType : public MiddleCommandType {
            public:
                ActuateJointsType(jobject _jthis, bool copy);
                ActuateJointsType(const ActuateJointsType &);
                static ActuateJointsType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                ActuateJointsType();
                ~ActuateJointsType();
                ::crclj::java::util::List getActuateJoint();
            }; // end class ActuateJointsType


    // class_index = 7 clss=class crcl.base.SetRotSpeedType

            
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


    // class_index = 8 clss=class crcl.base.DisableGripperType

            
            class DisableGripperType : public MiddleCommandType {
            public:
                DisableGripperType(jobject _jthis, bool copy);
                DisableGripperType(const DisableGripperType &);
                static DisableGripperType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                DisableGripperType();
                ~DisableGripperType();
                jstring getGripperName();
                void setGripperName(jstring string_0);
                void setGripperName(const char * easyArg_0);
            }; // end class DisableGripperType


    // class_index = 9 clss=class crcl.base.MoveToType

            
            class MoveToType : public MiddleCommandType {
            public:
                MoveToType(jobject _jthis, bool copy);
                MoveToType(const MoveToType &);
                static MoveToType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                MoveToType();
                ~MoveToType();
                PoseType getEndPosition();
                void setEndPosition(const PoseType & poseType_0);
                jboolean isMoveStraight();
                void setMoveStraight(jboolean boolean_0);
            }; // end class MoveToType
        } // end namespace base
    } // end namespace crcl



}; // end namespace crclj

