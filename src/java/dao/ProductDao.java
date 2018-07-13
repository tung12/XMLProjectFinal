/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jaxb.product.ProductImage;
import jaxb.product.ProductItem;
import org.hibernate.SQLQuery;
//import org.apache.lucene.search.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
//import org.hibernate.search.FullTextSession;
//import org.hibernate.search.Search;
//import org.hibernate.search.jpa.FullTextEntityManager;
//import org.hibernate.search.query.dsl.QueryBuilder;
import utils.Connections;
import utils.PaginationHandler;

/**
 *
 * @author ntien
 */
public class ProductDao extends BaseDao<ProductItem, BigInteger> {

    protected Class entityClass;
    SessionFactory factory = Connections.getSessionFactory();
    Session session = factory.getCurrentSession();

    public ProductDao() {
        this.entityClass = ProductItem.class;
        setTClass(entityClass);
    }

    public void saveProductToDB(List<ProductItem> list) {
        try {
            session = session.getSessionFactory().openSession();
            session.getTransaction().begin();
            List<ProductItem> listProductInDB = getList();
            List<ProductItem> result = new ArrayList<>();
            list.stream().forEach(product1 -> {
                if (!listProductInDB.stream().anyMatch(e -> e.getName().contains(product1.getName()))) {
                    //System.out.println("Product: " + product1.getName() + " NOT EXIST IN DB  ");                   
                    //setParentProduct(product1.getImageList(), product1);                   
                    product1.getImageList().stream().forEach(image -> {
                        image.setProduct(product1);
                        System.out.println(image.getUrl());
                    });
                    session.save(product1);
                } else {
                    //System.out.println("Product: " + product1.getName() + " EXIST IN DB  ");
                }
            });
            session.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
            session.getTransaction().rollback();
        }
    }

    public PaginationHandler<ProductItem> getAllProduct(Integer page, Integer maxResult, Integer maxNavigationResult) {
        String sql = "From " + ProductItem.class.getName();
        return getPaginationResult(sql, page, maxResult, maxNavigationResult);
    }

    public List<ProductItem> getAllProduct() {
        return getList();
    }

    public List<ProductItem> searchByName(String keyWords) throws Exception {
        session = session.getSessionFactory().openSession();
        session.getTransaction().begin();
        String sql="select * from product where name LIKE '%"+keyWords+"%'";
        SQLQuery query = session.createSQLQuery(sql).addEntity(entityClass);  
        query.setMaxResults(10);
        return (List<ProductItem>)query.list();
    }

    public ProductItem getProductById(BigInteger id) {
        return getByID(id);
    }
    
    public List<ProductImage> getImageProduct(BigInteger id){
        session = session.getSessionFactory().openSession();
        session.getTransaction().begin();
        List<ProductImage> result = session.createCriteria(ProductImage.class)
                .add(Restrictions.eq("productId", id)).list();
        return result;
    }
}
