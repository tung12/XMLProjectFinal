/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jaxb.user.UserItem;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.Connections;

/**
 *
 * @author ntien
 */
public class UserAccountDao {

    private static final String ATT_NAME_USER_NAME_COOKIE = "USER_NAME_IN_COOKIE";
    SessionFactory factory = Connections.getSessionFactory();
    Session session = factory.getCurrentSession();

    public void storeLoginedUser(HttpSession session, UserItem loginedUser) {
        session.setAttribute("loginedUser", loginedUser);
    }

    public UserItem getLoginedUser(HttpSession session) {
        UserItem loginedUser = (UserItem) session.getAttribute("loginedUser");
        return loginedUser;
    }

    public void storeUserCookie(HttpServletResponse response, UserItem user) {
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME_COOKIE, user.getUserName());
        cookieUserName.setMaxAge(24 * 60 * 60);
        response.addCookie(cookieUserName);
    }

    public String getUserNameInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ATT_NAME_USER_NAME_COOKIE.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public void deleteUserCookie(HttpServletResponse response) {
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME_COOKIE, null);
        cookieUserName.setMaxAge(0);
        response.addCookie(cookieUserName);
    }

    public UserItem findUser(String userName) {
        try {
            session = session.getSessionFactory().openSession();
            session.getTransaction().begin();
            Query query = session.createQuery("from UserItem where userName = :userName");
            query.setParameter("userName", userName);
            UserItem result = (UserItem) query.uniqueResult();
            session.getTransaction().commit();
            return result;
        } catch (HibernateException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
            session.getTransaction().rollback();
        }
        return null;
    }

    public  UserItem findUser(String userName, String password) {
        try {
            session = session.getSessionFactory().openSession();
            session.getTransaction().begin();
            Query query = session.createQuery("from UserItem where userName = :userName AND password = :password");
            query.setParameter("userName", userName);
            query.setParameter("password", password);
            UserItem result = (UserItem) query.uniqueResult();
            session.getTransaction().commit();
            return result;
        } catch (HibernateException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
            session.getTransaction().rollback();
        }
        return null;
    }
}
