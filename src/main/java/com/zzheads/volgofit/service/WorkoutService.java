package com.zzheads.volgofit.service;

import com.zzheads.volgofit.model.Workout.Workout;

import java.util.Date;
import java.util.List;

/**
 * Created by zzheads on 10.02.17.
 */
public interface WorkoutService {
    Workout findById(Long id);
    List<Workout> findAll();
    Workout save(Workout workout);
    void delete(Workout workout);
    List<Workout> getSchedule(Date from, Date to);
}
