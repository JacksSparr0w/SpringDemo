package com.example.demo.service;

import com.example.demo.entity.Producer;
import com.example.demo.entity.Product;
import com.example.demo.entity.Production;

import java.util.List;

public interface ProductionService extends Service<Production> {
    List<Production> findAllByProducerOOrderByProduct(Producer producer);

    List<Production> findAllByProduct(Product product);
}
