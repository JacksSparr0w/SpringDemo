package com.example.demo.dao.impl;

import com.example.demo.dao.ProductDao;
import com.example.demo.entity.Product;
import com.example.demo.util.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    public ProductDaoImpl() {
        sessionFactory = HibernateSessionFactory.getSessionFactory();
    }

    @Override
    public void persist(Product entity) {
        getCurrentSession().save(entity);
    }

    @Override
    public void update(Product entity) {
        getCurrentSession().update(entity);
    }

    @Override
    public Product findById(Integer id) {
        return getCurrentSession().get(Product.class, id);
    }

    @Override
    public void delete(Product entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    public List<Product> findAll() {
        return (List<Product>) getCurrentSession().createQuery("FROM product").list();
    }

    @Override
    public void deleteAll() {
        List<Product> products = findAll();
        for (Product product : products) {
            delete(product);
        }
    }

    @Override
    public void openSession() {
        session = sessionFactory.openSession();
    }

    @Override
    public void openTransaction() {
        transaction = session.beginTransaction();
    }

    @Override
    public void closeSession() {
        session.close();
    }

    @Override
    public void commit() {
        transaction.commit();
    }

    @Override
    public void rollBack() {
        transaction.rollback();
    }

    @Override
    public Session getCurrentSession() {
        if (session == null){
            openSession();
        }
        return session;
    }

    @Override
    public Transaction getCurrentTransaction() {
        if (transaction == null) {
            openTransaction();
        }
        return transaction;
    }
}
