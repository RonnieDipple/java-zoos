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
@OneToMany(mappedBy = "animal", cascade = CascadeType.ALL)
@JsonIgnoreProperties(value = "animal", allowSetters = true)
private List<ZooAnimals> zooanimals = new ArrayList<>();


    ////////////////////////////////////////////////////////////

    public Animal() {}

    public Animal(String animaltype) {
        this.animaltype = animaltype;
    }

    public long getAnimalid() {
        return animalid;
    }

    public void setAnimalid(long animalid) {
        this.animalid = animalid;
    }

    public String getAnimaltype() {
        return animaltype;
    }

    public void setAnimaltype(String animaltype) {
        this.animaltype = animaltype;
    }

    public List<ZooAnimals> getZooanimals() {
        return zooanimals;
    }

    public void setZooanimals(List<ZooAnimals> zooanimals) {
        this.zooanimals = zooanimals;
    }

}
