package com.zzheads.volgofit.dao;

import com.zzheads.volgofit.model.Workout.Workout;
import org.springframework.data.repository.CrudRepository;

public interface WorkoutDao extends CrudRepository<Workout, Long> {
}
