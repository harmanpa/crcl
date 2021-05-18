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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *                 CommandStatusType is derived from DataThingType.
 *                 An instance of CommandStatusType has the following elements:
 *                 Name (inherited, optional)
 *                 CommandID
 *                 StatusID
 *                 CommandState
 *                 StateDescription (optional)
 *                 ProgramFile (optional)
 *                 ProgramIndex (optional)
 *                 ProgramLength (optional).
 * 
 *                 The CommandStatusType relates the execution status of the
 *                 currently executing command (or the most recently executed
 *                 command, if there is no current command).
 *                 CommandID echoes the command id from the received command to
 *                 which the status message applies
 *                 StatusID is an ID associated with this particular status
 *                 message.
 *                 StateDescription is an optional brief description of the state
 *                 such as "Joint 3 at -171.0 less than limit -170.0" or 
 *                 "Waiting for Operator".
 *                 ProgramFile provides an optional reference if the currently executing 
 *                 command is known to have come from a particular file.
 *                 ProgramIndex provoides an optional reference to the element within a 
 *                 program. If the currently executing command is known to have come 
 *                 from a particular file. The InitCanon command will have index 0, 
 *                 and first MiddleCommand will have index 1.
 *                 ProgramLength is the number of commands in the current program if 
 *                 known.
 *           
 *           
 *           
 *                 The combination of StatusID and CommandID must be unique
 *                 within a session.
 *             
 * 
 * <p>Java class for CommandStatusType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CommandStatusType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{}DataThingType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CommandID" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="StatusID" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="CommandState" type="{}CommandStateEnumType"/&gt;
 *         &lt;element name="StateDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ProgramFile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ProgramIndex" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="ProgramLength" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="OverridePercent" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommandStatusType", propOrder = {
    "commandID",
    "statusID",
    "commandState",
    "stateDescription",
    "programFile",
    "programIndex",
    "programLength",
    "overridePercent"
})
public class CommandStatusType
    extends DataThingType
{

    @XmlElement(name = "CommandID")
    protected long commandID;
    @XmlElement(name = "StatusID")
    protected long statusID;
    @XmlElement(name = "CommandState", required = true)
    @XmlSchemaType(name = "string")
    protected CommandStateEnumType commandState;
    @XmlElement(name = "StateDescription")
    protected String stateDescription;
    @XmlElement(name = "ProgramFile")
    protected String programFile;
    @XmlElement(name = "ProgramIndex")
    protected Integer programIndex;
    @XmlElement(name = "ProgramLength")
    protected Integer programLength;
    @XmlElement(name = "OverridePercent")
    protected Integer overridePercent;

    /**
     * Gets the value of the commandID property.
     * 
     */
    public long getCommandID() {
        return commandID;
    }

    /**
     * Sets the value of the commandID property.
     * 
     */
    public void setCommandID(long value) {
        this.commandID = value;
    }

    /**
     * Gets the value of the statusID property.
     * 
     */
    public long getStatusID() {
        return statusID;
    }

    /**
     * Sets the value of the statusID property.
     * 
     */
    public void setStatusID(long value) {
        this.statusID = value;
    }

    /**
     * Gets the value of the commandState property.
     * 
     * @return
     *     possible object is
     *     {@link CommandStateEnumType }
     *     
     */
    public CommandStateEnumType getCommandState() {
        return commandState;
    }

    /**
     * Sets the value of the commandState property.
     * 
     * @param value
     *     allowed object is
     *     {@link CommandStateEnumType }
     *     
     */
    public void setCommandState(CommandStateEnumType value) {
        this.commandState = value;
    }

    /**
     * Gets the value of the stateDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStateDescription() {
        return stateDescription;
    }

    /**
     * Sets the value of the stateDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStateDescription(String value) {
        this.stateDescription = value;
    }

    /**
     * Gets the value of the programFile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProgramFile() {
        return programFile;
    }

    /**
     * Sets the value of the programFile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProgramFile(String value) {
        this.programFile = value;
    }

    /**
     * Gets the value of the programIndex property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getProgramIndex() {
        return programIndex;
    }

    /**
     * Sets the value of the programIndex property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setProgramIndex(Integer value) {
        this.programIndex = value;
    }

    /**
     * Gets the value of the programLength property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getProgramLength() {
        return programLength;
    }

    /**
     * Sets the value of the programLength property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setProgramLength(Integer value) {
        this.programLength = value;
    }

    /**
     * Gets the value of the overridePercent property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getOverridePercent() {
        return overridePercent;
    }

    /**
     * Sets the value of the overridePercent property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOverridePercent(Integer value) {
        this.overridePercent = value;
    }

}
