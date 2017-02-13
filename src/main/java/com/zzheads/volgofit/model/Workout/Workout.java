package com.zzheads.volgofit.model.Workout;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.zzheads.volgofit.model.Person.Client;
import com.zzheads.volgofit.model.Person.Trainer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by zzheads on 10.02.17.
 */

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
    private Trainer trainer;

    @ManyToMany(mappedBy = "workouts", cascade = CascadeType.ALL)
    private List<Client> clients;

    public Workout() {
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

    public static Comparator<Workout> compareByDates = new Comparator<Workout>() {
        @Override
        public int compare(Workout o1, Workout o2) {
            if (o1.getBeginTime().getTime() == o2.getBeginTime().getTime()) return 0;
            if (o1.getBeginTime().getTime() > o2.getBeginTime().getTime()) return 1;
            return -1;
        }
    };

    private static ExclusionStrategy WorkoutExclusionStartegy = new ExclusionStrategy() {
        @Override
        public boolean shouldSkipField(FieldAttributes f) {
            return (Objects.equals(f.getName(), "workouts"));
        }

        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return false;
        }
    };

    private static Gson gson = new GsonBuilder().setExclusionStrategies(WorkoutExclusionStartegy).serializeNulls().create();

    public String toJson() {
        return gson.toJson(this, Workout.class);
    }

    public Workout(String json) {
        Workout workout = gson.fromJson(json, Workout.class);
        this.id = workout.id;
        this.title = workout.title;
        this.description = workout.description;
        this.place = workout.place;
        this.image = workout.image;
        this.beginTime = workout.beginTime;
        this.endTime = workout.endTime;
        this.trainer = workout.trainer;
        this.clients = workout.clients;
    }

    public static String toJson(List<Workout> workouts) {
        Type token = new TypeToken<List<Workout>>(){}.getType();
        return gson.toJson(workouts, token);
    }
}
