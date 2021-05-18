//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.05.18 at 02:18:34 PM BST 
//


package crcl.base;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *                 ConfigureJointReportsType is derived from MiddleCommandType. 
 *                 An instance of ConfigureJointReportsType has the following elements:
 *                 Name (inherited, optional)
 *                 CommandID (inherited)
 *                 ResetAll
 *                 ConfigureJointReport (multiple).
 * 
 *                 ConfigureJointReportsType is used to specify how the status of the
 *                 robot joints should be reported. The ConfigureJointReports command
 *                 may be used more than once during a session to change joint status
 *                 reporting.
 * 
 *                 If ResetAll is set to true, an instance of
 *                 ConfigureJointReportsType resets the joint reporting of all
 *                 joints, and, in that case, if there is no ConfigureJointReport
 *                 element for a joint in the instance, the status of that joint
 *                 should not be reported. Thus, an instance of
 *                 ConfigureJointReportsType with ResetAll set to true and no
 *                 ConfigureJointReport elements turns off all joint reporting.
 * 
 *                 If ResetAll is set to false, status reporting is changed for only
 *                 those joints given in a ConfigureJointReport element. Status
 *                 reporting for other joints remains the same.
 * 
 *                 No joint may appear in more than one ConfigureJointReport element.
 *                 Joint numbers in ConfigureJointReport elements must be given in
 *                 increasing order.
 * 
 *                 See the in-line documentation of CRCLStatus.xsd for further
 *                 information.
 *             
 * 
 * <p>Java class for ConfigureJointReportsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConfigureJointReportsType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{}MiddleCommandType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ResetAll" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="ConfigureJointReport" type="{}ConfigureJointReportType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConfigureJointReportsType", propOrder = {
    "resetAll",
    "configureJointReport"
})
public class ConfigureJointReportsType
    extends MiddleCommandType
{

    @XmlElement(name = "ResetAll")
    protected boolean resetAll;
    @XmlElement(name = "ConfigureJointReport")
    protected List<ConfigureJointReportType> configureJointReport;

    /**
     * Gets the value of the resetAll property.
     * 
     */
    public boolean isResetAll() {
        return resetAll;
    }

    /**
     * Sets the value of the resetAll property.
     * 
     */
    public void setResetAll(boolean value) {
        this.resetAll = value;
    }

    /**
     * Gets the value of the configureJointReport property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the configureJointReport property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConfigureJointReport().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConfigureJointReportType }
     * 
     * 
     */
    public List<ConfigureJointReportType> getConfigureJointReport() {
        if (configureJointReport == null) {
            configureJointReport = new ArrayList<ConfigureJointReportType>();
        }
        return this.configureJointReport;
    }

}
