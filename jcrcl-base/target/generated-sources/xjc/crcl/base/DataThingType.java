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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *                 An instance of DataThingType has the following elements:
 *                 Name (optional)
 *                 .
 * 
 *                 DataThingType is an abstract type from which more specific types
 *                 of data thing are derived. That includes all complex data
 *                 types such as Vector, PoseType, etc.
 *             
 * 
 * <p>Java class for DataThingType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DataThingType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataThingType", propOrder = {
    "name"
})
@XmlSeeAlso({
    CRCLCommandInstanceType.class,
    CRCLProgramType.class,
    CRCLStatusType.class,
    JointPositionToleranceSettingType.class,
    JointPositionsTolerancesType.class,
    PointType.class,
    PoseType.class,
    TwistType.class,
    VectorType.class,
    WrenchType.class,
    PoseToleranceType.class,
    ParameterSettingType.class,
    RotAccelType.class,
    RotSpeedType.class,
    TransAccelType.class,
    TransSpeedType.class,
    GuardType.class,
    ActuateJointType.class,
    ConfigureJointReportType.class,
    JointDetailsType.class,
    CRCLCommandType.class,
    CommandStatusType.class,
    JointStatusesType.class,
    JointStatusType.class,
    JointLimitType.class,
    PoseStatusType.class,
    SettingsStatusType.class,
    GripperStatusType.class,
    SensorStatusesType.class,
    GuardsStatusesType.class,
    SensorStatusType.class
})
public abstract class DataThingType {

    @XmlElement(name = "Name")
    protected String name;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

}
