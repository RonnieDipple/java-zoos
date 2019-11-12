package com.example.javazoosassignment.services;

import com.example.javazoosassignment.models.Telephone;
import com.example.javazoosassignment.models.Zoo;
import com.example.javazoosassignment.views.ZooCountAnimals;
import com.example.javazoosassignment.views.ZooCountTelephones;

import java.util.ArrayList;
import java.util.List;

public interface ZooService {

    List<Zoo> findAll();

    List<Zoo> findByNameContaining(String zooname);

    Zoo findZooById(long id);

    void delete(long id);

    Zoo save(Zoo zoo);

    Zoo update(Zoo zoo, long id);

    void deleteZooAnimal(long zooid, long animalid);

    void addZooAnimal(long zooid, long animalid);

    List<ZooCountTelephones> getCountZooTelephones();
}
