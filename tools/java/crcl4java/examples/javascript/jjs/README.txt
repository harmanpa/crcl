
Jjs is JavaScript statndalone interpreter that invokes the Nashorn JavaScript 
engine that comes with Java 8.

The scripts will download two jar files if needed. If downloading within the 
script fails the jars can be downloaded manually using the following links:

    http://repo.maven.apache.org/maven2/com/github/wshackle/crcl4java-ui/1.3/crcl4java-ui-1.3-jar-with-dependencies.jar
    http://repo.maven.apache.org/maven2/com/github/wshackle/crcl4java-utils/1.3/crcl4java-utils-1.3-jar-with-dependencies.jar


To run the server written in javascript in Linux:

    ./runServer.sh

To run the server written in javascript in Windows:

    runServer.bat

The server will bind to the default port and wait for clients to send commands
and it will send back status messages to clients that request it with
the GetStatusType command.


Before running the client, a CRCL server should be started. One alternative
is the server started with runJavaGuiServer.sh script.

To run the client on Linux:

    ./runClient.sh

To run the client written in javascript in Windows:

    runClient.bat


The client connects to a CRCL server on localhost on the default port, sends a 
couple of commands, requests status and prints the status.


For a reference for all classes and functions available download and unzip:


    http://repo.maven.apache.org/maven2/com/github/wshackle/crcl4java-utils/1.3/crcl4java-utils-1.3-javadoc.jar
    https://raw.github.com/usnistgov/crcl/mvn-repo/com/github/wshackle/crcl4java-base/1.4-SNAPSHOT/crcl4java-base-1.4-20160428.123047-1-javadoc.jar

Or use the scripts showDoc.sh / showDoc.bat to download and open the docs.
The scripts will open two tabs. One for utils packages and one for the base package.

