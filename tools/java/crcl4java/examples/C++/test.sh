#!/bin/bash

set -x;

# Change to the directory this script is stored in. 
DIR=$(dirname $0 )
if test "x${DIR}" != "x" ; then
    if test -d "${DIR}" ; then
        cd "$DIR";
    fi
fi

echo "Testing C++ Example ..."

sh ./clean.sh || (echo "Clean Failed" ; exit 1) || exit 1;
sh ./build.sh || (echo "Build Failed" ; exit 1) || exit 1;
sh ./runCppServer.sh &
sleep 1
sh ./runCppClient.sh || (sh ./killCppServer.sh;  echo "Run Client Failed"; exit 1) || exit 1;
sh ./killCppServer.sh

