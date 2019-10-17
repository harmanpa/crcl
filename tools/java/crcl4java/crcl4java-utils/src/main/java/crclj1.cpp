
#include <jni.h>
#include <cstdlib>
#include <iostream>
#include "crclj.h"

namespace crclj {

    



// start_segment_index = 10
// start_segment_index = 20
// segment_index = 1
// classesSegList=[class crcl.base.RotAccelAbsoluteType, class crcl.base.GuardType, class crcl.base.MoveToType, class crcl.base.GuardLimitEnumType, class crcl.base.RunProgramType, class crcl.base.ForceTorqueSensorStatusType, class crcl.base.SetAngleUnitsType, class crcl.base.CloseToolChangerType, class crcl.base.EndCanonType, class crcl.base.GripperStatusType]


// class_index = 0 clss=class crcl.base.RotAccelAbsoluteType

    namespace crcl{
        namespace base{
        
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
                            DebugPrintJObject(__FILE__,__LINE__," RotAccelAbsoluteType::setOn jthis=",t);
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

    // class_index = 1 clss=class crcl.base.GuardType

        
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

    // class_index = 2 clss=class crcl.base.MoveToType

        
        // get JNI handle for class crcl.base.MoveToType
        static inline jclass getMoveToTypeClass();
        
        MoveToType::MoveToType(jobject _jthis, bool copy): MiddleCommandType(_jthis,copy) {
                
        }
        
        MoveToType::MoveToType(const MoveToType &objref): MiddleCommandType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class MoveToType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class MoveToType jthis=",jthis);
            }
        }
        
        MoveToType MoveToType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getMoveToTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            MoveToType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool MoveToType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getMoveToTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        MoveToType::MoveToType() : MiddleCommandType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getMoveToTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class MoveToType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," MoveToType::setLastCheckValue jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new MoveToType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new MoveToType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.MoveToType
        MoveToType::~MoveToType() {
        	// Place-holder for later extensibility.
        }


        jboolean MoveToType::isMoveStraight() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method isMoveStraight of crcl.base.MoveToType with jthis == NULL." << std::endl;
                return false;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," MoveToType::isMoveStraight jthis=",jthis);
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "isMoveStraight", "()Z");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," MoveToType::isMoveStraight jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.MoveToType has no method named isMoveStraight with signature ()Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallBooleanMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," MoveToType::isMoveStraight jthrowable t=",t);
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

        void MoveToType::setMoveStraight(jboolean boolean_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setMoveStraight of crcl.base.MoveToType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," MoveToType::setMoveStraight jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setMoveStraight", "(Z)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," MoveToType::setMoveStraight jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.MoveToType has no method named setMoveStraight with signature (Z)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,boolean_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," MoveToType::setMoveStraight jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        PoseType MoveToType::getEndPosition() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getEndPosition of crcl.base.MoveToType with jthis == NULL." << std::endl;
                static PoseType nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," MoveToType::getEndPosition jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getEndPosition", "()Lcrcl/base/PoseType;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," MoveToType::getEndPosition jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.MoveToType has no method named getEndPosition with signature ()Lcrcl/base/PoseType;." << std::endl;
                    static PoseType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," MoveToType::getEndPosition jthrowable t=",t);
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

        void MoveToType::setEndPosition(const PoseType & poseType_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setEndPosition of crcl.base.MoveToType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," MoveToType::setEndPosition jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setEndPosition", "(Lcrcl/base/PoseType;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," MoveToType::setEndPosition jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.MoveToType has no method named setEndPosition with signature (Lcrcl/base/PoseType;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,poseType_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," MoveToType::setEndPosition jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }
        static jclass getNewMoveToTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/MoveToType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/MoveToType" << std::endl;
            }
            return clss;
        }
        
        static jclass MoveToTypeClass = NULL;
        static inline jclass getMoveToTypeClass() {
            if (MoveToTypeClass != NULL) {
                return MoveToTypeClass;
            }
            MoveToTypeClass = getNewMoveToTypeClass();
            return MoveToTypeClass;
        }

    // class_index = 3 clss=class crcl.base.GuardLimitEnumType

        
        // get JNI handle for class crcl.base.GuardLimitEnumType
        static inline jclass getGuardLimitEnumTypeClass();
        
        GuardLimitEnumType::GuardLimitEnumType(jobject _jthis, bool copy): ::crclj::java::lang::Enum(_jthis,copy) {
                
        }
        
        GuardLimitEnumType::GuardLimitEnumType(const GuardLimitEnumType &objref): ::crclj::java::lang::Enum((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class GuardLimitEnumType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class GuardLimitEnumType jthis=",jthis);
            }
        }
        
        GuardLimitEnumType GuardLimitEnumType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getGuardLimitEnumTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            GuardLimitEnumType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool GuardLimitEnumType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getGuardLimitEnumTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        GuardLimitEnumType::GuardLimitEnumType() : ::crclj::java::lang::Enum((jobject)NULL,false) {
        JNIEnv *env =getEnv();
        static jclass cls = getGuardLimitEnumTypeClass();
        if (cls != NULL) {
            static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
            if (NULL == mid) {
                std::cerr << "Class GuardLimitEnumType has no method constructor signature ()V" << std::endl;
            } else {
                jthis = env->NewObject(cls, mid );
                jthrowable t = env->ExceptionOccurred();
                if(t != NULL) {
                    if(GetDebugJ4Cpp()) {
                        DebugPrintJObject(__FILE__,__LINE__," GuardLimitEnumType::setEndPosition jthis=",t);
                        env->ExceptionDescribe();
                    }
                    throw t;
                }
                if(jthis == NULL) {
                    std::cerr << "Call to create new GuardLimitEnumType with signature ()V returned null." << std::endl;
                    releaseEnv(env);
                    return;
                }
                jobjectRefType ref = env->GetObjectRefType(jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new GuardLimitEnumType jthis=",jthis);
                if(ref != JNIGlobalRefType) {
                    jthis = env->NewGlobalRef(jthis);
                }
            }
        }
        releaseEnv(env);
        }


        // Destructor for crcl.base.GuardLimitEnumType
        GuardLimitEnumType::~GuardLimitEnumType() {
        	// Place-holder for later extensibility.
        }


        // Field getter for OVER_MAX
        GuardLimitEnumType GuardLimitEnumType::getOVER_MAX() {
        JNIEnv *env =getEnv();
        static jclass cls = getGuardLimitEnumTypeClass();
        jobject retVal=NULL;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "OVER_MAX", "Lcrcl/base/GuardLimitEnumType;");
            if (NULL == fid) {
                std::cerr << "Class crcl.base.GuardLimitEnumType has no field named OVER_MAX with signature Lcrcl/base/GuardLimitEnumType;." << std::endl;
                static GuardLimitEnumType nullObject((jobject)NULL,false); return nullObject;
            } else {
                retVal= (jobject)  env->GetStaticObjectField( cls, fid );
            }
        }
        releaseEnv(env);
                    
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            GuardLimitEnumType retObject(retVal,false);
            return retObject;
        }

        // Field getter for UNDER_MIN
        GuardLimitEnumType GuardLimitEnumType::getUNDER_MIN() {
        JNIEnv *env =getEnv();
        static jclass cls = getGuardLimitEnumTypeClass();
        jobject retVal=NULL;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "UNDER_MIN", "Lcrcl/base/GuardLimitEnumType;");
            if (NULL == fid) {
                std::cerr << "Class crcl.base.GuardLimitEnumType has no field named UNDER_MIN with signature Lcrcl/base/GuardLimitEnumType;." << std::endl;
                static GuardLimitEnumType nullObject((jobject)NULL,false); return nullObject;
            } else {
                retVal= (jobject)  env->GetStaticObjectField( cls, fid );
            }
        }
        releaseEnv(env);
                    
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            GuardLimitEnumType retObject(retVal,false);
            return retObject;
        }

        // Field getter for INCREASE_OVER_LIMIT
        GuardLimitEnumType GuardLimitEnumType::getINCREASE_OVER_LIMIT() {
        JNIEnv *env =getEnv();
        static jclass cls = getGuardLimitEnumTypeClass();
        jobject retVal=NULL;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "INCREASE_OVER_LIMIT", "Lcrcl/base/GuardLimitEnumType;");
            if (NULL == fid) {
                std::cerr << "Class crcl.base.GuardLimitEnumType has no field named INCREASE_OVER_LIMIT with signature Lcrcl/base/GuardLimitEnumType;." << std::endl;
                static GuardLimitEnumType nullObject((jobject)NULL,false); return nullObject;
            } else {
                retVal= (jobject)  env->GetStaticObjectField( cls, fid );
            }
        }
        releaseEnv(env);
                    
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            GuardLimitEnumType retObject(retVal,false);
            return retObject;
        }

        // Field getter for DECREASE_BEYOND_LIMIT
        GuardLimitEnumType GuardLimitEnumType::getDECREASE_BEYOND_LIMIT() {
        JNIEnv *env =getEnv();
        static jclass cls = getGuardLimitEnumTypeClass();
        jobject retVal=NULL;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "DECREASE_BEYOND_LIMIT", "Lcrcl/base/GuardLimitEnumType;");
            if (NULL == fid) {
                std::cerr << "Class crcl.base.GuardLimitEnumType has no field named DECREASE_BEYOND_LIMIT with signature Lcrcl/base/GuardLimitEnumType;." << std::endl;
                static GuardLimitEnumType nullObject((jobject)NULL,false); return nullObject;
            } else {
                retVal= (jobject)  env->GetStaticObjectField( cls, fid );
            }
        }
        releaseEnv(env);
                    
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            GuardLimitEnumType retObject(retVal,false);
            return retObject;
        }

        jstring GuardLimitEnumType::value() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method value of crcl.base.GuardLimitEnumType with jthis == NULL." << std::endl;
                return NULL;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," GuardLimitEnumType::value jthis=",jthis);
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "value", "()Ljava/lang/String;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," GuardLimitEnumType::value jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.GuardLimitEnumType has no method named value with signature ()Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," GuardLimitEnumType::value jthrowable t=",t);
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

        jobjectArray GuardLimitEnumType::values() {
            JNIEnv *env =getEnv();
            static jclass cls = getGuardLimitEnumTypeClass();
            jobjectArray retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "values", "()[Lcrcl/base/GuardLimitEnumType;");
                if (NULL == mid) {
                    std::cerr << "Class crcl.base.GuardLimitEnumType has no method named values with signature ()[Lcrcl/base/GuardLimitEnumType;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jobjectArray)  env->CallStaticObjectMethod( cls, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," GuardLimitEnumType::values jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        GuardLimitEnumType GuardLimitEnumType::valueOf(jstring string_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getGuardLimitEnumTypeClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "valueOf", "(Ljava/lang/String;)Lcrcl/base/GuardLimitEnumType;");
                if (NULL == mid) {
                    std::cerr << "Class crcl.base.GuardLimitEnumType has no method named valueOf with signature (Ljava/lang/String;)Lcrcl/base/GuardLimitEnumType;." << std::endl;
                    static GuardLimitEnumType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," GuardLimitEnumType::valueOf jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
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

        GuardLimitEnumType GuardLimitEnumType::fromValue(jstring string_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getGuardLimitEnumTypeClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "fromValue", "(Ljava/lang/String;)Lcrcl/base/GuardLimitEnumType;");
                if (NULL == mid) {
                    std::cerr << "Class crcl.base.GuardLimitEnumType has no method named fromValue with signature (Ljava/lang/String;)Lcrcl/base/GuardLimitEnumType;." << std::endl;
                    static GuardLimitEnumType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," GuardLimitEnumType::fromValue jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
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
        static jclass getNewGuardLimitEnumTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/GuardLimitEnumType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/GuardLimitEnumType" << std::endl;
            }
            return clss;
        }
        
        static jclass GuardLimitEnumTypeClass = NULL;
        static inline jclass getGuardLimitEnumTypeClass() {
            if (GuardLimitEnumTypeClass != NULL) {
                return GuardLimitEnumTypeClass;
            }
            GuardLimitEnumTypeClass = getNewGuardLimitEnumTypeClass();
            return GuardLimitEnumTypeClass;
        }

    // class_index = 4 clss=class crcl.base.RunProgramType

        
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
                            DebugPrintJObject(__FILE__,__LINE__," RunProgramType::fromValue jthis=",t);
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

    // class_index = 5 clss=class crcl.base.ForceTorqueSensorStatusType

        
        // get JNI handle for class crcl.base.ForceTorqueSensorStatusType
        static inline jclass getForceTorqueSensorStatusTypeClass();
        
        ForceTorqueSensorStatusType::ForceTorqueSensorStatusType(jobject _jthis, bool copy): SensorStatusType(_jthis,copy) {
                
        }
        
        ForceTorqueSensorStatusType::ForceTorqueSensorStatusType(const ForceTorqueSensorStatusType &objref): SensorStatusType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class ForceTorqueSensorStatusType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class ForceTorqueSensorStatusType jthis=",jthis);
            }
        }
        
        ForceTorqueSensorStatusType ForceTorqueSensorStatusType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getForceTorqueSensorStatusTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            ForceTorqueSensorStatusType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool ForceTorqueSensorStatusType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getForceTorqueSensorStatusTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        ForceTorqueSensorStatusType::ForceTorqueSensorStatusType() : SensorStatusType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getForceTorqueSensorStatusTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class ForceTorqueSensorStatusType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ForceTorqueSensorStatusType::setProgramText jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new ForceTorqueSensorStatusType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new ForceTorqueSensorStatusType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.ForceTorqueSensorStatusType
        ForceTorqueSensorStatusType::~ForceTorqueSensorStatusType() {
        	// Place-holder for later extensibility.
        }


        jdouble ForceTorqueSensorStatusType::getFx() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getFx of crcl.base.ForceTorqueSensorStatusType with jthis == NULL." << std::endl;
                return (jdouble) -1.0;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ForceTorqueSensorStatusType::getFx jthis=",jthis);
            jdouble retVal= (jdouble) -1.0;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getFx", "()D");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ForceTorqueSensorStatusType::getFx jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ForceTorqueSensorStatusType has no method named getFx with signature ()D." << std::endl;
                    return (jdouble) -1.0;
                } else {
                    retVal= (jdouble)  env->CallDoubleMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ForceTorqueSensorStatusType::getFx jthrowable t=",t);
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

        void ForceTorqueSensorStatusType::setFx(jdouble double_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setFx of crcl.base.ForceTorqueSensorStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ForceTorqueSensorStatusType::setFx jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setFx", "(D)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ForceTorqueSensorStatusType::setFx jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ForceTorqueSensorStatusType has no method named setFx with signature (D)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,double_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ForceTorqueSensorStatusType::setFx jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        jdouble ForceTorqueSensorStatusType::getFy() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getFy of crcl.base.ForceTorqueSensorStatusType with jthis == NULL." << std::endl;
                return (jdouble) -1.0;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ForceTorqueSensorStatusType::getFy jthis=",jthis);
            jdouble retVal= (jdouble) -1.0;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getFy", "()D");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ForceTorqueSensorStatusType::getFy jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ForceTorqueSensorStatusType has no method named getFy with signature ()D." << std::endl;
                    return (jdouble) -1.0;
                } else {
                    retVal= (jdouble)  env->CallDoubleMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ForceTorqueSensorStatusType::getFy jthrowable t=",t);
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

        void ForceTorqueSensorStatusType::setFy(jdouble double_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setFy of crcl.base.ForceTorqueSensorStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ForceTorqueSensorStatusType::setFy jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setFy", "(D)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ForceTorqueSensorStatusType::setFy jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ForceTorqueSensorStatusType has no method named setFy with signature (D)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,double_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ForceTorqueSensorStatusType::setFy jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        jdouble ForceTorqueSensorStatusType::getFz() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getFz of crcl.base.ForceTorqueSensorStatusType with jthis == NULL." << std::endl;
                return (jdouble) -1.0;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ForceTorqueSensorStatusType::getFz jthis=",jthis);
            jdouble retVal= (jdouble) -1.0;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getFz", "()D");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ForceTorqueSensorStatusType::getFz jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ForceTorqueSensorStatusType has no method named getFz with signature ()D." << std::endl;
                    return (jdouble) -1.0;
                } else {
                    retVal= (jdouble)  env->CallDoubleMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ForceTorqueSensorStatusType::getFz jthrowable t=",t);
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

        void ForceTorqueSensorStatusType::setFz(jdouble double_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setFz of crcl.base.ForceTorqueSensorStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ForceTorqueSensorStatusType::setFz jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setFz", "(D)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ForceTorqueSensorStatusType::setFz jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ForceTorqueSensorStatusType has no method named setFz with signature (D)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,double_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ForceTorqueSensorStatusType::setFz jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        jdouble ForceTorqueSensorStatusType::getTx() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getTx of crcl.base.ForceTorqueSensorStatusType with jthis == NULL." << std::endl;
                return (jdouble) -1.0;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ForceTorqueSensorStatusType::getTx jthis=",jthis);
            jdouble retVal= (jdouble) -1.0;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getTx", "()D");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ForceTorqueSensorStatusType::getTx jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ForceTorqueSensorStatusType has no method named getTx with signature ()D." << std::endl;
                    return (jdouble) -1.0;
                } else {
                    retVal= (jdouble)  env->CallDoubleMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ForceTorqueSensorStatusType::getTx jthrowable t=",t);
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

        void ForceTorqueSensorStatusType::setTx(jdouble double_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setTx of crcl.base.ForceTorqueSensorStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ForceTorqueSensorStatusType::setTx jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setTx", "(D)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ForceTorqueSensorStatusType::setTx jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ForceTorqueSensorStatusType has no method named setTx with signature (D)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,double_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ForceTorqueSensorStatusType::setTx jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        jdouble ForceTorqueSensorStatusType::getTy() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getTy of crcl.base.ForceTorqueSensorStatusType with jthis == NULL." << std::endl;
                return (jdouble) -1.0;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ForceTorqueSensorStatusType::getTy jthis=",jthis);
            jdouble retVal= (jdouble) -1.0;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getTy", "()D");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ForceTorqueSensorStatusType::getTy jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ForceTorqueSensorStatusType has no method named getTy with signature ()D." << std::endl;
                    return (jdouble) -1.0;
                } else {
                    retVal= (jdouble)  env->CallDoubleMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ForceTorqueSensorStatusType::getTy jthrowable t=",t);
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

        void ForceTorqueSensorStatusType::setTy(jdouble double_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setTy of crcl.base.ForceTorqueSensorStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ForceTorqueSensorStatusType::setTy jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setTy", "(D)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ForceTorqueSensorStatusType::setTy jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ForceTorqueSensorStatusType has no method named setTy with signature (D)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,double_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ForceTorqueSensorStatusType::setTy jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        jdouble ForceTorqueSensorStatusType::getTz() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getTz of crcl.base.ForceTorqueSensorStatusType with jthis == NULL." << std::endl;
                return (jdouble) -1.0;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ForceTorqueSensorStatusType::getTz jthis=",jthis);
            jdouble retVal= (jdouble) -1.0;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getTz", "()D");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ForceTorqueSensorStatusType::getTz jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ForceTorqueSensorStatusType has no method named getTz with signature ()D." << std::endl;
                    return (jdouble) -1.0;
                } else {
                    retVal= (jdouble)  env->CallDoubleMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ForceTorqueSensorStatusType::getTz jthrowable t=",t);
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

        void ForceTorqueSensorStatusType::setTz(jdouble double_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setTz of crcl.base.ForceTorqueSensorStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ForceTorqueSensorStatusType::setTz jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setTz", "(D)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ForceTorqueSensorStatusType::setTz jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ForceTorqueSensorStatusType has no method named setTz with signature (D)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,double_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ForceTorqueSensorStatusType::setTz jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }
        static jclass getNewForceTorqueSensorStatusTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/ForceTorqueSensorStatusType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/ForceTorqueSensorStatusType" << std::endl;
            }
            return clss;
        }
        
        static jclass ForceTorqueSensorStatusTypeClass = NULL;
        static inline jclass getForceTorqueSensorStatusTypeClass() {
            if (ForceTorqueSensorStatusTypeClass != NULL) {
                return ForceTorqueSensorStatusTypeClass;
            }
            ForceTorqueSensorStatusTypeClass = getNewForceTorqueSensorStatusTypeClass();
            return ForceTorqueSensorStatusTypeClass;
        }

    // class_index = 6 clss=class crcl.base.SetAngleUnitsType

        
        // get JNI handle for class crcl.base.SetAngleUnitsType
        static inline jclass getSetAngleUnitsTypeClass();
        
        SetAngleUnitsType::SetAngleUnitsType(jobject _jthis, bool copy): MiddleCommandType(_jthis,copy) {
                
        }
        
        SetAngleUnitsType::SetAngleUnitsType(const SetAngleUnitsType &objref): MiddleCommandType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class SetAngleUnitsType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class SetAngleUnitsType jthis=",jthis);
            }
        }
        
        SetAngleUnitsType SetAngleUnitsType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getSetAngleUnitsTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            SetAngleUnitsType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool SetAngleUnitsType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getSetAngleUnitsTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        SetAngleUnitsType::SetAngleUnitsType() : MiddleCommandType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getSetAngleUnitsTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class SetAngleUnitsType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," SetAngleUnitsType::setTz jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new SetAngleUnitsType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new SetAngleUnitsType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.SetAngleUnitsType
        SetAngleUnitsType::~SetAngleUnitsType() {
        	// Place-holder for later extensibility.
        }


        AngleUnitEnumType SetAngleUnitsType::getUnitName() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getUnitName of crcl.base.SetAngleUnitsType with jthis == NULL." << std::endl;
                static AngleUnitEnumType nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," SetAngleUnitsType::getUnitName jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getUnitName", "()Lcrcl/base/AngleUnitEnumType;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," SetAngleUnitsType::getUnitName jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.SetAngleUnitsType has no method named getUnitName with signature ()Lcrcl/base/AngleUnitEnumType;." << std::endl;
                    static AngleUnitEnumType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," SetAngleUnitsType::getUnitName jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
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

        void SetAngleUnitsType::setUnitName(const AngleUnitEnumType & angleUnitEnumType_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setUnitName of crcl.base.SetAngleUnitsType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," SetAngleUnitsType::setUnitName jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setUnitName", "(Lcrcl/base/AngleUnitEnumType;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," SetAngleUnitsType::setUnitName jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.SetAngleUnitsType has no method named setUnitName with signature (Lcrcl/base/AngleUnitEnumType;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,angleUnitEnumType_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," SetAngleUnitsType::setUnitName jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }
        static jclass getNewSetAngleUnitsTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/SetAngleUnitsType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/SetAngleUnitsType" << std::endl;
            }
            return clss;
        }
        
        static jclass SetAngleUnitsTypeClass = NULL;
        static inline jclass getSetAngleUnitsTypeClass() {
            if (SetAngleUnitsTypeClass != NULL) {
                return SetAngleUnitsTypeClass;
            }
            SetAngleUnitsTypeClass = getNewSetAngleUnitsTypeClass();
            return SetAngleUnitsTypeClass;
        }

    // class_index = 7 clss=class crcl.base.CloseToolChangerType

        
        // get JNI handle for class crcl.base.CloseToolChangerType
        static inline jclass getCloseToolChangerTypeClass();
        
        CloseToolChangerType::CloseToolChangerType(jobject _jthis, bool copy): MiddleCommandType(_jthis,copy) {
                
        }
        
        CloseToolChangerType::CloseToolChangerType(const CloseToolChangerType &objref): MiddleCommandType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class CloseToolChangerType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class CloseToolChangerType jthis=",jthis);
            }
        }
        
        CloseToolChangerType CloseToolChangerType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getCloseToolChangerTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            CloseToolChangerType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool CloseToolChangerType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getCloseToolChangerTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        CloseToolChangerType::CloseToolChangerType() : MiddleCommandType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getCloseToolChangerTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class CloseToolChangerType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CloseToolChangerType::setUnitName jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new CloseToolChangerType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new CloseToolChangerType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.CloseToolChangerType
        CloseToolChangerType::~CloseToolChangerType() {
        	// Place-holder for later extensibility.
        }

        static jclass getNewCloseToolChangerTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/CloseToolChangerType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/CloseToolChangerType" << std::endl;
            }
            return clss;
        }
        
        static jclass CloseToolChangerTypeClass = NULL;
        static inline jclass getCloseToolChangerTypeClass() {
            if (CloseToolChangerTypeClass != NULL) {
                return CloseToolChangerTypeClass;
            }
            CloseToolChangerTypeClass = getNewCloseToolChangerTypeClass();
            return CloseToolChangerTypeClass;
        }

    // class_index = 8 clss=class crcl.base.EndCanonType

        
        // get JNI handle for class crcl.base.EndCanonType
        static inline jclass getEndCanonTypeClass();
        
        EndCanonType::EndCanonType(jobject _jthis, bool copy): CRCLCommandType(_jthis,copy) {
                
        }
        
        EndCanonType::EndCanonType(const EndCanonType &objref): CRCLCommandType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class EndCanonType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class EndCanonType jthis=",jthis);
            }
        }
        
        EndCanonType EndCanonType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getEndCanonTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            EndCanonType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool EndCanonType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getEndCanonTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        EndCanonType::EndCanonType() : CRCLCommandType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getEndCanonTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class EndCanonType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," EndCanonType::setUnitName jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new EndCanonType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new EndCanonType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.EndCanonType
        EndCanonType::~EndCanonType() {
        	// Place-holder for later extensibility.
        }

        static jclass getNewEndCanonTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/EndCanonType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/EndCanonType" << std::endl;
            }
            return clss;
        }
        
        static jclass EndCanonTypeClass = NULL;
        static inline jclass getEndCanonTypeClass() {
            if (EndCanonTypeClass != NULL) {
                return EndCanonTypeClass;
            }
            EndCanonTypeClass = getNewEndCanonTypeClass();
            return EndCanonTypeClass;
        }

    // class_index = 9 clss=class crcl.base.GripperStatusType

        
        // get JNI handle for class crcl.base.GripperStatusType
        static inline jclass getGripperStatusTypeClass();
        
        GripperStatusType::GripperStatusType(jobject _jthis, bool copy): DataThingType(_jthis,copy) {
                
        }
        
        GripperStatusType::GripperStatusType(const GripperStatusType &objref): DataThingType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class GripperStatusType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class GripperStatusType jthis=",jthis);
            }
        }
        
        GripperStatusType GripperStatusType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getGripperStatusTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            GripperStatusType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool GripperStatusType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getGripperStatusTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        GripperStatusType::GripperStatusType() : DataThingType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getGripperStatusTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class GripperStatusType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," GripperStatusType::setUnitName jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new GripperStatusType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new GripperStatusType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.GripperStatusType
        GripperStatusType::~GripperStatusType() {
        	// Place-holder for later extensibility.
        }


        jstring GripperStatusType::getGripperName() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getGripperName of crcl.base.GripperStatusType with jthis == NULL." << std::endl;
                return NULL;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," GripperStatusType::getGripperName jthis=",jthis);
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getGripperName", "()Ljava/lang/String;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," GripperStatusType::getGripperName jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.GripperStatusType has no method named getGripperName with signature ()Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," GripperStatusType::getGripperName jthrowable t=",t);
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

        void GripperStatusType::setGripperName(jstring string_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setGripperName of crcl.base.GripperStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," GripperStatusType::setGripperName jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setGripperName", "(Ljava/lang/String;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," GripperStatusType::setGripperName jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.GripperStatusType has no method named setGripperName with signature (Ljava/lang/String;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," GripperStatusType::setGripperName jthrowable t=",t);
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
        void GripperStatusType::setGripperName(const char * easyArg_0) {
            // convenience method, converts to/from JNI types to common C++ types.
            if(jthis == NULL) {
                std::cerr << "Call of method setGripperName of crcl.base.GripperStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," GripperStatusType::setGripperName jthis=",jthis);
            
                    
            jstring string_0 = env->NewStringUTF(easyArg_0);
            setGripperName(string_0);
            jobjectRefType ref_0 = env->GetObjectRefType(string_0);
            if(ref_0 == JNIGlobalRefType) {
                env->DeleteGlobalRef(string_0);
            }
            
            releaseEnv(env);
        }


        ::crclj::java::util::List GripperStatusType::getGripperOption() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getGripperOption of crcl.base.GripperStatusType with jthis == NULL." << std::endl;
                static ::crclj::java::util::List nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," GripperStatusType::getGripperOption jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getGripperOption", "()Ljava/util/List;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," GripperStatusType::getGripperOption jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.GripperStatusType has no method named getGripperOption with signature ()Ljava/util/List;." << std::endl;
                    static ::crclj::java::util::List nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," GripperStatusType::getGripperOption jthrowable t=",t);
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

        ::crclj::java::lang::Boolean GripperStatusType::isHoldingObject() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method isHoldingObject of crcl.base.GripperStatusType with jthis == NULL." << std::endl;
                static ::crclj::java::lang::Boolean nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," GripperStatusType::isHoldingObject jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "isHoldingObject", "()Ljava/lang/Boolean;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," GripperStatusType::isHoldingObject jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.GripperStatusType has no method named isHoldingObject with signature ()Ljava/lang/Boolean;." << std::endl;
                    static ::crclj::java::lang::Boolean nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," GripperStatusType::isHoldingObject jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            ::crclj::java::lang::Boolean retObject(retVal,false);
            return retObject;
        }

        void GripperStatusType::setHoldingObject(const ::crclj::java::lang::Boolean & boolean_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setHoldingObject of crcl.base.GripperStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," GripperStatusType::setHoldingObject jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setHoldingObject", "(Ljava/lang/Boolean;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," GripperStatusType::setHoldingObject jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.GripperStatusType has no method named setHoldingObject with signature (Ljava/lang/Boolean;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,boolean_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," GripperStatusType::setHoldingObject jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }
        static jclass getNewGripperStatusTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/GripperStatusType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/GripperStatusType" << std::endl;
            }
            return clss;
        }
        
        static jclass GripperStatusTypeClass = NULL;
        static inline jclass getGripperStatusTypeClass() {
            if (GripperStatusTypeClass != NULL) {
                return GripperStatusTypeClass;
            }
            GripperStatusTypeClass = getNewGripperStatusTypeClass();
            return GripperStatusTypeClass;
        }
        } // end namespace base
    } // end namespace crcl

    
    
        // end namespace crclj
    }
    
