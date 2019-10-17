
#include <jni.h>
#include <cstdlib>
#include <iostream>
#include "crclj.h"

namespace crclj {

    



// start_segment_index = 20
// start_segment_index = 30
// segment_index = 2
// classesSegList=[class crcl.base.TransSpeedRelativeType, class crcl.base.SetIntermediatePoseToleranceType, class crcl.base.OnOffSensorStatusType, class crcl.base.StopConditionEnumType, class crcl.base.RotSpeedAbsoluteType, class crcl.base.GuardLimitEnumType, class crcl.base.OpenToolChangerType, class crcl.base.TorqueUnitEnumType, class crcl.base.MessageType, class crcl.base.JointDetailsType]


// class_index = 0 clss=class crcl.base.TransSpeedRelativeType

    namespace crcl{
        namespace base{
        
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
                            DebugPrintJObject(__FILE__,__LINE__," TransSpeedRelativeType::getPoint jthis=",t);
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

    // class_index = 1 clss=class crcl.base.SetIntermediatePoseToleranceType

        
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

    // class_index = 2 clss=class crcl.base.OnOffSensorStatusType

        
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
                            DebugPrintJObject(__FILE__,__LINE__," OnOffSensorStatusType::setTolerance jthis=",t);
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

    // class_index = 3 clss=class crcl.base.StopConditionEnumType

        
        // get JNI handle for class crcl.base.StopConditionEnumType
        static inline jclass getStopConditionEnumTypeClass();
        
        StopConditionEnumType::StopConditionEnumType(jobject _jthis, bool copy): ::crclj::java::lang::Enum(_jthis,copy) {
                
        }
        
        StopConditionEnumType::StopConditionEnumType(const StopConditionEnumType &objref): ::crclj::java::lang::Enum((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class StopConditionEnumType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class StopConditionEnumType jthis=",jthis);
            }
        }
        
        StopConditionEnumType StopConditionEnumType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getStopConditionEnumTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            StopConditionEnumType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool StopConditionEnumType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getStopConditionEnumTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        StopConditionEnumType::StopConditionEnumType() : ::crclj::java::lang::Enum((jobject)NULL,false) {
        JNIEnv *env =getEnv();
        static jclass cls = getStopConditionEnumTypeClass();
        if (cls != NULL) {
            static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
            if (NULL == mid) {
                std::cerr << "Class StopConditionEnumType has no method constructor signature ()V" << std::endl;
            } else {
                jthis = env->NewObject(cls, mid );
                jthrowable t = env->ExceptionOccurred();
                if(t != NULL) {
                    if(GetDebugJ4Cpp()) {
                        DebugPrintJObject(__FILE__,__LINE__," StopConditionEnumType::setOn jthis=",t);
                        env->ExceptionDescribe();
                    }
                    throw t;
                }
                if(jthis == NULL) {
                    std::cerr << "Call to create new StopConditionEnumType with signature ()V returned null." << std::endl;
                    releaseEnv(env);
                    return;
                }
                jobjectRefType ref = env->GetObjectRefType(jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new StopConditionEnumType jthis=",jthis);
                if(ref != JNIGlobalRefType) {
                    jthis = env->NewGlobalRef(jthis);
                }
            }
        }
        releaseEnv(env);
        }


        // Destructor for crcl.base.StopConditionEnumType
        StopConditionEnumType::~StopConditionEnumType() {
        	// Place-holder for later extensibility.
        }


        // Field getter for IMMEDIATE
        StopConditionEnumType StopConditionEnumType::getIMMEDIATE() {
        JNIEnv *env =getEnv();
        static jclass cls = getStopConditionEnumTypeClass();
        jobject retVal=NULL;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "IMMEDIATE", "Lcrcl/base/StopConditionEnumType;");
            if (NULL == fid) {
                std::cerr << "Class crcl.base.StopConditionEnumType has no field named IMMEDIATE with signature Lcrcl/base/StopConditionEnumType;." << std::endl;
                static StopConditionEnumType nullObject((jobject)NULL,false); return nullObject;
            } else {
                retVal= (jobject)  env->GetStaticObjectField( cls, fid );
            }
        }
        releaseEnv(env);
                    
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            StopConditionEnumType retObject(retVal,false);
            return retObject;
        }

        // Field getter for FAST
        StopConditionEnumType StopConditionEnumType::getFAST() {
        JNIEnv *env =getEnv();
        static jclass cls = getStopConditionEnumTypeClass();
        jobject retVal=NULL;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "FAST", "Lcrcl/base/StopConditionEnumType;");
            if (NULL == fid) {
                std::cerr << "Class crcl.base.StopConditionEnumType has no field named FAST with signature Lcrcl/base/StopConditionEnumType;." << std::endl;
                static StopConditionEnumType nullObject((jobject)NULL,false); return nullObject;
            } else {
                retVal= (jobject)  env->GetStaticObjectField( cls, fid );
            }
        }
        releaseEnv(env);
                    
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            StopConditionEnumType retObject(retVal,false);
            return retObject;
        }

        // Field getter for NORMAL
        StopConditionEnumType StopConditionEnumType::getNORMAL() {
        JNIEnv *env =getEnv();
        static jclass cls = getStopConditionEnumTypeClass();
        jobject retVal=NULL;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "NORMAL", "Lcrcl/base/StopConditionEnumType;");
            if (NULL == fid) {
                std::cerr << "Class crcl.base.StopConditionEnumType has no field named NORMAL with signature Lcrcl/base/StopConditionEnumType;." << std::endl;
                static StopConditionEnumType nullObject((jobject)NULL,false); return nullObject;
            } else {
                retVal= (jobject)  env->GetStaticObjectField( cls, fid );
            }
        }
        releaseEnv(env);
                    
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            StopConditionEnumType retObject(retVal,false);
            return retObject;
        }

        jstring StopConditionEnumType::value() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method value of crcl.base.StopConditionEnumType with jthis == NULL." << std::endl;
                return NULL;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," StopConditionEnumType::value jthis=",jthis);
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "value", "()Ljava/lang/String;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," StopConditionEnumType::value jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.StopConditionEnumType has no method named value with signature ()Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," StopConditionEnumType::value jthrowable t=",t);
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

        jobjectArray StopConditionEnumType::values() {
            JNIEnv *env =getEnv();
            static jclass cls = getStopConditionEnumTypeClass();
            jobjectArray retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "values", "()[Lcrcl/base/StopConditionEnumType;");
                if (NULL == mid) {
                    std::cerr << "Class crcl.base.StopConditionEnumType has no method named values with signature ()[Lcrcl/base/StopConditionEnumType;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jobjectArray)  env->CallStaticObjectMethod( cls, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," StopConditionEnumType::values jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        StopConditionEnumType StopConditionEnumType::valueOf(jstring string_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getStopConditionEnumTypeClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "valueOf", "(Ljava/lang/String;)Lcrcl/base/StopConditionEnumType;");
                if (NULL == mid) {
                    std::cerr << "Class crcl.base.StopConditionEnumType has no method named valueOf with signature (Ljava/lang/String;)Lcrcl/base/StopConditionEnumType;." << std::endl;
                    static StopConditionEnumType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," StopConditionEnumType::valueOf jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            StopConditionEnumType retObject(retVal,false);
            return retObject;
        }

        StopConditionEnumType StopConditionEnumType::fromValue(jstring string_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getStopConditionEnumTypeClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "fromValue", "(Ljava/lang/String;)Lcrcl/base/StopConditionEnumType;");
                if (NULL == mid) {
                    std::cerr << "Class crcl.base.StopConditionEnumType has no method named fromValue with signature (Ljava/lang/String;)Lcrcl/base/StopConditionEnumType;." << std::endl;
                    static StopConditionEnumType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," StopConditionEnumType::fromValue jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            StopConditionEnumType retObject(retVal,false);
            return retObject;
        }
        static jclass getNewStopConditionEnumTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/StopConditionEnumType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/StopConditionEnumType" << std::endl;
            }
            return clss;
        }
        
        static jclass StopConditionEnumTypeClass = NULL;
        static inline jclass getStopConditionEnumTypeClass() {
            if (StopConditionEnumTypeClass != NULL) {
                return StopConditionEnumTypeClass;
            }
            StopConditionEnumTypeClass = getNewStopConditionEnumTypeClass();
            return StopConditionEnumTypeClass;
        }

    // class_index = 4 clss=class crcl.base.RotSpeedAbsoluteType

        
        // get JNI handle for class crcl.base.RotSpeedAbsoluteType
        static inline jclass getRotSpeedAbsoluteTypeClass();
        
        RotSpeedAbsoluteType::RotSpeedAbsoluteType(jobject _jthis, bool copy): RotSpeedType(_jthis,copy) {
                
        }
        
        RotSpeedAbsoluteType::RotSpeedAbsoluteType(const RotSpeedAbsoluteType &objref): RotSpeedType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class RotSpeedAbsoluteType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class RotSpeedAbsoluteType jthis=",jthis);
            }
        }
        
        RotSpeedAbsoluteType RotSpeedAbsoluteType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getRotSpeedAbsoluteTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            RotSpeedAbsoluteType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool RotSpeedAbsoluteType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getRotSpeedAbsoluteTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        RotSpeedAbsoluteType::RotSpeedAbsoluteType() : RotSpeedType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getRotSpeedAbsoluteTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class RotSpeedAbsoluteType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," RotSpeedAbsoluteType::fromValue jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new RotSpeedAbsoluteType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new RotSpeedAbsoluteType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.RotSpeedAbsoluteType
        RotSpeedAbsoluteType::~RotSpeedAbsoluteType() {
        	// Place-holder for later extensibility.
        }


        jdouble RotSpeedAbsoluteType::getSetting() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getSetting of crcl.base.RotSpeedAbsoluteType with jthis == NULL." << std::endl;
                return (jdouble) -1.0;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," RotSpeedAbsoluteType::getSetting jthis=",jthis);
            jdouble retVal= (jdouble) -1.0;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getSetting", "()D");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," RotSpeedAbsoluteType::getSetting jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.RotSpeedAbsoluteType has no method named getSetting with signature ()D." << std::endl;
                    return (jdouble) -1.0;
                } else {
                    retVal= (jdouble)  env->CallDoubleMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," RotSpeedAbsoluteType::getSetting jthrowable t=",t);
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

        void RotSpeedAbsoluteType::setSetting(jdouble double_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setSetting of crcl.base.RotSpeedAbsoluteType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," RotSpeedAbsoluteType::setSetting jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setSetting", "(D)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," RotSpeedAbsoluteType::setSetting jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.RotSpeedAbsoluteType has no method named setSetting with signature (D)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,double_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," RotSpeedAbsoluteType::setSetting jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }
        static jclass getNewRotSpeedAbsoluteTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/RotSpeedAbsoluteType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/RotSpeedAbsoluteType" << std::endl;
            }
            return clss;
        }
        
        static jclass RotSpeedAbsoluteTypeClass = NULL;
        static inline jclass getRotSpeedAbsoluteTypeClass() {
            if (RotSpeedAbsoluteTypeClass != NULL) {
                return RotSpeedAbsoluteTypeClass;
            }
            RotSpeedAbsoluteTypeClass = getNewRotSpeedAbsoluteTypeClass();
            return RotSpeedAbsoluteTypeClass;
        }

    // class_index = 5 clss=class crcl.base.GuardLimitEnumType

        
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
                        DebugPrintJObject(__FILE__,__LINE__," GuardLimitEnumType::setSetting jthis=",t);
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

    // class_index = 6 clss=class crcl.base.OpenToolChangerType

        
        // get JNI handle for class crcl.base.OpenToolChangerType
        static inline jclass getOpenToolChangerTypeClass();
        
        OpenToolChangerType::OpenToolChangerType(jobject _jthis, bool copy): MiddleCommandType(_jthis,copy) {
                
        }
        
        OpenToolChangerType::OpenToolChangerType(const OpenToolChangerType &objref): MiddleCommandType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class OpenToolChangerType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class OpenToolChangerType jthis=",jthis);
            }
        }
        
        OpenToolChangerType OpenToolChangerType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getOpenToolChangerTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            OpenToolChangerType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool OpenToolChangerType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getOpenToolChangerTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        OpenToolChangerType::OpenToolChangerType() : MiddleCommandType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getOpenToolChangerTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class OpenToolChangerType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," OpenToolChangerType::fromValue jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new OpenToolChangerType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new OpenToolChangerType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.OpenToolChangerType
        OpenToolChangerType::~OpenToolChangerType() {
        	// Place-holder for later extensibility.
        }

        static jclass getNewOpenToolChangerTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/OpenToolChangerType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/OpenToolChangerType" << std::endl;
            }
            return clss;
        }
        
        static jclass OpenToolChangerTypeClass = NULL;
        static inline jclass getOpenToolChangerTypeClass() {
            if (OpenToolChangerTypeClass != NULL) {
                return OpenToolChangerTypeClass;
            }
            OpenToolChangerTypeClass = getNewOpenToolChangerTypeClass();
            return OpenToolChangerTypeClass;
        }

    // class_index = 7 clss=class crcl.base.TorqueUnitEnumType

        
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

    // class_index = 8 clss=class crcl.base.MessageType

        
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
                            DebugPrintJObject(__FILE__,__LINE__," MessageType::fromValue jthis=",t);
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

    // class_index = 9 clss=class crcl.base.JointDetailsType

        
        // get JNI handle for class crcl.base.JointDetailsType
        static inline jclass getJointDetailsTypeClass();
        
        JointDetailsType::JointDetailsType(jobject _jthis, bool copy): DataThingType(_jthis,copy) {
                
        }
        
        JointDetailsType::JointDetailsType(const JointDetailsType &objref): DataThingType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class JointDetailsType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class JointDetailsType jthis=",jthis);
            }
        }
        
        JointDetailsType JointDetailsType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getJointDetailsTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            JointDetailsType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool JointDetailsType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getJointDetailsTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        JointDetailsType::JointDetailsType() : DataThingType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getJointDetailsTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class JointDetailsType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," JointDetailsType::setMessage jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new JointDetailsType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new JointDetailsType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.JointDetailsType
        JointDetailsType::~JointDetailsType() {
        	// Place-holder for later extensibility.
        }

        static jclass getNewJointDetailsTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/JointDetailsType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/JointDetailsType" << std::endl;
            }
            return clss;
        }
        
        static jclass JointDetailsTypeClass = NULL;
        static inline jclass getJointDetailsTypeClass() {
            if (JointDetailsTypeClass != NULL) {
                return JointDetailsTypeClass;
            }
            JointDetailsTypeClass = getNewJointDetailsTypeClass();
            return JointDetailsTypeClass;
        }
        } // end namespace base
    } // end namespace crcl

    
    
        // end namespace crclj
    }
    
