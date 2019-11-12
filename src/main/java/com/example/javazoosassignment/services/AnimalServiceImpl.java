package com.example.javazoosassignment.services;

import com.example.javazoosassignment.models.Animal;
import com.example.javazoosassignment.repository.AnimalRepository;
import com.example.javazoosassignment.repository.ZooRepository;
import com.example.javazoosassignment.views.ZooCountAnimals;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier(value = "animalService")
public class AnimalServiceImpl implements AnimalService {

    private AnimalRepository animalRepository;
    private ZooRepository zooRepository;

    public AnimalServiceImpl(AnimalRepository animalRepository, ZooRepository zooRepository) {
        this.animalRepository = animalRepository;
        this.zooRepository = zooRepository;
    }

    @Override
    public List<Animal> findAll() {
        List<Animal> list = new ArrayList<>();
        animalRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }


    @Override
    public Animal findAnimalById(long id) {
        return animalRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("no findAnimalById"));
    }

    @Transactional
    @Override
    public void delete(long id) {
        animalRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("no delete"));
        animalRepository.deleteById(id);
    }

    @Override
    public Animal save(Animal animal) {
        Animal newAnimal = new Animal();
        newAnimal.setAnimaltype(animal.getAnimaltype());
        if (animal.getZooanimals().size() > 0) throw new EntityNotFoundException("ZooAnimals not created  animal");
        return animalRepository.save(animal);
    }

    @Override
    public Animal update(long id, Animal animal) {

        if (animal.getAnimaltype() == null) throw new EntityNotFoundException("No animal type cannot update");
        if (animal.getZooanimals().size() > 0) throw new EntityNotFoundException("Zooanimals not updated  animal");

        if (animalRepository.findById(id) != null) {
            animalRepository.updateAnimalType(id, animal.getAnimaltype());
        } else throw new EntityNotFoundException("No animal with id " + id + " exists");
        return findAnimalById(id);
    }

    @Override
    public List<ZooCountAnimals> getCountAnimalZoos() {
        return animalRepository.getListOfAnimalsAndZoos();
    }
}
