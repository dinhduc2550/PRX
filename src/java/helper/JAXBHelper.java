/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javax.xml.transform.dom.DOMResult;


/**
 *
 * @author Thanh Dang
 * @param <T>
 */
public class JAXBHelper {

    private final JAXBContext JAXB_CONTEXT;

    public JAXBHelper(Class clzz) throws JAXBException {
        JAXB_CONTEXT = JAXBContext.newInstance(clzz);
    }

    public <T> T readXml(String file) throws JAXBException {
        Unmarshaller jaxbUnmarshaller = JAXB_CONTEXT.createUnmarshaller();
        return (T) jaxbUnmarshaller.unmarshal(new File(file));
    }

    public boolean writeXml(String file, Object input) {
        try {

            Marshaller jaxbMarshaller = JAXB_CONTEXT.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            
            new File(file).createNewFile();
            jaxbMarshaller.marshal(input, new File(file));
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
