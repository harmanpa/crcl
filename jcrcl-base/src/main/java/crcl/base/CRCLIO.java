/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crcl.base;

import crcl.base.exceptions.CRCLException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author peter
 */
public class CRCLIO {

    public static CRCLProgramType readProgram(InputStream is) throws CRCLException {
        return read(is, CRCLProgramType.class);
    }

    public static <T extends DataThingType> T read(InputStream is, Class<T> type) throws CRCLException {
        try {
            return JAXBContext.newInstance(ObjectFactory.class)
                    .createUnmarshaller()
                    .unmarshal(XMLInputFactory.newInstance()
                            .createXMLStreamReader(is), type).getValue();
        } catch (JAXBException | XMLStreamException ex) {
            throw new CRCLException("Deserialization failed", ex);
        }
    }

    public static <T extends DataThingType> void write(T object, OutputStream os) throws CRCLException {
        try {
            JAXBContext.newInstance(ObjectFactory.class)
                    .createMarshaller()
                    .marshal(object, XMLOutputFactory.newInstance().createXMLStreamWriter(os));
        } catch (JAXBException | XMLStreamException ex) {
            throw new CRCLException("Serialization failed", ex);
        }
    }
}
