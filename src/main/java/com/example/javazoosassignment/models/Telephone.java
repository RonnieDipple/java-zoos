package com.example.javazoosassignment.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "telephones", uniqueConstraints = {@UniqueConstraint(columnNames = {"zooid", "phonenumber"})})
public class Telephone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long phoneid;

    private String phonetype;

    @Column(nullable = false)
    @NumberFormat
    @ManyToOne
    private String phonenumber;



    public Telephone(){}

    public Telephone(String phonetype, String phonenumber) {
        this.phonetype = phonetype;
        this.phonenumber = phonenumber;
    }

    public long getPhoneid() {
        return phoneid;
    }

    public void setPhoneid(long phoneid) {
        this.phoneid = phoneid;
    }

    public String getPhonetype() {
        return phonetype;
    }

    public void setPhonetype(String phonetype) {
        this.phonetype = phonetype;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
