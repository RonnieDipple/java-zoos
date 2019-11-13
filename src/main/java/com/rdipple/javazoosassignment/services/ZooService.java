package com.rdipple.javazoosassignment.services;

import com.rdipple.javazoosassignment.models.Zoo;
import com.rdipple.javazoosassignment.views.ZooCountTelephones;

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
