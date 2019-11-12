package com.example.javazoosassignment.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "zoos", uniqueConstraints = {@UniqueConstraint(columnNames = {"zooid", "phonenumber", "animalid"})})
public class Zoo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long zooid;

    @Column(nullable = false,
            unique = true)
    private String zooname;

    @OneToMany(mappedBy = "zoos",
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("zoos")
    private List<Telephone> telephonesArrayList = new ArrayList<>();

    @ManyToMany(mappedBy = "zoos",
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("zoos")
    private List<Animal> animalsArrayList = new ArrayList<>();

    public Zoo(){}

    public Zoo(String zooname, List<Telephone> telephonesArrayList, List<Animal> animalsArrayList) {
        this.zooname = zooname;
        this.telephonesArrayList = telephonesArrayList;
        this.animalsArrayList = animalsArrayList;
    }

    public long getZooid() {
        return zooid;
    }

    public void setZooid(long zooid) {
        this.zooid = zooid;
    }

    public String getZooname() {
        return zooname;
    }

    public void setZooname(String zooname) {
        this.zooname = zooname;
    }

    public List<Telephone> getTelephonesArrayList() {
        return telephonesArrayList;
    }

    public void setTelephonesArrayList(List<Telephone> telephonesArrayList) {
        this.telephonesArrayList = telephonesArrayList;
    }

    public List<Animal> getAnimalsArrayList() {
        return animalsArrayList;
    }

    public void setAnimalsArrayList(List<Animal> animalsArrayList) {
        this.animalsArrayList = animalsArrayList;
    }
}
