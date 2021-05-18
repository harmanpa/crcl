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
 *                 RunProgramType is derived from MiddleCommandType.
 *                 An instance of RunProgramType has the following
 *                 elements:
 *                 Name (inherited, optional)
 *                 CommandID (inherited)
 *                 ProgramText.
 * 
 *                 The RunProgramType is used to instruct the low level controller to
 *                 run a program written in a non-CRCL language that controller
 *                 understands. The ProgramText element gives the text of the program.
 *             
 * 
 * <p>Java class for RunProgramType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RunProgramType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{}MiddleCommandType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ProgramText" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RunProgramType", propOrder = {
    "programText"
})
public class RunProgramType
    extends MiddleCommandType
{

    @XmlElement(name = "ProgramText", required = true)
    protected String programText;

    /**
     * Gets the value of the programText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProgramText() {
        return programText;
    }

    /**
     * Sets the value of the programText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProgramText(String value) {
        this.programText = value;
    }

}
