// Never include this file (crclj3.h) directly. include crclj.h instead.


// start_segment_index = 30
// start_segment_index = 40
// segment_index = 3
// classesSegList=[class crcl.base.DisableRobotParameterStatusType, class crcl.base.CountSensorStatusType, class crcl.base.OpenToolChangerType, class crcl.base.InitCanonType, class crcl.base.AngleUnitEnumType, class crcl.base.PointType, class crcl.base.DwellType, class crcl.base.VectorType, class crcl.base.JointStatusesType, class crcl.base.StopMotionType]

namespace crclj {


// class_index = 0 clss=class crcl.base.DisableRobotParameterStatusType

    namespace crcl{
        namespace base{
            
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


    // class_index = 1 clss=class crcl.base.CountSensorStatusType

            
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


    // class_index = 2 clss=class crcl.base.OpenToolChangerType

            
            class OpenToolChangerType : public MiddleCommandType {
            public:
                OpenToolChangerType(jobject _jthis, bool copy);
                OpenToolChangerType(const OpenToolChangerType &);
                static OpenToolChangerType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                OpenToolChangerType();
                ~OpenToolChangerType();
            }; // end class OpenToolChangerType


    // class_index = 3 clss=class crcl.base.InitCanonType

            
            class InitCanonType : public CRCLCommandType {
            public:
                InitCanonType(jobject _jthis, bool copy);
                InitCanonType(const InitCanonType &);
                static InitCanonType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                InitCanonType();
                ~InitCanonType();
            }; // end class InitCanonType


    // class_index = 4 clss=class crcl.base.AngleUnitEnumType

            
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


    // class_index = 5 clss=class crcl.base.PointType

            
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


    // class_index = 6 clss=class crcl.base.DwellType

            
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


    // class_index = 7 clss=class crcl.base.VectorType

            
            class VectorType : public DataThingType {
            public:
                VectorType(jobject _jthis, bool copy);
                VectorType(const VectorType &);
                static VectorType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                VectorType();
                ~VectorType();
                jdouble getI();
                void setI(jdouble double_0);
                jdouble getJ();
                void setJ(jdouble double_0);
                jdouble getK();
                void setK(jdouble double_0);
            }; // end class VectorType


    // class_index = 8 clss=class crcl.base.JointStatusesType

            
            class JointStatusesType : public DataThingType {
            public:
                JointStatusesType(jobject _jthis, bool copy);
                JointStatusesType(const JointStatusesType &);
                static JointStatusesType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                JointStatusesType();
                ~JointStatusesType();
                ::crclj::java::util::List getJointStatus();
            }; // end class JointStatusesType


    // class_index = 9 clss=class crcl.base.StopMotionType

            
            class StopMotionType : public MiddleCommandType {
            public:
                StopMotionType(jobject _jthis, bool copy);
                StopMotionType(const StopMotionType &);
                static StopMotionType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                StopMotionType();
                ~StopMotionType();
                StopConditionEnumType getStopCondition();
                void setStopCondition(const StopConditionEnumType & stopConditionEnumType_0);
            }; // end class StopMotionType
        } // end namespace base
    } // end namespace crcl



}; // end namespace crclj

