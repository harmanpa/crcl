#!/bin/bash

set -x;

# Change to the directory this script is stored in. 
DIR=$(dirname $0 )
if test "x${DIR}" != "x" ; then
    if test -d "${DIR}" ; then
        cd "$DIR";
    fi
fi

echo "Testing Javascript JJS Example ..."

sh ./clean.sh || (echo "Clean Failed" ; exit 1) || exit 1;
sh ./runJjsServer.sh &
sleep 1
sh ./runJjsClient.sh || (sh ./killJjsServer.sh;  echo "Run Client Failed"; exit 1) || exit 1;
sh ./killJjsServer.sh

