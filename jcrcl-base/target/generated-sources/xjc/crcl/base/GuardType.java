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
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 *                 The GuardType is derived from DataThingType.
 *                 An instance of GuardType has the following
 *                 elements:
 *                 Name (inherited, optional)
 *                 SensorID
 *                 SubField (optional)
 *                 LimitType
 *                 LimitValue
 *                 RecheckTimeMicroSeconds
 *                 CheckCount 
 *                 LastCheckTime 
 *                 LastCheckValue
 *                 RequireTrigger
 *                 ErrorOnTrigger
 *                 
 *                 A GuardType can be added to any command to indicate the
 *                 command should be aborted if the value of the sensors subfield
 *                 increases by at-least the MaxIncrease value if specified OR
 *                 decreases by at-least the MaxDecrease value if specified OR
 *                 is greater than or equal to the MaxValue if specified OR
 *                 is less than or equal to the MinValue if specified.
 *                 
 *                 If the RecheckTimeMicroSeconds is specified the gaurd the sensor
 *                 will be read to determine if the command must be aborted at that
 *                 frequency and will otherwise check at the default rate for that
 *                 sensor. Hardware and operating system limitations may require the
 *                 sensor be checked less frequently.
 *                 
 *                 CheckCount, LastCheckTime and LastCheckValue are only ignored when
 *                 inside a command. They may be optionaly set when included in status.
 *                 CheckCount is the number of times the guard has been checked. LastCheckTime is 
 *                 the time in milliseconds since 1970 (aka unix-time). 
 *                 
 *                 If RequireTrigger is specified and true, then if the command completes
 *                 without ever detecting a trigger the state will be set to CRCL_ERROR.
 *                 If ErrorOnTrigger is specified and true, then after the guard is triggered
 *                 the state will be set to CRCL_ERROR.
 *             
 * 
 * <p>Java class for GuardType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GuardType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{}DataThingType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SensorID" type="{http://www.w3.org/2001/XMLSchema}token"/&gt;
 *         &lt;element name="SubField" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/&gt;
 *         &lt;element name="LimitType" type="{}GuardLimitEnumType"/&gt;
 *         &lt;element name="LimitValue" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="RecheckTimeMicroSeconds" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="CheckCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="LastCheckTime" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="LastCheckValue" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="RequireTrigger" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="ErrorOnTrigger" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GuardType", propOrder = {
    "sensorID",
    "subField",
    "limitType",
    "limitValue",
    "recheckTimeMicroSeconds",
    "checkCount",
    "lastCheckTime",
    "lastCheckValue",
    "requireTrigger",
    "errorOnTrigger"
})
public class GuardType
    extends DataThingType
{

    @XmlElement(name = "SensorID", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String sensorID;
    @XmlElement(name = "SubField")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String subField;
    @XmlElement(name = "LimitType", required = true)
    @XmlSchemaType(name = "string")
    protected GuardLimitEnumType limitType;
    @XmlElement(name = "LimitValue")
    protected double limitValue;
    @XmlElement(name = "RecheckTimeMicroSeconds")
    protected Long recheckTimeMicroSeconds;
    @XmlElement(name = "CheckCount")
    protected Long checkCount;
    @XmlElement(name = "LastCheckTime")
    protected Long lastCheckTime;
    @XmlElement(name = "LastCheckValue")
    protected Double lastCheckValue;
    @XmlElement(name = "RequireTrigger")
    protected Boolean requireTrigger;
    @XmlElement(name = "ErrorOnTrigger")
    protected Boolean errorOnTrigger;

    /**
     * Gets the value of the sensorID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSensorID() {
        return sensorID;
    }

    /**
     * Sets the value of the sensorID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSensorID(String value) {
        this.sensorID = value;
    }

    /**
     * Gets the value of the subField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubField() {
        return subField;
    }

    /**
     * Sets the value of the subField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubField(String value) {
        this.subField = value;
    }

    /**
     * Gets the value of the limitType property.
     * 
     * @return
     *     possible object is
     *     {@link GuardLimitEnumType }
     *     
     */
    public GuardLimitEnumType getLimitType() {
        return limitType;
    }

    /**
     * Sets the value of the limitType property.
     * 
     * @param value
     *     allowed object is
     *     {@link GuardLimitEnumType }
     *     
     */
    public void setLimitType(GuardLimitEnumType value) {
        this.limitType = value;
    }

    /**
     * Gets the value of the limitValue property.
     * 
     */
    public double getLimitValue() {
        return limitValue;
    }

    /**
     * Sets the value of the limitValue property.
     * 
     */
    public void setLimitValue(double value) {
        this.limitValue = value;
    }

    /**
     * Gets the value of the recheckTimeMicroSeconds property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRecheckTimeMicroSeconds() {
        return recheckTimeMicroSeconds;
    }

    /**
     * Sets the value of the recheckTimeMicroSeconds property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRecheckTimeMicroSeconds(Long value) {
        this.recheckTimeMicroSeconds = value;
    }

    /**
     * Gets the value of the checkCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCheckCount() {
        return checkCount;
    }

    /**
     * Sets the value of the checkCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCheckCount(Long value) {
        this.checkCount = value;
    }

    /**
     * Gets the value of the lastCheckTime property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getLastCheckTime() {
        return lastCheckTime;
    }

    /**
     * Sets the value of the lastCheckTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setLastCheckTime(Long value) {
        this.lastCheckTime = value;
    }

    /**
     * Gets the value of the lastCheckValue property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getLastCheckValue() {
        return lastCheckValue;
    }

    /**
     * Sets the value of the lastCheckValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setLastCheckValue(Double value) {
        this.lastCheckValue = value;
    }

    /**
     * Gets the value of the requireTrigger property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRequireTrigger() {
        return requireTrigger;
    }

    /**
     * Sets the value of the requireTrigger property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRequireTrigger(Boolean value) {
        this.requireTrigger = value;
    }

    /**
     * Gets the value of the errorOnTrigger property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isErrorOnTrigger() {
        return errorOnTrigger;
    }

    /**
     * Sets the value of the errorOnTrigger property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setErrorOnTrigger(Boolean value) {
        this.errorOnTrigger = value;
    }

}
