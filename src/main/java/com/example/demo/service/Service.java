package com.example.demo.service;

import java.util.List;
import java.util.Optional;

public interface Service<T> {

    T save(T entity);

    Optional<T> findById(Long id);

    boolean existsById(Long id);

    List<T> findAll();

    void deleteById(Long id);

    void delete(T entity);

}
