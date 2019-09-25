package com.example.demo.util;

import com.example.demo.entity.Producer;
import com.example.demo.entity.Product;
import com.example.demo.entity.Production;
import com.example.demo.entity.Type;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class HibernateSessionFactory {
    private static AtomicReference<SessionFactory> sessionFactoryReference = new AtomicReference<>();
    private static Lock lock = new ReentrantLock();

    private HibernateSessionFactory() {

    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactoryReference.get() == null) {
            lock.lock();
            if (sessionFactoryReference.get() == null) {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Type.class);
                configuration.addAnnotatedClass(Product.class);
                configuration.addAnnotatedClass(Producer.class);
                configuration.addAnnotatedClass(Production.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
                builder.applySettings(configuration.getProperties());
                sessionFactoryReference.set(configuration.buildSessionFactory(builder.build()));
            }
        }
        return sessionFactoryReference.get();
    }
}
