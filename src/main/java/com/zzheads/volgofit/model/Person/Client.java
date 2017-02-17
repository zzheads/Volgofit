package com.zzheads.volgofit.model.Person;

import com.zzheads.volgofit.dto.Person.ClientDto;
import com.zzheads.volgofit.model.Workout.Workout;

import javax.persistence.*;
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

@Entity(name = "client")
public class Client extends Person {
    private List<Workout> workouts;

    public Client() {
    }

    public Client(Long id, String firstName, String lastName, String photo, Date birthDate, String street, String city, String country, String zipCode, String phone, String email, String imagePath, List<String> social, List<Workout> workouts) {
        super(id, firstName, lastName, photo, birthDate, street, city, country, zipCode, phone, email, imagePath, social);
        this.workouts = workouts;
    }

    public Client(ClientDto clientDto) {
        super(clientDto);
        this.workouts = Arrays.stream(clientDto.getWorkouts()).collect(Collectors.toList());
    }

    public Client(String json) {
        super(json);
        ClientDto clientDto = new ClientDto(json);
        this.workouts = Arrays.stream(clientDto.getWorkouts()).collect(Collectors.toList());
    }

    public boolean isTimeFree(Date from, Date to) {
        for (Workout workout : this.workouts) {
            if (workout.getEndTime().getTime() > from.getTime() || workout.getBeginTime().getTime() < to.getTime()) return false;
        }
        return true;
    }

    @Override
    @Column(name = "client_id")
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    @ManyToMany(cascade = CascadeType.PERSIST)
    @Column(name = "workouts")
    @JoinTable(name = "workouts_clients", joinColumns = { @JoinColumn(name = "client_id") }, inverseJoinColumns = { @JoinColumn(name = "workout_id") })
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
