/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxb.product;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author ntien
 */
@Entity
@Table(name = "image")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "productImage", propOrder = {
    "url",
    "lastModifiedDate",
    "lastModifiedBy",
    "createdDate",
    "createdBy",
    "isDeleted"
    
})
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlAttribute(name = "id")
    @XmlSchemaType(name = "positiveInteger")
    protected Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productId",nullable = false,insertable=true, updatable=false)
    @XmlTransient
    protected ProductItem product;
    @Column(name = "url")
    protected String url;
    @Column(name = "lastModifiedDate")
    protected String lastModifiedDate;
    @Column(name = "lastModifiedBy")
    protected String lastModifiedBy;
    @Column(name = "createdDate")
    protected String createdDate;
    @Column(name = "createdBy")
    protected String createdBy;
    @Column(name = "isDeleted")
    protected boolean isDeleted;

    public ProductImage() {
    }

    public ProductImage(Long id, String url, String lastModifiedBy, String lastModifiedDate, String createdBy, String createdDate, boolean isDeleted) {
        this.id = id;
        this.url = url;
        this.lastModifiedBy = lastModifiedBy;
        this.createdBy = createdBy;
        this.isDeleted = isDeleted;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductItem getProduct() {
        return product;
    }

    public void setProduct(ProductItem product) {
        this.product = product;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    @Override
    public String toString() {
        return "ProductImage{" + "id=" + id + ", product=" + product + ", url=" + url + ", lastModifiedDate=" + lastModifiedDate + ", lastModifiedBy=" + lastModifiedBy + ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", isDeleted=" + isDeleted + '}';
    }

}
