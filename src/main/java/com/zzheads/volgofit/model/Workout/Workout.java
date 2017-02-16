package com.zzheads.volgofit.model.Workout;

import com.zzheads.volgofit.dto.Workout.WorkoutDto;
import com.zzheads.volgofit.model.Imageable;
import com.zzheads.volgofit.model.Person.Client;
import com.zzheads.volgofit.model.Person.Trainer;
import com.zzheads.volgofit.util.DateConverter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 /
 / Project: <volgofit>
 / created by zzheads on 10.02.17
 /
 **/

@Entity(name = "workout")
public class Workout extends Imageable {
    private String title;
    private String description;
    private String place;
    private String image;
    private Date beginTime;
    private Date endTime;
    private Trainer trainer;
    private List<Client> clients;

    public Workout() {
    }

    public Workout(WorkoutDto workoutDto) {
        super.setId(workoutDto.getId());
        this.title = workoutDto.getTitle();
        this.description = workoutDto.getDescription();
        this.place = workoutDto.getPlace();
        this.image = workoutDto.getImagePath();
        this.beginTime = DateConverter.stringToDate(workoutDto.getBeginTime(), true);
        this.endTime = DateConverter.stringToDate(workoutDto.getEndTime(), true);
        this.trainer = workoutDto.getTrainer();
        this.clients = Arrays.stream(workoutDto.getClients()).collect(Collectors.toList());
    }

    public Workout(String title, String description, String place, String image, Date beginTime, Date endTime, Trainer trainer, List<Client> clients) {
        this.title = title;
        this.description = description;
        this.place = place;
        this.image = image;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.trainer = trainer;
        this.clients = clients;
    }

    @Override
    @Column(name = "workout_id")
    public Long getId() {
        return super.getId();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "DD/MM/YYYY HH:MM")
    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "DD/MM/YYYY HH:MM")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "trainer_id")
    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "workouts_clients", joinColumns = { @JoinColumn(name = "workout_id") }, inverseJoinColumns = { @JoinColumn(name = "client_id") })
    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public void removeClient(Client client) {
        clients.remove(client);
    }

    public static Comparator<Workout> compareByDates = ( (o1, o2) -> {
        if (o1.getBeginTime().getTime() == o2.getBeginTime().getTime()) return 0;
        if (o1.getBeginTime().getTime() > o2.getBeginTime().getTime()) return 1;
        return -1;
    });

    public String toJson() {
        return (new WorkoutDto(this)).toJson();
    }

    public Workout(String json) {
        WorkoutDto workoutDto = new WorkoutDto(json);
        super.setId(workoutDto.getId());
        this.title = workoutDto.getTitle();
        this.description = workoutDto.getDescription();
        this.place = workoutDto.getPlace();
        this.image = workoutDto.getImagePath();
        this.beginTime = DateConverter.stringToDate(workoutDto.getBeginTime(), true);
        this.endTime = DateConverter.stringToDate(workoutDto.getEndTime(), true);
        this.trainer = workoutDto.getTrainer();
        this.clients = Arrays.stream(workoutDto.getClients()).collect(Collectors.toList());
    }

    public static String toJson(Collection<Workout> workouts) {
        return WorkoutDto.toJson(WorkoutDto.toDto(workouts));
    }

    public static Collection<Workout> fromJson(String json) {
        return WorkoutDto.fromDto(WorkoutDto.fromJson(json));
    }
}
