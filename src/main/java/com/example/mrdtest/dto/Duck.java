package com.example.mrdtest.dto;

public class Duck extends Animal {

    public Duck(int id, String name) {
        this.setId(id);
        this.setName(name);
        this.setType("Duck");
        this.setNumberOfLegs(2);
    }

    public String makeSound() {
        return "Quack";
    }
}
