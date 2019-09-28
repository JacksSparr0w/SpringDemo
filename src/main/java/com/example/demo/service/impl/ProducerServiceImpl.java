package com.example.demo.service.impl;

import com.example.demo.entity.Producer;
import com.example.demo.repository.ProducerRepository;
import com.example.demo.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProducerServiceImpl implements ProducerService {

    @Autowired
    private ProducerRepository repository;

    @Override
    public Producer save(Producer entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<Producer> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    @Override
    public List<Producer> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(Producer entity) {
        repository.delete(entity);
    }
}
