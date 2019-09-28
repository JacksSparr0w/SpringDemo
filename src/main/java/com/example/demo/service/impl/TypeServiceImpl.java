package com.example.demo.service.impl;

import com.example.demo.entity.Type;
import com.example.demo.repository.TypeRepository;
import com.example.demo.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeRepository typeRepository;

    @Override
    public Type save(Type entity) {
        return typeRepository.save(entity);
    }

    @Override
    public Optional<Type> findById(Integer id) {
        return typeRepository.findById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return typeRepository.existsById(id);
    }

    @Override
    public List<Type> findAll() {
        return typeRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        typeRepository.deleteById(id);
    }

    @Override
    public void delete(Type entity) {
        typeRepository.delete(entity);
    }
}
