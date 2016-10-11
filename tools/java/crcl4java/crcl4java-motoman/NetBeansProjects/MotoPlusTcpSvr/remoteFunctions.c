/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

#include "motoPlus.h"
#include "remoteFunctions.h"

extern void *malloc(size_t);
extern void free(void *);

#define BUFF_MAX    1023

static int recvN(int handle, char *buf, int n, int flags) {
    int lastRecv = -1;
    int totalRecv = 0;
    do {
        lastRecv = mpRecv(handle, (buf + totalRecv), n - totalRecv, flags);
        if (lastRecv == 0) {
            fprintf(stderr, "recv returned 0\n");
            return lastRecv;
        }
        if (lastRecv < 1) {
            fprintf(stderr, "recv error : %s\n", strerror(errno));
            return lastRecv;
        }
        totalRecv += lastRecv;
    } while (totalRecv < n);
    return totalRecv;
}

static int sendN(int handle, char *buf, int n, int flags) {
    int lastSend = -1;
    int totalSend = 0;
    do {
        lastSend = mpSend(handle, (buf + totalSend), n - totalSend, flags);
        if (lastSend < 1) {
            fprintf(stderr, "send error : %s\n", strerror(errno));
            return lastSend;
        }
        totalSend += lastSend;
    } while (totalSend < n);
    return totalSend;
}

#ifdef DO_SWAP

static void swap(char *buf, int offset, int sz) {
    int i = 0;
    int ret = -1;
    char tmp;
    /*
        printf("swap(%p,%d,%d)\n",buf,offset,sz);
     */
    for (i = 0; i < sz / 2; i++) {
        tmp = buf[offset + i];
        buf[offset + i] = buf[offset + sz - 1 - i];
        buf[offset + sz - 1 - i] = tmp;
    }
}
#endif

// Stupid C won't include a lib function that won't make 
// me tear my hair out looking to make sure it is defined on every
// platform to do this
// so it gets reimplemented yet again.

#ifndef DO_NOT_NEED_STANDARD_INT_TYPES
typedef short int16_t;
typedef int int32_t;
#endif

static int16_t getInt16(char *buf, int offset) {
#ifdef DO_SWAP
    swap(buf, offset, 2);
#endif
    return *((int16_t *) (buf + offset));
}

static void setInt16(char *buf, int offset, int16_t val) {
    *((int16_t *) (buf + offset)) = val;
#ifdef DO_SWAP
    swap(buf, offset, 2);
#endif
}

static int32_t getInt32(char *buf, int offset) {
#ifdef DO_SWAP
    swap(buf, offset, 4);
#endif
    return *((int32_t *) (buf + offset));
}

static void setInt32(char *buf, int offset, int32_t val) {

    *((int32_t *) (buf + offset)) = val;
#ifdef DO_SWAP
    swap(buf, offset, 4);
#endif
}

// Return 0 for success, anything else will be treated like a fatal error closing
// the connection.

int handleSys1FunctionRequest(int acceptHandle, char *inBuffer, char *outBuffer, int type, int msgSize) {

    int sendRet = 0;
    int i = 0;
    MP_VAR_INFO varInfo[25];
    MP_VAR_DATA varData[25];
    LONG rData[25];
    LONG num;
    int ret;
    MP_CTRL_GRP_SEND_DATA ctrlGrpSendData;
    MP_CART_POS_RSP_DATA cartPosRspData;
    MP_PULSE_POS_RSP_DATA pulsePosRspData;
    MP_FB_PULSE_POS_RSP_DATA fbPulsePosRspData;
    MP_DEG_POS_RSP_DATA_EX degPosRspDataEx;
    
    int32_t controlGroup = 0;

    switch (type) {
        case SYS1_GET_VAR_DATA:
            num = getInt32(inBuffer, 12);
            if (num < 1 || num > 24) {
                fprintf(stderr, "invalid num for mpGetVarData num = %ld\n", num);
                return -1;
            }
            if (msgSize != 12 + (4 * num)) {
                fprintf(stderr, "invalid msgSize for mpGetVarData = %d != %ld for num = %ld\n", msgSize, 12 + (4 * num), num);
                return -1;
            }
            for (i = 0; i < num; i++) {
                varInfo[i].usType = getInt16(inBuffer, 16 + (4 * i));
                varInfo[i].usIndex = getInt16(inBuffer, 18 + (4 * i));
            }
            ret = mpGetVarData(varInfo, rData, num);
            setInt32(outBuffer, 0, 4 + num * 4);
            setInt32(outBuffer, 4, ret);
            for (i = 0; i < num; i++) {
                setInt32(outBuffer, 8 + i * 4, rData[i]);
            }
            sendRet = sendN(acceptHandle, outBuffer, 8 + num * 4, 0);
            if (sendRet != 8 + num * 4) {
                fprintf(stderr, "sendRet = %d != 8 + num*4\n", sendRet);
                return -1;
            }
            break;

        case SYS1_PUT_VAR_DATA:
            num = getInt32(inBuffer, 12);
            if (num < 1 || num > 24) {
                fprintf(stderr, "invalid num for mpPutVarData num = %ld\n", num);
                return -1;
            }
            if (msgSize != 12 + (num * 8)) {
                fprintf(stderr, "invalid msgSize for mpPutVarData = %d != %ld for num = %ld\n", msgSize, 12 + (num * 8), num);
                return -1;
            }
            for (i = 0; i < num; i++) {
                varData[i].usType = getInt16(inBuffer, 16 + (8 * i));
                varData[i].usIndex = getInt16(inBuffer, 18 + (8 * i));
                varData[i].ulValue = getInt32(inBuffer, 20 + (8 * i));
            }
            ret = mpPutVarData(varData, num);
            setInt32(outBuffer, 0, 4);
            setInt32(outBuffer, 4, ret);
            sendRet = sendN(acceptHandle, outBuffer, 8, 0);
            if (sendRet != 8) {
                fprintf(stderr, "sendRet = %d != 8\n", sendRet);
                return -1;
            }
            break;

        case SYS1_GET_CURRENT_CART_POS:
            if (msgSize != 12) {
                fprintf(stderr, "invalid msgSize for mpMotTargetClear = %d != 12\n", msgSize);
                return -1;
            }
            memset(&ctrlGrpSendData, 0, sizeof (ctrlGrpSendData));
            memset(&cartPosRspData, 0, sizeof (cartPosRspData));
            controlGroup = getInt32(inBuffer, 12);
            ctrlGrpSendData.sCtrlGrp = controlGroup;
            ret = mpGetCartPos(&ctrlGrpSendData, &cartPosRspData);
            setInt32(outBuffer, 0, 54);
            setInt32(outBuffer, 4, ret);
            for (i = 0; i < 6; i++) {
                setInt32(outBuffer, 8 + 4 * i, cartPosRspData.lPos[i]);
            }
            setInt16(outBuffer, 56, cartPosRspData.sConfig);
            sendRet = sendN(acceptHandle, outBuffer, 58, 0);
            if (sendRet != 58) {
                fprintf(stderr, "sendRet = %d != 58\n", sendRet);
                return -1;
            }
            break;

        case SYS1_GET_CURRENT_PULSE_POS:
            if (msgSize != 12) {
                fprintf(stderr, "invalid msgSize for mpMotTargetClear = %d != 12\n", msgSize);
                return -1;
            }
            memset(&ctrlGrpSendData, 0, sizeof (ctrlGrpSendData));
            memset(&pulsePosRspData, 0, sizeof (pulsePosRspData));
            controlGroup = getInt32(inBuffer, 12);
            ctrlGrpSendData.sCtrlGrp = controlGroup;
            ret = mpGetPulsePos(&ctrlGrpSendData, &pulsePosRspData);
            setInt32(outBuffer, 0, 68);
            setInt32(outBuffer, 4, ret);
            for (i = 0; i < 8; i++) {
                setInt32(outBuffer, 8 + 4 * i, pulsePosRspData.lPos[i]);
            }
            sendRet = sendN(acceptHandle, outBuffer, 72, 0);
            if (sendRet != 72) {
                fprintf(stderr, "sendRet = %d != 72\n", sendRet);
                return -1;
            }
            break;

        case SYS1_GET_CURRENT_FEEDBACK_PULSE_POS:
            if (msgSize != 12) {
                fprintf(stderr, "invalid msgSize for mpMotTargetClear = %d != 12\n", msgSize);
                return -1;
            }
            memset(&ctrlGrpSendData, 0, sizeof (ctrlGrpSendData));
            memset(&fbPulsePosRspData, 0, sizeof (fbPulsePosRspData));
            controlGroup = getInt32(inBuffer, 12);
            ctrlGrpSendData.sCtrlGrp = controlGroup;
            ret = mpGetFBPulsePos(&ctrlGrpSendData, &fbPulsePosRspData);
            setInt32(outBuffer, 0, 68);
            setInt32(outBuffer, 4, ret);
            for (i = 0; i < 8; i++) {
                setInt32(outBuffer, 8 + 4 * i, fbPulsePosRspData.lPos[i]);
            }
            sendRet = sendN(acceptHandle, outBuffer, 72, 0);
            if (sendRet != 72) {
                fprintf(stderr, "sendRet = %d != 72\n", sendRet);
                return -1;
            }
            break;

        case SYS1_GET_DEG_POS_EX:
            if (msgSize != 12) {
                fprintf(stderr, "invalid msgSize for mpMotTargetClear = %d != 12\n", msgSize);
                return -1;
            }
            memset(&ctrlGrpSendData, 0, sizeof (ctrlGrpSendData));
            memset(&degPosRspDataEx, 0, sizeof (degPosRspDataEx));
            controlGroup = getInt32(inBuffer, 12);
            ctrlGrpSendData.sCtrlGrp = controlGroup;
            ret = mpGetDegPosEx(&ctrlGrpSendData, &degPosRspDataEx);
            setInt32(outBuffer, 0, 132);
            setInt32(outBuffer, 4, ret);
            for (i = 0; i < 8; i++) {
                setInt32(outBuffer, 8 + 4 * i, degPosRspDataEx.lDegPos[i]);
            }
            for (i = 0; i < 8; i++) {
                setInt32(outBuffer, 72 + 4 * i, degPosRspDataEx.lDegUnit[i]);
            }
            sendRet = sendN(acceptHandle, outBuffer, 136, 0);
            if (sendRet != 136) {
                fprintf(stderr, "sendRet = %d != 136\n", sendRet);
                return -1;
            }
            break;

        default:
            fprintf(stderr, "invalid sys1 function type = %d\n", type);
            return -1;
    }
    return 0;
}

// Return 0 for success, anything else will be treated like a fatal error closing
// the connection.

int handleMotFunctionRequest(int acceptHandle, char *inBuffer, char *outBuffer, int type, int msgSize) {
    int32_t ret = -1;
    int32_t options = 0;
    int32_t controlGroup = 0;
    int32_t timeout = 0;
    int sendRet = 0;
    MP_TARGET target;
    MP_SPEED speed;
    int32_t grpNo = 0;
    int32_t aux = 0;
    int32_t coordType = 0;
    int32_t tool = 0;
    int32_t taskNo = 0;
    int recvId = 0;
    int i = 0;
    switch (type) {
        case MOT_START:
            if (msgSize != 12) {
                fprintf(stderr, "invalid msgSize for mpMotStart = %d != 12\n", msgSize);
                return -1;
            }
            options = getInt32(inBuffer, 12);
            ret = mpMotStart(options);
            setInt32(outBuffer, 0, 4);
            setInt32(outBuffer, 4, ret);
            sendRet = sendN(acceptHandle, outBuffer, 8, 0);
            if (sendRet != 8) {
                fprintf(stderr, "sendRet = %d != 8\n", sendRet);
                return -1;
            }
            break;

        case MOT_STOP:
            if (msgSize != 12) {
                fprintf(stderr, "invalid msgSize for mpMotStop = %d != 12\n", msgSize);
                return -1;
            }
            options = getInt32(inBuffer, 12);
            ret = mpMotStop(options);
            setInt32(outBuffer, 0, 4);
            setInt32(outBuffer, 4, ret);
            sendRet = sendN(acceptHandle, outBuffer, 8, 0);
            if (sendRet != 8) {
                fprintf(stderr, "sendRet = %d != 8\n", sendRet);
                return -1;
            }
            break;

        case MOT_TARGET_CLEAR:
            if (msgSize != 16) {
                fprintf(stderr, "invalid msgSize for mpMotTargetClear = %d != 16\n", msgSize);
                return -1;
            }
            controlGroup = getInt32(inBuffer, 12);
            options = getInt32(inBuffer, 16);
            ret = mpMotTargetClear(controlGroup, options);
            setInt32(outBuffer, 0, 4);
            setInt32(outBuffer, 4, ret);
            sendRet = sendN(acceptHandle, outBuffer, 8, 0);
            if (sendRet != 8) {
                fprintf(stderr, "sendRet = %d != 8\n", sendRet);
                return -1;
            }
            break;

        case MOT_JOINT_TARGET_SEND:
            if (msgSize != 88) {
                fprintf(stderr, "invalid msgSize for mpMotTargetSend = %d != 88\n", msgSize);
                return -1;
            }
            memset(&target, 0, sizeof (target));
            controlGroup = getInt32(inBuffer, 12);
            target.id = getInt32(inBuffer, 16);
            target.intp = getInt32(inBuffer, 20);
            for (i = 0; i < 8 /* MP_GRP_AXES_NUM */; i++) {
                target.dst.joint[i] = getInt32(inBuffer, 24 + (i * 4));
            }
            for (i = 0; i < 8 /* MP_GRP_AXES_NUM */; i++) {
                target.aux.joint[i] = getInt32(inBuffer, 56 + (i * 4));
            }
            timeout = getInt32(inBuffer, 88);
            ret = mpMotTargetSend(controlGroup, &target, timeout);
            setInt32(outBuffer, 0, 4);
            setInt32(outBuffer, 4, ret);
            sendRet = sendN(acceptHandle, outBuffer, 8, 0);
            if (sendRet != 8) {
                fprintf(stderr, "sendRet = %d != 8\n", sendRet);
                return -1;
            }
            break;

        case MOT_COORD_TARGET_SEND:
            if (msgSize != 88) {
                fprintf(stderr, "invalid msgSize for mpMotTargetSend = %d != 88\n", msgSize);
                return -1;
            }
            memset(&target, 0, sizeof (target));
            controlGroup = getInt32(inBuffer, 12);
            target.id = getInt32(inBuffer, 16);
            target.intp = getInt32(inBuffer, 20);
            target.dst.coord.x = getInt32(inBuffer, 24);
            target.dst.coord.y = getInt32(inBuffer, 28);
            target.dst.coord.z = getInt32(inBuffer, 32);
            target.dst.coord.rx = getInt32(inBuffer, 36);
            target.dst.coord.ry = getInt32(inBuffer, 40);
            target.dst.coord.rz = getInt32(inBuffer, 44);
            target.dst.coord.ex1 = getInt32(inBuffer, 48);
            target.dst.coord.ex2 = getInt32(inBuffer, 52);
            target.aux.coord.x = getInt32(inBuffer, 56);
            target.aux.coord.y = getInt32(inBuffer, 60);
            target.aux.coord.z = getInt32(inBuffer, 64);
            target.aux.coord.rx = getInt32(inBuffer, 68);
            target.aux.coord.ry = getInt32(inBuffer, 72);
            target.aux.coord.rz = getInt32(inBuffer, 76);
            target.aux.coord.ex1 = getInt32(inBuffer, 80);
            target.aux.coord.ex2 = getInt32(inBuffer, 84);
            timeout = getInt32(inBuffer, 88);
            ret = mpMotTargetSend(controlGroup, &target, timeout);
            setInt32(outBuffer, 0, 4);
            setInt32(outBuffer, 4, ret);
            sendRet = sendN(acceptHandle, outBuffer, 8, 0);
            if (sendRet != 8) {
                fprintf(stderr, "sendRet = %d != 8\n", sendRet);
                return -1;
            }
            break;

        case MOT_TARGET_RECEIVE:
            if (msgSize != 24) {
                fprintf(stderr, "invalid msgSize for mpMotTargetReceive = %d != 24\n", msgSize);
                return -1;
            }
            grpNo = getInt32(inBuffer, 12);
            target.id = getInt32(inBuffer, 16);
            timeout = getInt32(inBuffer, 20);
            options = getInt32(inBuffer, 24);
            ret = mpMotTargetReceive(grpNo, target.id, &recvId, timeout, options);
            setInt32(outBuffer, 0, 8);
            setInt32(outBuffer, 4, ret);
            setInt32(outBuffer, 8, recvId);
            sendRet = sendN(acceptHandle, outBuffer, 12, 0);
            if (sendRet != 12) {
                fprintf(stderr, "sendRet = %d != 12\n", sendRet);
                return -1;
            }
            break;

        case MOT_SET_COORD:
            if (msgSize != 20) {
                fprintf(stderr, "invalid msgSize for mpMotSetCoord = %d != 20\n", msgSize);
                return -1;
            }
            grpNo = getInt32(inBuffer, 12);
            coordType = getInt32(inBuffer, 16);
            aux = getInt32(inBuffer, 20);
            ret = mpMotSetCoord(grpNo, coordType, aux);
            setInt32(outBuffer, 0, 4);
            setInt32(outBuffer, 4, ret);
            sendRet = sendN(acceptHandle, outBuffer, 8, 0);
            if (sendRet != 8) {
                fprintf(stderr, "sendRet = %d != 8\n", sendRet);
                return -1;
            }
            break;

        case MOT_SET_TOOL:
            if (msgSize != 16) {
                fprintf(stderr, "invalid msgSize for mpMotSetTool = %d != 16\n", msgSize);
                return -1;
            }
            grpNo = getInt32(inBuffer, 12);
            tool = getInt32(inBuffer, 16);
            ret = mpMotSetTool(grpNo, tool);
            setInt32(outBuffer, 0, 4);
            setInt32(outBuffer, 4, ret);
            sendRet = sendN(acceptHandle, outBuffer, 8, 0);
            if (sendRet != 8) {
                fprintf(stderr, "sendRet = %d != 8\n", sendRet);
                return -1;
            }
            break;

        case MOT_SET_SPEED:
            if (msgSize != 32) {
                fprintf(stderr, "invalid msgSize for mpMotSetSpeed = %d != 32\n", msgSize);
                return -1;
            }
            grpNo = getInt32(inBuffer, 12);
            speed.vj = getInt32(inBuffer, 16);
            speed.v = getInt32(inBuffer, 24);
            speed.vr = getInt32(inBuffer, 32);
            ret = mpMotSetSpeed(grpNo, &speed);
            setInt32(outBuffer, 0, 4);
            setInt32(outBuffer, 4, ret);
            sendRet = sendN(acceptHandle, outBuffer, 8, 0);
            if (sendRet != 8) {
                fprintf(stderr, "sendRet = %d != 8\n", sendRet);
                return -1;
            }
            break;

        case MOT_SET_ORIGIN:
            if (msgSize != 16) {
                fprintf(stderr, "invalid msgSize for mpMotSetOrigin = %d != 16\n", msgSize);
                return -1;
            }
            grpNo = getInt32(inBuffer, 12);
            options = getInt32(inBuffer, 16);
            ret = mpMotSetOrigin(grpNo, options);
            setInt32(outBuffer, 0, 4);
            setInt32(outBuffer, 4, ret);
            sendRet = sendN(acceptHandle, outBuffer, 8, 0);
            if (sendRet != 8) {
                fprintf(stderr, "sendRet = %d != 8\n", sendRet);
                return -1;
            }
            break;

        case MOT_SET_TASK:
            if (msgSize != 16) {
                fprintf(stderr, "invalid msgSize for mpMotSetTask = %d != 16\n", msgSize);
                return -1;
            }
            grpNo = getInt32(inBuffer, 12);
            taskNo = getInt32(inBuffer, 16);
            ret = mpMotSetTask(grpNo, taskNo);
            setInt32(outBuffer, 0, 4);
            setInt32(outBuffer, 4, ret);
            sendRet = sendN(acceptHandle, outBuffer, 8, 0);
            if (sendRet != 8) {
                fprintf(stderr, "sendRet = %d != 8\n", sendRet);
                return -1;
            }
            break;

        case MOT_SET_SYNC:
            if (msgSize != 20) {
                fprintf(stderr, "invalid msgSize for mpMotSetSync = %d != 20\n", msgSize);
                return -1;
            }
            grpNo = getInt32(inBuffer, 12);
            aux = getInt32(inBuffer, 16);
            options = getInt32(inBuffer, 20);
            ret = mpMotSetSync(grpNo, aux, options);
            setInt32(outBuffer, 0, 4);
            setInt32(outBuffer, 4, ret);
            sendRet = sendN(acceptHandle, outBuffer, 8, 0);
            if (sendRet != 8) {
                fprintf(stderr, "sendRet = %d != 8\n", sendRet);
                return -1;
            }
            break;

        case MOT_RESET_SYNC:
            if (msgSize != 12) {
                fprintf(stderr, "invalid msgSize for mpMotResetSync = %d != 12\n", msgSize);
                return -1;
            }
            grpNo = getInt32(inBuffer, 12);
            ret = mpMotResetSync(grpNo);
            setInt32(outBuffer, 0, 4);
            setInt32(outBuffer, 4, ret);
            sendRet = sendN(acceptHandle, outBuffer, 8, 0);
            if (sendRet != 8) {
                fprintf(stderr, "sendRet = %d != 8\n", sendRet);
                return -1;
            }
            break;

        default:
            fprintf(stderr, "invalid mot function type = %d\n", type);
            return -1;
    }
    return 0;
}

void handleSingleConnection(int acceptHandle) {
    char *inBuffer = (char *) malloc(BUFF_MAX + 1);
    char *outBuffer = (char *) malloc(BUFF_MAX + 1);
    int32_t count = 0;
    int32_t group = 0;
    int32_t type = 0;
    int failed = 0;
    int bytesRecv;
    int32_t msgSize;

    printf("acceptHandle=%d\n", acceptHandle);
    printf("sizeof(int)=%d\n", sizeof (int));
    printf("sizeof(long)=%d\n", sizeof (long));
    printf("sizeof(LONG)=%d\n", sizeof (LONG));
    printf("sizeof(ULONG)=%d\n", sizeof (ULONG));
    printf("sizeof(MP_CART_POS_RSP_DATA)=%d\n", sizeof (MP_CART_POS_RSP_DATA));
    printf("sizeof(MP_TARGET)=%d\n", sizeof (MP_TARGET));

    while (failed == 0) {

        memset(inBuffer, 0, BUFF_MAX + 1);
        bytesRecv = recvN(acceptHandle, inBuffer, 4, 0);
        if (bytesRecv != 4) {
            failed = 1;
            break;
        }

        msgSize = getInt32(inBuffer, 0);
        printf("msgSize=%d\n", msgSize);
        if (msgSize < 8 || msgSize >= (BUFF_MAX - 4)) {
            printf("Invalid msgSize\n");
            break;
        }

        bytesRecv = recvN(acceptHandle, inBuffer + 4, (int) msgSize, 0);

        if (bytesRecv != msgSize)
            break;

        group = getInt32(inBuffer, 4);
        printf("group=%d\n", group);
        type = getInt32(inBuffer, 8);
        printf("type=%d\n", type);
        count++;
        printf("count=%d\n", count);

        switch (group) {
            case MOT_FUNCTION_GROUP:
                failed = handleMotFunctionRequest(acceptHandle, inBuffer, outBuffer, type, msgSize);
                break;

            case SYS1_FUNCTION_GROUP:
                failed = handleSys1FunctionRequest(acceptHandle, inBuffer, outBuffer, type, msgSize);
                break;

            default:
                fprintf(stderr, "unrecognized group =%d\n", group);
                failed = 1;
                break;
        }
    }
    printf("Closing acceptHandle=%d\n", acceptHandle);
    mpClose(acceptHandle);
    free(inBuffer);
    free(outBuffer);
}
