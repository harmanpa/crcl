// Never include this file (crclj9.h) directly. include crclj.h instead.


// start_segment_index = 90
// start_segment_index = 98
// segment_index = 9
// classesSegList=[class crcl.base.SetTorqueUnitsType, class crcl.base.SetEndEffectorParametersType, class java.lang.Number, class java.lang.Double, class java.lang.Long, interface java.util.List, class java.lang.Boolean, class java.lang.Integer]

namespace crclj {


// class_index = 0 clss=class crcl.base.SetTorqueUnitsType

    namespace crcl{
        namespace base{
            
            class SetTorqueUnitsType : public MiddleCommandType {
            public:
                SetTorqueUnitsType(jobject _jthis, bool copy);
                SetTorqueUnitsType(const SetTorqueUnitsType &);
                static SetTorqueUnitsType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                SetTorqueUnitsType();
                ~SetTorqueUnitsType();
                TorqueUnitEnumType getUnitName();
                void setUnitName(const TorqueUnitEnumType & torqueUnitEnumType_0);
            }; // end class SetTorqueUnitsType


    // class_index = 1 clss=class crcl.base.SetEndEffectorParametersType

            
            class SetEndEffectorParametersType : public MiddleCommandType {
            public:
                SetEndEffectorParametersType(jobject _jthis, bool copy);
                SetEndEffectorParametersType(const SetEndEffectorParametersType &);
                static SetEndEffectorParametersType cast(const ::crclj::java::lang::Object &);
                static bool instanceof(const ::crclj::java::lang::Object &);
                    
                SetEndEffectorParametersType();
                ~SetEndEffectorParametersType();
                ::crclj::java::util::List getParameterSetting();
            }; // end class SetEndEffectorParametersType
        } // end namespace base
    } // end namespace crcl



    // class_index = 2 clss=class java.lang.Number

    namespace java{
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


    // class_index = 3 clss=class java.lang.Double

            
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


    // class_index = 4 clss=class java.lang.Long

            
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



    // class_index = 5 clss=interface java.util.List

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



    // class_index = 6 clss=class java.lang.Boolean

        namespace lang{
            
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


    // class_index = 7 clss=class java.lang.Integer

            
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
        } // end namespace lang
    } // end namespace java



}; // end namespace crclj

