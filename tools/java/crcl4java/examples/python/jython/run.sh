#!/bin/sh

set -x;

jarfile="crcl4java-utils-1.3-jar-with-dependencies.jar"
if ! test -f "${jarfile}" ; then
    remotejarurl="http://repo.maven.apache.org/maven2/com/github/wshackle/crcl4java-utils/1.3/crcl4java-utils-1.3-jar-with-dependencies.jar";
    echo "Downloading ${remotejarurl}";
    wget "${remotejarurl}"
fi

export CLASSPATH="${jarfile}"
jython crclclient.py

