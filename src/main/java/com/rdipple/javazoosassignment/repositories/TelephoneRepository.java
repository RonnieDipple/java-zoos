package com.rdipple.javazoosassignment.repositories;

import com.rdipple.javazoosassignment.models.Telephone;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TelephoneRepository extends CrudRepository<Telephone, Long> {

    List<Telephone> findAllByZooZooid(long id);
}
