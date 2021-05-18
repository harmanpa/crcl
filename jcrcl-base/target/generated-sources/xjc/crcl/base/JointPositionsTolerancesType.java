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
 *                 JointPositionsTolerancesType is derived from DataThingType.
 *                 An instance of JointPositionsTolerancesType has the following elements:
 *                 Name (inherited, optional)
 *                 JointTolerances
 *                 
 *                 Used to specify how close to a given joint position given with
 *                 an actuate joints command the joint with the given jointnumber 
 *                 must be to to the goal position before the command may 
 *                 be considered done.
 *             
 * 
 * <p>Java class for JointPositionsTolerancesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="JointPositionsTolerancesType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{}DataThingType"&gt;
 *       &lt;sequence&gt;
 *         &lt;sequence&gt;
 *           &lt;element name="Setting" type="{}JointPositionToleranceSettingType" maxOccurs="unbounded"/&gt;
 *         &lt;/sequence&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JointPositionsTolerancesType", propOrder = {
    "setting"
})
public class JointPositionsTolerancesType
    extends DataThingType
{

    @XmlElement(name = "Setting", required = true)
    protected List<JointPositionToleranceSettingType> setting;

    /**
     * Gets the value of the setting property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the setting property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSetting().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JointPositionToleranceSettingType }
     * 
     * 
     */
    public List<JointPositionToleranceSettingType> getSetting() {
        if (setting == null) {
            setting = new ArrayList<JointPositionToleranceSettingType>();
        }
        return this.setting;
    }

}
