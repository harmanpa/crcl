#!/bin/sh

# This script was tested on Ubuntu 14.04. 
# To run the script you need jjs which is included in Java 8 and the crcl4java-utils jar file.
# The script will try to download them if not already downloaded and then run the 
# clclclient.js  with jjs with the jar file on the classpath.

set -x;

jarfile="crcl4java-utils-1.3-jar-with-dependencies.jar"
if ! test -f "${jarfile}" ; then
    remotejarurl="http://repo.maven.apache.org/maven2/com/github/wshackle/crcl4java-utils/1.3/crcl4java-utils-1.3-jar-with-dependencies.jar";
    echo "Downloading ${remotejarurl}";
    wget "${remotejarurl}"
fi

if ! which rhino >/dev/null 2>/dev/null ; then
    sudo apt-get install rhino;
fi

rhino crclclient.js
