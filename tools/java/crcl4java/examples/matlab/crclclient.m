% Matlab script using crcl4java jar file.
% Connects sends a simple command and checks the status.
%
% Add crcl4java-utils-1.0-SNAPSHOT-jar-with-dependencies.jar to
% javaclasspath
% eg.

javaaddpath('/home/shackle/crcl4java/crcl4java-utils/target/crcl4java-utils-1.0-SNAPSHOT-jar-with-dependencies.jar')

import crcl.base.*
import crcl.utils.*
import java.math.BigInteger
import java.math.BigDecimal


s = CRCLSocket('localhost',64444);
instance = CRCLCommandInstanceType();

init = InitCanonType();
init.setCommandID(BigInteger.valueOf(7))
instance.setCRCLCommand(init)
s.writeCommand(instance)

moveTo = MoveToType();
moveTo.setCommandID(BigInteger.valueOf(8))
pose =  PoseType();
pt = PointType();
pt.setX(BigDecimal.valueOf(0.65))
pt.setY(BigDecimal.valueOf(-0.2))
pt.setZ(BigDecimal.valueOf(0.1))
pose.setPoint(pt)
xAxis = VectorType();
xAxis.setI(BigDecimal.ONE)
xAxis.setJ(BigDecimal.ZERO)
xAxis.setK(BigDecimal.ZERO)
pose.setXAxis(xAxis)
zAxis = VectorType();
zAxis.setI(BigDecimal.ZERO)
zAxis.setJ(BigDecimal.ZERO)
zAxis.setK(BigDecimal.ONE)
pose.setZAxis(zAxis)
moveTo.setEndPosition(pose)
moveTo.setMoveStraight(false)
instance.setCRCLCommand(moveTo)
s.writeCommand(instance,false)

getStat = GetStatusType();
getStat.setCommandID(BigInteger.valueOf(9))
instance.setCRCLCommand(getStat)
s.writeCommand(instance,false)
 

stat = s.readStatus(false);
            
% Print out the status details.
cmdStat = stat.getCommandStatus();
IDback = cmdStat.getCommandID();
fprintf('CommandID=%s\n',char(IDback.toString()));

pt = stat.getPose().getPoint();
x =pt.getX().doubleValue();
y =pt.getY().doubleValue();
z =pt.getZ().doubleValue();
fprintf('X=%f\n',x);
fprintf('Y=%f\n',y);
fprintf('Z=%f\n',z);

jst = stat.getJointStatuses();
l = jst.getJointStatus();
lit = l.iterator();            
while lit.hasNext() 
    js = lit.next();
    jsn = js.getJointNumber().intValue();
    jsp = js.getJointPosition().doubleValue();
    fprintf('JointNumber=%d\n',jsn);
    fprintf('JointPosition=%f\n',jsp);
end

s.close();