/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.math.BigInteger;
import java.util.List;
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
    
    
    public List<NewsItem> getAllNews(){
        return getList();
    }
    public PaginationHandler<NewsItem> getAllNews(Integer page, Integer maxResult, Integer maxNavigationResult) {
        String sql = "From " + NewsItem.class.getName();
        return getPaginationResult(sql, page, maxResult, maxNavigationResult);
    }
    public NewsItem getNewsById(BigInteger id) {
        return getByID(id);
    }
}
