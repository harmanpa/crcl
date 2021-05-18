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
 *                 SetTransSpeedType is derived from MiddleCommandType.
 *                 An instance of SetTransSpeedType has the following
 *                 elements:
 *                 Name (inherited, optional)
 *                 CommandID (inherited)
 *                 TransSpeed.
 * 
 *                 TransSpeed specifies the translational speed that should be used.
 *             
 * 
 * <p>Java class for SetTransSpeedType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SetTransSpeedType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{}MiddleCommandType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TransSpeed" type="{}TransSpeedType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SetTransSpeedType", propOrder = {
    "transSpeed"
})
public class SetTransSpeedType
    extends MiddleCommandType
{

    @XmlElement(name = "TransSpeed", required = true)
    protected TransSpeedType transSpeed;

    /**
     * Gets the value of the transSpeed property.
     * 
     * @return
     *     possible object is
     *     {@link TransSpeedType }
     *     
     */
    public TransSpeedType getTransSpeed() {
        return transSpeed;
    }

    /**
     * Sets the value of the transSpeed property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransSpeedType }
     *     
     */
    public void setTransSpeed(TransSpeedType value) {
        this.transSpeed = value;
    }

}
