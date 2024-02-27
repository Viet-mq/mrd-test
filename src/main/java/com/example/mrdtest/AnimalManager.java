package com.example.mrdtest;

import com.example.mrdtest.dto.Animal;
import com.example.mrdtest.dto.Cat;
import com.example.mrdtest.dto.Duck;
import com.example.mrdtest.filestorage.FileStorage;
import com.example.mrdtest.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class AnimalManager implements CommandLineRunner {

    @Autowired
    private AnimalService animalService;

    public static void main(String[] args) {
        SpringApplication.run(AnimalManager.class, args);
    }

    @Override
    public void run(String... args) throws InterruptedException {
        List<Animal> animals = generateAnimals();

        CountDownLatch latch = new CountDownLatch(2);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> {
            animalService.saveAnimalsToDatabase(animals);
            latch.countDown();
        });
        executor.submit(() -> {
            FileStorage.saveToFile(animals);
            latch.countDown();
        });

        latch.await();

        System.out.println("Done");
        executor.shutdown();

        //write result
        List<Animal> animalsFromDB = null;
        List<Animal> animalsFromFile = null;
        try {
            animalsFromDB = animalService.getAllAnimalsFromDatabase();
            animalsFromFile = FileStorage.readFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (animalsFromDB != null && animalsFromFile != null) {
            boolean arraysEqual = Animal.compareAnimals(animalsFromDB, animalsFromFile);
            if (arraysEqual) {
                System.out.println("The arrays from DB and file are equal.");
            } else {
                System.out.println("The arrays from DB and file are not equal.");
            }
        }

    }

    private List<Animal> generateAnimals() {
        List<Animal> animals = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            int randomNumber = random.nextInt(1000) + 1;
            Animal animal;
            if (randomNumber % 2 == 0) {
                animal = new Cat(i, "Cat " + i);
            } else {
                animal = new Duck(i, "Duck " + i);
            }
            animals.add(animal);
        }
        return animals;
    }
}
