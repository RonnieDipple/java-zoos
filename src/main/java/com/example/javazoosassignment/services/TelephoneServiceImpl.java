package com.example.javazoosassignment.services;

import com.example.javazoosassignment.models.Telephone;
import com.example.javazoosassignment.repository.TelephoneRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("telephoneService")
public class TelephoneServiceImpl implements TelephoneService {

    private TelephoneRepository telephoneRepository;

    public TelephoneServiceImpl(TelephoneRepository telephoneRepository) {
        this.telephoneRepository = telephoneRepository;
    }

    @Override
    public List<Telephone> findAll() {
        List<Telephone> list = new ArrayList<>();
        telephoneRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Telephone findTelephoneById(long id) {
        return telephoneRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("no"));
    }

    @Override
    public List<Telephone> findByZooId(long id) {
        return telephoneRepository.findAllByZooid(id);
    }

    @Override
    public void delete(long id) {
        if (telephoneRepository.findById(id).isPresent()) {
            telephoneRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("no thanks");
        }
    }

    @Override
    public Telephone update(long phoneid, String phonenumber) {
        if (telephoneRepository.findById(phoneid).isPresent()) {
            Telephone telephone = findTelephoneById(phoneid);
            telephone.setPhonenumber(phonenumber);
            return telephoneRepository.save(telephone);
        } else {
            throw new EntityNotFoundException("No thanks");
        }
    }



}
