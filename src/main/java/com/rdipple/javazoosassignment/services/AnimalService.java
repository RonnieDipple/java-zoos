package com.rdipple.javazoosassignment.services;

import com.rdipple.javazoosassignment.models.Animal;
import com.rdipple.javazoosassignment.views.ZooCountAnimals;

import java.util.List;

public interface AnimalService {

    List<Animal> findAll();

    Animal findAnimalById(long id);

    void delete(long id);

    Animal save(Animal animal);

    Animal update(long id, Animal animal);

    List<ZooCountAnimals> getCountAnimalZoos();
}
