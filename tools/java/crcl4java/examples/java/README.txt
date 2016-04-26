
Atleast JDK 7 is required to compile or run these examples.

Use the script build.sh on Linux or build.bat for Windows to compile.

The server should be started and be running before the client is started.

Use the script runJavaServer.sh on Linux onr runJavaServer.bat on windows to run
the server.

Use the script runJavaClient.sh on Linux onr runJavaClient.bat on windows to run
the client.

The server will bind to the default port and wait for CRCL commands to be sent,
and return CRCL status messages when requested.

The client will connect to the default port on localhost, send a couple of 
commands and then request status until the last command is done.


