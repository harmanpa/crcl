/*
 * This software is public domain software, however it is preferred
 * that the following disclaimers be attached.
 * Software Copywrite/Warranty Disclaimer
 * 
 * This software was developed at the National Institute of Standards and
 * Technology by employees of the Federal Government in the course of their
 * official duties. Pursuant to title 17 Section 105 of the United States
 * Code this software is not subject to copyright protection and is in the
 * public domain.
 * 
 * This software is experimental. NIST assumes no responsibility whatsoever 
 * for its use by other parties, and makes no guarantees, expressed or 
 * implied, about its quality, reliability, or any other characteristic. 
 * We would appreciate acknowledgement if the software is used. 
 * This software can be redistributed and/or modified freely provided 
 * that any derivative works bear some notice that they are derived from it, 
 * and any modified versions bear some notice that they have been modified.
 * 
 *  See http://www.copyright.gov/title17/92chap1.html#105
 * 
 */
package crcl.ui.forcetorquesensorsimulator;

import crcl.base.PointType;
import crcl.base.PoseType;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author will
 */
public class InOutJPanel extends JPanel {

    public InOutJPanel() {
        myMouseAdapter = new InOutMouseAdapter();
        System.out.println("InOutJPanel.this.getSize() = " + InOutJPanel.this.getSize());
        super.addMouseListener(myMouseAdapter);
        super.addMouseMotionListener(myMouseAdapter);
    }

    private List<TrayStack> stacks;

    /**
     * Get the value of stacks
     *
     * @return the value of stacks
     */
    public List<TrayStack> getStacks() {
        return stacks;
    }

    /**
     * Set the value of stacks
     *
     * @param stacks new value of stacks
     */
    public void setStacks(List<TrayStack> stacks) {
        this.stacks = stacks;
        this.repaint();
    }

    private class InOutMouseAdapter extends MouseAdapter {

        @Override
        public void mouseMoved(MouseEvent e) {
//            super.mouseMoved(e); 
            mousePoint = new Point2D.Double(e.getX(), e.getY());
            InOutJPanel.this.repaint();
        }
    }

    private final InOutMouseAdapter myMouseAdapter;

    private Point2D.Double mousePoint = new Point2D.Double();

    private PoseType robotPose;

    public PoseType getRobotPose() {
        return robotPose;
    }

    public void setRobotPose(PoseType robotPose) {
        this.robotPose = robotPose;
        this.repaint();
    }

    public double poseStackZDiff(TrayStack stack) {
        if (stack == null || robotPose == null) {
            return Double.NaN;
        }

        PointType posePoint = robotPose.getPoint();
        if (null == posePoint) {
            return Double.NaN;
        }
        Point2D.Double point = new Point2D.Double(posePoint.getX(), posePoint.getY());
        if (insideStack(stack, point)) {
            final double stackTopZ = stack.z + stack.count * stack.height;
            double zdiff = stackTopZ - posePoint.getZ();
            return zdiff;
        } else {
            return Double.NaN;
        }
    }

    public static boolean insideStack(TrayStack stack, Point2D.Double point) {
        Rectangle2D.Double rect = new Rectangle2D.Double(stack.x, stack.y, stack.width, stack.length);
        double xdiff = point.x - rect.x;
        double ydiff = point.y - rect.y;
        double c = Math.cos(stack.rotationRadians);
        double s = Math.sin(stack.rotationRadians);
        double xdiffrot = xdiff * c + ydiff * s;
        double ydiffrot = ydiff * c - xdiff * s;
//        g2d.setColor(Color.GREEN);
//        g2d.draw(new Line2D.Double(rect.x, rect.y, rect.x + xdiffrot, rect.y + ydiffrot));
        return ((xdiffrot < rect.width) && (ydiffrot < stack.length) && (xdiffrot > 0) && (ydiffrot > 0));

    }

    @Override
    protected void paintComponent(Graphics g) {

        g.setColor(Color.white);
        g.fillRect(0, 0, 3000, 3000);
//        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.black);

        final double hvac = Math.cos(Math.toRadians(heightViewAngle));
        final double hvas = Math.sin(Math.toRadians(heightViewAngle));
        if (hvac > Double.MIN_NORMAL) {
            final Point2D.Double effectiveMousePoint = new Point2D.Double(mousePoint.x, mousePoint.y / hvac);

            g.drawString(effectiveMousePoint.toString(), 20, 20);

            final Point2D.Double robotPoint;
            final Point2D.Double robotShadowPoint;
            if (null != robotPose) {
                final PointType robotPosePoint = robotPose.getPoint();
                if (null != robotPosePoint) {
                    g.drawString("robot:" + robotPosePoint.getX() + ", " + robotPosePoint.getY() + ", " + robotPosePoint.getZ(), 20, 40);
                    robotPoint = new Point2D.Double(robotPosePoint.getX(), robotPosePoint.getY() * hvac - robotPosePoint.getZ() * hvas);
                    robotShadowPoint = new Point2D.Double(robotPosePoint.getX(), robotPosePoint.getY() * hvac);
                } else {
                    robotPoint = null;
                    robotShadowPoint = null;
                }
            } else {
                robotPoint = null;
                 robotShadowPoint = null;
            }

            if (null != stacks) {

                for (TrayStack stack : stacks) {
//                    Rectangle2D.Double rect = new Rectangle2D.Double(stack.x, stack.y, stack.width, stack.length);
                    final boolean inSide = insideStack(stack, effectiveMousePoint) || (null != robotPoint && poseStackZDiff(stack) > 0);
//                final AffineTransform transform = g2d.getTransform();

                    final double src = Math.cos(stack.rotationRadians);
                    final double srs = Math.sin(stack.rotationRadians);
                    final double x1 = stack.x;
                    final double y1 = stack.y;
                    final double x2 = x1 + stack.width * src;
                    final double y2 = y1 + stack.width * srs;

                    final double x3 = x2 - stack.length * srs;
                    final double y3 = y2 + stack.length * src;
                    final double x4 = x1 - stack.length * srs;
                    final double y4 = y1 + stack.length * src;
                    for (int i = 0; i < stack.count + 1; i++) {
                        final double yinc = stack.height * hvas;
                        Point2D.Double p1 = new Point2D.Double(x1, y1 * hvac - yinc * i);
                        Point2D.Double p2 = new Point2D.Double(x2, y2 * hvac - yinc * i);
                        Point2D.Double p3 = new Point2D.Double(x3, y3 * hvac - yinc * i);
                        Point2D.Double p4 = new Point2D.Double(x4, y4 * hvac - yinc * i);
                        Path2D.Double path = fourPointsClosedPath(p1, p2, p3, p4);

                        if (i > 0) {

                            final Point2D.Double p1Next = new Point2D.Double(p1.x, p1.y + yinc);
                            final Point2D.Double p2Next = new Point2D.Double(p2.x, p2.y + yinc);
                            final Point2D.Double p3Next = new Point2D.Double(p3.x, p3.y + yinc);
                            final Point2D.Double p4Next = new Point2D.Double(p4.x, p4.y + yinc);
                            if (inSide) {
                                g2d.setColor(Color.red);
                                g2d.fill(fourPointsClosedPath(p1, p1Next, p2Next, p2));
                                g2d.fill(fourPointsClosedPath(p2, p2Next, p3Next, p3));
                                g2d.fill(fourPointsClosedPath(p3, p3Next, p4Next, p4));
                                g2d.fill(fourPointsClosedPath(p4, p4Next, p1Next, p1));
                            }
                            g2d.setColor(Color.black);
                            g2d.draw(new Line2D.Double(p1, p1Next));
                            g2d.draw(new Line2D.Double(p2, p2Next));
                            g2d.draw(new Line2D.Double(p3, p3Next));
                            g2d.draw(new Line2D.Double(p4, p4Next));
//                        g2d.draw(new Line2D.Double(rect.x + rect.width, rect.y + stack.length, rect.x + rect.width, rect.y + stack.length - yinc));
//                        g2d.draw(new Line2D.Double(rect.x, rect.y + stack.length, rect.x, rect.y + stack.length - yinc));
                        }
//                    g2d.rotate(stack.rotationRadians, rect.x, rect.y);
//                    g2d.scale(1.0, hvac);

                        if (inSide) {
                            g2d.setColor(Color.red);
                            g2d.fill(path);
                        }
                        g2d.setColor(Color.black);
                        g2d.draw(path);
                        g2d.drawString(Integer.toString(i), (float) p1.x, (float) p1.y);
//                    rect.y -= yinc;
//                    g2d.setTransform(transform);
                    }
                    g2d.drawString(stack.name, (float) stack.x, (float) stack.y);
                }
            }
            if (null != robotPoint) {
                g.setColor(Color.red);
                g2d.draw(new Line2D.Double(robotPoint.x - 10, robotPoint.y, robotPoint.x + 10, robotPoint.y));
                g2d.draw(new Line2D.Double(robotPoint.x, robotPoint.y - 10, robotPoint.x, robotPoint.y + 10));
            }
             if (null != robotShadowPoint) {
                g.setColor(Color.pink);
                g2d.draw(new Line2D.Double(robotShadowPoint.x - 10, robotShadowPoint.y, robotShadowPoint.x + 10, robotShadowPoint.y));
                g2d.draw(new Line2D.Double(robotShadowPoint.x, robotShadowPoint.y - 10, robotShadowPoint.x, robotShadowPoint.y + 10));
            }
            g.setColor(Color.cyan);
            g2d.draw(new Line2D.Double(effectiveMousePoint.x - 10, effectiveMousePoint.y, effectiveMousePoint.x + 10, effectiveMousePoint.y));
            g2d.draw(new Line2D.Double(effectiveMousePoint.x, effectiveMousePoint.y - 10, effectiveMousePoint.x, effectiveMousePoint.y + 10));
        }
        g.setColor(Color.black);
        g2d.draw(new Line2D.Double(mousePoint.x - 10, mousePoint.y, mousePoint.x + 10, mousePoint.y));
        g2d.draw(new Line2D.Double(mousePoint.x, mousePoint.y - 10, mousePoint.x, mousePoint.y + 10));
    }

    private Path2D.Double fourPointsClosedPath(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3, Point2D.Double p4) {
        Path2D.Double path = new Path2D.Double();
        path.moveTo(p1.x, p1.y);
        path.lineTo(p2.x, p2.y);
        path.lineTo(p3.x, p3.y);
        path.lineTo(p4.x, p4.y);
        path.closePath();
        return path;
    }
    private double heightViewAngle = 30.0;

    public double getHeightViewAngle() {
        return heightViewAngle;
    }

    public void setHeightViewAngle(double heightViewAngle) {
        this.heightViewAngle = heightViewAngle;
        this.repaint();
    }

}
