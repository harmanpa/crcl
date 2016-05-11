#!/bin/sh

set -x;

# Change to the directory this script is stored in. 
DIR=$(dirname $0 )
if test "x${DIR}" != "x" ; then
    if test -d "${DIR}" ; then
        cd "$DIR";
    fi
fi


CRCL4JAVA_UTILS_JAR=`ls -1t crcl4java-utils*.jar | head -n 1`

java -cp "${CRCL4JAVA_UTILS_JAR}":.  CRCLServer
