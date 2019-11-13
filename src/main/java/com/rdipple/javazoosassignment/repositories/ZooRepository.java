package com.rdipple.javazoosassignment.repositories;

import com.rdipple.javazoosassignment.models.Zoo;
import com.rdipple.javazoosassignment.views.ZooCountTelephones;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ZooRepository extends CrudRepository<Zoo, Long> {


    @Query(value = "SELECT z.zooname as zoonamerpt, count(t.phoneid) as countphone FROM zoos z JOIN telephones t ON z.zooid = t.zooid GROUP BY z.zooname",
            nativeQuery = true)
    List<ZooCountTelephones> getCountZooAndAnimals();

    /*@Query(value = "SELECT u.zooname as zooname, count(ue.animalid) as countanimals FROM users u JOIN animals ue ON u.zooid = ue.zooid GROUP BY u.zooname",
           nativeQuery = true)
    List<ZooCountAnimals> getCountZooAnimals();*/


    /* String getZooName();
    int getCountAnimals();*/

    List<Zoo> findByzoonameContaining(String zooname);

    Zoo findByzooname(String zooname);
}

