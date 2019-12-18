package com.example.demo.service.impl;

import com.example.demo.entity.Correspondent;
import com.example.demo.repository.CorrespondentRepository;
import com.example.demo.service.CorrespondentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CorrespondentServiceImpl implements CorrespondentService {
    private CorrespondentRepository repository;

    @Autowired
    public CorrespondentServiceImpl(CorrespondentRepository repository){
        this.repository = repository;
    }

    @Override
    public Correspondent save(Correspondent entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<Correspondent> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public List<Correspondent> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(Correspondent entity) {
        repository.delete(entity);
    }
}
