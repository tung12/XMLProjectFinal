/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;

import dao.CategoryDao;
import dao.ProductDao;
import interfaces.ICategory;
import interfaces.ICrawler;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.math.BigInteger;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import jaxb.category.CategoryItem;
import jaxb.category.ListCategory;
import jaxb.product.ListProduct;
import jaxb.product.ProductImage;
import jaxb.product.ProductItem;
import utils.Utils;

/**
 *
 * @author ntien
 */
public class LimeorangeCrawler extends MainCrawler implements ICategory, ICrawler {

    private String domain;
    ListProduct list = new ListProduct();
    Utils utils = new Utils();

    public LimeorangeCrawler(ServletContext context, String domain) {
        super(context);
        this.domain = domain;
        // TODO Auto-generated constructor stub
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public List<CategoryItem> parseDocumentCategoryByStAX(String document) {
        // TODO Auto-generated method stub        
        List<CategoryItem> result = new ArrayList<>();
        try {
            XMLEventReader eventReader = parseStringtoXMLUseStAX(document);
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    StartElement startElement = (StartElement) event;
                    //System.out.println("Start element:" + startElement.getName());
                    String tagName = startElement.getName().getLocalPart();
                    if (tagName.equals("a")) {
                        Attribute aHref = startElement.getAttributeByName(new QName("href"));
                        String link = this.domain + aHref.getValue();
                        event = (XMLEvent) eventReader.next();
                        Characters name = event.asCharacters();
                        CategoryItem categoryItem = new CategoryItem(name.getData(), link, new Date().toString(), null,
                                new Date().toString(), null, false, null);
                        result.add(categoryItem);
                    }
                }
            }
            return result;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(LimeorangeCrawler.class.getName()).log(Level.SEVERE, "parseDocumentCategoryByStAX", ex);
        } catch (XMLStreamException ex) {
            Logger.getLogger(LimeorangeCrawler.class.getName()).log(Level.SEVERE, "parseDocumentCategoryByStAX", ex);
        }

        return null;
    }

    @Override
    public String getDocumentCategory(String url) {
        BufferedReader reader = null;
        String line = "";
        String document = "";
        boolean tmpStart = false;
        try {
            reader = getBufferdReaderUrl(url);
            while ((line = reader.readLine()) != null) {
                //Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, line);
                if (tmpStart && line.contains("</div>")) {
                    break;
                }
                if (tmpStart) {
                    document += line.trim();

                }
                if (line.contains("<div class=\"col-sm-2 sub-category-wapper\"")) {
                    tmpStart = true;
                }

            }
            document = convertEntityName(document);
            //Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, document);
        } catch (IOException ex) {
            Logger.getLogger(LimeorangeCrawler.class.getName()).log(Level.SEVERE, "getDocumentCategory", ex);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
        return document;
    }

    public void WriteFileForTest(String document)
            throws IOException {

        Writer writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(
                        "C:\\Users\\ntien\\Desktop\\XMLProjectFinal\\test.txt"), "UTF-8"));
        writer.write(document);
        writer.close();
    }

    @Override
    public int getLastPage(String url) {
        int page = 1;
        BufferedReader reader = null;
        String tmpUrl = url + "?page=" + page;
        String document = "";
        String line = "";
        boolean tmpStart = false;
        try {

            reader = getBufferdReaderUrl(tmpUrl);
            boolean isExist = false;
            while ((line = reader.readLine()) != null) {
                if (tmpStart && line.contains("</div>")) {
                    break;
                }
                if (tmpStart) {
                    document += convertEntityName(line.trim());
                }
                if (line.contains("id=\"pagination\"")) {
                    tmpStart = true;
                }
            }
            reader.close();
            //get lastpage
            List<Integer> tmpList = new ArrayList<>();
            XMLEventReader eventReader = parseStringtoXMLUseStAX(document);
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    StartElement startElement = (StartElement) event;
                    //System.out.println("Start element:" + startElement.getName());
                    String tagName = startElement.getName().getLocalPart();
                    if (tagName.equals("a")) {
                        Attribute aHref = startElement.getAttributeByName(new QName("href"));
                        String link = this.domain + aHref.getValue();
                        event = (XMLEvent) eventReader.next();
                        Characters text = null;
                        if (!event.isStartElement()) {
                            text = event.asCharacters();
                            tmpList.add(Integer.parseInt(text.getData()));
                        }
                    }
                }
            }
            if (!tmpList.isEmpty()) {
                return Collections.max(tmpList).intValue();
            } else {
                return page;
            }

        } catch (IOException ex) {
            Logger.getLogger(LimeorangeCrawler.class.getName()).log(Level.SEVERE, "getLastPage", ex);
        } catch (XMLStreamException ex) {
            Logger.getLogger(LimeorangeCrawler.class.getName()).log(Level.SEVERE, "getLastPage", ex);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }

        return page;
    }

    @Override
    public String getDocumentProduct(String url) {
        BufferedReader reader = null;
        String line = "";
        String document = "<document>";
        boolean tmpStart = false;
        try {

            reader = getBufferdReaderUrl(url);
            while ((line = reader.readLine()) != null) {
                //Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, line);                
                if (tmpStart && line.contains("</ul>")) {
                    break;
                }
                if (tmpStart) {

                    if (!line.contains("<meta") && !line.contains("<a class=\"add-to-car\"")
                            && !line.contains("<a class=\"sold_out\"") && !line.trim().isEmpty()) {
//                        if (line.contains("<img")) {
//                            //document += utils.fixWellFormed(line);
//                            System.out.println(line);
//                            
//                        }
                        document += line.trim();

                    }

                }
                if (line.contains("class=\"row product-list grid filter\"")) {
                    tmpStart = true;
                }
            }
            document += "</document>";
            document = convertEntityName(document);
            //document = utils.fixWellFormed(document);

            return document;
            //Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, document);
        } catch (IOException ex) {
            Logger.getLogger(LimeorangeCrawler.class.getName()).log(Level.SEVERE, "getDocumentProduct", ex);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean crawlProduct() {
        ListProduct products = new ListProduct();
        ListCategory categorys = new ListCategory();
        //crawl category 
        categorys.setCategory(parseDocumentCategoryByStAX(getDocumentCategory(domain)));
        CategoryDao categoryDao = new CategoryDao();
        categorys.setCategory(categoryDao.saveCategory(categorys.getCategory()));
        categorys.getCategory().stream().forEach(category -> {
            System.out.println("id:" + category.getId());
        });

        //crawl product
        categorys.getCategory().stream().parallel().forEach(category -> {
            int lastPage = getLastPage(category.getDomain());
            List<String> tmpListUrl = new ArrayList<>();
            for (int i = 1; i < lastPage + 1; i++) {
                String tmpUrlProduct = category.getDomain() + "?page=" + i;
                tmpListUrl.add(tmpUrlProduct);
            }
            tmpListUrl.stream().parallel().forEach(productUrl -> {
                String document = getDocumentProduct(productUrl);                               
                products.getProduct().addAll(parseProductByStAX(document, category.getId().toString()));
            });
        });

        System.out.println("Size:" + products.getProduct().size());
        try {

            //products.setProduct(parseProductByStAX(document));
            JAXBContext ct = JAXBContext.newInstance(ListProduct.class);
            Marshaller marshaller = ct.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(products, stringWriter);
            // WriteFileForTest(stringWriter.toString());
            Utils util = new Utils();
            ProductDao productDao = new ProductDao();
            boolean validate = util.validateXmlSchema("C:\\Users\\ntien\\Desktop\\XMLProjectFinal\\web\\WEB-INF\\schema\\productXmlSchema.xsd", stringWriter.toString());
            if (validate) {
                productDao.saveProductToDB(products.getProduct());
                return true;
            }

        } catch (JAXBException ex) {
            Logger.getLogger(LimeorangeCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<ProductItem> parseProductByStAX(String document, String idCategory) {
        List<ProductItem> listProduct = new ArrayList<>();
        String urlImg = "";
        String nameProduct = "";
        String price = "";
        String urlDetail = "";
        try {
            XMLEventReader eventReader = parseStringtoXMLUseStAX(document);
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    StartElement startElement = (StartElement) event;
                    //System.out.println("Start element:" + startElement.getName());
                    Characters characters = null;
                    Attribute attribute = null;
                    String tagName = startElement.getName().getLocalPart();
                    if (tagName.equals("div")) {
                        attribute = startElement.getAttributeByName(new QName("class"));
                        if (attribute.getValue().equals("left-block")) {
                            event = (XMLEvent) eventReader.next();
                            startElement = event.asStartElement();
                            //get <a> have url detail
                            attribute = startElement.getAttributeByName(new QName("href"));
                            urlDetail = this.domain + attribute.getValue();
                            //get  url image
                            event = (XMLEvent) eventReader.next();
                            startElement = event.asStartElement();
                            attribute = startElement.getAttributeByName(new QName("src"));
                            urlImg = attribute.getValue();
                        }

                        if (attribute.getValue().equals("right-block")) {
                            eventReader.next();
                            eventReader.next();
                            event = (XMLEvent) eventReader.next();
                            characters = event.asCharacters();
                            //get name product                   
                            nameProduct = characters.getData();
                            //get  price
                            eventReader.next();
                            eventReader.next();
                            eventReader.next();
                            eventReader.next();
                            event = (XMLEvent) eventReader.next();
                            characters = event.asCharacters();
                            price = characters.getData();

                            ProductItem product = new ProductItem();
                            product.setName(nameProduct);
                            product.setUrlDetail(urlDetail);
                            product.setImage(urlImg);
                            product.setPrice(parsePrice(price));
                            product.setDomain(this.domain);
                            product.setIdCategory(idCategory);
                            product.setCreatedDate(new Date().toString());
                            product.setIsDeleted(false);
                            listProduct.add(product);

                        }
                    }
                }
            }
            
            listProduct.stream().parallel().forEach(product -> {
                for (Map.Entry<String, String> entry : parseDetailByStAX(getDocumentDetail(product.getUrlDetail())).entrySet()) {
                    ProductImage productImage = new ProductImage(null, null, null, null, null, new Date().toString(), false);
                    String key = entry.getKey();
                    String value = entry.getValue();

                    if (value.equals("img")) {
                        //System.out.println("ProductName:"+product.getName()+" image:"+key);
                        productImage.setUrl(key);
                        product.getImageList().add(productImage);
                    }
                    if (value.equals("discription")) {
                        product.setDiscription(key);
                    }
                }
                System.out.println("ProductName:"+product.getName()+"\n");
                product.getImageList().stream().forEach(action->{
                    System.out.println("Image:"+action.getUrl()+"\n");
                });
                System.out.println("\n");
                
            });

            return listProduct;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(LimeorangeCrawler.class.getName()).log(Level.SEVERE, "parseProductByStAX", ex);
        } catch (XMLStreamException ex) {
            Logger.getLogger(LimeorangeCrawler.class.getName()).log(Level.SEVERE, "parseProductByStAX", ex);
        }
        return null;
    }

    @Override
    public String getDocumentDetail(String url) {
        BufferedReader reader = null;
        String line = "";
        String document = "<document>";
        boolean tmpStart = false;
        boolean tmpPause = false;
        boolean tmpStop = false;
        String result = "";
        try {

            reader = getBufferdReaderUrl(url);
            while ((line = reader.readLine()) != null) {
                //Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, line);               
                if (line.contains("</div>") && !tmpPause && tmpStart && tmpStop) {
                    break;
                }
                if (line.contains("</div>") && tmpStart) {
                    tmpPause = true;
                    tmpStart = false;
                }
                if (tmpStart) {
                    document += line.trim();
                }

                if (line.contains("<div id=\"slider-thumb\"")) {
                    tmpStart = true;
                }
                if (line.contains("<div id=\"product-detail\" class=\"tab-panel active\">")) {
                    tmpStart = true;
                    tmpPause = false;
                    tmpStop = true;
                }
            }
            document += "</div></document>";
            document = convertEntityName(document);
            return document;

            //Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, document);
        } catch (IOException ex) {
            Logger.getLogger(LimeorangeCrawler.class.getName()).log(Level.SEVERE, "getDocumentDetail", ex);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Map<String, String> parseDetailByStAX(String document) {
        Map<String, String> map = new HashMap<>();
        String result = "";
        try {
            boolean isFind = false;
            XMLEventReader eventReader = parseStringtoXMLUseStAX(document);
            while (eventReader.hasNext()) {
                XMLEvent event = (XMLEvent) eventReader.next();
                if (event.isStartElement()) {
                    StartElement startElement = (StartElement) event;
                    //System.out.println("Start element:" + startElement.getName());
                    Characters characters = null;
                    Attribute attribute = null;
                    String tagName = startElement.getName().getLocalPart();
                    if (tagName.equals("div")) {
                        attribute = startElement.getAttributeByName(new QName("class"));
                        if (attribute != null) {
                            if (attribute.getValue().equals("des")) {
                                isFind = true;
                            }
                        }

                    }
                    if (tagName.equals("img")) {
                        map.put(startElement.getAttributeByName(new QName("src")).getValue(), "img");
                    }

                }
                if (event.isCharacters()) {
                    result += event.asCharacters().getData();
                }
                if (isFind && event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    String tagName = endElement.getName().getLocalPart();
                    if (tagName.equals("div")) {
                        break;
                    }
                }
            }
            map.put(result, "discription");
            return map;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(LimeorangeCrawler.class.getName()).log(Level.SEVERE, "parseDetailByStAX", ex);
        } catch (XMLStreamException ex) {
            Logger.getLogger(LimeorangeCrawler.class.getName()).log(Level.SEVERE, "parseDetailByStAX", ex);
        }
        return null;
    }
}
