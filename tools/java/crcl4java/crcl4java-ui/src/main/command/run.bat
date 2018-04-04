

@REM set OLDDIR=%CD%
SET mypath=%~dp0

SET crcl_ui_jar=${project.build.directory}\${project.build.finalName}-jar-with-dependencies.jar

java -Dsimserver.logImages=true -jar %crcl_ui_jar% %*

@REM %mypath%\..\run.bat
