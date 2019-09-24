
#include <jni.h>
#include <cstdlib>
#include <iostream>
#include "crclj.h"

namespace crclj {

    



// start_segment_index = 90
// start_segment_index = 99
// segment_index = 9
// classesSegList=[class crcl.base.MoveToType, class crcl.base.EnableGripperType, class crcl.base.WrenchType, interface java.util.List, class java.lang.Number, class java.lang.Double, class java.lang.Boolean, class java.lang.Integer, class java.lang.Long]


// class_index = 0 clss=class crcl.base.MoveToType

    namespace crcl{
        namespace base{
        
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

    // class_index = 1 clss=class crcl.base.EnableGripperType

        
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

    // class_index = 2 clss=class crcl.base.WrenchType

        
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


    // class_index = 3 clss=interface java.util.List

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


    // class_index = 4 clss=class java.lang.Number

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

    // class_index = 5 clss=class java.lang.Double

        
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

    // class_index = 6 clss=class java.lang.Boolean

        
        // get JNI handle for class java.lang.Boolean
        static inline jclass getBooleanClass();
        
        Boolean::Boolean(jobject _jthis, bool copy): Object(_jthis,copy) {
                
        }
        
        Boolean::Boolean(const Boolean &objref): Object((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class Boolean _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class Boolean jthis=",jthis);
            }
        }
        
        Boolean Boolean::cast(const Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getBooleanClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            Boolean retVal(objref.jthis,true);
            return retVal;
        }
        
        bool Boolean::instanceof(const Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getBooleanClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        Boolean::Boolean() : Object((jobject)NULL,false) {
        JNIEnv *env =getEnv();
        static jclass cls = getBooleanClass();
        if (cls != NULL) {
            static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
            if (NULL == mid) {
                std::cerr << "Class Boolean has no method constructor signature ()V" << std::endl;
            } else {
                jthis = env->NewObject(cls, mid );
                jthrowable t = env->ExceptionOccurred();
                if(t != NULL) {
                    if(GetDebugJ4Cpp()) {
                        DebugPrintJObject(__FILE__,__LINE__," Boolean::parseDouble jthis=",t);
                        env->ExceptionDescribe();
                    }
                    throw t;
                }
                if(jthis == NULL) {
                    std::cerr << "Call to create new Boolean with signature ()V returned null." << std::endl;
                    releaseEnv(env);
                    return;
                }
                jobjectRefType ref = env->GetObjectRefType(jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new Boolean jthis=",jthis);
                if(ref != JNIGlobalRefType) {
                    jthis = env->NewGlobalRef(jthis);
                }
            }
        }
        releaseEnv(env);
        }

        Boolean::Boolean(jboolean boolean_0) : Object((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getBooleanClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "(Z)V");
                if (NULL == mid) {
                    std::cerr << "Class Boolean has no method constructor signature (Z)V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid ,boolean_0);
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Boolean::parseDouble jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new Boolean with signature (Z)V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new Boolean jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }

        Boolean::Boolean(jstring string_0) : Object((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getBooleanClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "(Ljava/lang/String;)V");
                if (NULL == mid) {
                    std::cerr << "Class Boolean has no method constructor signature (Ljava/lang/String;)V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid ,string_0);
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Boolean::parseDouble jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new Boolean with signature (Ljava/lang/String;)V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new Boolean jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }

        Boolean::Boolean(const char * easyArg_0) : Object((jobject)NULL,false) {
        // Convenience Constructor converts common C++ types to JNI types
        JNIEnv *env =getEnv();
        static jclass cls = getBooleanClass();
        jstring string_0 = env->NewStringUTF(easyArg_0);
        
        if (cls != NULL) {
            static jmethodID mid = env->GetMethodID(cls, "<init>", "(Ljava/lang/String;)V");
            if (NULL == mid) {
                std::cerr << "Class Boolean has no method constructor signature (Ljava/lang/String;)V" << std::endl;
            } else {
                jthis = env->NewObject(cls, mid ,string_0);
                jthrowable t = env->ExceptionOccurred();
                if(t != NULL) {
                    if(GetDebugJ4Cpp()) {
                        DebugPrintJObject(__FILE__,__LINE__," Boolean::parseDouble jthis=",t);
                        env->ExceptionDescribe();
                    }
                    throw t;
                }
                if(jthis == NULL) {
                    std::cerr << "Call to create new Boolean with signature (Ljava/lang/String;)V returned null." << std::endl;
                    releaseEnv(env);
                    return;
                }
                jobjectRefType ref = env->GetObjectRefType(jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new Boolean jthis=",jthis);
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

        // Destructor for java.lang.Boolean
        Boolean::~Boolean() {
        	// Place-holder for later extensibility.
        }


        // Field getter for TRUE
        Boolean Boolean::getTRUE() {
        JNIEnv *env =getEnv();
        static jclass cls = getBooleanClass();
        jobject retVal=NULL;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "TRUE", "Ljava/lang/Boolean;");
            if (NULL == fid) {
                std::cerr << "Class java.lang.Boolean has no field named TRUE with signature Ljava/lang/Boolean;." << std::endl;
                static Boolean nullObject((jobject)NULL,false); return nullObject;
            } else {
                retVal= (jobject)  env->GetStaticObjectField( cls, fid );
            }
        }
        releaseEnv(env);
                    
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            Boolean retObject(retVal,false);
            return retObject;
        }

        // Field getter for FALSE
        Boolean Boolean::getFALSE() {
        JNIEnv *env =getEnv();
        static jclass cls = getBooleanClass();
        jobject retVal=NULL;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "FALSE", "Ljava/lang/Boolean;");
            if (NULL == fid) {
                std::cerr << "Class java.lang.Boolean has no field named FALSE with signature Ljava/lang/Boolean;." << std::endl;
                static Boolean nullObject((jobject)NULL,false); return nullObject;
            } else {
                retVal= (jobject)  env->GetStaticObjectField( cls, fid );
            }
        }
        releaseEnv(env);
                    
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            Boolean retObject(retVal,false);
            return retObject;
        }

        jboolean Boolean::equals(const Object & object_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method equals of java.lang.Boolean with jthis == NULL." << std::endl;
                return false;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Boolean::equals jthis=",jthis);
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "equals", "(Ljava/lang/Object;)Z");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Boolean::equals jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Boolean has no method named equals with signature (Ljava/lang/Object;)Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallBooleanMethod(jthis, mid ,object_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Boolean::equals jthrowable t=",t);
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

        jstring Boolean::toString() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method toString of java.lang.Boolean with jthis == NULL." << std::endl;
                return NULL;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Boolean::toString jthis=",jthis);
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "toString", "()Ljava/lang/String;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Boolean::toString jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Boolean has no method named toString with signature ()Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Boolean::toString jthrowable t=",t);
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

        jstring Boolean::toString(jboolean boolean_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getBooleanClass();
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "toString", "(Z)Ljava/lang/String;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Boolean has no method named toString with signature (Z)Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallStaticObjectMethod( cls, mid ,boolean_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Boolean::toString jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Boolean::hashCode(jboolean boolean_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getBooleanClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "hashCode", "(Z)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Boolean has no method named hashCode with signature (Z)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,boolean_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Boolean::hashCode jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Boolean::hashCode() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method hashCode of java.lang.Boolean with jthis == NULL." << std::endl;
                return (jint) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Boolean::hashCode jthis=",jthis);
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "hashCode", "()I");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Boolean::hashCode jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Boolean has no method named hashCode with signature ()I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallIntMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Boolean::hashCode jthrowable t=",t);
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

        jint Boolean::compareTo(const Boolean & boolean_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method compareTo of java.lang.Boolean with jthis == NULL." << std::endl;
                return (jint) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Boolean::compareTo jthis=",jthis);
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "compareTo", "(Ljava/lang/Boolean;)I");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Boolean::compareTo jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Boolean has no method named compareTo with signature (Ljava/lang/Boolean;)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallIntMethod(jthis, mid ,boolean_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Boolean::compareTo jthrowable t=",t);
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

        jboolean Boolean::getBoolean(jstring string_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getBooleanClass();
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "getBoolean", "(Ljava/lang/String;)Z");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Boolean has no method named getBoolean with signature (Ljava/lang/String;)Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallStaticBooleanMethod( cls, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Boolean::getBoolean jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jboolean Boolean::booleanValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method booleanValue of java.lang.Boolean with jthis == NULL." << std::endl;
                return false;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Boolean::booleanValue jthis=",jthis);
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "booleanValue", "()Z");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Boolean::booleanValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Boolean has no method named booleanValue with signature ()Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallBooleanMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Boolean::booleanValue jthrowable t=",t);
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

        Boolean Boolean::valueOf(jstring string_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getBooleanClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "valueOf", "(Ljava/lang/String;)Ljava/lang/Boolean;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Boolean has no method named valueOf with signature (Ljava/lang/String;)Ljava/lang/Boolean;." << std::endl;
                    static Boolean nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Boolean::valueOf jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            Boolean retObject(retVal,false);
            return retObject;
        }

        Boolean Boolean::valueOf(jboolean boolean_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getBooleanClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "valueOf", "(Z)Ljava/lang/Boolean;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Boolean has no method named valueOf with signature (Z)Ljava/lang/Boolean;." << std::endl;
                    static Boolean nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,boolean_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Boolean::valueOf jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            Boolean retObject(retVal,false);
            return retObject;
        }

        jint Boolean::compare(jboolean boolean_0,jboolean boolean_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getBooleanClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "compare", "(ZZ)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Boolean has no method named compare with signature (ZZ)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,boolean_0,boolean_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Boolean::compare jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jboolean Boolean::parseBoolean(jstring string_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getBooleanClass();
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "parseBoolean", "(Ljava/lang/String;)Z");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Boolean has no method named parseBoolean with signature (Ljava/lang/String;)Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallStaticBooleanMethod( cls, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Boolean::parseBoolean jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jboolean Boolean::logicalAnd(jboolean boolean_0,jboolean boolean_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getBooleanClass();
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "logicalAnd", "(ZZ)Z");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Boolean has no method named logicalAnd with signature (ZZ)Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallStaticBooleanMethod( cls, mid ,boolean_0,boolean_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Boolean::logicalAnd jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jboolean Boolean::logicalOr(jboolean boolean_0,jboolean boolean_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getBooleanClass();
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "logicalOr", "(ZZ)Z");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Boolean has no method named logicalOr with signature (ZZ)Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallStaticBooleanMethod( cls, mid ,boolean_0,boolean_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Boolean::logicalOr jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jboolean Boolean::logicalXor(jboolean boolean_0,jboolean boolean_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getBooleanClass();
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "logicalXor", "(ZZ)Z");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Boolean has no method named logicalXor with signature (ZZ)Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallStaticBooleanMethod( cls, mid ,boolean_0,boolean_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Boolean::logicalXor jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }
        static jclass getNewBooleanClass() {
            jclass clss = getEnv()->FindClass("java/lang/Boolean");
            if (NULL == clss) {
                std::cerr << " Can't find class java/lang/Boolean" << std::endl;
            }
            return clss;
        }
        
        static jclass BooleanClass = NULL;
        static inline jclass getBooleanClass() {
            if (BooleanClass != NULL) {
                return BooleanClass;
            }
            BooleanClass = getNewBooleanClass();
            return BooleanClass;
        }

    // class_index = 7 clss=class java.lang.Integer

        
        // get JNI handle for class java.lang.Integer
        static inline jclass getIntegerClass();
        
        Integer::Integer(jobject _jthis, bool copy): Number(_jthis,copy) {
                
        }
        
        Integer::Integer(const Integer &objref): Number((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class Integer _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class Integer jthis=",jthis);
            }
        }
        
        Integer Integer::cast(const Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            Integer retVal(objref.jthis,true);
            return retVal;
        }
        
        bool Integer::instanceof(const Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        Integer::Integer() : Number((jobject)NULL,false) {
        JNIEnv *env =getEnv();
        static jclass cls = getIntegerClass();
        if (cls != NULL) {
            static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
            if (NULL == mid) {
                std::cerr << "Class Integer has no method constructor signature ()V" << std::endl;
            } else {
                jthis = env->NewObject(cls, mid );
                jthrowable t = env->ExceptionOccurred();
                if(t != NULL) {
                    if(GetDebugJ4Cpp()) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::logicalXor jthis=",t);
                        env->ExceptionDescribe();
                    }
                    throw t;
                }
                if(jthis == NULL) {
                    std::cerr << "Call to create new Integer with signature ()V returned null." << std::endl;
                    releaseEnv(env);
                    return;
                }
                jobjectRefType ref = env->GetObjectRefType(jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new Integer jthis=",jthis);
                if(ref != JNIGlobalRefType) {
                    jthis = env->NewGlobalRef(jthis);
                }
            }
        }
        releaseEnv(env);
        }

        Integer::Integer(jstring string_0) : Number((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "(Ljava/lang/String;)V");
                if (NULL == mid) {
                    std::cerr << "Class Integer has no method constructor signature (Ljava/lang/String;)V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid ,string_0);
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Integer::logicalXor jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new Integer with signature (Ljava/lang/String;)V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new Integer jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }

        Integer::Integer(const char * easyArg_0) : Number((jobject)NULL,false) {
        // Convenience Constructor converts common C++ types to JNI types
        JNIEnv *env =getEnv();
        static jclass cls = getIntegerClass();
        jstring string_0 = env->NewStringUTF(easyArg_0);
        
        if (cls != NULL) {
            static jmethodID mid = env->GetMethodID(cls, "<init>", "(Ljava/lang/String;)V");
            if (NULL == mid) {
                std::cerr << "Class Integer has no method constructor signature (Ljava/lang/String;)V" << std::endl;
            } else {
                jthis = env->NewObject(cls, mid ,string_0);
                jthrowable t = env->ExceptionOccurred();
                if(t != NULL) {
                    if(GetDebugJ4Cpp()) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::logicalXor jthis=",t);
                        env->ExceptionDescribe();
                    }
                    throw t;
                }
                if(jthis == NULL) {
                    std::cerr << "Call to create new Integer with signature (Ljava/lang/String;)V returned null." << std::endl;
                    releaseEnv(env);
                    return;
                }
                jobjectRefType ref = env->GetObjectRefType(jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new Integer jthis=",jthis);
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
        Integer::Integer(jint int_0) : Number((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "(I)V");
                if (NULL == mid) {
                    std::cerr << "Class Integer has no method constructor signature (I)V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid ,int_0);
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Integer::logicalXor jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new Integer with signature (I)V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new Integer jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for java.lang.Integer
        Integer::~Integer() {
        	// Place-holder for later extensibility.
        }


        // Field getter for MIN_VALUE
        jint Integer::getMIN_VALUE() {
        JNIEnv *env =getEnv();
        static jclass cls = getIntegerClass();
        jint retVal= (jint) -1;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "MIN_VALUE", "I");
            if (NULL == fid) {
                std::cerr << "Class java.lang.Integer has no field named MIN_VALUE with signature I." << std::endl;
                return (jint) -1;
            } else {
                retVal= (jint)  env->GetStaticIntField( cls, fid );
            }
        }
        releaseEnv(env);
        return retVal;
        }

        // Field getter for MAX_VALUE
        jint Integer::getMAX_VALUE() {
        JNIEnv *env =getEnv();
        static jclass cls = getIntegerClass();
        jint retVal= (jint) -1;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "MAX_VALUE", "I");
            if (NULL == fid) {
                std::cerr << "Class java.lang.Integer has no field named MAX_VALUE with signature I." << std::endl;
                return (jint) -1;
            } else {
                retVal= (jint)  env->GetStaticIntField( cls, fid );
            }
        }
        releaseEnv(env);
        return retVal;
        }

        // Field getter for SIZE
        jint Integer::getSIZE() {
        JNIEnv *env =getEnv();
        static jclass cls = getIntegerClass();
        jint retVal= (jint) -1;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "SIZE", "I");
            if (NULL == fid) {
                std::cerr << "Class java.lang.Integer has no field named SIZE with signature I." << std::endl;
                return (jint) -1;
            } else {
                retVal= (jint)  env->GetStaticIntField( cls, fid );
            }
        }
        releaseEnv(env);
        return retVal;
        }

        // Field getter for BYTES
        jint Integer::getBYTES() {
        JNIEnv *env =getEnv();
        static jclass cls = getIntegerClass();
        jint retVal= (jint) -1;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "BYTES", "I");
            if (NULL == fid) {
                std::cerr << "Class java.lang.Integer has no field named BYTES with signature I." << std::endl;
                return (jint) -1;
            } else {
                retVal= (jint)  env->GetStaticIntField( cls, fid );
            }
        }
        releaseEnv(env);
        return retVal;
        }

        jint Integer::numberOfLeadingZeros(jint int_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "numberOfLeadingZeros", "(I)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named numberOfLeadingZeros with signature (I)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,int_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::numberOfLeadingZeros jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Integer::numberOfTrailingZeros(jint int_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "numberOfTrailingZeros", "(I)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named numberOfTrailingZeros with signature (I)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,int_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::numberOfTrailingZeros jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Integer::bitCount(jint int_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "bitCount", "(I)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named bitCount with signature (I)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,int_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::bitCount jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jboolean Integer::equals(const Object & object_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method equals of java.lang.Integer with jthis == NULL." << std::endl;
                return false;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Integer::equals jthis=",jthis);
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "equals", "(Ljava/lang/Object;)Z");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Integer::equals jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Integer has no method named equals with signature (Ljava/lang/Object;)Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallBooleanMethod(jthis, mid ,object_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Integer::equals jthrowable t=",t);
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

        jstring Integer::toString(jint int_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "toString", "(I)Ljava/lang/String;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named toString with signature (I)Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallStaticObjectMethod( cls, mid ,int_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::toString jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jstring Integer::toString(jint int_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "toString", "(II)Ljava/lang/String;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named toString with signature (II)Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallStaticObjectMethod( cls, mid ,int_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::toString jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jstring Integer::toString() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method toString of java.lang.Integer with jthis == NULL." << std::endl;
                return NULL;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Integer::toString jthis=",jthis);
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "toString", "()Ljava/lang/String;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Integer::toString jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Integer has no method named toString with signature ()Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Integer::toString jthrowable t=",t);
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

        jint Integer::hashCode(jint int_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "hashCode", "(I)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named hashCode with signature (I)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,int_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::hashCode jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Integer::hashCode() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method hashCode of java.lang.Integer with jthis == NULL." << std::endl;
                return (jint) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Integer::hashCode jthis=",jthis);
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "hashCode", "()I");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Integer::hashCode jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Integer has no method named hashCode with signature ()I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallIntMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Integer::hashCode jthrowable t=",t);
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

        jint Integer::min(jint int_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "min", "(II)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named min with signature (II)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,int_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::min jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Integer::max(jint int_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "max", "(II)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named max with signature (II)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,int_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::max jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Integer::reverseBytes(jint int_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "reverseBytes", "(I)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named reverseBytes with signature (I)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,int_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::reverseBytes jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Integer::compareTo(const Integer & integer_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method compareTo of java.lang.Integer with jthis == NULL." << std::endl;
                return (jint) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Integer::compareTo jthis=",jthis);
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "compareTo", "(Ljava/lang/Integer;)I");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Integer::compareTo jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Integer has no method named compareTo with signature (Ljava/lang/Integer;)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallIntMethod(jthis, mid ,integer_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Integer::compareTo jthrowable t=",t);
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

        jbyte Integer::byteValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method byteValue of java.lang.Integer with jthis == NULL." << std::endl;
                return (jbyte) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Integer::byteValue jthis=",jthis);
            jbyte retVal= (jbyte) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "byteValue", "()B");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Integer::byteValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Integer has no method named byteValue with signature ()B." << std::endl;
                    return (jbyte) -1;
                } else {
                    retVal= (jbyte)  env->CallByteMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Integer::byteValue jthrowable t=",t);
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

        jshort Integer::shortValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method shortValue of java.lang.Integer with jthis == NULL." << std::endl;
                return (jshort) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Integer::shortValue jthis=",jthis);
            jshort retVal=(jshort) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "shortValue", "()S");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Integer::shortValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Integer has no method named shortValue with signature ()S." << std::endl;
                    return (jshort) -1;
                } else {
                    retVal= (jshort)  env->CallShortMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Integer::shortValue jthrowable t=",t);
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

        jint Integer::intValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method intValue of java.lang.Integer with jthis == NULL." << std::endl;
                return (jint) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Integer::intValue jthis=",jthis);
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "intValue", "()I");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Integer::intValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Integer has no method named intValue with signature ()I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallIntMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Integer::intValue jthrowable t=",t);
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

        jlong Integer::longValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method longValue of java.lang.Integer with jthis == NULL." << std::endl;
                return (jlong) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Integer::longValue jthis=",jthis);
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "longValue", "()J");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Integer::longValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Integer has no method named longValue with signature ()J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallLongMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Integer::longValue jthrowable t=",t);
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

        jfloat Integer::floatValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method floatValue of java.lang.Integer with jthis == NULL." << std::endl;
                return (jfloat) -1.0;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Integer::floatValue jthis=",jthis);
            jfloat retVal= (jfloat) -1.0;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "floatValue", "()F");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Integer::floatValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Integer has no method named floatValue with signature ()F." << std::endl;
                    return (jfloat) -1.0;
                } else {
                    retVal= (jfloat)  env->CallFloatMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Integer::floatValue jthrowable t=",t);
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

        jdouble Integer::doubleValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method doubleValue of java.lang.Integer with jthis == NULL." << std::endl;
                return (jdouble) -1.0;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Integer::doubleValue jthis=",jthis);
            jdouble retVal= (jdouble) -1.0;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "doubleValue", "()D");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Integer::doubleValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Integer has no method named doubleValue with signature ()D." << std::endl;
                    return (jdouble) -1.0;
                } else {
                    retVal= (jdouble)  env->CallDoubleMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Integer::doubleValue jthrowable t=",t);
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

        Integer Integer::valueOf(jint int_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "valueOf", "(I)Ljava/lang/Integer;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named valueOf with signature (I)Ljava/lang/Integer;." << std::endl;
                    static Integer nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,int_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::valueOf jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            Integer retObject(retVal,false);
            return retObject;
        }

        Integer Integer::valueOf(jstring string_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "valueOf", "(Ljava/lang/String;I)Ljava/lang/Integer;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named valueOf with signature (Ljava/lang/String;I)Ljava/lang/Integer;." << std::endl;
                    static Integer nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,string_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::valueOf jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            Integer retObject(retVal,false);
            return retObject;
        }

        Integer Integer::valueOf(jstring string_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "valueOf", "(Ljava/lang/String;)Ljava/lang/Integer;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named valueOf with signature (Ljava/lang/String;)Ljava/lang/Integer;." << std::endl;
                    static Integer nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::valueOf jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            Integer retObject(retVal,false);
            return retObject;
        }

        jstring Integer::toHexString(jint int_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "toHexString", "(I)Ljava/lang/String;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named toHexString with signature (I)Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallStaticObjectMethod( cls, mid ,int_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::toHexString jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        Integer Integer::decode(jstring string_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "decode", "(Ljava/lang/String;)Ljava/lang/Integer;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named decode with signature (Ljava/lang/String;)Ljava/lang/Integer;." << std::endl;
                    static Integer nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::decode jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            Integer retObject(retVal,false);
            return retObject;
        }

        jint Integer::compare(jint int_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "compare", "(II)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named compare with signature (II)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,int_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::compare jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Integer::reverse(jint int_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "reverse", "(I)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named reverse with signature (I)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,int_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::reverse jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jlong Integer::toUnsignedLong(jint int_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "toUnsignedLong", "(I)J");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named toUnsignedLong with signature (I)J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallStaticLongMethod( cls, mid ,int_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::toUnsignedLong jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Integer::parseInt(jstring string_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "parseInt", "(Ljava/lang/String;)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named parseInt with signature (Ljava/lang/String;)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::parseInt jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Integer::parseInt(jstring string_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "parseInt", "(Ljava/lang/String;I)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named parseInt with signature (Ljava/lang/String;I)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,string_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::parseInt jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Integer::sum(jint int_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "sum", "(II)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named sum with signature (II)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,int_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::sum jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Integer::compareUnsigned(jint int_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "compareUnsigned", "(II)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named compareUnsigned with signature (II)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,int_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::compareUnsigned jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jstring Integer::toUnsignedString(jint int_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "toUnsignedString", "(II)Ljava/lang/String;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named toUnsignedString with signature (II)Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallStaticObjectMethod( cls, mid ,int_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::toUnsignedString jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jstring Integer::toUnsignedString(jint int_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "toUnsignedString", "(I)Ljava/lang/String;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named toUnsignedString with signature (I)Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallStaticObjectMethod( cls, mid ,int_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::toUnsignedString jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jstring Integer::toOctalString(jint int_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "toOctalString", "(I)Ljava/lang/String;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named toOctalString with signature (I)Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallStaticObjectMethod( cls, mid ,int_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::toOctalString jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jstring Integer::toBinaryString(jint int_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "toBinaryString", "(I)Ljava/lang/String;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named toBinaryString with signature (I)Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallStaticObjectMethod( cls, mid ,int_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::toBinaryString jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Integer::parseUnsignedInt(jstring string_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "parseUnsignedInt", "(Ljava/lang/String;I)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named parseUnsignedInt with signature (Ljava/lang/String;I)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,string_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::parseUnsignedInt jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Integer::parseUnsignedInt(jstring string_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "parseUnsignedInt", "(Ljava/lang/String;)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named parseUnsignedInt with signature (Ljava/lang/String;)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::parseUnsignedInt jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        Integer Integer::getInteger(jstring string_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "getInteger", "(Ljava/lang/String;I)Ljava/lang/Integer;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named getInteger with signature (Ljava/lang/String;I)Ljava/lang/Integer;." << std::endl;
                    static Integer nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,string_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::getInteger jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            Integer retObject(retVal,false);
            return retObject;
        }

        Integer Integer::getInteger(jstring string_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "getInteger", "(Ljava/lang/String;)Ljava/lang/Integer;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named getInteger with signature (Ljava/lang/String;)Ljava/lang/Integer;." << std::endl;
                    static Integer nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::getInteger jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            Integer retObject(retVal,false);
            return retObject;
        }

        Integer Integer::getInteger(jstring string_0,const Integer & integer_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "getInteger", "(Ljava/lang/String;Ljava/lang/Integer;)Ljava/lang/Integer;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named getInteger with signature (Ljava/lang/String;Ljava/lang/Integer;)Ljava/lang/Integer;." << std::endl;
                    static Integer nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,string_0,integer_1.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::getInteger jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            Integer retObject(retVal,false);
            return retObject;
        }

        jint Integer::divideUnsigned(jint int_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "divideUnsigned", "(II)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named divideUnsigned with signature (II)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,int_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::divideUnsigned jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Integer::remainderUnsigned(jint int_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "remainderUnsigned", "(II)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named remainderUnsigned with signature (II)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,int_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::remainderUnsigned jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Integer::highestOneBit(jint int_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "highestOneBit", "(I)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named highestOneBit with signature (I)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,int_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::highestOneBit jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Integer::lowestOneBit(jint int_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "lowestOneBit", "(I)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named lowestOneBit with signature (I)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,int_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::lowestOneBit jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Integer::rotateLeft(jint int_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "rotateLeft", "(II)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named rotateLeft with signature (II)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,int_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::rotateLeft jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Integer::rotateRight(jint int_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "rotateRight", "(II)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named rotateRight with signature (II)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,int_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::rotateRight jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Integer::signum(jint int_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getIntegerClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "signum", "(I)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Integer has no method named signum with signature (I)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,int_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Integer::signum jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }
        static jclass getNewIntegerClass() {
            jclass clss = getEnv()->FindClass("java/lang/Integer");
            if (NULL == clss) {
                std::cerr << " Can't find class java/lang/Integer" << std::endl;
            }
            return clss;
        }
        
        static jclass IntegerClass = NULL;
        static inline jclass getIntegerClass() {
            if (IntegerClass != NULL) {
                return IntegerClass;
            }
            IntegerClass = getNewIntegerClass();
            return IntegerClass;
        }

    // class_index = 8 clss=class java.lang.Long

        
        // get JNI handle for class java.lang.Long
        static inline jclass getLongClass();
        
        Long::Long(jobject _jthis, bool copy): Number(_jthis,copy) {
                
        }
        
        Long::Long(const Long &objref): Number((jobject)NULL,false) {
            
            jobject _jthis = objref.jthis;
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class Long _jthis=",_jthis);
            if (_jthis != NULL) {
                jthis = getEnv()->NewGlobalRef(_jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"Copy Constructor for class Long jthis=",jthis);
            }
        }
        
        Long Long::cast(const Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            if(!env->IsAssignableFrom(objcls,cls)) {
                throw objcls;
            }
            Long retVal(objref.jthis,true);
            return retVal;
        }
        
        bool Long::instanceof(const Object &objref) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass(); 
            jclass objcls = env->GetObjectClass(objref.jthis);
            return (JNI_TRUE == env->IsAssignableFrom(objcls,cls));
        }
        Long::Long() : Number((jobject)NULL,false) {
        JNIEnv *env =getEnv();
        static jclass cls = getLongClass();
        if (cls != NULL) {
            static jmethodID mid = env->GetMethodID(cls, "<init>", "()V");
            if (NULL == mid) {
                std::cerr << "Class Long has no method constructor signature ()V" << std::endl;
            } else {
                jthis = env->NewObject(cls, mid );
                jthrowable t = env->ExceptionOccurred();
                if(t != NULL) {
                    if(GetDebugJ4Cpp()) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::signum jthis=",t);
                        env->ExceptionDescribe();
                    }
                    throw t;
                }
                if(jthis == NULL) {
                    std::cerr << "Call to create new Long with signature ()V returned null." << std::endl;
                    releaseEnv(env);
                    return;
                }
                jobjectRefType ref = env->GetObjectRefType(jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new Long jthis=",jthis);
                if(ref != JNIGlobalRefType) {
                    jthis = env->NewGlobalRef(jthis);
                }
            }
        }
        releaseEnv(env);
        }

        Long::Long(jstring string_0) : Number((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "(Ljava/lang/String;)V");
                if (NULL == mid) {
                    std::cerr << "Class Long has no method constructor signature (Ljava/lang/String;)V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid ,string_0);
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Long::signum jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new Long with signature (Ljava/lang/String;)V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new Long jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }

        Long::Long(const char * easyArg_0) : Number((jobject)NULL,false) {
        // Convenience Constructor converts common C++ types to JNI types
        JNIEnv *env =getEnv();
        static jclass cls = getLongClass();
        jstring string_0 = env->NewStringUTF(easyArg_0);
        
        if (cls != NULL) {
            static jmethodID mid = env->GetMethodID(cls, "<init>", "(Ljava/lang/String;)V");
            if (NULL == mid) {
                std::cerr << "Class Long has no method constructor signature (Ljava/lang/String;)V" << std::endl;
            } else {
                jthis = env->NewObject(cls, mid ,string_0);
                jthrowable t = env->ExceptionOccurred();
                if(t != NULL) {
                    if(GetDebugJ4Cpp()) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::signum jthis=",t);
                        env->ExceptionDescribe();
                    }
                    throw t;
                }
                if(jthis == NULL) {
                    std::cerr << "Call to create new Long with signature (Ljava/lang/String;)V returned null." << std::endl;
                    releaseEnv(env);
                    return;
                }
                jobjectRefType ref = env->GetObjectRefType(jthis);
                if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new Long jthis=",jthis);
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
        Long::Long(jlong long_0) : Number((jobject)NULL,false) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "<init>", "(J)V");
                if (NULL == mid) {
                    std::cerr << "Class Long has no method constructor signature (J)V" << std::endl;
                } else {
                    jthis = env->NewObject(cls, mid ,long_0);
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Long::signum jthis=",t);
                            env->ExceptionDescribe();
                        }
                        throw t;
                    }
                    if(jthis == NULL) {
                        std::cerr << "Call to create new Long with signature (J)V returned null." << std::endl;
                        releaseEnv(env);
                        return;
                    }
                    jobjectRefType ref = env->GetObjectRefType(jthis);
                    if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," new Long jthis=",jthis);
                    if(ref != JNIGlobalRefType) {
                        jthis = env->NewGlobalRef(jthis);
                    }
                }
            }
            releaseEnv(env);
        }


        // Destructor for java.lang.Long
        Long::~Long() {
        	// Place-holder for later extensibility.
        }


        // Field getter for MIN_VALUE
        jlong Long::getMIN_VALUE() {
        JNIEnv *env =getEnv();
        static jclass cls = getLongClass();
        jlong retVal= (jlong) -1;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "MIN_VALUE", "J");
            if (NULL == fid) {
                std::cerr << "Class java.lang.Long has no field named MIN_VALUE with signature J." << std::endl;
                return (jlong) -1;
            } else {
                retVal= (jlong)  env->GetStaticLongField( cls, fid );
            }
        }
        releaseEnv(env);
        return retVal;
        }

        // Field getter for MAX_VALUE
        jlong Long::getMAX_VALUE() {
        JNIEnv *env =getEnv();
        static jclass cls = getLongClass();
        jlong retVal= (jlong) -1;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "MAX_VALUE", "J");
            if (NULL == fid) {
                std::cerr << "Class java.lang.Long has no field named MAX_VALUE with signature J." << std::endl;
                return (jlong) -1;
            } else {
                retVal= (jlong)  env->GetStaticLongField( cls, fid );
            }
        }
        releaseEnv(env);
        return retVal;
        }

        // Field getter for SIZE
        jint Long::getSIZE() {
        JNIEnv *env =getEnv();
        static jclass cls = getLongClass();
        jint retVal= (jint) -1;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "SIZE", "I");
            if (NULL == fid) {
                std::cerr << "Class java.lang.Long has no field named SIZE with signature I." << std::endl;
                return (jint) -1;
            } else {
                retVal= (jint)  env->GetStaticIntField( cls, fid );
            }
        }
        releaseEnv(env);
        return retVal;
        }

        // Field getter for BYTES
        jint Long::getBYTES() {
        JNIEnv *env =getEnv();
        static jclass cls = getLongClass();
        jint retVal= (jint) -1;
        if (cls != NULL) {
            static jfieldID fid = env->GetStaticFieldID(cls, "BYTES", "I");
            if (NULL == fid) {
                std::cerr << "Class java.lang.Long has no field named BYTES with signature I." << std::endl;
                return (jint) -1;
            } else {
                retVal= (jint)  env->GetStaticIntField( cls, fid );
            }
        }
        releaseEnv(env);
        return retVal;
        }

        jint Long::numberOfLeadingZeros(jlong long_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "numberOfLeadingZeros", "(J)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named numberOfLeadingZeros with signature (J)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,long_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::numberOfLeadingZeros jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Long::numberOfTrailingZeros(jlong long_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "numberOfTrailingZeros", "(J)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named numberOfTrailingZeros with signature (J)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,long_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::numberOfTrailingZeros jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Long::bitCount(jlong long_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "bitCount", "(J)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named bitCount with signature (J)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,long_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::bitCount jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jboolean Long::equals(const Object & object_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method equals of java.lang.Long with jthis == NULL." << std::endl;
                return false;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Long::equals jthis=",jthis);
            jboolean retVal=false;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "equals", "(Ljava/lang/Object;)Z");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Long::equals jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Long has no method named equals with signature (Ljava/lang/Object;)Z." << std::endl;
                    return false;
                } else {
                    retVal= (jboolean)  env->CallBooleanMethod(jthis, mid ,object_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Long::equals jthrowable t=",t);
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

        jstring Long::toString(jlong long_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "toString", "(J)Ljava/lang/String;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named toString with signature (J)Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallStaticObjectMethod( cls, mid ,long_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::toString jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jstring Long::toString(jlong long_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "toString", "(JI)Ljava/lang/String;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named toString with signature (JI)Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallStaticObjectMethod( cls, mid ,long_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::toString jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jstring Long::toString() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method toString of java.lang.Long with jthis == NULL." << std::endl;
                return NULL;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Long::toString jthis=",jthis);
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "toString", "()Ljava/lang/String;");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Long::toString jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Long has no method named toString with signature ()Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallObjectMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Long::toString jthrowable t=",t);
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

        jint Long::hashCode(jlong long_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "hashCode", "(J)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named hashCode with signature (J)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,long_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::hashCode jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Long::hashCode() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method hashCode of java.lang.Long with jthis == NULL." << std::endl;
                return (jint) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Long::hashCode jthis=",jthis);
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "hashCode", "()I");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Long::hashCode jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Long has no method named hashCode with signature ()I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallIntMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Long::hashCode jthrowable t=",t);
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

        jlong Long::min(jlong long_0,jlong long_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "min", "(JJ)J");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named min with signature (JJ)J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallStaticLongMethod( cls, mid ,long_0,long_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::min jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jlong Long::max(jlong long_0,jlong long_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "max", "(JJ)J");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named max with signature (JJ)J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallStaticLongMethod( cls, mid ,long_0,long_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::max jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jlong Long::reverseBytes(jlong long_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "reverseBytes", "(J)J");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named reverseBytes with signature (J)J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallStaticLongMethod( cls, mid ,long_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::reverseBytes jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Long::compareTo(const Long & long_0) {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method compareTo of java.lang.Long with jthis == NULL." << std::endl;
                return (jint) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Long::compareTo jthis=",jthis);
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "compareTo", "(Ljava/lang/Long;)I");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Long::compareTo jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Long has no method named compareTo with signature (Ljava/lang/Long;)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallIntMethod(jthis, mid ,long_0.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Long::compareTo jthrowable t=",t);
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

        Long Long::getLong(jstring string_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "getLong", "(Ljava/lang/String;)Ljava/lang/Long;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named getLong with signature (Ljava/lang/String;)Ljava/lang/Long;." << std::endl;
                    static Long nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::getLong jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            Long retObject(retVal,false);
            return retObject;
        }

        Long Long::getLong(jstring string_0,jlong long_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "getLong", "(Ljava/lang/String;J)Ljava/lang/Long;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named getLong with signature (Ljava/lang/String;J)Ljava/lang/Long;." << std::endl;
                    static Long nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,string_0,long_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::getLong jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            Long retObject(retVal,false);
            return retObject;
        }

        Long Long::getLong(jstring string_0,const Long & long_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "getLong", "(Ljava/lang/String;Ljava/lang/Long;)Ljava/lang/Long;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named getLong with signature (Ljava/lang/String;Ljava/lang/Long;)Ljava/lang/Long;." << std::endl;
                    static Long nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,string_0,long_1.jthis );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::getLong jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            Long retObject(retVal,false);
            return retObject;
        }

        jbyte Long::byteValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method byteValue of java.lang.Long with jthis == NULL." << std::endl;
                return (jbyte) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Long::byteValue jthis=",jthis);
            jbyte retVal= (jbyte) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "byteValue", "()B");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Long::byteValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Long has no method named byteValue with signature ()B." << std::endl;
                    return (jbyte) -1;
                } else {
                    retVal= (jbyte)  env->CallByteMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Long::byteValue jthrowable t=",t);
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

        jshort Long::shortValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method shortValue of java.lang.Long with jthis == NULL." << std::endl;
                return (jshort) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Long::shortValue jthis=",jthis);
            jshort retVal=(jshort) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "shortValue", "()S");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Long::shortValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Long has no method named shortValue with signature ()S." << std::endl;
                    return (jshort) -1;
                } else {
                    retVal= (jshort)  env->CallShortMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Long::shortValue jthrowable t=",t);
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

        jint Long::intValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method intValue of java.lang.Long with jthis == NULL." << std::endl;
                return (jint) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Long::intValue jthis=",jthis);
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "intValue", "()I");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Long::intValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Long has no method named intValue with signature ()I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallIntMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Long::intValue jthrowable t=",t);
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

        jlong Long::longValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method longValue of java.lang.Long with jthis == NULL." << std::endl;
                return (jlong) -1;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Long::longValue jthis=",jthis);
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "longValue", "()J");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Long::longValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Long has no method named longValue with signature ()J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallLongMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Long::longValue jthrowable t=",t);
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

        jfloat Long::floatValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method floatValue of java.lang.Long with jthis == NULL." << std::endl;
                return (jfloat) -1.0;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Long::floatValue jthis=",jthis);
            jfloat retVal= (jfloat) -1.0;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "floatValue", "()F");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Long::floatValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Long has no method named floatValue with signature ()F." << std::endl;
                    return (jfloat) -1.0;
                } else {
                    retVal= (jfloat)  env->CallFloatMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Long::floatValue jthrowable t=",t);
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

        jdouble Long::doubleValue() {
            if(jthis == NULL) {
                std::cerr << __FILE__ << ":" << __LINE__ <<" Call of method doubleValue of java.lang.Long with jthis == NULL." << std::endl;
                return (jdouble) -1.0;
            }
            JNIEnv *env =getEnv();
            jclass cls = env->GetObjectClass(jthis);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__," Long::doubleValue jthis=",jthis);
            jdouble retVal= (jdouble) -1.0;
            if (cls != NULL) {
                static jmethodID mid = env->GetMethodID(cls, "doubleValue", "()D");
                if (NULL == mid) {
                    DebugPrintJObject(__FILE__,__LINE__," Long::doubleValue jthis=",jthis);
                    std::cerr << __FILE__ << ":" << __LINE__ <<  " Class java.lang.Long has no method named doubleValue with signature ()D." << std::endl;
                    return (jdouble) -1.0;
                } else {
                    retVal= (jdouble)  env->CallDoubleMethod(jthis, mid  );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        if(GetDebugJ4Cpp()) {
                            DebugPrintJObject(__FILE__,__LINE__," Long::doubleValue jthrowable t=",t);
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

        Long Long::valueOf(jstring string_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "valueOf", "(Ljava/lang/String;I)Ljava/lang/Long;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named valueOf with signature (Ljava/lang/String;I)Ljava/lang/Long;." << std::endl;
                    static Long nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,string_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::valueOf jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            Long retObject(retVal,false);
            return retObject;
        }

        Long Long::valueOf(jstring string_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "valueOf", "(Ljava/lang/String;)Ljava/lang/Long;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named valueOf with signature (Ljava/lang/String;)Ljava/lang/Long;." << std::endl;
                    static Long nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::valueOf jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            Long retObject(retVal,false);
            return retObject;
        }

        Long Long::valueOf(jlong long_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "valueOf", "(J)Ljava/lang/Long;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named valueOf with signature (J)Ljava/lang/Long;." << std::endl;
                    static Long nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,long_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::valueOf jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            Long retObject(retVal,false);
            return retObject;
        }

        jstring Long::toHexString(jlong long_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "toHexString", "(J)Ljava/lang/String;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named toHexString with signature (J)Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallStaticObjectMethod( cls, mid ,long_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::toHexString jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        Long Long::decode(jstring string_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jobject retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "decode", "(Ljava/lang/String;)Ljava/lang/Long;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named decode with signature (Ljava/lang/String;)Ljava/lang/Long;." << std::endl;
                    static Long nullObject((jobject)NULL,false); return nullObject;
                } else {
                    retVal= (jobject)  env->CallStaticObjectMethod( cls, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::decode jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
                        
            jobjectRefType ref = env->GetObjectRefType(retVal);
            if(GetDebugJ4Cpp()) DebugPrintJObject(__FILE__,__LINE__,"retVal=",retVal);            Long retObject(retVal,false);
            return retObject;
        }

        jint Long::compare(jlong long_0,jlong long_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "compare", "(JJ)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named compare with signature (JJ)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,long_0,long_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::compare jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jlong Long::reverse(jlong long_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "reverse", "(J)J");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named reverse with signature (J)J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallStaticLongMethod( cls, mid ,long_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::reverse jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jlong Long::sum(jlong long_0,jlong long_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "sum", "(JJ)J");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named sum with signature (JJ)J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallStaticLongMethod( cls, mid ,long_0,long_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::sum jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Long::compareUnsigned(jlong long_0,jlong long_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "compareUnsigned", "(JJ)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named compareUnsigned with signature (JJ)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,long_0,long_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::compareUnsigned jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jstring Long::toUnsignedString(jlong long_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "toUnsignedString", "(J)Ljava/lang/String;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named toUnsignedString with signature (J)Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallStaticObjectMethod( cls, mid ,long_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::toUnsignedString jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jstring Long::toUnsignedString(jlong long_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "toUnsignedString", "(JI)Ljava/lang/String;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named toUnsignedString with signature (JI)Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallStaticObjectMethod( cls, mid ,long_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::toUnsignedString jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jstring Long::toOctalString(jlong long_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "toOctalString", "(J)Ljava/lang/String;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named toOctalString with signature (J)Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallStaticObjectMethod( cls, mid ,long_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::toOctalString jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jstring Long::toBinaryString(jlong long_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jstring retVal=NULL;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "toBinaryString", "(J)Ljava/lang/String;");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named toBinaryString with signature (J)Ljava/lang/String;." << std::endl;
                    return NULL;
                } else {
                    retVal= (jstring)  env->CallStaticObjectMethod( cls, mid ,long_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::toBinaryString jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jlong Long::divideUnsigned(jlong long_0,jlong long_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "divideUnsigned", "(JJ)J");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named divideUnsigned with signature (JJ)J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallStaticLongMethod( cls, mid ,long_0,long_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::divideUnsigned jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jlong Long::remainderUnsigned(jlong long_0,jlong long_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "remainderUnsigned", "(JJ)J");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named remainderUnsigned with signature (JJ)J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallStaticLongMethod( cls, mid ,long_0,long_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::remainderUnsigned jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jlong Long::highestOneBit(jlong long_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "highestOneBit", "(J)J");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named highestOneBit with signature (J)J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallStaticLongMethod( cls, mid ,long_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::highestOneBit jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jlong Long::lowestOneBit(jlong long_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "lowestOneBit", "(J)J");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named lowestOneBit with signature (J)J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallStaticLongMethod( cls, mid ,long_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::lowestOneBit jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jlong Long::rotateLeft(jlong long_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "rotateLeft", "(JI)J");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named rotateLeft with signature (JI)J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallStaticLongMethod( cls, mid ,long_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::rotateLeft jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jlong Long::rotateRight(jlong long_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "rotateRight", "(JI)J");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named rotateRight with signature (JI)J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallStaticLongMethod( cls, mid ,long_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::rotateRight jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jint Long::signum(jlong long_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jint retVal= (jint) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "signum", "(J)I");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named signum with signature (J)I." << std::endl;
                    return (jint) -1;
                } else {
                    retVal= (jint)  env->CallStaticIntMethod( cls, mid ,long_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::signum jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jlong Long::parseLong(jstring string_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "parseLong", "(Ljava/lang/String;)J");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named parseLong with signature (Ljava/lang/String;)J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallStaticLongMethod( cls, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::parseLong jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jlong Long::parseLong(jstring string_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "parseLong", "(Ljava/lang/String;I)J");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named parseLong with signature (Ljava/lang/String;I)J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallStaticLongMethod( cls, mid ,string_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::parseLong jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jlong Long::parseUnsignedLong(jstring string_0,jint int_1) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "parseUnsignedLong", "(Ljava/lang/String;I)J");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named parseUnsignedLong with signature (Ljava/lang/String;I)J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallStaticLongMethod( cls, mid ,string_0,int_1 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::parseUnsignedLong jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }

        jlong Long::parseUnsignedLong(jstring string_0) {
            JNIEnv *env =getEnv();
            static jclass cls = getLongClass();
            jlong retVal= (jlong) -1;
            if (cls != NULL) {
                static jmethodID mid = env->GetStaticMethodID(cls, "parseUnsignedLong", "(Ljava/lang/String;)J");
                if (NULL == mid) {
                    std::cerr << "Class java.lang.Long has no method named parseUnsignedLong with signature (Ljava/lang/String;)J." << std::endl;
                    return (jlong) -1;
                } else {
                    retVal= (jlong)  env->CallStaticLongMethod( cls, mid ,string_0 );
                    jthrowable t = env->ExceptionOccurred();
                    if(t != NULL) {
                        DebugPrintJObject(__FILE__,__LINE__," Long::parseUnsignedLong jthis=",t);
                        if(GetDebugJ4Cpp()) env->ExceptionDescribe();
            //            env->ExceptionClear();
                        throw t;
                    }
                }
            }
            releaseEnv(env);
            return retVal;
        }
        static jclass getNewLongClass() {
            jclass clss = getEnv()->FindClass("java/lang/Long");
            if (NULL == clss) {
                std::cerr << " Can't find class java/lang/Long" << std::endl;
            }
            return clss;
        }
        
        static jclass LongClass = NULL;
        static inline jclass getLongClass() {
            if (LongClass != NULL) {
                return LongClass;
            }
            LongClass = getNewLongClass();
            return LongClass;
        }
        } // end namespace lang
    } // end namespace java

    
    
        // end namespace crclj
    }
    
