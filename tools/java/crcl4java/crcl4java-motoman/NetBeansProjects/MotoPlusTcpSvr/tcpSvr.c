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
#define PORT        11000

void tcpSvr(void) {

    runAcceptTcpClientsTask(PORT);

    mpSuspendSelf;
}

static int taskIdArray[100];
static int taskSpawnCounter = 0;

void runAcceptTcpClientsTask(ULONG portNo) {
    int sockHandle;
    struct sockaddr_in serverSockAddr;
    int rc;

    printf("TCP server starting for port %ld\n",portNo);

    sockHandle = mpSocket(AF_INET, SOCK_STREAM, 0);
    if (sockHandle < 0){
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
    rc = mpBind(sockHandle, (struct sockaddr *) &serverSockAddr, sizeof (serverSockAddr));

    if (rc < 0) {
        printf("Bind returned %d\n", rc);
        printf("Error = %s\n", strerror(errno));
        goto closeSockHandle;
    }

    rc = mpListen(sockHandle, SOMAXCONN);
    if (rc < 0) {
        printf("Listen returned %d\n", rc);
        printf("Error = %s\n", strerror(errno));
        goto closeSockHandle;
    }

    while (1) {
        int acceptHandle;
        struct sockaddr_in clientSockAddr;
        int sizeofSockAddr;

        memset(&clientSockAddr, 0, sizeof (clientSockAddr));
        sizeofSockAddr = sizeof (clientSockAddr);

        acceptHandle = mpAccept(sockHandle, (struct sockaddr *) &clientSockAddr, &sizeofSockAddr);

        if (acceptHandle < 0)
            break;

        taskIdArray[taskSpawnCounter] = mpCreateTask(MP_PRI_TIME_NORMAL, MP_STACK_SIZE, (FUNCPTR) handleSingleConnection,
                acceptHandle, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        taskSpawnCounter++;

    }
closeSockHandle:
    mpClose(sockHandle);

    return;
}

