//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.05.18 at 02:21:35 PM BST 
//


package crcl.base;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *                 The abstract TransAccelType is derived from DataThingType.
 *                 An instance of TransAccelType has the following elements:
 *                 Name (inherited, optional)
 *                 .
 * 
 *                 TransAccelType is an abstract type used as the parent type of: 
 *                 TransAccelAbsoluteType
 *                 TransAccelRelativeType.
 *             
 * 
 * <p>Java class for TransAccelType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TransAccelType"&gt;
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
@XmlType(name = "TransAccelType")
@XmlSeeAlso({
    TransAccelAbsoluteType.class,
    TransAccelRelativeType.class
})
public abstract class TransAccelType
    extends DataThingType
{


}
