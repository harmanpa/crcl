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
package crcl.utils;

import crcl.base.CRCLProgramType;
import crcl.base.MiddleCommandType;
import crcl.base.MoveToType;
import crcl.base.PointType;
import crcl.base.PoseType;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class ProgramPlotter {

    public ProgramPlotter(View view) {
        this.view = view;
    }

    private boolean autoScale;

    public static enum View {
        OVERHEAD, SIDE;
    }

    final private View view;

    /**
     * Get the value of view
     *
     * @return the value of view
     */
    public View getView() {
        return view;
    }

    /**
     * Get the value of autoScale
     *
     * @return the value of autoScale
     */
    public boolean isAutoScale() {
        return autoScale;
    }

    /**
     * Set the value of autoScale
     *
     * @param autoScale new value of autoScale
     */
    public void setAutoScale(boolean autoScale) {
        this.autoScale = autoScale;
    }

    private Rectangle2D.Double bounds;

    /**
     * Get the value of bounds
     *
     * @return the value of bounds
     */
    public Rectangle2D.Double getBounds() {
        return bounds;
    }

    /**
     * Set the value of bounds
     *
     * @param bounds new value of bounds
     */
    public void setBounds(Rectangle2D.Double bounds) {
        this.bounds = bounds;
    }

    public Rectangle2D.Double findBounds(CRCLProgramType program) {
        Rectangle2D.Double rect = new Rectangle2D.Double();
        double xmin = Double.POSITIVE_INFINITY;
        double xmax = Double.NEGATIVE_INFINITY;
        double ymin = Double.POSITIVE_INFINITY;
        double ymax = Double.NEGATIVE_INFINITY;
        for (MiddleCommandType cmd : program.getMiddleCommand()) {
            if (cmd instanceof MoveToType) {
                MoveToType moveCmd = (MoveToType) cmd;
                Point2D.Double pt2D = toPoint2D(((MoveToType) cmd).getEndPosition());
                xmin = Math.min(xmin, pt2D.x);
                xmax = Math.max(xmax, pt2D.x);
                ymin = Math.min(ymin, pt2D.y);
                ymax = Math.max(ymax, pt2D.y);
            }
        }
        rect.x = xmin;
        rect.width = xmax - xmin;
        rect.y = ymin;
        rect.height = ymax - ymin;
        return rect;
    }

    private int xMargin = 50;

    /**
     * Get the value of xMargin
     *
     * @return the value of xMargin
     */
    public int getxMargin() {
        return xMargin;
    }

    /**
     * Set the value of xMargin
     *
     * @param xMargin new value of xMargin
     */
    public void setxMargin(int xMargin) {
        this.xMargin = xMargin;
    }

    private int yMargin = 50;

    /**
     * Get the value of yMargin
     *
     * @return the value of yMargin
     */
    public int getyMargin() {
        return yMargin;
    }

    /**
     * Set the value of yMargin
     *
     * @param yMargin new value of yMargin
     */
    public void setyMargin(int yMargin) {
        this.yMargin = yMargin;
    }

    public Point2D.Double toPoint2D(PointType pt) {
        switch (view) {
            case SIDE:
                return CRCLPosemath.rzPoint2D(pt);

            case OVERHEAD:
            default:
                return CRCLPosemath.xyPoint2D(pt);
        }
    }

    public Point2D.Double toPoint2D(PoseType pose) {
        return toPoint2D(pose.getPoint());
    }

    private Color background = Color.white;

    /**
     * Get the value of background
     *
     * @return the value of background
     */
    public Color getBackground() {
        return background;
    }

    /**
     * Set the value of background
     *
     * @param background new value of background
     */
    public void setBackground(Color background) {
        this.background = background;
    }

    private Color afterIndexLineColor = Color.black;

    /**
     * Get the value of afterIndexLineColor
     *
     * @return the value of afterIndexLineColor
     */
    public Color getAfterIndexLineColor() {
        return afterIndexLineColor;
    }

    /**
     * Set the value of afterIndexLineColor
     *
     * @param afterIndexLineColor new value of afterIndexLineColor
     */
    public void setAfterIndexLineColor(Color afterIndexLineColor) {
        this.afterIndexLineColor = afterIndexLineColor;
    }

    private Color beforeIndexLineColor = Color.green.darker();

    /**
     * Get the value of beforeIndexLineColor
     *
     * @return the value of beforeIndexLineColor
     */
    public Color getBeforeIndexLineColor() {
        return beforeIndexLineColor;
    }

    /**
     * Set the value of beforeIndexLineColor
     *
     * @param beforeIndexLineColor new value of beforeIndexLineColor
     */
    public void setBeforeIndexLineColor(Color beforeIndexLineColor) {
        this.beforeIndexLineColor = beforeIndexLineColor;
    }

    private Color beforeIndexPointColor = Color.blue.darker();

    /**
     * Get the value of beforeIndexPointColor
     *
     * @return the value of beforeIndexPointColor
     */
    public Color getBeforeIndexPointColor() {
        return beforeIndexPointColor;
    }

    /**
     * Set the value of beforeIndexPointColor
     *
     * @param beforeIndexPointColor new value of beforeIndexPointColor
     */
    public void setBeforeIndexPointColor(Color beforeIndexPointColor) {
        this.beforeIndexPointColor = beforeIndexPointColor;
    }

    private Color afterIndexPointColor = Color.red.darker();

    /**
     * Get the value of afterIndexPointColor
     *
     * @return the value of afterIndexPointColor
     */
    public Color getAfterIndexPointColor() {
        return afterIndexPointColor;
    }

    /**
     * Set the value of afterIndexPointColor
     *
     * @param afterIndexPointColor new value of afterIndexPointColor
     */
    public void setAfterIndexPointColor(Color afterIndexPointColor) {
        this.afterIndexPointColor = afterIndexPointColor;
    }

    private Dimension dimension = new Dimension(256, 256);

    /**
     * Get the value of dimension
     *
     * @return the value of dimension
     */
    public Dimension getDimension() {
        return dimension;
    }

    /**
     * Set the value of dimension
     *
     * @param dimension new value of dimension
     */
    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public BufferedImage plotProgram(CRCLProgramType program, int programIndex) {
        BufferedImage bi = new BufferedImage(dimension.width, dimension.height, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g2d = bi.createGraphics();
        paint(program, g2d, programIndex);
        return bi;
    }

    public void paint(CRCLProgramType program, Graphics2D g2d, int programIndex) {
        if (autoScale || null == bounds) {
            setBounds(findBounds(program));
        }
        double heightUnder = 0;
        double widthUnder = 0;
        if (bounds.height < bounds.width) {
            heightUnder = bounds.width - bounds.height;
        } else {
            widthUnder = bounds.height - bounds.width;
        }
        double scale = Math.min((dimension.width - xMargin) / bounds.width,
                (dimension.height - yMargin) / bounds.height);
        g2d.setBackground(background);
        g2d.clearRect(0, 0, dimension.width, dimension.height);
        g2d.scale(scale, scale);
        g2d.translate(-bounds.x + xMargin / (2 * scale) + widthUnder / 2.0, -bounds.y + yMargin / (2 * scale) + heightUnder / 2.0);

//        g2d.setColor(Color.pink);
//        g2d.draw(bounds);
        Point2D.Double lastPoint = null;
        final double pointSize = Math.max(dimension.width, dimension.height) / 30.0;
        final double halfPointSize = pointSize / 2.0;
        for (int i = 0; i < program.getMiddleCommand().size(); i++) {
            MiddleCommandType cmd = program.getMiddleCommand().get(i);
            if (cmd instanceof MoveToType) {
                MoveToType moveCmd = (MoveToType) cmd;
                Point2D.Double nextPoint = toPoint2D(moveCmd.getEndPosition());
                if (null != lastPoint) {
                    if (i > programIndex+1) {
                        g2d.setColor(afterIndexLineColor);
                    } else {
                        g2d.setColor(beforeIndexLineColor);
                    }
                    g2d.draw(new Line2D.Double(nextPoint, lastPoint));
                }
                lastPoint = nextPoint;
            }
        }
        lastPoint = null;
        for (int i = 0; i < program.getMiddleCommand().size() && i <= programIndex; i++) {
            MiddleCommandType cmd = program.getMiddleCommand().get(i);
            if (cmd instanceof MoveToType) {
                MoveToType moveCmd = (MoveToType) cmd;
                Point2D.Double nextPoint = toPoint2D(moveCmd.getEndPosition());
                if (null != lastPoint) {
                    if (i > programIndex+1) {
                        g2d.setColor(afterIndexLineColor);
                    } else {
                        g2d.setColor(beforeIndexLineColor);
                    }
                    g2d.draw(new Line2D.Double(nextPoint, lastPoint));
                }
                lastPoint = nextPoint;
            }
        }
        for (int i = 0; i < program.getMiddleCommand().size(); i++) {
            MiddleCommandType cmd = program.getMiddleCommand().get(i);
            if (cmd instanceof MoveToType) {
                MoveToType moveCmd = (MoveToType) cmd;
                Point2D.Double nextPoint = toPoint2D(moveCmd.getEndPosition());
                if (i > programIndex+1) {
                    g2d.setColor(afterIndexPointColor);
                } else {
                    g2d.setColor(beforeIndexPointColor);
                }
                g2d.fill(new Arc2D.Double(nextPoint.x - halfPointSize, nextPoint.y - halfPointSize, pointSize, pointSize, 0, 360.0, Arc2D.PIE));
            }
        }
        for (int i = 0; i < program.getMiddleCommand().size() && i <= programIndex+1; i++) {
            MiddleCommandType cmd = program.getMiddleCommand().get(i);
            if (cmd instanceof MoveToType) {
                MoveToType moveCmd = (MoveToType) cmd;
                Point2D.Double nextPoint = toPoint2D(moveCmd.getEndPosition());
                if (i > programIndex+1) {
                    g2d.setColor(afterIndexPointColor);
                } else {
                    g2d.setColor(beforeIndexPointColor);
                }
                g2d.fill(new Arc2D.Double(nextPoint.x - halfPointSize, nextPoint.y - halfPointSize, pointSize, pointSize, 0, 360.0, Arc2D.PIE));
                lastPoint = nextPoint;
            }
        }
        if (null != lastPoint) {
            g2d.setColor(beforeIndexPointColor);
            g2d.draw(new Line2D.Double(lastPoint.x, bounds.y - yMargin / (2 * scale) - heightUnder / 2.0,
                    lastPoint.x, bounds.y + bounds.height + yMargin / (2 * scale) + heightUnder / 2.0));
            g2d.draw(new Line2D.Double(bounds.x - xMargin / (2 * scale) - widthUnder / 2.0, lastPoint.y,
                    bounds.x + bounds.width + xMargin / (2 * scale) + widthUnder / 2.0, lastPoint.y));
        }
    }

    public static void main(String[] args) throws CRCLException, IOException {
        ProgramPlotter pp = new ProgramPlotter(View.OVERHEAD);
        pp.setAutoScale(true);
        CRCLSocket cs = new CRCLSocket();
        CRCLProgramType program = CRCLSocket.readProgramFile("/home/shackle/usnistgov/crcl/tools/java/crcl4java/crcl4java-base/src/test/resources/main/MOVE_ALL_RED_PEGS.xml");
        BufferedImage bi = pp.plotProgram(program, 34);
        ImageIO.write(bi, "jpg", new File("test.jpg"));
    }
}
