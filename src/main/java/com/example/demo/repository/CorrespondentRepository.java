package com.example.demo.repository;

import com.example.demo.entity.Correspondent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorrespondentRepository extends JpaRepository<Correspondent, Long> {
}
