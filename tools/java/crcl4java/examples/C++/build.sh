#!/bin/sh
set -x;

## This will need to be edited on most systems that do NOT already have JAVA_HOME set.
JAVA_HOME_DEFAULT="/usr/local/jdk1.8.0_65/";

if test "x${JAVA_HOME}" = "x" -a -d "${JAVA_HOME_DEFAULT}"; then
    export JAVA_HOME="${JAVA_HOME_DEFAULT}";
fi

if test "x${JAVA_HOME}" = "x" ; then
    echo "Please install JDK 1.8 or higher and set JAVA_HOME to this directory";
    exit 1;
fi

if ! "${JAVA_HOME}/bin/java" -version 2>&1 | grep java | grep version | grep 1.8 >/dev/null 2>/dev/null ; then
    echo "Please install JDK 1.8 or higher and set JAVA_HOME to this directory";
    exit 1;
fi

./clean.sh



wget https://oss.sonatype.org/content/repositories/snapshots/com/github/wshackle/java4cpp/1.0-SNAPSHOT/java4cpp-1.0-20151030.144815-8-jar-with-dependencies.jar
JAVA4CPP_JAR=`ls -1t java4cpp*.jar | head -n 1`;
"${JAVA_HOME}/bin/java" -jar "${JAVA4CPP_JAR}" -p crcl -n crclj -j ../../crcl4java-utils/target/crcl4java-utils-1.0-SNAPSHOT-jar-with-dependencies.jar || exit 1;

if test "x${JVM_LIB_DIR}" = "x" ; then
    export JVM_LIB_DIR=`find "${JAVA_HOME}" -name libjvm.so | head -n 1 | sed 's#libjvm.so##g'`;
fi

ln -s "${JVM_LIB_DIR}" jvm_lib_dir
ln -s "${JAVA_HOME}" java_home

g++ -O0 -g -I "${JAVA_HOME}/include"  -I "${JAVA_HOME}/include/linux" crclj*.cpp use_crcl.cpp  -L "${JVM_LIB_DIR}" -Wl,--rpath "${JVM_LIB_DIR}" -ljvm -o crclj_test