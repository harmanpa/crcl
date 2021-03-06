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
 *                 ConfigureStatusReportType is derived from MiddleCommandType. 
 *                 An instance of ConfigureStatusReportType has the following elements:
 *                 Name (inherited, optional)
 *                 CommandID (inherited)
 *                 ReportJointStatuses
 *                 ReportPoseStatus
 *                 ReportGripperStatus
 *                 ReportSettingsStatus
 *                 ReportSensorsStatus
 *                 ReportGuardsStatus.
 * 
 *                 ConfigureStatusReportType is used to specify how the status of the
 *                 robot should be reported. 
 *             
 * 
 * <p>Java class for ConfigureStatusReportType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConfigureStatusReportType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{}MiddleCommandType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ReportJointStatuses" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="ReportPoseStatus" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="ReportGripperStatus" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="ReportSettingsStatus" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="ReportSensorsStatus" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="ReportGuardsStatus" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConfigureStatusReportType", propOrder = {
    "reportJointStatuses",
    "reportPoseStatus",
    "reportGripperStatus",
    "reportSettingsStatus",
    "reportSensorsStatus",
    "reportGuardsStatus"
})
public class ConfigureStatusReportType
    extends MiddleCommandType
{

    @XmlElement(name = "ReportJointStatuses")
    protected boolean reportJointStatuses;
    @XmlElement(name = "ReportPoseStatus")
    protected boolean reportPoseStatus;
    @XmlElement(name = "ReportGripperStatus")
    protected boolean reportGripperStatus;
    @XmlElement(name = "ReportSettingsStatus")
    protected boolean reportSettingsStatus;
    @XmlElement(name = "ReportSensorsStatus")
    protected boolean reportSensorsStatus;
    @XmlElement(name = "ReportGuardsStatus")
    protected boolean reportGuardsStatus;

    /**
     * Gets the value of the reportJointStatuses property.
     * 
     */
    public boolean isReportJointStatuses() {
        return reportJointStatuses;
    }

    /**
     * Sets the value of the reportJointStatuses property.
     * 
     */
    public void setReportJointStatuses(boolean value) {
        this.reportJointStatuses = value;
    }

    /**
     * Gets the value of the reportPoseStatus property.
     * 
     */
    public boolean isReportPoseStatus() {
        return reportPoseStatus;
    }

    /**
     * Sets the value of the reportPoseStatus property.
     * 
     */
    public void setReportPoseStatus(boolean value) {
        this.reportPoseStatus = value;
    }

    /**
     * Gets the value of the reportGripperStatus property.
     * 
     */
    public boolean isReportGripperStatus() {
        return reportGripperStatus;
    }

    /**
     * Sets the value of the reportGripperStatus property.
     * 
     */
    public void setReportGripperStatus(boolean value) {
        this.reportGripperStatus = value;
    }

    /**
     * Gets the value of the reportSettingsStatus property.
     * 
     */
    public boolean isReportSettingsStatus() {
        return reportSettingsStatus;
    }

    /**
     * Sets the value of the reportSettingsStatus property.
     * 
     */
    public void setReportSettingsStatus(boolean value) {
        this.reportSettingsStatus = value;
    }

    /**
     * Gets the value of the reportSensorsStatus property.
     * 
     */
    public boolean isReportSensorsStatus() {
        return reportSensorsStatus;
    }

    /**
     * Sets the value of the reportSensorsStatus property.
     * 
     */
    public void setReportSensorsStatus(boolean value) {
        this.reportSensorsStatus = value;
    }

    /**
     * Gets the value of the reportGuardsStatus property.
     * 
     */
    public boolean isReportGuardsStatus() {
        return reportGuardsStatus;
    }

    /**
     * Sets the value of the reportGuardsStatus property.
     * 
     */
    public void setReportGuardsStatus(boolean value) {
        this.reportGuardsStatus = value;
    }

}
