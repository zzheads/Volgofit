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
import javax.persistence.ManyToMany;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 /*
 /* volgofit - ${PACKAGE_NAME}
 /* created by zzheads on 10.02.17
 /*
 **/

@Entity
public class Client extends Person {

    @ManyToMany(cascade = CascadeType.PERSIST)
    @Column(name = "workouts")
    private List<Workout> workouts;

    public Client() {
    }

    public Client(String firstName, String lastName, String photo, Date birthDate, String street, String city, String country, String zipCode, String phone, String email, List<String> social, List<Workout> workouts) {
        super(firstName, lastName, photo, birthDate, street, city, country, zipCode, phone, email, social);
        this.workouts = workouts;
    }

    public boolean isTimeFree(Date from, Date to) {
        for (Workout workout : this.workouts) {
            if (workout.getEndTime().getTime() > from.getTime() || workout.getBeginTime().getTime() < to.getTime()) return false;
        }
        return true;
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
    }

    public void addWorkout(Workout workout) {
        workouts.add(workout);
    }

    public void removeWorkout(Workout workout) {
        workouts.remove(workout);
    }

    private static ExclusionStrategy ClientExclusionStartegy = new ExclusionStrategy() {
        @Override
        public boolean shouldSkipField(FieldAttributes f) {
            return (Objects.equals(f.getName(), "client"));
        }

        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return false;
        }
    };

    private static Gson gson = new GsonBuilder().setExclusionStrategies(ClientExclusionStartegy).serializeNulls().create();

    public String toJson() {
        return gson.toJson(this, Client.class);
    }

    public Client(String json) {
        Client client = gson.fromJson(json, Client.class);
        this.setFirstName(client.getFirstName());
        this.setLastName(client.getLastName());
        this.setPhoto(client.getPhoto());
        this.setBirthDate(client.getBirthDate());
        this.setStreet(client.getStreet());
        this.setCity(client.getCity());
        this.setCountry(client.getCountry());
        this.setZipCode(client.getZipCode());
        this.setPhone(client.getPhone());
        this.setEmail(client.getEmail());
        this.setSocial(client.getSocial());
        this.workouts = client.workouts;
    }

    public static String toJson(List<Client> clients) {
        Type token = new TypeToken<List<Client>>(){}.getType();
        return gson.toJson(clients, token);
    }
}
