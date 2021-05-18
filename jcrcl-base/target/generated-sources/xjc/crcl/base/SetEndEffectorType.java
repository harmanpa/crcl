//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.05.18 at 02:18:34 PM BST 
//


package crcl.base;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *                 SetEndEffectorType is derived from MiddleCommandType.
 *                 An instance of SetEndEffectorType has the following
 *                 elements:
 *                 Name (inherited, optional)
 *                 CommandID (inherited)
 *                 Setting.
 * 
 *                 SetEndEffectorType is for setting the effectivity of end effectors.
 *                 If an end effector has multiple control modes, the control mode
 *                 must be set using a SetEndEffectorParameters command, so that the
 *                 meaning of SetEndEffector commands is unambiguous.
 * 
 *                 For end effectors that have a continuously variable setting, the
 *                 Setting means a fraction of maximum openness, force, torque, power,
 *                 etc.
 * 
 *                 For end effectors that have only two choices (powered or unpowered,
 *                 open or closed, on or off), a positive Setting value means powered,
 *                 open, or on, while a zero Setting value means unpowered, closed, or
 *                 off.
 *             
 * 
 * <p>Java class for SetEndEffectorType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SetEndEffectorType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{}MiddleCommandType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Setting" type="{}FractionType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SetEndEffectorType", propOrder = {
    "setting"
})
public class SetEndEffectorType
    extends MiddleCommandType
{

    @XmlElement(name = "Setting")
    protected double setting;

    /**
     * Gets the value of the setting property.
     * 
     */
    public double getSetting() {
        return setting;
    }

    /**
     * Sets the value of the setting property.
     * 
     */
    public void setSetting(double value) {
        this.setting = value;
    }

}
