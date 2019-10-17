// Never include this file (crclj10.h) directly. include crclj.h instead.


// start_segment_index = 100
// start_segment_index = 105
// segment_index = 10
// classesSegList=[class java.lang.Integer, class java.lang.Boolean, class java.lang.Long, interface crcl.utils.CRCLCommandWrapper$CRCLCommandWrapperConsumer, class crcl.base.CRCLProgramType]

namespace crclj {


// class_index = 0 clss=class java.lang.Integer

    namespace java{
        namespace lang{
            
            class Integer : public Number {
            public:
                Integer(jobject _jthis, bool copy);
                Integer(const Integer &);
                static Integer cast(const Object &);
                static bool instanceof(const Object &);
                    
                protected:
                Integer();
                public:
                Integer(jstring string_0);
                Integer(const char * easyArg_0);
                Integer(jint int_0);
                ~Integer();
                static jint getMIN_VALUE();
                static jint getMAX_VALUE();
                static jint getSIZE();
                static jint getBYTES();
                static jint numberOfLeadingZeros(jint int_0);
                static jint numberOfTrailingZeros(jint int_0);
                static jint bitCount(jint int_0);
                jboolean equals(const Object & object_0);
                static jstring toString(jint int_0);
                static jstring toString(jint int_0,jint int_1);
                jstring toString();
                static jint hashCode(jint int_0);
                jint hashCode();
                static jint min(jint int_0,jint int_1);
                static jint max(jint int_0,jint int_1);
                static jint reverseBytes(jint int_0);
                jint compareTo(const Integer & integer_0);
                jbyte byteValue();
                jshort shortValue();
                jint intValue();
                jlong longValue();
                jfloat floatValue();
                jdouble doubleValue();
                static Integer valueOf(jint int_0);
                static Integer valueOf(jstring string_0,jint int_1);
                static Integer valueOf(jstring string_0);
                static jstring toHexString(jint int_0);
                static Integer decode(jstring string_0);
                static jint compare(jint int_0,jint int_1);
                static jint reverse(jint int_0);
                static jlong toUnsignedLong(jint int_0);
                static jint parseInt(jstring string_0);
                static jint parseInt(jstring string_0,jint int_1);
                static jint sum(jint int_0,jint int_1);
                static jint compareUnsigned(jint int_0,jint int_1);
                static jstring toUnsignedString(jint int_0,jint int_1);
                static jstring toUnsignedString(jint int_0);
                static jstring toOctalString(jint int_0);
                static jstring toBinaryString(jint int_0);
                static jint parseUnsignedInt(jstring string_0,jint int_1);
                static jint parseUnsignedInt(jstring string_0);
                static Integer getInteger(jstring string_0,jint int_1);
                static Integer getInteger(jstring string_0);
                static Integer getInteger(jstring string_0,const Integer & integer_1);
                static jint divideUnsigned(jint int_0,jint int_1);
                static jint remainderUnsigned(jint int_0,jint int_1);
                static jint highestOneBit(jint int_0);
                static jint lowestOneBit(jint int_0);
                static jint rotateLeft(jint int_0,jint int_1);
                static jint rotateRight(jint int_0,jint int_1);
                static jint signum(jint int_0);
            }; // end class Integer


    // class_index = 1 clss=class java.lang.Boolean

            
            class Boolean : public Object {
            public:
                Boolean(jobject _jthis, bool copy);
                Boolean(const Boolean &);
                static Boolean cast(const Object &);
                static bool instanceof(const Object &);
                    
                protected:
                Boolean();
                public:
                Boolean(jboolean boolean_0);
                Boolean(jstring string_0);
                Boolean(const char * easyArg_0);
                ~Boolean();
                static Boolean getTRUE();
                static Boolean getFALSE();
                jboolean equals(const Object & object_0);
                jstring toString();
                static jstring toString(jboolean boolean_0);
                static jint hashCode(jboolean boolean_0);
                jint hashCode();
                jint compareTo(const Boolean & boolean_0);
                static jboolean getBoolean(jstring string_0);
                jboolean booleanValue();
                static Boolean valueOf(jstring string_0);
                static Boolean valueOf(jboolean boolean_0);
                static jint compare(jboolean boolean_0,jboolean boolean_1);
                static jboolean parseBoolean(jstring string_0);
                static jboolean logicalAnd(jboolean boolean_0,jboolean boolean_1);
                static jboolean logicalOr(jboolean boolean_0,jboolean boolean_1);
                static jboolean logicalXor(jboolean boolean_0,jboolean boolean_1);
            }; // end class Boolean


    // class_index = 2 clss=class java.lang.Long

            
            class Long : public Number {
            public:
                Long(jobject _jthis, bool copy);
                Long(const Long &);
                static Long cast(const Object &);
                static bool instanceof(const Object &);
                    
                protected:
                Long();
                public:
                Long(jstring string_0);
                Long(const char * easyArg_0);
                Long(jlong long_0);
                ~Long();
                static jlong getMIN_VALUE();
                static jlong getMAX_VALUE();
                static jint getSIZE();
                static jint getBYTES();
                static jint numberOfLeadingZeros(jlong long_0);
                static jint numberOfTrailingZeros(jlong long_0);
                static jint bitCount(jlong long_0);
                jboolean equals(const Object & object_0);
                static jstring toString(jlong long_0);
                static jstring toString(jlong long_0,jint int_1);
                jstring toString();
                static jint hashCode(jlong long_0);
                jint hashCode();
                static jlong min(jlong long_0,jlong long_1);
                static jlong max(jlong long_0,jlong long_1);
                static jlong reverseBytes(jlong long_0);
                jint compareTo(const Long & long_0);
                static Long getLong(jstring string_0);
                static Long getLong(jstring string_0,jlong long_1);
                static Long getLong(jstring string_0,const Long & long_1);
                jbyte byteValue();
                jshort shortValue();
                jint intValue();
                jlong longValue();
                jfloat floatValue();
                jdouble doubleValue();
                static Long valueOf(jstring string_0,jint int_1);
                static Long valueOf(jstring string_0);
                static Long valueOf(jlong long_0);
                static jstring toHexString(jlong long_0);
                static Long decode(jstring string_0);
                static jint compare(jlong long_0,jlong long_1);
                static jlong reverse(jlong long_0);
                static jlong sum(jlong long_0,jlong long_1);
                static jint compareUnsigned(jlong long_0,jlong long_1);
                static jstring toUnsignedString(jlong long_0);
                static jstring toUnsignedString(jlong long_0,jint int_1);
                static jstring toOctalString(jlong long_0);
                static jstring toBinaryString(jlong long_0);
                static jlong divideUnsigned(jlong long_0,jlong long_1);
                static jlong remainderUnsigned(jlong long_0,jlong long_1);
                static jlong highestOneBit(jlong long_0);
                static jlong lowestOneBit(jlong long_0);
                static jlong rotateLeft(jlong long_0,jint int_1);
                static jlong rotateRight(jlong long_0,jint int_1);
                static jint signum(jlong long_0);
                static jlong parseLong(jstring string_0);
                static jlong parseLong(jstring string_0,jint int_1);
                static jlong parseUnsignedLong(jstring string_0,jint int_1);
                static jlong parseUnsignedLong(jstring string_0);
            }; // end class Long
        } // end namespace lang
    } // end namespace java



    // class_index = 3 clss=interface crcl.utils.CRCLCommandWrapper$CRCLCommandWrapperConsumer

    namespace crcl{
        namespace utils{
            
            class CRCLCommandWrapperCRCLCommandWrapperConsumer : public ::crclj::java::lang::Object {
            public:
                CRCLCommandWrapperCRCLCommandWrapperConsumer(jobject _jthis, bool copy);
                CRCLCommandWrapperCRCLCommandWrapperConsumer(const CRCLCommandWrapperCRCLCommandWrapperConsumer &);
                static CRCLCommandWrapperCRCLCommandWrapperConsumer cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                protected:
                CRCLCommandWrapperCRCLCommandWrapperConsumer() {};
                public:
                ~CRCLCommandWrapperCRCLCommandWrapperConsumer();
                void accept(const CRCLCommandWrapper & cRCLCommandWrapper_0);
            }; // end class CRCLCommandWrapperCRCLCommandWrapperConsumer
        } // end namespace utils



    // class_index = 4 clss=class crcl.base.CRCLProgramType

        namespace base{
            
            class CRCLProgramType : public DataThingType {
            public:
                CRCLProgramType(jobject _jthis, bool copy);
                CRCLProgramType(const CRCLProgramType &);
                static CRCLProgramType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                CRCLProgramType();
                ~CRCLProgramType();
                ::crclj::java::util::List getMiddleCommand();
            }; // end class CRCLProgramType
        } // end namespace base
    } // end namespace crcl



}; // end namespace crclj

