#!/bin/sh

set -x;

wget "http://repo.maven.apache.org/maven2/com/github/wshackle/crcl4java-utils/1.2/crcl4java-utils-1.2-jar-with-dependencies.jar"
CRCL4JAVA_UTILS_JAR=`ls -1t crcl4java-utils*.jar | head -n 1`

javac -cp "${CRCL4JAVA_UTILS_JAR}" crclclient.java

