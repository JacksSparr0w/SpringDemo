package com.example.demo.service.impl;

import com.example.demo.entity.PriceList;
import com.example.demo.entity.Producer;
import com.example.demo.entity.Type;
import com.example.demo.repository.PriceListRepository;
import com.example.demo.service.PriceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PriceListServiceImpl implements PriceListService {

    private final PriceListRepository repository;

    @Autowired
    public PriceListServiceImpl(PriceListRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<PriceList> findByProducerAndTypeAndDate(Producer producer, Type type, Date date) {
        return repository.findByProducerAndTypeAndDate(producer, type, date);
    }

    @Override
    public PriceList save(PriceList entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<PriceList> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    @Override
    public List<PriceList> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(PriceList entity) {
        repository.delete(entity);
    }
}
