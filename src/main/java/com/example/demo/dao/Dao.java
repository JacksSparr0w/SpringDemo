package com.example.demo.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public interface Dao<T> {
    void persist(T entity);

    void update(T entity);

    T findById(Integer id);

    void delete(T entity);

    List<T> findAll();

    void deleteAll();

    void openSession();

    void openTransaction();

    void closeSession();

    void commit();

    void rollBack();

    Session getCurrentSession();

    Transaction getCurrentTransaction();
}
