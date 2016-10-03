//mpMain.c
//
//This contains mpUsrRoot which is the entry point for your MotoPlus application

//ADDITIONAL INCLUDE FILES 
//(Note that motoPlus.h should be included in every source file)
#include "motoPlus.h"
#include "tcpSvr.h"

//GLOBAL DATA DEFINITIONS
int nTaskID1;

//FUNCTION PROTOTYPES
extern void tcpSvr(void);

//FUNCTION DEFINITIONS
void mpUsrRoot(int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8, int arg9, int arg10)
{	
	//TODO: Add additional intialization routines.

	//Creates and starts a new task in a seperate thread of execution.
	//All arguments will be passed to the new task if the function
	//prototype will accept them.
	nTaskID1 = mpCreateTask(MP_PRI_TIME_NORMAL, MP_STACK_SIZE, (FUNCPTR)tcpSvr,
						arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
	
	//Ends the initialization task.
	mpExitUsrRoot;
}
