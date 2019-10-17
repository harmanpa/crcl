// Never include this file (crclj2.h) directly. include crclj.h instead.


// start_segment_index = 20
// start_segment_index = 30
// segment_index = 2
// classesSegList=[class crcl.base.ThreeFingerGripperStatusType, class crcl.base.AngleUnitEnumType, class crcl.base.PointType, class crcl.base.DwellType, class crcl.base.SetMotionCoordinationType, class crcl.base.RotSpeedType, class crcl.base.RotSpeedAbsoluteType, class crcl.base.DisableRobotParameterStatusType, class crcl.base.CountSensorStatusType, class crcl.base.MoveScrewType]

namespace crclj {


// class_index = 0 clss=class crcl.base.ThreeFingerGripperStatusType

    namespace crcl{
        namespace base{
            
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


    // class_index = 1 clss=class crcl.base.AngleUnitEnumType

            
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


    // class_index = 2 clss=class crcl.base.PointType

            
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


    // class_index = 3 clss=class crcl.base.DwellType

            
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


    // class_index = 4 clss=class crcl.base.SetMotionCoordinationType

            
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


    // class_index = 5 clss=class crcl.base.RotSpeedType

            
            class RotSpeedType : public DataThingType {
            public:
                RotSpeedType(jobject _jthis, bool copy);
                RotSpeedType(const RotSpeedType &);
                static RotSpeedType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                RotSpeedType();
                ~RotSpeedType();
            }; // end class RotSpeedType


    // class_index = 6 clss=class crcl.base.RotSpeedAbsoluteType

            
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


    // class_index = 7 clss=class crcl.base.DisableRobotParameterStatusType

            
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


    // class_index = 8 clss=class crcl.base.CountSensorStatusType

            
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


    // class_index = 9 clss=class crcl.base.MoveScrewType

            
            class MoveScrewType : public MiddleCommandType {
            public:
                MoveScrewType(jobject _jthis, bool copy);
                MoveScrewType(const MoveScrewType &);
                static MoveScrewType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                MoveScrewType();
                ~MoveScrewType();
                ::crclj::java::lang::Double getAxialDistanceFree();
                PoseType getStartPosition();
                void setStartPosition(const PoseType & poseType_0);
                PointType getAxisPoint();
                void setAxisPoint(const PointType & pointType_0);
                void setAxialDistanceFree(const ::crclj::java::lang::Double & double_0);
                jdouble getAxialDistanceScrew();
                void setAxialDistanceScrew(jdouble double_0);
                jdouble getTurn();
                void setTurn(jdouble double_0);
            }; // end class MoveScrewType
        } // end namespace base
    } // end namespace crcl



}; // end namespace crclj

