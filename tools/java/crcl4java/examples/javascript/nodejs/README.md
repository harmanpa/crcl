
Node.js is JavaScript runtime built on Chrome's V8 JavaScript engine.

See :

    https://nodejs.org/en/

On Debian and Ubuntu the name of the Node.js executable is nodejs since the 
command used on other platforms "node" was already used by an unrelated
 application.


To install nodejs and the required modules on Ubuntu run:

    ./setup.sh

sudo privileges are required to run setup.sh.

On other platforms you will need to manually download the jar files below and 
install nodejs, and use "npm java" to install the java bindings as described
in https://www.npmjs.com/package/java :

    https://oss.sonatype.org/content/repositories/snapshots/com/github/wshackle/crcl4java-utils/1.4-SNAPSHOT/crcl4java-utils-1.4-20160422.130648-1-jar-with-dependencies.jar
    http://repo.maven.apache.org/maven2/com/github/wshackle/crcl4java-ui/1.3/crcl4java-ui-1.3-jar-with-dependencies.jar
    http://repo.maven.apache.org/maven2/com/github/wshackle/crcl4java-utils/1.3/crcl4java-utils-1.3-jar-with-dependencies.jar


To run the server written in javascript:

    ./runServer.sh

The server will bind to the default port and wait for clients to send commands
and it will send back status messages to clients that request it with
the GetStatusType command.


Before running the client, a CRCL server should be started. One alternative
is the server started with runJavaGuiServer.sh script.

To run the client on Linux:

    ./runClient.sh

The client connects to a CRCL server on localhost on the default port, sends a 
couple of commands, requests status and prints the status.

