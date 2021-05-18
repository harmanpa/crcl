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
 *                 JointForceTorqueType is derived from JointDetailsType.
 *                 An instance of JointForceTorqueType has the following elements:
 *                 Name (inherited, optional)
 *                 Setting (optional)
 *                 ChangeRate (optional).
 * 
 *                 JointForceTorqueType specifies the force or torque and the rate of
 *                 change of force or torque for a joint. For a translational joint,
 *                 Setting is in current force units, and ChangeRate is in current
 *                 force units per second. For a rotational joint, Setting is in
 *                 current torque units, and ChangeRate is in current torque units per
 *                 second.
 *             
 * 
 * <p>Java class for JointForceTorqueType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="JointForceTorqueType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{}JointDetailsType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Setting" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="ChangeRate" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JointForceTorqueType", propOrder = {
    "setting",
    "changeRate"
})
public class JointForceTorqueType
    extends JointDetailsType
{

    @XmlElement(name = "Setting")
    protected Double setting;
    @XmlElement(name = "ChangeRate")
    protected Double changeRate;

    /**
     * Gets the value of the setting property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSetting() {
        return setting;
    }

    /**
     * Sets the value of the setting property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSetting(Double value) {
        this.setting = value;
    }

    /**
     * Gets the value of the changeRate property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getChangeRate() {
        return changeRate;
    }

    /**
     * Sets the value of the changeRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setChangeRate(Double value) {
        this.changeRate = value;
    }

}
