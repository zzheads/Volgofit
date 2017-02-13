package com.zzheads.volgofit.service;

import com.zzheads.volgofit.model.Person.Client;

import java.util.List;

/**
 * Created by zzheads on 13.02.17.
 */

public interface ClientService {
    Client findById(Long id);
    List<Client> findAll();
    Client save(Client client);
    void delete(Client client);
    Client findByName(String name);
}
