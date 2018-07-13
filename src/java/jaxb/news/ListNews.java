
package jaxb.news;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for listNews complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="listNews">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="new" type="{http://xml.netbeans.org/schema/news}newsItem" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listNews", propOrder = {
    "_new"
})
@XmlRootElement(name="news")
public class ListNews {

    @XmlElement(name = "new", required = true)
    protected List<NewsItem> _new;

    /**
     * Gets the value of the new property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the new property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNew().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NewsItem }
     * 
     * 
     */
    public List<NewsItem> getNew() {
        if (_new == null) {
            _new = new ArrayList<NewsItem>();
        }
        return this._new;
    }

    public void setNew(List<NewsItem> _new) {
        this._new = _new;
    }

}
