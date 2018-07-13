
package jaxb.newsimage;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the jaxb.newsimage package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Images_QNAME = new QName("http://xml.netbeans.org/schema/newsImage", "images");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: jaxb.newsimage
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ListImages }
     * 
     */
    public ListImages createListImages() {
        return new ListImages();
    }

    /**
     * Create an instance of {@link NewsImage }
     * 
     */
    public NewsImage createNewsImage() {
        return new NewsImage();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListImages }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xml.netbeans.org/schema/newsImage", name = "images")
    public JAXBElement<ListImages> createImages(ListImages value) {
        return new JAXBElement<ListImages>(_Images_QNAME, ListImages.class, null, value);
    }

}
