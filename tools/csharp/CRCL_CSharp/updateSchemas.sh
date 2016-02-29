#!/bin/bash


set -x;

sudo apt-get install -y monodevelop
mkdir tmp
cd tmp
cp ../../../../schemas/*.xsd .

pwd
ls

xsd  CRCLCommandInstance.xsd CRCLCommands.xsd  /c /n:Schemas.CRCL.CommandInstance
xsd  CRCLProgramInstance.xsd CRCLCommands.xsd   /c /n:Schemas.CRCL.Program
xsd  CRCLStatus.xsd /c /n:Schemas.CRCL.Status
cp *.cs ../CRCL_CSharp/

pwd
ls

cd ..

\rm -rf tmp

sh build.sh



