//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.05.18 at 02:23:10 PM BST 
//


package crcl.base;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *                 InitCanonType is derived from CRCLCommandType.
 *                 An instance of InitCanonType has the following elements:
 *                 Name (inherited, optional)
 *                 CommandID (inherited).
 * 
 *                 An instance of InitCanonType is used to indicate that the robot
 *                 should be prepared to execute further canonical robot commands.
 *                 When a robot is ready to execute commands, the first CRCL command
 *                 it should be sent is an instance of InitCanonType. Any CRCL
 *                 commands received before an instance of InitCanonType must not be
 *                 executed. Other robot-specific actions may be taken in preparation
 *                 for executing CRCL commands.
 *             
 * 
 * <p>Java class for InitCanonType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InitCanonType"&gt;
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
@XmlType(name = "InitCanonType")
public class InitCanonType
    extends CRCLCommandType
{


}
