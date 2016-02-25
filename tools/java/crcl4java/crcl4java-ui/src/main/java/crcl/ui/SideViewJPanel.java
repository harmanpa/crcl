/* 
 * This is public domain software, however it is preferred
 * that the following disclaimers be attached.
 * 
 * Software Copywrite/Warranty Disclaimer
 * 
 * This software was developed at the National Institute of Standards and
 * Technology by employees of the Federal Government in the course of their
 * official duties. Pursuant to title 17 Section 105 of the United States
 * Code this software is not subject to copyright protection and is in the
 * public domain. This software is experimental.
 * NIST assumes no responsibility whatsoever for its use by other
 * parties, and makes no guarantees, expressed or implied, about its
 * quality, reliability, or any other characteristic. We would appreciate
 * acknowledgment if the software is used. This software can be
 * redistributed and/or modified freely provided that any derivative works
 * bear some notice that they are derived from it, and any modified
 * versions bear some notice that they have been modified.
 * 
 */
package crcl.ui;

import crcl.utils.SimRobotEnum;
import static crcl.utils.SimRobotEnum.PLAUSIBLE;
import static crcl.utils.SimRobotEnum.SIMPLE;
import crcl.utils.SimulatedKinematicsPlausible;
import crcl.utils.SimulatedKinematicsSimple;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class SideViewJPanel extends JPanel {

     private String tempDir = System.getProperty("temp.dir","/tmp");

    /**
     * Get the value of tempDir
     *
     * @return the value of tempDir
     */
    public String getTempDir() {
        return tempDir;
    }

    /**
     * Set the value of tempDir
     *
     * @param tempDir new value of tempDir
     */
    public void setTempDir(String tempDir) {
        this.tempDir = tempDir;
    }
    
    private SimRobotEnum robotType = SimRobotEnum.PLAUSIBLE;

    /**
     * Get the value of robotType
     *
     * @return the value of robotType
     */
    public SimRobotEnum getRobotType() {
        return robotType;
    }

    /**
     * Set the value of robotType
     *
     * @param robotType new value of robotType
     */
    public void setRobotType(SimRobotEnum robotType) {
        this.robotType = robotType;
        this.repaint();
    }

    private double[] jointvals;

    /**
     * Get the value of jointvals
     *
     * @return the value of jointvals
     */
    public double[] getJointvals() {
        return jointvals;
    }

    /**
     * Set the value of jointvals
     *
     * @param jointvals new value of jointvals
     */
    public void setJointvals(double[] jointvals) {
        this.jointvals = jointvals;
        try {
            if (logImages) {
                logImage();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logImages = false;
        }
    }

    public void logImage() throws IOException {
        Dimension d = this.getSize();
        BufferedImage bi = new BufferedImage(d.width, d.height, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g2d = bi.createGraphics();
        g2d.setColor(this.getBackground());
        g2d.fillRect(0, 0, d.width, d.height);
        g2d.setColor(this.getForeground());
        this.paintImage(g2d, d);
        File dir = new File(tempDir,"/simserver/side");
        dir.mkdirs();
        ImageIO.write(bi, "jpg", new File(dir, "side_" + System.currentTimeMillis() + ".jpg"));
    }
    private boolean logImages = false;

    /**
     * Get the value of logImages
     *
     * @return the value of logImages
     */
    public boolean isLogImages() {
        return logImages;
    }

    /**
     * Set the value of logImages
     *
     * @param logImages new value of logImages
     */
    public void setLogImages(boolean logImages) {
        this.logImages = logImages;
    }

    private double[] seglengths = SimulatedKinematicsPlausible.DEFAULT_SEGLENGTHS;

    /**
     * Get the value of seglengths
     *
     * @return the value of seglengths
     */
    public double[] getSeglengths() {
        return seglengths;
    }

    /**
     * Set the value of seglengths
     *
     * @param seglengths new value of seglengths
     */
    public void setSeglengths(double[] seglengths) {
        this.seglengths = seglengths;
        if (null == seglengths || seglengths.length < 1) {
            return;
        }
        l0rect.width = seglengths[0];
        if (seglengths.length < 2) {
            return;
        }
        l1rect.width = seglengths[1];
        if (seglengths.length < 3) {
            return;
        }
        l2rect.width = seglengths[2];
        if (seglengths.length < 4) {
            return;
        }
        l3rect.width = seglengths[3];
        if (seglengths.length < 5) {
            return;
        }
        l4rect.height = seglengths[4];
        if (seglengths.length < 6) {
            return;
        }
        l5rect.width = seglengths[5];
    }

    Arc2D.Double j1circle = new Arc2D.Double(-10.0, -10.0 // x,y
            , seglengths[3], seglengths[3] // w,h
            , 0, 360.0, Arc2D.CHORD);

    Arc2D.Double j2circle = new Arc2D.Double(-6, -6.0 // x,y
            , 12, 12 // w,h
            , 0, 360, Arc2D.CHORD);

    //Rectangle2D.Double jrect = new Rectangle2D.Double(0.0, -5.0, 10.0, 10.0);
    Rectangle2D.Double l0rect = new Rectangle2D.Double(0.0, -5.0, seglengths[0], 10.0);
    Rectangle2D.Double l1rect = new Rectangle2D.Double(0.0, -5.0, seglengths[1], 10.0);
    Rectangle2D.Double l2rect = new Rectangle2D.Double(0.0, -5.0, seglengths[2], 10.0);
    Rectangle2D.Double l3rect = new Rectangle2D.Double(0.0, -5.0, seglengths[3], 10.0);
    Rectangle2D.Double l4rect = new Rectangle2D.Double(0.0, -10.0, 5.0, seglengths[4]);
    Rectangle2D.Double l5rect = new Rectangle2D.Double(0.0, -0.5, seglengths[5], 1.0);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            Graphics2D g2d = (Graphics2D) g;
            Dimension d = this.getSize();
            if (paintImage(g2d, d)) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean paintImage(Graphics2D g2d, Dimension d) {
        g2d.translate(d.width / 2.0, d.height / 2.0);
        switch (robotType) {
            case PLAUSIBLE:
                if (paintPlausibleRobot(d, g2d)) {
                    return true;
                }
                break;
            case SIMPLE:
                if (paintSimpleRobot(d, g2d)) {
                    return true;
                }
        }
        return false;
    }

    private double maxSimpleJv0 = 0;

    private boolean paintSimpleRobot(Dimension d, Graphics2D g2d) {
        if (null == jointvals || jointvals.length < SimulatedKinematicsSimple.NUM_JOINTS) {
            return true;
        }
        maxSimpleJv0 = Math.max(maxSimpleJv0, jointvals[0]);
        double sfactor = Math.min(d.width / 2.0, d.height / 2.0) / (Math.abs(maxSimpleJv0) + SimulatedKinematicsSimple.DEFAULT_SEGLENGTHS[0]);
        g2d.scale(sfactor, -1.0 * sfactor);
        g2d.setColor(Color.gray);
        g2d.fill(j1circle);
        l0rect.width = jointvals[0];
        g2d.rotate(Math.toRadians(jointvals[2]));
        g2d.setColor(Color.yellow);
        g2d.fill(l0rect);
        g2d.translate(l0rect.width, 0.0);
        g2d.setColor(Color.gray);
        g2d.fill(j1circle);
        l1rect.width = Math.cos(Math.toRadians(jointvals[5] - jointvals[2])) * SimulatedKinematicsSimple.DEFAULT_SEGLENGTHS[0];
        g2d.rotate(Math.toRadians(jointvals[4] - jointvals[2]));
        g2d.setColor(Color.yellow);
        g2d.fill(l1rect);
        return false;
    }

    private boolean paintPlausibleRobot(Dimension d, Graphics2D g2d) {
        double sfactor = Math.min(d.width / 2.0, d.height / 2.0) / (seglengths[0] + seglengths[1] + seglengths[2] + seglengths[3]);
        g2d.scale(sfactor, -1.0 * sfactor);
        g2d.setColor(Color.gray);
        g2d.fill(j1circle);
        if (null == jointvals) {
            return true;
        }
        g2d.rotate(Math.toRadians(jointvals[1]));
        g2d.setColor(Color.yellow);
        g2d.fill(l0rect);
        g2d.translate(l0rect.width, 0.0);
        g2d.setColor(Color.gray);
        g2d.fill(j1circle);
        g2d.rotate(Math.toRadians(jointvals[2]));
        g2d.setColor(Color.yellow);
        g2d.fill(l1rect);
        g2d.translate(l1rect.width, 0.0);
        g2d.setColor(Color.gray);
        g2d.fill(j1circle);
        g2d.rotate(Math.toRadians(jointvals[3]));
        g2d.setColor(Color.yellow);
        g2d.fill(l2rect);
        g2d.translate(l2rect.width, 0.0);
        l3rect.width = this.seglengths[3] * Math.cos(Math.toRadians(jointvals[4]));
        if (l3rect.width <= 0) {
            return true;
        }
        g2d.setColor(Color.yellow);
        g2d.fill(l3rect);
        g2d.setColor(Color.gray);
        g2d.fill(j2circle);
        g2d.translate(l3rect.width, 0.0);
        g2d.setColor(Color.BLACK);
        l4rect.height = seglengths[4] * Math.abs(Math.cos(Math.toRadians(jointvals[5])));
        l4rect.y = -0.5 * l4rect.height;
        g2d.fill(l4rect);
        g2d.translate(0.0, l4rect.height / 2.0);
        g2d.fill(l5rect);
        g2d.translate(0.0, -l4rect.height);
        g2d.fill(l5rect);
        return false;
    }
    private static final Logger LOG = Logger.getLogger(SideViewJPanel.class.getName());

}
