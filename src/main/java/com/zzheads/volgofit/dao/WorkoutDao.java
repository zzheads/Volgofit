package com.zzheads.volgofit.dao;

import com.zzheads.volgofit.model.Workout.Workout;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by zzheads on 10.02.17.
 */

public interface WorkoutDao extends CrudRepository<Workout, Long> {
}
