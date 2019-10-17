
#include <jni.h>
#include <cstdlib>
#include <iostream>
#include "crclj.h"

namespace crclj {

    



// start_segment_index = 30
// start_segment_index = 40
// segment_index = 3
// classesSegList=[class crcl.base.SetForceUnitsType, class crcl.base.TransAccelType, class crcl.base.TransAccelRelativeType, class crcl.base.SetEndEffectorType, class crcl.base.JointStatusType, class crcl.base.PoseStatusType, class crcl.base.ConfigureStatusReportType, class crcl.base.VacuumGripperStatusType, class crcl.base.PoseType, class crcl.base.PoseAndSetType]


// class_index = 0 clss=class crcl.base.SetForceUnitsType

    namespace crcl{
        namespace base{
        
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

    // class_index = 1 clss=class crcl.base.TransAccelType

        
        // get JNI handle for class crcl.base.TransAccelType
        static inline jclass getTransAccelTypeClass();
        
        TransAccelType::TransAccelType(jobject _jthis, bool copy): DataThingType(_jthis,copy) {
                
        }
        
        TransAccelType::TransAccelType(const TransAccelType &objref): DataThingType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class TransAccelType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class TransAccelType jthis=",jthis);
            }
        }
        
        TransAccelType TransAccelType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getTransAccelTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            TransAccelType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool TransAccelType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getTransAccelTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        TransAccelType::TransAccelType() : DataThingType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getTransAccelTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class TransAccelType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," TransAccelType::setUnitName jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new TransAccelType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new TransAccelType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.TransAccelType
        TransAccelType::~TransAccelType() {
        	// Place-holder for later extensibility.
        }

        static jclass getNewTransAccelTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/TransAccelType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/TransAccelType" << std::endl;
            }
            return clss;
        }
        
        static jclass TransAccelTypeClass = NULL;
        static inline jclass getTransAccelTypeClass() {
            if (TransAccelTypeClass != NULL) {
                return TransAccelTypeClass;
            }
            TransAccelTypeClass = getNewTransAccelTypeClass();
            return TransAccelTypeClass;
        }

    // class_index = 2 clss=class crcl.base.TransAccelRelativeType

        
        // get JNI handle for class crcl.base.TransAccelRelativeType
        static inline jclass getTransAccelRelativeTypeClass();
        
        TransAccelRelativeType::TransAccelRelativeType(jobject _jthis, bool copy): TransAccelType(_jthis,copy) {
                
        }
        
        TransAccelRelativeType::TransAccelRelativeType(const TransAccelRelativeType &objref): TransAccelType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class TransAccelRelativeType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class TransAccelRelativeType jthis=",jthis);
            }
        }
        
        TransAccelRelativeType TransAccelRelativeType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getTransAccelRelativeTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            TransAccelRelativeType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool TransAccelRelativeType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getTransAccelRelativeTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        TransAccelRelativeType::TransAccelRelativeType() : TransAccelType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getTransAccelRelativeTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class TransAccelRelativeType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," TransAccelRelativeType::setUnitName jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new TransAccelRelativeType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new TransAccelRelativeType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.TransAccelRelativeType
        TransAccelRelativeType::~TransAccelRelativeType() {
        	// Place-holder for later extensibility.
        }


        jdouble TransAccelRelativeType::getFraction() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getFraction of crcl.base.TransAccelRelativeType with jthis == NULL." << std::endl;
                return (jdouble) -1.0;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," TransAccelRelativeType::getFraction jthis=",jthis);
            jdouble retVal= (jdouble) -1.0;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getFraction", "()D");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," TransAccelRelativeType::getFraction jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.TransAccelRelativeType has no method named getFraction with signature ()D." << std::endl;
                    return (jdouble) -1.0;
                } else {
                    retVal= (jdouble)  env->CallDoubleMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," TransAccelRelativeType::getFraction jthrowable t=",t);
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

        void TransAccelRelativeType::setFraction(jdouble double_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setFraction of crcl.base.TransAccelRelativeType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," TransAccelRelativeType::setFraction jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setFraction", "(D)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," TransAccelRelativeType::setFraction jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.TransAccelRelativeType has no method named setFraction with signature (D)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,double_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," TransAccelRelativeType::setFraction jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }
        static jclass getNewTransAccelRelativeTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/TransAccelRelativeType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/TransAccelRelativeType" << std::endl;
            }
            return clss;
        }
        
        static jclass TransAccelRelativeTypeClass = NULL;
        static inline jclass getTransAccelRelativeTypeClass() {
            if (TransAccelRelativeTypeClass != NULL) {
                return TransAccelRelativeTypeClass;
            }
            TransAccelRelativeTypeClass = getNewTransAccelRelativeTypeClass();
            return TransAccelRelativeTypeClass;
        }

    // class_index = 3 clss=class crcl.base.SetEndEffectorType

        
        // get JNI handle for class crcl.base.SetEndEffectorType
        static inline jclass getSetEndEffectorTypeClass();
        
        SetEndEffectorType::SetEndEffectorType(jobject _jthis, bool copy): MiddleCommandType(_jthis,copy) {
                
        }
        
        SetEndEffectorType::SetEndEffectorType(const SetEndEffectorType &objref): MiddleCommandType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class SetEndEffectorType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class SetEndEffectorType jthis=",jthis);
            }
        }
        
        SetEndEffectorType SetEndEffectorType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getSetEndEffectorTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            SetEndEffectorType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool SetEndEffectorType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getSetEndEffectorTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        SetEndEffectorType::SetEndEffectorType() : MiddleCommandType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getSetEndEffectorTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class SetEndEffectorType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," SetEndEffectorType::setFraction jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new SetEndEffectorType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new SetEndEffectorType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.SetEndEffectorType
        SetEndEffectorType::~SetEndEffectorType() {
        	// Place-holder for later extensibility.
        }


        jdouble SetEndEffectorType::getSetting() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method getSetting of crcl.base.SetEndEffectorType with jthis == NULL." << std::endl;
                return (jdouble) -1.0;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," SetEndEffectorType::getSetting jthis=",jthis);
            jdouble retVal= (jdouble) -1.0;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "getSetting", "()D");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," SetEndEffectorType::getSetting jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.SetEndEffectorType has no method named getSetting with signature ()D." << std::endl;
                    return (jdouble) -1.0;
                } else {
                    retVal= (jdouble)  env->CallDoubleMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," SetEndEffectorType::getSetting jthrowable t=",t);
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

        void SetEndEffectorType::setSetting(jdouble double_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setSetting of crcl.base.SetEndEffectorType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," SetEndEffectorType::setSetting jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setSetting", "(D)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," SetEndEffectorType::setSetting jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.SetEndEffectorType has no method named setSetting with signature (D)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,double_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," SetEndEffectorType::setSetting jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }
        static jclass getNewSetEndEffectorTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/SetEndEffectorType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/SetEndEffectorType" << std::endl;
            }
            return clss;
        }
        
        static jclass SetEndEffectorTypeClass = NULL;
        static inline jclass getSetEndEffectorTypeClass() {
            if (SetEndEffectorTypeClass != NULL) {
                return SetEndEffectorTypeClass;
            }
            SetEndEffectorTypeClass = getNewSetEndEffectorTypeClass();
            return SetEndEffectorTypeClass;
        }

    // class_index = 4 clss=class crcl.base.JointStatusType

        
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
                            DebugPrintJObject(__FILE__,__LINE__," JointStatusType::setSetting jthis=",t);
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

    // class_index = 5 clss=class crcl.base.PoseStatusType

        
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

    // class_index = 6 clss=class crcl.base.ConfigureStatusReportType

        
        // get JNI handle for class crcl.base.ConfigureStatusReportType
        static inline jclass getConfigureStatusReportTypeClass();
        
        ConfigureStatusReportType::ConfigureStatusReportType(jobject _jthis, bool copy): MiddleCommandType(_jthis,copy) {
                
        }
        
        ConfigureStatusReportType::ConfigureStatusReportType(const ConfigureStatusReportType &objref): MiddleCommandType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class ConfigureStatusReportType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class ConfigureStatusReportType jthis=",jthis);
            }
        }
        
        ConfigureStatusReportType ConfigureStatusReportType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getConfigureStatusReportTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            ConfigureStatusReportType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool ConfigureStatusReportType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getConfigureStatusReportTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        ConfigureStatusReportType::ConfigureStatusReportType() : MiddleCommandType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getConfigureStatusReportTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class ConfigureStatusReportType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ConfigureStatusReportType::setConfiguration jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new ConfigureStatusReportType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new ConfigureStatusReportType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.ConfigureStatusReportType
        ConfigureStatusReportType::~ConfigureStatusReportType() {
        	// Place-holder for later extensibility.
        }


        jboolean ConfigureStatusReportType::isReportJointStatuses() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method isReportJointStatuses of crcl.base.ConfigureStatusReportType with jthis == NULL." << std::endl;
                return false;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ConfigureStatusReportType::isReportJointStatuses jthis=",jthis);
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "isReportJointStatuses", "()Z");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ConfigureStatusReportType::isReportJointStatuses jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ConfigureStatusReportType has no method named isReportJointStatuses with signature ()Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallBooleanMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ConfigureStatusReportType::isReportJointStatuses jthrowable t=",t);
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

        void ConfigureStatusReportType::setReportJointStatuses(jboolean boolean_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setReportJointStatuses of crcl.base.ConfigureStatusReportType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ConfigureStatusReportType::setReportJointStatuses jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setReportJointStatuses", "(Z)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ConfigureStatusReportType::setReportJointStatuses jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ConfigureStatusReportType has no method named setReportJointStatuses with signature (Z)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,boolean_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ConfigureStatusReportType::setReportJointStatuses jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        jboolean ConfigureStatusReportType::isReportPoseStatus() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method isReportPoseStatus of crcl.base.ConfigureStatusReportType with jthis == NULL." << std::endl;
                return false;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ConfigureStatusReportType::isReportPoseStatus jthis=",jthis);
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "isReportPoseStatus", "()Z");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ConfigureStatusReportType::isReportPoseStatus jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ConfigureStatusReportType has no method named isReportPoseStatus with signature ()Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallBooleanMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ConfigureStatusReportType::isReportPoseStatus jthrowable t=",t);
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

        void ConfigureStatusReportType::setReportPoseStatus(jboolean boolean_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setReportPoseStatus of crcl.base.ConfigureStatusReportType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ConfigureStatusReportType::setReportPoseStatus jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setReportPoseStatus", "(Z)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ConfigureStatusReportType::setReportPoseStatus jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ConfigureStatusReportType has no method named setReportPoseStatus with signature (Z)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,boolean_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ConfigureStatusReportType::setReportPoseStatus jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        jboolean ConfigureStatusReportType::isReportGripperStatus() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method isReportGripperStatus of crcl.base.ConfigureStatusReportType with jthis == NULL." << std::endl;
                return false;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ConfigureStatusReportType::isReportGripperStatus jthis=",jthis);
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "isReportGripperStatus", "()Z");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ConfigureStatusReportType::isReportGripperStatus jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ConfigureStatusReportType has no method named isReportGripperStatus with signature ()Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallBooleanMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ConfigureStatusReportType::isReportGripperStatus jthrowable t=",t);
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

        void ConfigureStatusReportType::setReportGripperStatus(jboolean boolean_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setReportGripperStatus of crcl.base.ConfigureStatusReportType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ConfigureStatusReportType::setReportGripperStatus jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setReportGripperStatus", "(Z)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ConfigureStatusReportType::setReportGripperStatus jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ConfigureStatusReportType has no method named setReportGripperStatus with signature (Z)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,boolean_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ConfigureStatusReportType::setReportGripperStatus jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        jboolean ConfigureStatusReportType::isReportSettingsStatus() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method isReportSettingsStatus of crcl.base.ConfigureStatusReportType with jthis == NULL." << std::endl;
                return false;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ConfigureStatusReportType::isReportSettingsStatus jthis=",jthis);
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "isReportSettingsStatus", "()Z");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ConfigureStatusReportType::isReportSettingsStatus jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ConfigureStatusReportType has no method named isReportSettingsStatus with signature ()Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallBooleanMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ConfigureStatusReportType::isReportSettingsStatus jthrowable t=",t);
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

        void ConfigureStatusReportType::setReportSettingsStatus(jboolean boolean_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setReportSettingsStatus of crcl.base.ConfigureStatusReportType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ConfigureStatusReportType::setReportSettingsStatus jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setReportSettingsStatus", "(Z)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ConfigureStatusReportType::setReportSettingsStatus jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ConfigureStatusReportType has no method named setReportSettingsStatus with signature (Z)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,boolean_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ConfigureStatusReportType::setReportSettingsStatus jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        jboolean ConfigureStatusReportType::isReportSensorsStatus() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method isReportSensorsStatus of crcl.base.ConfigureStatusReportType with jthis == NULL." << std::endl;
                return false;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ConfigureStatusReportType::isReportSensorsStatus jthis=",jthis);
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "isReportSensorsStatus", "()Z");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ConfigureStatusReportType::isReportSensorsStatus jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ConfigureStatusReportType has no method named isReportSensorsStatus with signature ()Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallBooleanMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ConfigureStatusReportType::isReportSensorsStatus jthrowable t=",t);
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

        void ConfigureStatusReportType::setReportSensorsStatus(jboolean boolean_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setReportSensorsStatus of crcl.base.ConfigureStatusReportType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ConfigureStatusReportType::setReportSensorsStatus jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setReportSensorsStatus", "(Z)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ConfigureStatusReportType::setReportSensorsStatus jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ConfigureStatusReportType has no method named setReportSensorsStatus with signature (Z)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,boolean_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ConfigureStatusReportType::setReportSensorsStatus jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }

        jboolean ConfigureStatusReportType::isReportGuardsStatus() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method isReportGuardsStatus of crcl.base.ConfigureStatusReportType with jthis == NULL." << std::endl;
                return false;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ConfigureStatusReportType::isReportGuardsStatus jthis=",jthis);
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "isReportGuardsStatus", "()Z");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ConfigureStatusReportType::isReportGuardsStatus jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ConfigureStatusReportType has no method named isReportGuardsStatus with signature ()Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallBooleanMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ConfigureStatusReportType::isReportGuardsStatus jthrowable t=",t);
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

        void ConfigureStatusReportType::setReportGuardsStatus(jboolean boolean_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setReportGuardsStatus of crcl.base.ConfigureStatusReportType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," ConfigureStatusReportType::setReportGuardsStatus jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setReportGuardsStatus", "(Z)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," ConfigureStatusReportType::setReportGuardsStatus jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.ConfigureStatusReportType has no method named setReportGuardsStatus with signature (Z)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,boolean_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," ConfigureStatusReportType::setReportGuardsStatus jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }
        static jclass getNewConfigureStatusReportTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/ConfigureStatusReportType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/ConfigureStatusReportType" << std::endl;
            }
            return clss;
        }
        
        static jclass ConfigureStatusReportTypeClass = NULL;
        static inline jclass getConfigureStatusReportTypeClass() {
            if (ConfigureStatusReportTypeClass != NULL) {
                return ConfigureStatusReportTypeClass;
            }
            ConfigureStatusReportTypeClass = getNewConfigureStatusReportTypeClass();
            return ConfigureStatusReportTypeClass;
        }

    // class_index = 7 clss=class crcl.base.VacuumGripperStatusType

        
        // get JNI handle for class crcl.base.VacuumGripperStatusType
        static inline jclass getVacuumGripperStatusTypeClass();
        
        VacuumGripperStatusType::VacuumGripperStatusType(jobject _jthis, bool copy): GripperStatusType(_jthis,copy) {
                
        }
        
        VacuumGripperStatusType::VacuumGripperStatusType(const VacuumGripperStatusType &objref): GripperStatusType((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class VacuumGripperStatusType _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class VacuumGripperStatusType jthis=",jthis);
            }
        }
        
        VacuumGripperStatusType VacuumGripperStatusType::cast(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getVacuumGripperStatusTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            VacuumGripperStatusType retVal(objref.jthis,true);
            return retVal;
        }
        
        bool VacuumGripperStatusType::instanceof(const ::crclj::java::lang::Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getVacuumGripperStatusTypeClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        VacuumGripperStatusType::VacuumGripperStatusType() : GripperStatusType((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getVacuumGripperStatusTypeClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
                if (NULL == mid) {
                    std::cerr << "Class VacuumGripperStatusType has no method constructor signature ()V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," VacuumGripperStatusType::setReportGuardsStatus jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new VacuumGripperStatusType with signature ()V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new VacuumGripperStatusType jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for crcl.base.VacuumGripperStatusType
        VacuumGripperStatusType::~VacuumGripperStatusType() {
        	// Place-holder for later extensibility.
        }


        jboolean VacuumGripperStatusType::isIsPowered() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method isIsPowered of crcl.base.VacuumGripperStatusType with jthis == NULL." << std::endl;
                return false;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," VacuumGripperStatusType::isIsPowered jthis=",jthis);
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "isIsPowered", "()Z");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," VacuumGripperStatusType::isIsPowered jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.VacuumGripperStatusType has no method named isIsPowered with signature ()Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallBooleanMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," VacuumGripperStatusType::isIsPowered jthrowable t=",t);
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

        void VacuumGripperStatusType::setIsPowered(jboolean boolean_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method setIsPowered of crcl.base.VacuumGripperStatusType with jthis == NULL." << std::endl;
                return;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," VacuumGripperStatusType::setIsPowered jthis=",jthis);
            
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "setIsPowered", "(Z)V");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," VacuumGripperStatusType::setIsPowered jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class crcl.base.VacuumGripperStatusType has no method named setIsPowered with signature (Z)V." << std::endl;
                    return;
                } else {
                     env->CallVoidMethod(jthis, mid ,boolean_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," VacuumGripperStatusType::setIsPowered jthrowable t=",t);
                            env->ExceptionDescribe();
                        }
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            
        }
        static jclass getNewVacuumGripperStatusTypeClass() {
            jclass clss = getEnv()->FindClass("crcl/base/VacuumGripperStatusType");
            if (NULL == clss) {
                std::cerr << " Can't find class crcl/base/VacuumGripperStatusType" << std::endl;
            }
            return clss;
        }
        
        static jclass VacuumGripperStatusTypeClass = NULL;
        static inline jclass getVacuumGripperStatusTypeClass() {
            if (VacuumGripperStatusTypeClass != NULL) {
                return VacuumGripperStatusTypeClass;
            }
            VacuumGripperStatusTypeClass = getNewVacuumGripperStatusTypeClass();
            return VacuumGripperStatusTypeClass;
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
                            DebugPrintJObject(__FILE__,__LINE__," PoseType::setIsPowered jthis=",t);
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

    // class_index = 9 clss=class crcl.base.PoseAndSetType

        
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
                            DebugPrintJObject(__FILE__,__LINE__," PoseAndSetType::setZAxis jthis=",t);
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
        } // end namespace base
    } // end namespace crcl

    
    
        // end namespace crclj
    }
    
