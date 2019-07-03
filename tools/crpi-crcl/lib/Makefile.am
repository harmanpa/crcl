AM_CPPFLAGS = -I. -I../Libraries -I../Libraries/CRPI -I../Libraries/Math -I ../Libraries/Clustering -I../Libraries/Clustering/Cluster -I../Libraries/Clustering/kMeans -I../Libraries/Clustering/Patterns -std=gnu++11 -Wno-sign-compare -Wno-unused-variable -Wno-unused-value -Wno-return-type -Wno-parentheses -Wno-format-security -Wno-reorder -Wno-format-contains-nul -Wno-comment -Wno-unknown-pragmas -Wno-unused-but-set-variable -Wno-deprecated -Wno-deprecated-declarations -Wno-unused-function

AM_CPPFLAGS += -I../Libraries/ThirdParty/OpenCV2/include
AM_CPPFLAGS += -I../Libraries/ThirdParty/MyoSDK/include

lib_LIBRARIES = libCRPI.a libMath.a libSerial.a libMotionPrims.a libRegistrationKit.a libHRI.a libMoCap.a

lib_LIBRARIES += libulapi.a

lib_LIBRARIES += libCRCL.a

# no crpi_demo_hack.cpp

libCRPI_a_SOURCES = \
../Libraries/CRPI/crcl_xml.cpp \
../Libraries/CRPI/crpi_abb.cpp \
../Libraries/CRPI/crpi_allegro.cpp \
../Libraries/CRPI/crpi.cpp \
../Libraries/CRPI/crpi_kuka_lwr.cpp \
../Libraries/CRPI/crpi_robot.cpp \
../Libraries/CRPI/crpi_robotiq.cpp \
../Libraries/CRPI/crpi_robot_xml.cpp \
../Libraries/CRPI/crpi_schunk_sdh.cpp \
../Libraries/CRPI/crpi_universal.cpp \
../Libraries/CRPI/crpi_xml.cpp \
../Libraries/CRPI/crpi_crcl_robot.cpp \
../Libraries/CRPI/CRCLCommon.h \
../Libraries/CRPI/crpi_abb.h \
../Libraries/CRPI/crpi_allegro.h \
../Libraries/CRPI/crpi_crcl_robot.h \
../Libraries/CRPI/crpi.h \
../Libraries/CRPI/crpi_kuka_lwr.h \
../Libraries/CRPI/crpi_robot.h \
../Libraries/CRPI/crpi_robotiq.h \
../Libraries/CRPI/crpi_robot_xml.h \
../Libraries/CRPI/crpi_schunk_sdh.h \
../Libraries/CRPI/crpi_universal.h \
../Libraries/CRPI/crpi_xml.h \
../Libraries/CRPI/portable.h \
../Libraries/CRPI/types.h \
../Libraries/CRPI/vector.h

libMath_a_SOURCES = \
../Libraries/Math/Filters.cpp \
../Libraries/Math/MatrixMath.cpp \
../Libraries/Math/NumericalMath.cpp \
../Libraries/Math/VectorMath.cpp \
../Libraries/Math/Filters.h \
../Libraries/Math/MatrixMath.h \
../Libraries/Math/NumericalMath.h \
../Libraries/Math/VectorMath.h

libSerial_a_SOURCES = \
../Libraries/Serial/serial.cpp \
../Libraries/Serial/gccserial.h \
../Libraries/Serial/serial.h

libMotionPrims_a_SOURCES = \
../Libraries/MotionPrims/AssemblyPrims.cpp \
../Libraries/MotionPrims/MatHandlingPrims.cpp \
../Libraries/MotionPrims/AssemblyPrims.h \
../Libraries/MotionPrims/MatHandlingPrims.h

libRegistrationKit_a_SOURCES = \
../Libraries/RegistrationKit/CoordFrameReg.cpp \
../Libraries/RegistrationKit/CoordFrameReg.h \
../Libraries/Clustering/Cluster/Cluster.h \
../Libraries/Clustering/kMeans/kMeansCluster.h \
../Libraries/Clustering/Patterns/Pattern.h

libHRI_a_SOURCES = \
../Libraries/Sensor/HRI/LeapMotion.cpp \
../Libraries/Sensor/HRI/ManusVR.cpp \
../Libraries/Sensor/HRI/MYO.cpp \
../Libraries/Sensor/HRI/LeapMotion.h \
../Libraries/Sensor/HRI/ManusVR.h \
../Libraries/Sensor/HRI/MYO.h

libMoCap_a_SOURCES = \
../Libraries/Sensor/MoCap/OptiTrack.cpp \
../Libraries/Sensor/MoCap/Vicon.cpp \
../Libraries/Sensor/MoCap/MoCapTypes.h \
../Libraries/Sensor/MoCap/OptiTrack.h \
../Libraries/Sensor/MoCap/Vicon.h

libulapi_a_SOURCES = \
../Libraries/ulapi/src/inifile.c \
../Libraries/ulapi/src/unix_ulapi.c \
../Libraries/ulapi/src/inifile.h \
../Libraries/ulapi/src/ulapi.h

libCRCL_a_SOURCES = \
../Libraries/CRPI/CRCLCommandInstance.cxx \
../Libraries/CRPI/CRCLCommands.cxx \
../Libraries/CRPI/CRCLProgramInstance.cxx \
../Libraries/CRPI/CRCLStatus.cxx \
../Libraries/CRPI/DataPrimitives.cxx \
../Libraries/CRPI/URDF_CRPI.cxx \
../Libraries/CRPI/CRCLCommon.cpp \
../Libraries/CRPI/CRCLCommandInstance.hxx \
../Libraries/CRPI/CRCLCommands.hxx \
../Libraries/CRPI/CRCLProgramInstance.hxx \
../Libraries/CRPI/CRCLStatus.hxx \
../Libraries/CRPI/DataPrimitives.hxx \
../Libraries/CRPI/URDF_CRPI.hxx \
../Libraries/CRPI/CRCLCommon.h


