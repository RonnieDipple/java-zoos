package com.rdipple.javazoosassignment.controllers;
import com.rdipple.javazoosassignment.services.AnimalService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/animals")
public class AnimalController {

    private AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping(value = "/count", produces = {"application/json"})
    public ResponseEntity<?> getAnimalCounts() {
        return new ResponseEntity<>(animalService.getCountAnimalZoos(), HttpStatus.OK);
    }
}
