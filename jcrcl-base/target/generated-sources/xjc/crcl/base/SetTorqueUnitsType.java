//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.05.18 at 02:21:35 PM BST 
//


package crcl.base;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *                 SetTorqueUnitsType is derived from MiddleCommandType.
 *                 An instance of SetTorqueUnitsType has the following elements:
 *                 Name (inherited, optional)
 *                 CommandID (inherited)
 *                 UnitName.
 * 
 *                 UnitName is a string that can be only the literals 'newtonMeter'
 *                 or 'footPound'. This tells the robot that all further commands
 *                 giving torque values will implicitly use the named unit.
 *             
 * 
 * <p>Java class for SetTorqueUnitsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SetTorqueUnitsType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{}MiddleCommandType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="UnitName" type="{}TorqueUnitEnumType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SetTorqueUnitsType", propOrder = {
    "unitName"
})
public class SetTorqueUnitsType
    extends MiddleCommandType
{

    @XmlElement(name = "UnitName", required = true)
    @XmlSchemaType(name = "NMTOKEN")
    protected TorqueUnitEnumType unitName;

    /**
     * Gets the value of the unitName property.
     * 
     * @return
     *     possible object is
     *     {@link TorqueUnitEnumType }
     *     
     */
    public TorqueUnitEnumType getUnitName() {
        return unitName;
    }

    /**
     * Sets the value of the unitName property.
     * 
     * @param value
     *     allowed object is
     *     {@link TorqueUnitEnumType }
     *     
     */
    public void setUnitName(TorqueUnitEnumType value) {
        this.unitName = value;
    }

}
