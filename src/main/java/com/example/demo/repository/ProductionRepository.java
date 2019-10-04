package com.example.demo.repository;

import com.example.demo.entity.Producer;
import com.example.demo.entity.Product;
import com.example.demo.entity.Production;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductionRepository extends JpaRepository<Production, Integer> {

    List<Production> findAllByProducer(Producer producer, Sort sort);

    @Query(nativeQuery = true, value = "SELECT * FROM production p WHERE p.product_id = :id")
    List<Production> findAllByProduct(@Param("id") Integer id);

}
