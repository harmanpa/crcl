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
        SYS1_FUNCTION_GROUP = 2
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
        SYS1_GET_DEG_POS_EX=6,
        SYS1_INVALID_RESERVED2=7, // Place holder for GET_RAD_EX  not implemented.
        SYS1_GET_SERVO_POWER=8,
        SYS1_SET_SERVO_POWER=9,
        SYS1_READIO=10,
        SYS1_WRITEIO=11,
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

#ifdef __cplusplus
}
#endif

#endif /* REMOTEFUNCTIONS_H */

