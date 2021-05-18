//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.05.18 at 02:21:35 PM BST 
//


package crcl.base;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;


/**
 * <p>Java class for TorqueUnitEnumType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TorqueUnitEnumType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN"&gt;
 *     &lt;enumeration value="newtonMeter"/&gt;
 *     &lt;enumeration value="footPound"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "TorqueUnitEnumType")
@XmlEnum
public enum TorqueUnitEnumType {

    @XmlEnumValue("newtonMeter")
    NEWTON_METER("newtonMeter"),
    @XmlEnumValue("footPound")
    FOOT_POUND("footPound");
    private final String value;

    TorqueUnitEnumType(String v) {
        value = v;
    }

    @JsonValue
    public String value() {
        return value;
    }

    @JsonCreator
    public static TorqueUnitEnumType fromValue(String v) {
        for (TorqueUnitEnumType c: TorqueUnitEnumType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
