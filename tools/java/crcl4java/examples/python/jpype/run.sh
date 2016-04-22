#!/bin/sh

set -x;

if test "x${JAVA_HOME}" = "x" ; then
    echo "Please install Java and set JAVA_HOME environment variable to the installation dirctory"
    exit 1;
fi

export JVM_LIB=`find $JAVA_HOME -name libjvm.so`;

python crclclient.py

