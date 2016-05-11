#!/bin/sh

set -x;

jps

PID=`jps | grep CRCLServer | awk '{print $1}'`

kill -KILL ${PID}
