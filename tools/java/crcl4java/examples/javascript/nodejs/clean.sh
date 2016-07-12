#!/bin/sh

set -x;

# Change to the directory this script is stored in. 
DIR=$(dirname $0 )
if test "x${DIR}" != "x" ; then
    if test -d "${DIR}" ; then
        cd "$DIR";
    fi
fi

rm -rf node_modules build
\rm -f crcl4java*.jar*
\rm -f crcl4java*.jar
