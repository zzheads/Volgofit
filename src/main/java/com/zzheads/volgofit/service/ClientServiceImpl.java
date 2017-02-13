package com.zzheads.volgofit.service;

import com.zzheads.volgofit.dao.ClientDao;
import com.zzheads.volgofit.model.Person.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zzheads on 13.02.17.
 */

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientDao clientDao;

    @Autowired
    public ClientServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public Client findById(Long id) {
        return clientDao.findOne(id);
    }

    @Override
    public List<Client> findAll() {
        return (List<Client>) clientDao.findAll();
    }

    @Override
    public Client save(Client client) {
        clientDao.save(client);
        return client;
    }

    @Override
    public void delete(Client client) {
        clientDao.delete(client);
    }

    @Override
    public Client findByName(String name) {
        return findAll().stream().filter(client -> client.getFirstName().equals(name) || client.getLastName().equals(name)).findFirst().orElse(null);
    }
}
