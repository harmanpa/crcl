
#include <jni.h>
#include <cstdlib>
#include <iostream>
#include <string>

#include "crclj.h"

#ifdef __cplusplus
extern "C" {
#endif
 
static void registerNativeMethods(JNIEnv *);

#ifdef __cplusplus
}
#endif

namespace crclj {

    JNIEnv *getEnv();
    static bool debug_j4cpp = false;

    namespace java {
        namespace lang {

            Object::Object() {
                jthis = NULL;
            };

            Object::Object(const Object &objref) {
                jobject _jthis = objref.jthis;
                if (_jthis != NULL) {
                    jthis = getEnv()->NewGlobalRef(_jthis);
                }
            };

            Object::Object(jobject _jthis, bool copy) {
                //                std::cout << "_jthis = " << _jthis << std::endl;
                jthis = NULL;
                if (_jthis != NULL) {
                    JNIEnv *env = getEnv();
                    jobjectRefType ref0 = env->GetObjectRefType(jthis);
                    if (copy || ref0 != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(_jthis);
                    }
                }
            };

            Object & Object::operator =(const Object &otherobject) {
                jobject oldjthis = jthis;
                jobject _newjthis = otherobject.jthis;
                jobject newjthis= NULL;
                jthis= NULL;
                JNIEnv *env = getEnv();
                if (_newjthis != NULL) {
                    if(debug_j4cpp) DebugPrintJObject(__FILE__,__LINE__,"Object::operator= called for _newjthis=",_newjthis);
                    newjthis = env->NewGlobalRef(_newjthis);
                }
                if (NULL != oldjthis) {
                    if(debug_j4cpp) DebugPrintJObject(__FILE__,__LINE__,"Object::operator= called for oldjthis=",oldjthis);
                    env->DeleteGlobalRef(oldjthis);
                    oldjthis = NULL;
                }
                jthis=newjthis;
                releaseEnv(env);
                return *this;
            }
            
            Object::~Object() {
                if (NULL != jthis) {
                    if(debug_j4cpp) DebugPrintJObject(__FILE__,__LINE__,"Destructor called for ",jthis);
                    getEnv()->DeleteGlobalRef(jthis);
                    jthis = NULL;
                }
            };

            jstring Object::toString() {

                if (jthis == NULL) {
                    std::cerr << "Call of method toString of java.lang.Object with jthis == NULL." << std::endl;
                    return NULL;
                }
                JNIEnv *env = getEnv();
                jobjectRefType ref0 = env->GetObjectRefType(jthis);
                if(debug_j4cpp) DebugPrintJObject(__FILE__,__LINE__," Object::toString() jthis=",jthis);
                jclass cls = env->GetObjectClass(jthis);
                jstring retVal = NULL;
                if (cls != NULL) {
                    jmethodID mid = env->GetMethodID(cls, "toString", "()Ljava/lang/String;");
                    if (NULL == mid) {
                        std::cerr << "Class ava.lang.Object has no method named toString with signature ()Ljava/lang/String;." << std::endl;
                        return NULL;
                    } else {
                        retVal = (jstring) env->CallObjectMethod(jthis, mid);
                    }
                }
                return retVal;
            }
        }
    }



// start_segment_index = 0
// start_segment_index = 10
// segment_index = 0
// classesSegList=[class java.lang.Enum, class crcl.base.ForceUnitEnumType, class crcl.base.TorqueUnitEnumType, class crcl.base.DataThingType, class crcl.base.CRCLCommandType, class crcl.base.MiddleCommandType, class crcl.base.MessageType, class crcl.base.SensorStatusType, class crcl.base.OnOffSensorStatusType, class crcl.base.RotAccelType]


// class_index = 0 clss=class java.lang.Enum

    namespace java{
        namespace lang{
        
        // get JNI handle for class java.lang.Enum
        static inline jclass getEnumClass();
        
        Enum::Enum(jobject _jthis, bool copy): Object(_jthis,copy) {
                
        }
        
        Enum::Enum(const Enum &objref): Object((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class Enum _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class Enum jthis=",jthis);
            }
        }
        
        Enum Enum::cast(const Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getEnumClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            Enum retVal(objref.jthis,true);
            return retVal;
        }
        
        bool Enum::instanceof(const Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getEnumClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }

        // Destructor for java.lang.Enum
        Enum::~Enum() {
        	// Place-holder for later extensibility.
        }


        jstring Enum::name() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method name of java.lang.Enum with jthis == NULL." << std::endl;
                return NULL;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Enum::name jthis=",jthis);
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "name", "()Ljava/lang/String;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Enum::name jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Enum has no method named name with signature ()Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Enum::name jthrowable t=",t);
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

        jboolean Enum::equals(const Object & object_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method equals of java.lang.Enum with jthis == NULL." << std::endl;
                return false;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Enum::equals jthis=",jthis);
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "equals", "(Ljava/lang/Object;)Z");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Enum::equals jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Enum has no method named equals with signature (Ljava/lang/Object;)Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallBooleanMethod(jthis, mid ,object_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Enum::equals jthrowable t=",t);
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

        jstring Enum::toString() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method toString of java.lang.Enum with jthis == NULL." << std::endl;
                return NULL;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Enum::toString jthis=",jthis);
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "toString", "()Ljava/lang/String;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Enum::toString jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Enum has no method named toString with signature ()Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Enum::toString jthrowable t=",t);
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

        jint Enum::hashCode() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method hashCode of java.lang.Enum with jthis == NULL." << std::endl;
                return (jint) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Enum::hashCode jthis=",jthis);
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "hashCode", "()I");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Enum::hashCode jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Enum has no method named hashCode with signature ()I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallIntMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Enum::hashCode jthrowable t=",t);
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

        jint Enum::compareTo(const Enum & enum_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method compareTo of java.lang.Enum with jthis == NULL." << std::endl;
                return (jint) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Enum::compareTo jthis=",jthis);
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "compareTo", "(Ljava/lang/Enum;)I");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Enum::compareTo jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Enum has no method named compareTo with signature (Ljava/lang/Enum;)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallIntMethod(jthis, mid ,enum_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Enum::compareTo jthrowable t=",t);
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

        jint Enum::ordinal() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method ordinal of java.lang.Enum with jthis == NULL." << std::endl;
                return (jint) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Enum::ordinal jthis=",jthis);
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "ordinal", "()I");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Enum::ordinal jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Enum has no method named ordinal with signature ()I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallIntMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Enum::ordinal jthrowable t=",t);
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
        static jclass getNewEnumClass() {
            jclass clss = getEnv()->FindClass("java/lang/Enum");
            if (NULL == clss) {
                std::cerr << " Can't find class java/lang/Enum" << std::endl;
            }
            return clss;
        }
        
        static jclass EnumClass = NULL;
        static inline jclass getEnumClass() {
            if (EnumClass != NULL) {
                return EnumClass;
            }
            EnumClass = getNewEnumClass();
            return EnumClass;
        }
        } // end namespace lang
    } // end namespace java


    // class_index = 1 clss=class crcl.base.ForceUnitEnumType

    namespace crcl{
        namespace base{
        
        // get JNI handle for class crcl.base.ForceUnitEnumType
        static inline jclass getForceUnitEnumTypeClass();
        
        ForceUnitEnumType::ForceUnitEnumType(jobject _jthis, bool copy): ::crclj::java::lang::Enum(_jthis,copy) {
                
        }
        
        ForceUnitEnumType::ForceUnitEnumType(const ForceUnitEnumType &objref): ::crclj::java::lang::Enum((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class ForceUnitEnumType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class ForceUnitEnumType jthis=",jthis);
            }
        }
        
        ForceUnitEnumType ForceUnitEnumType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getForceUnitEnumTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            ForceUnitEnumType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool ForceUnitEnumType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getForceUnitEnumTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        ForceUnitEnumType::ForceUnitEnumType() : ::crclj::java::lang::Enum((jobject)NULL,false) {
        JNIEnv *env =getEnv();
        static jclass cls = getForceUnitEnumTypeClass();
        if (cls != NULL) {
            static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
            if (NULL == mid) {
                std::cerr << "Class ForceUnitEnumType has no method constructor signature ()V" << std::endl;
            } else {
                jthis = env->NewObject(cls, mid );
                jthrowable t = env->ExceptionOccurred();
                if(t != NULL) {
                    if(GetDebugJ4Cpp()) {
                        DebugPrintJObject(__FILE__,__LINE__," ForceUnitEnumType::ordinal jthis=",t);
                        env->ExceptionDescribe();
                    }
                    throw t;
                }
                if(jthis == NULL) {
                    std::cerr << "Call to create new ForceUnitEnumType with signature ()V returned null." << std::endl;
                    releaseEnv(env);
                    return;
                }
                jobjectRefType ref = env->GetObjectRefType(jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new ForceUnitEnumType jthis=",jthis);
                if(ref != JNIGlobalRefType) {
                    jthis = env->NewGlobalRef(jthis);
                }
            }
        }
        releaseEnv(env);
        }


        // Destructor for crcl.base.ForceUnitEnumType
        ForceUnitEnumType::~ForceUnitEnumType() {
        	// Place-holder for later extensibility.
        }


        // Field getter for NEWTON
        ForceUnitEnumType ForceUnitEnumType::getNEWTON() {
        JNIEnv *env =getEnv();
        static jclass cls = getForceUnitEnumTypeClass();
        jobject retVal=NULL;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "NEWTON", "Lcrcl/base/ForceUnitEnumType;");
            if (NULL == fid) {
                std::cerr << "Class crcl.base.ForceUnitEnumType has no field named NEWTON with signature Lcrcl/base/ForceUnitEnumType;." << std::endl;
                static ForceUnitEnumType nullObject((jobject)NULL,false); return nullObject;
            } else {
                retVal= (jobject)  env->GetStaticObjectField( cls, fid );
            }
        }
        releaseEnv(env);
                    
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            ForceUnitEnumType retObject(retVal,false);
            return retObject;
        }

        // Field getter for POUND
        ForceUnitEnumType ForceUnitEnumType::getPOUND() {
        JNIEnv *env =getEnv();
        static jclass cls = getForceUnitEnumTypeClass();
        jobject retVal=NULL;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "POUND", "Lcrcl/base/ForceUnitEnumType;");
            if (NULL == fid) {
                std::cerr << "Class crcl.base.ForceUnitEnumType has no field named POUND with signature Lcrcl/base/ForceUnitEnumType;." << std::endl;
                static ForceUnitEnumType nullObject((jobject)NULL,false); return nullObject;
            } else {
                retVal= (jobject)  env->GetStaticObjectField( cls, fid );
            }
        }
        releaseEnv(env);
                    
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            ForceUnitEnumType retObject(retVal,false);
            return retObject;
        }

        // Field getter for OUNCE
        ForceUnitEnumType ForceUnitEnumType::getOUNCE() {
        JNIEnv *env =getEnv();
        static jclass cls = getForceUnitEnumTypeClass();
        jobject retVal=NULL;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "OUNCE", "Lcrcl/base/ForceUnitEnumType;");
            if (NULL == fid) {
                std::cerr << "Class crcl.base.ForceUnitEnumType has no field named OUNCE with signature Lcrcl/base/ForceUnitEnumType;." << std::endl;
                static ForceUnitEnumType nullObject((jobject)NULL,false); return nullObject;
            } else {
                retVal= (jobject)  env->GetStaticObjectField( cls, fid );
            }
        }
        releaseEnv(env);
                    
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            ForceUnitEnumType retObject(retVal,false);
            return retObject;
        }

        jstring ForceUnitEnumType::value() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method value of crcl.base.ForceUnitEnumType with jthis == NULL." << std::endl;
                return NULL;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ForceUnitEnumType::value jthis=",jthis);
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "value", "()Ljava/lang/String;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ForceUnitEnumType::value jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ForceUnitEnumType has no method named value with signature ()Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ForceUnitEnumType::value jthrowable t=",t);
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

        jobjectArray ForceUnitEnumType::values() {
            JNIEnv *env =getEnv();
            static jclass cls = getForceUnitEnumTypeClass();
            jobjectArray retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "values", "()[Lcrcl/base/ForceUnitEnumType;");
                if (NULL == mid) {
                    std::cerr << "Class crcl.base.ForceUnitEnumType has no method named values with signature ()[Lcrcl/base/ForceUnitEnumType;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jobjectArray)  env->CallStaticObjectMethod( cls, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," ForceUnitEnumType::values jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        ForceUnitEnumType ForceUnitEnumType::valueOf(jstring string_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getForceUnitEnumTypeClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "valueOf", "(Ljava/lang/String;)Lcrcl/base/ForceUnitEnumType;");
                if (NULL == mid) {
                    std::cerr << "Class crcl.base.ForceUnitEnumType has no method named valueOf with signature (Ljava/lang/String;)Lcrcl/base/ForceUnitEnumType;." << std::endl;
                    static ForceUnitEnumType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," ForceUnitEnumType::valueOf jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            ForceUnitEnumType retObject(retVal,false);
            return retObject;
        }

        ForceUnitEnumType ForceUnitEnumType::fromValue(jstring string_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getForceUnitEnumTypeClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "fromValue", "(Ljava/lang/String;)Lcrcl/base/ForceUnitEnumType;");
                if (NULL == mid) {
                    std::cerr << "Class crcl.base.ForceUnitEnumType has no method named fromValue with signature (Ljava/lang/String;)Lcrcl/base/ForceUnitEnumType;." << std::endl;
                    static ForceUnitEnumType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," ForceUnitEnumType::fromValue jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            ForceUnitEnumType retObject(retVal,false);
            return retObject;
        }
        static jclass getNewForceUnitEnumTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/ForceUnitEnumType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/ForceUnitEnumType" << std::endl;
            }
            return clss;
        }
        
        static jclass ForceUnitEnumTypeClass = NULL;
        static inline jclass getForceUnitEnumTypeClass() {
            if (ForceUnitEnumTypeClass != NULL) {
                return ForceUnitEnumTypeClass;
            }
            ForceUnitEnumTypeClass = getNewForceUnitEnumTypeClass();
            return ForceUnitEnumTypeClass;
        }

    // class_index = 2 clss=class crcl.base.TorqueUnitEnumType

        
        // get JNI handle for class crcl.base.TorqueUnitEnumType
        static inline jclass getTorqueUnitEnumTypeClass();
        
        TorqueUnitEnumType::TorqueUnitEnumType(jobject _jthis, bool copy): ::crclj::java::lang::Enum(_jthis,copy) {
                
        }
        
        TorqueUnitEnumType::TorqueUnitEnumType(const TorqueUnitEnumType &objref): ::crclj::java::lang::Enum((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class TorqueUnitEnumType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class TorqueUnitEnumType jthis=",jthis);
            }
        }
        
        TorqueUnitEnumType TorqueUnitEnumType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getTorqueUnitEnumTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            TorqueUnitEnumType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool TorqueUnitEnumType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getTorqueUnitEnumTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        TorqueUnitEnumType::TorqueUnitEnumType() : ::crclj::java::lang::Enum((jobject)NULL,false) {
        JNIEnv *env =getEnv();
        static jclass cls = getTorqueUnitEnumTypeClass();
        if (cls != NULL) {
            static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
            if (NULL == mid) {
                std::cerr << "Class TorqueUnitEnumType has no method constructor signature ()V" << std::endl;
            } else {
                jthis = env->NewObject(cls, mid );
                jthrowable t = env->ExceptionOccurred();
                if(t != NULL) {
                    if(GetDebugJ4Cpp()) {
                        DebugPrintJObject(__FILE__,__LINE__," TorqueUnitEnumType::fromValue jthis=",t);
                        env->ExceptionDescribe();
                    }
                    throw t;
                }
                if(jthis == NULL) {
                    std::cerr << "Call to create new TorqueUnitEnumType with signature ()V returned null." << std::endl;
                    releaseEnv(env);
                    return;
                }
                jobjectRefType ref = env->GetObjectRefType(jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new TorqueUnitEnumType jthis=",jthis);
                if(ref != JNIGlobalRefType) {
                    jthis = env->NewGlobalRef(jthis);
                }
            }
        }
        releaseEnv(env);
        }


        // Destructor for crcl.base.TorqueUnitEnumType
        TorqueUnitEnumType::~TorqueUnitEnumType() {
        	// Place-holder for later extensibility.
        }


        // Field getter for NEWTON_METER
        TorqueUnitEnumType TorqueUnitEnumType::getNEWTON_METER() {
        JNIEnv *env =getEnv();
        static jclass cls = getTorqueUnitEnumTypeClass();
        jobject retVal=NULL;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "NEWTON_METER", "Lcrcl/base/TorqueUnitEnumType;");
            if (NULL == fid) {
                std::cerr << "Class crcl.base.TorqueUnitEnumType has no field named NEWTON_METER with signature Lcrcl/base/TorqueUnitEnumType;." << std::endl;
                static TorqueUnitEnumType nullObject((jobject)NULL,false); return nullObject;
            } else {
                retVal= (jobject)  env->GetStaticObjectField( cls, fid );
            }
        }
        releaseEnv(env);
                    
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            TorqueUnitEnumType retObject(retVal,false);
            return retObject;
        }

        // Field getter for FOOT_POUND
        TorqueUnitEnumType TorqueUnitEnumType::getFOOT_POUND() {
        JNIEnv *env =getEnv();
        static jclass cls = getTorqueUnitEnumTypeClass();
        jobject retVal=NULL;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "FOOT_POUND", "Lcrcl/base/TorqueUnitEnumType;");
            if (NULL == fid) {
                std::cerr << "Class crcl.base.TorqueUnitEnumType has no field named FOOT_POUND with signature Lcrcl/base/TorqueUnitEnumType;." << std::endl;
                static TorqueUnitEnumType nullObject((jobject)NULL,false); return nullObject;
            } else {
                retVal= (jobject)  env->GetStaticObjectField( cls, fid );
            }
        }
        releaseEnv(env);
                    
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            TorqueUnitEnumType retObject(retVal,false);
            return retObject;
        }

        jstring TorqueUnitEnumType::value() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method value of crcl.base.TorqueUnitEnumType with jthis == NULL." << std::endl;
                return NULL;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," TorqueUnitEnumType::value jthis=",jthis);
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "value", "()Ljava/lang/String;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," TorqueUnitEnumType::value jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.TorqueUnitEnumType has no method named value with signature ()Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," TorqueUnitEnumType::value jthrowable t=",t);
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

        jobjectArray TorqueUnitEnumType::values() {
            JNIEnv *env =getEnv();
            static jclass cls = getTorqueUnitEnumTypeClass();
            jobjectArray retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "values", "()[Lcrcl/base/TorqueUnitEnumType;");
                if (NULL == mid) {
                    std::cerr << "Class crcl.base.TorqueUnitEnumType has no method named values with signature ()[Lcrcl/base/TorqueUnitEnumType;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jobjectArray)  env->CallStaticObjectMethod( cls, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," TorqueUnitEnumType::values jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        TorqueUnitEnumType TorqueUnitEnumType::valueOf(jstring string_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getTorqueUnitEnumTypeClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "valueOf", "(Ljava/lang/String;)Lcrcl/base/TorqueUnitEnumType;");
                if (NULL == mid) {
                    std::cerr << "Class crcl.base.TorqueUnitEnumType has no method named valueOf with signature (Ljava/lang/String;)Lcrcl/base/TorqueUnitEnumType;." << std::endl;
                    static TorqueUnitEnumType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," TorqueUnitEnumType::valueOf jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            TorqueUnitEnumType retObject(retVal,false);
            return retObject;
        }

        TorqueUnitEnumType TorqueUnitEnumType::fromValue(jstring string_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getTorqueUnitEnumTypeClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "fromValue", "(Ljava/lang/String;)Lcrcl/base/TorqueUnitEnumType;");
                if (NULL == mid) {
                    std::cerr << "Class crcl.base.TorqueUnitEnumType has no method named fromValue with signature (Ljava/lang/String;)Lcrcl/base/TorqueUnitEnumType;." << std::endl;
                    static TorqueUnitEnumType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," TorqueUnitEnumType::fromValue jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            TorqueUnitEnumType retObject(retVal,false);
            return retObject;
        }
        static jclass getNewTorqueUnitEnumTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/TorqueUnitEnumType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/TorqueUnitEnumType" << std::endl;
            }
            return clss;
        }
        
        static jclass TorqueUnitEnumTypeClass = NULL;
        static inline jclass getTorqueUnitEnumTypeClass() {
            if (TorqueUnitEnumTypeClass != NULL) {
                return TorqueUnitEnumTypeClass;
            }
            TorqueUnitEnumTypeClass = getNewTorqueUnitEnumTypeClass();
            return TorqueUnitEnumTypeClass;
        }

    // class_index = 3 clss=class crcl.base.DataThingType

        
        // get JNI handle for class crcl.base.DataThingType
        static inline jclass getDataThingTypeClass();
        
        DataThingType::DataThingType(jobject _jthis, bool copy): ::crclj::java::lang::Object(_jthis,copy) {
                
        }
        
        DataThingType::DataThingType(const DataThingType &objref): ::crclj::java::lang::Object((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class DataThingType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class DataThingType jthis=",jthis);
            }
        }
        
        DataThingType DataThingType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getDataThingTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            DataThingType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool DataThingType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getDataThingTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        DataThingType::DataThingType() : ::crclj::java::lang::Object((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getDataThingTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class DataThingType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," DataThingType::fromValue jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new DataThingType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new DataThingType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.DataThingType
        DataThingType::~DataThingType() {
        	// Place-holder for later extensibility.
        }


        jstring DataThingType::getName() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getName of crcl.base.DataThingType with jthis == NULL." << std::endl;
                return NULL;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," DataThingType::getName jthis=",jthis);
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getName", "()Ljava/lang/String;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," DataThingType::getName jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.DataThingType has no method named getName with signature ()Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," DataThingType::getName jthrowable t=",t);
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

        void DataThingType::setName(jstring string_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setName of crcl.base.DataThingType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," DataThingType::setName jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setName", "(Ljava/lang/String;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," DataThingType::setName jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.DataThingType has no method named setName with signature (Ljava/lang/String;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," DataThingType::setName jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        // Easy call alternative for setName
        void DataThingType::setName(const char * easyArg_0) {
            // convenience method, converts to/from JNI types to common C++ types.
            if(jthis == NULL) {
                std::cerr << "Call of method setName of crcl.base.DataThingType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," DataThingType::setName jthis=",jthis);
            
                    
            jstring string_0 = env->NewStringUTF(easyArg_0);
            setName(string_0);
            jobjectRefType ref_0 = env->GetObjectRefType(string_0);
            if(ref_0 == JNIGlobalRefType) {
                env->DeleteGlobalRef(string_0);
            }
            
            releaseEnv(env);
        }

        static jclass getNewDataThingTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/DataThingType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/DataThingType" << std::endl;
            }
            return clss;
        }
        
        static jclass DataThingTypeClass = NULL;
        static inline jclass getDataThingTypeClass() {
            if (DataThingTypeClass != NULL) {
                return DataThingTypeClass;
            }
            DataThingTypeClass = getNewDataThingTypeClass();
            return DataThingTypeClass;
        }

    // class_index = 4 clss=class crcl.base.CRCLCommandType

        
        // get JNI handle for class crcl.base.CRCLCommandType
        static inline jclass getCRCLCommandTypeClass();
        
        CRCLCommandType::CRCLCommandType(jobject _jthis, bool copy): DataThingType(_jthis,copy) {
                
        }
        
        CRCLCommandType::CRCLCommandType(const CRCLCommandType &objref): DataThingType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class CRCLCommandType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class CRCLCommandType jthis=",jthis);
            }
        }
        
        CRCLCommandType CRCLCommandType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getCRCLCommandTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            CRCLCommandType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool CRCLCommandType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getCRCLCommandTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        CRCLCommandType::CRCLCommandType() : DataThingType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getCRCLCommandTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class CRCLCommandType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLCommandType::setName jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new CRCLCommandType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new CRCLCommandType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.CRCLCommandType
        CRCLCommandType::~CRCLCommandType() {
        	// Place-holder for later extensibility.
        }


        jlong CRCLCommandType::getCommandID() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getCommandID of crcl.base.CRCLCommandType with jthis == NULL." << std::endl;
                return (jlong) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLCommandType::getCommandID jthis=",jthis);
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getCommandID", "()J");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLCommandType::getCommandID jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.CRCLCommandType has no method named getCommandID with signature ()J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallLongMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLCommandType::getCommandID jthrowable t=",t);
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

        void CRCLCommandType::setCommandID(jlong long_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setCommandID of crcl.base.CRCLCommandType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLCommandType::setCommandID jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setCommandID", "(J)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLCommandType::setCommandID jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.CRCLCommandType has no method named setCommandID with signature (J)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,long_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLCommandType::setCommandID jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        ::crclj::java::util::List CRCLCommandType::getGuard() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getGuard of crcl.base.CRCLCommandType with jthis == NULL." << std::endl;
                static ::crclj::java::util::List nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLCommandType::getGuard jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getGuard", "()Ljava/util/List;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLCommandType::getGuard jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.CRCLCommandType has no method named getGuard with signature ()Ljava/util/List;." << std::endl;
                    static ::crclj::java::util::List nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLCommandType::getGuard jthrowable t=",t);
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
        static jclass getNewCRCLCommandTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/CRCLCommandType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/CRCLCommandType" << std::endl;
            }
            return clss;
        }
        
        static jclass CRCLCommandTypeClass = NULL;
        static inline jclass getCRCLCommandTypeClass() {
            if (CRCLCommandTypeClass != NULL) {
                return CRCLCommandTypeClass;
            }
            CRCLCommandTypeClass = getNewCRCLCommandTypeClass();
            return CRCLCommandTypeClass;
        }

    // class_index = 5 clss=class crcl.base.MiddleCommandType

        
        // get JNI handle for class crcl.base.MiddleCommandType
        static inline jclass getMiddleCommandTypeClass();
        
        MiddleCommandType::MiddleCommandType(jobject _jthis, bool copy): CRCLCommandType(_jthis,copy) {
                
        }
        
        MiddleCommandType::MiddleCommandType(const MiddleCommandType &objref): CRCLCommandType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class MiddleCommandType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class MiddleCommandType jthis=",jthis);
            }
        }
        
        MiddleCommandType MiddleCommandType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getMiddleCommandTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            MiddleCommandType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool MiddleCommandType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getMiddleCommandTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        MiddleCommandType::MiddleCommandType() : CRCLCommandType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getMiddleCommandTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class MiddleCommandType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," MiddleCommandType::getGuard jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new MiddleCommandType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new MiddleCommandType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.MiddleCommandType
        MiddleCommandType::~MiddleCommandType() {
        	// Place-holder for later extensibility.
        }

        static jclass getNewMiddleCommandTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/MiddleCommandType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/MiddleCommandType" << std::endl;
            }
            return clss;
        }
        
        static jclass MiddleCommandTypeClass = NULL;
        static inline jclass getMiddleCommandTypeClass() {
            if (MiddleCommandTypeClass != NULL) {
                return MiddleCommandTypeClass;
            }
            MiddleCommandTypeClass = getNewMiddleCommandTypeClass();
            return MiddleCommandTypeClass;
        }

    // class_index = 6 clss=class crcl.base.MessageType

        
        // get JNI handle for class crcl.base.MessageType
        static inline jclass getMessageTypeClass();
        
        MessageType::MessageType(jobject _jthis, bool copy): MiddleCommandType(_jthis,copy) {
                
        }
        
        MessageType::MessageType(const MessageType &objref): MiddleCommandType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class MessageType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class MessageType jthis=",jthis);
            }
        }
        
        MessageType MessageType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getMessageTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            MessageType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool MessageType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getMessageTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        MessageType::MessageType() : MiddleCommandType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getMessageTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class MessageType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," MessageType::getGuard jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new MessageType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new MessageType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.MessageType
        MessageType::~MessageType() {
        	// Place-holder for later extensibility.
        }


        jstring MessageType::getMessage() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getMessage of crcl.base.MessageType with jthis == NULL." << std::endl;
                return NULL;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," MessageType::getMessage jthis=",jthis);
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getMessage", "()Ljava/lang/String;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," MessageType::getMessage jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.MessageType has no method named getMessage with signature ()Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," MessageType::getMessage jthrowable t=",t);
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

        void MessageType::setMessage(jstring string_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setMessage of crcl.base.MessageType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," MessageType::setMessage jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setMessage", "(Ljava/lang/String;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," MessageType::setMessage jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.MessageType has no method named setMessage with signature (Ljava/lang/String;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," MessageType::setMessage jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        // Easy call alternative for setMessage
        void MessageType::setMessage(const char * easyArg_0) {
            // convenience method, converts to/from JNI types to common C++ types.
            if(jthis == NULL) {
                std::cerr << "Call of method setMessage of crcl.base.MessageType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," MessageType::setMessage jthis=",jthis);
            
                    
            jstring string_0 = env->NewStringUTF(easyArg_0);
            setMessage(string_0);
            jobjectRefType ref_0 = env->GetObjectRefType(string_0);
            if(ref_0 == JNIGlobalRefType) {
                env->DeleteGlobalRef(string_0);
            }
            
            releaseEnv(env);
        }

        static jclass getNewMessageTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/MessageType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/MessageType" << std::endl;
            }
            return clss;
        }
        
        static jclass MessageTypeClass = NULL;
        static inline jclass getMessageTypeClass() {
            if (MessageTypeClass != NULL) {
                return MessageTypeClass;
            }
            MessageTypeClass = getNewMessageTypeClass();
            return MessageTypeClass;
        }

    // class_index = 7 clss=class crcl.base.SensorStatusType

        
        // get JNI handle for class crcl.base.SensorStatusType
        static inline jclass getSensorStatusTypeClass();
        
        SensorStatusType::SensorStatusType(jobject _jthis, bool copy): DataThingType(_jthis,copy) {
                
        }
        
        SensorStatusType::SensorStatusType(const SensorStatusType &objref): DataThingType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class SensorStatusType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class SensorStatusType jthis=",jthis);
            }
        }
        
        SensorStatusType SensorStatusType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getSensorStatusTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            SensorStatusType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool SensorStatusType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getSensorStatusTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        SensorStatusType::SensorStatusType() : DataThingType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getSensorStatusTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class SensorStatusType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," SensorStatusType::setMessage jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new SensorStatusType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new SensorStatusType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.SensorStatusType
        SensorStatusType::~SensorStatusType() {
        	// Place-holder for later extensibility.
        }


        jstring SensorStatusType::getSensorID() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getSensorID of crcl.base.SensorStatusType with jthis == NULL." << std::endl;
                return NULL;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," SensorStatusType::getSensorID jthis=",jthis);
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getSensorID", "()Ljava/lang/String;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," SensorStatusType::getSensorID jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.SensorStatusType has no method named getSensorID with signature ()Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," SensorStatusType::getSensorID jthrowable t=",t);
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

        void SensorStatusType::setSensorID(jstring string_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setSensorID of crcl.base.SensorStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," SensorStatusType::setSensorID jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setSensorID", "(Ljava/lang/String;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," SensorStatusType::setSensorID jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.SensorStatusType has no method named setSensorID with signature (Ljava/lang/String;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," SensorStatusType::setSensorID jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        // Easy call alternative for setSensorID
        void SensorStatusType::setSensorID(const char * easyArg_0) {
            // convenience method, converts to/from JNI types to common C++ types.
            if(jthis == NULL) {
                std::cerr << "Call of method setSensorID of crcl.base.SensorStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," SensorStatusType::setSensorID jthis=",jthis);
            
                    
            jstring string_0 = env->NewStringUTF(easyArg_0);
            setSensorID(string_0);
            jobjectRefType ref_0 = env->GetObjectRefType(string_0);
            if(ref_0 == JNIGlobalRefType) {
                env->DeleteGlobalRef(string_0);
            }
            
            releaseEnv(env);
        }


        jint SensorStatusType::getReadCount() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getReadCount of crcl.base.SensorStatusType with jthis == NULL." << std::endl;
                return (jint) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," SensorStatusType::getReadCount jthis=",jthis);
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getReadCount", "()I");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," SensorStatusType::getReadCount jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.SensorStatusType has no method named getReadCount with signature ()I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallIntMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," SensorStatusType::getReadCount jthrowable t=",t);
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

        void SensorStatusType::setReadCount(jint int_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setReadCount of crcl.base.SensorStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," SensorStatusType::setReadCount jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setReadCount", "(I)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," SensorStatusType::setReadCount jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.SensorStatusType has no method named setReadCount with signature (I)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,int_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," SensorStatusType::setReadCount jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        jlong SensorStatusType::getLastReadTime() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getLastReadTime of crcl.base.SensorStatusType with jthis == NULL." << std::endl;
                return (jlong) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," SensorStatusType::getLastReadTime jthis=",jthis);
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getLastReadTime", "()J");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," SensorStatusType::getLastReadTime jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.SensorStatusType has no method named getLastReadTime with signature ()J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallLongMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," SensorStatusType::getLastReadTime jthrowable t=",t);
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

        void SensorStatusType::setLastReadTime(jlong long_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setLastReadTime of crcl.base.SensorStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," SensorStatusType::setLastReadTime jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setLastReadTime", "(J)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," SensorStatusType::setLastReadTime jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.SensorStatusType has no method named setLastReadTime with signature (J)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,long_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," SensorStatusType::setLastReadTime jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        ::crclj::java::util::List SensorStatusType::getSensorParameterSetting() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getSensorParameterSetting of crcl.base.SensorStatusType with jthis == NULL." << std::endl;
                static ::crclj::java::util::List nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," SensorStatusType::getSensorParameterSetting jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getSensorParameterSetting", "()Ljava/util/List;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," SensorStatusType::getSensorParameterSetting jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.SensorStatusType has no method named getSensorParameterSetting with signature ()Ljava/util/List;." << std::endl;
                    static ::crclj::java::util::List nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," SensorStatusType::getSensorParameterSetting jthrowable t=",t);
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
        static jclass getNewSensorStatusTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/SensorStatusType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/SensorStatusType" << std::endl;
            }
            return clss;
        }
        
        static jclass SensorStatusTypeClass = NULL;
        static inline jclass getSensorStatusTypeClass() {
            if (SensorStatusTypeClass != NULL) {
                return SensorStatusTypeClass;
            }
            SensorStatusTypeClass = getNewSensorStatusTypeClass();
            return SensorStatusTypeClass;
        }

    // class_index = 8 clss=class crcl.base.OnOffSensorStatusType

        
        // get JNI handle for class crcl.base.OnOffSensorStatusType
        static inline jclass getOnOffSensorStatusTypeClass();
        
        OnOffSensorStatusType::OnOffSensorStatusType(jobject _jthis, bool copy): SensorStatusType(_jthis,copy) {
                
        }
        
        OnOffSensorStatusType::OnOffSensorStatusType(const OnOffSensorStatusType &objref): SensorStatusType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class OnOffSensorStatusType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class OnOffSensorStatusType jthis=",jthis);
            }
        }
        
        OnOffSensorStatusType OnOffSensorStatusType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getOnOffSensorStatusTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            OnOffSensorStatusType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool OnOffSensorStatusType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getOnOffSensorStatusTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        OnOffSensorStatusType::OnOffSensorStatusType() : SensorStatusType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getOnOffSensorStatusTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class OnOffSensorStatusType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," OnOffSensorStatusType::getSensorParameterSetting jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new OnOffSensorStatusType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new OnOffSensorStatusType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.OnOffSensorStatusType
        OnOffSensorStatusType::~OnOffSensorStatusType() {
        	// Place-holder for later extensibility.
        }


        jboolean OnOffSensorStatusType::isOn() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method isOn of crcl.base.OnOffSensorStatusType with jthis == NULL." << std::endl;
                return false;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," OnOffSensorStatusType::isOn jthis=",jthis);
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "isOn", "()Z");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," OnOffSensorStatusType::isOn jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.OnOffSensorStatusType has no method named isOn with signature ()Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallBooleanMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," OnOffSensorStatusType::isOn jthrowable t=",t);
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

        void OnOffSensorStatusType::setOn(jboolean boolean_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setOn of crcl.base.OnOffSensorStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," OnOffSensorStatusType::setOn jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setOn", "(Z)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," OnOffSensorStatusType::setOn jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.OnOffSensorStatusType has no method named setOn with signature (Z)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,boolean_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," OnOffSensorStatusType::setOn jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }
        static jclass getNewOnOffSensorStatusTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/OnOffSensorStatusType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/OnOffSensorStatusType" << std::endl;
            }
            return clss;
        }
        
        static jclass OnOffSensorStatusTypeClass = NULL;
        static inline jclass getOnOffSensorStatusTypeClass() {
            if (OnOffSensorStatusTypeClass != NULL) {
                return OnOffSensorStatusTypeClass;
            }
            OnOffSensorStatusTypeClass = getNewOnOffSensorStatusTypeClass();
            return OnOffSensorStatusTypeClass;
        }

    // class_index = 9 clss=class crcl.base.RotAccelType

        
        // get JNI handle for class crcl.base.RotAccelType
        static inline jclass getRotAccelTypeClass();
        
        RotAccelType::RotAccelType(jobject _jthis, bool copy): DataThingType(_jthis,copy) {
                
        }
        
        RotAccelType::RotAccelType(const RotAccelType &objref): DataThingType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class RotAccelType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class RotAccelType jthis=",jthis);
            }
        }
        
        RotAccelType RotAccelType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getRotAccelTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            RotAccelType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool RotAccelType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getRotAccelTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        RotAccelType::RotAccelType() : DataThingType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getRotAccelTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class RotAccelType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," RotAccelType::setOn jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new RotAccelType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new RotAccelType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.RotAccelType
        RotAccelType::~RotAccelType() {
        	// Place-holder for later extensibility.
        }

        static jclass getNewRotAccelTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/RotAccelType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/RotAccelType" << std::endl;
            }
            return clss;
        }
        
        static jclass RotAccelTypeClass = NULL;
        static inline jclass getRotAccelTypeClass() {
            if (RotAccelTypeClass != NULL) {
                return RotAccelTypeClass;
            }
            RotAccelTypeClass = getNewRotAccelTypeClass();
            return RotAccelTypeClass;
        }
        } // end namespace base
    } // end namespace crcl

    
    
    static JavaVM *jvm = NULL; /* denotes a Java VM */
    
        static JNIEnv *getNewEnv() {
            
            JNIEnv *env; /* pointer to native method interface */
            JavaVM * jvmBuf[1];
            jsize nVMs;
            char *debugJ4CppEnv = NULL;
            char *classPathEnv = NULL;
            char *jvmOptionsEnv = NULL;
    #if defined(_WIN32) || defined(_WIN64)
            errno_t errno_val;
            size_t requiredSize;
            errno_val =  getenv_s( &requiredSize, NULL, 0, "DEBUG_J4CPP");
            if(requiredSize > 0) {
                debugJ4CppEnv = (char *) malloc(requiredSize);
                errno_val =  getenv_s( &requiredSize, debugJ4CppEnv, requiredSize, "DEBUG_J4CPP");
            }
            errno_val =  getenv_s( &requiredSize, NULL, 0, "CLASSPATH");
            if(requiredSize > 0) {
                classPathEnv = (char *) malloc(requiredSize);
                errno_val =  getenv_s( &requiredSize, classPathEnv, requiredSize, "CLASSPATH");
            }
            errno_val =  getenv_s( &requiredSize, NULL, 0, "JVM_OPTIONS");
            if(requiredSize > 0) {
                jvmOptionsEnv = (char *) malloc(requiredSize);
                errno_val =  getenv_s( &requiredSize, jvmOptionsEnv, requiredSize, "JVM_OPTIONS");
            }
    #else 
            debugJ4CppEnv = getenv("DEBUG_J4CPP");
            classPathEnv = getenv("CLASSPATH");
            jvmOptionsEnv = getenv("JVM_OPTIONS");
    #endif
            debug_j4cpp = debug_j4cpp || (debugJ4CppEnv != NULL && debugJ4CppEnv[0] != 0);
            std::string str;
            str += "/home/shackle/crcl/tools/java/crcl4java/crcl4java-utils/src/main/java:/home/shackle/crcl/tools/java/crcl4java/crcl4java-base/target/crcl4java-base-1.9.1-SNAPSHOT.jar";
            if (classPathEnv != NULL) {
                std::string classPathEnvStr(classPathEnv);
                if (debug_j4cpp) std::cout << "classPathEnv=" << classPathEnvStr << std::endl;
                str += ":";
                str += classPathEnvStr;
            }
            if (debug_j4cpp) std::cout << "str=" << str << std::endl;
    #if defined(_WIN32) || defined(_WIN64)
            _putenv_s("CLASSPATH", str.c_str());
    #else
            setenv("CLASSPATH", str.c_str(), 1);
    #endif
            std::string optsString;
            optsString += "-Djava.class.path=";
            optsString += str;
            if (jvmOptionsEnv != NULL) {
                std::string jvmOptionsEnvStr(jvmOptionsEnv);
                if (debug_j4cpp) std::cout << "jvmOptionsEnvStr=" << jvmOptionsEnvStr << std::endl;
                optsString += " ";
                optsString += jvmOptionsEnvStr;
            }
            if (debug_j4cpp) std::cout << "optsString=" << optsString << std::endl;
            jint v = JNI_GetCreatedJavaVMs(jvmBuf, 1, &nVMs);
            if (nVMs > 0) {
                jvm = jvmBuf[0];
                jvm->GetEnv((void **) &env, JNI_VERSION_1_6);
                return env;
            }
            JavaVMInitArgs vm_args; /* JDK/JRE 6 VM initialization arguments */
            JavaVMOption* options = new JavaVMOption[1];
            options[0].optionString = (char *) optsString.c_str();
            vm_args.version = JNI_VERSION_1_6;
            vm_args.nOptions = NULL != options[0].optionString ? 1 : 0;
            vm_args.options = options;
            vm_args.ignoreUnrecognized = false;
            /* load and initialize a Java VM, return a JNI interface
             * pointer in env */
            JNI_CreateJavaVM(&jvm,
                    ((void **) (&env)),
                    ((void *) (&vm_args)));
            delete options;
    #if defined(_WIN32) || defined(_WIN64)
            if(debugJ4CppEnv != NULL) {
                free(debugJ4CppEnv);
                debugJ4CppEnv = NULL;
            }
            if(classPathEnv != NULL) {
                free(classPathEnv);
                classPathEnv = NULL;
            }
            if(jvmOptionsEnv != NULL) {
                free(jvmOptionsEnv);
                jvmOptionsEnv = NULL;
            }
    #endif
            registerNativeMethods(env);
    //        static JNINativeMethod methods[1];
    //        jclass loaderclass = env->FindClass("java/lang/ClassLoader");
    //        std::cout << "loaderclass = " << loaderclass << std::endl;
    //        jmethodID mid = env->GetStaticMethodID(loaderclass,"getSystemClassLoader","()Ljava/lang/ClassLoader;");
    //        std::cout << "mid = " << mid << std::endl;
    //        jobject loader = env->CallStaticObjectMethod(loaderclass,mid);
    //        std::cout << "loader = " << loader << std::endl;
    //        jclass rn_clss = env->FindClass("NativeRunnable");
    //        std::cout << "rn_clss = " << rn_clss << std::endl;
    //        if(NULL != rn_clss) {
    //            methods[0].name = (char *) "run";
    //            methods[0].signature = (char *) "()V";
    //            methods[0].fnPtr = (void *) Java_NativeRunnable_run;
    //            jint rn_ret = env->RegisterNatives(rn_clss,methods,1);
    //            std::cout << "rn_ret = " << rn_ret << std::endl;
    //        }
            return env;
        }
    
        static JNIEnv *env = NULL;
    
        JNIEnv *getEnv() {
            if (env != NULL && jvm != NULL) {
                JNIEnv *env2=env;
                jint attach_ret = jvm->AttachCurrentThread((void **)&env2,NULL);
                if(attach_ret != JNI_OK) {
                    std::cerr << "JNI AttachCurrentThread failed returning " << attach_ret << std::endl;
                    return NULL;
                }
                return env2;
            }
            env = getNewEnv();
            return env;
        }
    
        void releaseEnv(JNIEnv *env) {
            // Maybe implement this later, 
            // This will probably cause a memory leak.
            // Don't like leaks? Why the hell are using C++?
        }
    
        static jclass getNewStringClass() {
            jclass clss = getEnv()->FindClass("java/lang/String");
            if (NULL == clss) {
                std::cerr << " Can't find class java/lang/String" << std::endl;
            }
            return clss;
        }
    
        static jclass StringClass = NULL;
    
        jclass getStringClass() {
            if (StringClass != NULL) {
                return StringClass;
            }
            StringClass = getNewStringClass();
            return StringClass;
        }
    
        static jclass getNewRuntimeExceptionClass() {
            jclass clss = getEnv()->FindClass("java/lang/RuntimeException");
            if (NULL == clss) {
                std::cerr << " Can't find class java/lang/RuntimeException" << std::endl;
            }
            return clss;
        }
    
        static jclass RuntimeExceptionClass = NULL;
    
        jclass getRuntimeExceptionClass() {
            if (RuntimeExceptionClass != NULL) {
                return StringClass;
            }
            RuntimeExceptionClass = getNewRuntimeExceptionClass();
            return RuntimeExceptionClass;
        }
        
        static jclass getNewClassClass() {
            jclass clss = getEnv()->FindClass("java/lang/Class");
            if (NULL == clss) {
                std::cerr << " Can't find class java/lang/Class" << std::endl;
            }
            return clss;
        }
    
        static jclass ClassClass = NULL;
    
        jclass getClassClass() {
            if (ClassClass != NULL) {
                return ClassClass;
            }
            ClassClass = getNewClassClass();
            return ClassClass;
        }
        
        static jclass getNewArraysClass() {
            jclass clss = getEnv()->FindClass("java/util/Arrays");
            if (NULL == clss) {
                std::cerr << " Can't find class java/util/Arrays" << std::endl;
            }
            return clss;
        }
    
        static jclass ArraysClass = NULL;
    
        jclass getArraysClass() {
            if (ArraysClass != NULL) {
                return ArraysClass;
            }
            ArraysClass = getNewArraysClass();
            return ArraysClass;
        }
        
        static jstring getNewEmptyString() {
            return getEnv()->NewStringUTF("");
        }
    
        static jstring EmptyString = NULL;
    
        jstring getEmptyString() {
            if (EmptyString != NULL) {
                return EmptyString;
            }
            EmptyString = getNewEmptyString();
            return EmptyString;
        }
    
        void PrintJObject(const char *prefix, jobject jobj) {
            if(NULL == jobj) {
                std::cout << prefix << "NULL" << std::endl;
                return;
            }
            JNIEnv *env = getEnv();
            jclass clss = env->GetObjectClass(jobj);
            jmethodID midToString = env->GetMethodID(clss, "toString", "()Ljava/lang/String;");
            if(NULL == midToString) {
                std::cout << prefix << "can not find toString method" << std::endl;
                return;
            }
            jstring jobjstr = (jstring) env->CallObjectMethod(jobj, midToString);
            if(NULL == jobjstr) {
                std::cout << prefix << "toString() returned NULL" << std::endl;
                return;
            }
            jboolean iscopy = JNI_FALSE;
            const char *cstr = env->GetStringUTFChars(jobjstr, &iscopy);
            std::cout << prefix << cstr << std::endl;
            env->ReleaseStringUTFChars(jobjstr,cstr);
        }
        
        void PrintJBooleanArray(const char *prefix, jbooleanArray jobj) {
            if(NULL == jobj) {
                std::cout << prefix << "NULL" << std::endl;
                return;
            }
            JNIEnv *env = getEnv();
            jclass clss = getArraysClass();
            jmethodID midToString = env->GetStaticMethodID(clss, "toString", "([Z)Ljava/lang/String;");
            if(NULL == midToString) {
                std::cout << prefix << "can not find toString method" << std::endl;
                return;
            }
            jstring jobjstr = (jstring) env->CallStaticObjectMethod(clss, midToString,jobj);
            if(NULL == jobjstr) {
                std::cout << prefix << "toString() returned NULL" << std::endl;
                return;
            }
            jboolean iscopy = JNI_FALSE;
            const char *cstr = env->GetStringUTFChars(jobjstr, &iscopy);
            std::cout << prefix << cstr << std::endl;
            env->ReleaseStringUTFChars(jobjstr,cstr);
        }
        
        void PrintJByteArray(const char *prefix, jbyteArray jobj) {
            if(NULL == jobj) {
                std::cout << prefix << "NULL" << std::endl;
                return;
            }
            JNIEnv *env = getEnv();
            jclass clss = getArraysClass();
            jmethodID midToString = env->GetStaticMethodID(clss, "toString", "([B)Ljava/lang/String;");
            if(NULL == midToString) {
                std::cout << prefix << "can not find toString method" << std::endl;
                return;
            }
            jstring jobjstr = (jstring) env->CallStaticObjectMethod(clss, midToString,jobj);
            if(NULL == jobjstr) {
                std::cout << prefix << "toString() returned NULL" << std::endl;
                return;
            }
            jboolean iscopy = JNI_FALSE;
            const char *cstr = env->GetStringUTFChars(jobjstr, &iscopy);
            std::cout << prefix << cstr << std::endl;
            env->ReleaseStringUTFChars(jobjstr,cstr);
        }
        
        void PrintJCharArray(const char *prefix, jcharArray jobj) {
            if(NULL == jobj) {
                std::cout << prefix << "NULL" << std::endl;
                return;
            }
            JNIEnv *env = getEnv();
            jclass clss = getArraysClass();
            jmethodID midToString = env->GetStaticMethodID(clss, "toString", "([C)Ljava/lang/String;");
            if(NULL == midToString) {
                std::cout << prefix << "can not find toString method" << std::endl;
                return;
            }
            jstring jobjstr = (jstring) env->CallStaticObjectMethod(clss, midToString,jobj);
            if(NULL == jobjstr) {
                std::cout << prefix << "toString() returned NULL" << std::endl;
                return;
            }
            jboolean iscopy = JNI_FALSE;
            const char *cstr = env->GetStringUTFChars(jobjstr, &iscopy);
            std::cout << prefix << cstr << std::endl;
            env->ReleaseStringUTFChars(jobjstr,cstr);
        }
        
        void PrintJShortArray(const char *prefix, jshortArray jobj) {
            if(NULL == jobj) {
                std::cout << prefix << "NULL" << std::endl;
                return;
            }
            JNIEnv *env = getEnv();
            jclass clss = getArraysClass();
            jmethodID midToString = env->GetStaticMethodID(clss, "toString", "([S)Ljava/lang/String;");
            if(NULL == midToString) {
                std::cout << prefix << "can not find toString method" << std::endl;
                return;
            }
            jstring jobjstr = (jstring) env->CallStaticObjectMethod(clss, midToString,jobj);
            if(NULL == jobjstr) {
                std::cout << prefix << "toString() returned NULL" << std::endl;
                return;
            }
            jboolean iscopy = JNI_FALSE;
            const char *cstr = env->GetStringUTFChars(jobjstr, &iscopy);
            std::cout << prefix << cstr << std::endl;
            env->ReleaseStringUTFChars(jobjstr,cstr);
        }
        
        void PrintJIntArray(const char *prefix, jintArray jobj) {
            if(NULL == jobj) {
                std::cout << prefix << "NULL" << std::endl;
                return;
            }
            JNIEnv *env = getEnv();
            jclass clss = getArraysClass();
            jmethodID midToString = env->GetStaticMethodID(clss, "toString", "([I)Ljava/lang/String;");
            if(NULL == midToString) {
                std::cout << prefix << "can not find toString method" << std::endl;
                return;
            }
            jstring jobjstr = (jstring) env->CallStaticObjectMethod(clss, midToString,jobj);
            if(NULL == jobjstr) {
                std::cout << prefix << "toString() returned NULL" << std::endl;
                return;
            }
            jboolean iscopy = JNI_FALSE;
            const char *cstr = env->GetStringUTFChars(jobjstr, &iscopy);
            std::cout << prefix << cstr << std::endl;
            env->ReleaseStringUTFChars(jobjstr,cstr);
        }
        
        void PrintJLongArray(const char *prefix, jlongArray jobj) {
            if(NULL == jobj) {
                std::cout << prefix << "NULL" << std::endl;
                return;
            }
            JNIEnv *env = getEnv();
            jclass clss = getArraysClass();
            jmethodID midToString = env->GetStaticMethodID(clss, "toString", "([J)Ljava/lang/String;");
            if(NULL == midToString) {
                std::cout << prefix << "can not find toString method" << std::endl;
                return;
            }
            jstring jobjstr = (jstring) env->CallStaticObjectMethod(clss, midToString,jobj);
            if(NULL == jobjstr) {
                std::cout << prefix << "toString() returned NULL" << std::endl;
                return;
            }
            jboolean iscopy = JNI_FALSE;
            const char *cstr = env->GetStringUTFChars(jobjstr, &iscopy);
            std::cout << prefix << cstr << std::endl;
            env->ReleaseStringUTFChars(jobjstr,cstr);
        }
        
        void PrintJFloatArray(const char *prefix, jfloatArray jobj) {
            if(NULL == jobj) {
                std::cout << prefix << "NULL" << std::endl;
                return;
            }
            JNIEnv *env = getEnv();
            jclass clss = getArraysClass();
            jmethodID midToString = env->GetStaticMethodID(clss, "toString", "([F)Ljava/lang/String;");
            if(NULL == midToString) {
                std::cout << prefix << "can not find toString method" << std::endl;
                return;
            }
            jstring jobjstr = (jstring) env->CallStaticObjectMethod(clss, midToString,jobj);
            if(NULL == jobjstr) {
                std::cout << prefix << "toString() returned NULL" << std::endl;
                return;
            }
            jboolean iscopy = JNI_FALSE;
            const char *cstr = env->GetStringUTFChars(jobjstr, &iscopy);
            std::cout << prefix << cstr << std::endl;
            env->ReleaseStringUTFChars(jobjstr,cstr);
        }
        
        void PrintJDoubleArray(const char *prefix, jdoubleArray jobj) {
            if(NULL == jobj) {
                std::cout << prefix << "NULL" << std::endl;
                return;
            }
            JNIEnv *env = getEnv();
            jclass clss = getArraysClass();
            jmethodID midToString = env->GetStaticMethodID(clss, "toString", "([D)Ljava/lang/String;");
            if(NULL == midToString) {
                std::cout << prefix << "can not find toString method" << std::endl;
                return;
            }
            jstring jobjstr = (jstring) env->CallStaticObjectMethod(clss, midToString,jobj);
            if(NULL == jobjstr) {
                std::cout << prefix << "toString() returned NULL" << std::endl;
                return;
            }
            jboolean iscopy = JNI_FALSE;
            const char *cstr = env->GetStringUTFChars(jobjstr, &iscopy);
            std::cout << prefix << cstr << std::endl;
            env->ReleaseStringUTFChars(jobjstr,cstr);
        }
        
        void PrintJThrowable(const char *prefix, jthrowable jobj) {
            JNIEnv *env = getEnv();
            jclass clss = env->GetObjectClass(jobj);
            jmethodID midToString = env->GetMethodID(clss, "toString", "()Ljava/lang/String;");
            jstring jobjstr = (jstring) env->CallObjectMethod(jobj, midToString);
            jboolean iscopy = JNI_FALSE;
            const char *cstr = env->GetStringUTFChars(jobjstr, &iscopy);
            std::cerr << prefix << cstr << std::endl;
            env->ReleaseStringUTFChars(jobjstr,cstr);
            env->ExceptionDescribe();
            env->ExceptionClear();
        }
        
        void DebugPrintJObject(const char *file, int lineno, const char *prefix, jobject jobj) {
            
            if(NULL == jobj) {
                std::cout << file << ":" << lineno << " " << prefix << "NULL" << std::endl;
                return;
            }
            JNIEnv *env = getEnv();
            jclass clss = env->GetObjectClass(jobj);
            bool isnull = (jobj == NULL) || (env->IsSameObject(jobj,NULL) == JNI_TRUE);
            if(isnull) {
                std::cout << file << ":" << lineno << " jobj=" << jobj << " " << prefix << "NULL" << std::endl;
                return;
            }
            jmethodID midToString = env->GetMethodID(clss, "toString", "()Ljava/lang/String;");
            jstring jobjstr = (jstring) env->CallObjectMethod(jobj, midToString);
            jboolean iscopy = JNI_FALSE;
            const char *cstr = env->GetStringUTFChars(jobjstr, &iscopy);
            std::cout << file << ":" << lineno << " jobj=" << jobj << " " << prefix << cstr << std::endl;
            env->ReleaseStringUTFChars(jobjstr,cstr);
        }
        
        void PrintObject(const char *prefix, const ::crclj::java::lang::Object &objref) {
            PrintJObject(prefix,objref.jthis);
        }
        
        const char *GetStringUTFChars(jstring js, jboolean *iscopy) {
            jboolean iscopy2;
            const char *ret = getEnv()->GetStringUTFChars(js,&iscopy2);
            if(NULL != iscopy) {
                *iscopy = iscopy2;
            }
            return ret;
        }
        
        void ReleaseStringUTFChars(jstring js, const char *utf) {
            getEnv()->ReleaseStringUTFChars(js,utf);
        }
        
        jboolean *GetBooleanArrayElements(jbooleanArray  jarray,jboolean *iscopy) {
            jboolean iscopy2;
            jboolean *ret = getEnv()->GetBooleanArrayElements(jarray,&iscopy2);
            if(NULL != iscopy) {
                *iscopy = iscopy2;
            }
            return ret;
        }
        
        jbyte *GetByteArrayElements(jbyteArray  jarray,jboolean *iscopy) {
            jboolean iscopy2;
            jbyte *ret = getEnv()->GetByteArrayElements(jarray,&iscopy2);
            if(NULL != iscopy) {
                *iscopy = iscopy2;
            }
            return ret;
        }
        
        jchar *GetCharArrayElements(jcharArray  jarray,jboolean *iscopy) {
            jboolean iscopy2;
            jchar *ret = getEnv()->GetCharArrayElements(jarray,&iscopy2);
            if(NULL != iscopy) {
                *iscopy = iscopy2;
            }
            return ret;
        }
        jshort *GetShortArrayElements(jshortArray  jarray,jboolean *iscopy) {
            jboolean iscopy2;
            jshort *ret = getEnv()->GetShortArrayElements(jarray,&iscopy2);
            if(NULL != iscopy) {
                *iscopy = iscopy2;
            }
            return ret;
        }
        jint *GetIntArrayElements(jintArray  jarray,jboolean *iscopy) {
            jboolean iscopy2;
            jint *ret = getEnv()->GetIntArrayElements(jarray,&iscopy2);
            if(NULL != iscopy) {
                *iscopy = iscopy2;
            }
            return ret;
        }
        jlong *GetLongArrayElements(jlongArray  jarray,jboolean *iscopy) {
            jboolean iscopy2;
            jlong *ret = getEnv()->GetLongArrayElements(jarray,&iscopy2);
            if(NULL != iscopy) {
                *iscopy = iscopy2;
            }
            return ret;
        }
        jfloat *GetFloatArrayElements(jfloatArray  jarray,jboolean *iscopy) {
            jboolean iscopy2;
            jfloat *ret = getEnv()->GetFloatArrayElements(jarray,&iscopy2);
            if(NULL != iscopy) {
                *iscopy = iscopy2;
            }
            return ret;
        }
        jdouble *GetDoubleArrayElements(jdoubleArray  jarray,jboolean *iscopy) {
            jboolean iscopy2;
            jdouble *ret = getEnv()->GetDoubleArrayElements(jarray,&iscopy2);
            if(NULL != iscopy) {
                *iscopy = iscopy2;
            }
            return ret;
        }
        
        void ReleaseBooleanArrayElements(jbooleanArray jarray, jboolean *nativeArray, jint mode) {
            getEnv()->ReleaseBooleanArrayElements(jarray,nativeArray,mode);
        }
        
        void ReleaseByteArrayElements(jbyteArray jarray, jbyte *nativeArray, jint mode) {
            getEnv()->ReleaseByteArrayElements(jarray,nativeArray,mode);
        }
        
        void ReleaseCharArrayElements(jcharArray jarray, jchar *nativeArray, jint mode) {
            getEnv()->ReleaseCharArrayElements(jarray,nativeArray,mode);
        }
        
        void ReleaseShortArrayElements(jshortArray jarray, jshort *nativeArray, jint mode) {
            getEnv()->ReleaseShortArrayElements(jarray,nativeArray,mode);
        }
        void ReleaseIntArrayElements(jintArray jarray, jint *nativeArray, jint mode) {
            getEnv()->ReleaseIntArrayElements(jarray,nativeArray,mode);
        }
        void ReleaseLongArrayElements(jlongArray jarray, jlong *nativeArray, jint mode) {
            getEnv()->ReleaseLongArrayElements(jarray,nativeArray,mode);
        }
        void ReleaseFloatArrayElements(jfloatArray jarray, jfloat *nativeArray, jint mode) {
            getEnv()->ReleaseFloatArrayElements(jarray,nativeArray,mode);
        }
        void ReleaseDoubleArrayElements(jdoubleArray jarray, jdouble *nativeArray, jint mode) {
            getEnv()->ReleaseDoubleArrayElements(jarray,nativeArray,mode);
        }
        
        
        void SetDebugJ4Cpp(bool debug) {
            debug_j4cpp = debug;
        }
        
        bool GetDebugJ4Cpp() { 
            return debug_j4cpp;
        }
        // end namespace crclj
    }
    
// No Native classes : registerNativMethods not needed.
static void registerNativeMethods(JNIEnv *env) {}
