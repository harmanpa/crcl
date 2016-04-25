#!/bin/sh

set -x;

CRCL4JAVA_UTILS_JAR=`ls -1t crcl4java-utils*.jar | head -n 1`

java -cp "${CRCL4JAVA_UTILS_JAR}":.  CRCLClient
