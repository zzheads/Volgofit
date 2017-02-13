package com.zzheads.volgofit.web.api;

import com.zzheads.volgofit.exceptions.ApiError;
import com.zzheads.volgofit.model.Person.Trainer;
import com.zzheads.volgofit.service.TrainerService;
import com.zzheads.volgofit.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by zzheads on 13.02.17.
 */

@RestController
@RequestMapping(value = "/api/trainer")
public class TrainerController {
    private final TrainerService trainerService;

    @Autowired
    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @RequestMapping(method = GET, produces = {"application/json"}, consumes = {"application/json"})
    @ResponseStatus(OK)
    public @ResponseBody String getAllTrainers() {
        List<Trainer> trainers = trainerService.findAll();
        return Trainer.toJson(trainers);
    }

    @RequestMapping(value = "/{id}", method = GET, produces = {"application/json"}, consumes = {"application/json"})
    @ResponseStatus(OK)
    public @ResponseBody String getTrainerById(@PathVariable Long id) {
        Trainer trainer = trainerService.findById(id);
        if (trainer != null) return trainer.toJson();
        throw new ApiError(NOT_FOUND);
    }

    @RequestMapping(value = "/byName/{name}", method = GET, produces = {"application/json"}, consumes = {"application/json"})
    @ResponseStatus(OK)
    public @ResponseBody String getTrainerByName(@PathVariable String name) {
        Trainer trainer = trainerService.findByName(name);
        if (trainer != null) return trainer.toJson();
        throw new ApiError(NOT_FOUND);
    }

    @RequestMapping(method = POST, produces = {"application/json"}, consumes = {"application/json"})
    @ResponseStatus(OK)
    public @ResponseBody String createTrainer(@RequestBody String json) {
        if (LoggedUser.isAdmin()) {
            Trainer trainer = new Trainer(json);
            if (trainer.getFirstName() != null) {
                return (trainerService.save(trainer)).toJson();
            }
            throw new ApiError(BAD_REQUEST);
        }
        throw new ApiError(FORBIDDEN);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = {"application/json"}, consumes = {"application/json"})
    @ResponseStatus(OK)
    public @ResponseBody String updateTrainer(@PathVariable Long id, @RequestBody String jsonString) {
        if (LoggedUser.isAdmin()) {
            if (trainerService.findById(id) == null) {
                throw new ApiError(NOT_FOUND);
            }
            Trainer trainer = new Trainer(jsonString);
            if (trainer.getFirstName() != null) {
                trainer.setId(id);
                return (trainerService.save(trainer)).toJson();
            }
        }
        throw new ApiError(FORBIDDEN);
    }

    @RequestMapping(value = "/{id}", method = DELETE, produces = {"application/json"}, consumes = {"application/json"})
    @ResponseStatus(NO_CONTENT)
    public void deleteTrainer(@PathVariable Long id) {
        if (LoggedUser.isAdmin()) {
            Trainer trainer = trainerService.findById(id);
            if (trainer != null) {
                trainerService.delete(trainer);
                return;
            }
            throw new ApiError(NOT_FOUND);
        }
        throw new ApiError(FORBIDDEN);
    }

}
