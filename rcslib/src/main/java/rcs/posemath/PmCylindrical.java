/* 
The NIST RCS (Real-time Control Systems) 
 library is public domain software, however it is preferred
 that the following disclaimers be attached.

Software Copywrite/Warranty Disclaimer

   This software was developed at the National Institute of Standards and
Technology by employees of the Federal Government in the course of their
official duties. Pursuant to title 17 Section 105 of the United States
Code this software is not subject to copyright protection and is in the
public domain. NIST Real-Time Control System software is an experimental
system. NIST assumes no responsibility whatsoever for its use by other
parties, and makes no guarantees, expressed or implied, about its
quality, reliability, or any other characteristic. We would appreciate
acknowledgement if the software is used. This software can be
redistributed and/or modified freely provided that any derivative works
bear some notice that they are derived from it, and any modified
versions bear some notice that they have been modified.



 */

 /*
*       New Java File starts here.
*       This file should be named PmCylindrical.java
 */
// Set Package Name
package rcs.posemath;

// Import all NML, CMS, and RCS classes and interfaces
import rcs.nml.NMLFormatConverter;
import rcs.posemath.PmException;

/*
*       Class definition for PmCylindrical
*       Automatically generated by RCS Java Diagnostics Tool.
*       on Wed Jan 07 10:53:44 EST 1998
 */
public class PmCylindrical {

    public double theta = 0;
    public double r = 0;
    public double z = 0;

    public void update(NMLFormatConverter nml_fc) {
        nml_fc.beginClass("PmCylindrical", null);
        theta = nml_fc.update_with_name("theta", theta);
        r = nml_fc.update_with_name("r", r);
        z = nml_fc.update_with_name("z", z);
        nml_fc.endClass("PmCylindrical", null);
    }

    PmCylindrical() {
    }

    PmCylindrical(double starttheta, double startr, double startz) throws PmException {
        if (startr < -Posemath.V_FUZZ) {
            throw new PmException(Posemath.PM_ERR, "PmCylindrical radius is less than zero!");
        }
        if (startr < 0.0) {
            startr = 0.0;
        }
        if (starttheta > Math.PI) {
            starttheta = starttheta - 2 * Math.PI * Math.ceil(starttheta / (Math.PI * 2));
        }
        if (starttheta < -Math.PI) {
            starttheta = starttheta + 2 * Math.PI * Math.ceil((-starttheta) / (Math.PI * 2));
        }
        theta = starttheta;
        r = startr;
        z = startz;
    }

    public boolean equals(PmCylindrical c) throws PmException {
        if (null == c) {
            return false;
        }
        return Posemath.pmCylCylCompare(c, this);
    }

    public boolean equals(PmCartesian v) throws PmException {
        if (null == v) {
            return false;
        }
        PmCartesian thisv = new PmCartesian();
        Posemath.pmCylCartConvert(this, thisv);
        return Posemath.pmCartCartCompare(v, thisv);
    }

    public boolean equals(PmSpherical s) throws PmException {
        if (null == s) {
            return false;
        }
        PmCartesian thisv = new PmCartesian();
        Posemath.pmCylCartConvert(this, thisv);
        PmCartesian v = new PmCartesian();
        Posemath.pmSphCartConvert(s, v);
        return Posemath.pmCartCartCompare(v, thisv);
    }

    public String toString() {
        return " { theta =" + theta + ", r = " + r + ", z = " + z + " } ";
    }
}
