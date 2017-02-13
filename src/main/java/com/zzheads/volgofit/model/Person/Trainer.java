package com.zzheads.volgofit.model.Person;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.zzheads.volgofit.model.Workout.Workout;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by zzheads on 10.02.17.
 */

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

    private static ExclusionStrategy TrainerExclusionStartegy = new ExclusionStrategy() {
        @Override
        public boolean shouldSkipField(FieldAttributes f) {
            return (Objects.equals(f.getName(), "trainer"));
        }

        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return false;
        }
    };

    private static Gson gson = new GsonBuilder().setExclusionStrategies(TrainerExclusionStartegy).serializeNulls().create();

    public String toJson() {
        return gson.toJson(this, Trainer.class);
    }

    public Trainer(String json) {
        Trainer trainer = gson.fromJson(json, Trainer.class);
        this.setFirstName(trainer.getFirstName());
        this.setLastName(trainer.getLastName());
        this.setPhoto(trainer.getPhoto());
        this.setBirthDate(trainer.getBirthDate());
        this.setStreet(trainer.getStreet());
        this.setCity(trainer.getCity());
        this.setCountry(trainer.getCountry());
        this.setZipCode(trainer.getZipCode());
        this.setPhone(trainer.getPhone());
        this.setEmail(trainer.getEmail());
        this.setSocial(trainer.getSocial());
        this.speciality = trainer.speciality;
        this.resume = trainer.resume;
        this.workouts = trainer.workouts;
    }

    public static String toJson(List<Trainer> trainers) {
        Type token = new TypeToken<List<Trainer>>(){}.getType();
        return gson.toJson(trainers, token);
    }
}
