// Never include this file (crclj09.h) directly. include crclj.h instead.


// start_segment_index = 90
// start_segment_index = 100
// segment_index = 9
// classesSegList=[class crcl.base.PoseAndSetType, class crcl.base.StopMotionType, class crcl.base.DataThingType, class crcl.base.CRCLCommandType, class crcl.base.MiddleCommandType, class crcl.base.MessageType, class crcl.utils.CRCLCommandWrapper, interface java.util.List, class java.lang.Number, class java.lang.Double]

namespace crclj {


// class_index = 0 clss=class crcl.base.PoseAndSetType

    namespace crcl{
        namespace base{
            
            class PoseAndSetType : public PoseType {
            public:
                PoseAndSetType(jobject _jthis, bool copy);
                PoseAndSetType(const PoseAndSetType &);
                static PoseAndSetType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                PoseAndSetType();
                ~PoseAndSetType();
                jboolean isCoordinated();
                void setCoordinated(jboolean boolean_0);
                TransSpeedType getTransSpeed();
                void setTransSpeed(const TransSpeedType & transSpeedType_0);
                RotSpeedType getRotSpeed();
                void setRotSpeed(const RotSpeedType & rotSpeedType_0);
                TransAccelType getTransAccel();
                void setTransAccel(const TransAccelType & transAccelType_0);
                RotAccelType getRotAccel();
                void setRotAccel(const RotAccelType & rotAccelType_0);
                PoseToleranceType getTolerance();
                void setTolerance(const PoseToleranceType & poseToleranceType_0);
            }; // end class PoseAndSetType


    // class_index = 1 clss=class crcl.base.StopMotionType

            
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


    // class_index = 2 clss=class crcl.base.DataThingType

            
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


    // class_index = 3 clss=class crcl.base.CRCLCommandType

            
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


    // class_index = 4 clss=class crcl.base.MiddleCommandType

            
            class MiddleCommandType : public CRCLCommandType {
            public:
                MiddleCommandType(jobject _jthis, bool copy);
                MiddleCommandType(const MiddleCommandType &);
                static MiddleCommandType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                MiddleCommandType();
                ~MiddleCommandType();
            }; // end class MiddleCommandType


    // class_index = 5 clss=class crcl.base.MessageType

            
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
        } // end namespace base



    // class_index = 6 clss=class crcl.utils.CRCLCommandWrapper

        namespace utils{
            
            class CRCLCommandWrapper : public ::crclj::crcl::base::MessageType {
            public:
                CRCLCommandWrapper(jobject _jthis, bool copy);
                CRCLCommandWrapper(const CRCLCommandWrapper &);
                static CRCLCommandWrapper cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                CRCLCommandWrapper();
                ~CRCLCommandWrapper();
                jstring toString();
                jstring getName();
                void setName(jstring string_0);
                void setName(const char * easyArg_0);
                static CRCLCommandWrapper wrap(const ::crclj::crcl::base::MiddleCommandType & middleCommandType_0,const CRCLCommandWrapperCRCLCommandWrapperConsumer & cRCLCommandWrapperConsumer_1,const CRCLCommandWrapperCRCLCommandWrapperConsumer & cRCLCommandWrapperConsumer_2,const CRCLCommandWrapperCRCLCommandWrapperConsumer & cRCLCommandWrapperConsumer_3);
                jlong getCommandID();
                void setCommandID(jlong long_0);
                jint getCurProgramIndex();
                void setCurProgramIndex(jint int_0);
                ::crclj::crcl::base::CRCLProgramType getCurProgram();
                void setCurProgram(const ::crclj::crcl::base::CRCLProgramType & cRCLProgramType_0);
                static CRCLCommandWrapper wrapWithOnDone(const ::crclj::crcl::base::MiddleCommandType & middleCommandType_0,const CRCLCommandWrapperCRCLCommandWrapperConsumer & cRCLCommandWrapperConsumer_1);
                static CRCLCommandWrapper wrapWithOnStart(const ::crclj::crcl::base::MiddleCommandType & middleCommandType_0,const CRCLCommandWrapperCRCLCommandWrapperConsumer & cRCLCommandWrapperConsumer_1);
                static CRCLCommandWrapper wrapWithOnError(const ::crclj::crcl::base::MiddleCommandType & middleCommandType_0,const CRCLCommandWrapperCRCLCommandWrapperConsumer & cRCLCommandWrapperConsumer_1);
                ::crclj::crcl::base::MiddleCommandType getWrappedCommand();
                void setWrappedCommand(const ::crclj::crcl::base::MiddleCommandType & middleCommandType_0);
                void addOnStartListener(const CRCLCommandWrapperCRCLCommandWrapperConsumer & cRCLCommandWrapperConsumer_0);
                void removeOnStartListener(const CRCLCommandWrapperCRCLCommandWrapperConsumer & cRCLCommandWrapperConsumer_0);
                void notifyOnStartListeners();
                void addOnDoneListener(const CRCLCommandWrapperCRCLCommandWrapperConsumer & cRCLCommandWrapperConsumer_0);
                void removeOnDoneListener(const CRCLCommandWrapperCRCLCommandWrapperConsumer & cRCLCommandWrapperConsumer_0);
                void notifyOnDoneListeners();
                void addOnErrorListener(const CRCLCommandWrapperCRCLCommandWrapperConsumer & cRCLCommandWrapperConsumer_0);
                void removeOnErrorListener(const CRCLCommandWrapperCRCLCommandWrapperConsumer & cRCLCommandWrapperConsumer_0);
                void notifyOnErrorListeners();
            }; // end class CRCLCommandWrapper
        } // end namespace utils
    } // end namespace crcl



    // class_index = 7 clss=interface java.util.List

    namespace java{
        namespace util{
            
            class List : public ::crclj::java::lang::Object {
            public:
                List(jobject _jthis, bool copy);
                List(const List &);
                static List cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                protected:
                List() {};
                public:
                ~List();
                void add(jint int_0,const ::crclj::java::lang::Object & object_1);
                jboolean add(const ::crclj::java::lang::Object & object_0);
                jboolean remove(const ::crclj::java::lang::Object & object_0);
                ::crclj::java::lang::Object remove(jint int_0);
                ::crclj::java::lang::Object get(jint int_0);
                jboolean equals(const ::crclj::java::lang::Object & object_0);
                jint hashCode();
                jint indexOf(const ::crclj::java::lang::Object & object_0);
                void clear();
                jboolean isEmpty();
                jint lastIndexOf(const ::crclj::java::lang::Object & object_0);
                jboolean contains(const ::crclj::java::lang::Object & object_0);
                jint size();
                List subList(jint int_0,jint int_1);
                jobjectArray toArray();
                jobjectArray toArray(jobjectArray objectArray_0);
                static List of(const ::crclj::java::lang::Object & object_0,const ::crclj::java::lang::Object & object_1);
                static List of(const ::crclj::java::lang::Object & object_0);
                static List of();
                static List of(const ::crclj::java::lang::Object & object_0,const ::crclj::java::lang::Object & object_1,const ::crclj::java::lang::Object & object_2,const ::crclj::java::lang::Object & object_3,const ::crclj::java::lang::Object & object_4,const ::crclj::java::lang::Object & object_5);
                static List of(const ::crclj::java::lang::Object & object_0,const ::crclj::java::lang::Object & object_1,const ::crclj::java::lang::Object & object_2);
                static List of(const ::crclj::java::lang::Object & object_0,const ::crclj::java::lang::Object & object_1,const ::crclj::java::lang::Object & object_2,const ::crclj::java::lang::Object & object_3);
                static List of(const ::crclj::java::lang::Object & object_0,const ::crclj::java::lang::Object & object_1,const ::crclj::java::lang::Object & object_2,const ::crclj::java::lang::Object & object_3,const ::crclj::java::lang::Object & object_4);
                static List of(const ::crclj::java::lang::Object & object_0,const ::crclj::java::lang::Object & object_1,const ::crclj::java::lang::Object & object_2,const ::crclj::java::lang::Object & object_3,const ::crclj::java::lang::Object & object_4,const ::crclj::java::lang::Object & object_5,const ::crclj::java::lang::Object & object_6,const ::crclj::java::lang::Object & object_7,const ::crclj::java::lang::Object & object_8,const ::crclj::java::lang::Object & object_9);
                static List of(jobjectArray objectArray_0);
                static List of(const ::crclj::java::lang::Object & object_0,const ::crclj::java::lang::Object & object_1,const ::crclj::java::lang::Object & object_2,const ::crclj::java::lang::Object & object_3,const ::crclj::java::lang::Object & object_4,const ::crclj::java::lang::Object & object_5,const ::crclj::java::lang::Object & object_6,const ::crclj::java::lang::Object & object_7,const ::crclj::java::lang::Object & object_8);
                static List of(const ::crclj::java::lang::Object & object_0,const ::crclj::java::lang::Object & object_1,const ::crclj::java::lang::Object & object_2,const ::crclj::java::lang::Object & object_3,const ::crclj::java::lang::Object & object_4,const ::crclj::java::lang::Object & object_5,const ::crclj::java::lang::Object & object_6);
                static List of(const ::crclj::java::lang::Object & object_0,const ::crclj::java::lang::Object & object_1,const ::crclj::java::lang::Object & object_2,const ::crclj::java::lang::Object & object_3,const ::crclj::java::lang::Object & object_4,const ::crclj::java::lang::Object & object_5,const ::crclj::java::lang::Object & object_6,const ::crclj::java::lang::Object & object_7);
                ::crclj::java::lang::Object set(jint int_0,const ::crclj::java::lang::Object & object_1);
            }; // end class List
        } // end namespace util



    // class_index = 8 clss=class java.lang.Number

        namespace lang{
            
            class Number : public Object {
            public:
                Number(jobject _jthis, bool copy);
                Number(const Number &);
                static Number cast(const Object &);
                static bool instanceof(const Object &);
                    
                Number();
                ~Number();
                jbyte byteValue();
                jshort shortValue();
                jint intValue();
                jlong longValue();
                jfloat floatValue();
                jdouble doubleValue();
            }; // end class Number


    // class_index = 9 clss=class java.lang.Double

            
            class Double : public Number {
            public:
                Double(jobject _jthis, bool copy);
                Double(const Double &);
                static Double cast(const Object &);
                static bool instanceof(const Object &);
                    
                protected:
                Double();
                public:
                Double(jdouble double_0);
                Double(jstring string_0);
                Double(const char * easyArg_0);
                ~Double();
                static jdouble getPOSITIVE_INFINITY();
                static jdouble getNEGATIVE_INFINITY();
                static jdouble getNaN();
                static jdouble getMAX_VALUE();
                static jdouble getMIN_NORMAL();
                static jdouble getMIN_VALUE();
                static jint getMAX_EXPONENT();
                static jint getMIN_EXPONENT();
                static jint getSIZE();
                static jint getBYTES();
                jboolean equals(const Object & object_0);
                static jstring toString(jdouble double_0);
                jstring toString();
                jint hashCode();
                static jint hashCode(jdouble double_0);
                static jdouble min(jdouble double_0,jdouble double_1);
                static jdouble max(jdouble double_0,jdouble double_1);
                static jlong doubleToRawLongBits(jdouble double_0);
                static jlong doubleToLongBits(jdouble double_0);
                static jdouble longBitsToDouble(jlong long_0);
                jint compareTo(const Double & double_0);
                jbyte byteValue();
                jshort shortValue();
                jint intValue();
                jlong longValue();
                jfloat floatValue();
                jdouble doubleValue();
                static Double valueOf(jdouble double_0);
                static Double valueOf(jstring string_0);
                static jstring toHexString(jdouble double_0);
                static jint compare(jdouble double_0,jdouble double_1);
                static jboolean isNaN(jdouble double_0);
                jboolean isNaN();
                static jboolean isInfinite(jdouble double_0);
                jboolean isInfinite();
                static jboolean isFinite(jdouble double_0);
                static jdouble sum(jdouble double_0,jdouble double_1);
                static jdouble parseDouble(jstring string_0);
            }; // end class Double
        } // end namespace lang
    } // end namespace java



}; // end namespace crclj

