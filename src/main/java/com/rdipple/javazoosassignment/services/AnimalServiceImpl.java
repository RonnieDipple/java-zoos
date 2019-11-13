package com.rdipple.javazoosassignment.services;

import com.rdipple.javazoosassignment.models.Animal;
import com.rdipple.javazoosassignment.repositories.AnimalRepository;
import com.rdipple.javazoosassignment.repositories.ZooRepository;
import com.rdipple.javazoosassignment.views.ZooCountAnimals;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier(value = "animalService")
public class AnimalServiceImpl implements AnimalService {

    private AnimalRepository animalRepo;
    private ZooRepository zooRepository;

    public AnimalServiceImpl(AnimalRepository animalRepo, ZooRepository zooRepository) {
        this.animalRepo = animalRepo;
        this.zooRepository = zooRepository;
    }

    @Override
    public List<Animal> findAll() {
        List<Animal> list = new ArrayList<>();
        animalRepo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Animal findAnimalById(long id) {
        return animalRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("FindAnimalByIdFail"));
    }

    @Transactional
    @Override
    public void delete(long id) {
        animalRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Animal delete fail"));
        animalRepo.deleteById(id);
    }

    @Override
    public Animal save(Animal animal) {
        Animal newAnimal = new Animal();
        newAnimal.setAnimaltype(animal.getAnimaltype());
        if (animal.getZooanimals().size() > 0) throw new EntityNotFoundException("ZooAnimals not created: Animal");
        return animalRepo.save(animal);
    }

    @Override
    public Animal update(long id, Animal animal) {

        if (animal.getAnimaltype() == null) throw new EntityNotFoundException("No animal type to update");
        if (animal.getZooanimals().size() > 0) throw new EntityNotFoundException("Zooanimals not updated: Animal");

        if (animalRepo.findById(id) != null) {
            animalRepo.updateAnimalType(id, animal.getAnimaltype());
        } else throw new EntityNotFoundException("No animal with animalId " + id + " exists");
        return findAnimalById(id);
    }

    @Override
    public List<ZooCountAnimals> getCountAnimalZoos() {
        return animalRepo.getListOfAnimalsAndZoos();
    }
}
