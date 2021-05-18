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
 *                 SetIntermediatePoseToleranceType is derived from MiddleCommandType.
 *                 An instance of SetIntermediatePoseToleranceType has the following
 *                 elements:
 *                 Name (inherited, optional)
 *                 CommandID (inherited)
 *                 Tolerance.
 * 
 *                 The Tolerance element indicates to the robot the precision with
 *                 which it must reach each intermediate waypoint.
 *             
 * 
 * <p>Java class for SetIntermediatePoseToleranceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SetIntermediatePoseToleranceType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{}MiddleCommandType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Tolerance" type="{}PoseToleranceType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SetIntermediatePoseToleranceType", propOrder = {
    "tolerance"
})
public class SetIntermediatePoseToleranceType
    extends MiddleCommandType
{

    @XmlElement(name = "Tolerance", required = true)
    protected PoseToleranceType tolerance;

    /**
     * Gets the value of the tolerance property.
     * 
     * @return
     *     possible object is
     *     {@link PoseToleranceType }
     *     
     */
    public PoseToleranceType getTolerance() {
        return tolerance;
    }

    /**
     * Sets the value of the tolerance property.
     * 
     * @param value
     *     allowed object is
     *     {@link PoseToleranceType }
     *     
     */
    public void setTolerance(PoseToleranceType value) {
        this.tolerance = value;
    }

}