#!/bin/sh

set -x;



# Change to the directory this script is stored in. 
DIR=$(dirname $0 )
if test "x${DIR}" != "x" ; then
    if test -d "${DIR}" ; then
        cd "$DIR";
    fi
fi
which matlab

mfile=`pwd`/crclclient.m

echo "Starting up matlab . . . (This may take a minute.)"
time matlab -nodisplay -nosplash -nodesktop -r "try, run('${mfile}'), catch, exit(1), end, exit(0);"