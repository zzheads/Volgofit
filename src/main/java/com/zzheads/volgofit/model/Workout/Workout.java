package com.zzheads.volgofit.model.Workout;

import com.zzheads.volgofit.dto.Workout.WorkoutDto;
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

@Entity
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workout_id")
    private Long id;

    private String title;
    private String description;
    private String place;
    private String image;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "DD/MM/YYYY HH:MM")
    private Date beginTime;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "DD/MM/YYYY HH:MM")
    private Date endTime;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id")
    private Trainer trainer;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "workouts_clients", joinColumns = { @JoinColumn(name = "workout_id") }, inverseJoinColumns = { @JoinColumn(name = "id") })
    private List<Client> clients;

    public Workout() {
    }

    public Workout(WorkoutDto workoutDto) {
        this.id = workoutDto.getId();
        this.title = workoutDto.getTitle();
        this.description = workoutDto.getDescription();
        this.place = workoutDto.getPlace();
        this.image = workoutDto.getImage();
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

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
        this.id = workoutDto.getId();
        this.title = workoutDto.getTitle();
        this.description = workoutDto.getDescription();
        this.place = workoutDto.getPlace();
        this.image = workoutDto.getImage();
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
