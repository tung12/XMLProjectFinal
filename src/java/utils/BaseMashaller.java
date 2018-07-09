/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.OutputStream;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import jaxb.product.ListProduct;
import servlets.admin.ProductServlet;

/**
 *
 * @author ntien
 * @param <T>
 * @param <P>
 */
public class BaseMashaller {
  

    public BaseMashaller() {
       
    }
   
    public void marshalToString(Class zclass,Object list , StringWriter stringWriter){
        JAXBContext jaxbContext;       
        try {
            jaxbContext = JAXBContext.newInstance(zclass);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            //jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //jaxbMarshaller.setProperty(Marshaller., "");
            jaxbMarshaller.marshal(list, stringWriter);
        } catch (JAXBException ex) {
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public void marshalToOutputStream(Class zclass,Object list , OutputStream outputStream) throws InstantiationException, IllegalAccessException{
        JAXBContext jaxbContext;       
        try {
            zclass = zclass.newInstance().getClass();
            jaxbContext = JAXBContext.newInstance(zclass);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            //jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //jaxbMarshaller.setProperty(Marshaller., "");
            jaxbMarshaller.marshal(list, outputStream);
        } catch (JAXBException ex) {
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
