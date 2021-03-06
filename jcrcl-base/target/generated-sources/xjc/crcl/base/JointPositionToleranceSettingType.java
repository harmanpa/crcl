//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.05.18 at 02:23:10 PM BST 
//


package crcl.base;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *                 JointPositionToleranceType is derived from DataThingType.
 *                 An instance of JointPositionToleranceType has the following elements:
 *                 Name (inherited, optional)
 *                 JointNumber
 *                 JointPositionTolerance
 * 
 *                 Used to specify how close to a given joint position given with
 *                 an actuate joints command the joint with the given jointnumber 
 *                 must be to to the goal position before the command may 
 *                 be considered done.
 *             
 * 
 * <p>Java class for JointPositionToleranceSettingType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="JointPositionToleranceSettingType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{}DataThingType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="JointNumber" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="JointPositionTolerance" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JointPositionToleranceSettingType", propOrder = {
    "jointNumber",
    "jointPositionTolerance"
})
public class JointPositionToleranceSettingType
    extends DataThingType
{

    @XmlElement(name = "JointNumber")
    protected int jointNumber;
    @XmlElement(name = "JointPositionTolerance")
    protected double jointPositionTolerance;

    /**
     * Gets the value of the jointNumber property.
     * 
     */
    public int getJointNumber() {
        return jointNumber;
    }

    /**
     * Sets the value of the jointNumber property.
     * 
     */
    public void setJointNumber(int value) {
        this.jointNumber = value;
    }

    /**
     * Gets the value of the jointPositionTolerance property.
     * 
     */
    public double getJointPositionTolerance() {
        return jointPositionTolerance;
    }

    /**
     * Sets the value of the jointPositionTolerance property.
     * 
     */
    public void setJointPositionTolerance(double value) {
        this.jointPositionTolerance = value;
    }

}
