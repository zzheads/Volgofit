package com.zzheads.volgofit.service;

import com.zzheads.volgofit.dao.TrainerDao;
import com.zzheads.volgofit.model.Person.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Created by zzheads on 13.02.17.
 */

@Service
public class TrainerServiceImpl implements TrainerService {
    private final TrainerDao trainerDao;

    @Autowired
    public TrainerServiceImpl(TrainerDao trainerDao) {
        this.trainerDao = trainerDao;
    }

    @Override
    public Trainer findById(Long id) {
        return trainerDao.findOne(id);
    }

    @Override
    public List<Trainer> findAll() {
        return (List<Trainer>) trainerDao.findAll();
    }

    @Override
    public Trainer save(Trainer trainer) {
        return trainerDao.save(trainer);
    }

    @Override
    public void delete(Trainer trainer) {
        trainerDao.delete(trainer);
    }

    @Override
    public Trainer findByName(String name) {
        return findAll().stream().filter(trainer -> (Objects.equals(trainer.getFirstName(), name)) || (Objects.equals(trainer.getLastName(), name))).findFirst().orElse(null);
    }
}
