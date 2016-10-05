/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

#include <pthread.h>
#include <time.h>
#include <sys/select.h>
#include "motoPlus.h"

#include <stdlib.h>


struct pthreadArg {
    FUNCPTR entryPt;
    int arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10;
};

void *pthread_start(void *arg) {
    struct pthreadArg *p;
    FUNCPTR entryPt;
    int arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10;
    p = ((struct pthreadArg *) arg);
    entryPt = p->entryPt;
    arg1 = p->arg1;
    arg2 = p->arg2;
    arg3 = p->arg3;
    arg4 = p->arg4;
    arg5 = p->arg5;
    arg6 = p->arg6;
    arg7 = p->arg7;
    arg8 = p->arg8;
    arg9 = p->arg9;
    arg10 = p->arg10;
    free((void*) p);
    entryPt(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
    return NULL;
}

int mpCreateTask(int mpPriSpec, int stackSize, FUNCPTR entryPt,
        int arg1, int arg2, int arg3, int arg4, int arg5,
        int arg6, int arg7, int arg8, int arg9, int arg10) {
    pthread_t thread;

    int pthreadErrCode;
    struct pthreadArg *p;
    p = (struct pthreadArg*) malloc(sizeof (struct pthreadArg));
    p->entryPt = entryPt;
    p->arg1 = arg1;
    p->arg2 = arg2;
    p->arg3 = arg3;
    p->arg4 = arg4;
    p->arg5 = arg5;
    p->arg6 = arg6;
    p->arg7 = arg7;
    p->arg8 = arg8;
    p->arg9 = arg9;
    p->arg10 = arg10;
    pthreadErrCode = pthread_create(&thread, NULL, &pthread_start, (void*) p);
    if (pthreadErrCode) {
        fprintf(stderr, "pthread_create failed: %s", strerror(pthreadErrCode));
        return -1;
    }
    return (int) thread;
}

STATUS mpTaskSuspend(int tid) {
    return 0;
}

int mpSocket(int domain, int type, int protocol) {
    return socket(AF_INET, SOCK_STREAM, 0);
}

int mpListen(int s, int backlog) {
    return listen(s, backlog);
}

int mpAccept(int s, struct sockaddr *addr, int *addrlen) {
    return accept(s, addr, addrlen);
}

int mpBind(int s, struct sockaddr *name, int namelen) {
    return bind(s, name, namelen);
}

int mpConnect(int s, struct sockaddr *name, int namelen) {
    return connect(s, name, namelen);
}

int mpRecv(int s, char *buf, int bufLen, int flags) {
    return recv(s, buf, bufLen, flags);
}

int mpSend(int s, const char *buf, int bufLen, int flags) {
    return send(s, buf, bufLen, flags);
}

STATUS mpClose(int fd) {
    close(fd);
    return 0;
}

int mpMotStart(int options) {
    printf("mpMotStart(%d) called.\n", options);
    return 0;
}

int mpMotStop(int options) {
    printf("mpMotStop(%d) called.\n", options);
    return 0;
}

int mpMotTargetClear(CTRLG_T grp, int options) {
    printf("mpMotTargetClear(%ld,%d) called.\n", grp, options);
    return 0;
}

int mpMotTargetSend(CTRLG_T grp, MP_TARGET *target, int timeout) {
    int i = 0;
    printf("mpMotTargetSend(%ld,%p,%d) called.\n", grp, target, timeout);
    printf("target.id=%d\n", target->id);
    printf("target.intp=%d\n", target->intp);
    printf("target.dst.coord.x=%ld\n", target->dst.coord.x);
    printf("target.dst.coord.y=%ld\n", target->dst.coord.y);
    printf("target.dst.coord.z=%ld\n", target->dst.coord.z);
    printf("target.dst.coord.rx=%ld\n", target->dst.coord.rx);
    printf("target.dst.coord.ry=%ld\n", target->dst.coord.ry);
    printf("target.dst.coord.rz=%ld\n", target->dst.coord.rz);
    printf("target.dst.coord.ex1=%ld\n", target->dst.coord.ex1);
    printf("target.dst.coord.ex2=%ld\n", target->dst.coord.ex2);
    printf("target.aux.coord.x=%ld\n", target->aux.coord.x);
    printf("target.aux.coord.y=%ld\n", target->aux.coord.y);
    printf("target.aux.coord.z=%ld\n", target->aux.coord.z);
    printf("target.aux.coord.rx=%ld\n", target->aux.coord.rx);
    printf("target.aux.coord.ry=%ld\n", target->aux.coord.ry);
    printf("target.aux.coord.rz=%ld\n", target->aux.coord.rz);
    printf("target.aux.coord.ex1=%ld\n", target->aux.coord.ex1);
    printf("target.aux.coord.ex2=%ld\n", target->aux.coord.ex2);
    for (i = 0; i < 8; i++) {
        printf("target.dst.joint[%d]=%ld\n", i, target->dst.joint[i]);
    }
    for (i = 0; i < 8; i++) {
        printf("target.aux.joint[%d]=%ld\n", i, target->aux.joint[i]);
    }
    return 0;
}

int mpMotTargetReceive(int grpNo, int id, int *recvId, int timeout, int options) {
    printf("mpMotTargetReceive(%d,%d,%p,%d,%d) called.\n", grpNo, id, recvId, timeout, options);
    if (recvId) {
        *recvId = id;
    }
    return 0;
}

int mpMotSetCoord(int grpNo, MP_COORD_TYPE type, int aux) {
    printf("mpMotSetCoord(%d,%d,%d) called.\n", grpNo, type, aux);
    return 0;
}

int mpMotSetTool(int grpNo, int toolNo) {
    printf("mpMotSetTool(%d,%d) called.\n", grpNo, toolNo);
    return 0;
}

int mpMotSetSpeed(int grpNo, MP_SPEED *spd) {
    printf("mpMotSetSpeed(%d,%p) called.\n", grpNo, spd);
    printf("spd.vj=%ld\n", spd->vj);
    printf("spd.v=%ld\n", spd->v);
    printf("spd.vr=%ld\n", spd->vr);
    return 0;
}

int mpMotSetOrigin(int grpNo, int options) {
    printf("mpMotSetOrigin(%d,%d) called.\n", grpNo, options);
    return 0;
}

int mpMotSetTask(int grpNo, int taskNo) {
    printf("mpMotSetTask(%d,%d) called.\n", grpNo, taskNo);
    return 0;
}

int mpMotSetSync(int grpNo, int aux, int options) {
    printf("mpMotSetSync(%d,%d,%d) called.\n", grpNo, aux, options);
    return 0;
}

int mpMotResetSync(int grpNo) {
    printf("mpMotResetSync(%d) called.\n", grpNo);
    return 0;
}

LONG mpGetVarData(MP_VAR_INFO *sData, LONG* rData, LONG num) {
    int i = 0;
    printf("mpGetVarData(%p,%p,%ld) called.\n", sData, rData, num);
    for (i = 0; i < num; i++) {
        printf("sData[%d].usType=%hu\n", i,sData[i].usType);
        printf("sData[%d].usIndex=%hu\n", i,sData[i].usIndex);
        rData[i] = 7+i;
        printf("rData=%ld\n", rData[i]);
    }
    return 0;
}

LONG mpPutVarData(MP_VAR_DATA *sData, LONG num) {
    int i = 0;
    printf("mpPutVarData(%p,%ld) called.\n", sData, num);
    for (i = 0; i < num; i++) {
        printf("sData[%d].usType=%u\n", i,sData[i].usType);
        printf("sData[%d].usIndex=%hd\n", i, sData[i].usIndex);
        printf("sData[%d].ulValue=%ld\n", i, sData[i].ulValue);
    }
    return 0;
}
