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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

/*>>>
import org.checkerframework.checker.nullness.qual.*;
import org.checkerframework.checker.regex.qual.*;
*/

public class PerfTestReadUntilEndTag {
    
    
    static final String programAllXml="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
"<!--\n" +
"\n" +
"This is a program file with instances of all CRCL commands in alphabetical\n" +
"order (except that InitCanon is first and EndCanon is last). The file\n" +
"is syntactically valid (so it is valid in XMLSpy) but violates semantic\n" +
"rules given in the in-line documentation of CRCLCommands.xsd, so it\n" +
"should not be executed.\n" +
"\n" +
"All instances of complexType may be given a Name. This file has a Name\n" +
"only in the InitCanon command.\n" +
"\n" +
"-->\n" +
"<CRCLProgram\n" +
"  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
"  xsi:noNamespaceSchemaLocation=\"../xmlSchemas/CRCLProgramInstance.xsd\">\n" +
"  <InitCanon>\n" +
"    <Name>start</Name>\n" +
"    <CommandID>1</CommandID>\n" +
"  </InitCanon>\n" +
"  <MiddleCommand xsi:type=\"ActuateJointsType\">\n" +
"    <CommandID>2</CommandID>\n" +
"    <ActuateJoint>\n" +
"      <JointNumber>1</JointNumber>\n" +
"      <JointPosition>3.8</JointPosition>\n" +
"      <JointDetails xsi:type=\"JointSpeedAccelType\">\n" +
"        <JointSpeed>3.7</JointSpeed>\n" +
"        <JointAccel>11</JointAccel>\n" +
"      </JointDetails>\n" +
"    </ActuateJoint>\n" +
"    <ActuateJoint>\n" +
"      <JointNumber>3</JointNumber>\n" +
"       <JointPosition>3.8</JointPosition>\n" +
"      <JointDetails xsi:type=\"JointForceTorqueType\">\n" +
"        <Setting>7</Setting>\n" +
"        <ChangeRate>13.0</ChangeRate>\n" +
"      </JointDetails>\n" +
"    </ActuateJoint>\n" +
"  </MiddleCommand>\n" +
"  <MiddleCommand xsi:type=\"CloseToolChangerType\">\n" +
"    <CommandID>3</CommandID>\n" +
"  </MiddleCommand>\n" +
"  <MiddleCommand xsi:type=\"ConfigureJointReportsType\">\n" +
"    <CommandID>4</CommandID>\n" +
"    <ResetAll>true</ResetAll>\n" +
"    <ConfigureJointReport>\n" +
"      <JointNumber>1</JointNumber>\n" +
"      <ReportPosition>true</ReportPosition>\n" +
"      <ReportTorqueOrForce>false</ReportTorqueOrForce>\n" +
"      <ReportVelocity>false</ReportVelocity>\n" +
"    </ConfigureJointReport>\n" +
"    <ConfigureJointReport>\n" +
"      <JointNumber>3</JointNumber>\n" +
"      <ReportPosition>true</ReportPosition>\n" +
"      <ReportTorqueOrForce>true</ReportTorqueOrForce>\n" +
"      <ReportVelocity>false</ReportVelocity>\n" +
"    </ConfigureJointReport>\n" +
"  </MiddleCommand>\n" +
"  <MiddleCommand xsi:type=\"DwellType\">\n" +
"    <CommandID>5</CommandID>\n" +
"    <DwellTime>2.5</DwellTime>\n" +
"  </MiddleCommand>\n" +
"  <MiddleCommand xsi:type=\"GetStatusType\">\n" +
"    <CommandID>6</CommandID>\n" +
"  </MiddleCommand>\n" +
"  <MiddleCommand xsi:type=\"MessageType\">\n" +
"    <CommandID>7</CommandID>\n" +
"    <Message>Hi Mom</Message>\n" +
"  </MiddleCommand>\n" +
"\n" +
"  <MiddleCommand xsi:type=\"MoveScrewType\">\n" +
"    <CommandID>8</CommandID>\n" +
"    <AxisPoint>\n" +
"      <X>8.25</X> <Y>1</Y> <Z>0.5</Z>\n" +
"    </AxisPoint>\n" +
"    <AxialDistanceScrew>6.10</AxialDistanceScrew>\n" +
"    <Turn>-3.14</Turn>\n" +
"  </MiddleCommand>\n" +
"  <MiddleCommand xsi:type=\"MoveThroughToType\">\n" +
"    <CommandID>9</CommandID>\n" +
"    <MoveStraight>false</MoveStraight>\n" +
"    <Waypoint>\n" +
"      <Point>\n" +
"        <X>8.25</X> <Y>1</Y> <Z>1</Z>\n" +
"      </Point>\n" +
"      <XAxis>\n" +
"        <I>1</I> <J>0</J> <K>0</K>\n" +
"      </XAxis>\n" +
"      <ZAxis>\n" +
"        <I>0</I> <J>0</J> <K>-1</K>\n" +
"      </ZAxis>\n" +
"    </Waypoint>\n" +
"    <Waypoint xsi:type=\"PoseAndSetType\">\n" +
"      <Point>\n" +
"        <X>8.25</X> <Y>1</Y> <Z>0.5</Z>\n" +
"      </Point>\n" +
"      <XAxis>\n" +
"        <I>1</I> <J>0</J> <K>0</K>\n" +
"      </XAxis>\n" +
"      <ZAxis>\n" +
"        <I>0</I> <J>0</J> <K>-1</K>\n" +
"      </ZAxis>\n" +
"      <Coordinated>true</Coordinated>\n" +
"      <TransSpeed xsi:type=\"TransSpeedRelativeType\">\n" +
"        <Fraction>0.9</Fraction>\n" +
"      </TransSpeed>\n" +
"      <TransAccel xsi:type=\"TransAccelRelativeType\">\n" +
"        <Fraction>0.5</Fraction>\n" +
"      </TransAccel>\n" +
"      <Tolerance>\n" +
"        <XPointTolerance>0.005</XPointTolerance>\n" +
"        <YPointTolerance>0.01</YPointTolerance>\n" +
"        <ZPointTolerance>0.015</ZPointTolerance>\n" +
"        <ZAxisTolerance>1.0</ZAxisTolerance>\n" +
"      </Tolerance>\n" +
"    </Waypoint>\n" +
"    <NumPositions>2</NumPositions>\n" +
"  </MiddleCommand>\n" +
"  <MiddleCommand xsi:type=\"MoveToType\">\n" +
"    <CommandID>12</CommandID>\n" +
"    <MoveStraight>true</MoveStraight>\n" +
"    <EndPosition>\n" +
"      <Point>\n" +
"        <X>8.25</X> <Y>1</Y> <Z>0.5</Z>\n" +
"      </Point>\n" +
"      <XAxis>\n" +
"        <I>1</I> <J>0</J> <K>0</K>\n" +
"      </XAxis>\n" +
"      <ZAxis>\n" +
"        <I>0</I> <J>0</J> <K>-1</K>\n" +
"      </ZAxis>\n" +
"    </EndPosition>\n" +
"  </MiddleCommand>\n" +
"  <MiddleCommand xsi:type=\"OpenToolChangerType\">\n" +
"    <CommandID>13</CommandID>\n" +
"  </MiddleCommand>\n" +
"  <MiddleCommand xsi:type=\"RunProgramType\">\n" +
"    <CommandID>14</CommandID>\n" +
"    <ProgramText>GH$%kkk457 xxx  65</ProgramText>\n" +
"  </MiddleCommand>\n" +
"  <MiddleCommand xsi:type=\"SetAngleUnitsType\">\n" +
"    <CommandID>15</CommandID>\n" +
"    <UnitName>degree</UnitName>\n" +
"  </MiddleCommand>\n" +
"  <MiddleCommand xsi:type=\"SetEndEffectorParametersType\">\n" +
"    <CommandID>16</CommandID>\n" +
"    <ParameterSetting>\n" +
"      <ParameterName>rhabdaciousness</ParameterName>\n" +
"      <ParameterValue>on</ParameterValue>\n" +
"    </ParameterSetting>\n" +
"    <ParameterSetting>\n" +
"      <ParameterName>fluoxity</ParameterName>\n" +
"      <ParameterValue>33</ParameterValue>\n" +
"    </ParameterSetting>\n" +
"  </MiddleCommand>\n" +
"  <MiddleCommand xsi:type=\"SetEndEffectorType\">\n" +
"    <CommandID>17</CommandID>\n" +
"    <Setting>1.0</Setting>\n" +
"  </MiddleCommand>\n" +
"  <MiddleCommand xsi:type=\"SetEndPoseToleranceType\">\n" +
"    <CommandID>18</CommandID>\n" +
"      <Tolerance>\n" +
"        <XPointTolerance>0.005</XPointTolerance>\n" +
"        <YPointTolerance>0.01</YPointTolerance>\n" +
"        <ZPointTolerance>0.015</ZPointTolerance>\n" +
"        <XAxisTolerance>1.0</XAxisTolerance>\n" +
"        <ZAxisTolerance>1.0</ZAxisTolerance>\n" +
"      </Tolerance>\n" +
"  </MiddleCommand>\n" +
"  <MiddleCommand xsi:type=\"SetForceUnitsType\">\n" +
"    <CommandID>19</CommandID>\n" +
"    <UnitName>ounce</UnitName>\n" +
"  </MiddleCommand>\n" +
"  <MiddleCommand xsi:type=\"SetIntermediatePoseToleranceType\">\n" +
"    <CommandID>20</CommandID>\n" +
"      <Tolerance>\n" +
"        <XPointTolerance>0.1</XPointTolerance>\n" +
"        <YPointTolerance>0.05</YPointTolerance>\n" +
"        <ZPointTolerance>0.06</ZPointTolerance>\n" +
"        <XAxisTolerance>1.0</XAxisTolerance>\n" +
"        <ZAxisTolerance>1.0</ZAxisTolerance>\n" +
"      </Tolerance>\n" +
"  </MiddleCommand>\n" +
"  <MiddleCommand xsi:type=\"SetLengthUnitsType\">\n" +
"    <CommandID>21</CommandID>\n" +
"    <UnitName>meter</UnitName>\n" +
"  </MiddleCommand>\n" +
"  <MiddleCommand xsi:type=\"SetMotionCoordinationType\">\n" +
"    <CommandID>22</CommandID>\n" +
"    <Coordinated>true</Coordinated>\n" +
"  </MiddleCommand>\n" +
"  <MiddleCommand xsi:type=\"SetRobotParametersType\">\n" +
"    <CommandID>23</CommandID>\n" +
"    <ParameterSetting>\n" +
"      <ParameterName>empathy</ParameterName>\n" +
"      <ParameterValue>3.2</ParameterValue>\n" +
"    </ParameterSetting>\n" +
"    <ParameterSetting>\n" +
"      <ParameterName>air pressure</ParameterName>\n" +
"      <ParameterValue>701</ParameterValue>\n" +
"    </ParameterSetting>\n" +
"  </MiddleCommand>\n" +
"  <MiddleCommand xsi:type=\"SetRotAccelType\">\n" +
"    <CommandID>24</CommandID>\n" +
"    <RotAccel xsi:type=\"RotAccelAbsoluteType\">\n" +
"      <Setting>4.08</Setting>\n" +
"    </RotAccel>\n" +
"  </MiddleCommand>\n" +
"  <MiddleCommand xsi:type=\"SetRotAccelType\">\n" +
"    <CommandID>25</CommandID>\n" +
"    <RotAccel xsi:type=\"RotAccelRelativeType\">\n" +
"      <Fraction>0.77</Fraction>\n" +
"    </RotAccel>\n" +
"  </MiddleCommand>\n" +
"  <MiddleCommand xsi:type=\"SetRotSpeedType\">\n" +
"    <CommandID>26</CommandID>\n" +
"    <RotSpeed xsi:type=\"RotSpeedAbsoluteType\">\n" +
"      <Setting>6.28</Setting>\n" +
"    </RotSpeed>\n" +
"  </MiddleCommand>\n" +
"  <MiddleCommand xsi:type=\"SetRotSpeedType\">\n" +
"    <CommandID>27</CommandID>\n" +
"    <RotSpeed xsi:type=\"RotSpeedRelativeType\">\n" +
"      <Fraction>0.55</Fraction>\n" +
"    </RotSpeed>\n" +
"  </MiddleCommand>\n" +
"  <MiddleCommand xsi:type=\"SetTorqueUnitsType\">\n" +
"    <CommandID>28</CommandID>\n" +
"    <UnitName>newtonMeter</UnitName>\n" +
"  </MiddleCommand>\n" +
"  <MiddleCommand xsi:type=\"SetTransAccelType\">\n" +
"    <CommandID>29</CommandID>\n" +
"    <TransAccel xsi:type=\"TransAccelAbsoluteType\">\n" +
"      <Setting>9.80</Setting>\n" +
"    </TransAccel>\n" +
"  </MiddleCommand>\n" +
"  <MiddleCommand xsi:type=\"SetTransAccelType\">\n" +
"    <CommandID>30</CommandID>\n" +
"    <TransAccel xsi:type=\"TransAccelRelativeType\">\n" +
"      <Fraction>0.75</Fraction>\n" +
"    </TransAccel>\n" +
"  </MiddleCommand>\n" +
"  <MiddleCommand xsi:type=\"SetTransSpeedType\">\n" +
"    <CommandID>31</CommandID>\n" +
"    <TransSpeed xsi:type=\"TransSpeedAbsoluteType\">\n" +
"      <Setting>5.0</Setting>\n" +
"    </TransSpeed>\n" +
"  </MiddleCommand>\n" +
"  <MiddleCommand xsi:type=\"SetTransSpeedType\">\n" +
"    <CommandID>32</CommandID>\n" +
"    <TransSpeed xsi:type=\"TransSpeedRelativeType\">\n" +
"      <Fraction>0.85</Fraction>\n" +
"    </TransSpeed>\n" +
"  </MiddleCommand>\n" +
"  <MiddleCommand xsi:type=\"StopMotionType\">\n" +
"    <CommandID>33</CommandID>\n" +
"    <StopCondition>Normal</StopCondition>\n" +
"  </MiddleCommand>\n" +
"  <EndCanon>\n" +
"    <CommandID>34</CommandID>\n" +
"  </EndCanon>\n" +
"</CRCLProgram>\n" +
"";
    
    
//    public static void testReadUntilEndTag() throws IOException {
//        s.readUntilEndTag("CRCLProgram", new ByteArrayInputStream(programAllXml.getBytes()));
//    }
//
//    public void testReadUntilEndTag2() throws IOException {
//        s.readUntilEndTag("CRCLProgram", new ByteArrayInputStream(programAllXml.getBytes()));
//    }
    
//    private static String readWith4() {
//        try {
//            return s.readUntilEndTag4("CRCLProgram", new ByteArrayInputStream(programAllXml.getBytes()));
//        } catch (IOException ex) {
//            Logger.getLogger(PerfTestReadUntilEndTag.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
    
//    private static String readWithOld() {
//        try {
//            return s.readUntilEndTagOld("CRCLProgram", new ByteArrayInputStream(programAllXml.getBytes()));
//        } catch (IOException ex) {
//            Logger.getLogger(PerfTestReadUntilEndTag.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
    
    //  /* @ org.checkerframework.checker.nullness.qual.Nullable*/
    
    /*@Nullable*/
    private static String readWithDefault(CRCLSocket s) {
        try {
            return s.readUntilEndTag("CRCLProgram", new ByteArrayInputStream(programAllXml.getBytes()));
        } catch (IOException ex) {
            Logger.getLogger(PerfTestReadUntilEndTag.class.getName()).log(Level.SEVERE, "Can not read "+programAllXml, ex);
        }
        return null;
    }
    
    private static long timeSupplier(MySupplier<String> supplier, String name) {
        long t0 = System.currentTimeMillis();
        for(int i = 0; i < 100; i++) {
            String tmp =
                    supplier.get();
        }
        long t1 = System.currentTimeMillis();
        System.out.println(name + " took "+((t1-t0)/100.0) +" ms");
        return (t1-t0);
    }
    
    private static interface MySupplier<T> {
        
        /*@Nullable*/
        T get();
    }
    
    public static void main(String[] args) throws CRCLException  {
        final CRCLSocket s = new CRCLSocket();
//        timeSupplier(new Supplier<String>() {
//            @Override
//            public String get() {
//                return readWith4();
//            }
//        }, "readWith4");
        timeSupplier(new MySupplier<String>() {
            @Override
            /*@Nullable*/
            public String get() {
                return readWithDefault(s);
            }
        }, "readWithDefault");
//        timeSupplier(new MySupplier<String>() {
//            @Override
//            public String get() {
//                return readWithOld();
//            }
//        }, "readWithOld");
    }
    private static final Logger LOG = Logger.getLogger(PerfTestReadUntilEndTag.class.getName());
}
