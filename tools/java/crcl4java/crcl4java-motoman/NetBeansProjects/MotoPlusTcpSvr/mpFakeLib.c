/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

#include <pthread.h>
#include <time.h>
#include <sys/select.h>
#include <unistd.h>
#include <fcntl.h>

/* Not technically required, but needed on some UNIX distributions */
#include <sys/types.h>
#include <sys/stat.h>

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
    return 0;
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

int mpSelect(int width, fd_set *pReadFds, fd_set *pWriteFds, fd_set *pExceptFds, struct timeval *pTimeOut) {
    return select(width, pReadFds, pWriteFds, pExceptFds, pTimeOut);
}

int mpCtrlGrpId2GrpNo(int in) {
    return in == 0 ? 0 : -1;
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
        printf("sData[%d].usType=%hu\n", i, sData[i].usType);
        printf("sData[%d].usIndex=%hu\n", i, sData[i].usIndex);
        rData[i] = 7 + i;
        printf("rData=%ld\n", rData[i]);
    }
    return 0;
}

LONG mpPutVarData(MP_VAR_DATA *sData, LONG num) {
    int i = 0;
    printf("mpPutVarData(%p,%ld) called.\n", sData, num);
    for (i = 0; i < num; i++) {
        printf("sData[%d].usType=%u\n", i, sData[i].usType);
        printf("sData[%d].usIndex=%hd\n", i, sData[i].usIndex);
        printf("sData[%d].ulValue=%ld\n", i, sData[i].ulValue);
    }
    return 0;
}

LONG mpGetCartPos(MP_CTRL_GRP_SEND_DATA *sData, MP_CART_POS_RSP_DATA *rData) {
    int i = 0;
    printf("mpGetCartPos(%p,%p) called.\n", sData, rData);
    printf("sData->sCtrlGrp = %ld\n", sData->sCtrlGrp);
    for (i = 0; i < 6; i++) {
        rData->lPos[i] = i + 5;
        printf("rData->lPos[%d]=%ld\n", i, rData->lPos[i]);
    }
    rData->sConfig = 99;
    printf("rData->sConfig = %hu\n", rData->sConfig);
    return 0;
}

LONG mpGetPulsePos(MP_CTRL_GRP_SEND_DATA *sData, MP_PULSE_POS_RSP_DATA *rData) {
    int i = 0;
    printf("mpGetPulsePos(%p,%p) called.\n", sData, rData);
    printf("sData->sCtrlGrp = %ld\n", sData->sCtrlGrp);
    for (i = 0; i < 8; i++) {
        rData->lPos[i] = i + 10;
        printf("rData->lPos[%d]=%ld\n", i, rData->lPos[i]);
    }
    return 0;
}

LONG mpGetFBPulsePos(MP_CTRL_GRP_SEND_DATA *sData, MP_FB_PULSE_POS_RSP_DATA *rData) {
    int i = 0;
    printf("mpGetFBPulsePos(%p,%p) called.\n", sData, rData);
    printf("sData->sCtrlGrp = %ld\n", sData->sCtrlGrp);
    for (i = 0; i < 8; i++) {
        rData->lPos[i] = i + 15;
        printf("rData->lPos[%d]=%ld\n", i, rData->lPos[i]);
    }
    return 0;
}

LONG mpGetDegPosEx(MP_CTRL_GRP_SEND_DATA *sData, MP_DEG_POS_RSP_DATA_EX *rData) {
    int i = 0;
    printf("mpGetDegPosEx(%p,%p) called.\n", sData, rData);
    printf("sData->sCtrlGrp = %ld\n", sData->sCtrlGrp);
    for (i = 0; i < 8; i++) {
        rData->lDegPos[i] = i + 25;
        printf("rData->lDegPos[%d]=%ld\n", i, rData->lDegPos[i]);
        rData->lDegUnit[i] = MP_POS_UNIT_DEGREE;
        printf("rData->lPos[%d]=%ld\n", i, rData->lDegUnit[i]);
    }
    return 0;
}

static short sServoPower;

LONG mpSetServoPower(MP_SERVO_POWER_SEND_DATA *sData, MP_STD_RSP_DATA *rData) {
    printf("mpSetServoPower(%p,%p) called.\n", sData, rData);
    printf("sData->sServoPower = %d\n", sData->sServoPower);
    sServoPower = sData->sServoPower;
    return 0;
}

LONG mpGetServoPower(MP_SERVO_POWER_RSP_DATA *rData) {
    rData->sServoPower = sServoPower;
    printf("mpGetServoPower(%p) called.\n", rData);
    printf("rData->sServoPower = %d\n", rData->sServoPower);
    return 0;
}

LONG mpReadIO(MP_IO_INFO *sData, USHORT* rData, LONG num) {
    short i = 0;
    printf("mpReadIO(%p,%p,%ld) called.\n", sData, rData, num);
    for (i = 0; i < num; i++) {
        printf("sData[%d].ulAddr=%ld\n", i, sData[i].ulAddr);
        rData[i] = 7 + i;
        printf("rData[%d]=%d\n", i, rData[i]);
    }
    return 0;
}

LONG mpWriteIO(MP_IO_DATA *sData, LONG num) {
    short i = 0;
    printf("mpWriteIO(%p,%ld) called.\n", sData, num);
    for (i = 0; i < num; i++) {
        printf("sData[%d].ulAddr=%ld\n", i, sData[i].ulAddr);
        printf("sData[%d].ulValue=%ld\n", i, sData[i].ulValue);
    }
    return 0;
}

LONG mpGetMode(MP_MODE_RSP_DATA *rData) {
    printf("mpGetMode(%p) called.\n", rData);
    rData->sMode = 2; // 1 = TEACH, 2 = PLAY
    rData->sRemote = 7; // ????
    return 0;
}

LONG mpGetCycle(MP_CYCLE_RSP_DATA *rData) {
    printf("mpGetCycle(%p) called.\n", rData);
    rData->sCycle = 1; // 1 = Step, 2 = 1Cycle, 3 = Auto
    return 0;
}

LONG mpGetAlarmStatus(MP_ALARM_STATUS_RSP_DATA *rData) {
    printf("mpGetAlarmStatus(%p) called.\n", rData);
    rData->sIsAlarm = 3; // D00 Error, D01 = Alarm 
    return 0;
}

LONG mpGetAlarmCode(MP_ALARM_CODE_RSP_DATA *rData) {
    int i = 0;
    printf("mpGetAlarmCode(%p) called.\n", rData);
    rData->usErrorNo = 13;
    rData->usErrorData = 113;
    rData->usAlarmNum = 3;
    for (i = 0; i < 3; i++) {
        rData->AlarmData.usAlarmData[i] = 70 + i;
        rData->AlarmData.usAlarmNo[i] = 90 + i;
    }
    return 0;
}

long mpRefreshFileList(short extensionId) {
    printf("mpRefreshFileList(%d) called.\n", extensionId);
    return 0;
}

long mpGetFileCount(void) {
    printf("mpGetFileCount() called.\n");
    return 1;
}

long mpGetFileName(int index, char *fileName) {
    printf("mpGetFileName(%d,%p) called.\n", index, fileName);
    strcpy(fileName, "foo.jbi");
    return 0;
}

long mpLoadFile(long mpRamDriveId, const char *loadPath, const char *fileName) {
    printf("mpLoadFile(%ld,%s,%s) called.\n", mpRamDriveId, loadPath, fileName);
    return 0;
}

long mpSaveFile(long mpRamDriveId, const char *savePath, const char *fileName) {
    printf("mpSaveFile(%ld,%s,%s) called.\n", mpRamDriveId, savePath, fileName);
    return 0;
}

long mpFdWriteFile(int fd, MP_FILE_NAME_SEND_DATA *sData) {
    printf("mpFdWriteFile(%d,%p) called.\n", fd, sData);
    printf("sData->cFileName=%s\n", sData->cFileName);
    return 0;
}

long mpFdReadFile(int fd, MP_FILE_NAME_SEND_DATA *sData) {
    printf("mpFdReadFile(%d,%p) called.\n", fd, sData);
    printf("sData->cFileName=%s\n", sData->cFileName);
    return 0;
}

long mpFdGetJobList(int fd, MP_GET_JOBLIST_RSP_DATA *rData) {
    printf("mpFdGetJobList(%d,%p) called.\n", fd, rData);
    rData->err_no = 0;
    rData->uIsEndFlag = 1;
    rData->uListDataNum = 1;
    strcpy(rData->cListData, "myjob");
    return 0;
}

int mpCreate(const char * name, int flags) {
    int ret = -1;
    printf("mpCreate(%s,%d) called.\n", name, flags);
    ret = creat(name, 0666);
    if (ret < 0) {
        printf("ret = %d, errno = %d, %s\n", ret, errno, strerror(errno));
    }
    return ret;
}

int mpOpen(const char * name, int flags, int mode) {
    int ret = -1;
    printf("mpOpen(%s,%d,%d) called.\n", name, flags, mode);
    ret = open(name, flags, 0666);
    if (ret < 0) {
        printf("ret = %d, errno = %d, %s\n", ret, errno, strerror(errno));
    }
    return ret;
}

STATUS mpRemove(const char * name) {
    printf("mpRemove(%s,) called.\n", name);
    return 0;
}

int mpRename(const char * oldName, const char * newName) {
    printf("mpRename(%s,%s) called.\n", oldName, newName);
    return 0;
}

int mpRead(int fd, char * buffer, size_t maxBytes) {
    int ret = -1;
    printf("mpRead(%d,%p,%ld) called.\n", fd, buffer, maxBytes);
    ret = read(fd, buffer, maxBytes);
    if (ret < 0) {
        printf("ret = %d, errno = %d, %s\n", ret, errno, strerror(errno));
    }
    return ret;
}

int mpWrite(int fd, char * buffer, size_t nBytes) {
    int ret = -1;
    printf("mpWrite(%d,%p,%ld) called.\n", fd, buffer, nBytes);
    ret = write(fd, buffer, nBytes);
    if (ret < 0) {
        printf("ret = %d, errno = %d, %s\n", ret, errno, strerror(errno));
    }
    return ret;
}

int mpGetRtc(void) {
    printf("mpGetRtc() called.\n");
    return 1;
}


int mpFcsStartMeasuring(MP_FCS_ROB_ID rob_id, int reset_time, MP_FCS_OFFSET_DATA offset_data) {
    printf("mpFcsStartMeasuring(rob_id=%d,reset_time=%d,...) called.\n", rob_id, reset_time);
    for (int i = 0; i < MP_FCS_AXES_NUM; i++) {
        offset_data[i] = i*20;
        printf("mpFcsStartMeasuring : returning offset_data[%d]=%d\n", i, offset_data[i]);
    }
    return 0;
}

int mpFcsGetForceData(MP_FCS_ROB_ID rob_id, int coord_type, int uf_no, MP_FCS_SENS_DATA sens_data) {
    printf("mpFcsGetForceData(rob_id=%d,coord_type=%d,uf_no=%d,...) called.\n", rob_id, coord_type, uf_no);
    for (int i = 0; i < MP_FCS_AXES_NUM; i++) {
        sens_data[i] = i *30;
        printf("mpFcsGetForceData : sens_data[%d]=%d\n", i, sens_data[i]);
    }
    return 0;
}

static MP_FCS_IMP_COEFF sim_m, sim_d, sim_k;

int mpFcsStartImp(MP_FCS_ROB_ID rob_id, MP_FCS_IMP_COEFF m, MP_FCS_IMP_COEFF d, MP_FCS_IMP_COEFF k,
        int coord_type, int uf_no, BITSTRING cart_axes, BITSTRING option_ctrl) {
    printf("mpFcsStartImp(rob_id=%d,coord_type=%d,uf_no=%d,cart_axes=0x%x,option_ctrl=0x%x...) called.\n", rob_id, coord_type, uf_no, cart_axes, option_ctrl);
    for (int i = 0; i < MP_FCS_AXES_NUM; i++) {
        printf("mpFcsStartImp : m[%d]=%d\n", i, m[i]);
        sim_m[i] = m[i];
    }
    for (int i = 0; i < MP_FCS_AXES_NUM; i++) {
        printf("mpFcsStartImp : d[%d]=%d\n", i, d[i]);
        sim_d[i] = d[i];
    }
    for (int i = 0; i < MP_FCS_AXES_NUM; i++) {
        printf("mpFcsStartImp : k[%d]=%d\n", i, k[i]);
        sim_k[i] = k[i];
    }
    return 0;
}

static MP_FCS_FREF_DATA sim_fref_data;

int mpFcsSetReferenceForce(MP_FCS_ROB_ID rob_id, MP_FCS_FREF_DATA fref_data) {
    printf("mpFcsSetReferenceForce(rob_id=%d,...) called.\n", rob_id);
    for (int i = 0; i < MP_FCS_AXES_NUM; i++) {
        printf("mpFcsSetReferenceForce : fref_data[%d]=%d\n", i, fref_data[i]);
        sim_fref_data[i] = fref_data[i];
    }
    return 0;
}

int mpFcsEndImp(MP_FCS_ROB_ID rob_id) {
    printf("mpFcsEndImp(rob_id=%d) called.\n", rob_id);
    return 0;
}

int mpFcsConvForceScale(MP_FCS_ROB_ID rob_id, int scale) {
    printf("mpFcsConvForceScale(rob_id=%d,scale=%d) called.\n", rob_id, scale);
    return 0;
}

int mpFcsGetSensorData(MP_FCS_ROB_ID rob_id, MP_FCS_SENS_DATA sens_data) {
    printf("mpFcsGetSensorData(rob_id=%d,...) called.\n", rob_id);
    for (int i = 0; i < MP_FCS_AXES_NUM; i++) {
        sens_data[i] = i *15;
        printf("mpFcsGetForceData : sens_data[%d]=%d\n", i, sens_data[i]);
    }
    return 0;
}

static BITSTRING figCtrlFlagsToBitString(BOOL front, BOOL upper, BOOL flip, BOOL r_lt_180, BOOL t_lt_180, BOOL s_lt_180) {
    BITSTRING bs =
            (front ? 0: 0x1)
            | (upper ? 0: 0x2)
            | (flip ? 0: 0x4)
            | (r_lt_180 ? 0: 0x8)
            | (t_lt_180 ? 0: 0x10)
            | (s_lt_180 ? 0: 0x20)
            ;
    return bs;
}

int mpConvAxesToCartPos(unsigned int grp_no,
        long angle[MP_GRP_AXES_NUM],
        unsigned int tool_no,
        BITSTRING *fig_ctrl,
        MP_COORD *coord) {
    printf("mpConvAxesToCartPos(grp_no=%d,...,tool_no=%d,...) called.\n", grp_no, tool_no);
    for (int i = 0; i < MP_GRP_AXES_NUM; i++) {
        printf("mpConvAxesToCartPos : angle[%d]=%ld\n", i, angle[i]);
    }
    *fig_ctrl = figCtrlFlagsToBitString(1,1,0,0,1,1);
    printf("mpConvAxesToCartPos returning : (*fig_ctrl)=%d\n",(*fig_ctrl));
    coord->x = 11;
    coord->y = 12;
    coord->z = 13;
    coord->rx = 150000;
    coord->ry = 300000;
    coord->rz = 450000;
    coord->ex1 = 60000;
    coord->ex2 = 299;
    printf("mpConvAxesToCartPos returning : (coord->x)=%ld\n",(coord->x));
    printf("mpConvAxesToCartPos returning : (coord->y)=%ld\n",(coord->y));
    printf("mpConvAxesToCartPos returning : (coord->z)=%ld\n",(coord->z));
    printf("mpConvAxesToCartPos returning : (coord->rx)=%ld\n",(coord->rx));
    printf("mpConvAxesToCartPos returning : (coord->ry)=%ld\n",(coord->ry));
    printf("mpConvAxesToCartPos returning : (coord->rz)=%ld\n",(coord->rz));
    printf("mpConvAxesToCartPos returning : (coord->ex1)=%ld\n",(coord->ex1));
    printf("mpConvAxesToCartPos returning : (coord->ex2)=%ld\n",(coord->ex2));
    return 0;
}

int mpConvCartPosToAxes(unsigned int grp_no, MP_COORD *coord, unsigned int tool_no, BITSTRING fig_ctrl, long prev_angle[MP_GRP_AXES_NUM], MP_KINEMA_TYPE kinema_type, long angle[MP_GRP_AXES_NUM]) {
    printf("mpConvCartPosToAxes(grp_no=%u,...,tool_no=%u,fig_ctrl=0x%x,...,kinema_type=%d) called.\n", grp_no, tool_no,fig_ctrl,kinema_type);
    for (int i = 0; i < MP_GRP_AXES_NUM; i++) {
        printf("mpConvCartPosToAxes : prev_angle[%d]=%ld\n", i, prev_angle[i]);
        angle[i]= prev_angle[i]+i;
    }
    for (int i = 0; i < MP_GRP_AXES_NUM; i++) {
        printf("mpConvCartPosToAxes : returning angle[%d]=%ld\n", i, angle[i]);
    }
    return 0;
}

int mpConvPulseToAngle(unsigned int grp_no, long pulse[MP_GRP_AXES_NUM], long angle[MP_GRP_AXES_NUM]) {
    printf("mpConvPulseToAngle(grp_no=%d,...,) called.\n", grp_no);
    for (int i = 0; i < MP_GRP_AXES_NUM; i++) {
        printf("mpConvPulseToAngle : pulse[%d]=%ld\n", i, pulse[i]);
        angle[i]= pulse[i]+i;
    }
    for (int i = 0; i < MP_GRP_AXES_NUM; i++) {
        printf("mpConvPulseToAngle : returning angle[%d]=%ld\n", i, angle[i]);
    }
    return 0;
}

int mpConvAngleToPulse(unsigned int grp_no, long angle[MP_GRP_AXES_NUM], long pulse[MP_GRP_AXES_NUM]) {
    printf("mpConvAngleToPulse(grp_no=%d,...,) called.\n", grp_no);
    for (int i = 0; i < MP_GRP_AXES_NUM; i++) {
        printf("mpConvAngleToPulse : angle[%d]=%ld\n", i, angle[i]);
        pulse[i]= angle[i]+i;
    }
    for (int i = 0; i < MP_GRP_AXES_NUM; i++) {
        printf("mpConvAngleToPulse : returning pulse[%d]=%ld\n", i, pulse[i]);
    }
    return 0;
}

int mpConvFBPulseToPulse(unsigned int grp_no, long fbpulse[MP_GRP_AXES_NUM], long pulse[MP_GRP_AXES_NUM]) {
    printf("mpConvFBPulseToPulse(grp_no=%d,...,) called.\n", grp_no);
    for (int i = 0; i < MP_GRP_AXES_NUM; i++) {
        printf("mpConvFBPulseToPulse : fbpulse[%d]=%ld\n", i, fbpulse[i]);
        pulse[i]= fbpulse[i]+i;
    }
    for (int i = 0; i < MP_GRP_AXES_NUM; i++) {
        printf("mpConvFBPulseToPulse : returning pulse[%d]=%ld\n", i, pulse[i]);
    }
    return 0;
}

int mpMakeFrame(MP_XYZ* org_vector, MP_XYZ* x_vector, MP_XYZ* y_vector, MP_FRAME* frame) {
    return 0;
}

int mpInvFrame(MP_FRAME*org_frame, MP_FRAME* frame) {
    return 0;
}

int mpRotFrame(MP_FRAME* org_frame, double angle, MP_XYZ* vector, MP_FRAME* frame) {
    return 0;
}

int mpMulFrame(MP_FRAME* frame1, MP_FRAME* frame2, MP_FRAME* frame_prod) {
    return 0;
}

int mpZYXeulerToFrame(MP_COORD* coord, MP_FRAME* frame) {
    return 0;
}

int mpFrameToZYXeuler(MP_FRAME*frame, MP_COORD* coord) {
    return 0;
}

int mpCrossProduct(MP_XYZ* vector1, MP_XYZ* vector2, MP_XYZ* xyz_prod) {
    return 0;
}

int mpInnerProduct(MP_XYZ* vector1, MP_XYZ* vector2, double* double_prod) {
    return 0;
}