package com.zzheads.volgofit.model.Person;

import com.zzheads.volgofit.dto.ClientDto;
import com.zzheads.volgofit.model.Workout.Workout;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    public Client(ClientDto clientDto) {
        super(clientDto);
        this.workouts = Arrays.stream(clientDto.getWorkouts()).collect(Collectors.toList());
    }

    public Client(String json) {
        new Client(new ClientDto(json));
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

    public String toJson() {
        return new ClientDto(this).toJson();
    }

    public static String toJson(Collection<Client> clients) {
        return ClientDto.toJson(ClientDto.toDto(clients));
    }

    public static Collection<Client> fromJson(String json) {
        return ClientDto.fromDto(ClientDto.fromJson(json));
    }
}
