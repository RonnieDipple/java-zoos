package com.example.javazoosassignment.services;

import com.example.javazoosassignment.models.Telephone;
import com.example.javazoosassignment.models.Zoo;
import com.example.javazoosassignment.repository.ZooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service(value = "zooService")
public class ZooServiceImpl implements ZooService {

    @Autowired
    private ZooRepository zoorepos;


    @Override
    public List<Zoo> findAllZoos() {

            List<Zoo> list = new ArrayList<>();
            zoorepos.findAll()
                    .iterator()
                    .forEachRemaining(list::add);
            return list;

    }

    @Override
    public Zoo findZooById(long id) throws EntityNotFoundException {

        return zoorepos.findById(id).orElseThrow(() -> new EntityNotFoundException("Zoo id" + id + "not found!"));
    }

    @Override
    public List<Zoo> findZooByNameLike(String thename) {
        return zoorepos.findByZooNameContainingIgnoreCase(thename);
    }

    @Override
    @Transactional
    public Zoo save(Zoo zoo, Telephone telephone) {
        Zoo newZoo = new Zoo();
        newZoo.setZooname(zoo.getZooname());
        return zoorepos.save(newZoo);
    }


}
