package com.example.mrdtest.service;

import com.example.mrdtest.dto.Animal;
import com.example.mrdtest.entity.JpaAnimal;
import com.example.mrdtest.repo.AnimalRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalService{

    @Autowired
    private AnimalRepository animalRepository;

    public void saveAnimalsToDatabase(List<Animal> animals) {
        if (CollectionUtils.isEmpty(animals)) {
            return;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<JpaAnimal> saveList = new ArrayList<>();
        animals.forEach(animal -> saveList.add(objectMapper.convertValue(animal, JpaAnimal.class)));
        animalRepository.saveAll(saveList);
    }
}
