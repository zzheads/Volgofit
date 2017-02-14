package com.zzheads.volgofit.service;

import com.zzheads.volgofit.model.Person.Trainer;

import java.util.List;

/**
 created by zzheads on 13.02.17
 **/

public interface TrainerService {
    Trainer findById(Long id);
    List<Trainer> findAll();
    Trainer save(Trainer trainer);
    void delete(Trainer trainer);
    Trainer findByName(String name);
}
