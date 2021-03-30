package com.jlb.spring.dbexo.service;

import com.jlb.spring.dbexo.domain.Person;
import com.jlb.spring.dbexo.domain.Vehicule;
import com.jlb.spring.dbexo.repository.PersonRepository;
import com.jlb.spring.dbexo.repository.VehiculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.persistence.EntityNotFoundException;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class SimpleService {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private PersonRepository personRepo;
    @Autowired
    private VehiculeRepository vehiculeRepo;

    public Mono<Long> countAllPersons() {
        return Mono.just(personRepo.count());
    }

    public Mono<Person> findById(Long id) {
        return Mono.just(personRepo.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public List<Vehicule> findVehiculesByPersonName(String name) {
        try (Stream<Vehicule> res = vehiculeRepo.findByPersonName(name)) {
            return res.collect(Collectors.toList());
        }
    }

    public Person addPerson(String name) {
        if (personRepo.findByName(name).size() > 0) {
            throw new IllegalArgumentException("name already in DB");
        } else {
            Person newPerson = new Person();
            newPerson.setName(name);
            return personRepo.save(newPerson);
        }
    }

    public Mono<Vehicule> findVehiculeById(Long id) {
        return Mono.just(vehiculeRepo.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public List<Vehicule> findVehiculesByOwnerId(Long id) {
        try (Stream<Vehicule> res = vehiculeRepo.findByOwner_Id(id)) {
//        try (Stream<Vehicule> res = vehiculeRepo.findByOwner(personRepo.findById(id).orElseThrow(EntityNotFoundException::new))) {
            return res.collect(Collectors.toList());
        }
    }
}
