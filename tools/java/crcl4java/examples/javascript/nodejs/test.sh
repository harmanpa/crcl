#!/bin/bash

set -x;

# Change to the directory this script is stored in. 
DIR=$(dirname $0 )
if test "x${DIR}" != "x" ; then
    if test -d "${DIR}" ; then
        cd "$DIR";
    fi
fi

echo "Testing NodeJs Example ..."

sh ./killNodejsServer.sh

sh ./clean.sh || (echo "Clean Failed" ; exit 1) || exit 1;
sh ./setup.sh || (echo "Setup Failed" ; exit 1) || exit 1;
sh ./runNodejsServer.sh &
sleep 1
sh ./runNodejsClient.sh || (sh ./killNodejsServer.sh;  echo "Run Client Failed"; exit 1) || exit 1;
sh ./killNodejsServer.sh

