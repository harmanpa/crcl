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
 *                 JointSpeedAccelType is derived from JointDetailsType.
 *                 An instance of JointSpeedAccelType has the following elements:
 *                 Name (inherited, optional)
 *                 JointSpeed (optional)
 *                 JointAccel (optional).
 * 
 *                 JointSpeedAccelType specifies the speed and acceleration for a
 *                 joint. For a rotational joint, the speed units are the current
 *                 angle units per second, and the acceleration units are the current
 *                 angle units per second per second. For a translational joint, the
 *                 speed units are the current length units per second, and the
 *                 acceleration units are the current length units per second per
 *                 second.
 *             
 * 
 * <p>Java class for JointSpeedAccelType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="JointSpeedAccelType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{}JointDetailsType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="JointSpeed" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="JointAccel" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JointSpeedAccelType", propOrder = {
    "jointSpeed",
    "jointAccel"
})
public class JointSpeedAccelType
    extends JointDetailsType
{

    @XmlElement(name = "JointSpeed")
    protected Double jointSpeed;
    @XmlElement(name = "JointAccel")
    protected Double jointAccel;

    /**
     * Gets the value of the jointSpeed property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getJointSpeed() {
        return jointSpeed;
    }

    /**
     * Sets the value of the jointSpeed property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setJointSpeed(Double value) {
        this.jointSpeed = value;
    }

    /**
     * Gets the value of the jointAccel property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getJointAccel() {
        return jointAccel;
    }

    /**
     * Sets the value of the jointAccel property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setJointAccel(Double value) {
        this.jointAccel = value;
    }

}
