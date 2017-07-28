@REM FIXME: Lots of hard-coded paths maven, netbeans, java, firefox ...



set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_66
set PATH=%JAVA_HOME%\bin;%PATH%
@REM set PATH=%PATH%;C:\Program Files\NetBeans 8.1\bin;
echo JAVA_HOME %JAVA_HOME%
@REM mvn -version


set OLDDIR=%CD%
echo "OLDDIR=" %OLDDIR%
SET mypath=%~dp0
echo mypath %mypath%
@REM start java -Dsimserver.logimages=true -jar %mypath%\crcl4java-ui\target\crcl4java-ui-1.0-SNAPSHOT-jar-with-dependencies.jar --mode GraphicalServer  %*

@REM cd %mypath%\crcl4java-vaadin-webapp
@REM start mvn jetty:run

@REM ping 192.0.2.2 -n 1 -w 10000 > nul
@REM cd %OLDDIR%
start "" http://localhost:8080/crcl4java-vaadin-webapp/
jps

java -jar crcl4java-vaadin-webapp\target\crcl4java-vaadin-webapp-1.4-SNAPSHOT-war-exec.jar
@REM start "" http://localhost:8080/crcl4java-vaadin-webapp/







