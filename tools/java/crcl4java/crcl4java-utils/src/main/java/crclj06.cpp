
#include <jni.h>
#include <cstdlib>
#include <iostream>
#include "crclj.h"

namespace crclj {

    



// start_segment_index = 60
// start_segment_index = 70
// segment_index = 6
// classesSegList=[class crcl.base.EnableGripperType, class crcl.base.WrenchType, class crcl.base.CountSensorStatusType, class crcl.base.ForceUnitEnumType, class crcl.base.MoveScrewType, class crcl.base.SetForceUnitsType, class crcl.base.TransSpeedAbsoluteType, class crcl.base.RotAccelAbsoluteType, class crcl.base.GuardType, class crcl.base.DisableRobotParameterStatusType]


// class_index = 0 clss=class crcl.base.EnableGripperType

    namespace crcl{
        namespace base{
        
        // get JNI handle for class crcl.base.EnableGripperType
        static inline jclass getEnableGripperTypeClass();
        
        EnableGripperType::EnableGripperType(jobject _jthis, bool copy): MiddleCommandType(_jthis,copy) {
                
        }
        
        EnableGripperType::EnableGripperType(const EnableGripperType &objref): MiddleCommandType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class EnableGripperType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class EnableGripperType jthis=",jthis);
            }
        }
        
        EnableGripperType EnableGripperType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getEnableGripperTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            EnableGripperType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool EnableGripperType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getEnableGripperTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        EnableGripperType::EnableGripperType() : MiddleCommandType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getEnableGripperTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class EnableGripperType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," EnableGripperType::setMoveStraight jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new EnableGripperType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new EnableGripperType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.EnableGripperType
        EnableGripperType::~EnableGripperType() {
        	// Place-holder for later extensibility.
        }


        jstring EnableGripperType::getGripperName() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getGripperName of crcl.base.EnableGripperType with jthis == NULL." << std::endl;
                return NULL;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," EnableGripperType::getGripperName jthis=",jthis);
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getGripperName", "()Ljava/lang/String;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," EnableGripperType::getGripperName jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.EnableGripperType has no method named getGripperName with signature ()Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," EnableGripperType::getGripperName jthrowable t=",t);
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

        void EnableGripperType::setGripperName(jstring string_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setGripperName of crcl.base.EnableGripperType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," EnableGripperType::setGripperName jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setGripperName", "(Ljava/lang/String;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," EnableGripperType::setGripperName jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.EnableGripperType has no method named setGripperName with signature (Ljava/lang/String;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," EnableGripperType::setGripperName jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        // Easy call alternative for setGripperName
        void EnableGripperType::setGripperName(const char * easyArg_0) {
            // convenience method, converts to/from JNI types to common C++ types.
            if(jthis == NULL) {
                std::cerr << "Call of method setGripperName of crcl.base.EnableGripperType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," EnableGripperType::setGripperName jthis=",jthis);
            
                    
            jstring string_0 = env->NewStringUTF(easyArg_0);
            setGripperName(string_0);
            jobjectRefType ref_0 = env->GetObjectRefType(string_0);
            if(ref_0 == JNIGlobalRefType) {
                env->DeleteGlobalRef(string_0);
            }
            
            releaseEnv(env);
        }


        ::crclj::java::util::List EnableGripperType::getGripperOption() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getGripperOption of crcl.base.EnableGripperType with jthis == NULL." << std::endl;
                static ::crclj::java::util::List nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," EnableGripperType::getGripperOption jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getGripperOption", "()Ljava/util/List;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," EnableGripperType::getGripperOption jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.EnableGripperType has no method named getGripperOption with signature ()Ljava/util/List;." << std::endl;
                    static ::crclj::java::util::List nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," EnableGripperType::getGripperOption jthrowable t=",t);
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
        static jclass getNewEnableGripperTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/EnableGripperType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/EnableGripperType" << std::endl;
            }
            return clss;
        }
        
        static jclass EnableGripperTypeClass = NULL;
        static inline jclass getEnableGripperTypeClass() {
            if (EnableGripperTypeClass != NULL) {
                return EnableGripperTypeClass;
            }
            EnableGripperTypeClass = getNewEnableGripperTypeClass();
            return EnableGripperTypeClass;
        }

    // class_index = 1 clss=class crcl.base.WrenchType

        
        // get JNI handle for class crcl.base.WrenchType
        static inline jclass getWrenchTypeClass();
        
        WrenchType::WrenchType(jobject _jthis, bool copy): DataThingType(_jthis,copy) {
                
        }
        
        WrenchType::WrenchType(const WrenchType &objref): DataThingType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class WrenchType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class WrenchType jthis=",jthis);
            }
        }
        
        WrenchType WrenchType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getWrenchTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            WrenchType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool WrenchType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getWrenchTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        WrenchType::WrenchType() : DataThingType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getWrenchTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class WrenchType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," WrenchType::getGripperOption jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new WrenchType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new WrenchType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.WrenchType
        WrenchType::~WrenchType() {
        	// Place-holder for later extensibility.
        }


        VectorType WrenchType::getForce() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getForce of crcl.base.WrenchType with jthis == NULL." << std::endl;
                static VectorType nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," WrenchType::getForce jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getForce", "()Lcrcl/base/VectorType;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," WrenchType::getForce jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.WrenchType has no method named getForce with signature ()Lcrcl/base/VectorType;." << std::endl;
                    static VectorType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," WrenchType::getForce jthrowable t=",t);
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

        void WrenchType::setForce(const VectorType & vectorType_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setForce of crcl.base.WrenchType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," WrenchType::setForce jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setForce", "(Lcrcl/base/VectorType;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," WrenchType::setForce jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.WrenchType has no method named setForce with signature (Lcrcl/base/VectorType;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,vectorType_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," WrenchType::setForce jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        VectorType WrenchType::getMoment() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getMoment of crcl.base.WrenchType with jthis == NULL." << std::endl;
                static VectorType nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," WrenchType::getMoment jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getMoment", "()Lcrcl/base/VectorType;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," WrenchType::getMoment jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.WrenchType has no method named getMoment with signature ()Lcrcl/base/VectorType;." << std::endl;
                    static VectorType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," WrenchType::getMoment jthrowable t=",t);
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

        void WrenchType::setMoment(const VectorType & vectorType_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setMoment of crcl.base.WrenchType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," WrenchType::setMoment jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setMoment", "(Lcrcl/base/VectorType;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," WrenchType::setMoment jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.WrenchType has no method named setMoment with signature (Lcrcl/base/VectorType;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,vectorType_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," WrenchType::setMoment jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }
        static jclass getNewWrenchTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/WrenchType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/WrenchType" << std::endl;
            }
            return clss;
        }
        
        static jclass WrenchTypeClass = NULL;
        static inline jclass getWrenchTypeClass() {
            if (WrenchTypeClass != NULL) {
                return WrenchTypeClass;
            }
            WrenchTypeClass = getNewWrenchTypeClass();
            return WrenchTypeClass;
        }

    // class_index = 2 clss=class crcl.base.CountSensorStatusType

        
        // get JNI handle for class crcl.base.CountSensorStatusType
        static inline jclass getCountSensorStatusTypeClass();
        
        CountSensorStatusType::CountSensorStatusType(jobject _jthis, bool copy): SensorStatusType(_jthis,copy) {
                
        }
        
        CountSensorStatusType::CountSensorStatusType(const CountSensorStatusType &objref): SensorStatusType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class CountSensorStatusType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class CountSensorStatusType jthis=",jthis);
            }
        }
        
        CountSensorStatusType CountSensorStatusType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getCountSensorStatusTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            CountSensorStatusType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool CountSensorStatusType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getCountSensorStatusTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        CountSensorStatusType::CountSensorStatusType() : SensorStatusType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getCountSensorStatusTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class CountSensorStatusType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CountSensorStatusType::setMoment jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new CountSensorStatusType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new CountSensorStatusType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.CountSensorStatusType
        CountSensorStatusType::~CountSensorStatusType() {
        	// Place-holder for later extensibility.
        }


        jint CountSensorStatusType::getCountValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getCountValue of crcl.base.CountSensorStatusType with jthis == NULL." << std::endl;
                return (jint) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CountSensorStatusType::getCountValue jthis=",jthis);
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getCountValue", "()I");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CountSensorStatusType::getCountValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.CountSensorStatusType has no method named getCountValue with signature ()I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallIntMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CountSensorStatusType::getCountValue jthrowable t=",t);
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

        void CountSensorStatusType::setCountValue(jint int_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setCountValue of crcl.base.CountSensorStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CountSensorStatusType::setCountValue jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setCountValue", "(I)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CountSensorStatusType::setCountValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.CountSensorStatusType has no method named setCountValue with signature (I)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,int_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CountSensorStatusType::setCountValue jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }
        static jclass getNewCountSensorStatusTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/CountSensorStatusType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/CountSensorStatusType" << std::endl;
            }
            return clss;
        }
        
        static jclass CountSensorStatusTypeClass = NULL;
        static inline jclass getCountSensorStatusTypeClass() {
            if (CountSensorStatusTypeClass != NULL) {
                return CountSensorStatusTypeClass;
            }
            CountSensorStatusTypeClass = getNewCountSensorStatusTypeClass();
            return CountSensorStatusTypeClass;
        }

    // class_index = 3 clss=class crcl.base.ForceUnitEnumType

        
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
                        DebugPrintJObject(__FILE__,__LINE__," ForceUnitEnumType::setCountValue jthis=",t);
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

    // class_index = 4 clss=class crcl.base.MoveScrewType

        
        // get JNI handle for class crcl.base.MoveScrewType
        static inline jclass getMoveScrewTypeClass();
        
        MoveScrewType::MoveScrewType(jobject _jthis, bool copy): MiddleCommandType(_jthis,copy) {
                
        }
        
        MoveScrewType::MoveScrewType(const MoveScrewType &objref): MiddleCommandType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class MoveScrewType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class MoveScrewType jthis=",jthis);
            }
        }
        
        MoveScrewType MoveScrewType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getMoveScrewTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            MoveScrewType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool MoveScrewType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getMoveScrewTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        MoveScrewType::MoveScrewType() : MiddleCommandType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getMoveScrewTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class MoveScrewType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," MoveScrewType::fromValue jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new MoveScrewType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new MoveScrewType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.MoveScrewType
        MoveScrewType::~MoveScrewType() {
        	// Place-holder for later extensibility.
        }


        PoseType MoveScrewType::getStartPosition() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getStartPosition of crcl.base.MoveScrewType with jthis == NULL." << std::endl;
                static PoseType nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," MoveScrewType::getStartPosition jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getStartPosition", "()Lcrcl/base/PoseType;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," MoveScrewType::getStartPosition jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.MoveScrewType has no method named getStartPosition with signature ()Lcrcl/base/PoseType;." << std::endl;
                    static PoseType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," MoveScrewType::getStartPosition jthrowable t=",t);
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

        void MoveScrewType::setStartPosition(const PoseType & poseType_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setStartPosition of crcl.base.MoveScrewType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," MoveScrewType::setStartPosition jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setStartPosition", "(Lcrcl/base/PoseType;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," MoveScrewType::setStartPosition jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.MoveScrewType has no method named setStartPosition with signature (Lcrcl/base/PoseType;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,poseType_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," MoveScrewType::setStartPosition jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        PointType MoveScrewType::getAxisPoint() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getAxisPoint of crcl.base.MoveScrewType with jthis == NULL." << std::endl;
                static PointType nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," MoveScrewType::getAxisPoint jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getAxisPoint", "()Lcrcl/base/PointType;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," MoveScrewType::getAxisPoint jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.MoveScrewType has no method named getAxisPoint with signature ()Lcrcl/base/PointType;." << std::endl;
                    static PointType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," MoveScrewType::getAxisPoint jthrowable t=",t);
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

        void MoveScrewType::setAxisPoint(const PointType & pointType_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setAxisPoint of crcl.base.MoveScrewType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," MoveScrewType::setAxisPoint jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setAxisPoint", "(Lcrcl/base/PointType;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," MoveScrewType::setAxisPoint jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.MoveScrewType has no method named setAxisPoint with signature (Lcrcl/base/PointType;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,pointType_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," MoveScrewType::setAxisPoint jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        ::crclj::java::lang::Double MoveScrewType::getAxialDistanceFree() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getAxialDistanceFree of crcl.base.MoveScrewType with jthis == NULL." << std::endl;
                static ::crclj::java::lang::Double nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," MoveScrewType::getAxialDistanceFree jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getAxialDistanceFree", "()Ljava/lang/Double;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," MoveScrewType::getAxialDistanceFree jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.MoveScrewType has no method named getAxialDistanceFree with signature ()Ljava/lang/Double;." << std::endl;
                    static ::crclj::java::lang::Double nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," MoveScrewType::getAxialDistanceFree jthrowable t=",t);
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

        void MoveScrewType::setAxialDistanceFree(const ::crclj::java::lang::Double & double_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setAxialDistanceFree of crcl.base.MoveScrewType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," MoveScrewType::setAxialDistanceFree jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setAxialDistanceFree", "(Ljava/lang/Double;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," MoveScrewType::setAxialDistanceFree jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.MoveScrewType has no method named setAxialDistanceFree with signature (Ljava/lang/Double;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,double_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," MoveScrewType::setAxialDistanceFree jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        jdouble MoveScrewType::getAxialDistanceScrew() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getAxialDistanceScrew of crcl.base.MoveScrewType with jthis == NULL." << std::endl;
                return (jdouble) -1.0;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," MoveScrewType::getAxialDistanceScrew jthis=",jthis);
            jdouble retVal= (jdouble) -1.0;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getAxialDistanceScrew", "()D");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," MoveScrewType::getAxialDistanceScrew jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.MoveScrewType has no method named getAxialDistanceScrew with signature ()D." << std::endl;
                    return (jdouble) -1.0;
                } else {
                    retVal= (jdouble)  env->CallDoubleMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," MoveScrewType::getAxialDistanceScrew jthrowable t=",t);
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

        void MoveScrewType::setAxialDistanceScrew(jdouble double_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setAxialDistanceScrew of crcl.base.MoveScrewType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," MoveScrewType::setAxialDistanceScrew jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setAxialDistanceScrew", "(D)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," MoveScrewType::setAxialDistanceScrew jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.MoveScrewType has no method named setAxialDistanceScrew with signature (D)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,double_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," MoveScrewType::setAxialDistanceScrew jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        jdouble MoveScrewType::getTurn() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getTurn of crcl.base.MoveScrewType with jthis == NULL." << std::endl;
                return (jdouble) -1.0;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," MoveScrewType::getTurn jthis=",jthis);
            jdouble retVal= (jdouble) -1.0;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getTurn", "()D");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," MoveScrewType::getTurn jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.MoveScrewType has no method named getTurn with signature ()D." << std::endl;
                    return (jdouble) -1.0;
                } else {
                    retVal= (jdouble)  env->CallDoubleMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," MoveScrewType::getTurn jthrowable t=",t);
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

        void MoveScrewType::setTurn(jdouble double_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setTurn of crcl.base.MoveScrewType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," MoveScrewType::setTurn jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setTurn", "(D)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," MoveScrewType::setTurn jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.MoveScrewType has no method named setTurn with signature (D)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,double_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," MoveScrewType::setTurn jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }
        static jclass getNewMoveScrewTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/MoveScrewType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/MoveScrewType" << std::endl;
            }
            return clss;
        }
        
        static jclass MoveScrewTypeClass = NULL;
        static inline jclass getMoveScrewTypeClass() {
            if (MoveScrewTypeClass != NULL) {
                return MoveScrewTypeClass;
            }
            MoveScrewTypeClass = getNewMoveScrewTypeClass();
            return MoveScrewTypeClass;
        }

    // class_index = 5 clss=class crcl.base.SetForceUnitsType

        
        // get JNI handle for class crcl.base.SetForceUnitsType
        static inline jclass getSetForceUnitsTypeClass();
        
        SetForceUnitsType::SetForceUnitsType(jobject _jthis, bool copy): MiddleCommandType(_jthis,copy) {
                
        }
        
        SetForceUnitsType::SetForceUnitsType(const SetForceUnitsType &objref): MiddleCommandType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class SetForceUnitsType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class SetForceUnitsType jthis=",jthis);
            }
        }
        
        SetForceUnitsType SetForceUnitsType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getSetForceUnitsTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            SetForceUnitsType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool SetForceUnitsType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getSetForceUnitsTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        SetForceUnitsType::SetForceUnitsType() : MiddleCommandType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getSetForceUnitsTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class SetForceUnitsType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," SetForceUnitsType::setTurn jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new SetForceUnitsType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new SetForceUnitsType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.SetForceUnitsType
        SetForceUnitsType::~SetForceUnitsType() {
        	// Place-holder for later extensibility.
        }


        ForceUnitEnumType SetForceUnitsType::getUnitName() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getUnitName of crcl.base.SetForceUnitsType with jthis == NULL." << std::endl;
                static ForceUnitEnumType nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," SetForceUnitsType::getUnitName jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getUnitName", "()Lcrcl/base/ForceUnitEnumType;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," SetForceUnitsType::getUnitName jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.SetForceUnitsType has no method named getUnitName with signature ()Lcrcl/base/ForceUnitEnumType;." << std::endl;
                    static ForceUnitEnumType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," SetForceUnitsType::getUnitName jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
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

        void SetForceUnitsType::setUnitName(const ForceUnitEnumType & forceUnitEnumType_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setUnitName of crcl.base.SetForceUnitsType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," SetForceUnitsType::setUnitName jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setUnitName", "(Lcrcl/base/ForceUnitEnumType;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," SetForceUnitsType::setUnitName jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.SetForceUnitsType has no method named setUnitName with signature (Lcrcl/base/ForceUnitEnumType;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,forceUnitEnumType_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," SetForceUnitsType::setUnitName jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }
        static jclass getNewSetForceUnitsTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/SetForceUnitsType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/SetForceUnitsType" << std::endl;
            }
            return clss;
        }
        
        static jclass SetForceUnitsTypeClass = NULL;
        static inline jclass getSetForceUnitsTypeClass() {
            if (SetForceUnitsTypeClass != NULL) {
                return SetForceUnitsTypeClass;
            }
            SetForceUnitsTypeClass = getNewSetForceUnitsTypeClass();
            return SetForceUnitsTypeClass;
        }

    // class_index = 6 clss=class crcl.base.TransSpeedAbsoluteType

        
        // get JNI handle for class crcl.base.TransSpeedAbsoluteType
        static inline jclass getTransSpeedAbsoluteTypeClass();
        
        TransSpeedAbsoluteType::TransSpeedAbsoluteType(jobject _jthis, bool copy): TransSpeedType(_jthis,copy) {
                
        }
        
        TransSpeedAbsoluteType::TransSpeedAbsoluteType(const TransSpeedAbsoluteType &objref): TransSpeedType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class TransSpeedAbsoluteType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class TransSpeedAbsoluteType jthis=",jthis);
            }
        }
        
        TransSpeedAbsoluteType TransSpeedAbsoluteType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getTransSpeedAbsoluteTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            TransSpeedAbsoluteType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool TransSpeedAbsoluteType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getTransSpeedAbsoluteTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        TransSpeedAbsoluteType::TransSpeedAbsoluteType() : TransSpeedType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getTransSpeedAbsoluteTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class TransSpeedAbsoluteType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," TransSpeedAbsoluteType::setUnitName jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new TransSpeedAbsoluteType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new TransSpeedAbsoluteType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.TransSpeedAbsoluteType
        TransSpeedAbsoluteType::~TransSpeedAbsoluteType() {
        	// Place-holder for later extensibility.
        }


        jdouble TransSpeedAbsoluteType::getSetting() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getSetting of crcl.base.TransSpeedAbsoluteType with jthis == NULL." << std::endl;
                return (jdouble) -1.0;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," TransSpeedAbsoluteType::getSetting jthis=",jthis);
            jdouble retVal= (jdouble) -1.0;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getSetting", "()D");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," TransSpeedAbsoluteType::getSetting jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.TransSpeedAbsoluteType has no method named getSetting with signature ()D." << std::endl;
                    return (jdouble) -1.0;
                } else {
                    retVal= (jdouble)  env->CallDoubleMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," TransSpeedAbsoluteType::getSetting jthrowable t=",t);
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

        void TransSpeedAbsoluteType::setSetting(jdouble double_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setSetting of crcl.base.TransSpeedAbsoluteType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," TransSpeedAbsoluteType::setSetting jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setSetting", "(D)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," TransSpeedAbsoluteType::setSetting jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.TransSpeedAbsoluteType has no method named setSetting with signature (D)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,double_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," TransSpeedAbsoluteType::setSetting jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }
        static jclass getNewTransSpeedAbsoluteTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/TransSpeedAbsoluteType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/TransSpeedAbsoluteType" << std::endl;
            }
            return clss;
        }
        
        static jclass TransSpeedAbsoluteTypeClass = NULL;
        static inline jclass getTransSpeedAbsoluteTypeClass() {
            if (TransSpeedAbsoluteTypeClass != NULL) {
                return TransSpeedAbsoluteTypeClass;
            }
            TransSpeedAbsoluteTypeClass = getNewTransSpeedAbsoluteTypeClass();
            return TransSpeedAbsoluteTypeClass;
        }

    // class_index = 7 clss=class crcl.base.RotAccelAbsoluteType

        
        // get JNI handle for class crcl.base.RotAccelAbsoluteType
        static inline jclass getRotAccelAbsoluteTypeClass();
        
        RotAccelAbsoluteType::RotAccelAbsoluteType(jobject _jthis, bool copy): RotAccelType(_jthis,copy) {
                
        }
        
        RotAccelAbsoluteType::RotAccelAbsoluteType(const RotAccelAbsoluteType &objref): RotAccelType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class RotAccelAbsoluteType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class RotAccelAbsoluteType jthis=",jthis);
            }
        }
        
        RotAccelAbsoluteType RotAccelAbsoluteType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getRotAccelAbsoluteTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            RotAccelAbsoluteType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool RotAccelAbsoluteType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getRotAccelAbsoluteTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        RotAccelAbsoluteType::RotAccelAbsoluteType() : RotAccelType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getRotAccelAbsoluteTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class RotAccelAbsoluteType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," RotAccelAbsoluteType::setSetting jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new RotAccelAbsoluteType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new RotAccelAbsoluteType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.RotAccelAbsoluteType
        RotAccelAbsoluteType::~RotAccelAbsoluteType() {
        	// Place-holder for later extensibility.
        }


        jdouble RotAccelAbsoluteType::getSetting() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getSetting of crcl.base.RotAccelAbsoluteType with jthis == NULL." << std::endl;
                return (jdouble) -1.0;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," RotAccelAbsoluteType::getSetting jthis=",jthis);
            jdouble retVal= (jdouble) -1.0;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getSetting", "()D");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," RotAccelAbsoluteType::getSetting jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.RotAccelAbsoluteType has no method named getSetting with signature ()D." << std::endl;
                    return (jdouble) -1.0;
                } else {
                    retVal= (jdouble)  env->CallDoubleMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," RotAccelAbsoluteType::getSetting jthrowable t=",t);
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

        void RotAccelAbsoluteType::setSetting(jdouble double_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setSetting of crcl.base.RotAccelAbsoluteType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," RotAccelAbsoluteType::setSetting jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setSetting", "(D)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," RotAccelAbsoluteType::setSetting jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.RotAccelAbsoluteType has no method named setSetting with signature (D)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,double_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," RotAccelAbsoluteType::setSetting jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }
        static jclass getNewRotAccelAbsoluteTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/RotAccelAbsoluteType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/RotAccelAbsoluteType" << std::endl;
            }
            return clss;
        }
        
        static jclass RotAccelAbsoluteTypeClass = NULL;
        static inline jclass getRotAccelAbsoluteTypeClass() {
            if (RotAccelAbsoluteTypeClass != NULL) {
                return RotAccelAbsoluteTypeClass;
            }
            RotAccelAbsoluteTypeClass = getNewRotAccelAbsoluteTypeClass();
            return RotAccelAbsoluteTypeClass;
        }

    // class_index = 8 clss=class crcl.base.GuardType

        
        // get JNI handle for class crcl.base.GuardType
        static inline jclass getGuardTypeClass();
        
        GuardType::GuardType(jobject _jthis, bool copy): DataThingType(_jthis,copy) {
                
        }
        
        GuardType::GuardType(const GuardType &objref): DataThingType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class GuardType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class GuardType jthis=",jthis);
            }
        }
        
        GuardType GuardType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getGuardTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            GuardType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool GuardType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getGuardTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        GuardType::GuardType() : DataThingType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getGuardTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class GuardType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," GuardType::setSetting jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new GuardType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new GuardType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.GuardType
        GuardType::~GuardType() {
        	// Place-holder for later extensibility.
        }


        jstring GuardType::getSensorID() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getSensorID of crcl.base.GuardType with jthis == NULL." << std::endl;
                return NULL;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," GuardType::getSensorID jthis=",jthis);
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getSensorID", "()Ljava/lang/String;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," GuardType::getSensorID jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.GuardType has no method named getSensorID with signature ()Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," GuardType::getSensorID jthrowable t=",t);
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

        void GuardType::setSensorID(jstring string_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setSensorID of crcl.base.GuardType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," GuardType::setSensorID jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setSensorID", "(Ljava/lang/String;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," GuardType::setSensorID jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.GuardType has no method named setSensorID with signature (Ljava/lang/String;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," GuardType::setSensorID jthrowable t=",t);
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
        void GuardType::setSensorID(const char * easyArg_0) {
            // convenience method, converts to/from JNI types to common C++ types.
            if(jthis == NULL) {
                std::cerr << "Call of method setSensorID of crcl.base.GuardType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," GuardType::setSensorID jthis=",jthis);
            
                    
            jstring string_0 = env->NewStringUTF(easyArg_0);
            setSensorID(string_0);
            jobjectRefType ref_0 = env->GetObjectRefType(string_0);
            if(ref_0 == JNIGlobalRefType) {
                env->DeleteGlobalRef(string_0);
            }
            
            releaseEnv(env);
        }


        jstring GuardType::getSubField() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getSubField of crcl.base.GuardType with jthis == NULL." << std::endl;
                return NULL;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," GuardType::getSubField jthis=",jthis);
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getSubField", "()Ljava/lang/String;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," GuardType::getSubField jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.GuardType has no method named getSubField with signature ()Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," GuardType::getSubField jthrowable t=",t);
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

        void GuardType::setSubField(jstring string_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setSubField of crcl.base.GuardType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," GuardType::setSubField jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setSubField", "(Ljava/lang/String;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," GuardType::setSubField jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.GuardType has no method named setSubField with signature (Ljava/lang/String;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," GuardType::setSubField jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        // Easy call alternative for setSubField
        void GuardType::setSubField(const char * easyArg_0) {
            // convenience method, converts to/from JNI types to common C++ types.
            if(jthis == NULL) {
                std::cerr << "Call of method setSubField of crcl.base.GuardType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," GuardType::setSubField jthis=",jthis);
            
                    
            jstring string_0 = env->NewStringUTF(easyArg_0);
            setSubField(string_0);
            jobjectRefType ref_0 = env->GetObjectRefType(string_0);
            if(ref_0 == JNIGlobalRefType) {
                env->DeleteGlobalRef(string_0);
            }
            
            releaseEnv(env);
        }


        GuardLimitEnumType GuardType::getLimitType() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getLimitType of crcl.base.GuardType with jthis == NULL." << std::endl;
                static GuardLimitEnumType nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," GuardType::getLimitType jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getLimitType", "()Lcrcl/base/GuardLimitEnumType;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," GuardType::getLimitType jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.GuardType has no method named getLimitType with signature ()Lcrcl/base/GuardLimitEnumType;." << std::endl;
                    static GuardLimitEnumType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," GuardType::getLimitType jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            GuardLimitEnumType retObject(retVal,false);
            return retObject;
        }

        void GuardType::setLimitType(const GuardLimitEnumType & guardLimitEnumType_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setLimitType of crcl.base.GuardType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," GuardType::setLimitType jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setLimitType", "(Lcrcl/base/GuardLimitEnumType;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," GuardType::setLimitType jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.GuardType has no method named setLimitType with signature (Lcrcl/base/GuardLimitEnumType;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,guardLimitEnumType_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," GuardType::setLimitType jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        ::crclj::java::lang::Double GuardType::getLimitValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getLimitValue of crcl.base.GuardType with jthis == NULL." << std::endl;
                static ::crclj::java::lang::Double nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," GuardType::getLimitValue jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getLimitValue", "()Ljava/lang/Double;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," GuardType::getLimitValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.GuardType has no method named getLimitValue with signature ()Ljava/lang/Double;." << std::endl;
                    static ::crclj::java::lang::Double nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," GuardType::getLimitValue jthrowable t=",t);
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

        void GuardType::setLimitValue(const ::crclj::java::lang::Double & double_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setLimitValue of crcl.base.GuardType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," GuardType::setLimitValue jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setLimitValue", "(Ljava/lang/Double;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," GuardType::setLimitValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.GuardType has no method named setLimitValue with signature (Ljava/lang/Double;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,double_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," GuardType::setLimitValue jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        ::crclj::java::lang::Long GuardType::getRecheckTimeMicroSeconds() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getRecheckTimeMicroSeconds of crcl.base.GuardType with jthis == NULL." << std::endl;
                static ::crclj::java::lang::Long nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," GuardType::getRecheckTimeMicroSeconds jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getRecheckTimeMicroSeconds", "()Ljava/lang/Long;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," GuardType::getRecheckTimeMicroSeconds jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.GuardType has no method named getRecheckTimeMicroSeconds with signature ()Ljava/lang/Long;." << std::endl;
                    static ::crclj::java::lang::Long nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," GuardType::getRecheckTimeMicroSeconds jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            ::crclj::java::lang::Long retObject(retVal,false);
            return retObject;
        }

        void GuardType::setRecheckTimeMicroSeconds(const ::crclj::java::lang::Long & long_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setRecheckTimeMicroSeconds of crcl.base.GuardType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," GuardType::setRecheckTimeMicroSeconds jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setRecheckTimeMicroSeconds", "(Ljava/lang/Long;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," GuardType::setRecheckTimeMicroSeconds jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.GuardType has no method named setRecheckTimeMicroSeconds with signature (Ljava/lang/Long;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,long_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," GuardType::setRecheckTimeMicroSeconds jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        ::crclj::java::lang::Long GuardType::getCheckCount() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getCheckCount of crcl.base.GuardType with jthis == NULL." << std::endl;
                static ::crclj::java::lang::Long nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," GuardType::getCheckCount jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getCheckCount", "()Ljava/lang/Long;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," GuardType::getCheckCount jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.GuardType has no method named getCheckCount with signature ()Ljava/lang/Long;." << std::endl;
                    static ::crclj::java::lang::Long nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," GuardType::getCheckCount jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            ::crclj::java::lang::Long retObject(retVal,false);
            return retObject;
        }

        void GuardType::setCheckCount(const ::crclj::java::lang::Long & long_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setCheckCount of crcl.base.GuardType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," GuardType::setCheckCount jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setCheckCount", "(Ljava/lang/Long;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," GuardType::setCheckCount jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.GuardType has no method named setCheckCount with signature (Ljava/lang/Long;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,long_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," GuardType::setCheckCount jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        ::crclj::java::lang::Long GuardType::getLastCheckTime() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getLastCheckTime of crcl.base.GuardType with jthis == NULL." << std::endl;
                static ::crclj::java::lang::Long nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," GuardType::getLastCheckTime jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getLastCheckTime", "()Ljava/lang/Long;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," GuardType::getLastCheckTime jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.GuardType has no method named getLastCheckTime with signature ()Ljava/lang/Long;." << std::endl;
                    static ::crclj::java::lang::Long nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," GuardType::getLastCheckTime jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            ::crclj::java::lang::Long retObject(retVal,false);
            return retObject;
        }

        void GuardType::setLastCheckTime(const ::crclj::java::lang::Long & long_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setLastCheckTime of crcl.base.GuardType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," GuardType::setLastCheckTime jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setLastCheckTime", "(Ljava/lang/Long;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," GuardType::setLastCheckTime jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.GuardType has no method named setLastCheckTime with signature (Ljava/lang/Long;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,long_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," GuardType::setLastCheckTime jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        ::crclj::java::lang::Double GuardType::getLastCheckValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getLastCheckValue of crcl.base.GuardType with jthis == NULL." << std::endl;
                static ::crclj::java::lang::Double nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," GuardType::getLastCheckValue jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getLastCheckValue", "()Ljava/lang/Double;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," GuardType::getLastCheckValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.GuardType has no method named getLastCheckValue with signature ()Ljava/lang/Double;." << std::endl;
                    static ::crclj::java::lang::Double nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," GuardType::getLastCheckValue jthrowable t=",t);
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

        void GuardType::setLastCheckValue(const ::crclj::java::lang::Double & double_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setLastCheckValue of crcl.base.GuardType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," GuardType::setLastCheckValue jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setLastCheckValue", "(Ljava/lang/Double;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," GuardType::setLastCheckValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.GuardType has no method named setLastCheckValue with signature (Ljava/lang/Double;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,double_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," GuardType::setLastCheckValue jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }
        static jclass getNewGuardTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/GuardType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/GuardType" << std::endl;
            }
            return clss;
        }
        
        static jclass GuardTypeClass = NULL;
        static inline jclass getGuardTypeClass() {
            if (GuardTypeClass != NULL) {
                return GuardTypeClass;
            }
            GuardTypeClass = getNewGuardTypeClass();
            return GuardTypeClass;
        }

    // class_index = 9 clss=class crcl.base.DisableRobotParameterStatusType

        
        // get JNI handle for class crcl.base.DisableRobotParameterStatusType
        static inline jclass getDisableRobotParameterStatusTypeClass();
        
        DisableRobotParameterStatusType::DisableRobotParameterStatusType(jobject _jthis, bool copy): MiddleCommandType(_jthis,copy) {
                
        }
        
        DisableRobotParameterStatusType::DisableRobotParameterStatusType(const DisableRobotParameterStatusType &objref): MiddleCommandType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class DisableRobotParameterStatusType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class DisableRobotParameterStatusType jthis=",jthis);
            }
        }
        
        DisableRobotParameterStatusType DisableRobotParameterStatusType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getDisableRobotParameterStatusTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            DisableRobotParameterStatusType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool DisableRobotParameterStatusType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getDisableRobotParameterStatusTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        DisableRobotParameterStatusType::DisableRobotParameterStatusType() : MiddleCommandType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getDisableRobotParameterStatusTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class DisableRobotParameterStatusType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," DisableRobotParameterStatusType::setLastCheckValue jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new DisableRobotParameterStatusType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new DisableRobotParameterStatusType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.DisableRobotParameterStatusType
        DisableRobotParameterStatusType::~DisableRobotParameterStatusType() {
        	// Place-holder for later extensibility.
        }


        jstring DisableRobotParameterStatusType::getRobotParameterName() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getRobotParameterName of crcl.base.DisableRobotParameterStatusType with jthis == NULL." << std::endl;
                return NULL;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," DisableRobotParameterStatusType::getRobotParameterName jthis=",jthis);
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getRobotParameterName", "()Ljava/lang/String;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," DisableRobotParameterStatusType::getRobotParameterName jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.DisableRobotParameterStatusType has no method named getRobotParameterName with signature ()Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," DisableRobotParameterStatusType::getRobotParameterName jthrowable t=",t);
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

        void DisableRobotParameterStatusType::setRobotParameterName(jstring string_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setRobotParameterName of crcl.base.DisableRobotParameterStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," DisableRobotParameterStatusType::setRobotParameterName jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setRobotParameterName", "(Ljava/lang/String;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," DisableRobotParameterStatusType::setRobotParameterName jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.DisableRobotParameterStatusType has no method named setRobotParameterName with signature (Ljava/lang/String;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," DisableRobotParameterStatusType::setRobotParameterName jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        // Easy call alternative for setRobotParameterName
        void DisableRobotParameterStatusType::setRobotParameterName(const char * easyArg_0) {
            // convenience method, converts to/from JNI types to common C++ types.
            if(jthis == NULL) {
                std::cerr << "Call of method setRobotParameterName of crcl.base.DisableRobotParameterStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," DisableRobotParameterStatusType::setRobotParameterName jthis=",jthis);
            
                    
            jstring string_0 = env->NewStringUTF(easyArg_0);
            setRobotParameterName(string_0);
            jobjectRefType ref_0 = env->GetObjectRefType(string_0);
            if(ref_0 == JNIGlobalRefType) {
                env->DeleteGlobalRef(string_0);
            }
            
            releaseEnv(env);
        }

        static jclass getNewDisableRobotParameterStatusTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/DisableRobotParameterStatusType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/DisableRobotParameterStatusType" << std::endl;
            }
            return clss;
        }
        
        static jclass DisableRobotParameterStatusTypeClass = NULL;
        static inline jclass getDisableRobotParameterStatusTypeClass() {
            if (DisableRobotParameterStatusTypeClass != NULL) {
                return DisableRobotParameterStatusTypeClass;
            }
            DisableRobotParameterStatusTypeClass = getNewDisableRobotParameterStatusTypeClass();
            return DisableRobotParameterStatusTypeClass;
        }
        } // end namespace base
    } // end namespace crcl

    
    
        // end namespace crclj
    }
    
