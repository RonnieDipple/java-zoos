package com.example.javazoosassignment.services;

import com.example.javazoosassignment.models.Telephone;
import com.example.javazoosassignment.models.Zoo;
import com.example.javazoosassignment.repository.AnimalRepository;
import com.example.javazoosassignment.repository.ZooRepository;
import com.example.javazoosassignment.views.ZooCountTelephones;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("zooService")
public class ZooServiceImpl implements ZooService {

    private ZooRepository zooRepository;
    private AnimalRepository animalRepository;

    public ZooServiceImpl(ZooRepository zooRepository, AnimalRepository animalRepository) {
        this.zooRepository = zooRepository;
        this.animalRepository = animalRepository;
    }

    @Override
    public List<Zoo> findAll() {

        List<Zoo> list = new ArrayList<>();
        zooRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public List<Zoo> findByNameContaining(String zooname) {
        return zooRepository.findByzoonameContaining(zooname);
    }

    @Override
    public Zoo findZooById(long id) {
        return zooRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("not found"));
    }

    @Transactional
    @Override
    public void delete(long id) {
        zooRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("delete error"));
        zooRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Zoo save(Zoo zoo) {
        if (zooRepository.findByzooname(zoo.getzooname()) != null) {
            throw new EntityNotFoundException(zoo.getzooname() + " already exists");
        }
        Zoo newZoo = new Zoo();
        newZoo.setzooname(zoo.getzooname());

        for( Telephone t : zoo.getTelephones()) {
            newZoo.getTelephones().add(new Telephone(t.getPhonetype(), t.getPhonenumber(), newZoo));
        }

        return zooRepository.save(newZoo);
    }

    @Transactional
    @Override
    public Zoo update(Zoo zoo, long id) {
        Zoo newZoo = findZooById(id);
        if (zoo.getzooname() != null) {
            newZoo.setzooname(zoo.getzooname());
        }

        if (zoo.getTelephones() != null) {
            for (Telephone t : zoo.getTelephones()) {
                newZoo.getTelephones().add(new Telephone(t.getPhonetype(), t.getPhonenumber(), newZoo));
            }
        }

        return zooRepository.save(newZoo);
    }

    @Transactional
    @Override
    public void deleteZooAnimal(long zooid, long animalid) {
        zooRepository.findById(zooid).orElseThrow(() -> new EntityNotFoundException("Zoo id " + zooid + " not found"));
        animalRepository.findById(zooid).orElseThrow(() -> new EntityNotFoundException("Zoo id " + animalid + " not found"));

        if (animalRepository.getBothZooAnimal(zooid, animalid).getCount() > 0) {
            animalRepository.deleteZooAnimals(zooid, animalid);
        } else throw new EntityNotFoundException("Zoo and Animal does not exist");

    }

    @Override
    public void addZooAnimal(long zooid, long animalid) {
        zooRepository.findById(zooid).orElseThrow(() -> new EntityNotFoundException("Zoo id " + zooid + " not found"));
        animalRepository.findById(zooid).orElseThrow(() -> new EntityNotFoundException("Zoo id " + animalid + " not found"));

        if (animalRepository.getBothZooAnimal(zooid, animalid).getCount() <= 0) {
            animalRepository.insertZooAndAnimal(zooid, animalid);
        } else throw new EntityNotFoundException("Zoo and animal already exists");
    }

    @Override
    public List<ZooCountTelephones> getCountZooTelephones() {
        return zooRepository.getCountZooAndAnimals();
    }
}
