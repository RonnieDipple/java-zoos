package com.example.javazoosassignment.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "animals", uniqueConstraints = {@UniqueConstraint(columnNames = {"animalid", "zooid"})})
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long animalid;

    private String animaltype;
//////////foreign key////////////////////
    @ManyToMany(mappedBy = "animals",
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("animals")
    private List<Animal> zoosArrayList = new ArrayList<>();

    ////////////////////////////////////////////////////////////

}
