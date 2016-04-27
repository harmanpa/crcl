
Atleast JDK 7 is required to compile or run these examples.

Use the script build.sh on Linux or build.bat for Windows to compile.

The server should be started and be running before the client is started.

Use the script runJavaServer.sh on Linux onr runJavaServer.bat on windows to run
the server.

Use the script runJavaClient.sh on Linux or runJavaClient.bat on windows to run
the client.

The server will bind to the default port and wait for CRCL commands to be sent,
and return CRCL status messages when requested.

The client will connect to the default port on localhost, send a couple of 
commands and then request status until the last command is done.

For a reference for all classes and functions available download and unzip:


    http://repo.maven.apache.org/maven2/com/github/wshackle/crcl4java-utils/1.3/crcl4java-utils-1.3-javadoc.jar
    http://repo.maven.apache.org/maven2/com/github/wshackle/crcl4java-base/1.3/crcl4java-base-1.3-javadoc.jar

Or use the scripts showDoc.sh / showDoc.bat to download and open the docs.




