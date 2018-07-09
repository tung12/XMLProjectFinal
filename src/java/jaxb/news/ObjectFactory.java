
package jaxb.news;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the jaxb.news package. 
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

    private final static QName _News_QNAME = new QName("http://xml.netbeans.org/schema/news", "news");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: jaxb.news
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ListNews }
     * 
     */
    public ListNews createListNews() {
        return new ListNews();
    }

    /**
     * Create an instance of {@link NewsItem }
     * 
     */
    public NewsItem createNewsItem() {
        return new NewsItem();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListNews }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xml.netbeans.org/schema/news", name = "news")
    public JAXBElement<ListNews> createNews(ListNews value) {
        return new JAXBElement<ListNews>(_News_QNAME, ListNews.class, null, value);
    }

}
