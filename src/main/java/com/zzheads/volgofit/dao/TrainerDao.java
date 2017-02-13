package com.zzheads.volgofit.dao;

import com.zzheads.volgofit.model.Person.Trainer;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by zzheads on 13.02.17.
 */
public interface TrainerDao extends CrudRepository<Trainer, Long> {
}
