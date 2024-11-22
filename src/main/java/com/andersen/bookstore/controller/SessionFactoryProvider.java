package com.andersen.bookstore.controller;


import com.andersen.bookstore.model.Book;
import com.andersen.bookstore.model.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class SessionFactoryProvider {

    private static SessionFactory sessionFactory;

    public static Session getSession() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(Book.class);
            configuration.addAnnotatedClass(Order.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        }
        return sessionFactory.openSession();
    }
}
