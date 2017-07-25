#!/bin/bash

set -x;

# Change to the directory this script is stored in. 
DIR=$(dirname $0 )
if test "x${DIR}" != "x" ; then
    if test -d "${DIR}" ; then
        cd "$DIR";
    fi
fi

echo "Testing Jython Example ..."

sh ./clean.sh || (echo "Clean Failed" ; exit 1) || exit 1;
if test !  -f setup.ok  ; then
    sudo -n sh ./setup.sh || (echo "Setup Failed. Please run sudo setup.sh." ; exit 1) || exit 1;
fi

sh ./runJavaCmdLineServer.sh &
sleep 1
sh ./run.sh || (sh ./killJavaServer.sh;  echo "Run Client Failed"; exit 1) || exit 1;
sh ./killJavaServer.sh

