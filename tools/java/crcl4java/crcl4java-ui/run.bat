

@REM set OLDDIR=%CD%
SET mypath=%~dp0

SET crcl_ui_jar=/mnt/ubuntu/home/shackle/usnistgov/crcl/tools/java/crcl4java/crcl4java-ui/target\crcl4java-ui-1.4-jar-with-dependencies.jar

java -Dsimserver.logImages=true -jar %crcl_ui_jar% --startserver %*

@REM %mypath%\..\run.bat
