package com.zzheads.volgofit.dao;

import com.zzheads.volgofit.model.Person.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientDao extends CrudRepository<Client, Long> {
}
