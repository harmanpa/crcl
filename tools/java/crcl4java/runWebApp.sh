#!/bin/sh

#CRCL4JAVARUNWEB.sh
if test ! -f ./runWebApp.sh || grep -v '#CRCL4JAVARUNWEB.sh'  ./run.sh  ; then 
    cd "${0%%runWebApp.sh}";
fi

if test "x" != "x${JAVA_HOME}" ; then
    export PATH="${JAVA_HOME}/bin/:${PATH}";
fi

JARFILE=`find . -name crcl4java-vaadin-webapp\*war-exec.jar | head -n 1`;

if test "x${JARFILE}" = "x" ; then
    export JARFILE=crcl4java-vaadin-webapp/target/crcl4java-vaadin-webapp-1.5-SNAPSHOT-war-exec.jar
fi

if test ! -f "${JARFILE}" ; then
    mvn -version || ( echo "Please install maven." && false)
    mvn -Pskip_tests -Pwithweb install
    ( cd crcl4java-vaadin-webapp; mvn -Pskip_tests -Ptomcat_embed install ; cd ..)
else 
    echo "Found executable jar file already compiled. ${JARFILE}";
fi;

\rm -f run[0-9]*.log run[0-9]*.err >/dev/null 2>/dev/null || true

pids=`jps | grep crcl4java-vaadin-webapp | awk '{print $1}'`
if test "x${pids}x" != "xx" ; then 
    echo "Killing previously started copies of this application. ${pids}"
    kill -KILL ${pids};
fi

echo "Running java -jar ${JARFILE}"

java  -jar "${JARFILE}" > run$$.log 2> run$$.err &
jpid=$!

sleep 10
tail -f run$$.log &
taillogpid=$!
tail -f run$$.err &
tailerrpid=$!

firefox http://localhost:8080/crcl4java-vaadin-webapp/ &

sleep 1
echo 
echo "Press enter to kill the server"
read var

kill ${jpid} ${taillogpid} ${tailerrpid}

pids=`jps | grep crcl4java-vaadin-webapp | awk '{print $1}'`
if test "x${pids}x" != "xx" ; then 
    echo "Killing previously started copies of this application. ${pids}"
    kill -KILL ${pids};
fi
