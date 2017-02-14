package com.zzheads.volgofit.dao;

import com.zzheads.volgofit.model.Person.Trainer;
import org.springframework.data.repository.CrudRepository;

public interface TrainerDao extends CrudRepository<Trainer, Long> {
}
