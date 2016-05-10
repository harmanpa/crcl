
REM Set JDK Installation directory.
REM This will likely need to be edited on each system.
set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_92

REM Set location of jvm.lib file.
set JVM_LIBDIR=%JAVA_HOME%\lib

REM Set Location of jvm.dll file.
set JVM_DLLDIR=%JAVA_HOME%\jre\bin\server

set PATH=%JAVA_HOME%\bin:%JVM_DLLDIR%:%PATH%

set crcljarfile=crcl4java-utils-1.4-20160505.202933-4-jar-with-dependencies.jar
set remotecrcljarfile=https://oss.sonatype.org/content/repositories/snapshots/com/github/wshackle/crcl4java-utils/1.4-SNAPSHOT/crcl4java-utils-1.4-20160505.202933-4-jar-with-dependencies.jar

if NOT exist %crcljarfile% (
    echo Downloading  %crcljarfile%
    powershell -Command "(New-Object Net.WebClient).DownloadFile('%remotecrcljarfile%', '%crcljarfile%')"
)

set java4cppjarfile=java4cpp-1.4-20160510.151536-5-jar-with-dependencies.jar
set java4cppremotejarfile=https://oss.sonatype.org/content/repositories/snapshots/com/github/wshackle/java4cpp/1.4-SNAPSHOT/java4cpp-1.4-20160510.151536-5-jar-with-dependencies.jar
if NOT exist %java4cppjarfile% (
    echo Downloading  %java4cppjarfile%
    powershell -Command "(New-Object Net.WebClient).DownloadFile('%java4cppremotejarfile%', '%java4cppjarfile%')"
)

mkdir generated
cd generated
java -jar "../%java4cppjarfile%" -p crcl -n crclj -j "../%crcljarfile%"
cd ..
