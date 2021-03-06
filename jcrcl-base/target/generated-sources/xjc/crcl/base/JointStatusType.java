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
 *                 JointStatusType is derived from DataThingType.
 *                 JointStatusType reports the status of one joint.
 *                 An instance of JointStatusType has the following elements:
 *                 Name (inherited, optional)
 *                 JointNumber
 *                 JointPosition (optional)
 *                 JointTorqueOrForce (optional)
 *                 JointVelocity (optional).
 * 
 *             
 * 
 * <p>Java class for JointStatusType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="JointStatusType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{}DataThingType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="JointNumber" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="JointPosition" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="JointTorqueOrForce" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="JointVelocity" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JointStatusType", propOrder = {
    "jointNumber",
    "jointPosition",
    "jointTorqueOrForce",
    "jointVelocity"
})
public class JointStatusType
    extends DataThingType
{

    @XmlElement(name = "JointNumber")
    protected int jointNumber;
    @XmlElement(name = "JointPosition")
    protected Double jointPosition;
    @XmlElement(name = "JointTorqueOrForce")
    protected Double jointTorqueOrForce;
    @XmlElement(name = "JointVelocity")
    protected Double jointVelocity;

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
     * Gets the value of the jointPosition property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getJointPosition() {
        return jointPosition;
    }

    /**
     * Sets the value of the jointPosition property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setJointPosition(Double value) {
        this.jointPosition = value;
    }

    /**
     * Gets the value of the jointTorqueOrForce property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getJointTorqueOrForce() {
        return jointTorqueOrForce;
    }

    /**
     * Sets the value of the jointTorqueOrForce property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setJointTorqueOrForce(Double value) {
        this.jointTorqueOrForce = value;
    }

    /**
     * Gets the value of the jointVelocity property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getJointVelocity() {
        return jointVelocity;
    }

    /**
     * Sets the value of the jointVelocity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setJointVelocity(Double value) {
        this.jointVelocity = value;
    }

}
