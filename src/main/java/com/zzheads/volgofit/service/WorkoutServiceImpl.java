package com.zzheads.volgofit.service;

import com.zzheads.volgofit.dao.WorkoutDao;
import com.zzheads.volgofit.model.Workout.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 created by zzheads on 10.02.17
 **/

@Service
public class WorkoutServiceImpl implements WorkoutService {
    private final WorkoutDao workoutDao;

    @Autowired
    public WorkoutServiceImpl(WorkoutDao workoutDao) {
        this.workoutDao = workoutDao;
    }

    @Override
    public Workout findById(Long id) {
        return workoutDao.findOne(id);
    }

    @Override
    public List<Workout> findAll() {
        return (List<Workout>) workoutDao.findAll();
    }

    @Override
    public Workout save(Workout workout) {
        return workoutDao.save(workout);
    }

    @Override
    public void delete(Workout workout) {
        workoutDao.delete(workout);
    }

    @Override
    public List<Workout> getSchedule(Date from, Date to) {
        Predicate<Workout> predicate = (workout -> (workout.getBeginTime().getTime() > from.getTime() && workout.getEndTime().getTime() < to.getTime()));
        return findAll().stream().filter(predicate).sorted(Workout.compareByDates).collect(Collectors.toList());
    }
}
