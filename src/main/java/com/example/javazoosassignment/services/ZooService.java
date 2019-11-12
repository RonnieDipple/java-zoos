package com.example.javazoosassignment.services;

import com.example.javazoosassignment.models.Telephone;
import com.example.javazoosassignment.models.Zoo;

import java.util.List;

public interface ZooService {
    List<Zoo> findAllZoos();

    Zoo findZooById(long id);

    List <Zoo> findZooByNameLike(String thename);

    Zoo save(Zoo zoo, Telephone telephone);


}
