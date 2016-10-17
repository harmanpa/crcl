/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   motoPlus.h
 * Author: shackle
 *
 * Created on October 3, 2016, 4:45 PM
 */

#ifndef MOTOPLUS_H
#define MOTOPLUS_H

#ifdef __cplusplus
extern "C" {
#endif

#include <stdio.h>
#include <errno.h>
#include <string.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <stdlib.h>

#define NO_WAIT (0)
#define WAIT_FOREVER (-1)

    typedef int BOOL;
    typedef int STATUS;
    typedef unsigned long ULONG;
    typedef unsigned short USHORT;
    typedef short SHORT;
    typedef char CHAR;
    typedef long LONG;
    typedef unsigned char UCHAR;
    typedef unsigned long CTRLG_T;

#define MP_GRP_AXES_NUM 8 
#define MP_STACK_SIZE  0 
#define MP_PRI_TIME_NORMAL 3 

#define mpSuspendSelf {}
#define mpHtons(x)  htons(x)

    typedef long MP_JOINT[MP_GRP_AXES_NUM];

#define MAX_CART_AXES (6) 

    typedef struct {
        LONG lPos[MAX_CART_AXES];
        SHORT sConfig;
        CHAR reserved[2];
    } MP_CART_POS_RSP_DATA;

    typedef struct {
        USHORT usType;
        USHORT usIndex;
    } MP_VAR_INFO;

    typedef struct {
        USHORT usType;
        USHORT usIndex;
        LONG ulValue;
    } MP_VAR_DATA;

    typedef enum {
        MP_PULSE_TYPE, /* pulse data type. */
        MP_ANGLE_TYPE, /* angle data type. */
        MP_BASE_TYPE, /* base coordinate data type. */
        MP_ROBOT_TYPE, /* robot coordinate data type. */
        MP_USER_TYPE, /* user coordinate data type. */
    } MP_COORD_TYPE;

    typedef enum {
        MP_R1_GID = 0,
        MP_R2_GID,
        MP_R3_GID,
        MP_R4_GID,
        MP_B1_GID,
        MP_B2_GID,
        MP_B3_GID,
        MP_B4_GID,
        MP_S1_GID,
        MP_S2_GID,
        MP_S3_GID
    } MP_GRP_ID_TYPE;

    typedef struct {
        long vj; /* joint velocity(0.01[%]). */
        long v; /* trajectory velocity(0.1[mm/sec]). */
        long vr; /* orientation velocity(0.1[deg/sec]). */
    } MP_SPEED;

    typedef enum {
        MP_MOV_NOP_TYPE, /* NOP */
        MP_MOVJ_TYPE, /* link interpolation type. */
        MP_MOVL_TYPE, /* linear interpolation type. */
        MP_MOVC_TYPE, /* circular interpolation type. */
    } MP_INTP_TYPE;

    typedef struct {
        long x, y, z;
        long rx, ry, rz;
        long ex1, ex2;
    } MP_COORD;

    typedef union {
        MP_COORD coord;
        MP_JOINT joint;
    } MP_POS;

    typedef struct {
        int id; /* target ID. */
        MP_INTP_TYPE intp; /* interpolation type. */
        MP_POS dst; /* destination position. */
        MP_POS aux; /* passing(auxiliary) position. */
    } MP_TARGET;

    typedef struct /* Control Group send data */ {
        CTRLG_T sCtrlGrp; /* Control Group */
    } MP_CTRL_GRP_SEND_DATA;

    typedef struct {
        SHORT sRobotNo;
        SHORT sFrame;
        SHORT sToolNo;
        CHAR reserved[2];
    } MP_CARTPOS_EX_SEND_DATA;


#define MAX_PULSE_AXES   (8) /* Maximum pulse axes */

    typedef struct /* Pulse position receive data */ {
        LONG lPos[MAX_PULSE_AXES]; /* Pulse position */
    } MP_PULSE_POS_RSP_DATA;

    typedef struct /* Radian position receive data */ {
        LONG lRadPos[MAX_PULSE_AXES]; /* Radian position */
    } MP_RAD_POS_RSP_DATA;

    typedef struct /* Degree position receive data */ {
        LONG lDegPos[MAX_PULSE_AXES]; /* Degree position */
    } MP_DEG_POS_RSP_DATA;

#define MP_POS_UNIT_DEGREE   (1)
#define MP_POS_UNIT_DISTANCE  (2)
#define MP_POS_UNIT_RADIAN   (3)

    typedef struct /* Radian position and unit receive data */ {
        LONG lRadPos[MAX_PULSE_AXES]; /* Radian position */
        LONG lRadUnit[MAX_PULSE_AXES]; /* Unit */
    } MP_RAD_POS_RSP_DATA_EX;

    typedef struct /* Degree position and unit receive data */ {
        LONG lDegPos[MAX_PULSE_AXES]; /* Degree position */
        LONG lDegUnit[MAX_PULSE_AXES]; /* Unit */
    } MP_DEG_POS_RSP_DATA_EX;

    typedef struct /* Feedback pulse position receive data */ {
        LONG lPos[MAX_PULSE_AXES]; /* Pulse position */
    } MP_FB_PULSE_POS_RSP_DATA;

    typedef struct /* Servo speed data */ {
        LONG lSpeed[MAX_PULSE_AXES]; /* Speed */
    } MP_SERVO_SPEED_RSP_DATA;

    typedef struct /* Feedback speed data */ {
        LONG lSpeed[MAX_PULSE_AXES]; /* Speed */
    } MP_FB_SPEED_RSP_DATA;

    typedef struct /* Torque receive data */ {
        LONG lTorquePcnt[MAX_PULSE_AXES]; /* Torque [percent] */
    } MP_TORQUE_RSP_DATA;

    typedef struct /* Torque receive data (absolute) */ {
        LONG lTorqueNm[MAX_PULSE_AXES]; /* Torque [percent] */
    } MP_TORQUE_EX_RSP_DATA;

    typedef struct /* JOG speed receive data */ {
        SHORT sJogSpeed; /* JOG speed */
        CHAR reserved[2];
    } MP_JOGSPEED_RSP_DATA;

    typedef struct /* JOG coordinate receive data */ {
        USHORT sJogCoord; /* JOG coordinate */
        CHAR reserved[2];
    } MP_JOGCOORD_RSP_DATA;

#define MAX_SVAR_SIZE    (16) /* size of S-Variable */

    typedef struct /* S-Variable recieve data */ {
        UCHAR ucValue[MAX_SVAR_SIZE + 1]; /* S-Variable data(with delimiter) */
        UCHAR reserved[3];
    } MP_SVAR_RECV_INFO;

    typedef struct {
        SHORT sServoPower;
        CHAR reserved[2];
    } MP_SERVO_POWER_SEND_DATA;

    typedef struct {
        SHORT sServoPower;
        CHAR reserved[2];
    } MP_SERVO_POWER_RSP_DATA;

    typedef struct /* Error number receive data */ {
        USHORT err_no; /* Error number */
        CHAR reserved[2];
    } MP_STD_RSP_DATA;

#ifdef __cplusplus
    typedef int (*FUNCPTR) (...); /* ptr to function returning int */
    typedef void (*VOIDFUNCPTR) (...); /* ptr to function returning void */
#else
    typedef int (*FUNCPTR) (); /* ptr to function returning int */
    typedef void (*VOIDFUNCPTR) (); /* ptr to function returning void */
#endif   /* _cplusplus */

    extern LONG mpPutVarData(MP_VAR_DATA *sData, LONG num);

    extern int mpCreateTask(int mpPriSpec, int stackSize, FUNCPTR entryPt,
            int arg1, int arg2, int arg3, int arg4, int arg5,
            int arg6, int arg7, int arg8, int arg9, int arg10);

    extern int mpRecv(int s, char *buf, int bufLen, int flags);
    extern int mpSend(int s, const char *buf, int bufLen, int flags);


    extern STATUS mpTaskSuspend(int tid);

    extern int mpSocket(int domain, int type, int protocol);

    extern int mpListen(int s, int backlog);

    extern int mpAccept(int s, struct sockaddr *addr, int *addrlen);

    extern int mpBind(int s, struct sockaddr *name, int namelen);

    extern int mpConnect(int s, struct sockaddr *name, int namelen);

    extern int mpRecv(int s, char *buf, int bufLen, int flags);

    extern int mpSend(int s, const char *buf, int bufLen, int flags);

    extern STATUS mpClose(int fd);

    extern int mpMotStart(int options);

    extern int mpMotStop(int options);

    extern int mpMotTargetClear(CTRLG_T grp, int options);

    extern int mpMotTargetSend(CTRLG_T grp, MP_TARGET *target, int timeout);

    extern int mpMotTargetReceive(int grpNo, int id, int *recvId, int timeout, int options);

    extern int mpMotSetCoord(int grpNo, MP_COORD_TYPE type, int aux);

    extern int mpMotSetTool(int grpNo, int toolNo);


    extern int mpMotSetSpeed(int grpNo, MP_SPEED *spd);

    extern int mpMotSetOrigin(int grpNo, int options);

    extern int mpMotSetTask(int grpNo, int taskNo);

    extern int mpMotSetSync(int grpNo, int aux, int options);

    extern int mpMotResetSync(int grpNo);

    extern LONG mpGetVarData(MP_VAR_INFO *sData, LONG* rData, LONG num);

    extern LONG mpPutVarData(MP_VAR_DATA *sData, LONG num);

    extern LONG mpGetCartPos(MP_CTRL_GRP_SEND_DATA *sData, MP_CART_POS_RSP_DATA *rData);

    extern LONG mpGetPulsePos(MP_CTRL_GRP_SEND_DATA *sData, MP_PULSE_POS_RSP_DATA *rData);

    extern LONG mpGetFBPulsePos(MP_CTRL_GRP_SEND_DATA *sData, MP_FB_PULSE_POS_RSP_DATA *rData);

    extern LONG mpGetDegPosEx(MP_CTRL_GRP_SEND_DATA *sData, MP_DEG_POS_RSP_DATA_EX *rData);

    extern LONG mpSetServoPower(MP_SERVO_POWER_SEND_DATA *sData, MP_STD_RSP_DATA *rData);

    extern LONG mpGetServoPower(MP_SERVO_POWER_RSP_DATA *rData);

#ifdef __cplusplus
}
#endif

#endif /* MOTOPLUS_H */

