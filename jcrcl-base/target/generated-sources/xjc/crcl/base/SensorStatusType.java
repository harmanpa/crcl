//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.05.18 at 02:23:10 PM BST 
//


package crcl.base;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 *                 SensorStatusType is derived from DataThingType.
 *                 SensorStatusType reports the status of one sensor.
 *                 An instance of SensorStatusType has the following elements:
 *                 Name (inherited, optional)
 *                 SensorID
 *                 ReadCount
 *                 LastReadTime
 *                 SensorParameterSetting (optional).
 *                 
 *                 SensorID should be unique and unlikely to change within a system such as hardware model and serial number or
 *                 a statically set IP or hostname. Read count returns the number of times
 *                 the sensor has been read or a frame number if available.  LastReadTime is in milliseconds
 *                 since 1970 (aka unix time).
 *             
 * 
 * <p>Java class for SensorStatusType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SensorStatusType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{}DataThingType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SensorID" type="{http://www.w3.org/2001/XMLSchema}token"/&gt;
 *         &lt;element name="ReadCount" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="LastReadTime" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="SensorParameterSetting" type="{}ParameterSettingType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SensorStatusType", propOrder = {
    "sensorID",
    "readCount",
    "lastReadTime",
    "sensorParameterSetting"
})
@XmlSeeAlso({
    OnOffSensorStatusType.class,
    ForceTorqueSensorStatusType.class,
    ScalarSensorStatusType.class,
    CountSensorStatusType.class
})
public class SensorStatusType
    extends DataThingType
{

    @XmlElement(name = "SensorID", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String sensorID;
    @XmlElement(name = "ReadCount")
    protected int readCount;
    @XmlElement(name = "LastReadTime")
    protected long lastReadTime;
    @XmlElement(name = "SensorParameterSetting")
    protected List<ParameterSettingType> sensorParameterSetting;

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
     * Gets the value of the readCount property.
     * 
     */
    public int getReadCount() {
        return readCount;
    }

    /**
     * Sets the value of the readCount property.
     * 
     */
    public void setReadCount(int value) {
        this.readCount = value;
    }

    /**
     * Gets the value of the lastReadTime property.
     * 
     */
    public long getLastReadTime() {
        return lastReadTime;
    }

    /**
     * Sets the value of the lastReadTime property.
     * 
     */
    public void setLastReadTime(long value) {
        this.lastReadTime = value;
    }

    /**
     * Gets the value of the sensorParameterSetting property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sensorParameterSetting property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSensorParameterSetting().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ParameterSettingType }
     * 
     * 
     */
    public List<ParameterSettingType> getSensorParameterSetting() {
        if (sensorParameterSetting == null) {
            sensorParameterSetting = new ArrayList<ParameterSettingType>();
        }
        return this.sensorParameterSetting;
    }

}
