//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.05.18 at 02:18:34 PM BST 
//


package crcl.base;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *                 SettingsStatusType is derived from DataThingType. It provides the values
 *                 echoed back from the appropriate command to set that parameter. It might
 *                 also provide the initial default value read from a configuration file or
 *                 from a lower level controller on startup if no command has yet been 
 *                 given.
 *         
 *                 An instance of SettingsStatusType has the following elements:
 *                 Name (inherited, optional)
 *                 AngleUnitName (optional)
 *                 EndEffectorParameterSetting (optional)
 *                 EndEffectorSetting (optional)
 *                 ForceUnitName (optional)
 *                 IntermediatePoseTolerance (optional)
 *                 JointLimits (optional)
 *                 LengthUnitName (optional)
 *                 MaxCartesianLimit (optional)
 *                 MinCartesianLimit (optional)
 *                 MotionCoordinated (optional)
 *                 PoseTolerance (optional)
 *                 RobotParameterSetting (optional)
 *                 RotAccelAbsolute (optional)
 *                 RotAccelRelative (optional)
 *                 RotSpeedAbsolute (optional)
 *                 RotSpeedRelative (optional)
 *                 TorqueUnitName (optional)
 *                 TransAccelAbsolute (optional)
 *                 TransAccelRelative (optional)
 *                 TransSpeedAbsolute (optional)
 *                 TransSpeedRelative (optional).
 * 
 *                 AngleUnitName is a string that can be only the literals 'radian' or
 *                 'degree'. This tells the robot that all further commands
 *                 giving angle values will implicitly use the named unit.
 *                 EndEffectorParameterSetting is for setting parameters of end
 *                 effectors that have parameters. The meaning of the parameter
 *                 settings is not part of CRCL. It is expected that this command will
 *                 be used only to send parameter values that can be used by the end
 *                 effector currently in use.
 *                 EndEffectorSetting is for setting the effectivity of end effectors.
 *                 If an end effector has multiple control modes, the control mode
 *                 must be set using a SetEndEffectorParameters command, so that the
 *                 meaning of SetEndEffector commands is unambiguous. For end effectors
 *                 that have a continuously variable setting, the Setting means a 
 *                 fraction of maximum openness, force, torque, power, etc. For end 
 *                 effectors that have only two choices (powered or unpowered, open or 
 *                 closed, on or off), a positive Setting value means powered, open, 
 *                 or on, while a zero Setting value means unpowered, closed, or off.
 *                 ForceUnitName is a string that can be only the literals 'newton',
 *                 'pound', or 'ounce'. This tells the robot that all further commands
 *                 giving force values will implicitly use the named unit.
 *                 JointLimits represents a list of different possible limits associated 
 *                 with each joint. These limits can not be directly set through CRCL.
 *                 IntermediatePoseTolerance indicates to the robot the precision with
 *                 which it must reach each intermediate waypoint.
 *                 LengthUnitName is a string that can be only the literals 'meter',
 *                 'millimeter', or 'inch'. This tells the robot that all further
 *                 commands giving position or length values will implicitly use the
 *                 named unit. 
 *                 MaxCartesianLimit is the point with greatest X,Y, and Z values that can 
 *                 be reached without violating a configured cartesian limit. It can no
 *                 be directly changed through CRCL.
 *                 MinCartesianLimit is the point with lowest X,Y, and Z values that can 
 *                 be reached without violating a configured cartesian limit. It can no
 *                 be directly changed through CRCL.
 *                 MotionCoordinated is a boolean. If the value is true, rotational and
 *                 translational motion must finish simultaneously in motion commands
 *                 (including each segment in a multiple segment motion command),
 *                 except as possibly temporarily overridden in the the motion
 *                 command. If the value is false, there is no such requirement.
 *                 PoseTolerance indicates to the robot the precision with
 *                 which it must reach its end location.
 *                 RobotParameterSetting is for setting robot parameters that
 *                 cannot be set by any other CRCL command. The meaning of the
 *                 parameter settings is not part of CRCL.
 *                 RotAccelAbsolute represents the target single axis
 *                 rotational acceleration for the robot, in current angle units per
 *                 second per second.
 *                 RotAccelRelative represents the fraction of the 
 *                 robot's maximum rotational acceleration that it should use.
 *                 RotSpeedAbsolute represents the target single axis
 *                 rotational speed for the robot, in current angle units per
 *                 second.
 *                 RotSpeedRelative represents the fraction of the 
 *                 robot's maximum rotational speed that it should use.
 *                 TorqueUnitName is a string that can be only the literals 'newtonMeter'
 *                 or 'footPound'. This tells the robot that all further commands
 *                 giving torque values will implicitly use the named unit.
 *                 TransAccelAbsolute represents the translational acceleration for the 
 *                 controlled point, in current length units per second per second.
 *                 TransAccelRelative represents the fraction of the 
 *                 robot's maximum translational acceleration that it should use.
 *                 TransSpeedAbsolute represents the translational speed for the 
 *                 controlled point, in current length units per second.
 *                 TransSpeedRelative represents the fraction of the 
 *                 robot's maximum translational speed that it should use.
 *             
 * 
 * <p>Java class for SettingsStatusType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SettingsStatusType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{}DataThingType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="AngleUnitName" type="{}AngleUnitEnumType" minOccurs="0"/&gt;
 *         &lt;element name="EndEffectorParameterSetting" type="{}ParameterSettingType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="EndEffectorSetting" type="{}FractionType" minOccurs="0"/&gt;
 *         &lt;element name="ForceUnitName" type="{}ForceUnitEnumType" minOccurs="0"/&gt;
 *         &lt;element name="JointLimits" type="{}JointLimitType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="IntermediatePoseTolerance" type="{}PoseToleranceType" minOccurs="0"/&gt;
 *         &lt;element name="LengthUnitName" type="{}LengthUnitEnumType" minOccurs="0"/&gt;
 *         &lt;element name="MaxCartesianLimit" type="{}PointType" minOccurs="0"/&gt;
 *         &lt;element name="MinCartesianLimit" type="{}PointType" minOccurs="0"/&gt;
 *         &lt;element name="MotionCoordinated" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="EndPoseTolerance" type="{}PoseToleranceType" minOccurs="0"/&gt;
 *         &lt;element name="RobotParameterSetting" type="{}ParameterSettingType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="RotAccelAbsolute" type="{}RotAccelAbsoluteType" minOccurs="0"/&gt;
 *         &lt;element name="RotAccelRelative" type="{}RotAccelRelativeType" minOccurs="0"/&gt;
 *         &lt;element name="RotSpeedAbsolute" type="{}RotSpeedAbsoluteType" minOccurs="0"/&gt;
 *         &lt;element name="RotSpeedRelative" type="{}RotSpeedRelativeType" minOccurs="0"/&gt;
 *         &lt;element name="TorqueUnitName" type="{}TorqueUnitEnumType" minOccurs="0"/&gt;
 *         &lt;element name="TransAccelAbsolute" type="{}TransAccelAbsoluteType" minOccurs="0"/&gt;
 *         &lt;element name="TransAccelRelative" type="{}TransAccelRelativeType" minOccurs="0"/&gt;
 *         &lt;element name="TransSpeedAbsolute" type="{}TransSpeedAbsoluteType" minOccurs="0"/&gt;
 *         &lt;element name="TransSpeedRelative" type="{}TransSpeedRelativeType" minOccurs="0"/&gt;
 *         &lt;element name="JointTolerances" type="{}JointPositionsTolerancesType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SettingsStatusType", propOrder = {
    "angleUnitName",
    "endEffectorParameterSetting",
    "endEffectorSetting",
    "forceUnitName",
    "jointLimits",
    "intermediatePoseTolerance",
    "lengthUnitName",
    "maxCartesianLimit",
    "minCartesianLimit",
    "motionCoordinated",
    "endPoseTolerance",
    "robotParameterSetting",
    "rotAccelAbsolute",
    "rotAccelRelative",
    "rotSpeedAbsolute",
    "rotSpeedRelative",
    "torqueUnitName",
    "transAccelAbsolute",
    "transAccelRelative",
    "transSpeedAbsolute",
    "transSpeedRelative",
    "jointTolerances"
})
public class SettingsStatusType
    extends DataThingType
{

    @XmlElement(name = "AngleUnitName")
    @XmlSchemaType(name = "NMTOKEN")
    protected AngleUnitEnumType angleUnitName;
    @XmlElement(name = "EndEffectorParameterSetting")
    protected List<ParameterSettingType> endEffectorParameterSetting;
    @XmlElement(name = "EndEffectorSetting")
    protected Double endEffectorSetting;
    @XmlElement(name = "ForceUnitName")
    @XmlSchemaType(name = "NMTOKEN")
    protected ForceUnitEnumType forceUnitName;
    @XmlElement(name = "JointLimits")
    protected List<JointLimitType> jointLimits;
    @XmlElement(name = "IntermediatePoseTolerance")
    protected PoseToleranceType intermediatePoseTolerance;
    @XmlElement(name = "LengthUnitName")
    @XmlSchemaType(name = "NMTOKEN")
    protected LengthUnitEnumType lengthUnitName;
    @XmlElement(name = "MaxCartesianLimit")
    protected PointType maxCartesianLimit;
    @XmlElement(name = "MinCartesianLimit")
    protected PointType minCartesianLimit;
    @XmlElement(name = "MotionCoordinated")
    protected Boolean motionCoordinated;
    @XmlElement(name = "EndPoseTolerance")
    protected PoseToleranceType endPoseTolerance;
    @XmlElement(name = "RobotParameterSetting")
    protected List<ParameterSettingType> robotParameterSetting;
    @XmlElement(name = "RotAccelAbsolute")
    protected RotAccelAbsoluteType rotAccelAbsolute;
    @XmlElement(name = "RotAccelRelative")
    protected RotAccelRelativeType rotAccelRelative;
    @XmlElement(name = "RotSpeedAbsolute")
    protected RotSpeedAbsoluteType rotSpeedAbsolute;
    @XmlElement(name = "RotSpeedRelative")
    protected RotSpeedRelativeType rotSpeedRelative;
    @XmlElement(name = "TorqueUnitName")
    @XmlSchemaType(name = "NMTOKEN")
    protected TorqueUnitEnumType torqueUnitName;
    @XmlElement(name = "TransAccelAbsolute")
    protected TransAccelAbsoluteType transAccelAbsolute;
    @XmlElement(name = "TransAccelRelative")
    protected TransAccelRelativeType transAccelRelative;
    @XmlElement(name = "TransSpeedAbsolute")
    protected TransSpeedAbsoluteType transSpeedAbsolute;
    @XmlElement(name = "TransSpeedRelative")
    protected TransSpeedRelativeType transSpeedRelative;
    @XmlElement(name = "JointTolerances")
    protected JointPositionsTolerancesType jointTolerances;

    /**
     * Gets the value of the angleUnitName property.
     * 
     * @return
     *     possible object is
     *     {@link AngleUnitEnumType }
     *     
     */
    public AngleUnitEnumType getAngleUnitName() {
        return angleUnitName;
    }

    /**
     * Sets the value of the angleUnitName property.
     * 
     * @param value
     *     allowed object is
     *     {@link AngleUnitEnumType }
     *     
     */
    public void setAngleUnitName(AngleUnitEnumType value) {
        this.angleUnitName = value;
    }

    /**
     * Gets the value of the endEffectorParameterSetting property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the endEffectorParameterSetting property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEndEffectorParameterSetting().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ParameterSettingType }
     * 
     * 
     */
    public List<ParameterSettingType> getEndEffectorParameterSetting() {
        if (endEffectorParameterSetting == null) {
            endEffectorParameterSetting = new ArrayList<ParameterSettingType>();
        }
        return this.endEffectorParameterSetting;
    }

    /**
     * Gets the value of the endEffectorSetting property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getEndEffectorSetting() {
        return endEffectorSetting;
    }

    /**
     * Sets the value of the endEffectorSetting property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setEndEffectorSetting(Double value) {
        this.endEffectorSetting = value;
    }

    /**
     * Gets the value of the forceUnitName property.
     * 
     * @return
     *     possible object is
     *     {@link ForceUnitEnumType }
     *     
     */
    public ForceUnitEnumType getForceUnitName() {
        return forceUnitName;
    }

    /**
     * Sets the value of the forceUnitName property.
     * 
     * @param value
     *     allowed object is
     *     {@link ForceUnitEnumType }
     *     
     */
    public void setForceUnitName(ForceUnitEnumType value) {
        this.forceUnitName = value;
    }

    /**
     * Gets the value of the jointLimits property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the jointLimits property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getJointLimits().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JointLimitType }
     * 
     * 
     */
    public List<JointLimitType> getJointLimits() {
        if (jointLimits == null) {
            jointLimits = new ArrayList<JointLimitType>();
        }
        return this.jointLimits;
    }

    /**
     * Gets the value of the intermediatePoseTolerance property.
     * 
     * @return
     *     possible object is
     *     {@link PoseToleranceType }
     *     
     */
    public PoseToleranceType getIntermediatePoseTolerance() {
        return intermediatePoseTolerance;
    }

    /**
     * Sets the value of the intermediatePoseTolerance property.
     * 
     * @param value
     *     allowed object is
     *     {@link PoseToleranceType }
     *     
     */
    public void setIntermediatePoseTolerance(PoseToleranceType value) {
        this.intermediatePoseTolerance = value;
    }

    /**
     * Gets the value of the lengthUnitName property.
     * 
     * @return
     *     possible object is
     *     {@link LengthUnitEnumType }
     *     
     */
    public LengthUnitEnumType getLengthUnitName() {
        return lengthUnitName;
    }

    /**
     * Sets the value of the lengthUnitName property.
     * 
     * @param value
     *     allowed object is
     *     {@link LengthUnitEnumType }
     *     
     */
    public void setLengthUnitName(LengthUnitEnumType value) {
        this.lengthUnitName = value;
    }

    /**
     * Gets the value of the maxCartesianLimit property.
     * 
     * @return
     *     possible object is
     *     {@link PointType }
     *     
     */
    public PointType getMaxCartesianLimit() {
        return maxCartesianLimit;
    }

    /**
     * Sets the value of the maxCartesianLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link PointType }
     *     
     */
    public void setMaxCartesianLimit(PointType value) {
        this.maxCartesianLimit = value;
    }

    /**
     * Gets the value of the minCartesianLimit property.
     * 
     * @return
     *     possible object is
     *     {@link PointType }
     *     
     */
    public PointType getMinCartesianLimit() {
        return minCartesianLimit;
    }

    /**
     * Sets the value of the minCartesianLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link PointType }
     *     
     */
    public void setMinCartesianLimit(PointType value) {
        this.minCartesianLimit = value;
    }

    /**
     * Gets the value of the motionCoordinated property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMotionCoordinated() {
        return motionCoordinated;
    }

    /**
     * Sets the value of the motionCoordinated property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMotionCoordinated(Boolean value) {
        this.motionCoordinated = value;
    }

    /**
     * Gets the value of the endPoseTolerance property.
     * 
     * @return
     *     possible object is
     *     {@link PoseToleranceType }
     *     
     */
    public PoseToleranceType getEndPoseTolerance() {
        return endPoseTolerance;
    }

    /**
     * Sets the value of the endPoseTolerance property.
     * 
     * @param value
     *     allowed object is
     *     {@link PoseToleranceType }
     *     
     */
    public void setEndPoseTolerance(PoseToleranceType value) {
        this.endPoseTolerance = value;
    }

    /**
     * Gets the value of the robotParameterSetting property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the robotParameterSetting property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRobotParameterSetting().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ParameterSettingType }
     * 
     * 
     */
    public List<ParameterSettingType> getRobotParameterSetting() {
        if (robotParameterSetting == null) {
            robotParameterSetting = new ArrayList<ParameterSettingType>();
        }
        return this.robotParameterSetting;
    }

    /**
     * Gets the value of the rotAccelAbsolute property.
     * 
     * @return
     *     possible object is
     *     {@link RotAccelAbsoluteType }
     *     
     */
    public RotAccelAbsoluteType getRotAccelAbsolute() {
        return rotAccelAbsolute;
    }

    /**
     * Sets the value of the rotAccelAbsolute property.
     * 
     * @param value
     *     allowed object is
     *     {@link RotAccelAbsoluteType }
     *     
     */
    public void setRotAccelAbsolute(RotAccelAbsoluteType value) {
        this.rotAccelAbsolute = value;
    }

    /**
     * Gets the value of the rotAccelRelative property.
     * 
     * @return
     *     possible object is
     *     {@link RotAccelRelativeType }
     *     
     */
    public RotAccelRelativeType getRotAccelRelative() {
        return rotAccelRelative;
    }

    /**
     * Sets the value of the rotAccelRelative property.
     * 
     * @param value
     *     allowed object is
     *     {@link RotAccelRelativeType }
     *     
     */
    public void setRotAccelRelative(RotAccelRelativeType value) {
        this.rotAccelRelative = value;
    }

    /**
     * Gets the value of the rotSpeedAbsolute property.
     * 
     * @return
     *     possible object is
     *     {@link RotSpeedAbsoluteType }
     *     
     */
    public RotSpeedAbsoluteType getRotSpeedAbsolute() {
        return rotSpeedAbsolute;
    }

    /**
     * Sets the value of the rotSpeedAbsolute property.
     * 
     * @param value
     *     allowed object is
     *     {@link RotSpeedAbsoluteType }
     *     
     */
    public void setRotSpeedAbsolute(RotSpeedAbsoluteType value) {
        this.rotSpeedAbsolute = value;
    }

    /**
     * Gets the value of the rotSpeedRelative property.
     * 
     * @return
     *     possible object is
     *     {@link RotSpeedRelativeType }
     *     
     */
    public RotSpeedRelativeType getRotSpeedRelative() {
        return rotSpeedRelative;
    }

    /**
     * Sets the value of the rotSpeedRelative property.
     * 
     * @param value
     *     allowed object is
     *     {@link RotSpeedRelativeType }
     *     
     */
    public void setRotSpeedRelative(RotSpeedRelativeType value) {
        this.rotSpeedRelative = value;
    }

    /**
     * Gets the value of the torqueUnitName property.
     * 
     * @return
     *     possible object is
     *     {@link TorqueUnitEnumType }
     *     
     */
    public TorqueUnitEnumType getTorqueUnitName() {
        return torqueUnitName;
    }

    /**
     * Sets the value of the torqueUnitName property.
     * 
     * @param value
     *     allowed object is
     *     {@link TorqueUnitEnumType }
     *     
     */
    public void setTorqueUnitName(TorqueUnitEnumType value) {
        this.torqueUnitName = value;
    }

    /**
     * Gets the value of the transAccelAbsolute property.
     * 
     * @return
     *     possible object is
     *     {@link TransAccelAbsoluteType }
     *     
     */
    public TransAccelAbsoluteType getTransAccelAbsolute() {
        return transAccelAbsolute;
    }

    /**
     * Sets the value of the transAccelAbsolute property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransAccelAbsoluteType }
     *     
     */
    public void setTransAccelAbsolute(TransAccelAbsoluteType value) {
        this.transAccelAbsolute = value;
    }

    /**
     * Gets the value of the transAccelRelative property.
     * 
     * @return
     *     possible object is
     *     {@link TransAccelRelativeType }
     *     
     */
    public TransAccelRelativeType getTransAccelRelative() {
        return transAccelRelative;
    }

    /**
     * Sets the value of the transAccelRelative property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransAccelRelativeType }
     *     
     */
    public void setTransAccelRelative(TransAccelRelativeType value) {
        this.transAccelRelative = value;
    }

    /**
     * Gets the value of the transSpeedAbsolute property.
     * 
     * @return
     *     possible object is
     *     {@link TransSpeedAbsoluteType }
     *     
     */
    public TransSpeedAbsoluteType getTransSpeedAbsolute() {
        return transSpeedAbsolute;
    }

    /**
     * Sets the value of the transSpeedAbsolute property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransSpeedAbsoluteType }
     *     
     */
    public void setTransSpeedAbsolute(TransSpeedAbsoluteType value) {
        this.transSpeedAbsolute = value;
    }

    /**
     * Gets the value of the transSpeedRelative property.
     * 
     * @return
     *     possible object is
     *     {@link TransSpeedRelativeType }
     *     
     */
    public TransSpeedRelativeType getTransSpeedRelative() {
        return transSpeedRelative;
    }

    /**
     * Sets the value of the transSpeedRelative property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransSpeedRelativeType }
     *     
     */
    public void setTransSpeedRelative(TransSpeedRelativeType value) {
        this.transSpeedRelative = value;
    }

    /**
     * Gets the value of the jointTolerances property.
     * 
     * @return
     *     possible object is
     *     {@link JointPositionsTolerancesType }
     *     
     */
    public JointPositionsTolerancesType getJointTolerances() {
        return jointTolerances;
    }

    /**
     * Sets the value of the jointTolerances property.
     * 
     * @param value
     *     allowed object is
     *     {@link JointPositionsTolerancesType }
     *     
     */
    public void setJointTolerances(JointPositionsTolerancesType value) {
        this.jointTolerances = value;
    }

}
