#!/bin/sh

if ! java -version >/dev/null 2>/dev/null ; then
    echo Please install Java version 1.8 or higher and/or update PATH environment variable.
    exit 1
fi

java_version=`(java -version 2>&1) | grep 'java version ' | sed 's#java version ##' | sed 's#["]##g' | sed 's#^1[.]##' | sed 's#[.].*$##'`;

if test ${java_version} -lt 8 ; then
    echo Please install Java version 1.8 or higher and/or update PATH environment variable.
    exit 1
fi

export crcl_ui_jar=C:\\Users\\shackle\\Documents\\NetBeansProjects\\crcl\\tools\\java\\crcl4java\\crcl4java-ui\\target/crcl4java-ui-1.4-SNAPSHOT-jar-with-dependencies.jar

\rm -f run[0-9]*.log run[0-9]*.err >/dev/null 2>/dev/null || true

java -jar "${crcl_ui_jar}" $* > run$$.log 2> run$$.err
