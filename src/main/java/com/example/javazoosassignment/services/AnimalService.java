package com.example.javazoosassignment.services;

import com.example.javazoosassignment.models.Animal;
import com.example.javazoosassignment.views.ZooCountAnimals;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "animalService")
public interface AnimalService {
    List<Animal> findAll();

    Animal findAnimalById(long id);

    void delete(long id);

    Animal save(Animal animal);

    Animal update(long id, Animal animal);

    List<ZooCountAnimals> getCountAnimalZoos();
}
