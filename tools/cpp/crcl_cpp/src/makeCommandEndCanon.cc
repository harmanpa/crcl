#include "crcl_cpp/CRCLCommandInstanceClasses.hh"
#include "crcl_cpp/CRCLCommandsClasses.hh"

int main(int argc, char * argv[])
{
  XmlVersion * versionIn;
  XmlHeaderForCRCLCommandInstance * headerIn;
  CRCLCommandInstanceType * CRCLCommandInstanceIn;
  EndCanonType * EndCanonCommand;
  CRCLCommandInstanceFile * CRCLCommandInstanceFileIn;
  enum {BUFFERLEN = 1024};
  char buffer[BUFFERLEN];
  size_t left, start;

  versionIn = new XmlVersion(true);
  headerIn = new XmlHeaderForCRCLCommandInstance;
  CRCLCommandInstanceIn = new CRCLCommandInstanceType;
  CRCLCommandInstanceFileIn =
    new CRCLCommandInstanceFile(versionIn, headerIn, CRCLCommandInstanceIn);
  headerIn->location =
    new SchemaLocation("xsi", "../xmlSchemas/CRCLCommandInstance.xsd", false);
  EndCanonCommand = new EndCanonType;
  EndCanonCommand->Name = 0;
  EndCanonCommand->printTypp = true;
  EndCanonCommand->CommandID = new XmlPositiveInteger("3");
  CRCLCommandInstanceIn->Name = 0;
  CRCLCommandInstanceIn->CRCLCommand = EndCanonCommand;
  left = sizeof(buffer);
  start = 0;
  CRCLCommandInstanceFileIn->printSelf(buffer, &left, &start);
  printf("%s\n", buffer);
  return 0;
}
