
#include <jni.h>
#include <cstdlib>
#include <iostream>
#include "crclj.h"

namespace crclj {

    



// start_segment_index = 80
// start_segment_index = 90
// segment_index = 8
// classesSegList=[class crcl.base.ConfigureJointReportsType, class crcl.base.SetTransAccelType, class crcl.base.CRCLStatusType, class crcl.base.ParameterSettingType, class crcl.base.TransSpeedRelativeType, class crcl.base.SetIntermediatePoseToleranceType, class crcl.base.TransSpeedAbsoluteType, class crcl.base.DisableGripperType, class crcl.base.EnableGripperType, class crcl.base.WrenchType]


// class_index = 0 clss=class crcl.base.ConfigureJointReportsType

    namespace crcl{
        namespace base{
        
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
                            DebugPrintJObject(__FILE__,__LINE__," ConfigureJointReportsType::fromValue jthis=",t);
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

    // class_index = 1 clss=class crcl.base.SetTransAccelType

        
        // get JNI handle for class crcl.base.SetTransAccelType
        static inline jclass getSetTransAccelTypeClass();
        
        SetTransAccelType::SetTransAccelType(jobject _jthis, bool copy): MiddleCommandType(_jthis,copy) {
                
        }
        
        SetTransAccelType::SetTransAccelType(const SetTransAccelType &objref): MiddleCommandType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class SetTransAccelType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class SetTransAccelType jthis=",jthis);
            }
        }
        
        SetTransAccelType SetTransAccelType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getSetTransAccelTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            SetTransAccelType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool SetTransAccelType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getSetTransAccelTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        SetTransAccelType::SetTransAccelType() : MiddleCommandType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getSetTransAccelTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class SetTransAccelType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," SetTransAccelType::getConfigureJointReport jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new SetTransAccelType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new SetTransAccelType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.SetTransAccelType
        SetTransAccelType::~SetTransAccelType() {
        	// Place-holder for later extensibility.
        }


        TransAccelType SetTransAccelType::getTransAccel() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getTransAccel of crcl.base.SetTransAccelType with jthis == NULL." << std::endl;
                static TransAccelType nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," SetTransAccelType::getTransAccel jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getTransAccel", "()Lcrcl/base/TransAccelType;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," SetTransAccelType::getTransAccel jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.SetTransAccelType has no method named getTransAccel with signature ()Lcrcl/base/TransAccelType;." << std::endl;
                    static TransAccelType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," SetTransAccelType::getTransAccel jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            TransAccelType retObject(retVal,false);
            return retObject;
        }

        void SetTransAccelType::setTransAccel(const TransAccelType & transAccelType_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setTransAccel of crcl.base.SetTransAccelType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," SetTransAccelType::setTransAccel jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setTransAccel", "(Lcrcl/base/TransAccelType;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," SetTransAccelType::setTransAccel jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.SetTransAccelType has no method named setTransAccel with signature (Lcrcl/base/TransAccelType;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,transAccelType_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," SetTransAccelType::setTransAccel jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }
        static jclass getNewSetTransAccelTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/SetTransAccelType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/SetTransAccelType" << std::endl;
            }
            return clss;
        }
        
        static jclass SetTransAccelTypeClass = NULL;
        static inline jclass getSetTransAccelTypeClass() {
            if (SetTransAccelTypeClass != NULL) {
                return SetTransAccelTypeClass;
            }
            SetTransAccelTypeClass = getNewSetTransAccelTypeClass();
            return SetTransAccelTypeClass;
        }

    // class_index = 2 clss=class crcl.base.CRCLStatusType

        
        // get JNI handle for class crcl.base.CRCLStatusType
        static inline jclass getCRCLStatusTypeClass();
        
        CRCLStatusType::CRCLStatusType(jobject _jthis, bool copy): DataThingType(_jthis,copy) {
                
        }
        
        CRCLStatusType::CRCLStatusType(const CRCLStatusType &objref): DataThingType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class CRCLStatusType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class CRCLStatusType jthis=",jthis);
            }
        }
        
        CRCLStatusType CRCLStatusType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getCRCLStatusTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            CRCLStatusType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool CRCLStatusType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getCRCLStatusTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        CRCLStatusType::CRCLStatusType() : DataThingType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getCRCLStatusTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class CRCLStatusType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::setTransAccel jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new CRCLStatusType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new CRCLStatusType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.CRCLStatusType
        CRCLStatusType::~CRCLStatusType() {
        	// Place-holder for later extensibility.
        }


        CommandStatusType CRCLStatusType::getCommandStatus() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getCommandStatus of crcl.base.CRCLStatusType with jthis == NULL." << std::endl;
                static CommandStatusType nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::getCommandStatus jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getCommandStatus", "()Lcrcl/base/CommandStatusType;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::getCommandStatus jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.CRCLStatusType has no method named getCommandStatus with signature ()Lcrcl/base/CommandStatusType;." << std::endl;
                    static CommandStatusType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::getCommandStatus jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            CommandStatusType retObject(retVal,false);
            return retObject;
        }

        void CRCLStatusType::setCommandStatus(const CommandStatusType & commandStatusType_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setCommandStatus of crcl.base.CRCLStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::setCommandStatus jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setCommandStatus", "(Lcrcl/base/CommandStatusType;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::setCommandStatus jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.CRCLStatusType has no method named setCommandStatus with signature (Lcrcl/base/CommandStatusType;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,commandStatusType_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::setCommandStatus jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        JointStatusesType CRCLStatusType::getJointStatuses() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getJointStatuses of crcl.base.CRCLStatusType with jthis == NULL." << std::endl;
                static JointStatusesType nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::getJointStatuses jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getJointStatuses", "()Lcrcl/base/JointStatusesType;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::getJointStatuses jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.CRCLStatusType has no method named getJointStatuses with signature ()Lcrcl/base/JointStatusesType;." << std::endl;
                    static JointStatusesType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::getJointStatuses jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            JointStatusesType retObject(retVal,false);
            return retObject;
        }

        void CRCLStatusType::setJointStatuses(const JointStatusesType & jointStatusesType_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setJointStatuses of crcl.base.CRCLStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::setJointStatuses jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setJointStatuses", "(Lcrcl/base/JointStatusesType;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::setJointStatuses jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.CRCLStatusType has no method named setJointStatuses with signature (Lcrcl/base/JointStatusesType;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,jointStatusesType_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::setJointStatuses jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        PoseStatusType CRCLStatusType::getPoseStatus() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getPoseStatus of crcl.base.CRCLStatusType with jthis == NULL." << std::endl;
                static PoseStatusType nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::getPoseStatus jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getPoseStatus", "()Lcrcl/base/PoseStatusType;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::getPoseStatus jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.CRCLStatusType has no method named getPoseStatus with signature ()Lcrcl/base/PoseStatusType;." << std::endl;
                    static PoseStatusType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::getPoseStatus jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            PoseStatusType retObject(retVal,false);
            return retObject;
        }

        void CRCLStatusType::setPoseStatus(const PoseStatusType & poseStatusType_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setPoseStatus of crcl.base.CRCLStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::setPoseStatus jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setPoseStatus", "(Lcrcl/base/PoseStatusType;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::setPoseStatus jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.CRCLStatusType has no method named setPoseStatus with signature (Lcrcl/base/PoseStatusType;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,poseStatusType_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::setPoseStatus jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        GripperStatusType CRCLStatusType::getGripperStatus() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getGripperStatus of crcl.base.CRCLStatusType with jthis == NULL." << std::endl;
                static GripperStatusType nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::getGripperStatus jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getGripperStatus", "()Lcrcl/base/GripperStatusType;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::getGripperStatus jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.CRCLStatusType has no method named getGripperStatus with signature ()Lcrcl/base/GripperStatusType;." << std::endl;
                    static GripperStatusType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::getGripperStatus jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            GripperStatusType retObject(retVal,false);
            return retObject;
        }

        void CRCLStatusType::setGripperStatus(const GripperStatusType & gripperStatusType_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setGripperStatus of crcl.base.CRCLStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::setGripperStatus jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setGripperStatus", "(Lcrcl/base/GripperStatusType;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::setGripperStatus jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.CRCLStatusType has no method named setGripperStatus with signature (Lcrcl/base/GripperStatusType;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,gripperStatusType_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::setGripperStatus jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        SettingsStatusType CRCLStatusType::getSettingsStatus() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getSettingsStatus of crcl.base.CRCLStatusType with jthis == NULL." << std::endl;
                static SettingsStatusType nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::getSettingsStatus jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getSettingsStatus", "()Lcrcl/base/SettingsStatusType;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::getSettingsStatus jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.CRCLStatusType has no method named getSettingsStatus with signature ()Lcrcl/base/SettingsStatusType;." << std::endl;
                    static SettingsStatusType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::getSettingsStatus jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            SettingsStatusType retObject(retVal,false);
            return retObject;
        }

        void CRCLStatusType::setSettingsStatus(const SettingsStatusType & settingsStatusType_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setSettingsStatus of crcl.base.CRCLStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::setSettingsStatus jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setSettingsStatus", "(Lcrcl/base/SettingsStatusType;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::setSettingsStatus jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.CRCLStatusType has no method named setSettingsStatus with signature (Lcrcl/base/SettingsStatusType;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,settingsStatusType_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::setSettingsStatus jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        SensorStatusesType CRCLStatusType::getSensorStatuses() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getSensorStatuses of crcl.base.CRCLStatusType with jthis == NULL." << std::endl;
                static SensorStatusesType nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::getSensorStatuses jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getSensorStatuses", "()Lcrcl/base/SensorStatusesType;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::getSensorStatuses jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.CRCLStatusType has no method named getSensorStatuses with signature ()Lcrcl/base/SensorStatusesType;." << std::endl;
                    static SensorStatusesType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::getSensorStatuses jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            SensorStatusesType retObject(retVal,false);
            return retObject;
        }

        void CRCLStatusType::setSensorStatuses(const SensorStatusesType & sensorStatusesType_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setSensorStatuses of crcl.base.CRCLStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::setSensorStatuses jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setSensorStatuses", "(Lcrcl/base/SensorStatusesType;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::setSensorStatuses jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.CRCLStatusType has no method named setSensorStatuses with signature (Lcrcl/base/SensorStatusesType;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,sensorStatusesType_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::setSensorStatuses jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        GuardsStatusesType CRCLStatusType::getGuardsStatuses() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getGuardsStatuses of crcl.base.CRCLStatusType with jthis == NULL." << std::endl;
                static GuardsStatusesType nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::getGuardsStatuses jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getGuardsStatuses", "()Lcrcl/base/GuardsStatusesType;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::getGuardsStatuses jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.CRCLStatusType has no method named getGuardsStatuses with signature ()Lcrcl/base/GuardsStatusesType;." << std::endl;
                    static GuardsStatusesType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::getGuardsStatuses jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            GuardsStatusesType retObject(retVal,false);
            return retObject;
        }

        void CRCLStatusType::setGuardsStatuses(const GuardsStatusesType & guardsStatusesType_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setGuardsStatuses of crcl.base.CRCLStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::setGuardsStatuses jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setGuardsStatuses", "(Lcrcl/base/GuardsStatusesType;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::setGuardsStatuses jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.CRCLStatusType has no method named setGuardsStatuses with signature (Lcrcl/base/GuardsStatusesType;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,guardsStatusesType_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLStatusType::setGuardsStatuses jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }
        static jclass getNewCRCLStatusTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/CRCLStatusType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/CRCLStatusType" << std::endl;
            }
            return clss;
        }
        
        static jclass CRCLStatusTypeClass = NULL;
        static inline jclass getCRCLStatusTypeClass() {
            if (CRCLStatusTypeClass != NULL) {
                return CRCLStatusTypeClass;
            }
            CRCLStatusTypeClass = getNewCRCLStatusTypeClass();
            return CRCLStatusTypeClass;
        }

    // class_index = 3 clss=class crcl.base.ParameterSettingType

        
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
                            DebugPrintJObject(__FILE__,__LINE__," ParameterSettingType::setGuardsStatuses jthis=",t);
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

    // class_index = 4 clss=class crcl.base.TransSpeedRelativeType

        
        // get JNI handle for class crcl.base.TransSpeedRelativeType
        static inline jclass getTransSpeedRelativeTypeClass();
        
        TransSpeedRelativeType::TransSpeedRelativeType(jobject _jthis, bool copy): TransSpeedType(_jthis,copy) {
                
        }
        
        TransSpeedRelativeType::TransSpeedRelativeType(const TransSpeedRelativeType &objref): TransSpeedType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class TransSpeedRelativeType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class TransSpeedRelativeType jthis=",jthis);
            }
        }
        
        TransSpeedRelativeType TransSpeedRelativeType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getTransSpeedRelativeTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            TransSpeedRelativeType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool TransSpeedRelativeType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getTransSpeedRelativeTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        TransSpeedRelativeType::TransSpeedRelativeType() : TransSpeedType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getTransSpeedRelativeTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class TransSpeedRelativeType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," TransSpeedRelativeType::setParameterValue jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new TransSpeedRelativeType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new TransSpeedRelativeType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.TransSpeedRelativeType
        TransSpeedRelativeType::~TransSpeedRelativeType() {
        	// Place-holder for later extensibility.
        }


        jdouble TransSpeedRelativeType::getFraction() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getFraction of crcl.base.TransSpeedRelativeType with jthis == NULL." << std::endl;
                return (jdouble) -1.0;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," TransSpeedRelativeType::getFraction jthis=",jthis);
            jdouble retVal= (jdouble) -1.0;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getFraction", "()D");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," TransSpeedRelativeType::getFraction jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.TransSpeedRelativeType has no method named getFraction with signature ()D." << std::endl;
                    return (jdouble) -1.0;
                } else {
                    retVal= (jdouble)  env->CallDoubleMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," TransSpeedRelativeType::getFraction jthrowable t=",t);
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

        void TransSpeedRelativeType::setFraction(jdouble double_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setFraction of crcl.base.TransSpeedRelativeType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," TransSpeedRelativeType::setFraction jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setFraction", "(D)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," TransSpeedRelativeType::setFraction jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.TransSpeedRelativeType has no method named setFraction with signature (D)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,double_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," TransSpeedRelativeType::setFraction jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }
        static jclass getNewTransSpeedRelativeTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/TransSpeedRelativeType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/TransSpeedRelativeType" << std::endl;
            }
            return clss;
        }
        
        static jclass TransSpeedRelativeTypeClass = NULL;
        static inline jclass getTransSpeedRelativeTypeClass() {
            if (TransSpeedRelativeTypeClass != NULL) {
                return TransSpeedRelativeTypeClass;
            }
            TransSpeedRelativeTypeClass = getNewTransSpeedRelativeTypeClass();
            return TransSpeedRelativeTypeClass;
        }

    // class_index = 5 clss=class crcl.base.SetIntermediatePoseToleranceType

        
        // get JNI handle for class crcl.base.SetIntermediatePoseToleranceType
        static inline jclass getSetIntermediatePoseToleranceTypeClass();
        
        SetIntermediatePoseToleranceType::SetIntermediatePoseToleranceType(jobject _jthis, bool copy): MiddleCommandType(_jthis,copy) {
                
        }
        
        SetIntermediatePoseToleranceType::SetIntermediatePoseToleranceType(const SetIntermediatePoseToleranceType &objref): MiddleCommandType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class SetIntermediatePoseToleranceType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class SetIntermediatePoseToleranceType jthis=",jthis);
            }
        }
        
        SetIntermediatePoseToleranceType SetIntermediatePoseToleranceType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getSetIntermediatePoseToleranceTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            SetIntermediatePoseToleranceType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool SetIntermediatePoseToleranceType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getSetIntermediatePoseToleranceTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        SetIntermediatePoseToleranceType::SetIntermediatePoseToleranceType() : MiddleCommandType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getSetIntermediatePoseToleranceTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class SetIntermediatePoseToleranceType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," SetIntermediatePoseToleranceType::setFraction jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new SetIntermediatePoseToleranceType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new SetIntermediatePoseToleranceType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.SetIntermediatePoseToleranceType
        SetIntermediatePoseToleranceType::~SetIntermediatePoseToleranceType() {
        	// Place-holder for later extensibility.
        }


        PoseToleranceType SetIntermediatePoseToleranceType::getTolerance() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getTolerance of crcl.base.SetIntermediatePoseToleranceType with jthis == NULL." << std::endl;
                static PoseToleranceType nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," SetIntermediatePoseToleranceType::getTolerance jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getTolerance", "()Lcrcl/base/PoseToleranceType;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," SetIntermediatePoseToleranceType::getTolerance jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.SetIntermediatePoseToleranceType has no method named getTolerance with signature ()Lcrcl/base/PoseToleranceType;." << std::endl;
                    static PoseToleranceType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," SetIntermediatePoseToleranceType::getTolerance jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            PoseToleranceType retObject(retVal,false);
            return retObject;
        }

        void SetIntermediatePoseToleranceType::setTolerance(const PoseToleranceType & poseToleranceType_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setTolerance of crcl.base.SetIntermediatePoseToleranceType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," SetIntermediatePoseToleranceType::setTolerance jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setTolerance", "(Lcrcl/base/PoseToleranceType;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," SetIntermediatePoseToleranceType::setTolerance jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.SetIntermediatePoseToleranceType has no method named setTolerance with signature (Lcrcl/base/PoseToleranceType;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,poseToleranceType_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," SetIntermediatePoseToleranceType::setTolerance jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }
        static jclass getNewSetIntermediatePoseToleranceTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/SetIntermediatePoseToleranceType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/SetIntermediatePoseToleranceType" << std::endl;
            }
            return clss;
        }
        
        static jclass SetIntermediatePoseToleranceTypeClass = NULL;
        static inline jclass getSetIntermediatePoseToleranceTypeClass() {
            if (SetIntermediatePoseToleranceTypeClass != NULL) {
                return SetIntermediatePoseToleranceTypeClass;
            }
            SetIntermediatePoseToleranceTypeClass = getNewSetIntermediatePoseToleranceTypeClass();
            return SetIntermediatePoseToleranceTypeClass;
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
                            DebugPrintJObject(__FILE__,__LINE__," TransSpeedAbsoluteType::setTolerance jthis=",t);
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

    // class_index = 7 clss=class crcl.base.DisableGripperType

        
        // get JNI handle for class crcl.base.DisableGripperType
        static inline jclass getDisableGripperTypeClass();
        
        DisableGripperType::DisableGripperType(jobject _jthis, bool copy): MiddleCommandType(_jthis,copy) {
                
        }
        
        DisableGripperType::DisableGripperType(const DisableGripperType &objref): MiddleCommandType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class DisableGripperType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class DisableGripperType jthis=",jthis);
            }
        }
        
        DisableGripperType DisableGripperType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getDisableGripperTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            DisableGripperType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool DisableGripperType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getDisableGripperTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        DisableGripperType::DisableGripperType() : MiddleCommandType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getDisableGripperTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class DisableGripperType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," DisableGripperType::setSetting jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new DisableGripperType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new DisableGripperType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.DisableGripperType
        DisableGripperType::~DisableGripperType() {
        	// Place-holder for later extensibility.
        }


        jstring DisableGripperType::getGripperName() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getGripperName of crcl.base.DisableGripperType with jthis == NULL." << std::endl;
                return NULL;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," DisableGripperType::getGripperName jthis=",jthis);
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getGripperName", "()Ljava/lang/String;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," DisableGripperType::getGripperName jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.DisableGripperType has no method named getGripperName with signature ()Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," DisableGripperType::getGripperName jthrowable t=",t);
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

        void DisableGripperType::setGripperName(jstring string_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setGripperName of crcl.base.DisableGripperType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," DisableGripperType::setGripperName jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setGripperName", "(Ljava/lang/String;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," DisableGripperType::setGripperName jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.DisableGripperType has no method named setGripperName with signature (Ljava/lang/String;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," DisableGripperType::setGripperName jthrowable t=",t);
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
        void DisableGripperType::setGripperName(const char * easyArg_0) {
            // convenience method, converts to/from JNI types to common C++ types.
            if(jthis == NULL) {
                std::cerr << "Call of method setGripperName of crcl.base.DisableGripperType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," DisableGripperType::setGripperName jthis=",jthis);
            
                    
            jstring string_0 = env->NewStringUTF(easyArg_0);
            setGripperName(string_0);
            jobjectRefType ref_0 = env->GetObjectRefType(string_0);
            if(ref_0 == JNIGlobalRefType) {
                env->DeleteGlobalRef(string_0);
            }
            
            releaseEnv(env);
        }

        static jclass getNewDisableGripperTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/DisableGripperType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/DisableGripperType" << std::endl;
            }
            return clss;
        }
        
        static jclass DisableGripperTypeClass = NULL;
        static inline jclass getDisableGripperTypeClass() {
            if (DisableGripperTypeClass != NULL) {
                return DisableGripperTypeClass;
            }
            DisableGripperTypeClass = getNewDisableGripperTypeClass();
            return DisableGripperTypeClass;
        }

    // class_index = 8 clss=class crcl.base.EnableGripperType

        
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
                            DebugPrintJObject(__FILE__,__LINE__," EnableGripperType::setGripperName jthis=",t);
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

    // class_index = 9 clss=class crcl.base.WrenchType

        
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
        } // end namespace base
    } // end namespace crcl

    
    
        // end namespace crclj
    }
    
