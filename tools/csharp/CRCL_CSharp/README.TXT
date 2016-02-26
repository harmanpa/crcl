
This directory is a top level directory for a C# solution with three projects.

	* a provides a utility library (CRCL_CSharp) for reading and writing CRCL messages/files.
	* an example server (SimRobotServer) 
	* a simple client (ConsoleClient)


CRCL is the Canonical Robot Command Language. An XML based language defined with xsd files. 
See  https://github.com/ros-industrial/crcl


To build on linux/mac:

  * Download and Install MonoDevelop from http://www.monodevelop.com/download/.

Either open the .sln file in the monodevelop IDE, select Release or Debug, and then Build->Build All.

or on the command line use

mdtool build CRCL_CSharp.sln


To run on linux/mac:

First start the server:

cd SimRobotServer/bin/Debug/ ; #( or cd SimRobotServer/bin/Release) 
mono SimRobotServer.exe

Start the client:

cd ConsoleClient/bin/Debug/ ;  #( or cd ConsoleClient/bin/Release) 
mono ConsoleClient.exe


On Windows it can be built with Visual Studio. 
The same programs should be run without the "mono "  first.

Troubleshooting:


If you see this when running the server:

System.Net.Sockets.SocketException (0x80004005): An attempt was made to access a socket in a way forbidden by its access permissions
   at System.Net.Sockets.Socket.DoBind(EndPoint endPointSnapshot, SocketAddress socketAddress)
   at System.Net.Sockets.Socket.Bind(EndPoint localEP)
   at SimRobotServer.AsynchronousSocketListener.StartListening() in c:\Users\shackle\Documents\NetBeansProjects\GitHubProjects\crac\CRCL_CSharp\SimRobotServer\SimRobotServerMain.cs:line 64

The most likely cause is another copy of the server running. 
Kill it with TaskManager if necessary and try again or switch to a different port.

    
If you see something like this when compiling (probably followed by a very long list of related errors):

/usr/lib/mono/4.5/Microsoft.Common.targets:  warning : Unable to find
      framework corresponding to the target framework moniker
      '.NETFramework,Version=v4.0.3'. Framework assembly references will be
      resolved from the GAC, which might not be the intended behavior.


The value of <TargetFrameworkVersion>v4.0.3</TargetFrameworkVersion> needs to be changed 
in all of the .csproj files. ie 

    CRCL_CSharp/CRCL_CSharp.csproj, ConsoleClient/ConsoleClient.csproj, SimRobotServer/SimRobotServer.csproj

You may need to use a separate text editor to fix it. 
Or within monodevelop select each project and goto options->build->general 
and change TargetFramework.
Or within Visual Studio, select each project and goto properties-> Application 
and change TargetFramework.

