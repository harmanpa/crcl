AM_CPPFLAGS = -I. -I.. -I../Libraries -I../Libraries/CRPI -I../Libraries/Math -std=gnu++11 -Wno-sign-compare -Wno-unused-variable -Wno-unused-value -Wno-return-type -Wno-parentheses -Wno-format-security -Wno-reorder -Wno-format-contains-nul -Wno-comment -Wno-unknown-pragmas -Wno-unused-but-set-variable -Wno-deprecated -Wno-deprecated-declarations -Wno-unused-function

bin_PROGRAMS = Application_CRPI_Test CRPI_Shell CRPI_Eval XMLInterface Small_Test Move_Test Fanuc_Test Motoman_Test

Application_CRPI_Test_SOURCES = \
../Applications/Application_CRPI_Test/Source.cpp

Application_CRPI_Test_LDADD = ../lib/libCRPI.a ../lib/libMath.a ../lib/libSerial.a ../lib/libCRCL.a ../lib/libulapi.a -ldl -lpthread

CRPI_Eval_SOURCES = \
../Applications/CRPI_Eval/crpi_eval.cpp

CRPI_Eval_LDADD = ../lib/libCRPI.a ../lib/libMath.a ../lib/libSerial.a ../lib/libCRCL.a ../lib/libulapi.a -ldl -lpthread

XMLInterface_SOURCES = \
../Applications/XMLInterface/XMLInterface.cpp

XMLInterface_LDADD = ../lib/libCRPI.a ../lib/libMath.a ../lib/libSerial.a ../lib/libCRCL.a ../lib/libulapi.a -ldl -lpthread

Small_Test_SOURCES = \
../Applications/Application_CRPI_Test/Small.cpp

Small_Test_LDADD = ../lib/libCRPI.a ../lib/libMath.a ../lib/libSerial.a ../lib/libCRCL.a ../lib/libulapi.a -ldl -lpthread

CRPI_Shell_SOURCES = \
../Applications/Application_CRPI_Test/CRPI_Shell.cpp

CRPI_Shell_LDADD = ../lib/libCRPI.a ../lib/libMath.a ../lib/libSerial.a ../lib/libCRCL.a ../lib/libulapi.a -ldl -lpthread

Move_Test_SOURCES = \
../Applications/Application_CRPI_Test/Move_Test.cpp

Move_Test_LDADD = ../lib/libCRPI.a ../lib/libMath.a ../lib/libSerial.a ../lib/libCRCL.a ../lib/libulapi.a -ldl -lpthread

Fanuc_Test_SOURCES = \
../Applications/Application_CRPI_Test/Fanuc_Test.cpp

Fanuc_Test_LDADD = ../lib/libCRPI.a ../lib/libMath.a ../lib/libSerial.a ../lib/libCRCL.a ../lib/libulapi.a -ldl -lpthread

Motoman_Test_SOURCES = \
../Applications/Application_CRPI_Test/Motoman_Test.cpp

Motoman_Test_LDADD = ../lib/libCRPI.a ../lib/libMath.a ../lib/libSerial.a ../lib/libCRCL.a ../lib/libulapi.a -ldl -lpthread

bin_PROGRAMS += CRCL_Status_Reader CRCL_Command_Reader CRCL_Robot_Simulator CRCL_Client_Shell

CRCL_Status_Reader_SOURCES = \
../Applications/CRCL_Test/CRCL_Status_Reader.cpp

CRCL_Status_Reader_LDADD = ../lib/libCRCL.a ../lib/libulapi.a -ldl -lpthread

CRCL_Command_Reader_SOURCES = \
../Applications/CRCL_Test/CRCL_Command_Reader.cpp

CRCL_Command_Reader_LDADD = ../lib/libCRCL.a ../lib/libulapi.a -ldl -lpthread

CRCL_Robot_Simulator_SOURCES = \
../Applications/CRCL_Test/CRCL_Robot_Simulator.cpp

CRCL_Robot_Simulator_LDADD = ../lib/libCRCL.a ../lib/libulapi.a -ldl -lpthread

CRCL_Client_Shell_SOURCES = \
../Applications/CRCL_Test/CRCL_Client_Shell.cpp

CRCL_Client_Shell_LDADD = ../lib/libCRCL.a ../lib/libulapi.a -ldl -lpthread

bin_PROGRAMS += URDF_CRPI_Test

URDF_CRPI_Test_SOURCES = \
../Applications/XMLInterface/URDF_CRPI_Test.cpp

URDF_CRPI_Test_LDADD = ../lib/libCRCL.a



