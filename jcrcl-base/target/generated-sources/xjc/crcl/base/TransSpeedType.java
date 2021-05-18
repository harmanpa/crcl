//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.05.18 at 02:23:10 PM BST 
//


package crcl.base;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *                 The abstract TransSpeedType is derived from DataThingType.
 *                 An instance of TransSpeedType has the following
 *                 elements:
 *                 Name (inherited, optional)
 *                 .
 * 
 *                 TransSpeedType is an abstract type used as the parent type of: 
 *                 TransSpeedAbsoluteType
 *                 TransSpeedRelativeType.
 *             
 * 
 * <p>Java class for TransSpeedType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TransSpeedType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{}DataThingType"&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransSpeedType")
@XmlSeeAlso({
    TransSpeedAbsoluteType.class,
    TransSpeedRelativeType.class
})
public abstract class TransSpeedType
    extends DataThingType
{


}
