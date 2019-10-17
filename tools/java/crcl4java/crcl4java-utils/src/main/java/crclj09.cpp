
#include <jni.h>
#include <cstdlib>
#include <iostream>
#include "crclj.h"

namespace crclj {

    



// start_segment_index = 90
// start_segment_index = 100
// segment_index = 9
// classesSegList=[class crcl.base.PoseAndSetType, class crcl.base.StopMotionType, class crcl.base.DataThingType, class crcl.base.CRCLCommandType, class crcl.base.MiddleCommandType, class crcl.base.MessageType, class crcl.utils.CRCLCommandWrapper, interface java.util.List, class java.lang.Number, class java.lang.Double]


// class_index = 0 clss=class crcl.base.PoseAndSetType

    namespace crcl{
        namespace base{
        
        // get JNI handle for class crcl.base.PoseAndSetType
        static inline jclass getPoseAndSetTypeClass();
        
        PoseAndSetType::PoseAndSetType(jobject _jthis, bool copy): PoseType(_jthis,copy) {
                
        }
        
        PoseAndSetType::PoseAndSetType(const PoseAndSetType &objref): PoseType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class PoseAndSetType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class PoseAndSetType jthis=",jthis);
            }
        }
        
        PoseAndSetType PoseAndSetType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getPoseAndSetTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            PoseAndSetType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool PoseAndSetType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getPoseAndSetTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        PoseAndSetType::PoseAndSetType() : PoseType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getPoseAndSetTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class PoseAndSetType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," PoseAndSetType::setTolerance jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new PoseAndSetType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new PoseAndSetType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.PoseAndSetType
        PoseAndSetType::~PoseAndSetType() {
        	// Place-holder for later extensibility.
        }


        jboolean PoseAndSetType::isCoordinated() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method isCoordinated of crcl.base.PoseAndSetType with jthis == NULL." << std::endl;
                return false;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," PoseAndSetType::isCoordinated jthis=",jthis);
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "isCoordinated", "()Z");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," PoseAndSetType::isCoordinated jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.PoseAndSetType has no method named isCoordinated with signature ()Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallBooleanMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," PoseAndSetType::isCoordinated jthrowable t=",t);
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

        void PoseAndSetType::setCoordinated(jboolean boolean_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setCoordinated of crcl.base.PoseAndSetType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," PoseAndSetType::setCoordinated jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setCoordinated", "(Z)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," PoseAndSetType::setCoordinated jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.PoseAndSetType has no method named setCoordinated with signature (Z)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,boolean_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," PoseAndSetType::setCoordinated jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        TransSpeedType PoseAndSetType::getTransSpeed() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getTransSpeed of crcl.base.PoseAndSetType with jthis == NULL." << std::endl;
                static TransSpeedType nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," PoseAndSetType::getTransSpeed jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getTransSpeed", "()Lcrcl/base/TransSpeedType;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," PoseAndSetType::getTransSpeed jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.PoseAndSetType has no method named getTransSpeed with signature ()Lcrcl/base/TransSpeedType;." << std::endl;
                    static TransSpeedType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," PoseAndSetType::getTransSpeed jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            TransSpeedType retObject(retVal,false);
            return retObject;
        }

        void PoseAndSetType::setTransSpeed(const TransSpeedType & transSpeedType_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setTransSpeed of crcl.base.PoseAndSetType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," PoseAndSetType::setTransSpeed jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setTransSpeed", "(Lcrcl/base/TransSpeedType;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," PoseAndSetType::setTransSpeed jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.PoseAndSetType has no method named setTransSpeed with signature (Lcrcl/base/TransSpeedType;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,transSpeedType_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," PoseAndSetType::setTransSpeed jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        RotSpeedType PoseAndSetType::getRotSpeed() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getRotSpeed of crcl.base.PoseAndSetType with jthis == NULL." << std::endl;
                static RotSpeedType nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," PoseAndSetType::getRotSpeed jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getRotSpeed", "()Lcrcl/base/RotSpeedType;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," PoseAndSetType::getRotSpeed jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.PoseAndSetType has no method named getRotSpeed with signature ()Lcrcl/base/RotSpeedType;." << std::endl;
                    static RotSpeedType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," PoseAndSetType::getRotSpeed jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            RotSpeedType retObject(retVal,false);
            return retObject;
        }

        void PoseAndSetType::setRotSpeed(const RotSpeedType & rotSpeedType_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setRotSpeed of crcl.base.PoseAndSetType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," PoseAndSetType::setRotSpeed jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setRotSpeed", "(Lcrcl/base/RotSpeedType;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," PoseAndSetType::setRotSpeed jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.PoseAndSetType has no method named setRotSpeed with signature (Lcrcl/base/RotSpeedType;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,rotSpeedType_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," PoseAndSetType::setRotSpeed jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        TransAccelType PoseAndSetType::getTransAccel() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getTransAccel of crcl.base.PoseAndSetType with jthis == NULL." << std::endl;
                static TransAccelType nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," PoseAndSetType::getTransAccel jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getTransAccel", "()Lcrcl/base/TransAccelType;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," PoseAndSetType::getTransAccel jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.PoseAndSetType has no method named getTransAccel with signature ()Lcrcl/base/TransAccelType;." << std::endl;
                    static TransAccelType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," PoseAndSetType::getTransAccel jthrowable t=",t);
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

        void PoseAndSetType::setTransAccel(const TransAccelType & transAccelType_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setTransAccel of crcl.base.PoseAndSetType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," PoseAndSetType::setTransAccel jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setTransAccel", "(Lcrcl/base/TransAccelType;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," PoseAndSetType::setTransAccel jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.PoseAndSetType has no method named setTransAccel with signature (Lcrcl/base/TransAccelType;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,transAccelType_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," PoseAndSetType::setTransAccel jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        RotAccelType PoseAndSetType::getRotAccel() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getRotAccel of crcl.base.PoseAndSetType with jthis == NULL." << std::endl;
                static RotAccelType nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," PoseAndSetType::getRotAccel jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getRotAccel", "()Lcrcl/base/RotAccelType;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," PoseAndSetType::getRotAccel jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.PoseAndSetType has no method named getRotAccel with signature ()Lcrcl/base/RotAccelType;." << std::endl;
                    static RotAccelType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," PoseAndSetType::getRotAccel jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            RotAccelType retObject(retVal,false);
            return retObject;
        }

        void PoseAndSetType::setRotAccel(const RotAccelType & rotAccelType_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setRotAccel of crcl.base.PoseAndSetType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," PoseAndSetType::setRotAccel jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setRotAccel", "(Lcrcl/base/RotAccelType;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," PoseAndSetType::setRotAccel jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.PoseAndSetType has no method named setRotAccel with signature (Lcrcl/base/RotAccelType;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,rotAccelType_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," PoseAndSetType::setRotAccel jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        PoseToleranceType PoseAndSetType::getTolerance() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getTolerance of crcl.base.PoseAndSetType with jthis == NULL." << std::endl;
                static PoseToleranceType nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," PoseAndSetType::getTolerance jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getTolerance", "()Lcrcl/base/PoseToleranceType;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," PoseAndSetType::getTolerance jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.PoseAndSetType has no method named getTolerance with signature ()Lcrcl/base/PoseToleranceType;." << std::endl;
                    static PoseToleranceType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," PoseAndSetType::getTolerance jthrowable t=",t);
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

        void PoseAndSetType::setTolerance(const PoseToleranceType & poseToleranceType_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setTolerance of crcl.base.PoseAndSetType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," PoseAndSetType::setTolerance jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setTolerance", "(Lcrcl/base/PoseToleranceType;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," PoseAndSetType::setTolerance jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.PoseAndSetType has no method named setTolerance with signature (Lcrcl/base/PoseToleranceType;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,poseToleranceType_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," PoseAndSetType::setTolerance jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }
        static jclass getNewPoseAndSetTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/PoseAndSetType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/PoseAndSetType" << std::endl;
            }
            return clss;
        }
        
        static jclass PoseAndSetTypeClass = NULL;
        static inline jclass getPoseAndSetTypeClass() {
            if (PoseAndSetTypeClass != NULL) {
                return PoseAndSetTypeClass;
            }
            PoseAndSetTypeClass = getNewPoseAndSetTypeClass();
            return PoseAndSetTypeClass;
        }

    // class_index = 1 clss=class crcl.base.StopMotionType

        
        // get JNI handle for class crcl.base.StopMotionType
        static inline jclass getStopMotionTypeClass();
        
        StopMotionType::StopMotionType(jobject _jthis, bool copy): MiddleCommandType(_jthis,copy) {
                
        }
        
        StopMotionType::StopMotionType(const StopMotionType &objref): MiddleCommandType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class StopMotionType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class StopMotionType jthis=",jthis);
            }
        }
        
        StopMotionType StopMotionType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getStopMotionTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            StopMotionType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool StopMotionType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getStopMotionTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        StopMotionType::StopMotionType() : MiddleCommandType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getStopMotionTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class StopMotionType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," StopMotionType::setTolerance jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new StopMotionType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new StopMotionType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.StopMotionType
        StopMotionType::~StopMotionType() {
        	// Place-holder for later extensibility.
        }


        StopConditionEnumType StopMotionType::getStopCondition() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getStopCondition of crcl.base.StopMotionType with jthis == NULL." << std::endl;
                static StopConditionEnumType nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," StopMotionType::getStopCondition jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getStopCondition", "()Lcrcl/base/StopConditionEnumType;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," StopMotionType::getStopCondition jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.StopMotionType has no method named getStopCondition with signature ()Lcrcl/base/StopConditionEnumType;." << std::endl;
                    static StopConditionEnumType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," StopMotionType::getStopCondition jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
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

        void StopMotionType::setStopCondition(const StopConditionEnumType & stopConditionEnumType_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setStopCondition of crcl.base.StopMotionType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," StopMotionType::setStopCondition jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setStopCondition", "(Lcrcl/base/StopConditionEnumType;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," StopMotionType::setStopCondition jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.StopMotionType has no method named setStopCondition with signature (Lcrcl/base/StopConditionEnumType;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,stopConditionEnumType_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," StopMotionType::setStopCondition jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }
        static jclass getNewStopMotionTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/StopMotionType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/StopMotionType" << std::endl;
            }
            return clss;
        }
        
        static jclass StopMotionTypeClass = NULL;
        static inline jclass getStopMotionTypeClass() {
            if (StopMotionTypeClass != NULL) {
                return StopMotionTypeClass;
            }
            StopMotionTypeClass = getNewStopMotionTypeClass();
            return StopMotionTypeClass;
        }

    // class_index = 2 clss=class crcl.base.DataThingType

        
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
                            DebugPrintJObject(__FILE__,__LINE__," DataThingType::setStopCondition jthis=",t);
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

    // class_index = 3 clss=class crcl.base.CRCLCommandType

        
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

    // class_index = 4 clss=class crcl.base.MiddleCommandType

        
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

    // class_index = 5 clss=class crcl.base.MessageType

        
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
        } // end namespace base


    // class_index = 6 clss=class crcl.utils.CRCLCommandWrapper

        namespace utils{
        
        // get JNI handle for class crcl.utils.CRCLCommandWrapper
        static inline jclass getCRCLCommandWrapperClass();
        
        CRCLCommandWrapper::CRCLCommandWrapper(jobject _jthis, bool copy): ::crclj::crcl::base::MessageType(_jthis,copy) {
                
        }
        
        CRCLCommandWrapper::CRCLCommandWrapper(const CRCLCommandWrapper &objref): ::crclj::crcl::base::MessageType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class CRCLCommandWrapper _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class CRCLCommandWrapper jthis=",jthis);
            }
        }
        
        CRCLCommandWrapper CRCLCommandWrapper::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getCRCLCommandWrapperClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            CRCLCommandWrapper retVal(objref.jthis,true);
            return retVal;
        }
        
        bool CRCLCommandWrapper::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getCRCLCommandWrapperClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        CRCLCommandWrapper::CRCLCommandWrapper() : ::crclj::crcl::base::MessageType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getCRCLCommandWrapperClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class CRCLCommandWrapper has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::setMessage jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new CRCLCommandWrapper with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new CRCLCommandWrapper jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.utils.CRCLCommandWrapper
        CRCLCommandWrapper::~CRCLCommandWrapper() {
        	// Place-holder for later extensibility.
        }


        jstring CRCLCommandWrapper::toString() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method toString of crcl.utils.CRCLCommandWrapper with jthis == NULL." << std::endl;
                return NULL;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::toString jthis=",jthis);
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "toString", "()Ljava/lang/String;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::toString jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.utils.CRCLCommandWrapper has no method named toString with signature ()Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::toString jthrowable t=",t);
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

        jstring CRCLCommandWrapper::getName() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getName of crcl.utils.CRCLCommandWrapper with jthis == NULL." << std::endl;
                return NULL;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::getName jthis=",jthis);
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getName", "()Ljava/lang/String;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::getName jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.utils.CRCLCommandWrapper has no method named getName with signature ()Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::getName jthrowable t=",t);
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

        void CRCLCommandWrapper::setName(jstring string_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setName of crcl.utils.CRCLCommandWrapper with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::setName jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setName", "(Ljava/lang/String;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::setName jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.utils.CRCLCommandWrapper has no method named setName with signature (Ljava/lang/String;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::setName jthrowable t=",t);
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
        void CRCLCommandWrapper::setName(const char * easyArg_0) {
            // convenience method, converts to/from JNI types to common C++ types.
            if(jthis == NULL) {
                std::cerr << "Call of method setName of crcl.utils.CRCLCommandWrapper with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::setName jthis=",jthis);
            
                    
            jstring string_0 = env->NewStringUTF(easyArg_0);
            setName(string_0);
            jobjectRefType ref_0 = env->GetObjectRefType(string_0);
            if(ref_0 == JNIGlobalRefType) {
                env->DeleteGlobalRef(string_0);
            }
            
            releaseEnv(env);
        }


        CRCLCommandWrapper CRCLCommandWrapper::wrap(const ::crclj::crcl::base::MiddleCommandType & middleCommandType_0,const CRCLCommandWrapperCRCLCommandWrapperConsumer & cRCLCommandWrapperConsumer_1,const CRCLCommandWrapperCRCLCommandWrapperConsumer & cRCLCommandWrapperConsumer_2,const CRCLCommandWrapperCRCLCommandWrapperConsumer & cRCLCommandWrapperConsumer_3) {
            JNIEnv *env =getEnv();
            static jclass cls = getCRCLCommandWrapperClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "wrap", "(Lcrcl/base/MiddleCommandType;Lcrcl/utils/CRCLCommandWrapper$CRCLCommandWrapperConsumer;Lcrcl/utils/CRCLCommandWrapper$CRCLCommandWrapperConsumer;Lcrcl/utils/CRCLCommandWrapper$CRCLCommandWrapperConsumer;)Lcrcl/utils/CRCLCommandWrapper;");
                if (NULL == mid) {
                    std::cerr << "Class crcl.utils.CRCLCommandWrapper has no method named wrap with signature (Lcrcl/base/MiddleCommandType;Lcrcl/utils/CRCLCommandWrapper$CRCLCommandWrapperConsumer;Lcrcl/utils/CRCLCommandWrapper$CRCLCommandWrapperConsumer;Lcrcl/utils/CRCLCommandWrapper$CRCLCommandWrapperConsumer;)Lcrcl/utils/CRCLCommandWrapper;." << std::endl;
                    static CRCLCommandWrapper nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,middleCommandType_0.jthis,cRCLCommandWrapperConsumer_1.jthis,cRCLCommandWrapperConsumer_2.jthis,cRCLCommandWrapperConsumer_3.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::wrap jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            CRCLCommandWrapper retObject(retVal,false);
            return retObject;
        }

        jlong CRCLCommandWrapper::getCommandID() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getCommandID of crcl.utils.CRCLCommandWrapper with jthis == NULL." << std::endl;
                return (jlong) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::getCommandID jthis=",jthis);
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getCommandID", "()J");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::getCommandID jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.utils.CRCLCommandWrapper has no method named getCommandID with signature ()J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallLongMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::getCommandID jthrowable t=",t);
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

        void CRCLCommandWrapper::setCommandID(jlong long_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setCommandID of crcl.utils.CRCLCommandWrapper with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::setCommandID jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setCommandID", "(J)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::setCommandID jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.utils.CRCLCommandWrapper has no method named setCommandID with signature (J)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,long_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::setCommandID jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        jint CRCLCommandWrapper::getCurProgramIndex() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getCurProgramIndex of crcl.utils.CRCLCommandWrapper with jthis == NULL." << std::endl;
                return (jint) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::getCurProgramIndex jthis=",jthis);
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getCurProgramIndex", "()I");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::getCurProgramIndex jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.utils.CRCLCommandWrapper has no method named getCurProgramIndex with signature ()I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallIntMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::getCurProgramIndex jthrowable t=",t);
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

        void CRCLCommandWrapper::setCurProgramIndex(jint int_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setCurProgramIndex of crcl.utils.CRCLCommandWrapper with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::setCurProgramIndex jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setCurProgramIndex", "(I)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::setCurProgramIndex jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.utils.CRCLCommandWrapper has no method named setCurProgramIndex with signature (I)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,int_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::setCurProgramIndex jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        ::crclj::crcl::base::CRCLProgramType CRCLCommandWrapper::getCurProgram() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getCurProgram of crcl.utils.CRCLCommandWrapper with jthis == NULL." << std::endl;
                static ::crclj::crcl::base::CRCLProgramType nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::getCurProgram jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getCurProgram", "()Lcrcl/base/CRCLProgramType;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::getCurProgram jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.utils.CRCLCommandWrapper has no method named getCurProgram with signature ()Lcrcl/base/CRCLProgramType;." << std::endl;
                    static ::crclj::crcl::base::CRCLProgramType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::getCurProgram jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            ::crclj::crcl::base::CRCLProgramType retObject(retVal,false);
            return retObject;
        }

        void CRCLCommandWrapper::setCurProgram(const ::crclj::crcl::base::CRCLProgramType & cRCLProgramType_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setCurProgram of crcl.utils.CRCLCommandWrapper with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::setCurProgram jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setCurProgram", "(Lcrcl/base/CRCLProgramType;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::setCurProgram jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.utils.CRCLCommandWrapper has no method named setCurProgram with signature (Lcrcl/base/CRCLProgramType;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,cRCLProgramType_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::setCurProgram jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        CRCLCommandWrapper CRCLCommandWrapper::wrapWithOnDone(const ::crclj::crcl::base::MiddleCommandType & middleCommandType_0,const CRCLCommandWrapperCRCLCommandWrapperConsumer & cRCLCommandWrapperConsumer_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getCRCLCommandWrapperClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "wrapWithOnDone", "(Lcrcl/base/MiddleCommandType;Lcrcl/utils/CRCLCommandWrapper$CRCLCommandWrapperConsumer;)Lcrcl/utils/CRCLCommandWrapper;");
                if (NULL == mid) {
                    std::cerr << "Class crcl.utils.CRCLCommandWrapper has no method named wrapWithOnDone with signature (Lcrcl/base/MiddleCommandType;Lcrcl/utils/CRCLCommandWrapper$CRCLCommandWrapperConsumer;)Lcrcl/utils/CRCLCommandWrapper;." << std::endl;
                    static CRCLCommandWrapper nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,middleCommandType_0.jthis,cRCLCommandWrapperConsumer_1.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::wrapWithOnDone jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            CRCLCommandWrapper retObject(retVal,false);
            return retObject;
        }

        CRCLCommandWrapper CRCLCommandWrapper::wrapWithOnStart(const ::crclj::crcl::base::MiddleCommandType & middleCommandType_0,const CRCLCommandWrapperCRCLCommandWrapperConsumer & cRCLCommandWrapperConsumer_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getCRCLCommandWrapperClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "wrapWithOnStart", "(Lcrcl/base/MiddleCommandType;Lcrcl/utils/CRCLCommandWrapper$CRCLCommandWrapperConsumer;)Lcrcl/utils/CRCLCommandWrapper;");
                if (NULL == mid) {
                    std::cerr << "Class crcl.utils.CRCLCommandWrapper has no method named wrapWithOnStart with signature (Lcrcl/base/MiddleCommandType;Lcrcl/utils/CRCLCommandWrapper$CRCLCommandWrapperConsumer;)Lcrcl/utils/CRCLCommandWrapper;." << std::endl;
                    static CRCLCommandWrapper nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,middleCommandType_0.jthis,cRCLCommandWrapperConsumer_1.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::wrapWithOnStart jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            CRCLCommandWrapper retObject(retVal,false);
            return retObject;
        }

        CRCLCommandWrapper CRCLCommandWrapper::wrapWithOnError(const ::crclj::crcl::base::MiddleCommandType & middleCommandType_0,const CRCLCommandWrapperCRCLCommandWrapperConsumer & cRCLCommandWrapperConsumer_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getCRCLCommandWrapperClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "wrapWithOnError", "(Lcrcl/base/MiddleCommandType;Lcrcl/utils/CRCLCommandWrapper$CRCLCommandWrapperConsumer;)Lcrcl/utils/CRCLCommandWrapper;");
                if (NULL == mid) {
                    std::cerr << "Class crcl.utils.CRCLCommandWrapper has no method named wrapWithOnError with signature (Lcrcl/base/MiddleCommandType;Lcrcl/utils/CRCLCommandWrapper$CRCLCommandWrapperConsumer;)Lcrcl/utils/CRCLCommandWrapper;." << std::endl;
                    static CRCLCommandWrapper nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,middleCommandType_0.jthis,cRCLCommandWrapperConsumer_1.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::wrapWithOnError jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            CRCLCommandWrapper retObject(retVal,false);
            return retObject;
        }

        ::crclj::crcl::base::MiddleCommandType CRCLCommandWrapper::getWrappedCommand() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getWrappedCommand of crcl.utils.CRCLCommandWrapper with jthis == NULL." << std::endl;
                static ::crclj::crcl::base::MiddleCommandType nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::getWrappedCommand jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getWrappedCommand", "()Lcrcl/base/MiddleCommandType;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::getWrappedCommand jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.utils.CRCLCommandWrapper has no method named getWrappedCommand with signature ()Lcrcl/base/MiddleCommandType;." << std::endl;
                    static ::crclj::crcl::base::MiddleCommandType nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::getWrappedCommand jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            ::crclj::crcl::base::MiddleCommandType retObject(retVal,false);
            return retObject;
        }

        void CRCLCommandWrapper::setWrappedCommand(const ::crclj::crcl::base::MiddleCommandType & middleCommandType_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setWrappedCommand of crcl.utils.CRCLCommandWrapper with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::setWrappedCommand jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setWrappedCommand", "(Lcrcl/base/MiddleCommandType;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::setWrappedCommand jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.utils.CRCLCommandWrapper has no method named setWrappedCommand with signature (Lcrcl/base/MiddleCommandType;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,middleCommandType_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::setWrappedCommand jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        void CRCLCommandWrapper::addOnStartListener(const CRCLCommandWrapperCRCLCommandWrapperConsumer & cRCLCommandWrapperConsumer_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method addOnStartListener of crcl.utils.CRCLCommandWrapper with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::addOnStartListener jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "addOnStartListener", "(Lcrcl/utils/CRCLCommandWrapper$CRCLCommandWrapperConsumer;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::addOnStartListener jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.utils.CRCLCommandWrapper has no method named addOnStartListener with signature (Lcrcl/utils/CRCLCommandWrapper$CRCLCommandWrapperConsumer;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,cRCLCommandWrapperConsumer_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::addOnStartListener jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        void CRCLCommandWrapper::removeOnStartListener(const CRCLCommandWrapperCRCLCommandWrapperConsumer & cRCLCommandWrapperConsumer_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method removeOnStartListener of crcl.utils.CRCLCommandWrapper with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::removeOnStartListener jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "removeOnStartListener", "(Lcrcl/utils/CRCLCommandWrapper$CRCLCommandWrapperConsumer;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::removeOnStartListener jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.utils.CRCLCommandWrapper has no method named removeOnStartListener with signature (Lcrcl/utils/CRCLCommandWrapper$CRCLCommandWrapperConsumer;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,cRCLCommandWrapperConsumer_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::removeOnStartListener jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        void CRCLCommandWrapper::notifyOnStartListeners() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method notifyOnStartListeners of crcl.utils.CRCLCommandWrapper with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::notifyOnStartListeners jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "notifyOnStartListeners", "()V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::notifyOnStartListeners jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.utils.CRCLCommandWrapper has no method named notifyOnStartListeners with signature ()V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::notifyOnStartListeners jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        void CRCLCommandWrapper::addOnDoneListener(const CRCLCommandWrapperCRCLCommandWrapperConsumer & cRCLCommandWrapperConsumer_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method addOnDoneListener of crcl.utils.CRCLCommandWrapper with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::addOnDoneListener jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "addOnDoneListener", "(Lcrcl/utils/CRCLCommandWrapper$CRCLCommandWrapperConsumer;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::addOnDoneListener jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.utils.CRCLCommandWrapper has no method named addOnDoneListener with signature (Lcrcl/utils/CRCLCommandWrapper$CRCLCommandWrapperConsumer;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,cRCLCommandWrapperConsumer_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::addOnDoneListener jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        void CRCLCommandWrapper::removeOnDoneListener(const CRCLCommandWrapperCRCLCommandWrapperConsumer & cRCLCommandWrapperConsumer_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method removeOnDoneListener of crcl.utils.CRCLCommandWrapper with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::removeOnDoneListener jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "removeOnDoneListener", "(Lcrcl/utils/CRCLCommandWrapper$CRCLCommandWrapperConsumer;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::removeOnDoneListener jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.utils.CRCLCommandWrapper has no method named removeOnDoneListener with signature (Lcrcl/utils/CRCLCommandWrapper$CRCLCommandWrapperConsumer;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,cRCLCommandWrapperConsumer_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::removeOnDoneListener jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        void CRCLCommandWrapper::notifyOnDoneListeners() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method notifyOnDoneListeners of crcl.utils.CRCLCommandWrapper with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::notifyOnDoneListeners jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "notifyOnDoneListeners", "()V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::notifyOnDoneListeners jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.utils.CRCLCommandWrapper has no method named notifyOnDoneListeners with signature ()V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::notifyOnDoneListeners jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        void CRCLCommandWrapper::addOnErrorListener(const CRCLCommandWrapperCRCLCommandWrapperConsumer & cRCLCommandWrapperConsumer_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method addOnErrorListener of crcl.utils.CRCLCommandWrapper with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::addOnErrorListener jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "addOnErrorListener", "(Lcrcl/utils/CRCLCommandWrapper$CRCLCommandWrapperConsumer;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::addOnErrorListener jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.utils.CRCLCommandWrapper has no method named addOnErrorListener with signature (Lcrcl/utils/CRCLCommandWrapper$CRCLCommandWrapperConsumer;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,cRCLCommandWrapperConsumer_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::addOnErrorListener jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        void CRCLCommandWrapper::removeOnErrorListener(const CRCLCommandWrapperCRCLCommandWrapperConsumer & cRCLCommandWrapperConsumer_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method removeOnErrorListener of crcl.utils.CRCLCommandWrapper with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::removeOnErrorListener jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "removeOnErrorListener", "(Lcrcl/utils/CRCLCommandWrapper$CRCLCommandWrapperConsumer;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::removeOnErrorListener jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.utils.CRCLCommandWrapper has no method named removeOnErrorListener with signature (Lcrcl/utils/CRCLCommandWrapper$CRCLCommandWrapperConsumer;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,cRCLCommandWrapperConsumer_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::removeOnErrorListener jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        void CRCLCommandWrapper::notifyOnErrorListeners() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method notifyOnErrorListeners of crcl.utils.CRCLCommandWrapper with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::notifyOnErrorListeners jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "notifyOnErrorListeners", "()V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::notifyOnErrorListeners jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.utils.CRCLCommandWrapper has no method named notifyOnErrorListeners with signature ()V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," CRCLCommandWrapper::notifyOnErrorListeners jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }
        static jclass getNewCRCLCommandWrapperClass() {
            jclass clss = getEnv()->FindClass("crcl/utils/CRCLCommandWrapper");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/utils/CRCLCommandWrapper" << std::endl;
            }
            return clss;
        }
        
        static jclass CRCLCommandWrapperClass = NULL;
        static inline jclass getCRCLCommandWrapperClass() {
            if (CRCLCommandWrapperClass != NULL) {
                return CRCLCommandWrapperClass;
            }
            CRCLCommandWrapperClass = getNewCRCLCommandWrapperClass();
            return CRCLCommandWrapperClass;
        }
        } // end namespace utils
    } // end namespace crcl


    // class_index = 7 clss=interface java.util.List

    namespace java{
        namespace util{
        
        // get JNI handle for class java.util.List
        static inline jclass getListClass();
        
        List::List(jobject _jthis, bool copy): ::crclj::java::lang::Object(_jthis,copy) {
                
        }
        
        List::List(const List &objref): ::crclj::java::lang::Object((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class List _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class List jthis=",jthis);
            }
        }
        
        List List::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getListClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            List retVal(objref.jthis,true);
            return retVal;
        }
        
        bool List::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getListClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }

        // Destructor for java.util.List
        List::~List() {
        	// Place-holder for later extensibility.
        }


        void List::add(jint int_0,const ::crclj::java::lang::Object & object_1) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method add of java.util.List with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," List::add jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "add", "(ILjava/lang/Object;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," List::add jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.util.List has no method named add with signature (ILjava/lang/Object;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,int_0,object_1.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," List::add jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        jboolean List::add(const ::crclj::java::lang::Object & object_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method add of java.util.List with jthis == NULL." << std::endl;
                return false;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," List::add jthis=",jthis);
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "add", "(Ljava/lang/Object;)Z");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," List::add jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.util.List has no method named add with signature (Ljava/lang/Object;)Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallBooleanMethod(jthis, mid ,object_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," List::add jthrowable t=",t);
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

        jboolean List::remove(const ::crclj::java::lang::Object & object_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method remove of java.util.List with jthis == NULL." << std::endl;
                return false;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," List::remove jthis=",jthis);
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "remove", "(Ljava/lang/Object;)Z");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," List::remove jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.util.List has no method named remove with signature (Ljava/lang/Object;)Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallBooleanMethod(jthis, mid ,object_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," List::remove jthrowable t=",t);
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

        ::crclj::java::lang::Object List::remove(jint int_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method remove of java.util.List with jthis == NULL." << std::endl;
                static ::crclj::java::lang::Object nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," List::remove jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "remove", "(I)Ljava/lang/Object;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," List::remove jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.util.List has no method named remove with signature (I)Ljava/lang/Object;." << std::endl;
                    static ::crclj::java::lang::Object nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid ,int_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," List::remove jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            ::crclj::java::lang::Object retObject(retVal,false);
            return retObject;
        }

        ::crclj::java::lang::Object List::get(jint int_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method get of java.util.List with jthis == NULL." << std::endl;
                static ::crclj::java::lang::Object nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," List::get jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "get", "(I)Ljava/lang/Object;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," List::get jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.util.List has no method named get with signature (I)Ljava/lang/Object;." << std::endl;
                    static ::crclj::java::lang::Object nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid ,int_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," List::get jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            ::crclj::java::lang::Object retObject(retVal,false);
            return retObject;
        }

        jboolean List::equals(const ::crclj::java::lang::Object & object_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method equals of java.util.List with jthis == NULL." << std::endl;
                return false;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," List::equals jthis=",jthis);
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "equals", "(Ljava/lang/Object;)Z");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," List::equals jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.util.List has no method named equals with signature (Ljava/lang/Object;)Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallBooleanMethod(jthis, mid ,object_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," List::equals jthrowable t=",t);
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

        jint List::hashCode() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method hashCode of java.util.List with jthis == NULL." << std::endl;
                return (jint) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," List::hashCode jthis=",jthis);
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "hashCode", "()I");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," List::hashCode jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.util.List has no method named hashCode with signature ()I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallIntMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," List::hashCode jthrowable t=",t);
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

        jint List::indexOf(const ::crclj::java::lang::Object & object_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method indexOf of java.util.List with jthis == NULL." << std::endl;
                return (jint) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," List::indexOf jthis=",jthis);
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "indexOf", "(Ljava/lang/Object;)I");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," List::indexOf jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.util.List has no method named indexOf with signature (Ljava/lang/Object;)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallIntMethod(jthis, mid ,object_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," List::indexOf jthrowable t=",t);
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

        void List::clear() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method clear of java.util.List with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," List::clear jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "clear", "()V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," List::clear jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.util.List has no method named clear with signature ()V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," List::clear jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        jboolean List::isEmpty() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method isEmpty of java.util.List with jthis == NULL." << std::endl;
                return false;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," List::isEmpty jthis=",jthis);
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "isEmpty", "()Z");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," List::isEmpty jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.util.List has no method named isEmpty with signature ()Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallBooleanMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," List::isEmpty jthrowable t=",t);
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

        jint List::lastIndexOf(const ::crclj::java::lang::Object & object_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method lastIndexOf of java.util.List with jthis == NULL." << std::endl;
                return (jint) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," List::lastIndexOf jthis=",jthis);
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "lastIndexOf", "(Ljava/lang/Object;)I");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," List::lastIndexOf jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.util.List has no method named lastIndexOf with signature (Ljava/lang/Object;)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallIntMethod(jthis, mid ,object_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," List::lastIndexOf jthrowable t=",t);
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

        jboolean List::contains(const ::crclj::java::lang::Object & object_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method contains of java.util.List with jthis == NULL." << std::endl;
                return false;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," List::contains jthis=",jthis);
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "contains", "(Ljava/lang/Object;)Z");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," List::contains jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.util.List has no method named contains with signature (Ljava/lang/Object;)Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallBooleanMethod(jthis, mid ,object_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," List::contains jthrowable t=",t);
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

        jint List::size() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method size of java.util.List with jthis == NULL." << std::endl;
                return (jint) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," List::size jthis=",jthis);
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "size", "()I");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," List::size jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.util.List has no method named size with signature ()I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallIntMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," List::size jthrowable t=",t);
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

        List List::subList(jint int_0,jint int_1) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method subList of java.util.List with jthis == NULL." << std::endl;
                static List nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," List::subList jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "subList", "(II)Ljava/util/List;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," List::subList jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.util.List has no method named subList with signature (II)Ljava/util/List;." << std::endl;
                    static List nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid ,int_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," List::subList jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            List retObject(retVal,false);
            return retObject;
        }

        jobjectArray List::toArray() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method toArray of java.util.List with jthis == NULL." << std::endl;
                return NULL;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," List::toArray jthis=",jthis);
            jobjectArray retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "toArray", "()[Ljava/lang/Object;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," List::toArray jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.util.List has no method named toArray with signature ()[Ljava/lang/Object;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jobjectArray)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," List::toArray jthrowable t=",t);
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

        jobjectArray List::toArray(jobjectArray objectArray_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method toArray of java.util.List with jthis == NULL." << std::endl;
                return NULL;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," List::toArray jthis=",jthis);
            jobjectArray retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "toArray", "([Ljava/lang/Object;)[Ljava/lang/Object;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," List::toArray jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.util.List has no method named toArray with signature ([Ljava/lang/Object;)[Ljava/lang/Object;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jobjectArray)  env->CallObjectMethod(jthis, mid ,objectArray_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," List::toArray jthrowable t=",t);
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

        List List::of(const ::crclj::java::lang::Object & object_0,const ::crclj::java::lang::Object & object_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getListClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "of", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/List;");
                if (NULL == mid) {
                    std::cerr << "Class java.util.List has no method named of with signature (Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/List;." << std::endl;
                    static List nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,object_0.jthis,object_1.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," List::of jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            List retObject(retVal,false);
            return retObject;
        }

        List List::of(const ::crclj::java::lang::Object & object_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getListClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "of", "(Ljava/lang/Object;)Ljava/util/List;");
                if (NULL == mid) {
                    std::cerr << "Class java.util.List has no method named of with signature (Ljava/lang/Object;)Ljava/util/List;." << std::endl;
                    static List nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,object_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," List::of jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            List retObject(retVal,false);
            return retObject;
        }

        List List::of() {
            JNIEnv *env =getEnv();
            static jclass cls = getListClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "of", "()Ljava/util/List;");
                if (NULL == mid) {
                    std::cerr << "Class java.util.List has no method named of with signature ()Ljava/util/List;." << std::endl;
                    static List nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," List::of jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            List retObject(retVal,false);
            return retObject;
        }

        List List::of(const ::crclj::java::lang::Object & object_0,const ::crclj::java::lang::Object & object_1,const ::crclj::java::lang::Object & object_2,const ::crclj::java::lang::Object & object_3,const ::crclj::java::lang::Object & object_4,const ::crclj::java::lang::Object & object_5) {
            JNIEnv *env =getEnv();
            static jclass cls = getListClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "of", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/List;");
                if (NULL == mid) {
                    std::cerr << "Class java.util.List has no method named of with signature (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/List;." << std::endl;
                    static List nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,object_0.jthis,object_1.jthis,object_2.jthis,object_3.jthis,object_4.jthis,object_5.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," List::of jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            List retObject(retVal,false);
            return retObject;
        }

        List List::of(const ::crclj::java::lang::Object & object_0,const ::crclj::java::lang::Object & object_1,const ::crclj::java::lang::Object & object_2) {
            JNIEnv *env =getEnv();
            static jclass cls = getListClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "of", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/List;");
                if (NULL == mid) {
                    std::cerr << "Class java.util.List has no method named of with signature (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/List;." << std::endl;
                    static List nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,object_0.jthis,object_1.jthis,object_2.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," List::of jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            List retObject(retVal,false);
            return retObject;
        }

        List List::of(const ::crclj::java::lang::Object & object_0,const ::crclj::java::lang::Object & object_1,const ::crclj::java::lang::Object & object_2,const ::crclj::java::lang::Object & object_3) {
            JNIEnv *env =getEnv();
            static jclass cls = getListClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "of", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/List;");
                if (NULL == mid) {
                    std::cerr << "Class java.util.List has no method named of with signature (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/List;." << std::endl;
                    static List nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,object_0.jthis,object_1.jthis,object_2.jthis,object_3.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," List::of jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            List retObject(retVal,false);
            return retObject;
        }

        List List::of(const ::crclj::java::lang::Object & object_0,const ::crclj::java::lang::Object & object_1,const ::crclj::java::lang::Object & object_2,const ::crclj::java::lang::Object & object_3,const ::crclj::java::lang::Object & object_4) {
            JNIEnv *env =getEnv();
            static jclass cls = getListClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "of", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/List;");
                if (NULL == mid) {
                    std::cerr << "Class java.util.List has no method named of with signature (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/List;." << std::endl;
                    static List nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,object_0.jthis,object_1.jthis,object_2.jthis,object_3.jthis,object_4.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," List::of jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            List retObject(retVal,false);
            return retObject;
        }

        List List::of(const ::crclj::java::lang::Object & object_0,const ::crclj::java::lang::Object & object_1,const ::crclj::java::lang::Object & object_2,const ::crclj::java::lang::Object & object_3,const ::crclj::java::lang::Object & object_4,const ::crclj::java::lang::Object & object_5,const ::crclj::java::lang::Object & object_6,const ::crclj::java::lang::Object & object_7,const ::crclj::java::lang::Object & object_8,const ::crclj::java::lang::Object & object_9) {
            JNIEnv *env =getEnv();
            static jclass cls = getListClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "of", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/List;");
                if (NULL == mid) {
                    std::cerr << "Class java.util.List has no method named of with signature (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/List;." << std::endl;
                    static List nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,object_0.jthis,object_1.jthis,object_2.jthis,object_3.jthis,object_4.jthis,object_5.jthis,object_6.jthis,object_7.jthis,object_8.jthis,object_9.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," List::of jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            List retObject(retVal,false);
            return retObject;
        }

        List List::of(jobjectArray objectArray_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getListClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "of", "([Ljava/lang/Object;)Ljava/util/List;");
                if (NULL == mid) {
                    std::cerr << "Class java.util.List has no method named of with signature ([Ljava/lang/Object;)Ljava/util/List;." << std::endl;
                    static List nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,objectArray_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," List::of jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            List retObject(retVal,false);
            return retObject;
        }

        List List::of(const ::crclj::java::lang::Object & object_0,const ::crclj::java::lang::Object & object_1,const ::crclj::java::lang::Object & object_2,const ::crclj::java::lang::Object & object_3,const ::crclj::java::lang::Object & object_4,const ::crclj::java::lang::Object & object_5,const ::crclj::java::lang::Object & object_6,const ::crclj::java::lang::Object & object_7,const ::crclj::java::lang::Object & object_8) {
            JNIEnv *env =getEnv();
            static jclass cls = getListClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "of", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/List;");
                if (NULL == mid) {
                    std::cerr << "Class java.util.List has no method named of with signature (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/List;." << std::endl;
                    static List nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,object_0.jthis,object_1.jthis,object_2.jthis,object_3.jthis,object_4.jthis,object_5.jthis,object_6.jthis,object_7.jthis,object_8.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," List::of jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            List retObject(retVal,false);
            return retObject;
        }

        List List::of(const ::crclj::java::lang::Object & object_0,const ::crclj::java::lang::Object & object_1,const ::crclj::java::lang::Object & object_2,const ::crclj::java::lang::Object & object_3,const ::crclj::java::lang::Object & object_4,const ::crclj::java::lang::Object & object_5,const ::crclj::java::lang::Object & object_6) {
            JNIEnv *env =getEnv();
            static jclass cls = getListClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "of", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/List;");
                if (NULL == mid) {
                    std::cerr << "Class java.util.List has no method named of with signature (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/List;." << std::endl;
                    static List nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,object_0.jthis,object_1.jthis,object_2.jthis,object_3.jthis,object_4.jthis,object_5.jthis,object_6.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," List::of jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            List retObject(retVal,false);
            return retObject;
        }

        List List::of(const ::crclj::java::lang::Object & object_0,const ::crclj::java::lang::Object & object_1,const ::crclj::java::lang::Object & object_2,const ::crclj::java::lang::Object & object_3,const ::crclj::java::lang::Object & object_4,const ::crclj::java::lang::Object & object_5,const ::crclj::java::lang::Object & object_6,const ::crclj::java::lang::Object & object_7) {
            JNIEnv *env =getEnv();
            static jclass cls = getListClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "of", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/List;");
                if (NULL == mid) {
                    std::cerr << "Class java.util.List has no method named of with signature (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/List;." << std::endl;
                    static List nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,object_0.jthis,object_1.jthis,object_2.jthis,object_3.jthis,object_4.jthis,object_5.jthis,object_6.jthis,object_7.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," List::of jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            List retObject(retVal,false);
            return retObject;
        }

        ::crclj::java::lang::Object List::set(jint int_0,const ::crclj::java::lang::Object & object_1) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method set of java.util.List with jthis == NULL." << std::endl;
                static ::crclj::java::lang::Object nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," List::set jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "set", "(ILjava/lang/Object;)Ljava/lang/Object;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," List::set jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.util.List has no method named set with signature (ILjava/lang/Object;)Ljava/lang/Object;." << std::endl;
                    static ::crclj::java::lang::Object nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid ,int_0,object_1.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," List::set jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            ::crclj::java::lang::Object retObject(retVal,false);
            return retObject;
        }
        static jclass getNewListClass() {
            jclass clss = getEnv()->FindClass("java/util/List");
            if (NULL == clss) {
                std::cerr << " Can't find class java/util/List" << std::endl;
            }
            return clss;
        }
        
        static jclass ListClass = NULL;
        static inline jclass getListClass() {
            if (ListClass != NULL) {
                return ListClass;
            }
            ListClass = getNewListClass();
            return ListClass;
        }
        } // end namespace util


    // class_index = 8 clss=class java.lang.Number

        namespace lang{
        
        // get JNI handle for class java.lang.Number
        static inline jclass getNumberClass();
        
        Number::Number(jobject _jthis, bool copy): Object(_jthis,copy) {
                
        }
        
        Number::Number(const Number &objref): Object((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class Number _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class Number jthis=",jthis);
            }
        }
        
        Number Number::cast(const Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getNumberClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            Number retVal(objref.jthis,true);
            return retVal;
        }
        
        bool Number::instanceof(const Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getNumberClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        Number::Number() : Object((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getNumberClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class Number has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Number::set jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new Number with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new Number jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for java.lang.Number
        Number::~Number() {
        	// Place-holder for later extensibility.
        }


        jbyte Number::byteValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method byteValue of java.lang.Number with jthis == NULL." << std::endl;
                return (jbyte) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Number::byteValue jthis=",jthis);
            jbyte retVal= (jbyte) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "byteValue", "()B");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Number::byteValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Number has no method named byteValue with signature ()B." << std::endl;
                    return (jbyte) -1;
                } else {
                    retVal= (jbyte)  env->CallByteMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Number::byteValue jthrowable t=",t);
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

        jshort Number::shortValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method shortValue of java.lang.Number with jthis == NULL." << std::endl;
                return (jshort) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Number::shortValue jthis=",jthis);
            jshort retVal=(jshort) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "shortValue", "()S");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Number::shortValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Number has no method named shortValue with signature ()S." << std::endl;
                    return (jshort) -1;
                } else {
                    retVal= (jshort)  env->CallShortMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Number::shortValue jthrowable t=",t);
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

        jint Number::intValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method intValue of java.lang.Number with jthis == NULL." << std::endl;
                return (jint) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Number::intValue jthis=",jthis);
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "intValue", "()I");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Number::intValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Number has no method named intValue with signature ()I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallIntMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Number::intValue jthrowable t=",t);
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

        jlong Number::longValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method longValue of java.lang.Number with jthis == NULL." << std::endl;
                return (jlong) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Number::longValue jthis=",jthis);
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "longValue", "()J");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Number::longValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Number has no method named longValue with signature ()J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallLongMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Number::longValue jthrowable t=",t);
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

        jfloat Number::floatValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method floatValue of java.lang.Number with jthis == NULL." << std::endl;
                return (jfloat) -1.0;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Number::floatValue jthis=",jthis);
            jfloat retVal= (jfloat) -1.0;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "floatValue", "()F");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Number::floatValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Number has no method named floatValue with signature ()F." << std::endl;
                    return (jfloat) -1.0;
                } else {
                    retVal= (jfloat)  env->CallFloatMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Number::floatValue jthrowable t=",t);
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

        jdouble Number::doubleValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method doubleValue of java.lang.Number with jthis == NULL." << std::endl;
                return (jdouble) -1.0;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Number::doubleValue jthis=",jthis);
            jdouble retVal= (jdouble) -1.0;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "doubleValue", "()D");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Number::doubleValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Number has no method named doubleValue with signature ()D." << std::endl;
                    return (jdouble) -1.0;
                } else {
                    retVal= (jdouble)  env->CallDoubleMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Number::doubleValue jthrowable t=",t);
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
        static jclass getNewNumberClass() {
            jclass clss = getEnv()->FindClass("java/lang/Number");
            if (NULL == clss) {
                std::cerr << " Can't find class java/lang/Number" << std::endl;
            }
            return clss;
        }
        
        static jclass NumberClass = NULL;
        static inline jclass getNumberClass() {
            if (NumberClass != NULL) {
                return NumberClass;
            }
            NumberClass = getNewNumberClass();
            return NumberClass;
        }

    // class_index = 9 clss=class java.lang.Double

        
        // get JNI handle for class java.lang.Double
        static inline jclass getDoubleClass();
        
        Double::Double(jobject _jthis, bool copy): Number(_jthis,copy) {
                
        }
        
        Double::Double(const Double &objref): Number((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class Double _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class Double jthis=",jthis);
            }
        }
        
        Double Double::cast(const Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getDoubleClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            Double retVal(objref.jthis,true);
            return retVal;
        }
        
        bool Double::instanceof(const Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getDoubleClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        Double::Double() : Number((jobject)NULL,false) {
        JNIEnv *env =getEnv();
        static jclass cls = getDoubleClass();
        if (cls != NULL) {
            static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
            if (NULL == mid) {
                std::cerr << "Class Double has no method constructor signature ()V" << std::endl;
            } else {
                jthis = env->NewObject(cls, mid );
                jthrowable t = env->ExceptionOccurred();
                if(t != NULL) {
                    if(GetDebugJ4Cpp()) {
                        DebugPrintJObject(__FILE__,__LINE__," Double::doubleValue jthis=",t);
                        env->ExceptionDescribe();
                    }
                    throw t;
                }
                if(jthis == NULL) {
                    std::cerr << "Call to create new Double with signature ()V returned null." << std::endl;
                    releaseEnv(env);
                    return;
                }
                jobjectRefType ref = env->GetObjectRefType(jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new Double jthis=",jthis);
                if(ref != JNIGlobalRefType) {
                    jthis = env->NewGlobalRef(jthis);
                }
            }
        }
        releaseEnv(env);
        }

        Double::Double(jdouble double_0) : Number((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getDoubleClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "(D)V");
                if (NULL == mid) {
                    std::cerr << "Class Double has no method constructor signature (D)V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid ,double_0);
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Double::doubleValue jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new Double with signature (D)V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new Double jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }

        Double::Double(jstring string_0) : Number((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getDoubleClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "(Ljava/lang/String;)V");
                if (NULL == mid) {
                    std::cerr << "Class Double has no method constructor signature (Ljava/lang/String;)V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid ,string_0);
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Double::doubleValue jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new Double with signature (Ljava/lang/String;)V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new Double jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }

        Double::Double(const char * easyArg_0) : Number((jobject)NULL,false) {
        // Convenience Constructor converts common C++ types to JNI types
        JNIEnv *env =getEnv();
        static jclass cls = getDoubleClass();
        jstring string_0 = env->NewStringUTF(easyArg_0);
        
        if (cls != NULL) {
            static jmethodID mid = env->GetMethodID(cls, "<init>", "(Ljava/lang/String;)V");
            if (NULL == mid) {
                std::cerr << "Class Double has no method constructor signature (Ljava/lang/String;)V" << std::endl;
            } else {
                jthis = env->NewObject(cls, mid ,string_0);
                jthrowable t = env->ExceptionOccurred();
                if(t != NULL) {
                    if(GetDebugJ4Cpp()) {
                        DebugPrintJObject(__FILE__,__LINE__," Double::doubleValue jthis=",t);
                        env->ExceptionDescribe();
                    }
                    throw t;
                }
                if(jthis == NULL) {
                    std::cerr << "Call to create new Double with signature (Ljava/lang/String;)V returned null." << std::endl;
                    releaseEnv(env);
                    return;
                }
                jobjectRefType ref = env->GetObjectRefType(jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new Double jthis=",jthis);
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

        // Destructor for java.lang.Double
        Double::~Double() {
        	// Place-holder for later extensibility.
        }


        // Field getter for POSITIVE_INFINITY
        jdouble Double::getPOSITIVE_INFINITY() {
        JNIEnv *env =getEnv();
        static jclass cls = getDoubleClass();
        jdouble retVal= (jdouble) -1.0;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "POSITIVE_INFINITY", "D");
            if (NULL == fid) {
                std::cerr << "Class java.lang.Double has no field named POSITIVE_INFINITY with signature D." << std::endl;
                return (jdouble) -1.0;
            } else {
                retVal= (jdouble)  env->GetStaticDoubleField( cls, fid );
            }
        }
        releaseEnv(env);
        return retVal;
        }

        // Field getter for NEGATIVE_INFINITY
        jdouble Double::getNEGATIVE_INFINITY() {
        JNIEnv *env =getEnv();
        static jclass cls = getDoubleClass();
        jdouble retVal= (jdouble) -1.0;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "NEGATIVE_INFINITY", "D");
            if (NULL == fid) {
                std::cerr << "Class java.lang.Double has no field named NEGATIVE_INFINITY with signature D." << std::endl;
                return (jdouble) -1.0;
            } else {
                retVal= (jdouble)  env->GetStaticDoubleField( cls, fid );
            }
        }
        releaseEnv(env);
        return retVal;
        }

        // Field getter for NaN
        jdouble Double::getNaN() {
        JNIEnv *env =getEnv();
        static jclass cls = getDoubleClass();
        jdouble retVal= (jdouble) -1.0;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "NaN", "D");
            if (NULL == fid) {
                std::cerr << "Class java.lang.Double has no field named NaN with signature D." << std::endl;
                return (jdouble) -1.0;
            } else {
                retVal= (jdouble)  env->GetStaticDoubleField( cls, fid );
            }
        }
        releaseEnv(env);
        return retVal;
        }

        // Field getter for MAX_VALUE
        jdouble Double::getMAX_VALUE() {
        JNIEnv *env =getEnv();
        static jclass cls = getDoubleClass();
        jdouble retVal= (jdouble) -1.0;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "MAX_VALUE", "D");
            if (NULL == fid) {
                std::cerr << "Class java.lang.Double has no field named MAX_VALUE with signature D." << std::endl;
                return (jdouble) -1.0;
            } else {
                retVal= (jdouble)  env->GetStaticDoubleField( cls, fid );
            }
        }
        releaseEnv(env);
        return retVal;
        }

        // Field getter for MIN_NORMAL
        jdouble Double::getMIN_NORMAL() {
        JNIEnv *env =getEnv();
        static jclass cls = getDoubleClass();
        jdouble retVal= (jdouble) -1.0;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "MIN_NORMAL", "D");
            if (NULL == fid) {
                std::cerr << "Class java.lang.Double has no field named MIN_NORMAL with signature D." << std::endl;
                return (jdouble) -1.0;
            } else {
                retVal= (jdouble)  env->GetStaticDoubleField( cls, fid );
            }
        }
        releaseEnv(env);
        return retVal;
        }

        // Field getter for MIN_VALUE
        jdouble Double::getMIN_VALUE() {
        JNIEnv *env =getEnv();
        static jclass cls = getDoubleClass();
        jdouble retVal= (jdouble) -1.0;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "MIN_VALUE", "D");
            if (NULL == fid) {
                std::cerr << "Class java.lang.Double has no field named MIN_VALUE with signature D." << std::endl;
                return (jdouble) -1.0;
            } else {
                retVal= (jdouble)  env->GetStaticDoubleField( cls, fid );
            }
        }
        releaseEnv(env);
        return retVal;
        }

        // Field getter for MAX_EXPONENT
        jint Double::getMAX_EXPONENT() {
        JNIEnv *env =getEnv();
        static jclass cls = getDoubleClass();
        jint retVal= (jint) -1;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "MAX_EXPONENT", "I");
            if (NULL == fid) {
                std::cerr << "Class java.lang.Double has no field named MAX_EXPONENT with signature I." << std::endl;
                return (jint) -1;
            } else {
                retVal= (jint)  env->GetStaticIntField( cls, fid );
            }
        }
        releaseEnv(env);
        return retVal;
        }

        // Field getter for MIN_EXPONENT
        jint Double::getMIN_EXPONENT() {
        JNIEnv *env =getEnv();
        static jclass cls = getDoubleClass();
        jint retVal= (jint) -1;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "MIN_EXPONENT", "I");
            if (NULL == fid) {
                std::cerr << "Class java.lang.Double has no field named MIN_EXPONENT with signature I." << std::endl;
                return (jint) -1;
            } else {
                retVal= (jint)  env->GetStaticIntField( cls, fid );
            }
        }
        releaseEnv(env);
        return retVal;
        }

        // Field getter for SIZE
        jint Double::getSIZE() {
        JNIEnv *env =getEnv();
        static jclass cls = getDoubleClass();
        jint retVal= (jint) -1;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "SIZE", "I");
            if (NULL == fid) {
                std::cerr << "Class java.lang.Double has no field named SIZE with signature I." << std::endl;
                return (jint) -1;
            } else {
                retVal= (jint)  env->GetStaticIntField( cls, fid );
            }
        }
        releaseEnv(env);
        return retVal;
        }

        // Field getter for BYTES
        jint Double::getBYTES() {
        JNIEnv *env =getEnv();
        static jclass cls = getDoubleClass();
        jint retVal= (jint) -1;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "BYTES", "I");
            if (NULL == fid) {
                std::cerr << "Class java.lang.Double has no field named BYTES with signature I." << std::endl;
                return (jint) -1;
            } else {
                retVal= (jint)  env->GetStaticIntField( cls, fid );
            }
        }
        releaseEnv(env);
        return retVal;
        }

        jboolean Double::equals(const Object & object_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method equals of java.lang.Double with jthis == NULL." << std::endl;
                return false;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Double::equals jthis=",jthis);
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "equals", "(Ljava/lang/Object;)Z");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Double::equals jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Double has no method named equals with signature (Ljava/lang/Object;)Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallBooleanMethod(jthis, mid ,object_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Double::equals jthrowable t=",t);
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

        jstring Double::toString(jdouble double_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getDoubleClass();
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "toString", "(D)Ljava/lang/String;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Double has no method named toString with signature (D)Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallStaticObjectMethod( cls, mid ,double_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Double::toString jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jstring Double::toString() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method toString of java.lang.Double with jthis == NULL." << std::endl;
                return NULL;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Double::toString jthis=",jthis);
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "toString", "()Ljava/lang/String;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Double::toString jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Double has no method named toString with signature ()Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Double::toString jthrowable t=",t);
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

        jint Double::hashCode() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method hashCode of java.lang.Double with jthis == NULL." << std::endl;
                return (jint) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Double::hashCode jthis=",jthis);
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "hashCode", "()I");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Double::hashCode jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Double has no method named hashCode with signature ()I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallIntMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Double::hashCode jthrowable t=",t);
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

        jint Double::hashCode(jdouble double_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getDoubleClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "hashCode", "(D)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Double has no method named hashCode with signature (D)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,double_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Double::hashCode jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jdouble Double::min(jdouble double_0,jdouble double_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getDoubleClass();
            jdouble retVal= (jdouble) -1.0;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "min", "(DD)D");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Double has no method named min with signature (DD)D." << std::endl;
                    return (jdouble) -1.0;
                } else {
                    retVal= (jdouble)  env->CallStaticDoubleMethod( cls, mid ,double_0,double_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Double::min jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jdouble Double::max(jdouble double_0,jdouble double_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getDoubleClass();
            jdouble retVal= (jdouble) -1.0;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "max", "(DD)D");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Double has no method named max with signature (DD)D." << std::endl;
                    return (jdouble) -1.0;
                } else {
                    retVal= (jdouble)  env->CallStaticDoubleMethod( cls, mid ,double_0,double_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Double::max jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jlong Double::doubleToRawLongBits(jdouble double_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getDoubleClass();
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "doubleToRawLongBits", "(D)J");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Double has no method named doubleToRawLongBits with signature (D)J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallStaticLongMethod( cls, mid ,double_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Double::doubleToRawLongBits jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jlong Double::doubleToLongBits(jdouble double_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getDoubleClass();
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "doubleToLongBits", "(D)J");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Double has no method named doubleToLongBits with signature (D)J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallStaticLongMethod( cls, mid ,double_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Double::doubleToLongBits jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jdouble Double::longBitsToDouble(jlong long_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getDoubleClass();
            jdouble retVal= (jdouble) -1.0;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "longBitsToDouble", "(J)D");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Double has no method named longBitsToDouble with signature (J)D." << std::endl;
                    return (jdouble) -1.0;
                } else {
                    retVal= (jdouble)  env->CallStaticDoubleMethod( cls, mid ,long_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Double::longBitsToDouble jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Double::compareTo(const Double & double_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method compareTo of java.lang.Double with jthis == NULL." << std::endl;
                return (jint) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Double::compareTo jthis=",jthis);
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "compareTo", "(Ljava/lang/Double;)I");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Double::compareTo jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Double has no method named compareTo with signature (Ljava/lang/Double;)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallIntMethod(jthis, mid ,double_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Double::compareTo jthrowable t=",t);
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

        jbyte Double::byteValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method byteValue of java.lang.Double with jthis == NULL." << std::endl;
                return (jbyte) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Double::byteValue jthis=",jthis);
            jbyte retVal= (jbyte) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "byteValue", "()B");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Double::byteValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Double has no method named byteValue with signature ()B." << std::endl;
                    return (jbyte) -1;
                } else {
                    retVal= (jbyte)  env->CallByteMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Double::byteValue jthrowable t=",t);
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

        jshort Double::shortValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method shortValue of java.lang.Double with jthis == NULL." << std::endl;
                return (jshort) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Double::shortValue jthis=",jthis);
            jshort retVal=(jshort) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "shortValue", "()S");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Double::shortValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Double has no method named shortValue with signature ()S." << std::endl;
                    return (jshort) -1;
                } else {
                    retVal= (jshort)  env->CallShortMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Double::shortValue jthrowable t=",t);
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

        jint Double::intValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method intValue of java.lang.Double with jthis == NULL." << std::endl;
                return (jint) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Double::intValue jthis=",jthis);
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "intValue", "()I");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Double::intValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Double has no method named intValue with signature ()I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallIntMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Double::intValue jthrowable t=",t);
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

        jlong Double::longValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method longValue of java.lang.Double with jthis == NULL." << std::endl;
                return (jlong) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Double::longValue jthis=",jthis);
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "longValue", "()J");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Double::longValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Double has no method named longValue with signature ()J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallLongMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Double::longValue jthrowable t=",t);
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

        jfloat Double::floatValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method floatValue of java.lang.Double with jthis == NULL." << std::endl;
                return (jfloat) -1.0;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Double::floatValue jthis=",jthis);
            jfloat retVal= (jfloat) -1.0;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "floatValue", "()F");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Double::floatValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Double has no method named floatValue with signature ()F." << std::endl;
                    return (jfloat) -1.0;
                } else {
                    retVal= (jfloat)  env->CallFloatMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Double::floatValue jthrowable t=",t);
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

        jdouble Double::doubleValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method doubleValue of java.lang.Double with jthis == NULL." << std::endl;
                return (jdouble) -1.0;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Double::doubleValue jthis=",jthis);
            jdouble retVal= (jdouble) -1.0;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "doubleValue", "()D");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Double::doubleValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Double has no method named doubleValue with signature ()D." << std::endl;
                    return (jdouble) -1.0;
                } else {
                    retVal= (jdouble)  env->CallDoubleMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Double::doubleValue jthrowable t=",t);
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

        Double Double::valueOf(jdouble double_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getDoubleClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "valueOf", "(D)Ljava/lang/Double;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Double has no method named valueOf with signature (D)Ljava/lang/Double;." << std::endl;
                    static Double nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,double_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Double::valueOf jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            Double retObject(retVal,false);
            return retObject;
        }

        Double Double::valueOf(jstring string_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getDoubleClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "valueOf", "(Ljava/lang/String;)Ljava/lang/Double;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Double has no method named valueOf with signature (Ljava/lang/String;)Ljava/lang/Double;." << std::endl;
                    static Double nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Double::valueOf jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            Double retObject(retVal,false);
            return retObject;
        }

        jstring Double::toHexString(jdouble double_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getDoubleClass();
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "toHexString", "(D)Ljava/lang/String;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Double has no method named toHexString with signature (D)Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallStaticObjectMethod( cls, mid ,double_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Double::toHexString jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Double::compare(jdouble double_0,jdouble double_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getDoubleClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "compare", "(DD)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Double has no method named compare with signature (DD)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,double_0,double_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Double::compare jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jboolean Double::isNaN(jdouble double_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getDoubleClass();
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "isNaN", "(D)Z");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Double has no method named isNaN with signature (D)Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallStaticBooleanMethod( cls, mid ,double_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Double::isNaN jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jboolean Double::isNaN() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method isNaN of java.lang.Double with jthis == NULL." << std::endl;
                return false;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Double::isNaN jthis=",jthis);
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "isNaN", "()Z");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Double::isNaN jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Double has no method named isNaN with signature ()Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallBooleanMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Double::isNaN jthrowable t=",t);
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

        jboolean Double::isInfinite(jdouble double_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getDoubleClass();
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "isInfinite", "(D)Z");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Double has no method named isInfinite with signature (D)Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallStaticBooleanMethod( cls, mid ,double_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Double::isInfinite jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jboolean Double::isInfinite() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method isInfinite of java.lang.Double with jthis == NULL." << std::endl;
                return false;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Double::isInfinite jthis=",jthis);
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "isInfinite", "()Z");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Double::isInfinite jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Double has no method named isInfinite with signature ()Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallBooleanMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Double::isInfinite jthrowable t=",t);
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

        jboolean Double::isFinite(jdouble double_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getDoubleClass();
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "isFinite", "(D)Z");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Double has no method named isFinite with signature (D)Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallStaticBooleanMethod( cls, mid ,double_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Double::isFinite jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jdouble Double::sum(jdouble double_0,jdouble double_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getDoubleClass();
            jdouble retVal= (jdouble) -1.0;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "sum", "(DD)D");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Double has no method named sum with signature (DD)D." << std::endl;
                    return (jdouble) -1.0;
                } else {
                    retVal= (jdouble)  env->CallStaticDoubleMethod( cls, mid ,double_0,double_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Double::sum jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jdouble Double::parseDouble(jstring string_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getDoubleClass();
            jdouble retVal= (jdouble) -1.0;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "parseDouble", "(Ljava/lang/String;)D");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Double has no method named parseDouble with signature (Ljava/lang/String;)D." << std::endl;
                    return (jdouble) -1.0;
                } else {
                    retVal= (jdouble)  env->CallStaticDoubleMethod( cls, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Double::parseDouble jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }
        static jclass getNewDoubleClass() {
            jclass clss = getEnv()->FindClass("java/lang/Double");
            if (NULL == clss) {
                std::cerr << " Can't find class java/lang/Double" << std::endl;
            }
            return clss;
        }
        
        static jclass DoubleClass = NULL;
        static inline jclass getDoubleClass() {
            if (DoubleClass != NULL) {
                return DoubleClass;
            }
            DoubleClass = getNewDoubleClass();
            return DoubleClass;
        }
        } // end namespace lang
    } // end namespace java

    
    
        // end namespace crclj
    }
    
