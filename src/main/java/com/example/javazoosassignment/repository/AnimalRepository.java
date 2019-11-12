package com.example.javazoosassignment.repository;

import com.example.javazoosassignment.models.Animal;
import com.example.javazoosassignment.views.Count;
import com.example.javazoosassignment.views.ZooCountAnimals;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AnimalRepository extends CrudRepository<Animal, Long> {

    @Query(value = "SELECT COUNT(*) AS count FROM zooanimals WHERE zooid = :zooid AND animalid = :animalid", nativeQuery = true)
    Count getBothZooAnimal(long zooid, long animalid);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM zooanimals WHERE zooid = :zooid AND animalid = :animalid",
            nativeQuery = true)
    void deleteZooAnimals(long zooid, long animalid);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO zooanimals(zooid, animalid) VALUES (:zooid, :animalid)", nativeQuery = true)
    void insertZooAndAnimal(long zooid, long animalid);

    @Query(value = "SELECT a.animaltype, count(z.animalid) as countanimal FROM animals a JOIN zooanimals z ON z.animalid = a.animalid GROUP BY a.animalid",
            nativeQuery = true)
    List<ZooCountAnimals> getListOfAnimalsAndZoos();

    @Query(value = "UPDATE animals SET animaltype = :animaltype WHERE animalid = :animalid", nativeQuery = true)
    Animal updateAnimalType(long animalid, String animaltype);

}
