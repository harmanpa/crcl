@REM FIXME: Lots of hard-coded paths maven, netbeans, java, firefox ...



set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_60\
set PATH=%JAVA_HOME%\bin;%PATH%
set PATH=%PATH%;C:\Program Files\NetBeans 8.0.2\java\maven\bin;
echo JAVA_HOME %JAVA_HOME%
@REM mvn -version


set OLDDIR=%CD%
echo "OLDDIR=" %OLDDIR%
SET mypath=%~dp0
echo mypath %mypath%
start java -Dsimserver.logimages=true -jar %mypath%\crcl4java-ui\target\crcl4java-ui-1.0-SNAPSHOT-jar-with-dependencies.jar --mode GraphicalServer  %*

cd %mypath%\crcl4java-vaadin-webapp
start mvn jetty:run

ping 192.0.2.2 -n 1 -w 10000 > nul
cd %OLDDIR%


"C:\Program Files (x86)\Mozilla Firefox\firefox.exe" http://localhost:8080/crcl4java-vaadin-webapp/





