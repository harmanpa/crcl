
REM If you didn't put nodejs or Python27 in the default locations this 
REM may need to be edited.
set PATH=%PATH%;C:\Program Files\nodejs;C:\Python27;

set jarfile=crcl4java-utils-1.3-jar-with-dependencies.jar
set remotejarfile=http://repo.maven.apache.org/maven2/com/github/wshackle/crcl4java-utils/1.3/crcl4java-utils-1.3-jar-with-dependencies.jar
set snapshotjarfile=crcl4java-utils-1.4-20160422.130648-1-jar-with-dependencies.jar
set remotestapshotjarfile=https://oss.sonatype.org/content/repositories/snapshots/com/github/wshackle/crcl4java-utils/1.4-SNAPSHOT/crcl4java-utils-1.4-20160422.130648-1-jar-with-dependencies.jar

if NOT exist %jarfile% (
    echo Downloading  %jarfile%
    powershell -Command "(New-Object Net.WebClient).DownloadFile('%remotejarfile%', '%jarfile%')"
)

if NOT exist %snapshotjarfile% (
    echo Downloading snapshot  %snapshotjarfile% 
    powershell -Command "(New-Object Net.WebClient).DownloadFile('%remotestapshotjarfile%', '%snapshotjarfile%')"
);

if NOT exist node_modules\java (
    echo "Installing npm java ..."
    call npm install java
    echo npm exit Code = %ERRORLEVEL%
)