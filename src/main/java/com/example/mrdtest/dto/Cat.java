package com.example.mrdtest.dto;

public class Cat extends Animal{

    public Cat(int id, String name) {
        this.setId(id);
        this.setName(name);
        this.setType("Cat");
        this.setNumberOfLegs(4);
    }

    public String makeSound() {
        return "Meow";
    }
}
