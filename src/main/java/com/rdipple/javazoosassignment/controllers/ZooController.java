package com.rdipple.javazoosassignment.controllers;


import com.rdipple.javazoosassignment.models.Zoo;
import com.rdipple.javazoosassignment.services.ZooService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/zoos")
public class ZooController {

    ZooService zooService;

    ///GET /zoos returns all zoos with their phone numbers and animals
    @GetMapping(value = "/zoos", produces = {"application/json"})
    public ResponseEntity<?> listAllZoos() {
        List<Zoo> myZoos = zooService.findAll();
        return new ResponseEntity<>(myZoos, HttpStatus.OK);
    }


    //GET /zoo/{id} returns all information related to a zoo based on its id
    @GetMapping(value = "/zoo/{zooid}", produces = {"application/json"})
    public ResponseEntity<?> getZooById(@PathVariable long zooid) {
        Zoo myZoo = zooService.findZooById(zooid);
        return new ResponseEntity<>(myZoo, HttpStatus.OK);
    }

    //GET /zoo/namelike/{name} returns a list of all the zoos with their information who have the given substring in their name
    @GetMapping(value = "/zoo/namelike/{name}", produces = {"application/json"})
    public ResponseEntity<?> getZooByNamelike(@PathVariable String name) {
        List<Zoo> myZoos = zooService.findByNameContaining(name);
        return new ResponseEntity<>(myZoos, HttpStatus.OK);
    }

    // POST /zoo - add a zoo
    public ResponseEntity<?> addNewZoo(@Valid @RequestBody Zoo newZoo) {
        newZoo = zooService.save(newZoo);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newZooURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{zooid}")
                .buildAndExpand(newZoo.getZooid())
                .toUri();
        responseHeaders.setLocation(newZooURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

   /* //GET http://localhost:2019/animals/count -  that returns a JSON object list listing the animals and a count of how many zoos where they can be found.
    @GetMapping(value = "/animals/count", produces = {"application/json"})
    public ResponseEntity<?> getZooAndAnimalNum(){
        return new ResponseEntity<>(zooService.getCountZooAnimals(), HttpStatus.OK);
    }*/

    //PUT localhost:2019/zoos/zoo/{id}
    @PutMapping(value = "/zoo/{zooid}", consumes = {"application/json"})
    public ResponseEntity<?> updateZoo(@RequestBody Zoo updateZoo, @PathVariable long zooid) {
        zooService.update(updateZoo, zooid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //DELETE localhost:2019/zoos/zoo/{id}
    @DeleteMapping(value = "/zoo/{zooid}")
    public ResponseEntity<?> deleteZooById(@PathVariable long zooid) {
        zooService.delete(zooid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // DELETE localhost:2019/zoos/zoo/{zooid}/animals/{animalid}
    @DeleteMapping(value = "/zoo/{zooid}/animals/{animalid}")
    public ResponseEntity<?> deleteZooAnimalByIds(@PathVariable long zooid, @PathVariable long animalid) {
        zooService.deleteZooAnimal(zooid, animalid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //POST localhost:2019/zoos/zoo/{zooid}/animals/{animalid}
    @PostMapping(value = "/zoo/{zooid}/animals/{animalid}")
    public ResponseEntity postZooAnimalByIds(@PathVariable long zooid, @PathVariable long animalid) {
        zooService.addZooAnimal(zooid, animalid);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    public ZooController(ZooService zooService) {
        this.zooService = zooService;
    }
}

/*You are creating a Java Spring REST API server which stores data in an H2 database. The table layouts should be

Zoo

zooid - long primary key
zooname - String
Telephone

phoneid - long primary key
phonetype - String
phonenumber - String
zooid - foreign key
There is a one to many relationship between zoos and telephones. One zoo can have multiple phone numbers but each phone number can only belong to one zoo.

Animal
animalid - long primary key
animaltype - String
There is a many to many relationship between zoos and animals. A zoo may have many animal types and an animal type may be at many zoos.

The data.sql file seeds the Zoo Database.

You goal is to implement the following endpoints for accessing this API.
GET /zoos/zoos - returns all zoos with their phone numbers and animals

GET /zoos/zoo/{id} - returns all information related to a zoo based on its id

GET /zoos/zoo/namelike/{name} - returns a list of all the zoos with their information who have the given substring in their name

POST /zoos/zoo - add a zoo

Add the Zoo and associated phone number(s). This does NOT address the Zoo Animal combinations! That would be a separate end point.
In the header return as the location of the newly created zoo POST /zoos/zoo/{id}
You could use to test:
{
   "zooname": "Port Angeles Zoo",
   "telephones": [
      {
          "phonetype": "education",
          "phonenumber": "555-777-777"
      },
      {
          "phonetype": "main",
          "phonenumber": "555-777-777"
      }
   ]
}
PUT /zoos/zoo/{id} - update the zoo referenced by the id number with the provided information
Update the Zoo and associated phone number. This does NOT address the Zoo Animal combinations! That would be a separate endpoint
You could use to test (use id 2):
{
   "zooname": "SanDiegoZoo",
   "telephones": [
      {
          "phonetype": "education",
          "phonenumber": "555-777-777"
      }
   ]
}
DELETE /zoos/zoo/{id} - delete the zoo, associated phone numbers, and zoo animals combination associated with this zoo id

This should delete the Zoo, associated telephone numbers, and zoo animals combinations associated with this zoo.
GET /animals/count - that returns a JSON object list listing the animals and a count of how many zoos where they can be found. Use a custom query for this.

DELETE /zoo/zoo/{zooid}/animals/{animalid} - delete the zoo animal combination based off of ids.

Hint: @PathVariable("zooid") long zooid, @PathVariable("animalid") long animalid
POST /zoos/zoo/{zooid}/animals/{animalid} - adds the zoo animal combination based off of ids.

Hint: @PathVariable("zooid") long zooid, @PathVariable("animalid") long animalid*/
