/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   remoteFunctions.h
 * Author: Will Shackleford {@literal <william.shackleford@nist.gov>}
 *
 * Created on September 29, 2016, 9:59 AM
 */

#ifndef REMOTEFUNCTIONS_H
#define REMOTEFUNCTIONS_H

#ifdef __cplusplus
extern "C" {
#endif

    enum RemoteFunctionGroup {
        INVALID_FUNCTION_GROUP = 0,
        MOT_FUNCTION_GROUP = 1,
        SYS1_FUNCTION_GROUP = 2,
        FILE_CTRL_FUNCTION_GROUP = 3,
        EX_FILE_CTRL_FUNCTION_GROUP = 4,
        FORCE_CTRL_FUNCTION_GROUP = 5,
        KINEMATICS_CONVERSION_FUNCTION_GROUP = 6
    };

    enum RemoteMotFunctionType {
        MOT_INVALID = 0,
        MOT_START = 1,
        MOT_STOP = 2,
        MOT_TARGET_CLEAR = 3,
        MOT_JOINT_TARGET_SEND = 4,
        MOT_COORD_TARGET_SEND = 5,
        MOT_TARGET_RECEIVE = 6,
        MOT_SET_COORD = 7,
        MOT_SET_TOOL = 8,
        MOT_SET_SPEED = 9,
        MOT_SET_ORIGIN = 10,
        MOT_SET_TASK = 11,
        MOT_SET_SYNC = 12,
        MOT_RESET_SYNC = 13
    };

    enum RemoteSys1FunctionType {
        SYS1_INVALID = 0,
        SYS1_GET_VAR_DATA = 1,
        SYS1_PUT_VAR_DATA = 2,
        SYS1_GET_CURRENT_CART_POS = 3,
        SYS1_GET_CURRENT_PULSE_POS = 4,
        SYS1_GET_CURRENT_FEEDBACK_PULSE_POS = 5,
        SYS1_GET_DEG_POS_EX = 6,
        SYS1_INVALID_RESERVED2 = 7, // Place holder for GET_RAD_EX  not implemented.
        SYS1_GET_SERVO_POWER = 8,
        SYS1_SET_SERVO_POWER = 9,
        SYS1_READIO = 10,
        SYS1_WRITEIO = 11,
        SYS1_GET_MODE = 12,
        SYS1_GET_CYCLE = 13,
        SYS1_GET_ALARM_STATUS = 14,
        SYS1_GET_ALARM_CODE = 15,
        SYS1_GET_RTC = 16,

    };

    enum RemoteFileFunctionType {
        FILE_CTRL_INVALID = 0,
        FILE_CTRL_OPEN = 1,
        FILE_CTRL_CREATE = 2,
        FILE_CTRL_CLOSE = 3,
        FILE_CTRL_READ = 4,
        FILE_CTRL_WRITE = 5,
    };

    enum RemoteExFileFunctionType {
        EX_FILE_CTRL_INVALID = 0,
        EX_FILE_CTRL_GET_FILE_COUNT = 1,
        EX_FILE_CTRL_GET_FILE_NAME = 2,
        EX_FILE_CTRL_LOAD_FILE = 3,
        EX_FILE_CTRL_SAVE_FILE = 4,
        EX_FILE_CTRL_FD_READ_FILE = 5,
        EX_FILE_CTRL_FD_WRITE_FILE = 6,
        EX_FILE_CTRL_FD_GET_JOB_LIST = 7,
    };

    enum RemoteForceConTrolFunctionType {
        FORCE_CONTROL_INVALID = 0,
        FORCE_CONTROL_START_MEASURING = 1,
        FORCE_CONTROL_GET_FORCE_DATA = 2,
        FORCE_CONTROL_START_IMP = 3,
        FORCE_CONTROL_SET_REFERENCE_FORCE = 4,
        FORCE_CONTROL_END_IMP = 5,
        FORCE_CONTROL_CONV_FORCE_SCALE = 6,
        FORCE_CONTROL_GET_SENSOR_DATA = 7,
    };
    
    enum RemoteKinematicsConversionFunctionType {
        KINEMATICS_CONVERSION_INVALID = 0,
        KINEMATICS_CONVERSION_CONVERT_AXES_TO_CART_POS = 1,
        KINEMATICS_CONVERSION_CONVERT_CART_POS_TO_AXES = 2,
        KINEMATICS_CONVERSION_CONVERT_PULSE_TO_ANGLE = 3,
        KINEMATICS_CONVERSION_CONVERT_ANGLE_TO_PULSE= 4,
        KINEMATICS_CONVERSION_CONVERT_FB_PULSE_TO_PULSE = 5,
        KINEMATICS_CONVERSION_MAKE_FRAME = 6, // reserved but not implemented.
        KINEMATICS_CONVERSION_INV_FRAME = 7, // reserved but not implemented.
        KINEMATICS_CONVERSION_ROT_FRAME = 8, // reserved but not implemented.
        KINEMATICS_CONVERSION_MUL_FRAME = 9, // reserved but not implemented.
        KINEMATICS_CONVERSION_ZYX_EULER_TO_FRAME = 10, // reserved but not implemented.
        KINEMATICS_CONVERSION_FRAME_TO_ZYX_EULER = 11, // reserved but not implemented.
        KINEMATICS_CONVERSION_CROSS_PRODUCT = 12, // reserved but not implemented.
        KINEMATICS_CONVERSION_INNER_PRODUCT = 13, // reserved but not implemented.
    };

    // Read requests on the given accepted socket handle, forever or until an
    // error occurs.
    extern int handleSingleConnection(int acceptHandle);

    // Call the appropriate mot related function and send a response on the accepted handle.
    // Note: Return 0 for successs, any other return value is treated as a fatal error 
    // closing the socket.
    extern int handleMotFunctionRequest(int acceptHandle, char *inBuffer, char *outBuffer, int type, int msgSize);

    // Call the appropriate from the sys related function from the first set roughly coorilating 
    //  with mpLegApi00.hh and send a response on the accepted handle.
    // Note: Return 0 for successs, any other return value is treated as a fatal error 
    // closing the socket.
    extern int handleSys1FunctionRequest(int acceptHandle, char *inBuffer, char *outBuffer, int type, int msgSize);


    // Call the appropriate from the fcs (force-control-system) related function
    // Note: Return 0 for successs, any other return value is treated as a fatal error 
    // closing the socket.
    extern int handleFcsFunctionRequest(int acceptHandle, char *inBuffer, char *outBuffer, int type, int msgSize);


    // Call the appropriate from the kinematics conversion  related function 
    // Note: Return 0 for successs, any other return value is treated as a fatal error 
    // closing the socket.
    extern int handleKinematicConvFunctionRequest(int acceptHandle, char *inBuffer, char *outBuffer, int type, int msgSize);


#ifdef __cplusplus
}
#endif

#endif /* REMOTEFUNCTIONS_H */

