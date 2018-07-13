/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxb.product;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author ntien
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listImageProduct", propOrder = {
    "productImage"
})
@XmlRootElement( name = "images")
public class ListImageProduct {
    @XmlElement(name="image",required = true)
    protected List<ProductImage> productImage;

    /**
     * Gets the value of the product property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the product property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProduct().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProductItem }
     *
     *
     */
    public List<ProductImage> getProductImage() {
        if (productImage == null) {
            productImage = new ArrayList<ProductImage>();
        }
        return this.productImage;
    }

    public void setProductImage(List<ProductImage> productImage) {
        this.productImage = productImage;
    }

    
}
