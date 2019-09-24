// Never include this file (crclj2.h) directly. include crclj.h instead.


// start_segment_index = 20
// start_segment_index = 30
// segment_index = 2
// classesSegList=[class crcl.base.GuardsStatusesType, class crcl.base.CloseToolChangerType, class crcl.base.RotSpeedType, class crcl.base.RotSpeedRelativeType, class crcl.base.TransSpeedType, class crcl.base.TransSpeedRelativeType, class crcl.base.SetIntermediatePoseToleranceType, class crcl.base.ActuateJointsType, class crcl.base.CRCLProgramType, class crcl.base.RotAccelType]

namespace crclj {


// class_index = 0 clss=class crcl.base.GuardsStatusesType

    namespace crcl{
        namespace base{
            
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


    // class_index = 1 clss=class crcl.base.CloseToolChangerType

            
            class CloseToolChangerType : public MiddleCommandType {
            public:
                CloseToolChangerType(jobject _jthis, bool copy);
                CloseToolChangerType(const CloseToolChangerType &);
                static CloseToolChangerType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                CloseToolChangerType();
                ~CloseToolChangerType();
            }; // end class CloseToolChangerType


    // class_index = 2 clss=class crcl.base.RotSpeedType

            
            class RotSpeedType : public DataThingType {
            public:
                RotSpeedType(jobject _jthis, bool copy);
                RotSpeedType(const RotSpeedType &);
                static RotSpeedType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                RotSpeedType();
                ~RotSpeedType();
            }; // end class RotSpeedType


    // class_index = 3 clss=class crcl.base.RotSpeedRelativeType

            
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


    // class_index = 4 clss=class crcl.base.TransSpeedType

            
            class TransSpeedType : public DataThingType {
            public:
                TransSpeedType(jobject _jthis, bool copy);
                TransSpeedType(const TransSpeedType &);
                static TransSpeedType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                TransSpeedType();
                ~TransSpeedType();
            }; // end class TransSpeedType


    // class_index = 5 clss=class crcl.base.TransSpeedRelativeType

            
            class TransSpeedRelativeType : public TransSpeedType {
            public:
                TransSpeedRelativeType(jobject _jthis, bool copy);
                TransSpeedRelativeType(const TransSpeedRelativeType &);
                static TransSpeedRelativeType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                TransSpeedRelativeType();
                ~TransSpeedRelativeType();
                jdouble getFraction();
                void setFraction(jdouble double_0);
            }; // end class TransSpeedRelativeType


    // class_index = 6 clss=class crcl.base.SetIntermediatePoseToleranceType

            
            class SetIntermediatePoseToleranceType : public MiddleCommandType {
            public:
                SetIntermediatePoseToleranceType(jobject _jthis, bool copy);
                SetIntermediatePoseToleranceType(const SetIntermediatePoseToleranceType &);
                static SetIntermediatePoseToleranceType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                SetIntermediatePoseToleranceType();
                ~SetIntermediatePoseToleranceType();
                PoseToleranceType getTolerance();
                void setTolerance(const PoseToleranceType & poseToleranceType_0);
            }; // end class SetIntermediatePoseToleranceType


    // class_index = 7 clss=class crcl.base.ActuateJointsType

            
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


    // class_index = 8 clss=class crcl.base.CRCLProgramType

            
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

