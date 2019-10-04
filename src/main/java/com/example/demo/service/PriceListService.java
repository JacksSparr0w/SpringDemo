package com.example.demo.service;

import com.example.demo.entity.PriceList;
import com.example.demo.entity.Producer;
import com.example.demo.entity.Type;

import java.util.Date;
import java.util.Optional;

public interface PriceListService extends Service<PriceList> {
    Optional<PriceList> findByProducerAndTypeAndDate(Producer producer, Type type, Date date);
}
