package com.jlb.spring.dbexo.controller;

import com.jlb.spring.dbexo.domain.Person;
import com.jlb.spring.dbexo.domain.Vehicule;
import com.jlb.spring.dbexo.service.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNull;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/dbexo")
public class SimpleController {

    @Autowired
    private SimpleService service;

    @GetMapping(value = "/count/persons", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Long> count() {
        return service.countAllPersons();
    }

    @GetMapping(value = "/persons/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<Person>> find(@PathVariable("id") @NonNull Long id) {
        try {
            return new ResponseEntity<Mono<Person>>(service.findById(id), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/vehicules/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<Vehicule>> findVehicule(@PathVariable("id") @NonNull Long id) {
        try {
            return new ResponseEntity<Mono<Vehicule>>(service.findVehiculeById(id), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/persons/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> addByName(@PathVariable("name") @NonNull String name) {
        try {
            return new ResponseEntity<Person>(service.addPerson(name), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
    }

    //TODO this is not reactive ! use Flux and stream ?
    @GetMapping(value = "/vehicules/owner/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Vehicule>> findAllVehiculeForPerson(@PathVariable("name") @NonNull String name) {
        return new ResponseEntity<List<Vehicule>>(service.findVehiculesByPersonName(name), HttpStatus.OK);
    }

    @GetMapping(value = "/persons/{id}/vehicules", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Vehicule>> findAllVehiculeByOwner(@PathVariable("id") @NonNull Long id) {
        return new ResponseEntity<List<Vehicule>>(service.findVehiculesByOwnerId(id), HttpStatus.OK);

    }
}
