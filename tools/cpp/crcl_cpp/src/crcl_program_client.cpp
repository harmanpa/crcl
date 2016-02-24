#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "crcl_cpp/CRCLProgramInstanceClasses.hh"
#include "crcl_cpp/CRCLCommandInstanceClasses.hh"
#include "crcl_cpp/CRCLCommandsClasses.hh"

extern CRCLProgramFile *CRCLProgramTree;
extern FILE *yyin;
extern int yyparse();
extern void yylex_destroy();

int main(int argc, char *argv[])
{
  FILE *inFile;
  XmlVersion *versionIn;
  XmlHeaderForCRCLCommandInstance *headerIn;
  CRCLCommandInstanceType *CRCLCommandInstanceIn;
  CRCLCommandInstanceFile *CRCLCommandInstanceFileIn;

  if (argc != 2) {
    fprintf(stderr, "Usage: %s <data file name>\n", argv[0]);
    return 1;
  }

  inFile = fopen(argv[1], "r");
  if (NULL == inFile) {
    fprintf(stderr, "unable to open file %s for reading\n", argv[1]);
    return 1;
  }

  yyin = inFile;
  yyparse();
  fclose(inFile);

  versionIn = new XmlVersion(true);
  headerIn = new XmlHeaderForCRCLCommandInstance;
  headerIn->location = new SchemaLocation("xsi", "CRCLCommandInstance.xsd", false);
  CRCLCommandInstanceIn = new CRCLCommandInstanceType;
  CRCLCommandInstanceFileIn = new CRCLCommandInstanceFile(versionIn, headerIn, CRCLCommandInstanceIn);

  for (std::list<MiddleCommandType *>::iterator iter = CRCLProgramTree->CRCLProgram->MiddleCommand->begin(); iter != CRCLProgramTree->CRCLProgram->MiddleCommand->end(); iter++) {
  char buffer[1024];
  size_t left, start;

#if 0
  WHEREWASI:
  You need to do something like this for each command:
  EndCanonCommand = new EndCanonType;
  EndCanonCommand->Name = 0;
  EndCanonCommand->printTypp = true;
  EndCanonCommand->CommandID = new XmlPositiveInteger("3");
  CRCLCommandInstanceIn->Name = 0;
  CRCLCommandInstanceIn->CRCLCommand = EndCanonCommand;
#endif

#define DO_IT(TYPE)					\
    if (dynamic_cast<TYPE *>(*iter)) {			\
      left = sizeof(buffer), start = 0;					\
      (dynamic_cast<TYPE *>(*iter))->printSelf(buffer, &left, &start);	\
      printf("%s\n", buffer);						\
      continue;								\
    }
    DO_IT(ActuateJointsType);
    DO_IT(CloseToolChangerType);
    DO_IT(ConfigureJointReportsType);
    DO_IT(DwellType);
    DO_IT(GetStatusType);
    DO_IT(MessageType);
    DO_IT(MoveScrewType);
    DO_IT(MoveThroughToType);
    DO_IT(MoveToType);
    DO_IT(OpenToolChangerType);
    DO_IT(RunProgramType);
    DO_IT(SetAngleUnitsType);
    DO_IT(SetEndEffectorParametersType);
    DO_IT(SetEndEffectorType);
    DO_IT(SetEndPoseToleranceType);
    DO_IT(SetForceUnitsType);
    DO_IT(SetIntermediatePoseToleranceType);
    DO_IT(SetLengthUnitsType);
    DO_IT(SetMotionCoordinationType);
    DO_IT(SetRobotParametersType);
    DO_IT(SetRotAccelType);
    DO_IT(SetRotSpeedType);
    DO_IT(SetTorqueUnitsType);
    DO_IT(SetTransAccelType);
    DO_IT(SetTransSpeedType);
    DO_IT(StopMotionType);
    // else unknown type
    printf("unknown type\n");
  } // for (iter)

  delete CRCLProgramTree;
  yylex_destroy();

  return 0;
}
