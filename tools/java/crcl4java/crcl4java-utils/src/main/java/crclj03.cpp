
#include <jni.h>
#include <cstdlib>
#include <iostream>
#include "crclj.h"

namespace crclj {

    



// start_segment_index = 30
// start_segment_index = 40
// segment_index = 3
// classesSegList=[class crcl.base.JointStatusType, class crcl.base.PoseStatusType, class crcl.base.CRCLCommandInstanceType, class crcl.base.EnableRobotParameterStatusType, class crcl.base.VectorType, class crcl.base.JointStatusesType, class crcl.base.CommandStateEnumType, class crcl.base.SetRobotParametersType, class crcl.base.JointLimitType, class crcl.base.GripperStatusType]


// class_index = 0 clss=class crcl.base.JointStatusType

    namespace crcl{
        namespace base{
        
        // get JNI handle for class crcl.base.JointStatusType
        static inline jclass getJointStatusTypeClass();
        
        JointStatusType::JointStatusType(jobject _jthis, bool copy): DataThingType(_jthis,copy) {
                
        }
        
        JointStatusType::JointStatusType(const JointStatusType &objref): DataThingType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class JointStatusType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class JointStatusType jthis=",jthis);
            }
        }
        
        JointStatusType JointStatusType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getJointStatusTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            JointStatusType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool JointStatusType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getJointStatusTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        JointStatusType::JointStatusType() : DataThingType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getJointStatusTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class JointStatusType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," JointStatusType::setMessage jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new JointStatusType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new JointStatusType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.JointStatusType
        JointStatusType::~JointStatusType() {
        	// Place-holder for later extensibility.
        }


        jint JointStatusType::getJointNumber() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getJointNumber of crcl.base.JointStatusType with jthis == NULL." << std::endl;
                return (jint) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," JointStatusType::getJointNumber jthis=",jthis);
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getJointNumber", "()I");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," JointStatusType::getJointNumber jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.JointStatusType has no method named getJointNumber with signature ()I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallIntMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," JointStatusType::getJointNumber jthrowable t=",t);
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

        void JointStatusType::setJointNumber(jint int_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setJointNumber of crcl.base.JointStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," JointStatusType::setJointNumber jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setJointNumber", "(I)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," JointStatusType::setJointNumber jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.JointStatusType has no method named setJointNumber with signature (I)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,int_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," JointStatusType::setJointNumber jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        ::crclj::java::lang::Double JointStatusType::getJointPosition() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getJointPosition of crcl.base.JointStatusType with jthis == NULL." << std::endl;
                static ::crclj::java::lang::Double nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," JointStatusType::getJointPosition jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getJointPosition", "()Ljava/lang/Double;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," JointStatusType::getJointPosition jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.JointStatusType has no method named getJointPosition with signature ()Ljava/lang/Double;." << std::endl;
                    static ::crclj::java::lang::Double nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," JointStatusType::getJointPosition jthrowable t=",t);
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

        void JointStatusType::setJointPosition(const ::crclj::java::lang::Double & double_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setJointPosition of crcl.base.JointStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," JointStatusType::setJointPosition jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setJointPosition", "(Ljava/lang/Double;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," JointStatusType::setJointPosition jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.JointStatusType has no method named setJointPosition with signature (Ljava/lang/Double;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,double_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," JointStatusType::setJointPosition jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        ::crclj::java::lang::Double JointStatusType::getJointTorqueOrForce() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getJointTorqueOrForce of crcl.base.JointStatusType with jthis == NULL." << std::endl;
                static ::crclj::java::lang::Double nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," JointStatusType::getJointTorqueOrForce jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getJointTorqueOrForce", "()Ljava/lang/Double;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," JointStatusType::getJointTorqueOrForce jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.JointStatusType has no method named getJointTorqueOrForce with signature ()Ljava/lang/Double;." << std::endl;
                    static ::crclj::java::lang::Double nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," JointStatusType::getJointTorqueOrForce jthrowable t=",t);
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

        void JointStatusType::setJointTorqueOrForce(const ::crclj::java::lang::Double & double_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setJointTorqueOrForce of crcl.base.JointStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," JointStatusType::setJointTorqueOrForce jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setJointTorqueOrForce", "(Ljava/lang/Double;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," JointStatusType::setJointTorqueOrForce jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.JointStatusType has no method named setJointTorqueOrForce with signature (Ljava/lang/Double;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,double_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," JointStatusType::setJointTorqueOrForce jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        ::crclj::java::lang::Double JointStatusType::getJointVelocity() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getJointVelocity of crcl.base.JointStatusType with jthis == NULL." << std::endl;
                static ::crclj::java::lang::Double nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," JointStatusType::getJointVelocity jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getJointVelocity", "()Ljava/lang/Double;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," JointStatusType::getJointVelocity jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.JointStatusType has no method named getJointVelocity with signature ()Ljava/lang/Double;." << std::endl;
                    static ::crclj::java::lang::Double nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," JointStatusType::getJointVelocity jthrowable t=",t);
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

        void JointStatusType::setJointVelocity(const ::crclj::java::lang::Double & double_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setJointVelocity of crcl.base.JointStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," JointStatusType::setJointVelocity jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setJointVelocity", "(Ljava/lang/Double;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," JointStatusType::setJointVelocity jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.JointStatusType has no method named setJointVelocity with signature (Ljava/lang/Double;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,double_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," JointStatusType::setJointVelocity jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }
        static jclass getNewJointStatusTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/JointStatusType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/JointStatusType" << std::endl;
            }
            return clss;
        }
        
        static jclass JointStatusTypeClass = NULL;
        static inline jclass getJointStatusTypeClass() {
            if (JointStatusTypeClass != NULL) {
                return JointStatusTypeClass;
            }
            JointStatusTypeClass = getNewJointStatusTypeClass();
            return JointStatusTypeClass;
        }

    // class_index = 1 clss=class crcl.base.PoseStatusType

        
        // get JNI handle for class crcl.base.PoseStatusType
        static inline jclass getPoseStatusTypeClass();
        
        PoseStatusType::PoseStatusType(jobject _jthis, bool copy): DataThingType(_jthis,copy) {
                
        }
        
        PoseStatusType::PoseStatusType(const PoseStatusType &objref): DataThingType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class PoseStatusType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class PoseStatusType jthis=",jthis);
            }
        }
        
        PoseStatusType PoseStatusType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getPoseStatusTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            PoseStatusType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool PoseStatusType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getPoseStatusTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        PoseStatusType::PoseStatusType() : DataThingType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getPoseStatusTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class PoseStatusType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," PoseStatusType::setJointVelocity jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new PoseStatusType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new PoseStatusType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.PoseStatusType
        PoseStatusType::~PoseStatusType() {
        	// Place-holder for later extensibility.
        }


        PoseType PoseStatusType::getPose() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getPose of crcl.base.PoseStatusType with jthis == NULL." << std::endl;
                static PoseType nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," PoseStatusType::getPose jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getPose", "()Lcrcl/base/PoseType;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," PoseStatusType::getPose jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.PoseStatusType has no method named getPose with signature ()Lcrcl/base/PoseType;." << std::endl;
                    static PoseType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," PoseStatusType::getPose jthrowable t=",t);
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

        void PoseStatusType::setPose(const PoseType & poseType_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setPose of crcl.base.PoseStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," PoseStatusType::setPose jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setPose", "(Lcrcl/base/PoseType;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," PoseStatusType::setPose jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.PoseStatusType has no method named setPose with signature (Lcrcl/base/PoseType;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,poseType_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," PoseStatusType::setPose jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        TwistType PoseStatusType::getTwist() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getTwist of crcl.base.PoseStatusType with jthis == NULL." << std::endl;
                static TwistType nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," PoseStatusType::getTwist jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getTwist", "()Lcrcl/base/TwistType;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," PoseStatusType::getTwist jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.PoseStatusType has no method named getTwist with signature ()Lcrcl/base/TwistType;." << std::endl;
                    static TwistType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," PoseStatusType::getTwist jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            TwistType retObject(retVal,false);
            return retObject;
        }

        void PoseStatusType::setTwist(const TwistType & twistType_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setTwist of crcl.base.PoseStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," PoseStatusType::setTwist jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setTwist", "(Lcrcl/base/TwistType;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," PoseStatusType::setTwist jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.PoseStatusType has no method named setTwist with signature (Lcrcl/base/TwistType;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,twistType_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," PoseStatusType::setTwist jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        WrenchType PoseStatusType::getWrench() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getWrench of crcl.base.PoseStatusType with jthis == NULL." << std::endl;
                static WrenchType nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," PoseStatusType::getWrench jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getWrench", "()Lcrcl/base/WrenchType;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," PoseStatusType::getWrench jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.PoseStatusType has no method named getWrench with signature ()Lcrcl/base/WrenchType;." << std::endl;
                    static WrenchType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," PoseStatusType::getWrench jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            WrenchType retObject(retVal,false);
            return retObject;
        }

        void PoseStatusType::setWrench(const WrenchType & wrenchType_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setWrench of crcl.base.PoseStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," PoseStatusType::setWrench jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setWrench", "(Lcrcl/base/WrenchType;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," PoseStatusType::setWrench jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.PoseStatusType has no method named setWrench with signature (Lcrcl/base/WrenchType;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,wrenchType_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," PoseStatusType::setWrench jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        jstring PoseStatusType::getConfiguration() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getConfiguration of crcl.base.PoseStatusType with jthis == NULL." << std::endl;
                return NULL;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," PoseStatusType::getConfiguration jthis=",jthis);
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getConfiguration", "()Ljava/lang/String;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," PoseStatusType::getConfiguration jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.PoseStatusType has no method named getConfiguration with signature ()Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," PoseStatusType::getConfiguration jthrowable t=",t);
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

        void PoseStatusType::setConfiguration(jstring string_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setConfiguration of crcl.base.PoseStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," PoseStatusType::setConfiguration jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setConfiguration", "(Ljava/lang/String;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," PoseStatusType::setConfiguration jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.PoseStatusType has no method named setConfiguration with signature (Ljava/lang/String;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," PoseStatusType::setConfiguration jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        // Easy call alternative for setConfiguration
        void PoseStatusType::setConfiguration(const char * easyArg_0) {
            // convenience method, converts to/from JNI types to common C++ types.
            if(jthis == NULL) {
                std::cerr << "Call of method setConfiguration of crcl.base.PoseStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," PoseStatusType::setConfiguration jthis=",jthis);
            
                    
            jstring string_0 = env->NewStringUTF(easyArg_0);
            setConfiguration(string_0);
            jobjectRefType ref_0 = env->GetObjectRefType(string_0);
            if(ref_0 == JNIGlobalRefType) {
                env->DeleteGlobalRef(string_0);
            }
            
            releaseEnv(env);
        }

        static jclass getNewPoseStatusTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/PoseStatusType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/PoseStatusType" << std::endl;
            }
            return clss;
        }
        
        static jclass PoseStatusTypeClass = NULL;
        static inline jclass getPoseStatusTypeClass() {
            if (PoseStatusTypeClass != NULL) {
                return PoseStatusTypeClass;
            }
            PoseStatusTypeClass = getNewPoseStatusTypeClass();
            return PoseStatusTypeClass;
        }

    // class_index = 2 clss=class crcl.base.CRCLCommandInstanceType

        
        // get JNI handle for class crcl.base.CRCLCommandInstanceType
        static inline jclass getCRCLCommandInstanceTypeClass();
        
        CRCLCommandInstanceType::CRCLCommandInstanceType(jobject _jthis, bool copy): DataThingType(_jthis,copy) {
                
        }
        
        CRCLCommandInstanceType::CRCLCommandInstanceType(const CRCLCommandInstanceType &objref): DataThingType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class CRCLCommandInstanceType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class CRCLCommandInstanceType jthis=",jthis);
            }
        }
        
        CRCLCommandInstanceType CRCLCommandInstanceType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getCRCLCommandInstanceTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            CRCLCommandInstanceType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool CRCLCommandInstanceType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getCRCLCommandInstanceTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        CRCLCommandInstanceType::CRCLCommandInstanceType() : DataThingType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getCRCLCommandInstanceTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class CRCLCommandInstanceType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLCommandInstanceType::setConfiguration jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new CRCLCommandInstanceType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new CRCLCommandInstanceType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.CRCLCommandInstanceType
        CRCLCommandInstanceType::~CRCLCommandInstanceType() {
        	// Place-holder for later extensibility.
        }


        CRCLCommandType CRCLCommandInstanceType::getCRCLCommand() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getCRCLCommand of crcl.base.CRCLCommandInstanceType with jthis == NULL." << std::endl;
                static CRCLCommandType nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLCommandInstanceType::getCRCLCommand jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getCRCLCommand", "()Lcrcl/base/CRCLCommandType;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLCommandInstanceType::getCRCLCommand jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.CRCLCommandInstanceType has no method named getCRCLCommand with signature ()Lcrcl/base/CRCLCommandType;." << std::endl;
                    static CRCLCommandType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLCommandInstanceType::getCRCLCommand jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            CRCLCommandType retObject(retVal,false);
            return retObject;
        }

        void CRCLCommandInstanceType::setCRCLCommand(const CRCLCommandType & cRCLCommandType_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setCRCLCommand of crcl.base.CRCLCommandInstanceType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLCommandInstanceType::setCRCLCommand jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setCRCLCommand", "(Lcrcl/base/CRCLCommandType;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLCommandInstanceType::setCRCLCommand jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.CRCLCommandInstanceType has no method named setCRCLCommand with signature (Lcrcl/base/CRCLCommandType;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,cRCLCommandType_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLCommandInstanceType::setCRCLCommand jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        jstring CRCLCommandInstanceType::getProgramFile() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getProgramFile of crcl.base.CRCLCommandInstanceType with jthis == NULL." << std::endl;
                return NULL;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLCommandInstanceType::getProgramFile jthis=",jthis);
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getProgramFile", "()Ljava/lang/String;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLCommandInstanceType::getProgramFile jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.CRCLCommandInstanceType has no method named getProgramFile with signature ()Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLCommandInstanceType::getProgramFile jthrowable t=",t);
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

        void CRCLCommandInstanceType::setProgramFile(jstring string_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setProgramFile of crcl.base.CRCLCommandInstanceType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLCommandInstanceType::setProgramFile jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setProgramFile", "(Ljava/lang/String;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLCommandInstanceType::setProgramFile jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.CRCLCommandInstanceType has no method named setProgramFile with signature (Ljava/lang/String;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLCommandInstanceType::setProgramFile jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        // Easy call alternative for setProgramFile
        void CRCLCommandInstanceType::setProgramFile(const char * easyArg_0) {
            // convenience method, converts to/from JNI types to common C++ types.
            if(jthis == NULL) {
                std::cerr << "Call of method setProgramFile of crcl.base.CRCLCommandInstanceType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLCommandInstanceType::setProgramFile jthis=",jthis);
            
                    
            jstring string_0 = env->NewStringUTF(easyArg_0);
            setProgramFile(string_0);
            jobjectRefType ref_0 = env->GetObjectRefType(string_0);
            if(ref_0 == JNIGlobalRefType) {
                env->DeleteGlobalRef(string_0);
            }
            
            releaseEnv(env);
        }


        ::crclj::java::lang::Integer CRCLCommandInstanceType::getProgramIndex() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getProgramIndex of crcl.base.CRCLCommandInstanceType with jthis == NULL." << std::endl;
                static ::crclj::java::lang::Integer nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLCommandInstanceType::getProgramIndex jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getProgramIndex", "()Ljava/lang/Integer;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLCommandInstanceType::getProgramIndex jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.CRCLCommandInstanceType has no method named getProgramIndex with signature ()Ljava/lang/Integer;." << std::endl;
                    static ::crclj::java::lang::Integer nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLCommandInstanceType::getProgramIndex jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            ::crclj::java::lang::Integer retObject(retVal,false);
            return retObject;
        }

        void CRCLCommandInstanceType::setProgramIndex(const ::crclj::java::lang::Integer & integer_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setProgramIndex of crcl.base.CRCLCommandInstanceType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLCommandInstanceType::setProgramIndex jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setProgramIndex", "(Ljava/lang/Integer;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLCommandInstanceType::setProgramIndex jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.CRCLCommandInstanceType has no method named setProgramIndex with signature (Ljava/lang/Integer;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,integer_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLCommandInstanceType::setProgramIndex jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        ::crclj::java::lang::Integer CRCLCommandInstanceType::getProgramLength() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getProgramLength of crcl.base.CRCLCommandInstanceType with jthis == NULL." << std::endl;
                static ::crclj::java::lang::Integer nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLCommandInstanceType::getProgramLength jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getProgramLength", "()Ljava/lang/Integer;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLCommandInstanceType::getProgramLength jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.CRCLCommandInstanceType has no method named getProgramLength with signature ()Ljava/lang/Integer;." << std::endl;
                    static ::crclj::java::lang::Integer nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLCommandInstanceType::getProgramLength jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            ::crclj::java::lang::Integer retObject(retVal,false);
            return retObject;
        }

        void CRCLCommandInstanceType::setProgramLength(const ::crclj::java::lang::Integer & integer_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setProgramLength of crcl.base.CRCLCommandInstanceType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLCommandInstanceType::setProgramLength jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setProgramLength", "(Ljava/lang/Integer;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLCommandInstanceType::setProgramLength jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.CRCLCommandInstanceType has no method named setProgramLength with signature (Ljava/lang/Integer;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,integer_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLCommandInstanceType::setProgramLength jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }
        static jclass getNewCRCLCommandInstanceTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/CRCLCommandInstanceType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/CRCLCommandInstanceType" << std::endl;
            }
            return clss;
        }
        
        static jclass CRCLCommandInstanceTypeClass = NULL;
        static inline jclass getCRCLCommandInstanceTypeClass() {
            if (CRCLCommandInstanceTypeClass != NULL) {
                return CRCLCommandInstanceTypeClass;
            }
            CRCLCommandInstanceTypeClass = getNewCRCLCommandInstanceTypeClass();
            return CRCLCommandInstanceTypeClass;
        }

    // class_index = 3 clss=class crcl.base.EnableRobotParameterStatusType

        
        // get JNI handle for class crcl.base.EnableRobotParameterStatusType
        static inline jclass getEnableRobotParameterStatusTypeClass();
        
        EnableRobotParameterStatusType::EnableRobotParameterStatusType(jobject _jthis, bool copy): MiddleCommandType(_jthis,copy) {
                
        }
        
        EnableRobotParameterStatusType::EnableRobotParameterStatusType(const EnableRobotParameterStatusType &objref): MiddleCommandType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class EnableRobotParameterStatusType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class EnableRobotParameterStatusType jthis=",jthis);
            }
        }
        
        EnableRobotParameterStatusType EnableRobotParameterStatusType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getEnableRobotParameterStatusTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            EnableRobotParameterStatusType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool EnableRobotParameterStatusType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getEnableRobotParameterStatusTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        EnableRobotParameterStatusType::EnableRobotParameterStatusType() : MiddleCommandType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getEnableRobotParameterStatusTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class EnableRobotParameterStatusType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," EnableRobotParameterStatusType::setProgramLength jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new EnableRobotParameterStatusType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new EnableRobotParameterStatusType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.EnableRobotParameterStatusType
        EnableRobotParameterStatusType::~EnableRobotParameterStatusType() {
        	// Place-holder for later extensibility.
        }


        jstring EnableRobotParameterStatusType::getRobotParameterName() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getRobotParameterName of crcl.base.EnableRobotParameterStatusType with jthis == NULL." << std::endl;
                return NULL;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," EnableRobotParameterStatusType::getRobotParameterName jthis=",jthis);
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getRobotParameterName", "()Ljava/lang/String;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," EnableRobotParameterStatusType::getRobotParameterName jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.EnableRobotParameterStatusType has no method named getRobotParameterName with signature ()Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," EnableRobotParameterStatusType::getRobotParameterName jthrowable t=",t);
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

        void EnableRobotParameterStatusType::setRobotParameterName(jstring string_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setRobotParameterName of crcl.base.EnableRobotParameterStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," EnableRobotParameterStatusType::setRobotParameterName jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setRobotParameterName", "(Ljava/lang/String;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," EnableRobotParameterStatusType::setRobotParameterName jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.EnableRobotParameterStatusType has no method named setRobotParameterName with signature (Ljava/lang/String;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," EnableRobotParameterStatusType::setRobotParameterName jthrowable t=",t);
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
        void EnableRobotParameterStatusType::setRobotParameterName(const char * easyArg_0) {
            // convenience method, converts to/from JNI types to common C++ types.
            if(jthis == NULL) {
                std::cerr << "Call of method setRobotParameterName of crcl.base.EnableRobotParameterStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," EnableRobotParameterStatusType::setRobotParameterName jthis=",jthis);
            
                    
            jstring string_0 = env->NewStringUTF(easyArg_0);
            setRobotParameterName(string_0);
            jobjectRefType ref_0 = env->GetObjectRefType(string_0);
            if(ref_0 == JNIGlobalRefType) {
                env->DeleteGlobalRef(string_0);
            }
            
            releaseEnv(env);
        }

        static jclass getNewEnableRobotParameterStatusTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/EnableRobotParameterStatusType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/EnableRobotParameterStatusType" << std::endl;
            }
            return clss;
        }
        
        static jclass EnableRobotParameterStatusTypeClass = NULL;
        static inline jclass getEnableRobotParameterStatusTypeClass() {
            if (EnableRobotParameterStatusTypeClass != NULL) {
                return EnableRobotParameterStatusTypeClass;
            }
            EnableRobotParameterStatusTypeClass = getNewEnableRobotParameterStatusTypeClass();
            return EnableRobotParameterStatusTypeClass;
        }

    // class_index = 4 clss=class crcl.base.VectorType

        
        // get JNI handle for class crcl.base.VectorType
        static inline jclass getVectorTypeClass();
        
        VectorType::VectorType(jobject _jthis, bool copy): DataThingType(_jthis,copy) {
                
        }
        
        VectorType::VectorType(const VectorType &objref): DataThingType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class VectorType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class VectorType jthis=",jthis);
            }
        }
        
        VectorType VectorType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getVectorTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            VectorType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool VectorType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getVectorTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        VectorType::VectorType() : DataThingType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getVectorTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class VectorType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," VectorType::setRobotParameterName jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new VectorType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new VectorType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.VectorType
        VectorType::~VectorType() {
        	// Place-holder for later extensibility.
        }


        void VectorType::setK(jdouble double_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setK of crcl.base.VectorType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," VectorType::setK jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setK", "(D)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," VectorType::setK jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.VectorType has no method named setK with signature (D)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,double_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," VectorType::setK jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        jdouble VectorType::getI() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getI of crcl.base.VectorType with jthis == NULL." << std::endl;
                return (jdouble) -1.0;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," VectorType::getI jthis=",jthis);
            jdouble retVal= (jdouble) -1.0;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getI", "()D");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," VectorType::getI jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.VectorType has no method named getI with signature ()D." << std::endl;
                    return (jdouble) -1.0;
                } else {
                    retVal= (jdouble)  env->CallDoubleMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," VectorType::getI jthrowable t=",t);
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

        void VectorType::setI(jdouble double_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setI of crcl.base.VectorType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," VectorType::setI jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setI", "(D)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," VectorType::setI jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.VectorType has no method named setI with signature (D)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,double_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," VectorType::setI jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        jdouble VectorType::getJ() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getJ of crcl.base.VectorType with jthis == NULL." << std::endl;
                return (jdouble) -1.0;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," VectorType::getJ jthis=",jthis);
            jdouble retVal= (jdouble) -1.0;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getJ", "()D");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," VectorType::getJ jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.VectorType has no method named getJ with signature ()D." << std::endl;
                    return (jdouble) -1.0;
                } else {
                    retVal= (jdouble)  env->CallDoubleMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," VectorType::getJ jthrowable t=",t);
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

        void VectorType::setJ(jdouble double_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setJ of crcl.base.VectorType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," VectorType::setJ jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setJ", "(D)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," VectorType::setJ jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.VectorType has no method named setJ with signature (D)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,double_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," VectorType::setJ jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        jdouble VectorType::getK() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getK of crcl.base.VectorType with jthis == NULL." << std::endl;
                return (jdouble) -1.0;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," VectorType::getK jthis=",jthis);
            jdouble retVal= (jdouble) -1.0;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getK", "()D");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," VectorType::getK jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.VectorType has no method named getK with signature ()D." << std::endl;
                    return (jdouble) -1.0;
                } else {
                    retVal= (jdouble)  env->CallDoubleMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," VectorType::getK jthrowable t=",t);
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
        static jclass getNewVectorTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/VectorType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/VectorType" << std::endl;
            }
            return clss;
        }
        
        static jclass VectorTypeClass = NULL;
        static inline jclass getVectorTypeClass() {
            if (VectorTypeClass != NULL) {
                return VectorTypeClass;
            }
            VectorTypeClass = getNewVectorTypeClass();
            return VectorTypeClass;
        }

    // class_index = 5 clss=class crcl.base.JointStatusesType

        
        // get JNI handle for class crcl.base.JointStatusesType
        static inline jclass getJointStatusesTypeClass();
        
        JointStatusesType::JointStatusesType(jobject _jthis, bool copy): DataThingType(_jthis,copy) {
                
        }
        
        JointStatusesType::JointStatusesType(const JointStatusesType &objref): DataThingType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class JointStatusesType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class JointStatusesType jthis=",jthis);
            }
        }
        
        JointStatusesType JointStatusesType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getJointStatusesTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            JointStatusesType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool JointStatusesType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getJointStatusesTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        JointStatusesType::JointStatusesType() : DataThingType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getJointStatusesTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class JointStatusesType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," JointStatusesType::getK jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new JointStatusesType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new JointStatusesType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.JointStatusesType
        JointStatusesType::~JointStatusesType() {
        	// Place-holder for later extensibility.
        }


        ::crclj::java::util::List JointStatusesType::getJointStatus() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getJointStatus of crcl.base.JointStatusesType with jthis == NULL." << std::endl;
                static ::crclj::java::util::List nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," JointStatusesType::getJointStatus jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getJointStatus", "()Ljava/util/List;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," JointStatusesType::getJointStatus jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.JointStatusesType has no method named getJointStatus with signature ()Ljava/util/List;." << std::endl;
                    static ::crclj::java::util::List nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," JointStatusesType::getJointStatus jthrowable t=",t);
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
        static jclass getNewJointStatusesTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/JointStatusesType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/JointStatusesType" << std::endl;
            }
            return clss;
        }
        
        static jclass JointStatusesTypeClass = NULL;
        static inline jclass getJointStatusesTypeClass() {
            if (JointStatusesTypeClass != NULL) {
                return JointStatusesTypeClass;
            }
            JointStatusesTypeClass = getNewJointStatusesTypeClass();
            return JointStatusesTypeClass;
        }

    // class_index = 6 clss=class crcl.base.CommandStateEnumType

        
        // get JNI handle for class crcl.base.CommandStateEnumType
        static inline jclass getCommandStateEnumTypeClass();
        
        CommandStateEnumType::CommandStateEnumType(jobject _jthis, bool copy): ::crclj::java::lang::Enum(_jthis,copy) {
                
        }
        
        CommandStateEnumType::CommandStateEnumType(const CommandStateEnumType &objref): ::crclj::java::lang::Enum((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class CommandStateEnumType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class CommandStateEnumType jthis=",jthis);
            }
        }
        
        CommandStateEnumType CommandStateEnumType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getCommandStateEnumTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            CommandStateEnumType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool CommandStateEnumType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getCommandStateEnumTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        CommandStateEnumType::CommandStateEnumType() : ::crclj::java::lang::Enum((jobject)NULL,false) {
        JNIEnv *env =getEnv();
        static jclass cls = getCommandStateEnumTypeClass();
        if (cls != NULL) {
            static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
            if (NULL == mid) {
                std::cerr << "Class CommandStateEnumType has no method constructor signature ()V" << std::endl;
            } else {
                jthis = env->NewObject(cls, mid );
                jthrowable t = env->ExceptionOccurred();
                if(t != NULL) {
                    if(GetDebugJ4Cpp()) {
                        DebugPrintJObject(__FILE__,__LINE__," CommandStateEnumType::getJointStatus jthis=",t);
                        env->ExceptionDescribe();
                    }
                    throw t;
                }
                if(jthis == NULL) {
                    std::cerr << "Call to create new CommandStateEnumType with signature ()V returned null." << std::endl;
                    releaseEnv(env);
                    return;
                }
                jobjectRefType ref = env->GetObjectRefType(jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new CommandStateEnumType jthis=",jthis);
                if(ref != JNIGlobalRefType) {
                    jthis = env->NewGlobalRef(jthis);
                }
            }
        }
        releaseEnv(env);
        }


        // Destructor for crcl.base.CommandStateEnumType
        CommandStateEnumType::~CommandStateEnumType() {
        	// Place-holder for later extensibility.
        }


        // Field getter for CRCL_DONE
        CommandStateEnumType CommandStateEnumType::getCRCL_DONE() {
        JNIEnv *env =getEnv();
        static jclass cls = getCommandStateEnumTypeClass();
        jobject retVal=NULL;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "CRCL_DONE", "Lcrcl/base/CommandStateEnumType;");
            if (NULL == fid) {
                std::cerr << "Class crcl.base.CommandStateEnumType has no field named CRCL_DONE with signature Lcrcl/base/CommandStateEnumType;." << std::endl;
                static CommandStateEnumType nullObject((jobject)NULL,false); return nullObject;
            } else {
                retVal= (jobject)  env->GetStaticObjectField( cls, fid );
            }
        }
        releaseEnv(env);
                    
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            CommandStateEnumType retObject(retVal,false);
            return retObject;
        }

        // Field getter for CRCL_ERROR
        CommandStateEnumType CommandStateEnumType::getCRCL_ERROR() {
        JNIEnv *env =getEnv();
        static jclass cls = getCommandStateEnumTypeClass();
        jobject retVal=NULL;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "CRCL_ERROR", "Lcrcl/base/CommandStateEnumType;");
            if (NULL == fid) {
                std::cerr << "Class crcl.base.CommandStateEnumType has no field named CRCL_ERROR with signature Lcrcl/base/CommandStateEnumType;." << std::endl;
                static CommandStateEnumType nullObject((jobject)NULL,false); return nullObject;
            } else {
                retVal= (jobject)  env->GetStaticObjectField( cls, fid );
            }
        }
        releaseEnv(env);
                    
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            CommandStateEnumType retObject(retVal,false);
            return retObject;
        }

        // Field getter for CRCL_WORKING
        CommandStateEnumType CommandStateEnumType::getCRCL_WORKING() {
        JNIEnv *env =getEnv();
        static jclass cls = getCommandStateEnumTypeClass();
        jobject retVal=NULL;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "CRCL_WORKING", "Lcrcl/base/CommandStateEnumType;");
            if (NULL == fid) {
                std::cerr << "Class crcl.base.CommandStateEnumType has no field named CRCL_WORKING with signature Lcrcl/base/CommandStateEnumType;." << std::endl;
                static CommandStateEnumType nullObject((jobject)NULL,false); return nullObject;
            } else {
                retVal= (jobject)  env->GetStaticObjectField( cls, fid );
            }
        }
        releaseEnv(env);
                    
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            CommandStateEnumType retObject(retVal,false);
            return retObject;
        }

        // Field getter for CRCL_READY
        CommandStateEnumType CommandStateEnumType::getCRCL_READY() {
        JNIEnv *env =getEnv();
        static jclass cls = getCommandStateEnumTypeClass();
        jobject retVal=NULL;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "CRCL_READY", "Lcrcl/base/CommandStateEnumType;");
            if (NULL == fid) {
                std::cerr << "Class crcl.base.CommandStateEnumType has no field named CRCL_READY with signature Lcrcl/base/CommandStateEnumType;." << std::endl;
                static CommandStateEnumType nullObject((jobject)NULL,false); return nullObject;
            } else {
                retVal= (jobject)  env->GetStaticObjectField( cls, fid );
            }
        }
        releaseEnv(env);
                    
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            CommandStateEnumType retObject(retVal,false);
            return retObject;
        }

        jstring CommandStateEnumType::value() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method value of crcl.base.CommandStateEnumType with jthis == NULL." << std::endl;
                return NULL;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CommandStateEnumType::value jthis=",jthis);
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "value", "()Ljava/lang/String;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CommandStateEnumType::value jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.CommandStateEnumType has no method named value with signature ()Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CommandStateEnumType::value jthrowable t=",t);
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

        jobjectArray CommandStateEnumType::values() {
            JNIEnv *env =getEnv();
            static jclass cls = getCommandStateEnumTypeClass();
            jobjectArray retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "values", "()[Lcrcl/base/CommandStateEnumType;");
                if (NULL == mid) {
                    std::cerr << "Class crcl.base.CommandStateEnumType has no method named values with signature ()[Lcrcl/base/CommandStateEnumType;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jobjectArray)  env->CallStaticObjectMethod( cls, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," CommandStateEnumType::values jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        CommandStateEnumType CommandStateEnumType::valueOf(jstring string_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getCommandStateEnumTypeClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "valueOf", "(Ljava/lang/String;)Lcrcl/base/CommandStateEnumType;");
                if (NULL == mid) {
                    std::cerr << "Class crcl.base.CommandStateEnumType has no method named valueOf with signature (Ljava/lang/String;)Lcrcl/base/CommandStateEnumType;." << std::endl;
                    static CommandStateEnumType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," CommandStateEnumType::valueOf jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            CommandStateEnumType retObject(retVal,false);
            return retObject;
        }

        CommandStateEnumType CommandStateEnumType::fromValue(jstring string_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getCommandStateEnumTypeClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "fromValue", "(Ljava/lang/String;)Lcrcl/base/CommandStateEnumType;");
                if (NULL == mid) {
                    std::cerr << "Class crcl.base.CommandStateEnumType has no method named fromValue with signature (Ljava/lang/String;)Lcrcl/base/CommandStateEnumType;." << std::endl;
                    static CommandStateEnumType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," CommandStateEnumType::fromValue jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            CommandStateEnumType retObject(retVal,false);
            return retObject;
        }
        static jclass getNewCommandStateEnumTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/CommandStateEnumType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/CommandStateEnumType" << std::endl;
            }
            return clss;
        }
        
        static jclass CommandStateEnumTypeClass = NULL;
        static inline jclass getCommandStateEnumTypeClass() {
            if (CommandStateEnumTypeClass != NULL) {
                return CommandStateEnumTypeClass;
            }
            CommandStateEnumTypeClass = getNewCommandStateEnumTypeClass();
            return CommandStateEnumTypeClass;
        }

    // class_index = 7 clss=class crcl.base.SetRobotParametersType

        
        // get JNI handle for class crcl.base.SetRobotParametersType
        static inline jclass getSetRobotParametersTypeClass();
        
        SetRobotParametersType::SetRobotParametersType(jobject _jthis, bool copy): MiddleCommandType(_jthis,copy) {
                
        }
        
        SetRobotParametersType::SetRobotParametersType(const SetRobotParametersType &objref): MiddleCommandType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class SetRobotParametersType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class SetRobotParametersType jthis=",jthis);
            }
        }
        
        SetRobotParametersType SetRobotParametersType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getSetRobotParametersTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            SetRobotParametersType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool SetRobotParametersType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getSetRobotParametersTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        SetRobotParametersType::SetRobotParametersType() : MiddleCommandType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getSetRobotParametersTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class SetRobotParametersType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," SetRobotParametersType::fromValue jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new SetRobotParametersType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new SetRobotParametersType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.SetRobotParametersType
        SetRobotParametersType::~SetRobotParametersType() {
        	// Place-holder for later extensibility.
        }


        ::crclj::java::util::List SetRobotParametersType::getParameterSetting() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getParameterSetting of crcl.base.SetRobotParametersType with jthis == NULL." << std::endl;
                static ::crclj::java::util::List nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," SetRobotParametersType::getParameterSetting jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getParameterSetting", "()Ljava/util/List;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," SetRobotParametersType::getParameterSetting jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.SetRobotParametersType has no method named getParameterSetting with signature ()Ljava/util/List;." << std::endl;
                    static ::crclj::java::util::List nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," SetRobotParametersType::getParameterSetting jthrowable t=",t);
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
        static jclass getNewSetRobotParametersTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/SetRobotParametersType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/SetRobotParametersType" << std::endl;
            }
            return clss;
        }
        
        static jclass SetRobotParametersTypeClass = NULL;
        static inline jclass getSetRobotParametersTypeClass() {
            if (SetRobotParametersTypeClass != NULL) {
                return SetRobotParametersTypeClass;
            }
            SetRobotParametersTypeClass = getNewSetRobotParametersTypeClass();
            return SetRobotParametersTypeClass;
        }

    // class_index = 8 clss=class crcl.base.JointLimitType

        
        // get JNI handle for class crcl.base.JointLimitType
        static inline jclass getJointLimitTypeClass();
        
        JointLimitType::JointLimitType(jobject _jthis, bool copy): DataThingType(_jthis,copy) {
                
        }
        
        JointLimitType::JointLimitType(const JointLimitType &objref): DataThingType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class JointLimitType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class JointLimitType jthis=",jthis);
            }
        }
        
        JointLimitType JointLimitType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getJointLimitTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            JointLimitType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool JointLimitType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getJointLimitTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        JointLimitType::JointLimitType() : DataThingType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getJointLimitTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class JointLimitType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," JointLimitType::getParameterSetting jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new JointLimitType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new JointLimitType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.JointLimitType
        JointLimitType::~JointLimitType() {
        	// Place-holder for later extensibility.
        }


        jint JointLimitType::getJointNumber() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getJointNumber of crcl.base.JointLimitType with jthis == NULL." << std::endl;
                return (jint) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," JointLimitType::getJointNumber jthis=",jthis);
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getJointNumber", "()I");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," JointLimitType::getJointNumber jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.JointLimitType has no method named getJointNumber with signature ()I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallIntMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," JointLimitType::getJointNumber jthrowable t=",t);
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

        void JointLimitType::setJointNumber(jint int_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setJointNumber of crcl.base.JointLimitType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," JointLimitType::setJointNumber jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setJointNumber", "(I)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," JointLimitType::setJointNumber jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.JointLimitType has no method named setJointNumber with signature (I)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,int_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," JointLimitType::setJointNumber jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        ::crclj::java::lang::Double JointLimitType::getJointMinPosition() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getJointMinPosition of crcl.base.JointLimitType with jthis == NULL." << std::endl;
                static ::crclj::java::lang::Double nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," JointLimitType::getJointMinPosition jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getJointMinPosition", "()Ljava/lang/Double;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," JointLimitType::getJointMinPosition jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.JointLimitType has no method named getJointMinPosition with signature ()Ljava/lang/Double;." << std::endl;
                    static ::crclj::java::lang::Double nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," JointLimitType::getJointMinPosition jthrowable t=",t);
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

        void JointLimitType::setJointMinPosition(const ::crclj::java::lang::Double & double_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setJointMinPosition of crcl.base.JointLimitType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," JointLimitType::setJointMinPosition jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setJointMinPosition", "(Ljava/lang/Double;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," JointLimitType::setJointMinPosition jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.JointLimitType has no method named setJointMinPosition with signature (Ljava/lang/Double;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,double_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," JointLimitType::setJointMinPosition jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        ::crclj::java::lang::Double JointLimitType::getJointMaxPosition() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getJointMaxPosition of crcl.base.JointLimitType with jthis == NULL." << std::endl;
                static ::crclj::java::lang::Double nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," JointLimitType::getJointMaxPosition jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getJointMaxPosition", "()Ljava/lang/Double;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," JointLimitType::getJointMaxPosition jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.JointLimitType has no method named getJointMaxPosition with signature ()Ljava/lang/Double;." << std::endl;
                    static ::crclj::java::lang::Double nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," JointLimitType::getJointMaxPosition jthrowable t=",t);
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

        void JointLimitType::setJointMaxPosition(const ::crclj::java::lang::Double & double_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setJointMaxPosition of crcl.base.JointLimitType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," JointLimitType::setJointMaxPosition jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setJointMaxPosition", "(Ljava/lang/Double;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," JointLimitType::setJointMaxPosition jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.JointLimitType has no method named setJointMaxPosition with signature (Ljava/lang/Double;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,double_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," JointLimitType::setJointMaxPosition jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        ::crclj::java::lang::Double JointLimitType::getJointMaxTorqueOrForce() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getJointMaxTorqueOrForce of crcl.base.JointLimitType with jthis == NULL." << std::endl;
                static ::crclj::java::lang::Double nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," JointLimitType::getJointMaxTorqueOrForce jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getJointMaxTorqueOrForce", "()Ljava/lang/Double;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," JointLimitType::getJointMaxTorqueOrForce jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.JointLimitType has no method named getJointMaxTorqueOrForce with signature ()Ljava/lang/Double;." << std::endl;
                    static ::crclj::java::lang::Double nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," JointLimitType::getJointMaxTorqueOrForce jthrowable t=",t);
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

        void JointLimitType::setJointMaxTorqueOrForce(const ::crclj::java::lang::Double & double_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setJointMaxTorqueOrForce of crcl.base.JointLimitType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," JointLimitType::setJointMaxTorqueOrForce jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setJointMaxTorqueOrForce", "(Ljava/lang/Double;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," JointLimitType::setJointMaxTorqueOrForce jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.JointLimitType has no method named setJointMaxTorqueOrForce with signature (Ljava/lang/Double;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,double_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," JointLimitType::setJointMaxTorqueOrForce jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        ::crclj::java::lang::Double JointLimitType::getJointMaxVelocity() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getJointMaxVelocity of crcl.base.JointLimitType with jthis == NULL." << std::endl;
                static ::crclj::java::lang::Double nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," JointLimitType::getJointMaxVelocity jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getJointMaxVelocity", "()Ljava/lang/Double;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," JointLimitType::getJointMaxVelocity jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.JointLimitType has no method named getJointMaxVelocity with signature ()Ljava/lang/Double;." << std::endl;
                    static ::crclj::java::lang::Double nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," JointLimitType::getJointMaxVelocity jthrowable t=",t);
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

        void JointLimitType::setJointMaxVelocity(const ::crclj::java::lang::Double & double_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setJointMaxVelocity of crcl.base.JointLimitType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," JointLimitType::setJointMaxVelocity jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setJointMaxVelocity", "(Ljava/lang/Double;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," JointLimitType::setJointMaxVelocity jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.JointLimitType has no method named setJointMaxVelocity with signature (Ljava/lang/Double;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,double_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," JointLimitType::setJointMaxVelocity jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }
        static jclass getNewJointLimitTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/JointLimitType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/JointLimitType" << std::endl;
            }
            return clss;
        }
        
        static jclass JointLimitTypeClass = NULL;
        static inline jclass getJointLimitTypeClass() {
            if (JointLimitTypeClass != NULL) {
                return JointLimitTypeClass;
            }
            JointLimitTypeClass = getNewJointLimitTypeClass();
            return JointLimitTypeClass;
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
                            DebugPrintJObject(__FILE__,__LINE__," GripperStatusType::setJointMaxVelocity jthis=",t);
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
    
