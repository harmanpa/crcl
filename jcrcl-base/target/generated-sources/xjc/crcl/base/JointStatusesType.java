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
 *                 JointStatusesType is derived from DataThingType.
 *                 An instance of JointStatusesType has the following elements:
 *                 Name (inherited, optional)
 *                 JointStatus (multiple).
 *  
 *                 Each JointStatus element gives the status of one joint. No
 *                 joint may be reported more than once in an instance of
 *                 JointStatusesType. See notes at the beginning of this file
 *                 regarding configuring joint status.
 *             
 * 
 * <p>Java class for JointStatusesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="JointStatusesType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{}DataThingType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="JointStatus" type="{}JointStatusType" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JointStatusesType", propOrder = {
    "jointStatus"
})
public class JointStatusesType
    extends DataThingType
{

    @XmlElement(name = "JointStatus", required = true)
    protected List<JointStatusType> jointStatus;

    /**
     * Gets the value of the jointStatus property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the jointStatus property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getJointStatus().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JointStatusType }
     * 
     * 
     */
    public List<JointStatusType> getJointStatus() {
        if (jointStatus == null) {
            jointStatus = new ArrayList<JointStatusType>();
        }
        return this.jointStatus;
    }

}
