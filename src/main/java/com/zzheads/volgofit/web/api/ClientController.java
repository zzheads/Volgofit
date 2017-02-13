package com.zzheads.volgofit.web.api;

import com.zzheads.volgofit.exceptions.ApiError;
import com.zzheads.volgofit.model.Person.Client;
import com.zzheads.volgofit.service.ClientService;
import com.zzheads.volgofit.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by zzheads on 13.02.17.
 */

@RestController
@RequestMapping(value = "/api/client")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService ClientService) {
        this.clientService = ClientService;
    }

    @RequestMapping(method = GET, produces = {"application/json"}, consumes = {"application/json"})
    @ResponseStatus(OK)
    public @ResponseBody
    String getAllClients() {
        List<Client> Clients = clientService.findAll();
        return Client.toJson(Clients);
    }

    @RequestMapping(value = "/{id}", method = GET, produces = {"application/json"}, consumes = {"application/json"})
    @ResponseStatus(OK)
    public @ResponseBody String getClientById(@PathVariable Long id) {
        Client Client = clientService.findById(id);
        if (Client != null) return Client.toJson();
        throw new ApiError(NOT_FOUND);
    }

    @RequestMapping(value = "/byName/{name}", method = GET, produces = {"application/json"}, consumes = {"application/json"})
    @ResponseStatus(OK)
    public @ResponseBody String getClientByName(@PathVariable String name) {
        Client Client = clientService.findByName(name);
        if (Client != null) return Client.toJson();
        throw new ApiError(NOT_FOUND);
    }

    @RequestMapping(method = POST, produces = {"application/json"}, consumes = {"application/json"})
    @ResponseStatus(OK)
    public @ResponseBody String createClient(@RequestBody String json) {
        if (LoggedUser.isAdmin()) {
            Client Client = new Client(json);
            if (Client.getFirstName() != null) {
                clientService.save(Client);
                return Client.toJson();
            }
            throw new ApiError(BAD_REQUEST);
        }
        throw new ApiError(FORBIDDEN);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = {"application/json"}, consumes = {"application/json"})
    @ResponseStatus(OK)
    public @ResponseBody String updateClient(@PathVariable Long id, @RequestBody String jsonString) {
        if (LoggedUser.isAdmin()) {
            if (clientService.findById(id) == null) {
                throw new ApiError(NOT_FOUND);
            }
            Client Client = new Client(jsonString);
            if (Client.getFirstName() != null) {
                Client.setId(id);
                clientService.save(Client);
                return Client.toJson();
            }
        }
        throw new ApiError(FORBIDDEN);
    }

    @RequestMapping(value = "/{id}", method = DELETE, produces = {"application/json"}, consumes = {"application/json"})
    @ResponseStatus(NO_CONTENT)
    public void deleteClient(@PathVariable Long id) {
        if (LoggedUser.isAdmin()) {
            Client Client = clientService.findById(id);
            if (Client != null) {
                clientService.delete(Client);
                return;
            }
            throw new ApiError(NOT_FOUND);
        }
        throw new ApiError(FORBIDDEN);
    }

}
