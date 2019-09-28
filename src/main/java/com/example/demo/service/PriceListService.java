package com.example.demo.service;

import com.example.demo.entity.PriceList;

import java.util.Date;
import java.util.Optional;

public interface PriceListService extends Service<PriceList>{
    Optional<PriceList> findFirstByDateFromBeforeDateAndDateToAfter(Date date);
}
