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
package crcl.utils;

import crcl.base.CRCLProgramType;
import crcl.base.CRCLStatusType;
import crcl.base.CommandStateEnumType;
import crcl.base.CommandStatusType;
import crcl.base.GripperStatusType;
import crcl.base.JointStatusType;
import crcl.base.JointStatusesType;
import crcl.base.ParallelGripperStatusType;
import crcl.base.PointType;
import crcl.base.PoseStatusType;
import crcl.base.PoseToleranceType;
import crcl.base.PoseType;
import crcl.base.TwistType;
import crcl.base.VectorType;
import crcl.base.WrenchType;
import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import rcs.posemath.PmCartesian;
import rcs.posemath.PmException;
import rcs.posemath.PmPose;
import rcs.posemath.PmQuaternion;
import rcs.posemath.PmRotationMatrix;
import rcs.posemath.PmRotationVector;
import rcs.posemath.PmRpy;
import rcs.posemath.Posemath;

@SuppressWarnings("nullness")
public class CRCLPosemathTest {

    public CRCLPosemathTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    private PointType pt123 = null;
    private PointType pt321 = null;
    private PmCartesian cart123 = null;
    private PmCartesian cart321 = null;
    private VectorType xvec = null;
    private VectorType yvec = null;
    private VectorType zvec = null;
    private PoseType pose123 = null;
    private PoseType pose321 = null;
    private PoseType pose321rot90 = null;
    private PmPose pmPose123 = null;

    static final private double ASSERT_TOLERANCE_DELTA = 1e-6;

    private void checkEquals(String msg, double v1, double v2) {
        assertEquals(msg, v1, v2, ASSERT_TOLERANCE_DELTA);
    }

//    private void checkEquals(String msg, BigDecimal v1,double v2) {
//        assertEquals(msg, v1.doubleValue(), v2, ASSERT_TOLERANCE_DELTA);
//    }
//    
//    private void checkEquals(String msg, double v1, BigDecimal v2) {
//        assertEquals(msg, v1, v2.doubleValue(), ASSERT_TOLERANCE_DELTA);
//    }
    private void checkEquals(String msg, BigDecimal v1, BigDecimal v2) {
        assertTrue(msg + " both are null or neither is null", (v1 == null) == (v2 == null));
        if (v1 == null) {
            return;
        }
        checkEquals(msg, v1.doubleValue(), v2.doubleValue());
    }

    private void checkEquals(String msg, PmCartesian cart1, PmCartesian cart2) {
        checkEquals(msg + ".x", cart1.x, cart2.x);
        checkEquals(msg + ".y", cart1.y, cart2.y);
        checkEquals(msg + ".z", cart1.z, cart2.z);
    }

    private void checkEquals(String msg, PmQuaternion quat1, PmQuaternion quat2) {
        checkEquals(msg + ".s", quat1.s, quat2.s);
        checkEquals(msg + ".x", quat1.x, quat2.x);
        checkEquals(msg + ".y", quat1.y, quat2.y);
        checkEquals(msg + ".z", quat1.z, quat2.z);
    }

    private void checkEquals(String msg, PmPose p1, PmPose p2) {
        checkEquals(msg + ".tran", p1.tran, p2.tran);
        checkEquals(msg + ".rot", p1.rot, p2.rot);
    }

    private void checkEquals(String msg, PmRotationMatrix cart1, PmRotationMatrix cart2) {
        checkEquals(msg + ".x", cart1.x, cart2.x);
        checkEquals(msg + ".y", cart1.y, cart2.y);
        checkEquals(msg + ".z", cart1.z, cart2.z);
    }

    private void checkEquals(String msg, PointType pt1, PointType pt2) {
        checkEquals(msg + ".getX()", pt1.getX(), pt2.getX());
        checkEquals(msg + ".getY()", pt1.getY(), pt2.getY());
        checkEquals(msg + ".getZ()", pt1.getZ(), pt2.getZ());
    }

    private void checkEquals(String msg, VectorType v1, VectorType v2) {
        checkEquals(msg + ".getI()", v1.getI(), v2.getI());
        checkEquals(msg + ".getJ()", v1.getJ(), v2.getJ());
        checkEquals(msg + ".getK()", v1.getK(), v2.getK());
    }

    private void checkEquals(String msg, PoseType pose1, PoseType pt2) {
        checkEquals(msg + ".getPoint()", pose1.getPoint(), pt2.getPoint());
        checkEquals(msg + ".getXAxis()", pose1.getXAxis(), pt2.getXAxis());
        checkEquals(msg + ".getZAxis()", pose1.getZAxis(), pt2.getZAxis());
    }

    @Before
    public void setUp() {
        pt123 = new PointType();
        pt123.setX(BIG_DECIMAL_1);
        pt123.setY(BIG_DECIMAL_2);
        pt123.setZ(BIG_DECIMAL_3);

        pt321 = new PointType();
        pt321.setX(BIG_DECIMAL_3);
        pt321.setY(BIG_DECIMAL_2);
        pt321.setZ(BIG_DECIMAL_1);

        xvec = new VectorType();
        xvec.setI(BigDecimal.ONE);
        xvec.setJ(BigDecimal.ZERO);
        xvec.setK(BigDecimal.ZERO);

        yvec = new VectorType();
        yvec.setI(BigDecimal.ZERO);
        yvec.setJ(BigDecimal.ONE);
        yvec.setK(BigDecimal.ZERO);

        zvec = new VectorType();
        zvec.setI(BigDecimal.ZERO);
        zvec.setJ(BigDecimal.ZERO);
        zvec.setK(BigDecimal.ONE);

        pose123 = new PoseType();
        pose123.setPoint(pt123);
        pose123.setXAxis(xvec);
        pose123.setZAxis(zvec);

        pose321 = new PoseType();
        pose321.setPoint(pt321);
        pose321.setXAxis(xvec);
        pose321.setZAxis(zvec);

        pose321rot90 = new PoseType();
        pose321rot90.setPoint(pt321);
        pose321rot90.setXAxis(yvec);
        pose321rot90.setZAxis(zvec);

        cart123 = new PmCartesian(1.0, 2.0, 3.0);
        cart321 = new PmCartesian(3.0, 2.0, 1.0);
    }

    private static final BigDecimal BIG_DECIMAL_1 = BigDecimal.ONE;
    private static final BigDecimal BIG_DECIMAL_2 = new BigDecimal("2");
    private static final BigDecimal BIG_DECIMAL_3 = new BigDecimal("3");
    private static final BigDecimal BIG_DECIMAL_4 = new BigDecimal("4");

    @After
    public void tearDown() {
    }

    /**
     * Test of toPmCartesian method, of class CRCLToPosemath.
     */
    @Test
    public void testPointToPmCartesian() {
        System.out.println("pointToPmCartesian");
        PointType pt = this.pt123;
        PmCartesian expResult = this.cart123;
        PmCartesian result = CRCLPosemath.toPmCartesian(pt);
        checkEquals("123", expResult, result);
    }

    /**
     * Test of diffPoints method, of class CRCLToPosemath.
     */
    @Test
    public void testDiffPoints() {
        System.out.println("diffPoints");
        PointType pt1 = this.pt123;
        PointType pt2 = this.pt321;

        // result should = sqrt((3-1)^2 + (2-2)^2 + (1-3)^2) = sqrt(8)
        double expResult = Math.sqrt(8);
        double result = CRCLPosemath.diffPoints(pt1, pt2);

        assertEquals(expResult, result, ASSERT_TOLERANCE_DELTA);
    }

    /**
     * Test of diffPosesTran method, of class CRCLToPosemath.
     */
    @Test
    public void testDiffPosesTran() {
        System.out.println("diffPosesTran");
        PoseType p1 = this.pose123;
        PoseType p2 = this.pose321;
        // result should = sqrt((3-1)^2 + (2-2)^2+(1-3)^2) = sqrt(8)
        double expResult = Math.sqrt(8.0);
        double result = CRCLPosemath.diffPosesTran(p1, p2);
        assertEquals(expResult, result, ASSERT_TOLERANCE_DELTA);
    }

    /**
     * Test of vectorToPmCartesian method, of class CRCLToPosemath.
     */
    @Test
    public void testVectorToPmCartesian() {
        System.out.println("vectorToPmCartesian");
        VectorType v = this.xvec;
        PmCartesian expResult = new PmCartesian(1.0, 0.0, 0.0);
        PmCartesian result = CRCLPosemath.vectorToPmCartesian(v);
        checkEquals("xvec", expResult, result);
    }

    /**
     * Test of toPoseType method, of class CRCLToPosemath.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testToPoseType() throws Exception {
        System.out.println("toPoseType");
        PmCartesian tran = this.cart321;
        PmRotationVector v = new PmRotationVector(Math.PI / 2, 0.0, 0.0, 1.0);
        PoseType expResult = this.pose321rot90;
        PoseType result = CRCLPosemath.toPoseType(tran, v);
        checkEquals("pose321rot90", expResult, result);
    }

    /**
     * Test of toPmRotationMatrix method, of class CRCLToPosemath.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testToPmRotationMatrix() throws Exception {
        System.out.println("toPmRotationMatrix");
        PoseType p = this.pose123;
        PmRotationMatrix expResult = new PmRotationMatrix(
                1.0, 0.0, 0.0,
                0.0, 1.0, 0.0,
                0.0, 0.0, 1.0
        );
        PmRotationMatrix result = CRCLPosemath.toPmRotationMatrix(p);
        checkEquals("RotMat", expResult, result);

        Random r = new Random(RANDOM_SEED);
        for (int i = 0; i < 300; i++) {
            PmRpy rpy1 = new PmRpy(r.nextDouble() * 2.0 * Math.PI - Math.PI,
                    r.nextDouble() * Math.PI - Math.PI / 2.0,
                    r.nextDouble() * 2.0 * Math.PI - Math.PI);
            expResult = Posemath.toMat(rpy1);
            p = CRCLPosemath.toPoseType(cart123, Posemath.toRot(rpy1));
            result = CRCLPosemath.toPmRotationMatrix(p);
            checkEquals("randRotMat", expResult, result);
        }
    }

    /**
     * Test of toPmRotationVector method, of class CRCLToPosemath.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testToPmRotationVector() throws Exception {
        System.out.println("toPmRotationVector");
        PoseType p = this.pose321rot90;
        PmRotationVector expResult = new PmRotationVector(Math.PI / 2, 0.0, 0.0, 1.0);
        PmRotationVector result = CRCLPosemath.toPmRotationVector(p);

        assertEquals("s", expResult.s, result.s, ASSERT_TOLERANCE_DELTA);
        assertEquals("x", expResult.x, result.x, ASSERT_TOLERANCE_DELTA);
        assertEquals("y", expResult.y, result.y, ASSERT_TOLERANCE_DELTA);
        assertEquals("z", expResult.z, result.z, ASSERT_TOLERANCE_DELTA);
    }

    /**
     * Test of maxDiffDoubleArray method, of class CRCLToPosemath.
     */
    @Test
    public void testMaxDiffDoubleArray() {
        System.out.println("maxDiffDoubleArray");
        double[] da = new double[]{0, 0, 0.2, 0};
        double[] da2 = new double[]{0, 0, -0.2, 0};
        double expResult = 0.4;
        double result = CRCLPosemath.maxDiffDoubleArray(da, da2);
        assertEquals(expResult, result, ASSERT_TOLERANCE_DELTA);
        try {
            CRCLPosemath.maxDiffDoubleArray(da, new double[1]);
            fail("Expected IllegalArgumentException since array lengths don't match");
        } catch (IllegalArgumentException expectedException) {
        }
    }

    /**
     * Test of diffPosesRot method, of class CRCLToPosemath.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testDiffPosesRot() throws Exception {
        System.out.println("diffPosesRot");
        PoseType pose1 = this.pose321;
        PoseType pose2 = this.pose321rot90;
        double expResult = Math.PI / 2;
        double result = CRCLPosemath.diffPosesRot(pose1, pose2);
        assertEquals(expResult, result, ASSERT_TOLERANCE_DELTA);
    }

    /**
     * Test of toPointType method, of class CRCLToPosemath.
     */
    @Test
    public void testToPointType() {
        System.out.println("toPointType");
        PmCartesian c = this.cart123;
        PointType expResult = this.pt123;
        PointType result = CRCLPosemath.toPointType(c);
        checkEquals("123", expResult, result);
    }

//    /**
//     * Test of toPoseType method, of class CRCLToPosemath.
//     */
//    @Test
//    public void testToPoseType() throws Exception {
//        System.out.println("toPoseType");
//        PmCartesian tran = this.cart123;
//        PmRotationVector v = new PmRotationVector(Math.toRadians(30), 0.0, 0.0, 1.0);
//        PoseType expResult = new PoseType();
//        VectorType xvector = new VectorType();
//        xvector.setI(BigDecimal.valueOf(Math.cos(Math.toRadians(30.0))));
//        xvector.setJ(BigDecimal.valueOf(Math.sin(Math.toRadians(30.0))));
//        xvector.setK(BigDecimal.ZERO);
//        VectorType zvector = new VectorType();
//        zvector.setI(BigDecimal.ZERO);
//        zvector.setJ(BigDecimal.ZERO);
//        zvector.setK(BigDecimal.ONE);
//        PointType pt = new PointType();
//        pt.setX(BigDecimal.ONE);
//        pt.setY(BIG_DECIMAL_2);
//        pt.setZ(BIG_DECIMAL_3);
//        expResult.setPoint(pt);
//        expResult.setXAxis(xvector);
//        expResult.setZAxis(zvector);
//        PoseType result = CRCLPosemath.toPoseType(tran, v);
//        checkEquals("123", expResult, result);
//    }
    /**
     * Test of toPmRpy method, of class CRCLToPosemath.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testToPmRpy() throws Exception {
        System.out.println("toPmRpy");
        PoseType p = new PoseType() {
        };
        PmRpy expResult = new PmRpy(Math.toRadians(20.0), Math.toRadians(25.0), Math.toRadians(30.0));
        PmRotationMatrix mat = Posemath.toMat(expResult);
        VectorType xvector = new VectorType();
        xvector.setI(BigDecimal.valueOf(mat.x.x));
        xvector.setJ(BigDecimal.valueOf(mat.x.y));
        xvector.setK(BigDecimal.valueOf(mat.x.z));
        p.setXAxis(xvector);
        VectorType zvector = new VectorType();
        zvector.setI(BigDecimal.valueOf(mat.z.x));
        zvector.setJ(BigDecimal.valueOf(mat.z.y));
        zvector.setK(BigDecimal.valueOf(mat.z.z));
        p.setZAxis(zvector);
        PmRpy result = CRCLPosemath.toPmRpy(p);
        assertEquals(expResult.r, result.r, 1e-4);
        assertEquals(expResult.p, result.p, 1e-4);
        assertEquals(expResult.y, result.y, 1e-4);
    }

    /**
     * Test of poseToString method, of class CRCLToPosemath.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testPoseToString() throws Exception {
        System.out.println("poseToString");
        PoseType pose = pose321rot90;
        String expResult = "{\n"
                + "{0.00,1.00,0.00,3.00},\n"
                + "{-1.00,0.00,0.00,2.00},\n"
                + "{0.00,0.00,1.00,1.00},\n"
                + "{0.00,0.00,0.00,1.00}\n"
                + "}";
        String result = CRCLPosemath.poseToString(pose);
        assertEquals(expResult, result);
    }

    /**
     * Test of toPoseType method, of class CRCLToPosemath.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testToPoseType_3args() throws Exception {
        System.out.println("toPoseType");
        PmCartesian tran = this.cart321;
        PmRotationVector v = new PmRotationVector(Math.PI / 2, 0.0, 0.0, 1.0);
        PoseType pose_in = new PoseType();
        PoseType expResult = pose_in;
        PoseType result = CRCLPosemath.toPoseType(tran, v, pose_in);
        // result refences the same memory as expResult
        assertTrue(expResult == result);

        result = CRCLPosemath.toPoseType(tran, v, null);
        // result no longer refences the same memory but all members are approximately equal
        assertTrue(expResult != result);
        checkEquals("123rot90", expResult, result);

    }

    /**
     * Test of add method, of class CRCLPosemath.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        PointType p1 = this.pt123;
        PointType p2 = this.pt321;
        PointType expResult = new PointType();
        expResult.setX(BIG_DECIMAL_4);
        expResult.setY(BIG_DECIMAL_4);
        expResult.setZ(BIG_DECIMAL_4);
        PointType result = CRCLPosemath.add(p1, p2);
        checkEquals("444", expResult, result);
    }

    /**
     * Test of subtract method, of class CRCLPosemath.
     */
    @Test
    public void testSubtract() {
        System.out.println("subtract");
        PointType p1 = this.pt123;
        PointType p2 = this.pt321;
        PointType expResult = new PointType();
        expResult.setX(BigDecimal.valueOf(-2.0));
        expResult.setY(BigDecimal.valueOf(0.0));
        expResult.setZ(BigDecimal.valueOf(+2.0));
        PointType result = CRCLPosemath.subtract(p1, p2);
        checkEquals("-2,0,+2", expResult, result);
    }

    /**
     * Test of multiply method, of class CRCLPosemath.
     */
    @Test
    public void testMultiply_BigDecimal_VectorType() {
        System.out.println("multiply");
        BigDecimal dist = BigDecimal.valueOf(2.5);
        VectorType v = this.xvec;
        PointType expResult = new PointType();
        expResult.setX(BigDecimal.valueOf(2.5));
        expResult.setY(BigDecimal.ZERO);
        expResult.setZ(BigDecimal.ZERO);
        PointType result = CRCLPosemath.multiply(dist, v);
        checkEquals("2.5,0,0", expResult, result);
    }

    /**
     * Test of multiply method, of class CRCLPosemath.
     */
    @Test
    public void testMultiply_double_VectorType() {
        System.out.println("multiply");
        double dist = 2.5;
        VectorType v = this.zvec;
        PointType expResult = new PointType();
        expResult.setX(BigDecimal.ZERO);
        expResult.setY(BigDecimal.ZERO);
        expResult.setZ(BigDecimal.valueOf(2.5));
        PointType result = CRCLPosemath.multiply(dist, v);
        checkEquals("0,0,2.5", expResult, result);
    }

    /**
     * Test of multiply method, of class CRCLPosemath.
     */
    @Test
    public void testMultiply_BigDecimal_PointType() {
        System.out.println("multiply");
        BigDecimal dist = BigDecimal.valueOf(3.5);
        PointType p = this.pt123;
        PointType expResult = new PointType();
        expResult.setX(BigDecimal.valueOf(1.0 * 3.5));
        expResult.setY(BigDecimal.valueOf(2.0 * 3.5));
        expResult.setZ(BigDecimal.valueOf(3.0 * 3.5));
        PointType result = CRCLPosemath.multiply(dist, p);
        checkEquals("3.5*(1,2,3)", expResult, result);
    }

    /**
     * Test of multiply method, of class CRCLPosemath.
     */
    @Test
    public void testMultiply_double_PointType() {
        System.out.println("multiply");
        double dist = 0.3;
        PointType p = this.pt321;
        PointType expResult = new PointType();
        expResult.setX(BigDecimal.valueOf(3.0 * 0.3));
        expResult.setY(BigDecimal.valueOf(2.0 * 0.3));
        expResult.setZ(BigDecimal.valueOf(1.0 * 0.3));
        PointType result = CRCLPosemath.multiply(dist, p);
        checkEquals("0.3*(3,2,1)", expResult, result);
    }

    /**
     * Test of dot method, of class CRCLPosemath.
     */
    @Test
    public void testDot_VectorType_VectorType() {
        System.out.println("dot");
        VectorType v1 = this.xvec;
        VectorType v2 = this.yvec;
        BigDecimal expResult = BigDecimal.ZERO;
        BigDecimal result = CRCLPosemath.dot(v1, v2);
        checkEquals("x dot y", expResult, result);
        v1 = this.zvec;
        v2 = this.zvec;
        expResult = BigDecimal.ONE;
        result = CRCLPosemath.dot(v1, v2);
        checkEquals("z dot z", expResult, result);
    }

    /**
     * Test of dot method, of class CRCLPosemath.
     */
    @Test
    public void testDot_VectorType_PointType() {
        System.out.println("dot");
        VectorType v1 = this.zvec;
        PointType p2 = this.pt123;
        BigDecimal expResult = BIG_DECIMAL_3;
        BigDecimal result = CRCLPosemath.dot(v1, p2);
        checkEquals("z dot (1,2,3)", expResult, result);
    }

    /**
     * Test of cross method, of class CRCLPosemath.
     */
    @Test
    public void testCross() {
        System.out.println("cross");
        VectorType v1 = this.xvec;
        VectorType v2 = this.yvec;
        VectorType expResult = this.zvec;
        VectorType result = CRCLPosemath.cross(v1, v2);
        checkEquals("x cross y", expResult, result);
        v1 = this.zvec;
        v2 = this.xvec;
        expResult = this.yvec;
        result = CRCLPosemath.cross(v1, v2);
        checkEquals("z cross x", expResult, result);

        Random r = new Random(RANDOM_SEED);
        for (int i = 0; i < 100; i++) {
            try {
                PmCartesian cart1 = new PmCartesian(r.nextDouble() * 2.0 - 1.0,
                        r.nextDouble() * 2.0 - 1.0,
                        r.nextDouble() * 2.0 - 1.0);
                cart1 = cart1.multiply(1.0 / Posemath.mag(cart1));
                PmCartesian cart2 = new PmCartesian(r.nextDouble() * 2.0 - 1.0,
                        r.nextDouble() * 2.0 - 1.0,
                        r.nextDouble() * 2.0 - 1.0);
                cart2 = cart2.multiply(1.0 / Posemath.mag(cart2));
                v1 = new VectorType();
                v1.setI(BigDecimal.valueOf(cart1.x));
                v1.setJ(BigDecimal.valueOf(cart1.y));
                v1.setK(BigDecimal.valueOf(cart1.z));
                v2 = new VectorType();
                v2.setI(BigDecimal.valueOf(cart2.x));
                v2.setJ(BigDecimal.valueOf(cart2.y));
                v2.setK(BigDecimal.valueOf(cart2.z));
                PmCartesian cross = new PmCartesian();
                Posemath.pmCartCartCross(cart1, cart2, cross);
                expResult = new VectorType();
                expResult.setI(BigDecimal.valueOf(cross.x));
                expResult.setJ(BigDecimal.valueOf(cross.y));
                expResult.setK(BigDecimal.valueOf(cross.z));

                result = CRCLPosemath.cross(v1, v2);
                checkEquals("randomCross (posemath)", expResult, result);

//                Vector3D v3Da = new Vector3D(cart1.x,cart1.y,cart1.z);
//                Vector3D v3Db = new Vector3D(cart2.x,cart2.y,cart2.z);
//                Vector3D cross2 = v3Da.crossProduct(v3Db);
//                expResult = CRCLPosemath.toCRCLVector(cross2);
//                checkEquals("randomCross2 (commons-math)", expResult, result);
            } catch (PmException ex) {
                Logger.getLogger(CRCLPosemathTest.class.getName()).log(Level.SEVERE, null, ex);
                fail("Exception not expected.");
            }
        }
    }

    private static final int RANDOM_SEED = 9321;

    /**
     * Test of multiply method, of class CRCLPosemath.
     */
    @Test
    public void testMultiply_PoseType_PoseType() {
        System.out.println("multiply");
        PoseType p1 = this.pose123;
        PoseType p2 = this.pose321;
        PoseType expResult = new PoseType();
        PointType pt444 = new PointType();
        pt444.setX(BIG_DECIMAL_4);
        pt444.setY(BIG_DECIMAL_4);
        pt444.setZ(BIG_DECIMAL_4);
        expResult.setPoint(pt444);
        expResult.setXAxis(xvec);
        expResult.setZAxis(zvec);
        PoseType result = CRCLPosemath.multiply(p1, p2);
        checkEquals("444", expResult, result);
        p1 = this.pose321rot90;
        p2 = this.pose123;
        expResult = new PoseType();
        PointType pt2 = new PointType();
        pt2.setX(BigDecimal.valueOf(3.0 - 2.0));
        pt2.setY(BigDecimal.valueOf(2.0 + 1.0));
        pt2.setZ(BigDecimal.valueOf(1.0 + 3.0));
        expResult.setPoint(pt2);
        expResult.setXAxis(yvec);
        expResult.setZAxis(zvec);
        result = CRCLPosemath.multiply(p1, p2);
        try {
            PmCartesian chkCart
                    = CRCLPosemath.toPmRotationMatrix(p1).multiply(CRCLPosemath.toPmCartesian(p2.getPoint()));
            System.out.println("chkCart = " + chkCart);
        } catch (PmException ex) {
            Logger.getLogger(CRCLPosemathTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        checkEquals("234", expResult, result);

        testWithRandomMults();
    }

    public void testWithRandomMults() {
        PoseType p1;
        PoseType p2;
        PoseType result;
        PoseType expResult;
        Random r = new Random(RANDOM_SEED);
        for (int i = 0; i < 100; i++) {
            try {
                PmRpy rpy1 = new PmRpy(r.nextDouble() * 2.0 * Math.PI - Math.PI,
                        r.nextDouble() * Math.PI - Math.PI / 2.0,
                        r.nextDouble() * 2.0 * Math.PI - Math.PI
                );
                PmRpy rpy2 = new PmRpy(r.nextDouble() * 2.0 * Math.PI - Math.PI,
                        r.nextDouble() * Math.PI - Math.PI / 2.0,
                        r.nextDouble() * 2.0 * Math.PI - Math.PI
                );
                PmCartesian cart1 = new PmCartesian(r.nextDouble() * 2.0 - 1.0,
                        r.nextDouble() * 2.0 - 1.0,
                        r.nextDouble() * 2.0 - 1.0);

                PmCartesian cart2 = new PmCartesian(r.nextDouble() * 2.0 - 1.0,
                        r.nextDouble() * 2.0 - 1.0,
                        r.nextDouble() * 2.0 - 1.0);

                PmPose pose1 = new PmPose(cart1, Posemath.toQuat(rpy1));
                PmPose pose2 = new PmPose(cart2, Posemath.toQuat(rpy2));
                PmPose poseProduct = new PmPose();
                Posemath.pmPosePoseMult(pose1, pose2, poseProduct);
                PmPose revPoseProduct = new PmPose();
                Posemath.pmPosePoseMult(pose2, pose1, revPoseProduct);
                p1 = CRCLPosemath.toPoseType(cart1, Posemath.toRot(rpy1));
                p2 = CRCLPosemath.toPoseType(cart2, Posemath.toRot(rpy2));
                result = CRCLPosemath.multiply(p1, p2);
                expResult = CRCLPosemath.toPoseType(poseProduct.tran, Posemath.toRot(poseProduct.rot));
                checkEquals("poseProduct", expResult, result);
            } catch (PmException ex) {
                Logger.getLogger(CRCLPosemathTest.class.getName()).log(Level.SEVERE, null, ex);
                fail("PmException thrown.");
            }

        }
    }

    /**
     * Test of shift method, of class CRCLPosemath.
     */
    @Test
    public void testShift() {
        System.out.println("shift");
        PoseType poseIn = this.pose321rot90;
        PointType pt = this.pt123;
        PoseType expResult = new PoseType();
        PointType expResultPt = CRCLPosemath.add(pose321rot90.getPoint(), pt);
        expResult.setPoint(expResultPt);
        expResult.setXAxis(poseIn.getXAxis());
        expResult.setZAxis(poseIn.getZAxis());
        PoseType result = CRCLPosemath.shift(poseIn, pt);
        checkEquals("444rot90", expResult, result);
    }

    /**
     * Test of pointXAxisZAxisToPose method, of class CRCLPosemath.
     */
    @Test
    public void testPointXAxisZAxisToPose() {
        System.out.println("pointXAxisZAxisToPose");
        PointType pt = this.pt123;
        VectorType x = this.xvec;
        VectorType z = this.zvec;
        PoseType expResult = this.pose123;
        PoseType result = CRCLPosemath.pointXAxisZAxisToPose(pt, x, z);
        checkEquals("123", expResult, result);
    }

    /**
     * Test of toPoseType method, of class CRCLPosemath.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testToPoseType_PmCartesian_PmRotationVector() throws Exception {
        System.out.println("toPoseType");
        PmCartesian tran = this.cart321;
        PmRotationVector v = new PmRotationVector(Math.PI / 2.0, 0.0, 0.0, 1.0);
        PoseType expResult = this.pose321rot90;
        PoseType result = CRCLPosemath.toPoseType(tran, v);
        checkEquals("pose321rot90", expResult, result);
    }

    /**
     * Test of toPose method, of class CRCLPosemath.
     */
    @Test
    public void testToPose() {
        System.out.println("toPose");
        double[][] mat = new double[][]{
            {+0.0, -1.0, +0.0, +3.0},
            {+1.0, +0.0, +0.0, +2.0},
            {+0.0, +0.0, +1.0, +1.0},
            {+0.0, +0.0, +0.0, +1.0}
        };
        PoseType expResult = this.pose321rot90;
        PoseType result = CRCLPosemath.toPose(mat);
        checkEquals("fromMat", expResult, result);
    }

    /**
     * Test of invert method, of class CRCLPosemath.
     */
    @Test
    public void testInvert() {
        System.out.println("invert");
        PoseType p = this.pose123;
        PoseType expResult = new PoseType();
        PointType pt = new PointType();
        pt.setX(BIG_DECIMAL_1.negate());
        pt.setY(BIG_DECIMAL_2.negate());
        pt.setZ(BIG_DECIMAL_3.negate());
        expResult.setPoint(pt);
        expResult.setXAxis(xvec);
        expResult.setZAxis(zvec);
        PoseType result = CRCLPosemath.invert(p);
        checkEquals("invert", expResult, result);

        PoseType identity = CRCLPosemath.identityPose();
        PoseType identityCheck = CRCLPosemath.multiply(p, result);
        checkEquals("identity", identityCheck, identity);
        Random r = new Random(RANDOM_SEED);
        for (int i = 0; i < 100; i++) {
            try {
                PmRpy rpy1 = new PmRpy(r.nextDouble() * 2.0 * Math.PI - Math.PI,
                        r.nextDouble() * Math.PI - Math.PI / 2.0,
                        r.nextDouble() * 2.0 * Math.PI - Math.PI
                );
                PmCartesian cart1 = new PmCartesian(r.nextDouble() * 2.0 - 1.0,
                        r.nextDouble() * 2.0 - 1.0,
                        r.nextDouble() * 2.0 - 1.0);

                p = CRCLPosemath.toPoseType(cart1, Posemath.toRot(rpy1));
                result = CRCLPosemath.invert(p);
                identityCheck = CRCLPosemath.multiply(p, result);
                checkEquals("identity", identityCheck, identity);
            } catch (PmException ex) {
                Logger.getLogger(CRCLPosemathTest.class.getName()).log(Level.SEVERE, null, ex);
                fail("PmException thrown.");
            }
        }
    }

    /**
     * Test of identityPose method, of class CRCLPosemath.
     */
    @Test
    public void testIdentityPose() {
        System.out.println("identityPose");
        PoseType result = CRCLPosemath.identityPose();
        checkEquals("xvec", xvec, result.getXAxis());
        checkEquals("zvec", zvec, result.getZAxis());
        PointType zeroPoint = new PointType();
        zeroPoint.setX(BigDecimal.ZERO);
        zeroPoint.setY(BigDecimal.ZERO);
        zeroPoint.setZ(BigDecimal.ZERO);
        checkEquals("point", zeroPoint, result.getPoint());
    }

    /**
     * Test of toPmPose method, of class CRCLPosemath.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testToPmPose() throws Exception {
        System.out.println("toPmPose");
        PoseType p = pose321rot90;
        PmPose expResult = new PmPose(cart321,
                new PmQuaternion(new PmRotationVector(Math.PI / 2, 0, 0, 1)));
        PmPose result = CRCLPosemath.toPmPose(p);
        checkEquals("tran", result.tran, expResult.tran);
        checkEquals("rot", result.rot, expResult.rot);

    }

    /**
     * Test of toHomMat method, of class CRCLPosemath.
     */
    @Test
    public void testToHomMat() {
        System.out.println("toHomMat");
        PoseType poseIn = pose321rot90;
        double[][] expResult = new double[][]{
            {+0, 1, 0, 3},
            {-1, 0, 0, 2},
            {+0, 0, 1, 1},
            {+0, 0, 0, 1}
        };
        double[][] result = CRCLPosemath.toHomMat(poseIn);
        assertArrayEquals("mat[0]", expResult[0], result[0], ASSERT_TOLERANCE_DELTA);
        assertArrayEquals("mat[1]", expResult[1], result[1], ASSERT_TOLERANCE_DELTA);
        assertArrayEquals("mat[2]", expResult[2], result[2], ASSERT_TOLERANCE_DELTA);
        assertArrayEquals("mat[3]", expResult[3], result[3], ASSERT_TOLERANCE_DELTA);
    }
    private static final Logger LOG = Logger.getLogger(CRCLPosemathTest.class.getName());

    /**
     * Test of getPose method, of class CRCLPosemath.
     */
    @Test
    public void testGetPose() {
        System.out.println("getPose");
        CRCLStatusType stat = new CRCLStatusType();

        PoseType expResult = pose123;
        CRCLPosemath.setPose(stat, expResult);
        PoseType result = CRCLPosemath.getPose(stat);
        assertEquals(expResult, result);
    }

    /**
     * Test of compute2DTransform method, of class CRCLPosemath.
     */
    @Test
    public void testCompute2DTransform() throws Exception {
        System.out.println("compute2DTransform");
//        PmCartesian p10 = new PmCartesian(547.076, -64.96, -135);
//        PmCartesian p19 = new PmCartesian(303.885, 113.24, -136.362);
//        PmCartesian l1 = new PmCartesian(308.567, 59.495, -144.128);
//        PmCartesian l2 = new PmCartesian(548.967, -128.502, -137.681);
//        PointType origPoint1 = CRCLPosemath.toPointType(p10);
//        System.out.println("origPoint1 = " + CRCLPosemath.toPmCartesian(origPoint1));
//        PointType origPoint2 = CRCLPosemath.toPointType(p19);
//        System.out.println("origPoint2 = " + CRCLPosemath.toPmCartesian(origPoint2));
//        PointType newPoint1 = CRCLPosemath.toPointType(l1);
//        System.out.println("newPoint1 = " + CRCLPosemath.toPmCartesian(newPoint1));
//        PointType newPoint2 = CRCLPosemath.toPointType(l2);
//        System.out.println("newPoint2 = " + CRCLPosemath.toPmCartesian(newPoint2));
//        
//        PoseType transform = CRCLPosemath.compute2DTransform(origPoint1, origPoint2, newPoint1, newPoint2);
//        System.out.println("transform = " + CRCLPosemath.poseToString(transform));
//        System.out.println("transform = " + CRCLPosemath.toPmPose(transform));
//        PointType newPoint1Transformed = CRCLPosemath.multiply(transform, origPoint1);
//        System.out.println("newPoint1Transformed = " + CRCLPosemath.toPmCartesian(newPoint1Transformed));
//        PointType point1Error = CRCLPosemath.subtract(newPoint1, newPoint1Transformed);
//        System.out.println("point1Error = " + CRCLPosemath.toPmCartesian(point1Error));
//        PointType newPoint2Transformed = CRCLPosemath.multiply(transform, origPoint2);
//        System.out.println("newPoint2Transformed = " + CRCLPosemath.toPmCartesian(newPoint2Transformed));
//        PointType point2Error = CRCLPosemath.subtract(newPoint2, newPoint2Transformed);
//        System.out.println("point2Error = " + CRCLPosemath.toPmCartesian(point2Error));
//        fail("justkiding");
        PointType a1 = CRCLPosemath.toPointType(new PmCartesian(1.0, 2.0, 0.0));
        PointType a2 = CRCLPosemath.toPointType(new PmCartesian(2.0, 2.0, 0.0));
        PointType b1 = CRCLPosemath.toPointType(new PmCartesian(2.0, 3.0, 0.0));
        PointType b2 = CRCLPosemath.toPointType(new PmCartesian(3.0, 3.0, 0.0));
        PoseType expResult = CRCLPosemath.toPose(new PmPose(new PmCartesian(1.0, 1.0, 0.0),
                new PmRpy(0, 0, 0.0)));
        PoseType result = CRCLPosemath.compute2DTransform(a1, a2, b1, b2);
        checkEquals("pose", expResult, result);
        Random rand = new Random(2344);
        for (int i = 0; i < 100; i++) {

            a1 = new PointType();
            a1.setX(BigDecimal.valueOf(rand.nextDouble() * 10.0 - 5.0));
            a1.setY(BigDecimal.valueOf(rand.nextDouble() * 10.0 - 5.0));
            a1.setZ(BigDecimal.valueOf(rand.nextDouble() * 10.0 - 5.0));

            a2 = new PointType();
            a2.setX(BigDecimal.valueOf(rand.nextDouble() * 10.0 - 5.0));
            a2.setY(BigDecimal.valueOf(rand.nextDouble() * 10.0 - 5.0));
            a2.setZ(BigDecimal.valueOf(rand.nextDouble() * 10.0 - 5.0));

            b1 = new PointType();
            b1.setX(BigDecimal.valueOf(rand.nextDouble() * 10.0 - 5.0));
            b1.setY(BigDecimal.valueOf(rand.nextDouble() * 10.0 - 5.0));
            b1.setZ(BigDecimal.valueOf(rand.nextDouble() * 10.0 - 5.0));

            double dist = Math.sqrt((a1.getX().doubleValue() - a2.getX().doubleValue()) * (a1.getX().doubleValue() - a2.getX().doubleValue())
                    + (a1.getY().doubleValue() - a2.getY().doubleValue()) * (a1.getY().doubleValue() - a2.getY().doubleValue()));
            double angle = rand.nextDouble() * 2 * Math.PI;
            b2 = new PointType();
            b2.setX(BigDecimal.valueOf(b1.getX().doubleValue() + Math.cos(angle) * dist));
            b2.setY(BigDecimal.valueOf(b1.getY().doubleValue() + Math.sin(angle) * dist));
            b2.setZ(BigDecimal.valueOf(b1.getZ().doubleValue() + a2.getZ().doubleValue() - a1.getZ().doubleValue()));
            PoseType pose = CRCLPosemath.compute2DTransform(a1, a2, b1, b2);
//            System.out.println("pose = " + CRCLPosemath.poseToString(pose));
            PmPose pmPose = CRCLPosemath.toPmPose(pose);
//            System.out.println("pmPose = " + pmPose);
            PmCartesian cartOut = new PmCartesian();
            Posemath.pmPoseCartMult(pmPose, CRCLPosemath.toPmCartesian(a1), cartOut);
//            System.out.println("cartOut = " + cartOut);
            PointType b1AltRecompute = CRCLPosemath.toPointType(cartOut);
//            System.out.println("b1AltRecompute = " + CRCLPosemath.toPmCartesian(b1AltRecompute));
            PointType b1Recompute = CRCLPosemath.multiply(pose, a1);
//            System.out.println("b1Recompute = " + CRCLPosemath.toPmCartesian(b1Recompute));
            
            
            PmCartesian pmA1 = CRCLPosemath.toPmCartesian(a1);
            PmCartesian pmA2 = CRCLPosemath.toPmCartesian(a2);
            PmCartesian pmB1 = CRCLPosemath.toPmCartesian(b1);
            PmCartesian pmB2 = CRCLPosemath.toPmCartesian(b2);
//            System.out.println("pmA1 = " + pmA1);
//            System.out.println("pmA2 = " + pmA2);
//            System.out.println("pmB1 = " + pmB1);
//            System.out.println("pmB2 = " + pmB2);
            PmPose pmPoseCheck = CRCLPosemath.compute2DPmTransform(pmA1, pmA2, pmB1, pmB2);
//            System.out.println("pmPoseCheck = " + pmPoseCheck);
            PmCartesian b1RecomputeCheck2 = new PmCartesian();
            Posemath.pmPoseCartMult(pmPoseCheck, pmA1, b1RecomputeCheck2);
//            System.out.println("b1RecomputeCheck2 = " + b1RecomputeCheck2);
            
//            System.out.println("b1 = " + CRCLPosemath.toPmCartesian(b1));
            checkEquals("b1(" + i + ")", b1, b1Recompute);
            PointType b2Recompute = CRCLPosemath.multiply(pose, a2);
            checkEquals("b2(" + i + ")", b2, b2Recompute);
        }
    }

    /**
     * Test of compute2DPmTransform method, of class CRCLPosemath.
     */
    @Test
    public void testCompute2DPmTransform() throws Exception {
        System.out.println("compute2DPmTransform");

//        PmCartesian p10 = new PmCartesian(547.076, -64.96, -135);
//        System.out.println("p10 = " + p10);
//        PmCartesian p19 = new PmCartesian(303.885, 113.24, -136.362);
//        System.out.println("p19 = " + p19);
//        PmCartesian l1 = new PmCartesian(308.567, 59.495, -144.128);
//        System.out.println("l1 = " + l1);
//        PmCartesian l2 = new PmCartesian(548.967, -128.502, -137.681);
//        System.out.println("l2 = " + l2);
//        double ldist = l2.distance(l1.x, l1.y);
//        System.out.println("ldist = " + ldist);
//        double pdist = p10.distance(p19.x, p19.y);
//        System.out.println("pdist = " + pdist);
//        PmPose trans1 = CRCLPosemath.compute2DPmTransform(p10, p19, l1, l2);
//        System.out.println("trans1 = " + trans1);
//        System.out.println("trans1 = " + CRCLPosemath.poseToString(CRCLPosemath.toPose(trans1)));
//        PmCartesian p10transformed = new PmCartesian();
//        Posemath.pmPoseCartMult(trans1, p10, p10transformed);
//        System.out.println("p10transformed = " + p10transformed);
//        PmCartesian err1 = new PmCartesian();
//        Posemath.pmCartCartSub(l1, p10transformed, err1);
//        System.out.println("err1 = " + err1);
//        PmCartesian p19transformed = new PmCartesian();
//        Posemath.pmPoseCartMult(trans1, p19, p19transformed);
//        System.out.println("p19transformed = " + p19transformed);
//        PmCartesian err2 = new PmCartesian();
//        Posemath.pmCartCartSub(l2, p19transformed, err2);
//        System.out.println("err2 = " + err2);
//        fail("just kidding");
        PmCartesian a1 = new PmCartesian(1.0, 2.0, 0.0);
        PmCartesian a2 = new PmCartesian(1.0, 3.0, 0.0);
        PmCartesian b1 = new PmCartesian(2.0, 2.0, 0.0);
        PmCartesian b2 = new PmCartesian(2.0, 3.0, 0.0);;
        PmPose expResult = new PmPose(new PmCartesian(1.0, 0.0, 0.0),
                new PmRpy(0, 0, 0.0));
        PmPose result = CRCLPosemath.compute2DPmTransform(a1, a2, b1, b2);
        checkEquals("PmTransform", expResult, result);
        a1 = new PmCartesian(1.0, 0.0, 0.0);
        a2 = new PmCartesian(-1.0, 0.0, 0.0);
        b1 = new PmCartesian(0.0, 1.0, 0.0);
        b2 = new PmCartesian(0.0, -1.0, 0.0);;
        expResult = new PmPose(new PmCartesian(0.0, 0.0, 0.0),
                new PmRpy(0, 0, Math.PI / 2.0));
        result = CRCLPosemath.compute2DPmTransform(a1, a2, b1, b2);
        checkEquals("PmTransform", expResult, result);
        Random rand = new Random(2344);
        for (int i = 0; i < 100; i++) {
            a1 = new PmCartesian(rand.nextDouble() * 10.0 - 5.0, rand.nextDouble() * 10.0 - 5.0, rand.nextDouble() * 10.0 - 5.0);
            a2 = new PmCartesian(rand.nextDouble() * 10.0 - 5.0, rand.nextDouble() * 10.0 - 5.0, rand.nextDouble() * 10.0 - 5.0);
            b1 = new PmCartesian(rand.nextDouble() * 10.0 - 5.0, rand.nextDouble() * 10.0 - 5.0, rand.nextDouble() * 10.0 - 5.0);
            double dist = Math.sqrt((a1.x - a2.x) * (a1.x - a2.x) + (a1.y - a2.y) * (a1.y - a2.y));
            double angle = rand.nextDouble() * 2 * Math.PI;
            b2 = new PmCartesian(b1.x + Math.cos(angle) * dist, b1.y + Math.sin(angle) * dist, b1.z + a2.z - a1.z);
            PmPose pose = CRCLPosemath.compute2DPmTransform(a1, a2, b1, b2);
            PmCartesian b1Recompute = new PmCartesian();
            Posemath.pmPoseCartMult(pose, a1, b1Recompute);
            checkEquals("b1(" + i + ")", b1, b1Recompute);
            PmCartesian b2Recompute = new PmCartesian();
            Posemath.pmPoseCartMult(pose, a2, b2Recompute);
            checkEquals("b2(" + i + ")", b2, b2Recompute);
        }
    }

    /**
     * Test of getPoint method, of class CRCLPosemath.
     */
    @Test
    public void testGetPoint() {
        System.out.println("getPoint");
        CRCLStatusType stat = new CRCLStatusType();
        PointType expResult = pt123;
        CRCLPosemath.setPoint(stat, expResult);
        PointType result = CRCLPosemath.getPoint(stat);
        checkEquals("point", expResult, result);
    }

    /**
     * Test of getXAxis method, of class CRCLPosemath.
     */
    @Test
    public void testGetXAxis() {
        System.out.println("getXAxis");
        CRCLStatusType stat = new CRCLStatusType();
        VectorType expResult = xvec;
        CRCLPosemath.setXAxis(stat, expResult);
        VectorType result = CRCLPosemath.getXAxis(stat);
        checkEquals("xaxis", expResult, result);
    }

    /**
     * Test of getZAxis method, of class CRCLPosemath.
     */
    @Test
    public void testGetZAxis() {
        System.out.println("getZAxis");
        CRCLStatusType stat = new CRCLStatusType();
        VectorType expResult = zvec;
        CRCLPosemath.setZAxis(stat, expResult);
        VectorType result = CRCLPosemath.getZAxis(stat);
        checkEquals("zaxis", expResult, result);
    }

    /**
     * Test of setPose method, of class CRCLPosemath.
     */
    @Test
    public void testSetPose() {
        System.out.println("setPose");
        CRCLStatusType stat = new CRCLStatusType();
        PoseType pose = pose123;
        CRCLPosemath.setPose(stat, pose);
        assertTrue(CRCLPosemath.getPose(stat) == pose);
    }

    /**
     * Test of setPoint method, of class CRCLPosemath.
     */
    @Test
    public void testSetPoint() {
        System.out.println("setPoint");
        CRCLStatusType stat = new CRCLStatusType();
        PointType pt = pt123;
        CRCLPosemath.setPoint(stat, pt);
        assertTrue(CRCLPosemath.getPoint(stat) == pt);
    }

    /**
     * Test of setXAxis method, of class CRCLPosemath.
     */
    @Test
    public void testSetXAxis() {
        System.out.println("setXAxis");
        CRCLStatusType stat = new CRCLStatusType();
        VectorType xAxis = xvec;
        CRCLPosemath.setXAxis(stat, xAxis);
        assertTrue(CRCLPosemath.getXAxis(stat) == xAxis);
    }

    /**
     * Test of setZAxis method, of class CRCLPosemath.
     */
    @Test
    public void testSetZAxis() {
        System.out.println("setZAxis");
        CRCLStatusType stat = new CRCLStatusType();
        VectorType zAxis = zvec;
        CRCLPosemath.setZAxis(stat, zAxis);
        assertTrue(CRCLPosemath.getZAxis(stat) == zAxis);
    }

    /**
     * Test of initPose method, of class CRCLPosemath.
     */
    @Test
    public void testInitPose() {
        System.out.println("initPose");
        CRCLStatusType status = new CRCLStatusType();
        CRCLPosemath.initPose(status);
        assertTrue(status.getPoseStatus() != null);
        assertTrue(status.getPoseStatus().getPose() != null);
        assertTrue(status.getPoseStatus().getPose().getPoint() != null);
        assertTrue(status.getPoseStatus().getPose().getXAxis() != null);
        assertTrue(status.getPoseStatus().getPose().getZAxis() != null);
    }

    /**
     * Test of toString method, of class CRCLPosemath.
     */
    @Test
    public void testToString_PointType() {
        System.out.println("toString");
        PointType pt = pt123;
        String expResult = "{x=1,y=2,z=3}";
        String result = CRCLPosemath.toString(pt);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class CRCLPosemath.
     */
    @Test
    public void testToString_VectorType() {
        System.out.println("toString");
        VectorType v = xvec;
        String expResult = "{i=1,j=0,k=0}";
        String result = CRCLPosemath.toString(v);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class CRCLPosemath.
     */
    @Test
    public void testToString_PoseType() {
        System.out.println("toString");
        PoseType pose = pose123;
        String expResult = "{pt={x=1,y=2,z=3},xAxis={i=1,j=0,k=0},zAxis={i=0,j=0,k=1}}";
        String result = CRCLPosemath.toString(pose);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class CRCLPosemath.
     */
    @Test
    public void testToString_PoseToleranceType() {
        System.out.println("toString");
        PoseToleranceType posetol = new PoseToleranceType();
        posetol.setXPointTolerance(BIG_DECIMAL_1);
        String expResult = "{XPointTolerance=1,YPointTolerance=null,ZPointTolerance=null,XAxisTolerance=null,ZAxisTolerance=null,}";
        String result = CRCLPosemath.toString(posetol);
        assertEquals(expResult, result);
    }

    /**
     * Test of toPmCartesian method, of class CRCLPosemath.
     */
    @Test
    public void testToPmCartesian() {
        System.out.println("toPmCartesian");
        PointType pt = pt123;
        PmCartesian expResult = new PmCartesian(1.0, 2.0, 3.0);
        PmCartesian result = CRCLPosemath.toPmCartesian(pt);
        checkEquals("cart", expResult, result);
    }

    /**
     * Test of toPmPose method, of class CRCLPosemath.
     */
    @Test
    public void testToPmPose_CRCLStatusType() throws Exception {
        System.out.println("toPmPose");
        CRCLStatusType stat = new CRCLStatusType();
        CRCLPosemath.setPose(stat, pose321rot90);
        PoseType p = pose321rot90;
        PmPose expResult = new PmPose(cart321,
                new PmQuaternion(new PmRotationVector(Math.PI / 2, 0, 0, 1)));

        PmPose result = CRCLPosemath.toPmPose(stat);
        checkEquals("pose", expResult, result);
    }

    /**
     * Test of toPmPose method, of class CRCLPosemath.
     */
    @Test
    public void testToPmPose_PoseType() throws Exception {
        System.out.println("toPmPose");
        PoseType p = pose321rot90;
        PmPose expResult = new PmPose(cart321,
                new PmQuaternion(new PmRotationVector(Math.PI / 2, 0, 0, 1)));;
        PmPose result = CRCLPosemath.toPmPose(p);
        checkEquals("pose", expResult, result);
    }

    /**
     * Test of multiply method, of class CRCLPosemath.
     */
    @Test
    public void testMultiply_PoseType_PointType() throws Exception {
        System.out.println("multiply");
        PoseType pose = pose321rot90;
        PointType pt = pt123;
        PointType expResult = new PointType();
        expResult.setX(BigDecimal.ONE);
        expResult.setY(BigDecimal.valueOf(3.0));
        expResult.setZ(BigDecimal.valueOf(4.0));
        PointType result = CRCLPosemath.multiply(pose, pt);
        checkEquals("point", expResult, result);
    }

    /**
     * Test of norm method, of class CRCLPosemath.
     */
    @Test
    public void testNorm() {
        System.out.println("norm");
        VectorType v1 = new VectorType();
        v1.setI(BIG_DECIMAL_1);
        v1.setJ(BIG_DECIMAL_1);
        v1.setK(BigDecimal.ZERO);
        double expResult = Math.sqrt(2.0);
        double result = CRCLPosemath.norm(v1);
        assertEquals(expResult, result, ASSERT_TOLERANCE_DELTA);
    }

    /**
     * Test of normalize method, of class CRCLPosemath.
     */
    @Test
    public void testNormalize() throws Exception {
        System.out.println("normalize");
        VectorType v1 = new VectorType();
        v1.setI(BIG_DECIMAL_1);
        v1.setJ(BIG_DECIMAL_1);
        v1.setK(BigDecimal.ZERO);
        VectorType expResult = new VectorType();
        expResult.setI(new BigDecimal(String.format("%.6f", Math.sqrt(2.0) / 2.0)));
        expResult.setJ(new BigDecimal(String.format("%.6f", Math.sqrt(2.0) / 2.0)));
        expResult.setK(BigDecimal.ZERO);
        VectorType result = CRCLPosemath.normalize(v1);
        checkEquals("vec", expResult, result);
    }

    /**
     * Test of toPose method, of class CRCLPosemath.
     */
    @Test
    public void testToPose_PmPose() throws Exception {
        System.out.println("toPose");
        PmPose pose = new PmPose(new PmCartesian(1.0, 2.0, 3.0), new PmRpy());
        PoseType expResult = pose123;
        PoseType result = CRCLPosemath.toPose(pose);
        checkEquals("pose", expResult, result);
    }

    /**
     * Test of toPose method, of class CRCLPosemath.
     */
    @Test
    public void testToPose_doubleArrArr() {
        System.out.println("toPose");
        double[][] mat = new double[][]{
            {1.0, 0.0, 0.0, 1.0},
            {0.0, 1.0, 0.0, 2.0},
            {0.0, 0.0, 1.0, 3.0},
            {0.0, 0.0, 0.0, 1.0},};
        PoseType expResult = pose123;
        PoseType result = CRCLPosemath.toPose(mat);
        checkEquals("pose", expResult, result);
    }

    /**
     * Test of toPoseType method, of class CRCLPosemath.
     */
    @Test
    public void testToPoseType_3args_1() throws Exception {
        System.out.println("toPoseType");
        PmCartesian tran = new PmCartesian(3.0, 2.0, 1.0);
        PmRotationVector v = new PmRotationVector(Math.PI / 2.0, 0.0, 0.0, 1.0);
        PoseType pose_in = null;
        PoseType expResult = pose321rot90;
        PoseType result = CRCLPosemath.toPoseType(tran, v, pose_in);
        checkEquals("pose", expResult, result);
    }

    /**
     * Test of toPoseType method, of class CRCLPosemath.
     */
    @Test
    public void testToPoseType_3args_2() throws Exception {
        System.out.println("toPoseType");
        PmCartesian tran = new PmCartesian(3.0, 2.0, 1.0);
        PmRpy v = new PmRpy(0.0, 0.0, Math.PI / 2.0);
        PoseType pose_in = null;
        PoseType expResult = pose321rot90;
        PoseType result = CRCLPosemath.toPoseType(tran, v, pose_in);
        checkEquals("pose", expResult, result);
    }

    /**
     * Test of toPoseType method, of class CRCLPosemath.
     */
    @Test
    public void testToPoseType_PmCartesian_PmRpy() throws Exception {
        System.out.println("toPoseType");
        PmCartesian tran = new PmCartesian(3.0, 2.0, 1.0);
        PmRpy v = new PmRpy(0.0, 0.0, Math.PI / 2.0);
        PoseType expResult = pose321rot90;
        PoseType result = CRCLPosemath.toPoseType(tran, v);
        checkEquals("pose", expResult, result);
    }

    /**
     * Test of newZeroedPoint method, of class CRCLPosemath.
     */
    @Test
    public void testNewZeroedPoint() {
        System.out.println("newZeroedPoint");
        PointType result = CRCLPosemath.newZeroedPoint();
        checkEquals("x", BigDecimal.ZERO, result.getX());
        checkEquals("y", BigDecimal.ZERO, result.getY());
        checkEquals("z", BigDecimal.ZERO, result.getZ());
    }

    /**
     * Test of copy method, of class CRCLPosemath.
     */
    @Test
    public void testCopy_PointType() {
        System.out.println("copy(PointType)");
        PointType pt = pt123;
        PointType expResult = pt123;
        PointType result = CRCLPosemath.copy(pt);
        checkEquals("Point", result, expResult);
        assertTrue(result != pt);
    }

    /**
     * Test of xyPoint2D method, of class CRCLPosemath.
     */
    @Test
    public void testXyPoint2D_PointType() {
        System.out.println("xyPoint2D");
        PointType pt = pt123;
        Point2D.Double expResult = new Point2D.Double(1.0,2.0);
        Point2D.Double result = CRCLPosemath.xyPoint2D(pt);
        checkEquals("x", result.x, expResult.x);
        checkEquals("y", result.y, expResult.y);
    }

    /**
     * Test of xyPoint2D method, of class CRCLPosemath.
     */
    @Test
    public void testXyPoint2D_PoseType() {
        System.out.println("xyPoint2D");
        PoseType pose = pose123;
        Point2D.Double expResult = new Point2D.Double(1.0,2.0);
        Point2D.Double result = CRCLPosemath.xyPoint2D(pose);
        checkEquals("x", result.x, expResult.x);
        checkEquals("y", result.y, expResult.y);
    }

    /**
     * Test of rzPoint2D method, of class CRCLPosemath.
     */
    @Test
    public void testRzPoint2D_PointType() {
        System.out.println("rzPoint2D");
        PointType pt = pt123;
        Point2D.Double expResult = new Point2D.Double(Math.sqrt(5),3.0);
        Point2D.Double result = CRCLPosemath.rzPoint2D(pt);
        assertEquals(expResult, result);
        checkEquals("x(r)", result.x, expResult.x);
        checkEquals("y(z)", result.y, expResult.y);
    }

    /**
     * Test of rzPoint2D method, of class CRCLPosemath.
     */
    @Test
    public void testRzPoint2D_PoseType() {
        System.out.println("rzPoint2D");
        PoseType pose = pose123;
        Point2D.Double expResult = new Point2D.Double(Math.sqrt(5),3.0);
        Point2D.Double result = CRCLPosemath.rzPoint2D(pose);
        checkEquals("x(r)", result.x, expResult.x);
        checkEquals("y(z)", result.y, expResult.y);
    }

    /**
     * Test of copy method, of class CRCLPosemath.
     */
    @Test
    public void testCopy_VectorType() {
        System.out.println("copy(VectorType)");
        VectorType vec = xvec;
        VectorType expResult = xvec;
        VectorType result = CRCLPosemath.copy(vec);
        checkEquals("vector", result, expResult);
        assertTrue(result != vec);
    }

    /**
     * Test of copy method, of class CRCLPosemath.
     */
    @Test
    public void testCopy_PoseType() {
        System.out.println("copy(PoseType)");
        PoseType pose = pose321rot90;
        PoseType expResult = pose321rot90;
        PoseType result = CRCLPosemath.copy(pose);
        checkEquals("pose", result, expResult);
        assertTrue(result != pose);
        assertTrue(result.getPoint() != pose.getPoint());
        assertTrue(result.getXAxis() != pose.getXAxis());
        assertTrue(result.getZAxis() != pose.getZAxis());
    }

    /**
     * Test of copy method, of class CRCLPosemath.
     */
    @Test
    public void testCopy_CRCLStatusType() {
        System.out.println("copy(CRCLStatusType)");
        CRCLStatusType status = new CRCLStatusType();
        CommandStatusType commandStatus = new CommandStatusType();
        commandStatus.setCommandID(BigInteger.valueOf(233));
        commandStatus.setStatusID(BigInteger.valueOf(2343));
        status.setCommandStatus(commandStatus);
        PoseStatusType poseStatus = new PoseStatusType();
        poseStatus.setPose(pose123);
        status.setPoseStatus(poseStatus);
        JointStatusesType jointStatuses = new JointStatusesType();
        JointStatusType js1 = new JointStatusType();
        js1.setJointNumber(BigInteger.ONE);
        js1.setJointPosition(BIG_DECIMAL_1);
        jointStatuses.getJointStatus().add(js1);
        status.setJointStatuses(jointStatuses);
        CRCLStatusType expResult = status;
        CRCLStatusType result = CRCLPosemath.copy(status);
        
        checkEquals("pose", result.getPoseStatus().getPose(), expResult.getPoseStatus().getPose());
        assertTrue(result != status);
        assertTrue(result.getPoseStatus() != status.getPoseStatus());
        assertTrue(result.getPoseStatus().getPose() != status.getPoseStatus().getPose());
        assertTrue(result.getCommandStatus() != status.getCommandStatus());
        assertEquals(result.getCommandStatus().getCommandID(), expResult.getCommandStatus().getCommandID());
        assertTrue(result.getCommandStatus() != status.getCommandStatus());
    }

    /**
     * Test of copy method, of class CRCLPosemath.
     */
    @Test
    public void testCopy_GripperStatusType() {
        System.out.println("copy(GripperStatusType)");
        ParallelGripperStatusType status = new ParallelGripperStatusType();
        status.setSeparation(BIG_DECIMAL_1);
        GripperStatusType expResult = status;
        GripperStatusType result = CRCLPosemath.copy(status);
        checkEquals("seperation", ((ParallelGripperStatusType) result).getSeparation(), 
                status.getSeparation());
        assertTrue(result != status);
    }

    /**
     * Test of copy method, of class CRCLPosemath.
     */
    @Test
    public void testCopy_PoseStatusType() {
        System.out.println("copy(PoseStatusType)");
        PoseStatusType status = new PoseStatusType();
        status.setPose(pose123);
        PoseStatusType expResult = status;
        PoseStatusType result = CRCLPosemath.copy(status);
        checkEquals("pose", result.getPose(), expResult.getPose());
        assertTrue(result != status);
        assertTrue(result.getPose() != status.getPose());
    }

    /**
     * Test of copy method, of class CRCLPosemath.
     */
    @Test
    public void testCopy_TwistType() {
        System.out.println("copy(TwistType)");
        TwistType twist = new TwistType();
        twist.setAngularVelocity(xvec);
        twist.setLinearVelocity(zvec);
        TwistType expResult = twist;
        TwistType result = CRCLPosemath.copy(twist);
        checkEquals("angularVelocity", result.getAngularVelocity(), expResult.getAngularVelocity());
        checkEquals("linearVelocity", result.getLinearVelocity(), expResult.getLinearVelocity());
        assertTrue(result != twist);
        assertTrue(result.getAngularVelocity() != twist.getAngularVelocity());
        assertTrue(result.getLinearVelocity() != twist.getLinearVelocity());
        
    }

    /**
     * Test of copy method, of class CRCLPosemath.
     */
    @Test
    public void testCopy_WrenchType() {
        System.out.println("copy(WrenchType)");
        WrenchType wrench = new WrenchType();
        wrench.setForce(yvec);
        wrench.setMoment(xvec);
        WrenchType expResult = wrench;
        WrenchType result = CRCLPosemath.copy(wrench);
        checkEquals("force", result.getForce(), expResult.getForce());
        checkEquals("moment", result.getMoment(), expResult.getMoment());
        assertTrue(result != wrench);
        assertTrue(result.getForce() != wrench.getForce());
        assertTrue(result.getMoment() != wrench.getMoment());
    }

    /**
     * Test of copy method, of class CRCLPosemath.
     */
    @Test
    public void testCopy_JointStatusesType() {
        System.out.println("copy(JointStatusesType)");
        JointStatusesType status = new JointStatusesType();
        JointStatusType js1 = new JointStatusType();
        js1.setJointNumber(BigInteger.ONE);
        js1.setJointPosition(BIG_DECIMAL_1);
        status.getJointStatus().add(js1);
        JointStatusesType expResult = status;
        JointStatusesType result = CRCLPosemath.copy(status);
        checkEquals("jointPosition", result.getJointStatus().get(0).getJointPosition(),
                expResult.getJointStatus().get(0).getJointPosition());
        assertTrue(result != status);
        assertTrue(result.getJointStatus() != status.getJointStatus());
        assertTrue(result.getJointStatus().size() == status.getJointStatus().size());
        assertTrue(result.getJointStatus().get(0) != status.getJointStatus().get(0));
        
    }

    /**
     * Test of copy method, of class CRCLPosemath.
     */
    @Test
    public void testCopy_JointStatusType() {
        System.out.println("copy(JointStatusType)");
        JointStatusType status = new JointStatusType();
        status.setJointNumber(BigInteger.ONE);
        status.setJointPosition(BIG_DECIMAL_1);
        status.setJointTorqueOrForce(BIG_DECIMAL_2);
        status.setJointVelocity(BIG_DECIMAL_3);
        JointStatusType expResult = status;
        JointStatusType result = CRCLPosemath.copy(status);
        checkEquals("Position", result.getJointPosition(), expResult.getJointPosition());
        checkEquals("TorqueOrForce", result.getJointTorqueOrForce(), expResult.getJointTorqueOrForce());
        checkEquals("Velocity", result.getJointVelocity(), expResult.getJointVelocity());
        assertTrue(result != status);
    }

    /**
     * Test of copy method, of class CRCLPosemath.
     */
    @Test
    public void testCopy_CommandStatusType() {
        System.out.println("copy(CommandStatusType)");
        CommandStatusType status = new CommandStatusType();
        status.setCommandID(BigInteger.ONE);
        status.setCommandState(CommandStateEnumType.CRCL_DONE);
        status.setStatusID(BigInteger.ONE);
        CommandStatusType expResult = status;
        CommandStatusType result = CRCLPosemath.copy(status);
        assertEquals(expResult.getCommandID(), result.getCommandID());
        assertEquals(expResult.getStatusID(), result.getStatusID());
        assertEquals(expResult.getCommandState(), result.getCommandState());
        assertTrue(result != status);
    }

}
