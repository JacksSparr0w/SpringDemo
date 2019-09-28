package com.example.demo.repository;

import com.example.demo.entity.Producer;
import com.example.demo.entity.Product;
import com.example.demo.entity.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductionRepository extends JpaRepository<Production, Integer> {
    List<Production> findAllByProducerOrderByProduct(Producer producer);

    List<Production> findAllByProduct(Product product);
}
