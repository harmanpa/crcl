#!/bin/sh

#CRCL4JAVARUNWEB.sh
if test ! -f ./runWebApp.sh || grep -v '#CRCL4JAVARUNWEB.sh'  ./run.sh  ; then 
    cd "${0%%runWebApp.sh}";
fi

if test "x" != "x${JAVA_HOME}" ; then
    export PATH="${JAVA_HOME}/bin/:${PATH}";
fi

export JARFILE=crcl4java-ui/target/crcl4java-ui-1.0-SNAPSHOT-jar-with-dependencies.jar
if test ! -f "${JARFILE}" ; then
    mvn -version || ( echo "Please install maven." && false)
    mvn package
    mvn jetty:help -Ddetail=true
fi;

\rm -f run[0-9]*.log run[0-9]*.err >/dev/null 2>/dev/null || true

mvn -version

pids=`jps | grep crcl4java-ui | awk '{print $1}'`
if test "x${pids}x" != "xx" ; then 
    kill -KILL ${pids};
fi

java -Dsimserver.logimages=true -jar "${JARFILE}" --mode GraphicalServer > run$$.log 2> run$$.err &

( sleep 30 ; firefox http://localhost:8080/crcl4java-vaadin-webapp/ ) &
( set -x; cd crcl4java-vaadin-webapp ; mvn jetty:stop >/dev/null 2>/dev/null || true ; mvn jetty:run ; (mvn jetty:stop >/dev/null 2>/dev/null) || true );
