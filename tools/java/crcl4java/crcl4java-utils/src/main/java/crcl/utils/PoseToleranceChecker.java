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
 *  This software is experimental. NIST assumes no responsibility whatsoever for its use by other
 * parties, and makes no guarantees, expressed or implied, about its
 * quality, reliability, or any other characteristic. We would appreciate
 * acknowledgement if the software is used. This software can be
 * redistributed and/or modified freely provided that any derivative works
 * bear some notice that they are derived from it, and any modified
 * versions bear some notice that they have been modified.
 * 
 *  See http://www.copyright.gov/title17/92chap1.html#105
 * 
 */
package crcl.utils;


import crcl.base.AngleUnitEnumType;
import crcl.base.PointType;
import crcl.base.PoseType;
import crcl.base.PoseToleranceType;
import crcl.base.VectorType;
import static crcl.utils.CRCLPosemath.dot;
import static crcl.utils.CRCLPosemath.getNonNullZAxis;
import static crcl.utils.CRCLUtils.getNonNullPoint;
import static crcl.utils.CRCLUtils.getNonNullXAxis;
import java.math.BigDecimal;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.qual.Nullable;


public class PoseToleranceChecker {
    
    private PoseToleranceChecker() {
        // Can't call me
    }
    
    
    static public boolean containsNull(@Nullable PointType pt) {
        return  pt == null;
//                || pt.getY() == null 
//                || pt.getY() == null
//                || pt.getZ() == null;
    }
    
    static public boolean containsNull(@Nullable VectorType vec) {
        return  vec == null;
//                || vec.getI() == null 
//                || vec.getJ() == null
//                || vec.getK() == null;
    }
    
    
    static public boolean containsNull(@Nullable PoseType pose) {
        return pose == null
                || containsNull(pose.getPoint())
                || containsNull(pose.getXAxis())
                || containsNull(pose.getZAxis());
    }
    
    static public boolean containsNull(@Nullable PoseToleranceType tol) {
        return tol == null
                || tol.getYPointTolerance() == null
                || tol.getYPointTolerance() == null
                || tol.getZPointTolerance() == null
                || tol.getXAxisTolerance() == null
                || tol.getZAxisTolerance() == null;
    }
    
    
    static public boolean isInTolerance(BigDecimal v1, BigDecimal v2, BigDecimal tol) {
           return v1.subtract(v2).abs().compareTo(tol) <= 0;
    }
    
    static public boolean isInTolerance(double v1, double v2,@Nullable Double tol) {
           return tol == null || Double.compare(Math.abs(v2-v1), tol.doubleValue()) <= 0;
    }
    
    static public boolean isInTolerance(double v1, double v2, double tol) {
           return Double.compare(Math.abs(v2-v1), tol) <= 0;
    }
    
    static public boolean isInTolerance(final PointType pt1, final PointType pt2, final PoseToleranceType tol) {
        final Double xPointTolerance = tol.getXPointTolerance();
        final Double yPointTolerance = tol.getYPointTolerance();
        final Double zPointTolerance = tol.getZPointTolerance();
        return 
                isInTolerance(pt1.getX(),pt2.getX(), xPointTolerance)
                && isInTolerance(pt1.getY(),pt2.getY(), yPointTolerance)
                && isInTolerance(pt1.getZ(),pt2.getZ(), zPointTolerance);
    }
    
    
    
    static public boolean isInTolerance(VectorType v1, VectorType v2, BigDecimal tol, AngleUnitEnumType angleType) {
        if(null == tol) {
            return true;
        }
        return isInTolerance(v1, v2, tol.doubleValue(), angleType);
    }

     static public boolean isInTolerance(VectorType v1, VectorType v2, @Nullable Double tol, AngleUnitEnumType angleType) {
        return tol == null || isInTolerance(v1, v2, tol.doubleValue(), angleType);
    }
     
    static public boolean isInTolerance(VectorType v1, VectorType v2, double tol, AngleUnitEnumType angleType) {
        double dot = dot(v1, v2);
        if(angleType == AngleUnitEnumType.DEGREE) {
            return dot > Math.cos(Math.toRadians(tol));
        }
        return dot > Math.cos(tol);
    }
    
    static public boolean isInTolerance(final PoseType pose1, final PoseType pose2, 
            final PoseToleranceType tol, final AngleUnitEnumType angleType) {
        final PointType pt1 = getNonNullPoint(pose1);
        final PointType pt2 = getNonNullPoint(pose2);
        final VectorType xAxis1 = getNonNullXAxis(pose1);
        final VectorType xAxis2 = getNonNullXAxis(pose2);
        final VectorType zAxis1 = getNonNullZAxis(pose1);
        final VectorType zAxis2 = getNonNullZAxis(pose2);
        return isInTolerance(pt1, pt2,tol)
               && isInTolerance(xAxis1, xAxis2,tol.getXAxisTolerance(),angleType)
               && isInTolerance(zAxis1, zAxis2,tol.getZAxisTolerance(),angleType);
    }
    
    private static double absDiff(double a, double b) {
        return Math.abs(a-b);
    }
    
    private static BigDecimal absDiff(BigDecimal a, BigDecimal b) {
        return a.subtract(b).abs();
    }
    
    static public String checkToleranceString(
            final PoseType pose1, 
            final PoseType pose2, 
            final PoseToleranceType tol, 
            final AngleUnitEnumType angleType) {
        StringBuilder sb = new StringBuilder();
        final PointType pt1 = getNonNullPoint(pose1);
        final PointType pt2 = getNonNullPoint(pose2);
        if(!isInTolerance(pt1.getX(), pt2.getX(), tol.getXPointTolerance())) {
            double diff = absDiff(pt1.getX(), pt2.getX());
            sb.append("X Point positions ").append(pt1.getX()).append(" and ").append(pt2.getX()).append(" differ by ").append(diff).append(" over tolerance of ").append(tol.getXPointTolerance()).append("\n");
        }
        if(!isInTolerance(pt1.getY(), pt2.getY(), tol.getYPointTolerance())) {
            double diff = absDiff(pt1.getY(), pt2.getY());
            sb.append("Y Point positions ").append(pt1.getY()).append(" and ").append(pt2.getY()).append(" differ by ").append(diff).append(" over tolerance of ").append(tol.getYPointTolerance()).append("\n");
        }
        if(!isInTolerance(pt1.getZ(), pt2.getZ(), tol.getZPointTolerance())) {
            double diff = absDiff(pt1.getZ(), pt2.getZ());
            sb.append("Z Point positions ").append(pt1.getZ()).append(" and ").append(pt2.getZ()).append(" differ by ").append(diff).append(" over tolerance of ").append(tol.getZPointTolerance()).append("\n");
        }
        final VectorType xAxis1 = getNonNullXAxis(pose1);
        final VectorType xAxis2 = getNonNullXAxis(pose2);
        final Double xAxisTolerance = tol.getXAxisTolerance();
        if(null != xAxisTolerance && !isInTolerance(xAxis1, xAxis2, xAxisTolerance,angleType)) {
            sb.append("X Axis diff by ").append(Math.toDegrees(Math.acos(dot(xAxis1, xAxis2)))).append(" degrees.");
            if(angleType == AngleUnitEnumType.DEGREE) {
                sb.append(" over tolerance of ").append(xAxisTolerance).append(" degrees\n");
            }
            else {
                sb.append(" over tolerance of ").append(Math.toDegrees(xAxisTolerance)).append(" degrees\n");
            }
        }
        final VectorType zAxis1 = getNonNullZAxis(pose1);
        final VectorType zAxis2 = getNonNullZAxis(pose2);        
        final Double zAxisTolerance = tol.getZAxisTolerance();
        if(null != zAxisTolerance && !isInTolerance(zAxis1, zAxis2, zAxisTolerance,angleType)) {
            sb.append("Z Axis diff by ").append(Math.toDegrees(Math.acos(dot(zAxis1, zAxis2)))).append(" degrees.");
            if(angleType == AngleUnitEnumType.DEGREE) {
                sb.append(" over tolerance of ").append(zAxisTolerance).append(" degrees\n");
            }
            else {
                sb.append(" over tolerance of ").append(Math.toDegrees(zAxisTolerance)).append(" degrees\n");
            }
        }
        return sb.toString();
    }
    private static final Logger LOG = Logger.getLogger(PoseToleranceChecker.class.getName());
}
