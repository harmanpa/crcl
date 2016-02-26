#!/bin/sh

set -x;

mdtool build -c:Release CRCL_CSharp.sln || exit 1
mdtool build -c:Debug CRCL_CSharp.sln || exit 1
mdtool build -p:ConsoleClient -c:Release CRCL_CSharp.sln || exit 1
mdtool build  -p:SimRobotServer -c:Release CRCL_CSharp.sln || exit 1
mdtool build -p:ConsoleClient -c:Debug CRCL_CSharp.sln || exit 1
mdtool build  -p:SimRobotServer -c:Debug CRCL_CSharp.sln 

