package com.example.mrdtest.filestorage;

import com.example.mrdtest.dto.Animal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileStorage {
    public static void saveToFile(List<Animal> animals) {
        try {
            FileWriter writer = new FileWriter("animals.txt");
            for (Animal animal : animals) {
                writer.write(animal.getType() + ": " + animal.getName() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static List<Animal> readFromFile() {
        List<Animal> animals = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("animals.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(": ");
                String type = parts[0];
                String name = parts[1];
                Animal animal = new Animal();
                animal.setType(type);
                animal.setName(name);
                animals.add(animal);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return animals;
    }
}

