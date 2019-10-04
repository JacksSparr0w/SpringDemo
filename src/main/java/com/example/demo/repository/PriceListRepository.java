package com.example.demo.repository;

import com.example.demo.entity.PriceList;
import com.example.demo.entity.Producer;
import com.example.demo.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface PriceListRepository extends JpaRepository<PriceList, Integer> {

    @Query("SELECT pl FROM PriceList pl WHERE pl.type = :_type AND pl.producer = :producer AND :date BETWEEN pl.dateFrom AND pl.dateTo")
    Optional<PriceList> findByProducerAndTypeAndDate
            (@Param("producer") Producer producer, @Param("_type") Type type, @Param("date") Date date);

}
