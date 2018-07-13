/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.math.BigInteger;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jaxb.news.NewsItem;
import utils.PaginationHandler;

/**
 *
 * @author ntien
 */
public class NewsDao extends BaseDao<NewsItem, BigInteger> {

    protected Class entityClass;

    public NewsDao() {
        this.entityClass = NewsItem.class;
        setTClass(entityClass);
    }

    public List<NewsItem> getAllNews() {
        return getList();
    }

    public PaginationHandler<NewsItem> getAllNews(Integer page, Integer maxResult, Integer maxNavigationResult) {
        String sql = "From " + NewsItem.class.getName();
        return getPaginationResult(sql, page, maxResult, maxNavigationResult);
    }

    public NewsItem getNewsById(BigInteger id) {
        return getByID(id);
    }

    public BigInteger addNews(NewsItem newsItem) {
        try {
            session = session.getSessionFactory().openSession();
            session.getTransaction().begin();
            newsItem.getImageList().stream().forEach(image -> {
                image.setNews(newsItem);
            });
            BigInteger id = (BigInteger) session.save(newsItem);
            session.getTransaction().commit();
            return id;
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
            session.getTransaction().rollback();
        }
        return null;
    }

}
