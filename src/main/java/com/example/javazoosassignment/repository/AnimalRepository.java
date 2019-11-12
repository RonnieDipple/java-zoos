package com.example.javazoosassignment.repository;

import com.example.javazoosassignment.models.Animal;
import org.springframework.data.repository.CrudRepository;

public interface AnimalRepository extends CrudRepository<Animal, Long> {
}
