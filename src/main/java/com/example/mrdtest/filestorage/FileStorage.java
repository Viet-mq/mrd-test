package com.example.mrdtest.filestorage;

import com.example.mrdtest.dto.Animal;

import java.io.FileWriter;
import java.io.IOException;
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
}

