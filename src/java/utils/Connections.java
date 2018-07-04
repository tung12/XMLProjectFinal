/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.imageio.spi.ServiceRegistry;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author ntien
 */
public class Connections {

    private static final SessionFactory sessionFactory = buildSession();

    private static SessionFactory buildSession() {
        try {
            Configuration configuration = new Configuration().configure("/config/hibernate.cfg.xml");
            final StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            final SessionFactory factory = configuration.buildSessionFactory(builder.build());
            final Session session = factory.openSession();

            return session.getSessionFactory();
        } catch (Throwable ex) {

            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Giải phóng cache và Connection Pools.
        getSessionFactory().close();
    }
}
