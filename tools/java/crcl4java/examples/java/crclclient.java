/* 
 * Compile with :
 * javac -cp ../../crcl4java-utils/target/crcl4java-utils-1.0-SNAPSHOT-jar-with-dependencies.jar crclclient.java
 * 
 * Run with:
 * java -cp ../../crcl4java-utils/target/crcl4java-utils-1.0-SNAPSHOT-jar-with-dependencies.jar:.  crclclient
 * 
 */


import crcl.base.*;
import static crcl.utils.CRCLPosemath.point;
import static crcl.utils.CRCLPosemath.pose;
import static crcl.utils.CRCLPosemath.vector;
import java.math.BigInteger;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import crcl.utils.CRCLSocket;

/**
 *
 * @author Will Shackleford{@literal <william.shackleford@nist.gov> }
 */
public class crclclient {
    public static void main(String[] args) {
        try {
            // Connect to the server
            CRCLSocket s = new CRCLSocket("localhost",CRCLSocket.DEFAULT_PORT);
            
            // Create an instance to wrap all commands.
            CRCLCommandInstanceType instance = new CRCLCommandInstanceType();
            
            // Create and send init command.
            InitCanonType init = new InitCanonType();
            init.setCommandID(BigInteger.valueOf(7));
            instance.setCRCLCommand(init);
            s.writeCommand(instance);
            
            // Create and send MoveTo command.
            MoveToType moveTo = new MoveToType();
            moveTo.setCommandID(BigInteger.valueOf(8));
            PoseType pose = pose(point(0.65,0.05,0.15),vector(1,0,0),vector(0,0,1));
            moveTo.setEndPosition(pose);
            moveTo.setMoveStraight(false);
            instance.setCRCLCommand(moveTo);
            s.writeCommand(instance,true);
            
            // Create and send getStatus request.
            GetStatusType getStat = new GetStatusType();
            getStat.setCommandID(BigInteger.valueOf(9));
            instance.setCRCLCommand(getStat);
            s.writeCommand(instance);
            
            // Read status from server
            CRCLStatusType stat = s.readStatus();
            
            // Print out the status details.
            CommandStatusType cmdStat = stat.getCommandStatus();
            BigInteger IDback = cmdStat.getCommandID();
            System.out.println("Status:");
            System.out.println("CommandID = " + IDback);
            System.out.println("State = "+cmdStat.getCommandState());
            PointType pt = stat.getPoseStatus().getPose().getPoint();
            System.out.println("pose = "+pt.getX()+","+pt.getY()+","+pt.getZ());
            JointStatusesType jst = stat.getJointStatuses();
            List<JointStatusType> l = jst.getJointStatus();
            System.out.println("Joints:");
            for(JointStatusType js : l) {
                System.out.println("Num="+js.getJointNumber()+" Pos="+js.getJointPosition());
            }
        } catch (Exception ex) {
            Logger.getLogger(crclclient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
