#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include <unistd.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <arpa/inet.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <pthread.h>
#include <time.h>

#include <crcl_cpp/CRCLCommandInstanceClasses.hh>
#include <crcl_cpp/CRCLCommandsClasses.hh>
#include <crcl_cpp/crcl_server.h>

CRCLStatus::CRCLStatus(void)
{
  versionIn = new XmlVersion(true);
  headerIn = new XmlHeaderForCRCLStatus;
  CommandStatusIn = new CommandStatusType;
  JointStatusesIn = new JointStatusesType(NULL, new std::list<JointStatusType *>);
  PoseIn = new PoseStatusType;
  GripperStatusIn = new ParallelGripperStatusType(NULL, new XmlNMTOKEN("ParallelGripper"), new XmlDecimal(0.0));
  GripperStatusIn->printTypp = true;
  CRCLStatusIn = new CRCLStatusType(NULL, CommandStatusIn, JointStatusesIn, PoseIn, GripperStatusIn);
  CRCLStatusFileIn = new CRCLStatusFile(versionIn, headerIn, CRCLStatusIn);
  headerIn->location = new SchemaLocation("xsi", "CRCLStatus.xsd", false);

  CommandStatusIn->Name = NULL;
  CommandStatusIn->CommandID =  new XmlNonNegativeInteger("0");
  CommandStatusIn->StatusID = new XmlPositiveInteger("1");
  CommandStatusIn->CommandState = new CommandStateEnumType("CRCL_Working");

  PoseIn->Name = NULL;
  PoseIn->Pose = new PoseType();
  PoseIn->Pose->Point = new PointType(NULL, new XmlDecimal(0.0), new XmlDecimal(0.0), new XmlDecimal(0.0));
  PoseIn->Pose->XAxis = new VectorType(NULL, new XmlDecimal(1.0), new XmlDecimal(0.0), new XmlDecimal(0.0));
  PoseIn->Pose->ZAxis = new VectorType(NULL, new XmlDecimal(0.0), new XmlDecimal(0.0), new XmlDecimal(1.0));
}

CRCLStatus::~CRCLStatus(void)
{

}

int CRCLStatus::setXYZ(double x, double y, double z)
{
  PoseIn->Pose->Point->X->val = x;
  PoseIn->Pose->Point->Y->val = y;
  PoseIn->Pose->Point->Z->val = z;

  return 0;
}

int CRCLStatus::setVec(double xx, double xy, double xz, double zx, double zy, double zz)
{
  PoseIn->Pose->XAxis->I->val = xx;
  PoseIn->Pose->XAxis->J->val = xy;
  PoseIn->Pose->XAxis->K->val = xz;

  PoseIn->Pose->ZAxis->I->val = zx;
  PoseIn->Pose->ZAxis->J->val = zy;
  PoseIn->Pose->ZAxis->K->val = zz;

  return 0;
}

// number of digits occupied by integer type 'x'
#define DIGITS_IN(x) (sizeof(x) * 3 + 1)

// results are volatile, so copy them right away
char *intToStr(int i)
{
  static char str[DIGITS_IN(int)];
  sprintf(str, "%i", i);
  return str;
}

int CRCLStatus::setJointPosition(int number, double position)
{
  for (std::list<JointStatusType *>::iterator iter = JointStatusesIn->JointStatus->begin(); iter != JointStatusesIn->JointStatus->end(); iter++) {
    if (number == (*iter)->JointNumber->val) {
      (*iter)->JointPosition->val = position;
      return 0;
    }
  }
  // else there's no joint with this number, so add one
  JointStatusesIn->JointStatus->push_back(new JointStatusType(NULL, new XmlPositiveInteger(intToStr(number)), new XmlDecimal(position), new XmlDecimal(0.0), new XmlDecimal(0.0)));
  
  return 0;
}

int CRCLStatus::print(char *buffer, size_t len)
{
  size_t left, start;

  left = len;
  start = 0;

  CRCLStatusFileIn->printSelf(buffer, &left, &start);

  return left;
}

// ---------------------

// globals used by the parser
extern CRCLCommandInstanceFile *CRCLCommandInstanceTree;
extern int yyparse();
extern void yylex_destroy();
extern char *yyStringInputPointer;
extern char *yyStringInputEnd;

static int socket_get_server_id_on_interface(int port, const char *intf)
{
  int socket_fd;
  struct sockaddr_in myaddr;
  int on;
  struct linger mylinger = { 0 };
  enum {BACKLOG = 5};

  if (-1 == (socket_fd = socket(PF_INET, SOCK_STREAM, 0))) {
    perror("socket");
    return -1;
  }

  /*
    Turn off bind address checking, and allow
    port numbers to be reused - otherwise the
    TIME_WAIT phenomenon will prevent binding
    to these address.port combinations for
    (2 * MSL) seconds.
  */
  on = 1;
  if (-1 == 
      setsockopt(socket_fd,
		 SOL_SOCKET,
		 SO_REUSEADDR,
		 (const char *) &on,
		 sizeof(on))) {
    perror("setsockopt");
    close(socket_fd);
    return -1;
  }

  /*
    When connection is closed, there is a need
    to linger to ensure all data is transmitted.
  */
  mylinger.l_onoff = 1;
  mylinger.l_linger = 30;
  if (-1 ==
      setsockopt(socket_fd,
		 SOL_SOCKET,
		 SO_LINGER,
		 (const char *) &mylinger,
		 sizeof(struct linger))) {
    perror("setsockopt");
    close(socket_fd);
    return -1;
  }

  memset(&myaddr, 0, sizeof(struct sockaddr_in));
  myaddr.sin_family = PF_INET;
  if (NULL == intf) {
    myaddr.sin_addr.s_addr = htonl(INADDR_ANY);
  } else {
    myaddr.sin_addr.s_addr = inet_addr(intf);
  }
  myaddr.sin_port = htons(port);

  if (-1 == bind(socket_fd, (struct sockaddr *) &myaddr,
		 sizeof(struct sockaddr_in))) {
    perror("bind");
    close(socket_fd);
    return -1;
  }

  if (-1 == listen(socket_fd, BACKLOG)) {
    perror("listen");
    close(socket_fd);
    return -1;
  }

  return socket_fd;
}

static int socket_get_connection_id(int socket_fd)
{
  fd_set rfds;
  struct sockaddr_in client_addr;
  struct sockaddr client_info;
  unsigned int client_len;
  int client_fd;
  int retval;

  do {
    FD_ZERO(&rfds);
    FD_SET(socket_fd, &rfds);
    retval = select(socket_fd + 1, &rfds, NULL, NULL, NULL);
  } while (0 == retval ||
	   ! FD_ISSET(socket_fd, &rfds));

  if (retval == -1) {
    perror("select");
    return -1;
  }

  memset(&client_addr, 0, sizeof(struct sockaddr_in));
  client_len = sizeof(struct sockaddr_in);
  client_fd = 
    accept(socket_fd,
	   (struct sockaddr *) &client_addr, 
	   &client_len);
  if (-1 == client_fd) {
    perror("accept");
    return -1;
  }
  
  return client_fd;
}

static int task_start(pthread_t *task,
		      void *(taskcode)(void *),
		      void *taskarg,
		      int prio)
{
  pthread_attr_t attr;
  struct sched_param sched_param;

  pthread_attr_init(&attr);
  sched_param.sched_priority = prio;
  pthread_attr_setschedparam(&attr, &sched_param);
  pthread_create(task, &attr, taskcode, taskarg);

  return 0;
}

static void task_sleep(double secs)
{
  int isecs, insecs;
  struct timespec ts;

  isecs = (int) secs;
  insecs = (int) ((secs - isecs) * 1.0e9);

  ts.tv_sec = isecs;
  ts.tv_nsec = insecs;

  (void) nanosleep(&ts, NULL);
}

// -----------------

CRCLServer::CRCLServer(void)
{
  port = -1;
  server_fd = -1;
  client_fd = -1;
}

CRCLServer::~CRCLServer(void)
{
  if (-1 != client_fd) close(client_fd);
  if (-1 != server_fd) close(server_fd);
}

int CRCLServer::getServer(int _port)
{
  int _fd;

  port = _port;
  _fd = socket_get_server_id_on_interface(_port, NULL);
  if (_fd < 0) return -1;
  server_fd = _fd;

  return 0;
}

int CRCLServer::getConnection(void)
{
  int _fd;
  pthread_t pt;

  _fd = socket_get_connection_id(server_fd);
  if (_fd < 0) return -1;
  client_fd = _fd;

  task_start(&pt, reinterpret_cast<void *(*)(void *)>(&CRCLServer::reportStatus), this, 1);

  return 0;
}

int CRCLServer::readCommand(void)
{
  enum {BUFFERLEN = 1024};
  char inbuf[BUFFERLEN];
  int nchars;

  nchars = recv(client_fd, inbuf, sizeof(inbuf) - 1, 0);
  if (nchars <= 0) {
    close(client_fd);
    client_fd = -1;
    return -1;
  }
  if (nchars >= sizeof(inbuf)) {
    return -1;
  }

  inbuf[nchars] = 0;
  yyStringInputPointer = inbuf;
  yyStringInputEnd = inbuf + nchars;

  return 0;
}

CRCLCommandType *CRCLServer::parseCommand(void)
{
  pid_t pid;
  int status;

  // yyparse() calls exit() directly upon error, so we'll test it first
  pid = fork();
  if (0 == pid) {
    // try it in this child process first
    yyparse();
    // if we got here, it worked, so exit with a 0 status
    exit(0);
  }
  // else we're the parent, so check the child's exit code
  waitpid(pid, &status, 0);
  if (0 == WEXITSTATUS(status)) {
    // it worked, so do it again here
    yyparse();
    yylex_destroy();
    return CRCLCommandInstanceTree->CRCLCommandInstance->CRCLCommand;
  } else {
    // it didn't work
    return NULL;
  }

  return NULL;
}

int CRCLServer::writeStatus(void)
{
  enum {BUFFERLEN = 8192};
  char outbuf[BUFFERLEN];
  int nchars;

  if (client_fd < 0) return -1;

  nchars = status.print(outbuf, sizeof(outbuf));

#if 1
  send(client_fd, outbuf, nchars, 0);
#else
  printf("%s\n", outbuf);
#endif

  return 0;
}

void *CRCLServer::reportStatus(void *arg)
{
  int retval;

  while (true) {
    retval = writeStatus();
    if (0 != retval) break;
    task_sleep(1);
  }

  return NULL;
}

void CRCLServer::quit(void)
{
  if (-1 != client_fd) close(client_fd);
  if (-1 != server_fd) close(server_fd);

  port = -1;
  server_fd = -1;
  client_fd = -1;

  return;
}

int CRCLServer::HandleActuateJointsType(ActuateJointsType *cmd)
{
  return 0;
}

int CRCLServer::HandleCloseToolChangerType(CloseToolChangerType *cmd)
{
  return 0;
}

int CRCLServer::HandleConfigureJointReportsType(ConfigureJointReportsType *cmd)
{
  return 0;
}

int CRCLServer::HandleDwellType(DwellType *cmd)
{
  return 0;
}

int CRCLServer::HandleGetStatusType(GetStatusType *cmd)
{
  return 0;
}

int CRCLServer::HandleMessageType(MessageType *cmd)
{
  return 0;
}

int CRCLServer::HandleMoveScrewType(MoveScrewType *cmd)
{
  return 0;
}

int CRCLServer::HandleMoveThroughToType(MoveThroughToType *cmd)
{
  return 0;
}

int CRCLServer::HandleMoveToType(MoveToType *cmd)
{
  double x, y, z;
  double xi, xj, xk;
  double zi, zj, zk;

  x = cmd->EndPosition->Point->X->val;
  y = cmd->EndPosition->Point->Y->val;
  z = cmd->EndPosition->Point->Z->val;

  xi = cmd->EndPosition->XAxis->I->val;
  xj = cmd->EndPosition->XAxis->J->val;
  xk = cmd->EndPosition->XAxis->K->val;

  zi = cmd->EndPosition->ZAxis->I->val;
  zj = cmd->EndPosition->ZAxis->J->val;
  zk = cmd->EndPosition->ZAxis->K->val;

  status.setXYZ(x, y, z);
  status.setVec(xi, xj, xk, zi, zj, zk);
  status.setJointPosition(1, x);
  status.setJointPosition(2, y);
  status.setJointPosition(3, z);
  status.setJointPosition(4, xi);
  status.setJointPosition(5, xj);
  status.setJointPosition(6, xk);

  return 0;
}

int CRCLServer::HandleOpenToolChangerType(OpenToolChangerType *cmd)
{
  return 0;
}

int CRCLServer::HandleRunProgramType(RunProgramType *cmd)
{
  return 0;
}

int CRCLServer::HandleSetAngleUnitsType(SetAngleUnitsType *cmd)
{
  return 0;
}

int CRCLServer::HandleSetEndEffectorParametersType(SetEndEffectorParametersType *cmd)
{
  return 0;
}

int CRCLServer::HandleSetEndEffectorType(SetEndEffectorType *cmd)
{
  return 0;
}

int CRCLServer::HandleSetEndPoseToleranceType(SetEndPoseToleranceType *cmd)
{
  return 0;
}

int CRCLServer::HandleSetForceUnitsType(SetForceUnitsType *cmd)
{
  return 0;
}

int CRCLServer::HandleSetIntermediatePoseToleranceType(SetIntermediatePoseToleranceType *cmd)
{
  return 0;
}

int CRCLServer::HandleSetLengthUnitsType(SetLengthUnitsType *cmd)
{
  return 0;
}

int CRCLServer::HandleSetMotionCoordinationType(SetMotionCoordinationType *cmd)
{
  return 0;
}

int CRCLServer::HandleSetRobotParametersType(SetRobotParametersType *cmd)
{
  return 0;
}

int CRCLServer::HandleSetRotAccelType(SetRotAccelType *cmd)
{
  return 0;
}

int CRCLServer::HandleSetRotSpeedType(SetRotSpeedType *cmd)
{
  return 0;
}

int CRCLServer::HandleSetTorqueUnitsType(SetTorqueUnitsType *cmd)
{
  return 0;
}

int CRCLServer::HandleSetTransAccelType(SetTransAccelType *cmd)
{
  return 0;
}

int CRCLServer::HandleSetTransSpeedType(SetTransSpeedType *cmd)
{
  return 0;
}

int CRCLServer::HandleStopMotionType(StopMotionType *cmd)
{
  return 0;
}

