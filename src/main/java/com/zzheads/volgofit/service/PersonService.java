package com.zzheads.volgofit.service;

import com.zzheads.volgofit.model.Person.Person;

import java.util.List;

/**
 * Created by zzheads on 10.02.17.
 */

public interface PersonService {
    Person findById(Long id);
    List<Person> findAll();
    Person save(Person person);
    void delete(Person person);
}
