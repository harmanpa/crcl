
#include <jni.h>
#include <cstdlib>
#include <iostream>
#include "crclj.h"

namespace crclj {

    



// start_segment_index = 100
// start_segment_index = 105
// segment_index = 10
// classesSegList=[class java.lang.Integer, class java.lang.Boolean, class java.lang.Long, interface crcl.utils.CRCLCommandWrapper$CRCLCommandWrapperConsumer, class crcl.base.CRCLProgramType]


// class_index = 0 clss=class java.lang.Integer

    namespace java{
        namespace lang{
        
        // get JNI handle for class java.lang.Integer
        static inline jclass getIntegerClass();
        
        Integer::Integer(jobject _jthis, bool copy): Number(_jthis,copy) {
                
        }
        
        Integer::Integer(const Integer &objref): Number((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class Integer _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class Integer jthis=",jthis);
            }
        }
        
        Integer Integer::cast(const Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            Integer retVal(objref.jthis,true);
            return retVal;
        }
        
        bool Integer::instanceof(const Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        Integer::Integer() : Number((jobject)NULL,false) {
        JNIEnv *env =getEnv();
        static jclass cls = getIntegerClass();
        if (cls != NULL) {
            static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
            if (NULL == mid) {
                std::cerr << "Class Integer has no method constructor signature ()V" << std::endl;
            } else {
                jthis = env->NewObject(cls, mid );
                jthrowable t = env->ExceptionOccurred();
                if(t != NULL) {
                    if(GetDebugJ4Cpp()) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::parseDouble jthis=",t);
                        env->ExceptionDescribe();
                    }
                    throw t;
                }
                if(jthis == NULL) {
                    std::cerr << "Call to create new Integer with signature ()V returned null." << std::endl;
                    releaseEnv(env);
                    return;
                }
                jobjectRefType ref = env->GetObjectRefType(jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new Integer jthis=",jthis);
                if(ref != JNIGlobalRefType) {
                    jthis = env->NewGlobalRef(jthis);
                }
            }
        }
        releaseEnv(env);
        }

        Integer::Integer(jstring string_0) : Number((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "(Ljava/lang/String;)V");
                if (NULL == mid) {
                    std::cerr << "Class Integer has no method constructor signature (Ljava/lang/String;)V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid ,string_0);
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Integer::parseDouble jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new Integer with signature (Ljava/lang/String;)V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new Integer jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }

        Integer::Integer(const char * easyArg_0) : Number((jobject)NULL,false) {
        // Convenience Constructor converts common C++ types to JNI types
        JNIEnv *env =getEnv();
        static jclass cls = getIntegerClass();
        jstring string_0 = env->NewStringUTF(easyArg_0);
        
        if (cls != NULL) {
            static jmethodID mid = env->GetMethodID(cls, "<init>", "(Ljava/lang/String;)V");
            if (NULL == mid) {
                std::cerr << "Class Integer has no method constructor signature (Ljava/lang/String;)V" << std::endl;
            } else {
                jthis = env->NewObject(cls, mid ,string_0);
                jthrowable t = env->ExceptionOccurred();
                if(t != NULL) {
                    if(GetDebugJ4Cpp()) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::parseDouble jthis=",t);
                        env->ExceptionDescribe();
                    }
                    throw t;
                }
                if(jthis == NULL) {
                    std::cerr << "Call to create new Integer with signature (Ljava/lang/String;)V returned null." << std::endl;
                    releaseEnv(env);
                    return;
                }
                jobjectRefType ref = env->GetObjectRefType(jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new Integer jthis=",jthis);
                if(ref != JNIGlobalRefType) {
                    jthis = env->NewGlobalRef(jthis);
                }
            }
        }
        jobjectRefType ref_0 = env->GetObjectRefType(string_0);
        if(ref_0 == JNIGlobalRefType) {
            env->DeleteGlobalRef(string_0);
        }
        
        releaseEnv(env);
        }
        Integer::Integer(jint int_0) : Number((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "(I)V");
                if (NULL == mid) {
                    std::cerr << "Class Integer has no method constructor signature (I)V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid ,int_0);
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Integer::parseDouble jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new Integer with signature (I)V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new Integer jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for java.lang.Integer
        Integer::~Integer() {
        	// Place-holder for later extensibility.
        }


        // Field getter for MIN_VALUE
        jint Integer::getMIN_VALUE() {
        JNIEnv *env =getEnv();
        static jclass cls = getIntegerClass();
        jint retVal= (jint) -1;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "MIN_VALUE", "I");
            if (NULL == fid) {
                std::cerr << "Class java.lang.Integer has no field named MIN_VALUE with signature I." << std::endl;
                return (jint) -1;
            } else {
                retVal= (jint)  env->GetStaticIntField( cls, fid );
            }
        }
        releaseEnv(env);
        return retVal;
        }

        // Field getter for MAX_VALUE
        jint Integer::getMAX_VALUE() {
        JNIEnv *env =getEnv();
        static jclass cls = getIntegerClass();
        jint retVal= (jint) -1;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "MAX_VALUE", "I");
            if (NULL == fid) {
                std::cerr << "Class java.lang.Integer has no field named MAX_VALUE with signature I." << std::endl;
                return (jint) -1;
            } else {
                retVal= (jint)  env->GetStaticIntField( cls, fid );
            }
        }
        releaseEnv(env);
        return retVal;
        }

        // Field getter for SIZE
        jint Integer::getSIZE() {
        JNIEnv *env =getEnv();
        static jclass cls = getIntegerClass();
        jint retVal= (jint) -1;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "SIZE", "I");
            if (NULL == fid) {
                std::cerr << "Class java.lang.Integer has no field named SIZE with signature I." << std::endl;
                return (jint) -1;
            } else {
                retVal= (jint)  env->GetStaticIntField( cls, fid );
            }
        }
        releaseEnv(env);
        return retVal;
        }

        // Field getter for BYTES
        jint Integer::getBYTES() {
        JNIEnv *env =getEnv();
        static jclass cls = getIntegerClass();
        jint retVal= (jint) -1;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "BYTES", "I");
            if (NULL == fid) {
                std::cerr << "Class java.lang.Integer has no field named BYTES with signature I." << std::endl;
                return (jint) -1;
            } else {
                retVal= (jint)  env->GetStaticIntField( cls, fid );
            }
        }
        releaseEnv(env);
        return retVal;
        }

        jint Integer::numberOfLeadingZeros(jint int_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "numberOfLeadingZeros", "(I)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named numberOfLeadingZeros with signature (I)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,int_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::numberOfLeadingZeros jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Integer::numberOfTrailingZeros(jint int_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "numberOfTrailingZeros", "(I)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named numberOfTrailingZeros with signature (I)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,int_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::numberOfTrailingZeros jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Integer::bitCount(jint int_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "bitCount", "(I)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named bitCount with signature (I)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,int_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::bitCount jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jboolean Integer::equals(const Object & object_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method equals of java.lang.Integer with jthis == NULL." << std::endl;
                return false;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Integer::equals jthis=",jthis);
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "equals", "(Ljava/lang/Object;)Z");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Integer::equals jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Integer has no method named equals with signature (Ljava/lang/Object;)Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallBooleanMethod(jthis, mid ,object_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Integer::equals jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jstring Integer::toString(jint int_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "toString", "(I)Ljava/lang/String;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named toString with signature (I)Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallStaticObjectMethod( cls, mid ,int_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::toString jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jstring Integer::toString(jint int_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "toString", "(II)Ljava/lang/String;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named toString with signature (II)Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallStaticObjectMethod( cls, mid ,int_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::toString jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jstring Integer::toString() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method toString of java.lang.Integer with jthis == NULL." << std::endl;
                return NULL;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Integer::toString jthis=",jthis);
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "toString", "()Ljava/lang/String;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Integer::toString jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Integer has no method named toString with signature ()Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Integer::toString jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Integer::hashCode(jint int_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "hashCode", "(I)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named hashCode with signature (I)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,int_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::hashCode jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Integer::hashCode() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method hashCode of java.lang.Integer with jthis == NULL." << std::endl;
                return (jint) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Integer::hashCode jthis=",jthis);
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "hashCode", "()I");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Integer::hashCode jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Integer has no method named hashCode with signature ()I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallIntMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Integer::hashCode jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Integer::min(jint int_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "min", "(II)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named min with signature (II)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,int_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::min jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Integer::max(jint int_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "max", "(II)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named max with signature (II)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,int_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::max jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Integer::reverseBytes(jint int_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "reverseBytes", "(I)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named reverseBytes with signature (I)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,int_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::reverseBytes jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Integer::compareTo(const Integer & integer_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method compareTo of java.lang.Integer with jthis == NULL." << std::endl;
                return (jint) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Integer::compareTo jthis=",jthis);
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "compareTo", "(Ljava/lang/Integer;)I");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Integer::compareTo jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Integer has no method named compareTo with signature (Ljava/lang/Integer;)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallIntMethod(jthis, mid ,integer_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Integer::compareTo jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jbyte Integer::byteValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method byteValue of java.lang.Integer with jthis == NULL." << std::endl;
                return (jbyte) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Integer::byteValue jthis=",jthis);
            jbyte retVal= (jbyte) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "byteValue", "()B");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Integer::byteValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Integer has no method named byteValue with signature ()B." << std::endl;
                    return (jbyte) -1;
                } else {
                    retVal= (jbyte)  env->CallByteMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Integer::byteValue jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jshort Integer::shortValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method shortValue of java.lang.Integer with jthis == NULL." << std::endl;
                return (jshort) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Integer::shortValue jthis=",jthis);
            jshort retVal=(jshort) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "shortValue", "()S");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Integer::shortValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Integer has no method named shortValue with signature ()S." << std::endl;
                    return (jshort) -1;
                } else {
                    retVal= (jshort)  env->CallShortMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Integer::shortValue jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Integer::intValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method intValue of java.lang.Integer with jthis == NULL." << std::endl;
                return (jint) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Integer::intValue jthis=",jthis);
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "intValue", "()I");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Integer::intValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Integer has no method named intValue with signature ()I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallIntMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Integer::intValue jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jlong Integer::longValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method longValue of java.lang.Integer with jthis == NULL." << std::endl;
                return (jlong) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Integer::longValue jthis=",jthis);
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "longValue", "()J");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Integer::longValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Integer has no method named longValue with signature ()J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallLongMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Integer::longValue jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jfloat Integer::floatValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method floatValue of java.lang.Integer with jthis == NULL." << std::endl;
                return (jfloat) -1.0;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Integer::floatValue jthis=",jthis);
            jfloat retVal= (jfloat) -1.0;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "floatValue", "()F");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Integer::floatValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Integer has no method named floatValue with signature ()F." << std::endl;
                    return (jfloat) -1.0;
                } else {
                    retVal= (jfloat)  env->CallFloatMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Integer::floatValue jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jdouble Integer::doubleValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method doubleValue of java.lang.Integer with jthis == NULL." << std::endl;
                return (jdouble) -1.0;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Integer::doubleValue jthis=",jthis);
            jdouble retVal= (jdouble) -1.0;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "doubleValue", "()D");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Integer::doubleValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Integer has no method named doubleValue with signature ()D." << std::endl;
                    return (jdouble) -1.0;
                } else {
                    retVal= (jdouble)  env->CallDoubleMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Integer::doubleValue jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        Integer Integer::valueOf(jint int_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "valueOf", "(I)Ljava/lang/Integer;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named valueOf with signature (I)Ljava/lang/Integer;." << std::endl;
                    static Integer nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,int_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::valueOf jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            Integer retObject(retVal,false);
            return retObject;
        }

        Integer Integer::valueOf(jstring string_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "valueOf", "(Ljava/lang/String;I)Ljava/lang/Integer;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named valueOf with signature (Ljava/lang/String;I)Ljava/lang/Integer;." << std::endl;
                    static Integer nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,string_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::valueOf jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            Integer retObject(retVal,false);
            return retObject;
        }

        Integer Integer::valueOf(jstring string_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "valueOf", "(Ljava/lang/String;)Ljava/lang/Integer;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named valueOf with signature (Ljava/lang/String;)Ljava/lang/Integer;." << std::endl;
                    static Integer nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::valueOf jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            Integer retObject(retVal,false);
            return retObject;
        }

        jstring Integer::toHexString(jint int_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "toHexString", "(I)Ljava/lang/String;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named toHexString with signature (I)Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallStaticObjectMethod( cls, mid ,int_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::toHexString jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        Integer Integer::decode(jstring string_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "decode", "(Ljava/lang/String;)Ljava/lang/Integer;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named decode with signature (Ljava/lang/String;)Ljava/lang/Integer;." << std::endl;
                    static Integer nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::decode jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            Integer retObject(retVal,false);
            return retObject;
        }

        jint Integer::compare(jint int_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "compare", "(II)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named compare with signature (II)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,int_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::compare jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Integer::reverse(jint int_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "reverse", "(I)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named reverse with signature (I)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,int_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::reverse jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jlong Integer::toUnsignedLong(jint int_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "toUnsignedLong", "(I)J");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named toUnsignedLong with signature (I)J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallStaticLongMethod( cls, mid ,int_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::toUnsignedLong jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Integer::parseInt(jstring string_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "parseInt", "(Ljava/lang/String;)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named parseInt with signature (Ljava/lang/String;)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::parseInt jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Integer::parseInt(jstring string_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "parseInt", "(Ljava/lang/String;I)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named parseInt with signature (Ljava/lang/String;I)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,string_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::parseInt jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Integer::sum(jint int_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "sum", "(II)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named sum with signature (II)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,int_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::sum jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Integer::compareUnsigned(jint int_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "compareUnsigned", "(II)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named compareUnsigned with signature (II)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,int_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::compareUnsigned jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jstring Integer::toUnsignedString(jint int_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "toUnsignedString", "(II)Ljava/lang/String;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named toUnsignedString with signature (II)Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallStaticObjectMethod( cls, mid ,int_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::toUnsignedString jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jstring Integer::toUnsignedString(jint int_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "toUnsignedString", "(I)Ljava/lang/String;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named toUnsignedString with signature (I)Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallStaticObjectMethod( cls, mid ,int_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::toUnsignedString jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jstring Integer::toOctalString(jint int_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "toOctalString", "(I)Ljava/lang/String;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named toOctalString with signature (I)Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallStaticObjectMethod( cls, mid ,int_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::toOctalString jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jstring Integer::toBinaryString(jint int_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "toBinaryString", "(I)Ljava/lang/String;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named toBinaryString with signature (I)Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallStaticObjectMethod( cls, mid ,int_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::toBinaryString jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Integer::parseUnsignedInt(jstring string_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "parseUnsignedInt", "(Ljava/lang/String;I)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named parseUnsignedInt with signature (Ljava/lang/String;I)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,string_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::parseUnsignedInt jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Integer::parseUnsignedInt(jstring string_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "parseUnsignedInt", "(Ljava/lang/String;)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named parseUnsignedInt with signature (Ljava/lang/String;)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::parseUnsignedInt jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        Integer Integer::getInteger(jstring string_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "getInteger", "(Ljava/lang/String;I)Ljava/lang/Integer;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named getInteger with signature (Ljava/lang/String;I)Ljava/lang/Integer;." << std::endl;
                    static Integer nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,string_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::getInteger jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            Integer retObject(retVal,false);
            return retObject;
        }

        Integer Integer::getInteger(jstring string_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "getInteger", "(Ljava/lang/String;)Ljava/lang/Integer;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named getInteger with signature (Ljava/lang/String;)Ljava/lang/Integer;." << std::endl;
                    static Integer nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::getInteger jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            Integer retObject(retVal,false);
            return retObject;
        }

        Integer Integer::getInteger(jstring string_0,const Integer & integer_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "getInteger", "(Ljava/lang/String;Ljava/lang/Integer;)Ljava/lang/Integer;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named getInteger with signature (Ljava/lang/String;Ljava/lang/Integer;)Ljava/lang/Integer;." << std::endl;
                    static Integer nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,string_0,integer_1.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::getInteger jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            Integer retObject(retVal,false);
            return retObject;
        }

        jint Integer::divideUnsigned(jint int_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "divideUnsigned", "(II)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named divideUnsigned with signature (II)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,int_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::divideUnsigned jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Integer::remainderUnsigned(jint int_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "remainderUnsigned", "(II)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named remainderUnsigned with signature (II)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,int_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::remainderUnsigned jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Integer::highestOneBit(jint int_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "highestOneBit", "(I)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named highestOneBit with signature (I)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,int_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::highestOneBit jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Integer::lowestOneBit(jint int_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "lowestOneBit", "(I)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named lowestOneBit with signature (I)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,int_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::lowestOneBit jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Integer::rotateLeft(jint int_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "rotateLeft", "(II)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named rotateLeft with signature (II)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,int_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::rotateLeft jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Integer::rotateRight(jint int_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "rotateRight", "(II)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named rotateRight with signature (II)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,int_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::rotateRight jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Integer::signum(jint int_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "signum", "(I)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named signum with signature (I)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,int_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::signum jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }
        static jclass getNewIntegerClass() {
            jclass clss = getEnv()->FindClass("java/lang/Integer");
            if (NULL == clss) {
                std::cerr << " Can't find class java/lang/Integer" << std::endl;
            }
            return clss;
        }
        
        static jclass IntegerClass = NULL;
        static inline jclass getIntegerClass() {
            if (IntegerClass != NULL) {
                return IntegerClass;
            }
            IntegerClass = getNewIntegerClass();
            return IntegerClass;
        }

    // class_index = 1 clss=class java.lang.Boolean

        
        // get JNI handle for class java.lang.Boolean
        static inline jclass getBooleanClass();
        
        Boolean::Boolean(jobject _jthis, bool copy): Object(_jthis,copy) {
                
        }
        
        Boolean::Boolean(const Boolean &objref): Object((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class Boolean _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class Boolean jthis=",jthis);
            }
        }
        
        Boolean Boolean::cast(const Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getBooleanClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            Boolean retVal(objref.jthis,true);
            return retVal;
        }
        
        bool Boolean::instanceof(const Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getBooleanClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        Boolean::Boolean() : Object((jobject)NULL,false) {
        JNIEnv *env =getEnv();
        static jclass cls = getBooleanClass();
        if (cls != NULL) {
            static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
            if (NULL == mid) {
                std::cerr << "Class Boolean has no method constructor signature ()V" << std::endl;
            } else {
                jthis = env->NewObject(cls, mid );
                jthrowable t = env->ExceptionOccurred();
                if(t != NULL) {
                    if(GetDebugJ4Cpp()) {
                        DebugPrintJObject(__FILE__,__LINE__," Boolean::signum jthis=",t);
                        env->ExceptionDescribe();
                    }
                    throw t;
                }
                if(jthis == NULL) {
                    std::cerr << "Call to create new Boolean with signature ()V returned null." << std::endl;
                    releaseEnv(env);
                    return;
                }
                jobjectRefType ref = env->GetObjectRefType(jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new Boolean jthis=",jthis);
                if(ref != JNIGlobalRefType) {
                    jthis = env->NewGlobalRef(jthis);
                }
            }
        }
        releaseEnv(env);
        }

        Boolean::Boolean(jboolean boolean_0) : Object((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getBooleanClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "(Z)V");
                if (NULL == mid) {
                    std::cerr << "Class Boolean has no method constructor signature (Z)V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid ,boolean_0);
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Boolean::signum jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new Boolean with signature (Z)V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new Boolean jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }

        Boolean::Boolean(jstring string_0) : Object((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getBooleanClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "(Ljava/lang/String;)V");
                if (NULL == mid) {
                    std::cerr << "Class Boolean has no method constructor signature (Ljava/lang/String;)V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid ,string_0);
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Boolean::signum jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new Boolean with signature (Ljava/lang/String;)V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new Boolean jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }

        Boolean::Boolean(const char * easyArg_0) : Object((jobject)NULL,false) {
        // Convenience Constructor converts common C++ types to JNI types
        JNIEnv *env =getEnv();
        static jclass cls = getBooleanClass();
        jstring string_0 = env->NewStringUTF(easyArg_0);
        
        if (cls != NULL) {
            static jmethodID mid = env->GetMethodID(cls, "<init>", "(Ljava/lang/String;)V");
            if (NULL == mid) {
                std::cerr << "Class Boolean has no method constructor signature (Ljava/lang/String;)V" << std::endl;
            } else {
                jthis = env->NewObject(cls, mid ,string_0);
                jthrowable t = env->ExceptionOccurred();
                if(t != NULL) {
                    if(GetDebugJ4Cpp()) {
                        DebugPrintJObject(__FILE__,__LINE__," Boolean::signum jthis=",t);
                        env->ExceptionDescribe();
                    }
                    throw t;
                }
                if(jthis == NULL) {
                    std::cerr << "Call to create new Boolean with signature (Ljava/lang/String;)V returned null." << std::endl;
                    releaseEnv(env);
                    return;
                }
                jobjectRefType ref = env->GetObjectRefType(jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new Boolean jthis=",jthis);
                if(ref != JNIGlobalRefType) {
                    jthis = env->NewGlobalRef(jthis);
                }
            }
        }
        jobjectRefType ref_0 = env->GetObjectRefType(string_0);
        if(ref_0 == JNIGlobalRefType) {
            env->DeleteGlobalRef(string_0);
        }
        
        releaseEnv(env);
        }

        // Destructor for java.lang.Boolean
        Boolean::~Boolean() {
        	// Place-holder for later extensibility.
        }


        // Field getter for TRUE
        Boolean Boolean::getTRUE() {
        JNIEnv *env =getEnv();
        static jclass cls = getBooleanClass();
        jobject retVal=NULL;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "TRUE", "Ljava/lang/Boolean;");
            if (NULL == fid) {
                std::cerr << "Class java.lang.Boolean has no field named TRUE with signature Ljava/lang/Boolean;." << std::endl;
                static Boolean nullObject((jobject)NULL,false); return nullObject;
            } else {
                retVal= (jobject)  env->GetStaticObjectField( cls, fid );
            }
        }
        releaseEnv(env);
                    
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            Boolean retObject(retVal,false);
            return retObject;
        }

        // Field getter for FALSE
        Boolean Boolean::getFALSE() {
        JNIEnv *env =getEnv();
        static jclass cls = getBooleanClass();
        jobject retVal=NULL;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "FALSE", "Ljava/lang/Boolean;");
            if (NULL == fid) {
                std::cerr << "Class java.lang.Boolean has no field named FALSE with signature Ljava/lang/Boolean;." << std::endl;
                static Boolean nullObject((jobject)NULL,false); return nullObject;
            } else {
                retVal= (jobject)  env->GetStaticObjectField( cls, fid );
            }
        }
        releaseEnv(env);
                    
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            Boolean retObject(retVal,false);
            return retObject;
        }

        jboolean Boolean::equals(const Object & object_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method equals of java.lang.Boolean with jthis == NULL." << std::endl;
                return false;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Boolean::equals jthis=",jthis);
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "equals", "(Ljava/lang/Object;)Z");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Boolean::equals jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Boolean has no method named equals with signature (Ljava/lang/Object;)Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallBooleanMethod(jthis, mid ,object_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Boolean::equals jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jstring Boolean::toString() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method toString of java.lang.Boolean with jthis == NULL." << std::endl;
                return NULL;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Boolean::toString jthis=",jthis);
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "toString", "()Ljava/lang/String;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Boolean::toString jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Boolean has no method named toString with signature ()Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Boolean::toString jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jstring Boolean::toString(jboolean boolean_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getBooleanClass();
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "toString", "(Z)Ljava/lang/String;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Boolean has no method named toString with signature (Z)Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallStaticObjectMethod( cls, mid ,boolean_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Boolean::toString jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Boolean::hashCode(jboolean boolean_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getBooleanClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "hashCode", "(Z)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Boolean has no method named hashCode with signature (Z)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,boolean_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Boolean::hashCode jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Boolean::hashCode() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method hashCode of java.lang.Boolean with jthis == NULL." << std::endl;
                return (jint) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Boolean::hashCode jthis=",jthis);
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "hashCode", "()I");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Boolean::hashCode jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Boolean has no method named hashCode with signature ()I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallIntMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Boolean::hashCode jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Boolean::compareTo(const Boolean & boolean_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method compareTo of java.lang.Boolean with jthis == NULL." << std::endl;
                return (jint) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Boolean::compareTo jthis=",jthis);
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "compareTo", "(Ljava/lang/Boolean;)I");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Boolean::compareTo jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Boolean has no method named compareTo with signature (Ljava/lang/Boolean;)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallIntMethod(jthis, mid ,boolean_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Boolean::compareTo jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jboolean Boolean::getBoolean(jstring string_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getBooleanClass();
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "getBoolean", "(Ljava/lang/String;)Z");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Boolean has no method named getBoolean with signature (Ljava/lang/String;)Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallStaticBooleanMethod( cls, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Boolean::getBoolean jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jboolean Boolean::booleanValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method booleanValue of java.lang.Boolean with jthis == NULL." << std::endl;
                return false;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Boolean::booleanValue jthis=",jthis);
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "booleanValue", "()Z");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Boolean::booleanValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Boolean has no method named booleanValue with signature ()Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallBooleanMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Boolean::booleanValue jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        Boolean Boolean::valueOf(jstring string_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getBooleanClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "valueOf", "(Ljava/lang/String;)Ljava/lang/Boolean;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Boolean has no method named valueOf with signature (Ljava/lang/String;)Ljava/lang/Boolean;." << std::endl;
                    static Boolean nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Boolean::valueOf jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            Boolean retObject(retVal,false);
            return retObject;
        }

        Boolean Boolean::valueOf(jboolean boolean_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getBooleanClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "valueOf", "(Z)Ljava/lang/Boolean;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Boolean has no method named valueOf with signature (Z)Ljava/lang/Boolean;." << std::endl;
                    static Boolean nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,boolean_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Boolean::valueOf jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            Boolean retObject(retVal,false);
            return retObject;
        }

        jint Boolean::compare(jboolean boolean_0,jboolean boolean_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getBooleanClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "compare", "(ZZ)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Boolean has no method named compare with signature (ZZ)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,boolean_0,boolean_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Boolean::compare jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jboolean Boolean::parseBoolean(jstring string_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getBooleanClass();
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "parseBoolean", "(Ljava/lang/String;)Z");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Boolean has no method named parseBoolean with signature (Ljava/lang/String;)Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallStaticBooleanMethod( cls, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Boolean::parseBoolean jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jboolean Boolean::logicalAnd(jboolean boolean_0,jboolean boolean_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getBooleanClass();
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "logicalAnd", "(ZZ)Z");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Boolean has no method named logicalAnd with signature (ZZ)Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallStaticBooleanMethod( cls, mid ,boolean_0,boolean_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Boolean::logicalAnd jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jboolean Boolean::logicalOr(jboolean boolean_0,jboolean boolean_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getBooleanClass();
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "logicalOr", "(ZZ)Z");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Boolean has no method named logicalOr with signature (ZZ)Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallStaticBooleanMethod( cls, mid ,boolean_0,boolean_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Boolean::logicalOr jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jboolean Boolean::logicalXor(jboolean boolean_0,jboolean boolean_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getBooleanClass();
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "logicalXor", "(ZZ)Z");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Boolean has no method named logicalXor with signature (ZZ)Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallStaticBooleanMethod( cls, mid ,boolean_0,boolean_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Boolean::logicalXor jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }
        static jclass getNewBooleanClass() {
            jclass clss = getEnv()->FindClass("java/lang/Boolean");
            if (NULL == clss) {
                std::cerr << " Can't find class java/lang/Boolean" << std::endl;
            }
            return clss;
        }
        
        static jclass BooleanClass = NULL;
        static inline jclass getBooleanClass() {
            if (BooleanClass != NULL) {
                return BooleanClass;
            }
            BooleanClass = getNewBooleanClass();
            return BooleanClass;
        }

    // class_index = 2 clss=class java.lang.Long

        
        // get JNI handle for class java.lang.Long
        static inline jclass getLongClass();
        
        Long::Long(jobject _jthis, bool copy): Number(_jthis,copy) {
                
        }
        
        Long::Long(const Long &objref): Number((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class Long _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class Long jthis=",jthis);
            }
        }
        
        Long Long::cast(const Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            Long retVal(objref.jthis,true);
            return retVal;
        }
        
        bool Long::instanceof(const Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        Long::Long() : Number((jobject)NULL,false) {
        JNIEnv *env =getEnv();
        static jclass cls = getLongClass();
        if (cls != NULL) {
            static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
            if (NULL == mid) {
                std::cerr << "Class Long has no method constructor signature ()V" << std::endl;
            } else {
                jthis = env->NewObject(cls, mid );
                jthrowable t = env->ExceptionOccurred();
                if(t != NULL) {
                    if(GetDebugJ4Cpp()) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::logicalXor jthis=",t);
                        env->ExceptionDescribe();
                    }
                    throw t;
                }
                if(jthis == NULL) {
                    std::cerr << "Call to create new Long with signature ()V returned null." << std::endl;
                    releaseEnv(env);
                    return;
                }
                jobjectRefType ref = env->GetObjectRefType(jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new Long jthis=",jthis);
                if(ref != JNIGlobalRefType) {
                    jthis = env->NewGlobalRef(jthis);
                }
            }
        }
        releaseEnv(env);
        }

        Long::Long(jstring string_0) : Number((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "(Ljava/lang/String;)V");
                if (NULL == mid) {
                    std::cerr << "Class Long has no method constructor signature (Ljava/lang/String;)V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid ,string_0);
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Long::logicalXor jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new Long with signature (Ljava/lang/String;)V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new Long jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }

        Long::Long(const char * easyArg_0) : Number((jobject)NULL,false) {
        // Convenience Constructor converts common C++ types to JNI types
        JNIEnv *env =getEnv();
        static jclass cls = getLongClass();
        jstring string_0 = env->NewStringUTF(easyArg_0);
        
        if (cls != NULL) {
            static jmethodID mid = env->GetMethodID(cls, "<init>", "(Ljava/lang/String;)V");
            if (NULL == mid) {
                std::cerr << "Class Long has no method constructor signature (Ljava/lang/String;)V" << std::endl;
            } else {
                jthis = env->NewObject(cls, mid ,string_0);
                jthrowable t = env->ExceptionOccurred();
                if(t != NULL) {
                    if(GetDebugJ4Cpp()) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::logicalXor jthis=",t);
                        env->ExceptionDescribe();
                    }
                    throw t;
                }
                if(jthis == NULL) {
                    std::cerr << "Call to create new Long with signature (Ljava/lang/String;)V returned null." << std::endl;
                    releaseEnv(env);
                    return;
                }
                jobjectRefType ref = env->GetObjectRefType(jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new Long jthis=",jthis);
                if(ref != JNIGlobalRefType) {
                    jthis = env->NewGlobalRef(jthis);
                }
            }
        }
        jobjectRefType ref_0 = env->GetObjectRefType(string_0);
        if(ref_0 == JNIGlobalRefType) {
            env->DeleteGlobalRef(string_0);
        }
        
        releaseEnv(env);
        }
        Long::Long(jlong long_0) : Number((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "(J)V");
                if (NULL == mid) {
                    std::cerr << "Class Long has no method constructor signature (J)V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid ,long_0);
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Long::logicalXor jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new Long with signature (J)V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new Long jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for java.lang.Long
        Long::~Long() {
        	// Place-holder for later extensibility.
        }


        // Field getter for MIN_VALUE
        jlong Long::getMIN_VALUE() {
        JNIEnv *env =getEnv();
        static jclass cls = getLongClass();
        jlong retVal= (jlong) -1;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "MIN_VALUE", "J");
            if (NULL == fid) {
                std::cerr << "Class java.lang.Long has no field named MIN_VALUE with signature J." << std::endl;
                return (jlong) -1;
            } else {
                retVal= (jlong)  env->GetStaticLongField( cls, fid );
            }
        }
        releaseEnv(env);
        return retVal;
        }

        // Field getter for MAX_VALUE
        jlong Long::getMAX_VALUE() {
        JNIEnv *env =getEnv();
        static jclass cls = getLongClass();
        jlong retVal= (jlong) -1;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "MAX_VALUE", "J");
            if (NULL == fid) {
                std::cerr << "Class java.lang.Long has no field named MAX_VALUE with signature J." << std::endl;
                return (jlong) -1;
            } else {
                retVal= (jlong)  env->GetStaticLongField( cls, fid );
            }
        }
        releaseEnv(env);
        return retVal;
        }

        // Field getter for SIZE
        jint Long::getSIZE() {
        JNIEnv *env =getEnv();
        static jclass cls = getLongClass();
        jint retVal= (jint) -1;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "SIZE", "I");
            if (NULL == fid) {
                std::cerr << "Class java.lang.Long has no field named SIZE with signature I." << std::endl;
                return (jint) -1;
            } else {
                retVal= (jint)  env->GetStaticIntField( cls, fid );
            }
        }
        releaseEnv(env);
        return retVal;
        }

        // Field getter for BYTES
        jint Long::getBYTES() {
        JNIEnv *env =getEnv();
        static jclass cls = getLongClass();
        jint retVal= (jint) -1;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "BYTES", "I");
            if (NULL == fid) {
                std::cerr << "Class java.lang.Long has no field named BYTES with signature I." << std::endl;
                return (jint) -1;
            } else {
                retVal= (jint)  env->GetStaticIntField( cls, fid );
            }
        }
        releaseEnv(env);
        return retVal;
        }

        jint Long::numberOfLeadingZeros(jlong long_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "numberOfLeadingZeros", "(J)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named numberOfLeadingZeros with signature (J)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,long_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::numberOfLeadingZeros jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Long::numberOfTrailingZeros(jlong long_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "numberOfTrailingZeros", "(J)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named numberOfTrailingZeros with signature (J)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,long_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::numberOfTrailingZeros jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Long::bitCount(jlong long_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "bitCount", "(J)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named bitCount with signature (J)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,long_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::bitCount jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jboolean Long::equals(const Object & object_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method equals of java.lang.Long with jthis == NULL." << std::endl;
                return false;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Long::equals jthis=",jthis);
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "equals", "(Ljava/lang/Object;)Z");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Long::equals jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Long has no method named equals with signature (Ljava/lang/Object;)Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallBooleanMethod(jthis, mid ,object_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Long::equals jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jstring Long::toString(jlong long_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "toString", "(J)Ljava/lang/String;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named toString with signature (J)Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallStaticObjectMethod( cls, mid ,long_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::toString jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jstring Long::toString(jlong long_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "toString", "(JI)Ljava/lang/String;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named toString with signature (JI)Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallStaticObjectMethod( cls, mid ,long_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::toString jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jstring Long::toString() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method toString of java.lang.Long with jthis == NULL." << std::endl;
                return NULL;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Long::toString jthis=",jthis);
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "toString", "()Ljava/lang/String;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Long::toString jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Long has no method named toString with signature ()Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Long::toString jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Long::hashCode(jlong long_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "hashCode", "(J)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named hashCode with signature (J)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,long_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::hashCode jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Long::hashCode() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method hashCode of java.lang.Long with jthis == NULL." << std::endl;
                return (jint) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Long::hashCode jthis=",jthis);
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "hashCode", "()I");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Long::hashCode jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Long has no method named hashCode with signature ()I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallIntMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Long::hashCode jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jlong Long::min(jlong long_0,jlong long_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "min", "(JJ)J");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named min with signature (JJ)J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallStaticLongMethod( cls, mid ,long_0,long_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::min jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jlong Long::max(jlong long_0,jlong long_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "max", "(JJ)J");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named max with signature (JJ)J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallStaticLongMethod( cls, mid ,long_0,long_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::max jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jlong Long::reverseBytes(jlong long_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "reverseBytes", "(J)J");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named reverseBytes with signature (J)J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallStaticLongMethod( cls, mid ,long_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::reverseBytes jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Long::compareTo(const Long & long_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method compareTo of java.lang.Long with jthis == NULL." << std::endl;
                return (jint) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Long::compareTo jthis=",jthis);
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "compareTo", "(Ljava/lang/Long;)I");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Long::compareTo jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Long has no method named compareTo with signature (Ljava/lang/Long;)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallIntMethod(jthis, mid ,long_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Long::compareTo jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        Long Long::getLong(jstring string_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "getLong", "(Ljava/lang/String;)Ljava/lang/Long;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named getLong with signature (Ljava/lang/String;)Ljava/lang/Long;." << std::endl;
                    static Long nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::getLong jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            Long retObject(retVal,false);
            return retObject;
        }

        Long Long::getLong(jstring string_0,jlong long_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "getLong", "(Ljava/lang/String;J)Ljava/lang/Long;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named getLong with signature (Ljava/lang/String;J)Ljava/lang/Long;." << std::endl;
                    static Long nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,string_0,long_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::getLong jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            Long retObject(retVal,false);
            return retObject;
        }

        Long Long::getLong(jstring string_0,const Long & long_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "getLong", "(Ljava/lang/String;Ljava/lang/Long;)Ljava/lang/Long;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named getLong with signature (Ljava/lang/String;Ljava/lang/Long;)Ljava/lang/Long;." << std::endl;
                    static Long nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,string_0,long_1.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::getLong jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            Long retObject(retVal,false);
            return retObject;
        }

        jbyte Long::byteValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method byteValue of java.lang.Long with jthis == NULL." << std::endl;
                return (jbyte) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Long::byteValue jthis=",jthis);
            jbyte retVal= (jbyte) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "byteValue", "()B");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Long::byteValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Long has no method named byteValue with signature ()B." << std::endl;
                    return (jbyte) -1;
                } else {
                    retVal= (jbyte)  env->CallByteMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Long::byteValue jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jshort Long::shortValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method shortValue of java.lang.Long with jthis == NULL." << std::endl;
                return (jshort) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Long::shortValue jthis=",jthis);
            jshort retVal=(jshort) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "shortValue", "()S");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Long::shortValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Long has no method named shortValue with signature ()S." << std::endl;
                    return (jshort) -1;
                } else {
                    retVal= (jshort)  env->CallShortMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Long::shortValue jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Long::intValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method intValue of java.lang.Long with jthis == NULL." << std::endl;
                return (jint) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Long::intValue jthis=",jthis);
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "intValue", "()I");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Long::intValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Long has no method named intValue with signature ()I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallIntMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Long::intValue jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jlong Long::longValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method longValue of java.lang.Long with jthis == NULL." << std::endl;
                return (jlong) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Long::longValue jthis=",jthis);
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "longValue", "()J");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Long::longValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Long has no method named longValue with signature ()J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallLongMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Long::longValue jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jfloat Long::floatValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method floatValue of java.lang.Long with jthis == NULL." << std::endl;
                return (jfloat) -1.0;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Long::floatValue jthis=",jthis);
            jfloat retVal= (jfloat) -1.0;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "floatValue", "()F");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Long::floatValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Long has no method named floatValue with signature ()F." << std::endl;
                    return (jfloat) -1.0;
                } else {
                    retVal= (jfloat)  env->CallFloatMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Long::floatValue jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jdouble Long::doubleValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method doubleValue of java.lang.Long with jthis == NULL." << std::endl;
                return (jdouble) -1.0;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Long::doubleValue jthis=",jthis);
            jdouble retVal= (jdouble) -1.0;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "doubleValue", "()D");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Long::doubleValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Long has no method named doubleValue with signature ()D." << std::endl;
                    return (jdouble) -1.0;
                } else {
                    retVal= (jdouble)  env->CallDoubleMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Long::doubleValue jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        Long Long::valueOf(jstring string_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "valueOf", "(Ljava/lang/String;I)Ljava/lang/Long;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named valueOf with signature (Ljava/lang/String;I)Ljava/lang/Long;." << std::endl;
                    static Long nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,string_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::valueOf jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            Long retObject(retVal,false);
            return retObject;
        }

        Long Long::valueOf(jstring string_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "valueOf", "(Ljava/lang/String;)Ljava/lang/Long;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named valueOf with signature (Ljava/lang/String;)Ljava/lang/Long;." << std::endl;
                    static Long nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::valueOf jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            Long retObject(retVal,false);
            return retObject;
        }

        Long Long::valueOf(jlong long_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "valueOf", "(J)Ljava/lang/Long;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named valueOf with signature (J)Ljava/lang/Long;." << std::endl;
                    static Long nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,long_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::valueOf jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            Long retObject(retVal,false);
            return retObject;
        }

        jstring Long::toHexString(jlong long_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "toHexString", "(J)Ljava/lang/String;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named toHexString with signature (J)Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallStaticObjectMethod( cls, mid ,long_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::toHexString jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        Long Long::decode(jstring string_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "decode", "(Ljava/lang/String;)Ljava/lang/Long;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named decode with signature (Ljava/lang/String;)Ljava/lang/Long;." << std::endl;
                    static Long nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::decode jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            Long retObject(retVal,false);
            return retObject;
        }

        jint Long::compare(jlong long_0,jlong long_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "compare", "(JJ)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named compare with signature (JJ)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,long_0,long_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::compare jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jlong Long::reverse(jlong long_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "reverse", "(J)J");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named reverse with signature (J)J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallStaticLongMethod( cls, mid ,long_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::reverse jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jlong Long::sum(jlong long_0,jlong long_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "sum", "(JJ)J");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named sum with signature (JJ)J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallStaticLongMethod( cls, mid ,long_0,long_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::sum jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Long::compareUnsigned(jlong long_0,jlong long_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "compareUnsigned", "(JJ)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named compareUnsigned with signature (JJ)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,long_0,long_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::compareUnsigned jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jstring Long::toUnsignedString(jlong long_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "toUnsignedString", "(J)Ljava/lang/String;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named toUnsignedString with signature (J)Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallStaticObjectMethod( cls, mid ,long_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::toUnsignedString jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jstring Long::toUnsignedString(jlong long_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "toUnsignedString", "(JI)Ljava/lang/String;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named toUnsignedString with signature (JI)Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallStaticObjectMethod( cls, mid ,long_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::toUnsignedString jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jstring Long::toOctalString(jlong long_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "toOctalString", "(J)Ljava/lang/String;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named toOctalString with signature (J)Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallStaticObjectMethod( cls, mid ,long_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::toOctalString jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jstring Long::toBinaryString(jlong long_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "toBinaryString", "(J)Ljava/lang/String;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named toBinaryString with signature (J)Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallStaticObjectMethod( cls, mid ,long_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::toBinaryString jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jlong Long::divideUnsigned(jlong long_0,jlong long_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "divideUnsigned", "(JJ)J");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named divideUnsigned with signature (JJ)J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallStaticLongMethod( cls, mid ,long_0,long_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::divideUnsigned jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jlong Long::remainderUnsigned(jlong long_0,jlong long_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "remainderUnsigned", "(JJ)J");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named remainderUnsigned with signature (JJ)J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallStaticLongMethod( cls, mid ,long_0,long_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::remainderUnsigned jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jlong Long::highestOneBit(jlong long_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "highestOneBit", "(J)J");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named highestOneBit with signature (J)J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallStaticLongMethod( cls, mid ,long_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::highestOneBit jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jlong Long::lowestOneBit(jlong long_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "lowestOneBit", "(J)J");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named lowestOneBit with signature (J)J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallStaticLongMethod( cls, mid ,long_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::lowestOneBit jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jlong Long::rotateLeft(jlong long_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "rotateLeft", "(JI)J");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named rotateLeft with signature (JI)J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallStaticLongMethod( cls, mid ,long_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::rotateLeft jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jlong Long::rotateRight(jlong long_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "rotateRight", "(JI)J");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named rotateRight with signature (JI)J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallStaticLongMethod( cls, mid ,long_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::rotateRight jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Long::signum(jlong long_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "signum", "(J)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named signum with signature (J)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,long_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::signum jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jlong Long::parseLong(jstring string_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "parseLong", "(Ljava/lang/String;)J");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named parseLong with signature (Ljava/lang/String;)J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallStaticLongMethod( cls, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::parseLong jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jlong Long::parseLong(jstring string_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "parseLong", "(Ljava/lang/String;I)J");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named parseLong with signature (Ljava/lang/String;I)J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallStaticLongMethod( cls, mid ,string_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::parseLong jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jlong Long::parseUnsignedLong(jstring string_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "parseUnsignedLong", "(Ljava/lang/String;I)J");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named parseUnsignedLong with signature (Ljava/lang/String;I)J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallStaticLongMethod( cls, mid ,string_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::parseUnsignedLong jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jlong Long::parseUnsignedLong(jstring string_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "parseUnsignedLong", "(Ljava/lang/String;)J");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named parseUnsignedLong with signature (Ljava/lang/String;)J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallStaticLongMethod( cls, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::parseUnsignedLong jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }
        static jclass getNewLongClass() {
            jclass clss = getEnv()->FindClass("java/lang/Long");
            if (NULL == clss) {
                std::cerr << " Can't find class java/lang/Long" << std::endl;
            }
            return clss;
        }
        
        static jclass LongClass = NULL;
        static inline jclass getLongClass() {
            if (LongClass != NULL) {
                return LongClass;
            }
            LongClass = getNewLongClass();
            return LongClass;
        }
        } // end namespace lang
    } // end namespace java


    // class_index = 3 clss=interface crcl.utils.CRCLCommandWrapper$CRCLCommandWrapperConsumer

    namespace crcl{
        namespace utils{
        
        // get JNI handle for class crcl.utils.CRCLCommandWrapper.CRCLCommandWrapperConsumer
        static inline jclass getCRCLCommandWrapperCRCLCommandWrapperConsumerClass();
        
        CRCLCommandWrapperCRCLCommandWrapperConsumer::CRCLCommandWrapperCRCLCommandWrapperConsumer(jobject _jthis, bool copy): ::crclj::java::lang::Object(_jthis,copy) {
                
        }
        
        CRCLCommandWrapperCRCLCommandWrapperConsumer::CRCLCommandWrapperCRCLCommandWrapperConsumer(const CRCLCommandWrapperCRCLCommandWrapperConsumer &objref): ::crclj::java::lang::Object((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class CRCLCommandWrapperCRCLCommandWrapperConsumer _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class CRCLCommandWrapperCRCLCommandWrapperConsumer jthis=",jthis);
            }
        }
        
        CRCLCommandWrapperCRCLCommandWrapperConsumer CRCLCommandWrapperCRCLCommandWrapperConsumer::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getCRCLCommandWrapperCRCLCommandWrapperConsumerClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            CRCLCommandWrapperCRCLCommandWrapperConsumer retVal(objref.jthis,true);
            return retVal;
        }
        
        bool CRCLCommandWrapperCRCLCommandWrapperConsumer::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getCRCLCommandWrapperCRCLCommandWrapperConsumerClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }

        // Destructor for crcl.utils.CRCLCommandWrapper.CRCLCommandWrapperConsumer
        CRCLCommandWrapperCRCLCommandWrapperConsumer::~CRCLCommandWrapperCRCLCommandWrapperConsumer() {
        	// Place-holder for later extensibility.
        }


        void CRCLCommandWrapperCRCLCommandWrapperConsumer::accept(const CRCLCommandWrapper & cRCLCommandWrapper_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method accept of crcl.utils.CRCLCommandWrapper.CRCLCommandWrapperConsumer with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapperCRCLCommandWrapperConsumer::accept jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "accept", "(Lcrcl/utils/CRCLCommandWrapper;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapperCRCLCommandWrapperConsumer::accept jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.utils.CRCLCommandWrapper.CRCLCommandWrapperConsumer has no method named accept with signature (Lcrcl/utils/CRCLCommandWrapper;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,cRCLCommandWrapper_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapperCRCLCommandWrapperConsumer::accept jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }
        static jclass getNewCRCLCommandWrapperCRCLCommandWrapperConsumerClass() {
            jclass clss = getEnv()->FindClass("crcl/utils/CRCLCommandWrapper$CRCLCommandWrapperConsumer");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/utils/CRCLCommandWrapper$CRCLCommandWrapperConsumer" << std::endl;
            }
            return clss;
        }
        
        static jclass CRCLCommandWrapperCRCLCommandWrapperConsumerClass = NULL;
        static inline jclass getCRCLCommandWrapperCRCLCommandWrapperConsumerClass() {
            if (CRCLCommandWrapperCRCLCommandWrapperConsumerClass != NULL) {
                return CRCLCommandWrapperCRCLCommandWrapperConsumerClass;
            }
            CRCLCommandWrapperCRCLCommandWrapperConsumerClass = getNewCRCLCommandWrapperCRCLCommandWrapperConsumerClass();
            return CRCLCommandWrapperCRCLCommandWrapperConsumerClass;
        }
        } // end namespace utils


    // class_index = 4 clss=class crcl.base.CRCLProgramType

        namespace base{
        
        // get JNI handle for class crcl.base.CRCLProgramType
        static inline jclass getCRCLProgramTypeClass();
        
        CRCLProgramType::CRCLProgramType(jobject _jthis, bool copy): DataThingType(_jthis,copy) {
                
        }
        
        CRCLProgramType::CRCLProgramType(const CRCLProgramType &objref): DataThingType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class CRCLProgramType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class CRCLProgramType jthis=",jthis);
            }
        }
        
        CRCLProgramType CRCLProgramType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getCRCLProgramTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            CRCLProgramType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool CRCLProgramType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getCRCLProgramTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        CRCLProgramType::CRCLProgramType() : DataThingType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getCRCLProgramTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class CRCLProgramType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLProgramType::accept jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new CRCLProgramType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new CRCLProgramType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.CRCLProgramType
        CRCLProgramType::~CRCLProgramType() {
        	// Place-holder for later extensibility.
        }


        ::crclj::java::util::List CRCLProgramType::getMiddleCommand() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getMiddleCommand of crcl.base.CRCLProgramType with jthis == NULL." << std::endl;
                static ::crclj::java::util::List nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLProgramType::getMiddleCommand jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getMiddleCommand", "()Ljava/util/List;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLProgramType::getMiddleCommand jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.CRCLProgramType has no method named getMiddleCommand with signature ()Ljava/util/List;." << std::endl;
                    static ::crclj::java::util::List nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLProgramType::getMiddleCommand jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            ::crclj::java::util::List retObject(retVal,false);
            return retObject;
        }
        static jclass getNewCRCLProgramTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/CRCLProgramType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/CRCLProgramType" << std::endl;
            }
            return clss;
        }
        
        static jclass CRCLProgramTypeClass = NULL;
        static inline jclass getCRCLProgramTypeClass() {
            if (CRCLProgramTypeClass != NULL) {
                return CRCLProgramTypeClass;
            }
            CRCLProgramTypeClass = getNewCRCLProgramTypeClass();
            return CRCLProgramTypeClass;
        }
        } // end namespace base
    } // end namespace crcl

    
    
        // end namespace crclj
    }
    
