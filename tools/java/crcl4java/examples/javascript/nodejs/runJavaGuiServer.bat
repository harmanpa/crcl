set jarfile=crcl4java-ui-1.3-jar-with-dependencies.jar
set remotejarfile=http://repo.maven.apache.org/maven2/com/github/wshackle/crcl4java-ui/1.3/crcl4java-ui-1.3-jar-with-dependencies.jar

if NOT exist %jarfile% (
    echo Downloading  %jarfile%
    powershell -Command "(New-Object Net.WebClient).DownloadFile('%remotejarfile%', '%jarfile%')"
)

java -jar %jarfile% --mode GraphicalServer
