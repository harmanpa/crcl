#!/usr/bin/python

# CRCL Client Example using Java CRCL Library and JPype (http://jpype.sourceforge.net/)
# Run with:
# python crclclient.py

from jpype import *
import urllib
import os

jarfilename="crcl4java-utils-1.3-jar-with-dependencies.jar"
if not os.path.isfile(jarfilename):
    remotejarurl = "http://repo.maven.apache.org/maven2/com/github/wshackle/crcl4java-utils/1.3/crcl4java-utils-1.3-jar-with-dependencies.jar"
    print ("Downloading "+remotejarurl)
    urllib.urlretrieve (remotejarurl,jarfilename )


javahome=os.getenv('JAVA_HOME', '/usr/lib/jvm/java-7-openjdk-amd64/')
jvmlib=os.getenv('JVM_LIB',javahome+"/jre/lib/amd64/server/libjvm.so")
startJVM(jvmlib,
    "-Djava.class.path=crcl4java-utils-1.3-jar-with-dependencies.jar")
crcl = JPackage('crcl')
CRCLSocket = JClass('crcl.utils.CRCLSocket')
CRCLPosemath = JClass('crcl.utils.CRCLPosemath')
BigInteger = JClass('java.math.BigInteger')
BigDecimal = JClass('java.math.BigDecimal')

print "Connect"
s = CRCLSocket(JString("localhost"), JInt(64444))
#s.setEXIEnabled(True);

instance = crcl.base.CRCLCommandInstanceType()

## Create and send InitCanon command
print "Send InitCanon"
init = crcl.base.InitCanonType()
init.setCommandID(BigInteger.valueOf(7))
instance.setCRCLCommand(init)
s.writeCommand(instance)
    
## Create and send MoveTo command.
print "Send MoveTo"
moveTo = crcl.base.MoveToType()
moveTo.setCommandID(BigInteger.valueOf(8))
pt = CRCLPosemath.point(0.6,0.1,0.1)
xaxis = CRCLPosemath.vector(1.0,0.0,0.0)
zaxis = CRCLPosemath.vector(0.0,0.0,1.0)
pose = CRCLPosemath.pose(pt,xaxis,zaxis)
moveTo.setEndPosition(pose)
moveTo.setMoveStraight(False)
instance.setCRCLCommand(moveTo)
s.writeCommand(instance)


### Create and send getStatus request.
print "Send GetStatus"
getStat = crcl.base.GetStatusType()
getStat.setCommandID(BigInteger.valueOf(9))
instance.setCRCLCommand(getStat)
s.writeCommand(instance)

### Read status from server
stat = s.readStatus()


### Print out the status details.
cmdStat = stat.getCommandStatus()
IDback = cmdStat.getCommandID()
print "Status:"
print("CommandID = " + IDback.toString())
print("State = " + cmdStat.getCommandState().toString())
pt = stat.getPoseStatus().getPose().getPoint()
print("pose = " + pt.getX().toString() + "," + pt.getY().toString() + "," + pt.getZ().toString())
jst = stat.getJointStatuses()
l = jst.getJointStatus()
print "Joints:" 
for i in range(0,l.size()):
    js = l.get(i)
    print("Num="+js.getJointNumber().toString()+" Pos="+js.getJointPosition().toString())

s.close();
shutdownJVM() 