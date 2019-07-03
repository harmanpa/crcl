SUBDIRS = lib bin

include_HEADERS = \
Libraries/CRPI/crpi.h \
Libraries/Math/VectorMath.h

EXTRA_DIST = Makefile.am acinclude.m4 configure.ac autoconf.sh config/config.guess config/config.sub

EXTRA_DIST += \
Libraries/ThirdParty/CL_NUI/include \
Libraries/ThirdParty/LeapSDK/include \
Libraries/ThirdParty/Manus/ManusIK.h \
Libraries/ThirdParty/Manus/Manus.h \
Libraries/ThirdParty/MyoSDK/include \
Libraries/ThirdParty/NI/NIDAQ/include \
Libraries/ThirdParty/OpenCV/include \
Libraries/ThirdParty/OpenCV2/include \
Libraries/ThirdParty/OpenNI/include \
Libraries/ThirdParty/OpenVR/headers \
Libraries/ThirdParty/OptiTrack/include \
Libraries/ThirdParty/Vicon/include \
Libraries/ThirdParty/glew/glew-1.11.0/include \
Libraries/ThirdParty/glut/include \
Libraries/ThirdParty/sdl2-2.0.3/include

# Build the Doxygen doc with 'make doxygen-doc', assuming doxygen is
# installed and found during the ./configure step

include doxygen.am
