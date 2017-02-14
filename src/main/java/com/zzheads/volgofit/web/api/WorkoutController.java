package com.zzheads.volgofit.web.api;

import com.zzheads.volgofit.exceptions.ApiError;
import com.zzheads.volgofit.model.Workout.Workout;
import com.zzheads.volgofit.service.WorkoutService;
import com.zzheads.volgofit.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

//
//  created by zzheads on 13.02.17
//

@RestController
@RequestMapping(value = "/api/workout")
public class WorkoutController {
    private final WorkoutService workoutService;

    @Autowired
    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @RequestMapping(method = GET, produces = {"application/json"}, consumes = {"application/json"})
    @ResponseStatus(OK)
    public @ResponseBody String getAllWorkouts() {
        List<Workout> workouts = workoutService.findAll();
        return Workout.toJson(workouts);
    }

    @RequestMapping(value = "/{id}", method = GET, produces = {"application/json"}, consumes = {"application/json"})
    @ResponseStatus(OK)
    public @ResponseBody String getWorkoutById(@PathVariable Long id) {
        Workout workout = workoutService.findById(id);
        if (workout != null) return workout.toJson();
        throw new ApiError(NOT_FOUND);
    }

    @RequestMapping(method = POST, produces = {"application/json"}, consumes = {"application/json"})
    @ResponseStatus(OK)
    public @ResponseBody String createWorkout(@RequestBody String json) {
        if (LoggedUser.isAdmin()) {
            Workout workout = new Workout(json);
            if (workout.getTitle() != null) {
                return (workoutService.save(workout)).toJson();
            }
            throw new ApiError(BAD_REQUEST);
        }
        throw new ApiError(FORBIDDEN);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = {"application/json"}, consumes = {"application/json"})
    @ResponseStatus(OK)
    public @ResponseBody String updateWorkout(@PathVariable Long id, @RequestBody String jsonString) {
        if (LoggedUser.isAdmin()) {
            if (workoutService.findById(id) == null) {
                throw new ApiError(NOT_FOUND);
            }
            Workout workout = new Workout(jsonString);
            if (workout.getTitle() != null) {
                workout.setId(id);
                return (workoutService.save(workout)).toJson();
            }
        }
        throw new ApiError(FORBIDDEN);
    }

    @RequestMapping(value = "/{id}", method = DELETE, produces = {"application/json"}, consumes = {"application/json"})
    @ResponseStatus(NO_CONTENT)
    public void deleteWorkout(@PathVariable Long id) {
        if (LoggedUser.isAdmin()) {
            Workout workout = workoutService.findById(id);
            if (workout != null) {
                workoutService.delete(workout);
                return;
            }
            throw new ApiError(NOT_FOUND);
        }
        throw new ApiError(FORBIDDEN);
    }

}
