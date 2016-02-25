/* 
 * Compile with :
 * javac -cp ../../crcl4java-utils/target/crcl4java-utils-1.0-SNAPSHOT-jar-with-dependencies.jar crclclient.java
 * 
 * Run with:
 * java -cp ../../crcl4java-utils/target/crcl4java-utils-1.0-SNAPSHOT-jar-with-dependencies.jar:.  crclclient
 * 
 */


import com.siemens.ct.exi.exceptions.EXIException;
import crcl.base.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
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
            PoseType pose = new PoseType();
            PointType pt = new PointType();
            pt.setX(BigDecimal.valueOf(0.65));
            pt.setY(BigDecimal.valueOf(0.05));
            pt.setZ(BigDecimal.valueOf(0.15));
            pose.setPoint(pt);
            VectorType xAxis = new VectorType();
            xAxis.setI(BigDecimal.ONE);
            xAxis.setJ(BigDecimal.ZERO);
            xAxis.setK(BigDecimal.ZERO);
            pose.setXAxis(xAxis);
            VectorType zAxis = new VectorType();
            zAxis.setI(BigDecimal.ZERO);
            zAxis.setJ(BigDecimal.ZERO);
            zAxis.setK(BigDecimal.ONE);
            pose.setZAxis(zAxis);
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
            pt = stat.getPose().getPoint();
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
