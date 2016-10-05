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

    typedef int BOOL;
    typedef int STATUS;
    typedef unsigned long ULONG;
    typedef unsigned short USHORT;
    typedef long LONG;
    typedef unsigned long CTRLG_T;

#define MP_GRP_AXES_NUM 8 
#define MP_STACK_SIZE  0 
#define MP_PRI_TIME_NORMAL 3 

#define mpSuspendSelf {}
#define mpHtons(x)  htons(x)

    typedef long MP_JOINT[MP_GRP_AXES_NUM];

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

#ifdef __cplusplus
    typedef int (*FUNCPTR) (...); /* ptr to function returning int */
    typedef void (*VOIDFUNCPTR) (...); /* ptr to function returning void */
#else
    typedef int (*FUNCPTR) (); /* ptr to function returning int */
    typedef void (*VOIDFUNCPTR) (); /* ptr to function returning void */
#endif   /* _cplusplus */

    extern LONG mpPutVarData(MP_VAR_DATA *sData, LONG num);

#ifdef __cplusplus
}
#endif

#endif /* MOTOPLUS_H */

