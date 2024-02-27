package com.example.mrdtest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Animal {

    private int id;
    private String name;
    private String type;
    private int numberOfLegs;

    public static boolean compareAnimals(List<Animal> list1, List<Animal> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }

        for (int i = 0; i < list1.size(); i++) {
            Animal animal1 = list1.get(i);
            Animal animal2 = list2.get(i);
            if (!animal1.getName().equals(animal2.getName())) {
                return false;
            }
        }

        return true;
    }
}
