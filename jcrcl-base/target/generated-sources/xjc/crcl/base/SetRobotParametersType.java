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
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *                 SetRobotParametersType is derived from MiddleCommandType.
 *                 An instance of SetRobotParametersType has the following
 *                 elements:
 *                 Name (inherited, optional)
 *                 CommandID (inherited)
 *                 ParameterSetting (multiple).
 * 
 *                 SetRobotParametersType is for setting robot parameters that
 *                 cannot be set by any other CRCL command. The meaning of the
 *                 parameter settings is not part of CRCL.
 *             
 * 
 * <p>Java class for SetRobotParametersType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SetRobotParametersType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{}MiddleCommandType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ParameterSetting" type="{}ParameterSettingType" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SetRobotParametersType", propOrder = {
    "parameterSetting"
})
public class SetRobotParametersType
    extends MiddleCommandType
{

    @XmlElement(name = "ParameterSetting", required = true)
    protected List<ParameterSettingType> parameterSetting;

    /**
     * Gets the value of the parameterSetting property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the parameterSetting property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParameterSetting().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ParameterSettingType }
     * 
     * 
     */
    public List<ParameterSettingType> getParameterSetting() {
        if (parameterSetting == null) {
            parameterSetting = new ArrayList<ParameterSettingType>();
        }
        return this.parameterSetting;
    }

}
