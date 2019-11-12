package com.example.javazoosassignment.repository;

import com.example.javazoosassignment.models.Zoo;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ZooRepository extends CrudRepository<Zoo, Long> {

    ArrayList<Zoo>findByZooNameContainingIgnoreCase(String likename);
}
