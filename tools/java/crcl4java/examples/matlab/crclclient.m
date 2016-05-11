% Matlab script using crcl4java jar file.
% A CRCL server should be started before running this script.
% Connects sends a simple command and checks the status.
%
% Add crcl4java-utils-1.3-jar-with-dependencies.jar to
% javaclasspath
% eg.


% If the CRCLSocket class is not already loaded download the jar (if necessary)
% and add the jar to the javaclasspath.
if exist('CRCLSocket','class') ~= 8 
    jarfilename='crcl4java-utils-1.3-jar-with-dependencies.jar';
    fulljarfilename=fullfile(pwd(),'crcl4java-utils-1.3-jar-with-dependencies.jar');
    
    % Check To see if jar file was already downloaded but not added.
    if exist(fulljarfilename, 'file') == 2
        fprintf('Adding %s to javapath\n',jarfilename);
        javaaddpath(jarfilename);
    else
        remotejarurl='https://raw.github.com/usnistgov/crcl/mvn-repo/com/github/wshackle/crcl4java-utils/1.3/crcl4java-utils-1.3-jar-with-dependencies.jar';
        fprintf('Downloading %s\n',remotejarurl);
        outfilename = websave(jarfilename,remotejarurl);
        fprintf('Adding %s to javapath\n',outfilename);
        javaaddpath(outfilename)
    end
    
    import crcl.base.*
    import crcl.utils.*
    import java.math.BigInteger
    import java.math.BigDecimal
end

% Connect to server running on localhost on default port (64444)
%  ( One could use the run.bat or run.sh scripts in the main crcl4java 
%  directory to start server. If you get 
%  "java.net.ConnectException: Connection refused" error, then most likely
%  the server is not running.) 
s = CRCLSocket('localhost',64444);

% Create an instance which is just a container for a single command.
instance = CRCLCommandInstanceType();

% Create an InitCanon command, put it in the instance, give it a unique ID
% and send it.
init = InitCanonType();
init.setCommandID(BigInteger.valueOf(7))
instance.setCRCLCommand(init)
s.writeCommand(instance)

% Create an MoveTo command, put it in the instance, give it a unique ID,
% create a pose for the end postion and send it.
moveTo = MoveToType();
moveTo.setCommandID(BigInteger.valueOf(8))
pose =  CRCLPosemath.pose(CRCLPosemath.point(0.65,-0.2,0.1),CRCLPosemath.vector(1,0,0),CRCLPosemath.vector(0,0,1));
moveTo.setEndPosition(pose)
moveTo.setMoveStraight(false)
instance.setCRCLCommand(moveTo)
s.writeCommand(instance,false)

% Create an GetStatus command, put it in the instance, give it a unique ID,
%  and send it.
getStat = GetStatusType();
getStat.setCommandID(BigInteger.valueOf(9))
instance.setCRCLCommand(getStat)
s.writeCommand(instance,false)
 
% Wait for the response from the GetStatus request as a CRCLStatus object.
stat = s.readStatus(false);
            
% Print out the status details.
cmdStat = stat.getCommandStatus();
IDback = cmdStat.getCommandID();
fprintf('CommandID=%s\n',char(IDback.toString()));

pt = stat.getPoseStatus().getPose().getPoint();
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