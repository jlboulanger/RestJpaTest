package com.jlb.spring.dbexo.repository;

import com.jlb.spring.dbexo.domain.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long> {

    public List<Person> findByName(String name);
}
