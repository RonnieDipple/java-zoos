package com.example.javazoosassignment.repository;

import com.example.javazoosassignment.models.Telephone;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TelephoneRepository extends CrudRepository<Telephone, Long> {

    List<Telephone> findAllByZooZooid(long id);
}
