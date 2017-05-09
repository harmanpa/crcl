#!/bin/sh

set -x;

mdtool build -t:Clean -c:Release CRCL_CSharp.sln || exit 1
mdtool build -t:Clean -c:Debug CRCL_CSharp.sln || exit 1
mdtool build -t:Clean -p:ConsoleClient -c:Release CRCL_CSharp.sln || exit 1
mdtool build -t:Clean  -p:SimRobotServer -c:Release CRCL_CSharp.sln || exit 1
mdtool build -t:Clean -p:ConsoleClient -c:Debug CRCL_CSharp.sln || exit 1
mdtool build -t:Clean  -p:SimRobotServer -c:Debug CRCL_CSharp.sln 

