/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import crawler.LimeorangeCrawler;
import interfaces.IDao;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.catalina.loader.ParallelWebappClassLoader;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import utils.Connections;
import utils.PaginationHandler;

/**
 *
 * @author ntien
 */
@SuppressWarnings("unchecked")
public class BaseDao<T, PK extends Serializable> implements IDao {

    SessionFactory factory = Connections.getSessionFactory();
    Session session = factory.getCurrentSession();
    protected Class entityClass;

    public BaseDao() {

    }

    public void setTClass(final Class clazz) {
        // TODO Setting entity class
        this.entityClass = clazz;
    }

    public T getByID(PK id) {
        try {
            session = session.getSessionFactory().openSession();
            session.getTransaction().begin();
            T user = (T)session.get(entityClass, id );
            session.getTransaction().commit();
            return user;
        } catch (HibernateException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
            session.getTransaction().rollback();
        }

        return null;
    }

    public List<T> getList() {
        try {
            session = session.getSessionFactory().openSession();
            session.getTransaction().begin();
            List<T> result = (List<T>) session.createQuery("from " + entityClass.getName())
                    .list();
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
            session.getTransaction().rollback();
        }

        return null;
    }

    public PaginationHandler<T> getPaginationResult(String sql, Integer page, Integer maxResult, Integer maxNavigationResult) {
        try {
            session = session.getSessionFactory().openSession();
            session.getTransaction().begin();
            Query query = session.createQuery(sql);
            PaginationHandler<T> result
                    = new PaginationHandler<T>(query, page, maxResult, maxNavigationResult);
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
            session.getTransaction().rollback();
        }

        return null;
    }

    public Long count() {
        try {
            session = session.getSessionFactory().openSession();
            session.getTransaction().begin();
            Long result = (Long) session
                    .createCriteria(entityClass)
                    .setProjection(Projections.rowCount())
                    .uniqueResult();
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
            session.getTransaction().rollback();
        }

        return null;
    }

    public List search(String parameter) {
        // TODO For search purpose
//        Criteria criteria = getCurrentSession().createCriteria(clazz);
//        Set fieldName = parameterMap.keySet();
//        for (String field : fieldName) {
//            criteria.add(Restrictions.ilike(field, parameterMap.get(field)));
//        }
        return null;
    }

    public PK insert(T entity) {
        try {
            session = session.getSessionFactory().openSession();
            session.getTransaction().begin();
            PK id = (PK) session.save(entity);
            session.getTransaction().commit();
            //session.close();
            return (PK) id;
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
            session.getTransaction().rollback();
        }

        return null;
    }

    public void update(T entity) {
        getCurrentSession().update(entity);
    }

    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

    public void deleteById(PK id) {
        delete(getByID(id));
    }

//    protected DetachedCriteria createDetachedCriteria() {
//        return DetachedCriteria.forClass(clazz);
//    }
// 
    private Session getCurrentSession() {
        return factory.getCurrentSession();
    }

    @Override
    public void saveCrawlerToDB(List list) {
        try {
            session = session.getSessionFactory().openSession();
            session.getTransaction().begin();
            for (int i = 0; i < list.size(); i++) {
                session.save(list.get(i));
            }

            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Parser: " + list.size() + " is parsed");
            session.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
            session.getTransaction().rollback();
        }

    }

    @Override
    public boolean validateExist(String parameter) {
        try {
            session = session.getSessionFactory().openSession();
            session.getTransaction().begin();
            Criteria criteria = session.createCriteria(entityClass);
            T object = (T) criteria.add(Restrictions.eq("name", parameter)).uniqueResult();

            session.getTransaction().commit();
            //session.close();
            if (object == null) {
                return true;
            }

        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
            session.getTransaction().rollback();
        }

        return false;
    }

}
