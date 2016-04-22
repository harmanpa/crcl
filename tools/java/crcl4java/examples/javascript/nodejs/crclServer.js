
var java = require("java");
java.classpath.push("crcl4java-utils-1.4-20160422.130648-1-jar-with-dependencies.jar");


// By default for node-java, you have to put "Sync" after everything for the normal function 
// or pass a callback and use the ASync version. 
// These options reverse this so you can use the Sync version by default and add "ASync" 
// if you want to pass the callback.
java.asyncOptions = {
  asyncSuffix: "ASync",
  syncSuffix: ""
};



// ********************************
// ** Server variables
// ********************************
var net     = require('net');
var server  = net.createServer();
var clients = [];
var SERVER_PORT_NUM = java.getStaticFieldValue("crcl.utils.CRCLSocket", "DEFAULT_PORT");

// ********************************
// ** Server Routines
// ********************************

var CRCLStatusType = java.import("crcl.base.CRCLStatusType");
var PoseStatusType = java.import("crcl.base.PoseStatusType");
var PoseType = java.import("crcl.base.PoseType");
var CommandStatusType = java.import("crcl.base.CommandStatusType");
var BigInteger = java.import("java.math.BigInteger");
var status = new CRCLStatusType();
var cmdStatus = new CommandStatusType();
var poseStatus = new PoseStatusType();
cmdStatus.setCommandID(BigInteger.valueOf(1));
status.setCommandStatus(cmdStatus);

server.on('connection', function (socket) {
       console.log ('New client connected.');

       var crclsocket = java.newInstanceSync("crcl.utils.CRCLSocket");

       clients.push(socket);

       // define which things were interested in
       socket.on('data', function(data) {
            //console.log(data.toString());
            l = crclsocket.parseMultiCommandString(data.toString());
            var i = 0;
            for(i = 0; i < l.size(); i++) {
                instance = l.get(i);
                cmd = instance.getCRCLCommand();
                console.log("command class name="+cmd.getClass().getName());
                if(cmd.getClass().getName() == "crcl.base.GetStatusType") {
                    socket.write(crclsocket.statusToString(status,false))
                } else {
                    cmdStatus.setCommandID(cmd.getCommandID());
                    switch(cmd.getClass().getName()) {
                        case "crcl.base.MoveToType":
                            poseStatus.setPose(cmd.getEndPosition());
                            status.setPoseStatus(poseStatus);
                            break;
                            
                       default:
                           break;
                    }
                }
            }
        });

       socket.on('error', function(error) {
         console.log ('Server error -- ', error.message);
       });

       socket.on('close', function() {
	 console.log ('connection closed.');
	 var client_index = clients.indexOf(socket);
	 clients.splice(client_index, 1);
       });
});

server.listen(SERVER_PORT_NUM);
      


