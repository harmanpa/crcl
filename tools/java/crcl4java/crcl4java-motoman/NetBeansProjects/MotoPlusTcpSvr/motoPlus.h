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
#include <stdlib.h>

typedef	int		BOOL;
typedef	int		STATUS;
typedef unsigned long	CTRLG_T;	// control group specified.[bit-string(d0~d3)]. BIT-ON:activated group

#define MP_GRP_AXES_NUM	8	// number per one control group of control axes.

#define MP_STACK_SIZE		0	/* The optimum value is automatically decided. */
#define MP_PRI_TIME_NORMAL	3	/* normal time task(it standard). */

/* joint position */
typedef long MP_JOINT[MP_GRP_AXES_NUM]; /* pulse or angle(0.0001[deg]) */


/* coordinate system type */
typedef enum {
        MP_PULSE_TYPE,                  /* pulse data type. */
        MP_ANGLE_TYPE,                  /* angle data type. */
        MP_BASE_TYPE,                   /* base coordinate data type. */
        MP_ROBOT_TYPE,                  /* robot coordinate data type. */
        MP_USER_TYPE,                   /* user coordinate data type. */
        } MP_COORD_TYPE;

/* motion speed */
typedef struct {
        long    vj;                     /* joint velocity(0.01[%]). */
        long    v;                      /* trajectory velocity(0.1[mm/sec]). */
        long    vr;                     /* orientation velocity(0.1[deg/sec]). */
        } MP_SPEED;

/* interpolation type */
typedef enum {
        MP_MOV_NOP_TYPE,                /* NOP */
        MP_MOVJ_TYPE,                   /* link interpolation type. */
        MP_MOVL_TYPE,                   /* linear interpolation type. */
        MP_MOVC_TYPE,                   /* circular interpolation type. */
        } MP_INTP_TYPE;

/* type of X-Y-Z coordinate */
typedef struct  {
	long	x, y, z;
	long	rx, ry, rz;
	long	ex1, ex2;
	}	MP_COORD;
        
/* position */
typedef union {
        MP_COORD coord;
        MP_JOINT joint;
        } MP_POS;

/* motion target */
typedef struct {
        int     id;                     /* target ID. */
        MP_INTP_TYPE intp;              /* interpolation type. */
        MP_POS  dst;                    /* destination position. */
        MP_POS  aux;                    /* passing(auxiliary) position. */
        } MP_TARGET;

#ifdef __cplusplus
typedef int 		(*FUNCPTR) (...);	/* ptr to function returning int */
typedef void 		(*VOIDFUNCPTR) (...);	/* ptr to function returning void */
#else
typedef int 		(*FUNCPTR) ();		/* ptr to function returning int */
typedef void 		(*VOIDFUNCPTR) ();	/* ptr to function returning void */
#endif			/* _cplusplus */



#ifdef __cplusplus
}
#endif

#endif /* MOTOPLUS_H */

