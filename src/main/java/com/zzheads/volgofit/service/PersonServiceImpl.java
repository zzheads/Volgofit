package com.zzheads.volgofit.service;

import com.zzheads.volgofit.dao.PersonDao;
import com.zzheads.volgofit.model.Person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zzheads on 10.02.17.
 */

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonDao personDao;

    @Autowired
    public PersonServiceImpl(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public Person findById(Long id) {
        return personDao.findOne(id);
    }

    @Override
    public List<Person> findAll() {
        return (List<Person>) personDao.findAll();
    }

    @Override
    public Person save(Person person) {
        return personDao.save(person);
    }

    @Override
    public void delete(Person person) {
        personDao.delete(person);
    }
}
