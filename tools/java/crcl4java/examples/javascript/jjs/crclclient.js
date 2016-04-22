

// Import all the Java types we will need.
var CRCLSocket = Java.type('crcl.utils.CRCLSocket');
var CRCLPosemath = Java.type('crcl.utils.CRCLPosemath');
var CRCLCommandInstanceType = Java.type('crcl.base.CRCLCommandInstanceType');
var InitCanonType = Java.type('crcl.base.InitCanonType');
var MoveToType = Java.type('crcl.base.MoveToType');
var GetStatusType = Java.type('crcl.base.GetStatusType')
var BigInteger = Java.type('java.math.BigInteger');

// Convenience functions instead of static imports for creating Points,Vectors an Poses
function pose(pt,xaxis,zaxis) {
    return CRCLPosemath.pose(pt,xaxis,zaxis);
}

function point(x,y,z) {
    return CRCLPosemath.point(x,y,z);
}

function vector(i,j,k) {
    return CRCLPosemath.vector(i,j,k);
}

var socket = new CRCLSocket("localhost", CRCLSocket.DEFAULT_PORT);
// Create an instance to wrap all commands.
print("s=" + socket);
var instance = new CRCLCommandInstanceType();
print("instance=" + instance);

//        CRCLCommandInstanceType instance = new CRCLCommandInstanceType();
//        // Create and send init command
var init = new InitCanonType();
init.setCommandID(BigInteger.valueOf(7));
instance.setCRCLCommand(init);
socket.writeCommand(instance);

// Create and send MoveTo command.
var moveTo = new MoveToType();
moveTo.setCommandID(BigInteger.valueOf(8));
var pose = pose(point(0.65,0.05,0.15),vector(1,0,0),vector(0,0,1));
moveTo.setEndPosition(pose);
moveTo.setMoveStraight(false);
instance.setCRCLCommand(moveTo);
socket.writeCommand(instance);
print("moveTo="+moveTo);

// Create and send getStatus request.
var getStat = new GetStatusType();
getStat.setCommandID(BigInteger.valueOf(9));
instance.setCRCLCommand(getStat);
socket.writeCommand(instance);

// Read status from server
var stat = socket.readStatus();
print("stat="+stat);

// Print out the status details.
var cmdStat = stat.getCommandStatus();
var IDback = cmdStat.getCommandID();
print("Status:");
print("CommandID = " + IDback);
print("State = " + cmdStat.getCommandState());
pt = stat.getPoseStatus().getPose().getPoint();
print("pt = " + pt.getX() + "," + pt.getY() + "," + pt.getZ());
var jst = stat.getJointStatuses();
if(jst != null) {
    var l = jst.getJointStatus();
    print("Joints:");
    for (index=0; index<l.size(); index++) {
        var js = l.get(index);
        print("Num=" + js.getJointNumber() + " Pos=" + js.getJointPosition());
    }
}

print("Closing socket");
socket.close();
//        // Create and send MoveTo command.
//        MoveToType moveTo = new MoveToType();
//        moveTo.setCommandID(BigInteger.valueOf(8));
//        PoseType pose = new PoseType();
//        PointType pt = new PointType();
//        pt.setX(BigDecimal.valueOf(0.65));
//        pt.setY(BigDecimal.valueOf(0.05));
//        pt.setZ(BigDecimal.valueOf(0.15));
//        pose.setPoint(pt);
//        VectorType xAxis = new VectorType();
//        xAxis.setI(BigDecimal.ONE);
//        xAxis.setJ(BigDecimal.ZERO);
//        xAxis.setK(BigDecimal.ZERO);
//        pose.setXAxis(xAxis);
//        VectorType zAxis = new VectorType();
//        zAxis.setI(BigDecimal.ZERO);
//        zAxis.setJ(BigDecimal.ZERO);
//        zAxis.setK(BigDecimal.ONE);
//        pose.setZAxis(zAxis);
//        moveTo.setEndPosition(pose);
//        moveTo.setMoveStraight(false);
//        instance.setCRCLCommand(moveTo);
//        s.writeCommand(instance, true);
//        // Create and send getStatus request.
//        GetStatusType getStat = new GetStatusType();
//        getStat.setCommandID(BigInteger.valueOf(9));
//        instance.setCRCLCommand(getStat);
//        s.writeCommand(instance);
//        // Read status from server
//        CRCLStatusType stat = s.readStatus();
//        // Print out the status details.
//        CommandStatusType cmdStat = stat.getCommandStatus();
//        BigInteger IDback = cmdStat.getCommandID();
//        System.out.println("Status:");
//        System.out.println("CommandID = " + IDback);
//        System.out.println("State = " + cmdStat.getCommandState());
//        pt = stat.getPose().getPoint();
//        System.out.println("pose = " + pt.getX() + "," + pt.getY() + "," + pt.getZ());
//        JointStatusesType jst = stat.getJointStatuses();
//        List < JointStatusType > l = jst.getJointStatus();
//        System.out.println("Joints:");
//        for (JointStatusType js : l) {
//System.out.println("Num=" + js.getJointNumber() + " Pos=" + js.getJointPosition());
//        }
