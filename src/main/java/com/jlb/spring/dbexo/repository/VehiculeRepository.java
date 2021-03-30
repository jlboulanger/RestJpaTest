package com.jlb.spring.dbexo.repository;

import com.jlb.spring.dbexo.domain.Person;
import com.jlb.spring.dbexo.domain.Vehicule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.stream.Stream;

public interface VehiculeRepository extends CrudRepository<Vehicule, Long> {
    @Query(" select v from Vehicule v left join Person p on v.owner.id = p.id where p.name = :name")
    Stream<Vehicule> findByPersonName(String name);

    //@Query(" select v from Vehicule v left join Person p on v.owner.id = p.id where p.id = :ownerId")
    Stream<Vehicule> findByOwner_Id(Long ownerId);

    Stream<Vehicule> findByOwner(Person owner);
}
