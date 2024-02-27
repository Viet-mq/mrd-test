package com.example.mrdtest.repo;

import com.example.mrdtest.entity.JpaAnimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<JpaAnimal, Long> {
}

