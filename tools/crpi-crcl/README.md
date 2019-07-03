# CRPI and CRCL Integration
This software provides integration between the functional application programming interface (API) of the Collaborative Robotics Programming Interface (CRPI) and the XML-based messaging interface of the Canonical Robot Control Language (CRCL). Integration is achieved through a mapping of the CRPI functions to message passing implementations. With this integration software, a CRPI application can connect as a CRCL client to a robot running a CRCL server. 
## Installation of supporting tools
This software runs on both Windows and Unix. It requires the installation of some supporting tools that are available for free for both platforms. These are the CodeSynthesis XSD Tree program to generate the parsing source code from the CRCL XML schemas, and the underlying Apache Xerces XML parsing library used by CoseSynthesis. 

### Linux installation

For Ubuntu Linux, there is a Xerces package, which can be installed as follows:
```
sudo apt-get install xsdcxx
```
For the Linux version of CoseSynthesis XSD, there is no Ubuntu package, so download the distribution from the CodeSynthesis web site, [www.codesynthesis.com/products/xsd/download.xhtml](https://www.codesynthesis.com/products/xsd/download.xhtml). Extract the downloaded archive you selected following the instructions on the CodeSynthesis web site, which would be something like this:
```
tar --bzip2 -xvf xsd-4.0.0-x86_64-linux-gnu.tar.bz2
```
Copy the installed `xsd` binary to a conventional locational, e.g., `/usr/local`:
```
cp xsd-4.0.0-x86_64-linux-gnu/bin /usr/local/bin
```

### Windows installation

For Windows, there is an installer that provides both CodeSynthesis XSD and Apache Xerces. Get this from the downloads page, [www.codesynthesis.com/products/xsd/download.xhtml](https://www.codesynthesis.com/products/xsd/download.xhtml).

The Windows version of the CRPI-CRCL integration code was tested using Microsoft Visual Studio 2015 Community Edition. This is a free C++ compiler avaliable at [https://visualstudio.microsoft.com/vs/community/](https://visualstudio.microsoft.com/vs/community/).

## Building the CRPI-CRCL integration code
The CRPI-CRCL integration code is available from this repository. Clone it locally as follows:
```
git clone git@gitlab.nist.gov/proctor/crpi-crcl
```

### Linux build

For Linux, build using the conventional Linux Automake method, as follows:
```
cd crpi-crcl
./configure
make
```

Resulting programs are located in the `bin` directory.

### Windows build

For Windows, load the Microsoft Visual Studio solution into Microsoft Visual Studio 2015 Community Edition, and build from the menu. The solution file is located in the `win32` folder, as `CRPI_CRCL.sln`.

Note that the projects only build for the 'x86' platform. The 'x64' platform is not supported. Select 'x86' from the Solution Platforms drop-down in Visual Studio.

# Robot description file

CRPI uses an XML-based robot description file that was integrated into a single XML schema with the ROS Unified Robot Description Format (URDF). See the sample [here](https://gitlab.nist.gov/gitlab/proctor/crpi-crcl/blob/master/etc/urdf_crpi.xml). The CRPI-CRCL tags are described below.

`Debug`, with boolean element `true` or `false`. Turns debug printing on or off, respectively. Optional. 
```
<Debug>true</Debug>
```

`TCP_IP`, with attributes `Address`, `Port`, and `Client`. Specifies the TCP/IP network parameters for the robot.
```
<TCP_IP Address="localhost" Port="64444" Client="false" />
```

`ComType`, with attribute `Val`. Specifies the type of communication to the robot. Can be `TCP_IP` or `Serial`.
```
<ComType Val="TCP_IP" />
```

`CRCL_Schemas`, with attributes `Command` and `Status`. These specify the schema paths to put into XML messages, for recipients who validate the messages. The defaults are `CRCLCommandInstance.xsd` and `CRCLStatus.xsd`, respectively. Optional. 
```
<CRCL_Schemas Command="../../schemas/CRCLCommandInstance.xsd" Status="schemas/CRCLStatus.xsd" />
```

`Mounting`, with attributes `x`, `Y`, `Z`, `XR`, `YR`, and `ZR` for the mounting of the robot.
```
<Mounting X="0" Y="0" Z="0" XR="135" YR="0" ZR="0"/>
```

`ToWorld`, with pose attributes `X Y Z XR YR ZR` as for `Mounting`, and added attributes for a 4x4 matrix `M00` through `M33`.
```
<ToWorld X="236.72" Y="678.641" Z="-722.799" XR="0.508279"
	   YR="-0.319192" ZR="81.3383" M00="0.150599" M01="-0.988563"
	   M02="0.00793089" M03="236.72" M10="0.988579" M11="0.150546"
	   M12="-0.00684316" M13="678.641" M20="0.00557093"
	   M21="0.00887088" M22="0.999945" M23="-722.799" M30="0"
	   M31="0" M32="0" M33="1"/> 
```
`Tool` for end effector tooling, with attributes `ID`, `Name`, mounting `X Y Z XR YR ZR`, the `Mass` and  three inertia elements `MX MY Mz`.
```
<Tool ID="1" Name="gripper_gear_fred" X="0" Y="0" Z="140" XR="0" YR="0" ZR="0" Mass="0.9" MX="0" MY="0" MZ="71"/> 
```


# Testing

There are several utilities for testing CRPI-CRCL integration. A simulated CRCL robot lets you test CRPI programs without requiring a real robot. A CRCL client shell can be used to test a real CRCL robot. Sample CRPI programs show how to connect to and control a CRCL robot. These are detailed below.

## CRCL Robot Simulator

The `CRCL_Robot_Simulator` application runs a simple CRCL server that responds to robot control messages and replies to status requests. Requests to move the robot will results in the simulated pose being changed after a few seconds, but no real physical simulation is done. The intent of this simulator is to validate that messages are being handled properly by the CRPI-to-CRCL client. 

Command line arguments: 
```
-p <port>    : TCP port to serve
-t <seconds> : period to update status
-a           : send status automatically
-r <robot file> : path to the robot configuration file
-s <schema>  : client's path to the status schema
-v           : validate messages to command schema
-d           : turn debug printing on
-?           : print this help
```
Command-line arguments override the settings in the robot configuration file. 

## CRCL Client Application

The `CRCL_Client_Shell` application provides a simple console application that sends command messages to a CRCL robot server and reads the status back. The intent of this application is to verify that the server is responding as expected. 

Command line arguments: 
```
-p <port> : TCP port of server
-h <host> : hostname of server
-r <file> : robot configuration file
-s <schema> : server's path to command schema
-h <host> : hostname of server
-?        : print this help
```
Command-line arguments override the settings in the robot configuration file. 

Interactive commands:
```
  printf("?                  : print this help\n");
  printf("quit, q            : quit the application\n");
  printf("(enter)            : print robot status\n");
  printf("don, doff <target> : turn debug ON or OFF for <target>, one of:\n");
  printf("                     status, command\n");
  printf("init               : send an init command\n");
  printf("end                : send an end command\n");
  printf("status             : force a request for robot status\n");
  printf("move <x> <y> <z> <r> <p> <w> :\n  move to Cartesian coordinates\n");
  printf("actuate <joint> <effort> <joint> <effort> :\n");
  printf("    send an actuate joint command for two joints\n");
  printf("tool 'off' | 'on <name>' | 'value <value>' :\n");
  printf("    take tool off, put tool <name> on, activate tool with <value>\n");
  printf("units 'length' <unit> | 'angle' <unit> :\n");
  printf("    set the length or angle units, one of:\n");
  printf("    meter | millimeter | inch , degree | radian\n");
  printf("report <flags, joints pose gripper settings> :\n");
  printf("    turn status reporting on or off for these\n");
  printf("jointreport <flags, position effort speed> :\n");
  printf("    turn joint reporting on or off for these\n");
```


# Handling of units

The CRCL robot model supports the setting and querying of units by client applications through messages to the CRCL robot server and status messages reported back. Once set with the `SetLengthUnitsType` command, these units apply to length and angle values in subsequent commands. Clients can see the current units in the `LengthUnitName` and `AngleUnitName` elements in the `SettingsStatus` element of a `CRCLStatus` message. Internally, the `CRCL_Robot_Simulator` maintains SI units (meters and radians), and converts lengths and angles to and from these internal units. 

If multiple client applications are running, there is the potential that one client will set units differenty from another client. If this happens, clients will detect a mismatch between their assumed units and those reported back from the server after the change by another client. 




