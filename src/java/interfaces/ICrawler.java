/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;



import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import jaxb.product.ProductItem;


/**
 *
 * @author ntien
 */
public interface ICrawler {
    public int getLastPage(String url);
    public String getDocumentProduct(String url);
    public boolean crawlProduct();
    public List<ProductItem> parseProductByStAX(String document,String idCategory);
    public String getDocumentDetail(String url);
    public Map<String,String> parseDetailByStAX(String document);
}
