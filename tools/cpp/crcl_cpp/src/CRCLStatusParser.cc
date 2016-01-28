#include <stdio.h>   // fprintf
#include <string.h>  // strlen
#include <stdlib.h>  // exit
#include "crcl_cpp/CRCLStatusClasses.hh"
#define MAX_SIZE 10000000

extern CRCLStatusFile * CRCLStatusTree;
extern FILE * yyin;
extern int yyparse();
extern void yylex_destroy();
extern char * yyStringInputPointer;
extern char * yyStringInputEnd;

int main(       /* ARGUMENTS                                      */
 int argc,      /* one more than the number of command arguments  */
 char * argv[]) /* array of executable name and command arguments */
{
  std::string outFileName;
  FILE * inFile;
  FILE * outFile;
  char * inputString;
  int n;
  int stringSize;

  if (argc != 2)
    {
      fprintf(stderr, "Usage: %s <data file name>\n", argv[0]);
      exit(1);
    }
  for (stringSize = 10000; stringSize <= MAX_SIZE; stringSize *= 10)
    {
      inputString = new char[stringSize + 1];
      inFile = fopen(argv[1], "r");
      if (inFile == 0)
	{
	  fprintf(stderr, "unable to open file %s for reading\n", argv[1]);
	  exit(1);
	}
      for (n = 0; (((inputString[n] = getc(inFile)) != EOF) &&
		   (n < stringSize)); n++);
      fclose(inFile);
      if (n < stringSize)
	break;
      else
	delete [] inputString;
    }
  if (stringSize > MAX_SIZE)
    {
      fprintf(stderr,
	      "input file is too large (more than %d bytes), exiting\n",
	      MAX_SIZE);
      return 1;
    }
  inputString[n] = 0;
  yyStringInputPointer = inputString;
  yyStringInputEnd = (inputString + n);
  yyparse();
  outFileName = argv[1];
  outFileName.append("echo");
  outFile = fopen(outFileName.c_str(), "w");
  CRCLStatusTree->PRINTSELF;
  fclose(outFile);
  delete CRCLStatusTree;
  delete [] inputString;
  yylex_destroy();
  return 0;
}
