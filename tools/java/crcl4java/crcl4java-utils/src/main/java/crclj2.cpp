
#include <jni.h>
#include <cstdlib>
#include <iostream>
#include "crclj.h"

namespace crclj {

    



// start_segment_index = 20
// start_segment_index = 30
// segment_index = 2
// classesSegList=[class crcl.base.ThreeFingerGripperStatusType, class crcl.base.AngleUnitEnumType, class crcl.base.PointType, class crcl.base.DwellType, class crcl.base.SetMotionCoordinationType, class crcl.base.RotSpeedType, class crcl.base.RotSpeedAbsoluteType, class crcl.base.DisableRobotParameterStatusType, class crcl.base.CountSensorStatusType, class crcl.base.MoveScrewType]


// class_index = 0 clss=class crcl.base.ThreeFingerGripperStatusType

    namespace crcl{
        namespace base{
        
        // get JNI handle for class crcl.base.ThreeFingerGripperStatusType
        static inline jclass getThreeFingerGripperStatusTypeClass();
        
        ThreeFingerGripperStatusType::ThreeFingerGripperStatusType(jobject _jthis, bool copy): GripperStatusType(_jthis,copy) {
                
        }
        
        ThreeFingerGripperStatusType::ThreeFingerGripperStatusType(const ThreeFingerGripperStatusType &objref): GripperStatusType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class ThreeFingerGripperStatusType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class ThreeFingerGripperStatusType jthis=",jthis);
            }
        }
        
        ThreeFingerGripperStatusType ThreeFingerGripperStatusType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getThreeFingerGripperStatusTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            ThreeFingerGripperStatusType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool ThreeFingerGripperStatusType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getThreeFingerGripperStatusTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        ThreeFingerGripperStatusType::ThreeFingerGripperStatusType() : GripperStatusType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getThreeFingerGripperStatusTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class ThreeFingerGripperStatusType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ThreeFingerGripperStatusType::setHoldingObject jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new ThreeFingerGripperStatusType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new ThreeFingerGripperStatusType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.ThreeFingerGripperStatusType
        ThreeFingerGripperStatusType::~ThreeFingerGripperStatusType() {
        	// Place-holder for later extensibility.
        }


        ::crclj::java::lang::Double ThreeFingerGripperStatusType::getFinger1Position() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getFinger1Position of crcl.base.ThreeFingerGripperStatusType with jthis == NULL." << std::endl;
                static ::crclj::java::lang::Double nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ThreeFingerGripperStatusType::getFinger1Position jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getFinger1Position", "()Ljava/lang/Double;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ThreeFingerGripperStatusType::getFinger1Position jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ThreeFingerGripperStatusType has no method named getFinger1Position with signature ()Ljava/lang/Double;." << std::endl;
                    static ::crclj::java::lang::Double nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ThreeFingerGripperStatusType::getFinger1Position jthrowable t=",t);
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

        void ThreeFingerGripperStatusType::setFinger1Position(const ::crclj::java::lang::Double & double_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setFinger1Position of crcl.base.ThreeFingerGripperStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ThreeFingerGripperStatusType::setFinger1Position jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setFinger1Position", "(Ljava/lang/Double;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ThreeFingerGripperStatusType::setFinger1Position jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ThreeFingerGripperStatusType has no method named setFinger1Position with signature (Ljava/lang/Double;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,double_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ThreeFingerGripperStatusType::setFinger1Position jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        ::crclj::java::lang::Double ThreeFingerGripperStatusType::getFinger2Position() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getFinger2Position of crcl.base.ThreeFingerGripperStatusType with jthis == NULL." << std::endl;
                static ::crclj::java::lang::Double nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ThreeFingerGripperStatusType::getFinger2Position jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getFinger2Position", "()Ljava/lang/Double;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ThreeFingerGripperStatusType::getFinger2Position jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ThreeFingerGripperStatusType has no method named getFinger2Position with signature ()Ljava/lang/Double;." << std::endl;
                    static ::crclj::java::lang::Double nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ThreeFingerGripperStatusType::getFinger2Position jthrowable t=",t);
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

        void ThreeFingerGripperStatusType::setFinger2Position(const ::crclj::java::lang::Double & double_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setFinger2Position of crcl.base.ThreeFingerGripperStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ThreeFingerGripperStatusType::setFinger2Position jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setFinger2Position", "(Ljava/lang/Double;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ThreeFingerGripperStatusType::setFinger2Position jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ThreeFingerGripperStatusType has no method named setFinger2Position with signature (Ljava/lang/Double;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,double_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ThreeFingerGripperStatusType::setFinger2Position jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        ::crclj::java::lang::Double ThreeFingerGripperStatusType::getFinger3Position() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getFinger3Position of crcl.base.ThreeFingerGripperStatusType with jthis == NULL." << std::endl;
                static ::crclj::java::lang::Double nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ThreeFingerGripperStatusType::getFinger3Position jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getFinger3Position", "()Ljava/lang/Double;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ThreeFingerGripperStatusType::getFinger3Position jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ThreeFingerGripperStatusType has no method named getFinger3Position with signature ()Ljava/lang/Double;." << std::endl;
                    static ::crclj::java::lang::Double nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ThreeFingerGripperStatusType::getFinger3Position jthrowable t=",t);
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

        void ThreeFingerGripperStatusType::setFinger3Position(const ::crclj::java::lang::Double & double_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setFinger3Position of crcl.base.ThreeFingerGripperStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ThreeFingerGripperStatusType::setFinger3Position jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setFinger3Position", "(Ljava/lang/Double;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ThreeFingerGripperStatusType::setFinger3Position jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ThreeFingerGripperStatusType has no method named setFinger3Position with signature (Ljava/lang/Double;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,double_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ThreeFingerGripperStatusType::setFinger3Position jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        ::crclj::java::lang::Double ThreeFingerGripperStatusType::getFinger1Force() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getFinger1Force of crcl.base.ThreeFingerGripperStatusType with jthis == NULL." << std::endl;
                static ::crclj::java::lang::Double nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ThreeFingerGripperStatusType::getFinger1Force jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getFinger1Force", "()Ljava/lang/Double;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ThreeFingerGripperStatusType::getFinger1Force jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ThreeFingerGripperStatusType has no method named getFinger1Force with signature ()Ljava/lang/Double;." << std::endl;
                    static ::crclj::java::lang::Double nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ThreeFingerGripperStatusType::getFinger1Force jthrowable t=",t);
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

        void ThreeFingerGripperStatusType::setFinger1Force(const ::crclj::java::lang::Double & double_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setFinger1Force of crcl.base.ThreeFingerGripperStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ThreeFingerGripperStatusType::setFinger1Force jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setFinger1Force", "(Ljava/lang/Double;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ThreeFingerGripperStatusType::setFinger1Force jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ThreeFingerGripperStatusType has no method named setFinger1Force with signature (Ljava/lang/Double;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,double_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ThreeFingerGripperStatusType::setFinger1Force jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        ::crclj::java::lang::Double ThreeFingerGripperStatusType::getFinger2Force() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getFinger2Force of crcl.base.ThreeFingerGripperStatusType with jthis == NULL." << std::endl;
                static ::crclj::java::lang::Double nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ThreeFingerGripperStatusType::getFinger2Force jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getFinger2Force", "()Ljava/lang/Double;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ThreeFingerGripperStatusType::getFinger2Force jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ThreeFingerGripperStatusType has no method named getFinger2Force with signature ()Ljava/lang/Double;." << std::endl;
                    static ::crclj::java::lang::Double nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ThreeFingerGripperStatusType::getFinger2Force jthrowable t=",t);
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

        void ThreeFingerGripperStatusType::setFinger2Force(const ::crclj::java::lang::Double & double_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setFinger2Force of crcl.base.ThreeFingerGripperStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ThreeFingerGripperStatusType::setFinger2Force jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setFinger2Force", "(Ljava/lang/Double;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ThreeFingerGripperStatusType::setFinger2Force jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ThreeFingerGripperStatusType has no method named setFinger2Force with signature (Ljava/lang/Double;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,double_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ThreeFingerGripperStatusType::setFinger2Force jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        ::crclj::java::lang::Double ThreeFingerGripperStatusType::getFinger3Force() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getFinger3Force of crcl.base.ThreeFingerGripperStatusType with jthis == NULL." << std::endl;
                static ::crclj::java::lang::Double nullObject((jobject)NULL,false); return nullObject;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ThreeFingerGripperStatusType::getFinger3Force jthis=",jthis);
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getFinger3Force", "()Ljava/lang/Double;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ThreeFingerGripperStatusType::getFinger3Force jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ThreeFingerGripperStatusType has no method named getFinger3Force with signature ()Ljava/lang/Double;." << std::endl;
                    static ::crclj::java::lang::Double nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ThreeFingerGripperStatusType::getFinger3Force jthrowable t=",t);
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

        void ThreeFingerGripperStatusType::setFinger3Force(const ::crclj::java::lang::Double & double_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setFinger3Force of crcl.base.ThreeFingerGripperStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ThreeFingerGripperStatusType::setFinger3Force jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setFinger3Force", "(Ljava/lang/Double;)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ThreeFingerGripperStatusType::setFinger3Force jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ThreeFingerGripperStatusType has no method named setFinger3Force with signature (Ljava/lang/Double;)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,double_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ThreeFingerGripperStatusType::setFinger3Force jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }
        static jclass getNewThreeFingerGripperStatusTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/ThreeFingerGripperStatusType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/ThreeFingerGripperStatusType" << std::endl;
            }
            return clss;
        }
        
        static jclass ThreeFingerGripperStatusTypeClass = NULL;
        static inline jclass getThreeFingerGripperStatusTypeClass() {
            if (ThreeFingerGripperStatusTypeClass != NULL) {
                return ThreeFingerGripperStatusTypeClass;
            }
            ThreeFingerGripperStatusTypeClass = getNewThreeFingerGripperStatusTypeClass();
            return ThreeFingerGripperStatusTypeClass;
        }

    // class_index = 1 clss=class crcl.base.AngleUnitEnumType

        
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
                        DebugPrintJObject(__FILE__,__LINE__," AngleUnitEnumType::setFinger3Force jthis=",t);
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

    // class_index = 2 clss=class crcl.base.PointType

        
        // get JNI handle for class crcl.base.PointType
        static inline jclass getPointTypeClass();
        
        PointType::PointType(jobject _jthis, bool copy): DataThingType(_jthis,copy) {
                
        }
        
        PointType::PointType(const PointType &objref): DataThingType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class PointType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class PointType jthis=",jthis);
            }
        }
        
        PointType PointType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getPointTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            PointType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool PointType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getPointTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        PointType::PointType() : DataThingType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getPointTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class PointType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," PointType::fromValue jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new PointType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new PointType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.PointType
        PointType::~PointType() {
        	// Place-holder for later extensibility.
        }


        jdouble PointType::getX() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getX of crcl.base.PointType with jthis == NULL." << std::endl;
                return (jdouble) -1.0;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," PointType::getX jthis=",jthis);
            jdouble retVal= (jdouble) -1.0;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getX", "()D");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," PointType::getX jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.PointType has no method named getX with signature ()D." << std::endl;
                    return (jdouble) -1.0;
                } else {
                    retVal= (jdouble)  env->CallDoubleMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," PointType::getX jthrowable t=",t);
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

        void PointType::setX(jdouble double_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setX of crcl.base.PointType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," PointType::setX jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setX", "(D)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," PointType::setX jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.PointType has no method named setX with signature (D)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,double_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," PointType::setX jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        jdouble PointType::getY() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getY of crcl.base.PointType with jthis == NULL." << std::endl;
                return (jdouble) -1.0;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," PointType::getY jthis=",jthis);
            jdouble retVal= (jdouble) -1.0;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getY", "()D");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," PointType::getY jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.PointType has no method named getY with signature ()D." << std::endl;
                    return (jdouble) -1.0;
                } else {
                    retVal= (jdouble)  env->CallDoubleMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," PointType::getY jthrowable t=",t);
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

        void PointType::setY(jdouble double_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setY of crcl.base.PointType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," PointType::setY jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setY", "(D)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," PointType::setY jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.PointType has no method named setY with signature (D)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,double_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," PointType::setY jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        jdouble PointType::getZ() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getZ of crcl.base.PointType with jthis == NULL." << std::endl;
                return (jdouble) -1.0;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," PointType::getZ jthis=",jthis);
            jdouble retVal= (jdouble) -1.0;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getZ", "()D");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," PointType::getZ jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.PointType has no method named getZ with signature ()D." << std::endl;
                    return (jdouble) -1.0;
                } else {
                    retVal= (jdouble)  env->CallDoubleMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," PointType::getZ jthrowable t=",t);
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

        void PointType::setZ(jdouble double_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setZ of crcl.base.PointType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," PointType::setZ jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setZ", "(D)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," PointType::setZ jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.PointType has no method named setZ with signature (D)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,double_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," PointType::setZ jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }
        static jclass getNewPointTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/PointType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/PointType" << std::endl;
            }
            return clss;
        }
        
        static jclass PointTypeClass = NULL;
        static inline jclass getPointTypeClass() {
            if (PointTypeClass != NULL) {
                return PointTypeClass;
            }
            PointTypeClass = getNewPointTypeClass();
            return PointTypeClass;
        }

    // class_index = 3 clss=class crcl.base.DwellType

        
        // get JNI handle for class crcl.base.DwellType
        static inline jclass getDwellTypeClass();
        
        DwellType::DwellType(jobject _jthis, bool copy): MiddleCommandType(_jthis,copy) {
                
        }
        
        DwellType::DwellType(const DwellType &objref): MiddleCommandType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class DwellType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class DwellType jthis=",jthis);
            }
        }
        
        DwellType DwellType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getDwellTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            DwellType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool DwellType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getDwellTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        DwellType::DwellType() : MiddleCommandType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getDwellTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class DwellType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," DwellType::setZ jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new DwellType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new DwellType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.DwellType
        DwellType::~DwellType() {
        	// Place-holder for later extensibility.
        }


        jdouble DwellType::getDwellTime() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getDwellTime of crcl.base.DwellType with jthis == NULL." << std::endl;
                return (jdouble) -1.0;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," DwellType::getDwellTime jthis=",jthis);
            jdouble retVal= (jdouble) -1.0;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getDwellTime", "()D");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," DwellType::getDwellTime jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.DwellType has no method named getDwellTime with signature ()D." << std::endl;
                    return (jdouble) -1.0;
                } else {
                    retVal= (jdouble)  env->CallDoubleMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," DwellType::getDwellTime jthrowable t=",t);
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

        void DwellType::setDwellTime(jdouble double_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setDwellTime of crcl.base.DwellType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," DwellType::setDwellTime jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setDwellTime", "(D)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," DwellType::setDwellTime jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.DwellType has no method named setDwellTime with signature (D)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,double_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," DwellType::setDwellTime jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }
        static jclass getNewDwellTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/DwellType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/DwellType" << std::endl;
            }
            return clss;
        }
        
        static jclass DwellTypeClass = NULL;
        static inline jclass getDwellTypeClass() {
            if (DwellTypeClass != NULL) {
                return DwellTypeClass;
            }
            DwellTypeClass = getNewDwellTypeClass();
            return DwellTypeClass;
        }

    // class_index = 4 clss=class crcl.base.SetMotionCoordinationType

        
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
                            DebugPrintJObject(__FILE__,__LINE__," SetMotionCoordinationType::setDwellTime jthis=",t);
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

    // class_index = 5 clss=class crcl.base.RotSpeedType

        
        // get JNI handle for class crcl.base.RotSpeedType
        static inline jclass getRotSpeedTypeClass();
        
        RotSpeedType::RotSpeedType(jobject _jthis, bool copy): DataThingType(_jthis,copy) {
                
        }
        
        RotSpeedType::RotSpeedType(const RotSpeedType &objref): DataThingType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class RotSpeedType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class RotSpeedType jthis=",jthis);
            }
        }
        
        RotSpeedType RotSpeedType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getRotSpeedTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            RotSpeedType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool RotSpeedType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getRotSpeedTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        RotSpeedType::RotSpeedType() : DataThingType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getRotSpeedTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class RotSpeedType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," RotSpeedType::setCoordinated jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new RotSpeedType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new RotSpeedType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.RotSpeedType
        RotSpeedType::~RotSpeedType() {
        	// Place-holder for later extensibility.
        }

        static jclass getNewRotSpeedTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/RotSpeedType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/RotSpeedType" << std::endl;
            }
            return clss;
        }
        
        static jclass RotSpeedTypeClass = NULL;
        static inline jclass getRotSpeedTypeClass() {
            if (RotSpeedTypeClass != NULL) {
                return RotSpeedTypeClass;
            }
            RotSpeedTypeClass = getNewRotSpeedTypeClass();
            return RotSpeedTypeClass;
        }

    // class_index = 6 clss=class crcl.base.RotSpeedAbsoluteType

        
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
                            DebugPrintJObject(__FILE__,__LINE__," RotSpeedAbsoluteType::setCoordinated jthis=",t);
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

    // class_index = 7 clss=class crcl.base.DisableRobotParameterStatusType

        
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
                            DebugPrintJObject(__FILE__,__LINE__," DisableRobotParameterStatusType::setSetting jthis=",t);
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

    // class_index = 8 clss=class crcl.base.CountSensorStatusType

        
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
                            DebugPrintJObject(__FILE__,__LINE__," CountSensorStatusType::setRobotParameterName jthis=",t);
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

    // class_index = 9 clss=class crcl.base.MoveScrewType

        
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
                            DebugPrintJObject(__FILE__,__LINE__," MoveScrewType::setCountValue jthis=",t);
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
        } // end namespace base
    } // end namespace crcl

    
    
        // end namespace crclj
    }
    
