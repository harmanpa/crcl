
#include <jni.h>
#include <cstdlib>
#include <iostream>
#include "crclj.h"

namespace crclj {

    



// start_segment_index = 10
// start_segment_index = 20
// segment_index = 1
// classesSegList=[class java.lang.Enum, class crcl.base.AngleUnitEnumType, class crcl.base.CRCLProgramType, class crcl.base.SetMotionCoordinationType, class crcl.base.ConfigureJointReportsType, class crcl.base.RunProgramType, class crcl.base.GuardsStatusesType, class crcl.base.ParameterSettingType, class crcl.base.PoseType, class crcl.base.TransSpeedType]


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


    // class_index = 1 clss=class crcl.base.AngleUnitEnumType

    namespace crcl{
        namespace base{
        
        // get JNI handle for class crcl.base.AngleUnitEnumType
        static inline jclass getAngleUnitEnumTypeClass();
        
        AngleUnitEnumType::AngleUnitEnumType(jobject _jthis, bool copy): ::crclj::java::lang::Enum(_jthis,copy) {
                
        }
        
        AngleUnitEnumType::AngleUnitEnumType(const AngleUnitEnumType &objref): ::crclj::java::lang::Enum((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class AngleUnitEnumType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class AngleUnitEnumType jthis=",jthis);
            }
        }
        
        AngleUnitEnumType AngleUnitEnumType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getAngleUnitEnumTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            AngleUnitEnumType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool AngleUnitEnumType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getAngleUnitEnumTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        AngleUnitEnumType::AngleUnitEnumType() : ::crclj::java::lang::Enum((jobject)NULL,false) {
        JNIEnv *env =getEnv();
        static jclass cls = getAngleUnitEnumTypeClass();
        if (cls != NULL) {
            static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
            if (NULL == mid) {
                std::cerr << "Class AngleUnitEnumType has no method constructor signature ()V" << std::endl;
            } else {
                jthis = env->NewObject(cls, mid );
                jthrowable t = env->ExceptionOccurred();
                if(t != NULL) {
                    if(GetDebugJ4Cpp()) {
                        DebugPrintJObject(__FILE__,__LINE__," AngleUnitEnumType::ordinal jthis=",t);
                        env->ExceptionDescribe();
                    }
                    throw t;
                }
                if(jthis == NULL) {
                    std::cerr << "Call to create new AngleUnitEnumType with signature ()V returned null." << std::endl;
                    releaseEnv(env);
                    return;
                }
                jobjectRefType ref = env->GetObjectRefType(jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new AngleUnitEnumType jthis=",jthis);
                if(ref != JNIGlobalRefType) {
                    jthis = env->NewGlobalRef(jthis);
                }
            }
        }
        releaseEnv(env);
        }


        // Destructor for crcl.base.AngleUnitEnumType
        AngleUnitEnumType::~AngleUnitEnumType() {
        	// Place-holder for later extensibility.
        }


        // Field getter for DEGREE
        AngleUnitEnumType AngleUnitEnumType::getDEGREE() {
        JNIEnv *env =getEnv();
        static jclass cls = getAngleUnitEnumTypeClass();
        jobject retVal=NULL;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "DEGREE", "Lcrcl/base/AngleUnitEnumType;");
            if (NULL == fid) {
                std::cerr << "Class crcl.base.AngleUnitEnumType has no field named DEGREE with signature Lcrcl/base/AngleUnitEnumType;." << std::endl;
                static AngleUnitEnumType nullObject((jobject)NULL,false); return nullObject;
            } else {
                retVal= (jobject)  env->GetStaticObjectField( cls, fid );
            }
        }
        releaseEnv(env);
                    
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            AngleUnitEnumType retObject(retVal,false);
            return retObject;
        }

        // Field getter for RADIAN
        AngleUnitEnumType AngleUnitEnumType::getRADIAN() {
        JNIEnv *env =getEnv();
        static jclass cls = getAngleUnitEnumTypeClass();
        jobject retVal=NULL;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "RADIAN", "Lcrcl/base/AngleUnitEnumType;");
            if (NULL == fid) {
                std::cerr << "Class crcl.base.AngleUnitEnumType has no field named RADIAN with signature Lcrcl/base/AngleUnitEnumType;." << std::endl;
                static AngleUnitEnumType nullObject((jobject)NULL,false); return nullObject;
            } else {
                retVal= (jobject)  env->GetStaticObjectField( cls, fid );
            }
        }
        releaseEnv(env);
                    
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            AngleUnitEnumType retObject(retVal,false);
            return retObject;
        }

        jstring AngleUnitEnumType::value() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method value of crcl.base.AngleUnitEnumType with jthis == NULL." << std::endl;
                return NULL;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," AngleUnitEnumType::value jthis=",jthis);
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "value", "()Ljava/lang/String;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," AngleUnitEnumType::value jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.AngleUnitEnumType has no method named value with signature ()Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," AngleUnitEnumType::value jthrowable t=",t);
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

        jobjectArray AngleUnitEnumType::values() {
            JNIEnv *env =getEnv();
            static jclass cls = getAngleUnitEnumTypeClass();
            jobjectArray retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "values", "()[Lcrcl/base/AngleUnitEnumType;");
                if (NULL == mid) {
                    std::cerr << "Class crcl.base.AngleUnitEnumType has no method named values with signature ()[Lcrcl/base/AngleUnitEnumType;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jobjectArray)  env->CallStaticObjectMethod( cls, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," AngleUnitEnumType::values jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        AngleUnitEnumType AngleUnitEnumType::valueOf(jstring string_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getAngleUnitEnumTypeClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "valueOf", "(Ljava/lang/String;)Lcrcl/base/AngleUnitEnumType;");
                if (NULL == mid) {
                    std::cerr << "Class crcl.base.AngleUnitEnumType has no method named valueOf with signature (Ljava/lang/String;)Lcrcl/base/AngleUnitEnumType;." << std::endl;
                    static AngleUnitEnumType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," AngleUnitEnumType::valueOf jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            AngleUnitEnumType retObject(retVal,false);
            return retObject;
        }

        AngleUnitEnumType AngleUnitEnumType::fromValue(jstring string_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getAngleUnitEnumTypeClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "fromValue", "(Ljava/lang/String;)Lcrcl/base/AngleUnitEnumType;");
                if (NULL == mid) {
                    std::cerr << "Class crcl.base.AngleUnitEnumType has no method named fromValue with signature (Ljava/lang/String;)Lcrcl/base/AngleUnitEnumType;." << std::endl;
                    static AngleUnitEnumType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," AngleUnitEnumType::fromValue jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            AngleUnitEnumType retObject(retVal,false);
            return retObject;
        }
        static jclass getNewAngleUnitEnumTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/AngleUnitEnumType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/AngleUnitEnumType" << std::endl;
            }
            return clss;
        }
        
        static jclass AngleUnitEnumTypeClass = NULL;
        static inline jclass getAngleUnitEnumTypeClass() {
            if (AngleUnitEnumTypeClass != NULL) {
                return AngleUnitEnumTypeClass;
            }
            AngleUnitEnumTypeClass = getNewAngleUnitEnumTypeClass();
            return AngleUnitEnumTypeClass;
        }

    // class_index = 2 clss=class crcl.base.CRCLProgramType

        
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
                            DebugPrintJObject(__FILE__,__LINE__," CRCLProgramType::fromValue jthis=",t);
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


        InitCanonType CRCLProgramType::getInitCanon() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getInitCanon of crcl.base.CRCLProgramType with jthis == NULL." << std::endl;
                static InitCanonType nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLProgramType::getInitCanon jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getInitCanon", "()Lcrcl/base/InitCanonType;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLProgramType::getInitCanon jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.CRCLProgramType has no method named getInitCanon with signature ()Lcrcl/base/InitCanonType;." << std::endl;
                    static InitCanonType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLProgramType::getInitCanon jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            InitCanonType retObject(retVal,false);
            return retObject;
        }

        void CRCLProgramType::setInitCanon(const InitCanonType & initCanonType_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setInitCanon of crcl.base.CRCLProgramType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLProgramType::setInitCanon jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setInitCanon", "(Lcrcl/base/InitCanonType;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLProgramType::setInitCanon jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.CRCLProgramType has no method named setInitCanon with signature (Lcrcl/base/InitCanonType;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,initCanonType_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLProgramType::setInitCanon jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
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

        EndCanonType CRCLProgramType::getEndCanon() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getEndCanon of crcl.base.CRCLProgramType with jthis == NULL." << std::endl;
                static EndCanonType nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLProgramType::getEndCanon jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getEndCanon", "()Lcrcl/base/EndCanonType;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLProgramType::getEndCanon jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.CRCLProgramType has no method named getEndCanon with signature ()Lcrcl/base/EndCanonType;." << std::endl;
                    static EndCanonType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLProgramType::getEndCanon jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            EndCanonType retObject(retVal,false);
            return retObject;
        }

        void CRCLProgramType::setEndCanon(const EndCanonType & endCanonType_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setEndCanon of crcl.base.CRCLProgramType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLProgramType::setEndCanon jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setEndCanon", "(Lcrcl/base/EndCanonType;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLProgramType::setEndCanon jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.CRCLProgramType has no method named setEndCanon with signature (Lcrcl/base/EndCanonType;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,endCanonType_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLProgramType::setEndCanon jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
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

    // class_index = 3 clss=class crcl.base.SetMotionCoordinationType

        
        // get JNI handle for class crcl.base.SetMotionCoordinationType
        static inline jclass getSetMotionCoordinationTypeClass();
        
        SetMotionCoordinationType::SetMotionCoordinationType(jobject _jthis, bool copy): MiddleCommandType(_jthis,copy) {
                
        }
        
        SetMotionCoordinationType::SetMotionCoordinationType(const SetMotionCoordinationType &objref): MiddleCommandType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class SetMotionCoordinationType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class SetMotionCoordinationType jthis=",jthis);
            }
        }
        
        SetMotionCoordinationType SetMotionCoordinationType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getSetMotionCoordinationTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            SetMotionCoordinationType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool SetMotionCoordinationType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getSetMotionCoordinationTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        SetMotionCoordinationType::SetMotionCoordinationType() : MiddleCommandType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getSetMotionCoordinationTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class SetMotionCoordinationType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," SetMotionCoordinationType::setEndCanon jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new SetMotionCoordinationType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new SetMotionCoordinationType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.SetMotionCoordinationType
        SetMotionCoordinationType::~SetMotionCoordinationType() {
        	// Place-holder for later extensibility.
        }


        jboolean SetMotionCoordinationType::isCoordinated() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method isCoordinated of crcl.base.SetMotionCoordinationType with jthis == NULL." << std::endl;
                return false;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," SetMotionCoordinationType::isCoordinated jthis=",jthis);
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "isCoordinated", "()Z");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," SetMotionCoordinationType::isCoordinated jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.SetMotionCoordinationType has no method named isCoordinated with signature ()Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallBooleanMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," SetMotionCoordinationType::isCoordinated jthrowable t=",t);
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

        void SetMotionCoordinationType::setCoordinated(jboolean boolean_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setCoordinated of crcl.base.SetMotionCoordinationType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," SetMotionCoordinationType::setCoordinated jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setCoordinated", "(Z)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," SetMotionCoordinationType::setCoordinated jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.SetMotionCoordinationType has no method named setCoordinated with signature (Z)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,boolean_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," SetMotionCoordinationType::setCoordinated jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }
        static jclass getNewSetMotionCoordinationTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/SetMotionCoordinationType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/SetMotionCoordinationType" << std::endl;
            }
            return clss;
        }
        
        static jclass SetMotionCoordinationTypeClass = NULL;
        static inline jclass getSetMotionCoordinationTypeClass() {
            if (SetMotionCoordinationTypeClass != NULL) {
                return SetMotionCoordinationTypeClass;
            }
            SetMotionCoordinationTypeClass = getNewSetMotionCoordinationTypeClass();
            return SetMotionCoordinationTypeClass;
        }

    // class_index = 4 clss=class crcl.base.ConfigureJointReportsType

        
        // get JNI handle for class crcl.base.ConfigureJointReportsType
        static inline jclass getConfigureJointReportsTypeClass();
        
        ConfigureJointReportsType::ConfigureJointReportsType(jobject _jthis, bool copy): MiddleCommandType(_jthis,copy) {
                
        }
        
        ConfigureJointReportsType::ConfigureJointReportsType(const ConfigureJointReportsType &objref): MiddleCommandType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class ConfigureJointReportsType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class ConfigureJointReportsType jthis=",jthis);
            }
        }
        
        ConfigureJointReportsType ConfigureJointReportsType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getConfigureJointReportsTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            ConfigureJointReportsType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool ConfigureJointReportsType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getConfigureJointReportsTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        ConfigureJointReportsType::ConfigureJointReportsType() : MiddleCommandType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getConfigureJointReportsTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class ConfigureJointReportsType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ConfigureJointReportsType::setCoordinated jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new ConfigureJointReportsType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new ConfigureJointReportsType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.ConfigureJointReportsType
        ConfigureJointReportsType::~ConfigureJointReportsType() {
        	// Place-holder for later extensibility.
        }


        jboolean ConfigureJointReportsType::isResetAll() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method isResetAll of crcl.base.ConfigureJointReportsType with jthis == NULL." << std::endl;
                return false;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ConfigureJointReportsType::isResetAll jthis=",jthis);
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "isResetAll", "()Z");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ConfigureJointReportsType::isResetAll jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ConfigureJointReportsType has no method named isResetAll with signature ()Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallBooleanMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ConfigureJointReportsType::isResetAll jthrowable t=",t);
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

        void ConfigureJointReportsType::setResetAll(jboolean boolean_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setResetAll of crcl.base.ConfigureJointReportsType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ConfigureJointReportsType::setResetAll jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setResetAll", "(Z)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ConfigureJointReportsType::setResetAll jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ConfigureJointReportsType has no method named setResetAll with signature (Z)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,boolean_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ConfigureJointReportsType::setResetAll jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        ::crclj::java::util::List ConfigureJointReportsType::getConfigureJointReport() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getConfigureJointReport of crcl.base.ConfigureJointReportsType with jthis == NULL." << std::endl;
                static ::crclj::java::util::List nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ConfigureJointReportsType::getConfigureJointReport jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getConfigureJointReport", "()Ljava/util/List;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ConfigureJointReportsType::getConfigureJointReport jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ConfigureJointReportsType has no method named getConfigureJointReport with signature ()Ljava/util/List;." << std::endl;
                    static ::crclj::java::util::List nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ConfigureJointReportsType::getConfigureJointReport jthrowable t=",t);
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
        static jclass getNewConfigureJointReportsTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/ConfigureJointReportsType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/ConfigureJointReportsType" << std::endl;
            }
            return clss;
        }
        
        static jclass ConfigureJointReportsTypeClass = NULL;
        static inline jclass getConfigureJointReportsTypeClass() {
            if (ConfigureJointReportsTypeClass != NULL) {
                return ConfigureJointReportsTypeClass;
            }
            ConfigureJointReportsTypeClass = getNewConfigureJointReportsTypeClass();
            return ConfigureJointReportsTypeClass;
        }

    // class_index = 5 clss=class crcl.base.RunProgramType

        
        // get JNI handle for class crcl.base.RunProgramType
        static inline jclass getRunProgramTypeClass();
        
        RunProgramType::RunProgramType(jobject _jthis, bool copy): MiddleCommandType(_jthis,copy) {
                
        }
        
        RunProgramType::RunProgramType(const RunProgramType &objref): MiddleCommandType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class RunProgramType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class RunProgramType jthis=",jthis);
            }
        }
        
        RunProgramType RunProgramType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getRunProgramTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            RunProgramType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool RunProgramType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getRunProgramTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        RunProgramType::RunProgramType() : MiddleCommandType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getRunProgramTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class RunProgramType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," RunProgramType::getConfigureJointReport jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new RunProgramType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new RunProgramType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.RunProgramType
        RunProgramType::~RunProgramType() {
        	// Place-holder for later extensibility.
        }


        jstring RunProgramType::getProgramText() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getProgramText of crcl.base.RunProgramType with jthis == NULL." << std::endl;
                return NULL;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," RunProgramType::getProgramText jthis=",jthis);
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getProgramText", "()Ljava/lang/String;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," RunProgramType::getProgramText jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.RunProgramType has no method named getProgramText with signature ()Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," RunProgramType::getProgramText jthrowable t=",t);
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

        void RunProgramType::setProgramText(jstring string_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setProgramText of crcl.base.RunProgramType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," RunProgramType::setProgramText jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setProgramText", "(Ljava/lang/String;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," RunProgramType::setProgramText jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.RunProgramType has no method named setProgramText with signature (Ljava/lang/String;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," RunProgramType::setProgramText jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        // Easy call alternative for setProgramText
        void RunProgramType::setProgramText(const char * easyArg_0) {
            // convenience method, converts to/from JNI types to common C++ types.
            if(jthis == NULL) {
                std::cerr << "Call of method setProgramText of crcl.base.RunProgramType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," RunProgramType::setProgramText jthis=",jthis);
            
                    
            jstring string_0 = env->NewStringUTF(easyArg_0);
            setProgramText(string_0);
            jobjectRefType ref_0 = env->GetObjectRefType(string_0);
            if(ref_0 == JNIGlobalRefType) {
                env->DeleteGlobalRef(string_0);
            }
            
            releaseEnv(env);
        }

        static jclass getNewRunProgramTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/RunProgramType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/RunProgramType" << std::endl;
            }
            return clss;
        }
        
        static jclass RunProgramTypeClass = NULL;
        static inline jclass getRunProgramTypeClass() {
            if (RunProgramTypeClass != NULL) {
                return RunProgramTypeClass;
            }
            RunProgramTypeClass = getNewRunProgramTypeClass();
            return RunProgramTypeClass;
        }

    // class_index = 6 clss=class crcl.base.GuardsStatusesType

        
        // get JNI handle for class crcl.base.GuardsStatusesType
        static inline jclass getGuardsStatusesTypeClass();
        
        GuardsStatusesType::GuardsStatusesType(jobject _jthis, bool copy): DataThingType(_jthis,copy) {
                
        }
        
        GuardsStatusesType::GuardsStatusesType(const GuardsStatusesType &objref): DataThingType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class GuardsStatusesType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class GuardsStatusesType jthis=",jthis);
            }
        }
        
        GuardsStatusesType GuardsStatusesType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getGuardsStatusesTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            GuardsStatusesType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool GuardsStatusesType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getGuardsStatusesTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        GuardsStatusesType::GuardsStatusesType() : DataThingType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getGuardsStatusesTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class GuardsStatusesType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," GuardsStatusesType::setProgramText jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new GuardsStatusesType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new GuardsStatusesType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.GuardsStatusesType
        GuardsStatusesType::~GuardsStatusesType() {
        	// Place-holder for later extensibility.
        }


        ::crclj::java::util::List GuardsStatusesType::getGuard() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getGuard of crcl.base.GuardsStatusesType with jthis == NULL." << std::endl;
                static ::crclj::java::util::List nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," GuardsStatusesType::getGuard jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getGuard", "()Ljava/util/List;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," GuardsStatusesType::getGuard jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.GuardsStatusesType has no method named getGuard with signature ()Ljava/util/List;." << std::endl;
                    static ::crclj::java::util::List nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," GuardsStatusesType::getGuard jthrowable t=",t);
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

        jint GuardsStatusesType::getTriggerCount() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getTriggerCount of crcl.base.GuardsStatusesType with jthis == NULL." << std::endl;
                return (jint) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," GuardsStatusesType::getTriggerCount jthis=",jthis);
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getTriggerCount", "()I");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," GuardsStatusesType::getTriggerCount jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.GuardsStatusesType has no method named getTriggerCount with signature ()I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallIntMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," GuardsStatusesType::getTriggerCount jthrowable t=",t);
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

        void GuardsStatusesType::setTriggerCount(jint int_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setTriggerCount of crcl.base.GuardsStatusesType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," GuardsStatusesType::setTriggerCount jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setTriggerCount", "(I)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," GuardsStatusesType::setTriggerCount jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.GuardsStatusesType has no method named setTriggerCount with signature (I)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,int_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," GuardsStatusesType::setTriggerCount jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        jlong GuardsStatusesType::getTriggerStopTimeMicros() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getTriggerStopTimeMicros of crcl.base.GuardsStatusesType with jthis == NULL." << std::endl;
                return (jlong) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," GuardsStatusesType::getTriggerStopTimeMicros jthis=",jthis);
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getTriggerStopTimeMicros", "()J");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," GuardsStatusesType::getTriggerStopTimeMicros jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.GuardsStatusesType has no method named getTriggerStopTimeMicros with signature ()J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallLongMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," GuardsStatusesType::getTriggerStopTimeMicros jthrowable t=",t);
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

        void GuardsStatusesType::setTriggerStopTimeMicros(jlong long_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setTriggerStopTimeMicros of crcl.base.GuardsStatusesType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," GuardsStatusesType::setTriggerStopTimeMicros jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setTriggerStopTimeMicros", "(J)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," GuardsStatusesType::setTriggerStopTimeMicros jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.GuardsStatusesType has no method named setTriggerStopTimeMicros with signature (J)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,long_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," GuardsStatusesType::setTriggerStopTimeMicros jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        ::crclj::java::lang::Double GuardsStatusesType::getTriggerValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getTriggerValue of crcl.base.GuardsStatusesType with jthis == NULL." << std::endl;
                static ::crclj::java::lang::Double nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," GuardsStatusesType::getTriggerValue jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getTriggerValue", "()Ljava/lang/Double;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," GuardsStatusesType::getTriggerValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.GuardsStatusesType has no method named getTriggerValue with signature ()Ljava/lang/Double;." << std::endl;
                    static ::crclj::java::lang::Double nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," GuardsStatusesType::getTriggerValue jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            ::crclj::java::lang::Double retObject(retVal,false);
            return retObject;
        }

        void GuardsStatusesType::setTriggerValue(const ::crclj::java::lang::Double & double_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setTriggerValue of crcl.base.GuardsStatusesType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," GuardsStatusesType::setTriggerValue jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setTriggerValue", "(Ljava/lang/Double;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," GuardsStatusesType::setTriggerValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.GuardsStatusesType has no method named setTriggerValue with signature (Ljava/lang/Double;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,double_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," GuardsStatusesType::setTriggerValue jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        PoseType GuardsStatusesType::getTriggerPose() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getTriggerPose of crcl.base.GuardsStatusesType with jthis == NULL." << std::endl;
                static PoseType nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," GuardsStatusesType::getTriggerPose jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getTriggerPose", "()Lcrcl/base/PoseType;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," GuardsStatusesType::getTriggerPose jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.GuardsStatusesType has no method named getTriggerPose with signature ()Lcrcl/base/PoseType;." << std::endl;
                    static PoseType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," GuardsStatusesType::getTriggerPose jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            PoseType retObject(retVal,false);
            return retObject;
        }

        void GuardsStatusesType::setTriggerPose(const PoseType & poseType_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setTriggerPose of crcl.base.GuardsStatusesType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," GuardsStatusesType::setTriggerPose jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setTriggerPose", "(Lcrcl/base/PoseType;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," GuardsStatusesType::setTriggerPose jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.GuardsStatusesType has no method named setTriggerPose with signature (Lcrcl/base/PoseType;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,poseType_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," GuardsStatusesType::setTriggerPose jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }
        static jclass getNewGuardsStatusesTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/GuardsStatusesType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/GuardsStatusesType" << std::endl;
            }
            return clss;
        }
        
        static jclass GuardsStatusesTypeClass = NULL;
        static inline jclass getGuardsStatusesTypeClass() {
            if (GuardsStatusesTypeClass != NULL) {
                return GuardsStatusesTypeClass;
            }
            GuardsStatusesTypeClass = getNewGuardsStatusesTypeClass();
            return GuardsStatusesTypeClass;
        }

    // class_index = 7 clss=class crcl.base.ParameterSettingType

        
        // get JNI handle for class crcl.base.ParameterSettingType
        static inline jclass getParameterSettingTypeClass();
        
        ParameterSettingType::ParameterSettingType(jobject _jthis, bool copy): DataThingType(_jthis,copy) {
                
        }
        
        ParameterSettingType::ParameterSettingType(const ParameterSettingType &objref): DataThingType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class ParameterSettingType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class ParameterSettingType jthis=",jthis);
            }
        }
        
        ParameterSettingType ParameterSettingType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getParameterSettingTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            ParameterSettingType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool ParameterSettingType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getParameterSettingTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        ParameterSettingType::ParameterSettingType() : DataThingType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getParameterSettingTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class ParameterSettingType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ParameterSettingType::setTriggerPose jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new ParameterSettingType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new ParameterSettingType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.ParameterSettingType
        ParameterSettingType::~ParameterSettingType() {
        	// Place-holder for later extensibility.
        }


        jstring ParameterSettingType::getParameterName() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getParameterName of crcl.base.ParameterSettingType with jthis == NULL." << std::endl;
                return NULL;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ParameterSettingType::getParameterName jthis=",jthis);
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getParameterName", "()Ljava/lang/String;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ParameterSettingType::getParameterName jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ParameterSettingType has no method named getParameterName with signature ()Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ParameterSettingType::getParameterName jthrowable t=",t);
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

        void ParameterSettingType::setParameterName(jstring string_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setParameterName of crcl.base.ParameterSettingType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ParameterSettingType::setParameterName jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setParameterName", "(Ljava/lang/String;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ParameterSettingType::setParameterName jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ParameterSettingType has no method named setParameterName with signature (Ljava/lang/String;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ParameterSettingType::setParameterName jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        // Easy call alternative for setParameterName
        void ParameterSettingType::setParameterName(const char * easyArg_0) {
            // convenience method, converts to/from JNI types to common C++ types.
            if(jthis == NULL) {
                std::cerr << "Call of method setParameterName of crcl.base.ParameterSettingType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ParameterSettingType::setParameterName jthis=",jthis);
            
                    
            jstring string_0 = env->NewStringUTF(easyArg_0);
            setParameterName(string_0);
            jobjectRefType ref_0 = env->GetObjectRefType(string_0);
            if(ref_0 == JNIGlobalRefType) {
                env->DeleteGlobalRef(string_0);
            }
            
            releaseEnv(env);
        }


        jstring ParameterSettingType::getParameterValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getParameterValue of crcl.base.ParameterSettingType with jthis == NULL." << std::endl;
                return NULL;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ParameterSettingType::getParameterValue jthis=",jthis);
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getParameterValue", "()Ljava/lang/String;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ParameterSettingType::getParameterValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ParameterSettingType has no method named getParameterValue with signature ()Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ParameterSettingType::getParameterValue jthrowable t=",t);
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

        void ParameterSettingType::setParameterValue(jstring string_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setParameterValue of crcl.base.ParameterSettingType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ParameterSettingType::setParameterValue jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setParameterValue", "(Ljava/lang/String;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ParameterSettingType::setParameterValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ParameterSettingType has no method named setParameterValue with signature (Ljava/lang/String;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ParameterSettingType::setParameterValue jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        // Easy call alternative for setParameterValue
        void ParameterSettingType::setParameterValue(const char * easyArg_0) {
            // convenience method, converts to/from JNI types to common C++ types.
            if(jthis == NULL) {
                std::cerr << "Call of method setParameterValue of crcl.base.ParameterSettingType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ParameterSettingType::setParameterValue jthis=",jthis);
            
                    
            jstring string_0 = env->NewStringUTF(easyArg_0);
            setParameterValue(string_0);
            jobjectRefType ref_0 = env->GetObjectRefType(string_0);
            if(ref_0 == JNIGlobalRefType) {
                env->DeleteGlobalRef(string_0);
            }
            
            releaseEnv(env);
        }

        static jclass getNewParameterSettingTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/ParameterSettingType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/ParameterSettingType" << std::endl;
            }
            return clss;
        }
        
        static jclass ParameterSettingTypeClass = NULL;
        static inline jclass getParameterSettingTypeClass() {
            if (ParameterSettingTypeClass != NULL) {
                return ParameterSettingTypeClass;
            }
            ParameterSettingTypeClass = getNewParameterSettingTypeClass();
            return ParameterSettingTypeClass;
        }

    // class_index = 8 clss=class crcl.base.PoseType

        
        // get JNI handle for class crcl.base.PoseType
        static inline jclass getPoseTypeClass();
        
        PoseType::PoseType(jobject _jthis, bool copy): DataThingType(_jthis,copy) {
                
        }
        
        PoseType::PoseType(const PoseType &objref): DataThingType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class PoseType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class PoseType jthis=",jthis);
            }
        }
        
        PoseType PoseType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getPoseTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            PoseType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool PoseType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getPoseTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        PoseType::PoseType() : DataThingType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getPoseTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class PoseType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," PoseType::setParameterValue jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new PoseType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new PoseType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.PoseType
        PoseType::~PoseType() {
        	// Place-holder for later extensibility.
        }


        void PoseType::setPoint(const PointType & pointType_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setPoint of crcl.base.PoseType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," PoseType::setPoint jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setPoint", "(Lcrcl/base/PointType;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," PoseType::setPoint jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.PoseType has no method named setPoint with signature (Lcrcl/base/PointType;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,pointType_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," PoseType::setPoint jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        VectorType PoseType::getXAxis() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getXAxis of crcl.base.PoseType with jthis == NULL." << std::endl;
                static VectorType nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," PoseType::getXAxis jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getXAxis", "()Lcrcl/base/VectorType;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," PoseType::getXAxis jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.PoseType has no method named getXAxis with signature ()Lcrcl/base/VectorType;." << std::endl;
                    static VectorType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," PoseType::getXAxis jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            VectorType retObject(retVal,false);
            return retObject;
        }

        void PoseType::setXAxis(const VectorType & vectorType_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setXAxis of crcl.base.PoseType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," PoseType::setXAxis jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setXAxis", "(Lcrcl/base/VectorType;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," PoseType::setXAxis jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.PoseType has no method named setXAxis with signature (Lcrcl/base/VectorType;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,vectorType_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," PoseType::setXAxis jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        VectorType PoseType::getZAxis() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getZAxis of crcl.base.PoseType with jthis == NULL." << std::endl;
                static VectorType nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," PoseType::getZAxis jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getZAxis", "()Lcrcl/base/VectorType;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," PoseType::getZAxis jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.PoseType has no method named getZAxis with signature ()Lcrcl/base/VectorType;." << std::endl;
                    static VectorType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," PoseType::getZAxis jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            VectorType retObject(retVal,false);
            return retObject;
        }

        void PoseType::setZAxis(const VectorType & vectorType_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setZAxis of crcl.base.PoseType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," PoseType::setZAxis jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setZAxis", "(Lcrcl/base/VectorType;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," PoseType::setZAxis jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.PoseType has no method named setZAxis with signature (Lcrcl/base/VectorType;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,vectorType_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," PoseType::setZAxis jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        PointType PoseType::getPoint() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getPoint of crcl.base.PoseType with jthis == NULL." << std::endl;
                static PointType nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," PoseType::getPoint jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getPoint", "()Lcrcl/base/PointType;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," PoseType::getPoint jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.PoseType has no method named getPoint with signature ()Lcrcl/base/PointType;." << std::endl;
                    static PointType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," PoseType::getPoint jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            PointType retObject(retVal,false);
            return retObject;
        }
        static jclass getNewPoseTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/PoseType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/PoseType" << std::endl;
            }
            return clss;
        }
        
        static jclass PoseTypeClass = NULL;
        static inline jclass getPoseTypeClass() {
            if (PoseTypeClass != NULL) {
                return PoseTypeClass;
            }
            PoseTypeClass = getNewPoseTypeClass();
            return PoseTypeClass;
        }

    // class_index = 9 clss=class crcl.base.TransSpeedType

        
        // get JNI handle for class crcl.base.TransSpeedType
        static inline jclass getTransSpeedTypeClass();
        
        TransSpeedType::TransSpeedType(jobject _jthis, bool copy): DataThingType(_jthis,copy) {
                
        }
        
        TransSpeedType::TransSpeedType(const TransSpeedType &objref): DataThingType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class TransSpeedType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class TransSpeedType jthis=",jthis);
            }
        }
        
        TransSpeedType TransSpeedType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getTransSpeedTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            TransSpeedType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool TransSpeedType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getTransSpeedTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        TransSpeedType::TransSpeedType() : DataThingType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getTransSpeedTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class TransSpeedType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," TransSpeedType::getPoint jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new TransSpeedType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new TransSpeedType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.TransSpeedType
        TransSpeedType::~TransSpeedType() {
        	// Place-holder for later extensibility.
        }

        static jclass getNewTransSpeedTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/TransSpeedType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/TransSpeedType" << std::endl;
            }
            return clss;
        }
        
        static jclass TransSpeedTypeClass = NULL;
        static inline jclass getTransSpeedTypeClass() {
            if (TransSpeedTypeClass != NULL) {
                return TransSpeedTypeClass;
            }
            TransSpeedTypeClass = getNewTransSpeedTypeClass();
            return TransSpeedTypeClass;
        }
        } // end namespace base
    } // end namespace crcl

    
    
        // end namespace crclj
    }
    
