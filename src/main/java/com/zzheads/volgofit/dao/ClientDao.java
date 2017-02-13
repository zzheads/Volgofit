package com.zzheads.volgofit.dao;

import com.zzheads.volgofit.model.Person.Client;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by zzheads on 13.02.17.
 */
public interface ClientDao extends CrudRepository<Client, Long> {
}
