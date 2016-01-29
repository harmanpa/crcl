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

#include <vector>

#include <tf/transform_datatypes.h>

#include <crcl_cpp/CRCLCommandInstanceClasses.hh>
#include <crcl_cpp/CRCLCommandsClasses.hh>
#include <crcl_cpp/crcl_server.h>

// ----------------------

int CRCLPose::setXYZ(double x, double y, double z)
{
  tran.setX(x);
  tran.setY(y);
  tran.setZ(z);

  return 0;
}

int CRCLPose::setRPY(double r, double p, double y)
{
  rot.setRPY(r, p, y);
}

int CRCLPose::setVec(double xx, double xy, double xz, double zx, double zy, double zz)
{
  tf::Vector3 xaxis(xx, xy, xz);
  tf::Vector3 zaxis(zx, zy, zz);
  tf::Vector3 yaxis = zaxis.cross(xaxis);
  rot = tf::Matrix3x3(xx, yaxis.getX(), zx,
		      xy, yaxis.getY(), zy,
		      xz, yaxis.getZ(), zz);

  return 0;
}

int CRCLPose::getXYZ(double *x, double *y, double *z)
{
  *x = tran.getX(), *y = tran.getY(), *z = tran.getZ();
}

int CRCLPose::getRPY(double *r, double *p, double *y)
{
  double _r, _p, _y;

  rot.getRPY(_r, _p, _y);
  *r = _r, *p = _p, *y = _y;

  return 0;
}

int CRCLPose::getVec(double *xx, double *xy, double *xz, double *zx, double *zy, double *zz)
{
  tf::Vector3 vec;

  vec = rot.getColumn(0);
  *xx = vec.getX(), *xy = vec.getY(), *xz = vec.getZ();

  vec = rot.getColumn(2);
  *zx = vec.getX(), *zy = vec.getY(), *zz = vec.getZ();

  return 0;
}

// ---------------------------

CRCLRobotModel::CRCLRobotModel()
{
  jointNumber = 0;
}

CRCLRobotModel::~CRCLRobotModel(void)
{
}

int CRCLRobotModel::setJointNumber(int _jointNumber)
{
  CRCLJointModel j;

  jointNumber = _jointNumber;

  for (int t = 0; t < jointNumber; t++) {
    joints.push_back(j);
  }
  
  return 0;
}

int CRCLRobotModel::setJointPosition(int index, double value)
{
  if (index < 0 || index >= joints.size()) return -1;
  joints[index].setPosition(value);
  return 0;
}

int CRCLRobotModel::getJointPosition(int index, double *value)
{
  if (index < 0 || index >= joints.size()) return -1;
  joints[index].getPosition(value);
  return 0;
}

// ----------------------

// number of digits occupied by integer type 'x'
#define DIGITS_IN(x) (sizeof(x) * 3 + 1)

// results are volatile, so copy them right away
char *intToStr(int i)
{
  static char str[DIGITS_IN(int)];
  sprintf(str, "%i", i);
  return str;
}

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

int CRCLStatus::setJointNumber(int _jointNumber)
{
  jointNumber = _jointNumber;

  for (int t = 1; t <= jointNumber; t++) {
    JointStatusesIn->JointStatus->push_back(new JointStatusType(NULL, new XmlPositiveInteger(intToStr(t)), new XmlDecimal(0.0), new XmlDecimal(0.0), new XmlDecimal(0.0)));
  }

  return 0;
}

int CRCLStatus::setPose(double x, double y, double z, double r, double p, double w)
{
  tf::Matrix3x3 m;
  tf::Vector3 xaxis, zaxis;

  PoseIn->Pose->Point->X->val = x;
  PoseIn->Pose->Point->Y->val = y;
  PoseIn->Pose->Point->Z->val = z;

  m.setRPY(r, p, w);
  xaxis = m.getColumn(0);
  zaxis = m.getColumn(2);

  PoseIn->Pose->XAxis->I->val = xaxis.getX();
  PoseIn->Pose->XAxis->J->val = xaxis.getY();
  PoseIn->Pose->XAxis->K->val = xaxis.getZ();

  PoseIn->Pose->ZAxis->I->val = zaxis.getX();
  PoseIn->Pose->ZAxis->J->val = zaxis.getY();
  PoseIn->Pose->ZAxis->K->val = zaxis.getZ();

  return 0;
}

void CRCLStatus::print(void)
{
  enum {BIG_ENOUGH = 10000};
  char statusMessage[BIG_ENOUGH];
  size_t left, start;

  left = sizeof(statusMessage);
  start = 0;

  CRCLStatusFileIn->printSelf(statusMessage, &left, &start);
  printf("%s", statusMessage);
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

int CRCLServer::setJointNumber(int jointNumber)
{
  robotModel.setJointNumber(jointNumber);
  status.setJointNumber(jointNumber);

  return 0;
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

  _fd = socket_get_connection_id(server_fd);
  if (_fd < 0) return -1;
  client_fd = _fd;

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

void CRCLServer::printStatus(void)
{
  status.print();
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
