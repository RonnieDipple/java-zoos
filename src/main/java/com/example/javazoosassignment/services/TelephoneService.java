package com.example.javazoosassignment.services;

import com.example.javazoosassignment.models.Telephone;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "telephoneService")
public interface TelephoneService {
    List<Telephone> findAll();

    Telephone findTelephoneById(long id);

    List<Telephone> findbyzooid(long id);

    void delete(long id);

    Telephone update(long phoneid, String phonenumber);
}
