//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.05.18 at 02:18:34 PM BST 
//


package crcl.base;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *                 EndCanonType is derived from CRCLCommandType.
 *                 An instance of EndCanonType has the following elements:
 *                 Name (inherited, optional)
 *                 CommandID (inherited).
 * 
 *                 An instance of EndCanonType is used to indicate that the robot
 *                 should not execute any further CRCL commands other than an
 *                 instance of InitCanonType until an InitCanonType command is
 *                 received. Other robot-specific actions may be taken in
 *                 preparation for shutting down.
 *             
 * 
 * <p>Java class for EndCanonType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EndCanonType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{}CRCLCommandType"&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EndCanonType")
public class EndCanonType
    extends CRCLCommandType
{


}