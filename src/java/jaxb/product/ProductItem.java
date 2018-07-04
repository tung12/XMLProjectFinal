
package jaxb.product;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlTransient;
/**
 * <p>Java class for productItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="productItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="image" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="discription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="domain" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *       &lt;attribute name="idCategory" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@Entity
@Table(name = "Product")
//  uniqueConstraints = { @UniqueConstraint(columnNames = { "DEPT_NO" }) })
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "productItem", propOrder = {
    "name",
    "image",
    "price",
    "discription",
    "domain",
    "urlDetail",
    "lastModifiedDate",
    "lastModifiedBy",
    "createdDate",
    "createdBy",
    "isDeleted"
    
})
public class ProductItem {

    @XmlElement(required = true)
    @Column(name = "name")
    protected String name;
    @XmlElement(required = true)
    @Column(name = "image")
    protected String image;
    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    @Column(name = "price")
    protected BigInteger price;
    @XmlElement(required = true)
    @Column(name = "discription")
    protected String discription;
    @XmlElement(required = true)
    @Column(name = "domain")
    protected String domain;
    @XmlElement(required = true)
    @Column(name = "urlDetail")
    protected String urlDetail;
    @XmlAttribute(name = "id")
    @XmlSchemaType(name = "positiveInteger")
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    protected BigInteger id;
    @Column(name = "idCategory")
    @XmlAttribute(name = "idCategory", required = true)
    protected String idCategory;
    @Column(name = "lastModifiedDate")
    @XmlElement(name="lastModifiedDate",required = false,nillable = true)
    protected String lastModifiedDate;
    @Column(name = "lastModifiedBy")
    @XmlElement(name="lastModifiedBy",required = false,nillable = true)
    protected String lastModifiedBy;
    @Column(name = "createdDate")
    @XmlElement(name="createdDate",required = false,nillable = true)
    protected String createdDate;
    @Column(name = "createdBy")
    @XmlElement(name="createdBy",required = false,nillable = true)
    protected String createdBy;
    @Column(name = "isDeleted")
    @XmlElement(name="isDeleted",required = false,nillable = true)
    protected boolean isDeleted;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product", fetch = FetchType.EAGER)
    @XmlTransient
     public List<ProductImage> imageList;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    public ProductItem() {
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the image property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the value of the image property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImage(String value) {
        this.image = value;
    }

    /**
     * Gets the value of the price property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPrice(BigInteger value) {
        this.price = value;
    }

    /**
     * Gets the value of the discription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiscription() {
        return discription;
    }

    /**
     * Sets the value of the discription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscription(String value) {
        this.discription = value;
    }

    /**
     * Gets the value of the domain property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDomain() {
        return domain;
    }

    /**
     * Sets the value of the domain property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDomain(String value) {
        this.domain = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setId(BigInteger value) {
        this.id = value;
    }

    /**
     * Gets the value of the idCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdCategory() {
        return idCategory;
    }

    /**
     * Sets the value of the idCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdCategory(String value) {
        this.idCategory = value;
    }

    public List<ProductImage> getImageList() {
        if (imageList == null) {
            imageList = new ArrayList<>();
        }
        return imageList;
    }

    public void setImageList(List<ProductImage> imageList) {
        this.imageList = imageList;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getUrlDetail() {
        return urlDetail;
    }

    public void setUrlDetail(String urlDetail) {
        this.urlDetail = urlDetail;
    }

    @Override
    public String toString() {
        return "ProductItem{" + "name=" + name + ", image=" + image + ", price=" + price + ", discription=" + discription + ", domain=" + domain + ", urlDetail=" + urlDetail + ", id=" + id + ", idCategory=" + idCategory + ", lastModifiedDate=" + lastModifiedDate + ", lastModifiedBy=" + lastModifiedBy + ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", isDeleted=" + isDeleted + ", imageList=" + imageList + '}';
    }
    
    
}
