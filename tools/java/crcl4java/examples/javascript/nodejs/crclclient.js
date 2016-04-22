var java = require("java");
java.classpath.push("crcl4java-utils-1.3-jar-with-dependencies.jar");


// By default for node-java, you have to put "Sync" after everything for the normal function 
// or pass a callback and use the ASync version. 
// These options reverse this so you can use the Sync version by default and add "ASync" 
// if you want to pass the callback.
java.asyncOptions = {
  asyncSuffix: "ASync",
  syncSuffix: ""
};


// Convenient replacements for the java static imports.
function BigInteger(i) {
    return java.callStaticMethodSync("java.math.BigInteger","valueOf",i);
}

function pose(pt,xAxis,zAxis) {
    return java.callStaticMethodSync("crcl.utils.CRCLPosemath","pose",pt,xAxis,zAxis);
}

function point(x,y,z) {
    return java.callStaticMethodSync("crcl.utils.CRCLPosemath","point",x,y,z);
}

function vector(i,j,k) {
    return java.callStaticMethodSync("crcl.utils.CRCLPosemath","vector",i,j,k);
}

// Create a socket connected to the default port on localhost
var socket = java.newInstanceSync("crcl.utils.CRCLSocket","localhost", 
        java.getStaticFieldValue("crcl.utils.CRCLSocket", "DEFAULT_PORT"));
// Create an instance to wrap all commands.
console.log("s=" + socket);
var instance = java.newInstanceSync("crcl.base.CRCLCommandInstanceType");
console.log("instance=" + instance);

// Create and send init command
var init = java.newInstanceSync("crcl.base.InitCanonType");
init.setCommandID(BigInteger(7));
instance.setCRCLCommand(init);
socket.writeCommand(instance);

// Create and send MoveTo command.
var moveTo = java.newInstanceSync("crcl.base.MoveToType");
moveTo.setCommandID(BigInteger(8));
var pose = pose(point(0.65,0.05,0.15),vector(1,0,0),vector(0,0,1));
moveTo.setEndPosition(pose);
moveTo.setMoveStraight(false);
instance.setCRCLCommand(moveTo);
socket.writeCommand(instance);
console.log("moveTo="+moveTo);

// Create and send getStatus request.
var getStat = java.newInstanceSync("crcl.base.GetStatusType");
getStat.setCommandID(BigInteger(9));
instance.setCRCLCommand(getStat);
socket.writeCommand(instance);

// Read status from server
var stat = socket.readStatus();
console.log("stat="+stat);

// Print out the status details.
var cmdStat = stat.getCommandStatus();
var IDback = cmdStat.getCommandID();
console.log("Status:");
console.log("CommandID = " + IDback);
console.log("State = " + cmdStat.getCommandState());
pt = stat.getPoseStatus().getPose().getPoint();
console.log("pt = " + pt.getX() + "," + pt.getY() + "," + pt.getZ());
var jst = stat.getJointStatuses();
if(jst != null) {
    var l = jst.getJointStatus();
    console.log("Joints:");
    for (index=0; index<l.size(); index++) {
        var js = l.get(index);
        console.log("Num=" + js.getJointNumber() + " Pos=" + js.getJointPosition());
    }
}

console.log("Closing socket");
socket.close();
