package com.example.demo.service.impl;

import com.example.demo.entity.Producer;
import com.example.demo.entity.Product;
import com.example.demo.entity.Production;
import com.example.demo.repository.ProductionRepository;
import com.example.demo.service.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductionServiceImpl implements ProductionService {
    @Autowired
    private ProductionRepository repository;

    @Override
    public List<Production> findAllByProducerOOrderByProduct(Producer producer) {
        return repository.findAllByProducerOrderByProduct(producer);
    }

    @Override
    public List<Production> findAllByProduct(Product product) {
        return repository.findAllByProduct(product);
    }

    @Override
    public Production save(Production entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<Production> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    @Override
    public List<Production> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(Production entity) {
        repository.delete(entity);
    }
}
