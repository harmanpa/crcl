/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

#include "motoPlus.h"
#include "remoteFunctions.h"

// I had issues so I can't trust stdlib.h to declare this properly across platforms.
extern void *malloc(size_t);

// for API & FUNCTIONS
void runAcceptTcpClientsTask(ULONG portNo);
#define PORT        12222

void tcpSvr(void) {

    runAcceptTcpClientsTask(PORT);

    mpSuspendSelf;
}


#define MAX_CLIENT_HANDLES (256)
static int clientHandles[MAX_CLIENT_HANDLES];
static int maxClientHandleIndex = 0;

void printTcpSvrInfo() {
    printf("tcpSvr: sizeof(short)=%ld\n", sizeof (short));
    printf("tcpSvr: sizeof(int)=%ld\n", sizeof (int));
    printf("tcpSvr: sizeof(long)=%ld\n", sizeof (long));
    printf("tcpSvr: sizeof(LONG)=%ld\n", sizeof (LONG));
    printf("tcpSvr: sizeof(ULONG)=%ld\n", sizeof (ULONG));
    printf("tcpSvr: angleLen=%d\n", getAngleLen());
    printf("tcpSvr: MP_GRP_AXES_NUM=%d\n", MP_GRP_AXES_NUM);
    printf("tcpSvr: MP_FCS_AXES_NUM=%d\n", MP_FCS_AXES_NUM);
    printf("tcpSvr: sizeof(MP_CART_POS_RSP_DATA)=%ld\n", sizeof (MP_CART_POS_RSP_DATA));
    printf("tcpSvr: sizeof(MP_TARGET)=%ld\n", sizeof (MP_TARGET));
    printf("tcpSvr: sizeof(MP_COORD)=%ld\n", sizeof (MP_COORD));
    printf("tcpSvr: MP_R1_GID = %d\n", MP_R1_GID);
    printf("tcpSvr: mpCtrlGrpId2GrpNo(MP_R1_GID) = %d\n", mpCtrlGrpId2GrpNo(MP_R1_GID));
    printf("tcpSvr: MP_R2_GID = %d\n", MP_R2_GID);
    printf("tcpSvr: mpCtrlGrpId2GrpNo(MP_R2_GID) = %d\n", mpCtrlGrpId2GrpNo(MP_R2_GID));
    printf("tcpSvr: MP_B1_GID = %d\n", MP_B1_GID);
    printf("tcpSvr: mpCtrlGrpId2GrpNo(MP_B1_GID) = %d\n", mpCtrlGrpId2GrpNo(MP_B1_GID));
    printf("tcpSvr: MP_B2_GID = %d\n", MP_B2_GID);
    printf("tcpSvr: mpCtrlGrpId2GrpNo(MP_B2_GID) = %d\n", mpCtrlGrpId2GrpNo(MP_B2_GID));
    printf("tcpSvr: MP_S1_GID = %d\n", MP_S1_GID);
    printf("tcpSvr: mpCtrlGrpId2GrpNo(MP_S1_GID) = %d\n", mpCtrlGrpId2GrpNo(MP_S1_GID));
    printf("tcpSvr: MP_S2_GID = %d\n", MP_S2_GID);
    printf("tcpSvr: mpCtrlGrpId2GrpNo(MP_S2_GID) = %d\n", mpCtrlGrpId2GrpNo(MP_S2_GID));
}

void runAcceptTcpClientsTask(ULONG portNo) {
    int sockHandle;
    struct sockaddr_in serverSockAddr;
    int rc;

    int acceptHandle;
    struct sockaddr_in clientSockAddr;
    int sizeofSockAddr;
    int selectRet;
    int maxFd = 0;
    fd_set readFdSet;
    fd_set exceptFdSet;
    int emptyIndexFound = 0;
    int i = 0;
    int requestsHandled = 0;
    int exceptCount = 0;
    int failCount = 0;
    printf("sizeof(clientHandles)=  %ld\n", sizeof (clientHandles));
    memset(clientHandles, 0, sizeof (clientHandles));

    /*
        printf("sizeof(taskIdArray)=  %ld\n", sizeof (taskIdArray));
        memset(taskIdArray, 0, sizeof (taskIdArray));
     */

    printf("TCP server starting for port %ld\n", portNo);

    sockHandle = mpSocket(AF_INET, SOCK_STREAM, 0);
    if (sockHandle < 0) {
        printf("socket returned %d\n", sockHandle);
        printf("Error = %s\n", strerror(errno));
        return;
    }

    memset(&serverSockAddr, 0, sizeof (serverSockAddr));
    serverSockAddr.sin_family = AF_INET;
    serverSockAddr.sin_addr.s_addr = INADDR_ANY;
    serverSockAddr.sin_port = mpHtons(portNo);
    /*
        printf("portNo=%us\n", serverSockAddr.sin_port);
     */
    printf("Binding socket.\n");
    rc = mpBind(sockHandle, (struct sockaddr *) &serverSockAddr, sizeof (serverSockAddr));

    if (rc < 0) {
        printf("Bind returned %d\n", rc);
        printf("Error = %s\n", strerror(errno));
        goto closeSockHandle;
    }

    printf("Listen socket.\n");
    rc = mpListen(sockHandle, SOMAXCONN);
    if (rc < 0) {
        printf("Listen returned %d\n", rc);
        printf("Error = %s\n", strerror(errno));
        goto closeSockHandle;
    }

    while (1) {

        FD_ZERO(&readFdSet);
        FD_ZERO(&exceptFdSet);

        FD_SET(sockHandle, &readFdSet);
        FD_SET(sockHandle, &exceptFdSet);
        maxFd = sockHandle;


        for (i = 0; i < maxClientHandleIndex; i++) {
            if (clientHandles[i] > 0) {
                FD_SET(clientHandles[i], &readFdSet);
                FD_SET(clientHandles[i], &exceptFdSet);
                maxFd = maxFd > clientHandles[i] ? maxFd : clientHandles[i];
            }
        }
        /*
                printf("Calling select(%d,%p,NULL,%p,NULL) ...\n", (maxFd + 1), &readFdSet, &exceptFdSet);
         */
        selectRet = mpSelect(maxFd + 1, &readFdSet, NULL, &exceptFdSet, NULL);

        /*
                printf("select returned %d\n", selectRet);
         */
        if (selectRet <= 0) {
            printf("mpSelect returned %d\n", selectRet);
            printf("Error = %s\n", strerror(errno));
            for (i = 0; i < maxClientHandleIndex; i++) {
                printf("clientHandles[i=%d]=%d\n",
                        i, clientHandles[i]);
                if (clientHandles[i] > 0) {
                    printf("closing clientHandles[i=%d]=%d\n",
                            i, clientHandles[i]);
                    mpClose(clientHandles[i]);
                }
                clientHandles[i] = 0;
                printf("setting clientHandles[i=%d]=%d\n",
                        i, clientHandles[i]);
            }
            printf("maxFd=%d\n", maxFd);
            printf("maxClientHandleIndex=%d\n", maxClientHandleIndex);
            maxClientHandleIndex = 0;
            printf("maxClientHandleIndex=%d\n", maxClientHandleIndex);
            continue;
        }
        if (FD_ISSET(sockHandle, &readFdSet)) {
            memset(&clientSockAddr, 0, sizeof (clientSockAddr));
            sizeofSockAddr = sizeof (clientSockAddr);
            acceptHandle = mpAccept(sockHandle, (struct sockaddr *) &clientSockAddr, &sizeofSockAddr);
            printf("tcpSvr: acceptHandle=%d\n", acceptHandle);
            printTcpSvrInfo();
            if (acceptHandle <= 0) {
                continue;
            }
            emptyIndexFound = 0;
            for (i = 0; i < maxClientHandleIndex; i++) {
                printf("clientHandles[i=%d]=%d\n",
                        i, clientHandles[i]);
                if (clientHandles[i] < 2) {
                    emptyIndexFound = 1;
                    clientHandles[i] = acceptHandle;
                    printf("Setting clientHandles[i=%d]=%d\n",
                            i, clientHandles[i]);
                    break;
                }
            }
            if (emptyIndexFound == 0) {
                if (maxClientHandleIndex >= MAX_CLIENT_HANDLES) {
                    printf("Too many open socket handle\n");
                    mpClose(acceptHandle);
                    for (i = 0; i < maxClientHandleIndex; i++) {
                        printf("clientHandles[i=%d]=%d\n",
                                i, clientHandles[i]);
                        if (clientHandles[i] > 0) {
                            mpClose(clientHandles[i]);
                        }
                        clientHandles[i] = 0;
                    }
                    maxClientHandleIndex = 0;
                    printf("sizeof(clientHandles)=  %ld\n", sizeof (clientHandles));
                    memset(clientHandles, 0, sizeof (clientHandles));
                    continue;
                }
                clientHandles[maxClientHandleIndex] = acceptHandle;
                printf("clientHandles[maxClientHandleIndex=%d]=%d\n",
                        maxClientHandleIndex, clientHandles[maxClientHandleIndex]);
                maxClientHandleIndex++;
                printf("maxClientHandleIndex=%d\n", maxClientHandleIndex);
            }
        }
        for (i = 0; i < maxClientHandleIndex; i++) {
            if (clientHandles[i] > 0) {
                if (FD_ISSET(clientHandles[i], &exceptFdSet)) {
                    printf("exceptFdSet set for clientHandle\n");
                    printf("Closing clientHandles[i=%d]=%d\n",
                            i, clientHandles[i]);
                    mpClose(clientHandles[i]);
                    clientHandles[i] = 0;
                }
                if (FD_ISSET(clientHandles[i], &readFdSet)) {
                    requestsHandled++;
                    if (requestsHandled % 25 == 0) {
                        printf("requestsHandled=%d\n",
                                requestsHandled);
                    }
                    if (handleSingleConnection(clientHandles[i]) != 0) {
                        failCount++;
                        printf("handleSingleConnection() returned non-zero\n");
                        printf("Closing clientHandles[i=%d]=%d\n",
                                i, clientHandles[i]);
                        printf("failCount=%d\n",
                                failCount);
                        printf("requestsHandled=%d\n",
                                requestsHandled);
                        mpClose(clientHandles[i]);
                        clientHandles[i] = 0;
                        printf("setting clientHandles[i=%d]=%d\n",
                                i, clientHandles[i]);
                    }
                }
            }
        }

        /*
                taskIdArray[taskSpawnCounter % 100] = mpCreateTask(MP_PRI_TIME_NORMAL, MP_STACK_SIZE, (FUNCPTR) handleSingleConnection,
                        acceptHandle, 0, 0, 0, 0, 0, 0, 0, 0, 0);
                printf("tcpSvr: taskSpawnCounter = %d\n", taskSpawnCounter);
                printf("tcpSvr: taskIdArray[taskSpawnCounter%%100] = %d\n", taskIdArray[taskSpawnCounter % 100]);
                taskSpawnCounter++;
                printf("tcpSvr: taskSpawnCounter = %d\n", taskSpawnCounter);
         */
    }
closeSockHandle:
    mpClose(sockHandle);

    printf("maxClientHandleIndex=%d\n",
            maxClientHandleIndex);
    printf("requestsHandled=%d\n",
            requestsHandled);
    return;
}

