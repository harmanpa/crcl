#!/bin/sh

jarfile="crcl4java-utils-1.3-jar-with-dependencies.jar";
if ! test -d node_modules/java -a -f "${jarfile}" ; then
    ./setup.sh
fi

nodejs crclclient.js
