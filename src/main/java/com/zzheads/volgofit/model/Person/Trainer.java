package com.zzheads.volgofit.model.Person;

import com.zzheads.volgofit.dto.TrainerDto;
import com.zzheads.volgofit.model.Workout.Workout;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 /*
 /* Project: <volgofit>
 /* created by zzheads on 10.02.17
 /*
 **/

@Entity
public class Trainer extends Person {

    private String speciality;
    private String resume;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trainer")
    @Column(name = "workouts")
    private List<Workout> workouts;

    public Trainer() {
    }

    public Trainer(String firstName, String lastName, String photo, Date birthDate, String street, String city, String country, String zipCode, String phone, String email, List<String> social, String speciality, String resume, List<Workout> workouts) {
        super(firstName, lastName, photo, birthDate, street, city, country, zipCode, phone, email, social);
        this.speciality = speciality;
        this.resume = resume;
        this.workouts = workouts;
    }

    public Trainer(TrainerDto trainerDto) {
        super(trainerDto);
        this.speciality = trainerDto.getSpeciality();
        this.resume = trainerDto.getResume();
        this.workouts = Arrays.stream(trainerDto.getWorkouts()).collect(Collectors.toList());
    }

    public Trainer(String json) {
        super(json);
        TrainerDto trainerDto = new TrainerDto(json);
        this.resume = trainerDto.getResume();
        this.speciality = trainerDto.getSpeciality();
        this.workouts = Arrays.stream(trainerDto.getWorkouts()).collect(Collectors.toList());
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
    }

    public boolean isTimeFree(Date from, Date to) {
        for (Workout workout : this.workouts) {
            if (workout.getEndTime().getTime() > from.getTime() || workout.getBeginTime().getTime() < to.getTime()) return false;
        }
        return true;
    }

    public String toJson() {
        return new TrainerDto(this).toJson();
    }

    public static String toJson(Collection<Trainer> trainers) {
        return TrainerDto.toJson(TrainerDto.toDto(trainers));
    }

    public static Collection<Trainer> fromJson(String json) {
        return TrainerDto.fromDto(TrainerDto.fromJson(json));
    }
}
