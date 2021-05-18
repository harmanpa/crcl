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
 *                 TransSpeedAbsoluteType is derived from TransSpeedType.
 *                 An instance of TransSpeedAbsoluteType has the following
 *                 elements:
 *                 Name (inherited, optional)
 *                 Setting.
 * 
 *                 Setting is a real number that represents the target speed for the
 *                 controlled point, in current length units per second.
 *             
 * 
 * <p>Java class for TransSpeedAbsoluteType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TransSpeedAbsoluteType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{}TransSpeedType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Setting" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransSpeedAbsoluteType", propOrder = {
    "setting"
})
public class TransSpeedAbsoluteType
    extends TransSpeedType
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
