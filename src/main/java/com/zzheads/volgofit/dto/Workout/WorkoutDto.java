package com.zzheads.volgofit.dto.Workout;//

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.zzheads.volgofit.model.Person.Client;
import com.zzheads.volgofit.model.Person.Trainer;
import com.zzheads.volgofit.model.Workout.Workout;
import com.zzheads.volgofit.util.DateConverter;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.stream.Collectors;

//  created by zzheads on 15.02.17
//
public class WorkoutDto {
    private Long id;
    private String title;
    private String description;
    private String place;
    private String imagePath;
    private String beginTime;
    private String endTime;
    private Trainer trainer;
    private Client[] clients;

    public WorkoutDto(Workout workout) {
        this.id = workout.getId();
        this.title = workout.getTitle();
        this.description = workout.getDescription();
        this.place = workout.getPlace();
        this.imagePath = workout.getImagePath();
        this.beginTime = DateConverter.dateToString(workout.getBeginTime(), true);
        this.endTime = DateConverter.dateToString(workout.getEndTime(), true);
        this.trainer = workout.getTrainer();
        this.clients = workout.getClients().toArray(new Client[workout.getClients().size()]);
    }

    public WorkoutDto(Long id, String title, String description, String place, String imagePath, String beginTime, String endTime, Trainer trainer, Client[] clients) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.place = place;
        this.imagePath = imagePath;
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Client[] getClients() {
        return clients;
    }

    public void setClients(Client[] clients) {
        this.clients = clients;
    }

    private static ExclusionStrategy WorkoutDtoExclusionStartegy = new ExclusionStrategy() {
        @Override
        public boolean shouldSkipField(FieldAttributes f) {
            return f.getName().equals("workouts");
        }

        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return (clazz == Workout.class);
        }
    };

    private static Gson gson = new GsonBuilder().setExclusionStrategies(WorkoutDtoExclusionStartegy).serializeNulls().create();

    public String toJson() {
        return gson.toJson(this, WorkoutDto.class);
    }

    public WorkoutDto(String json) {
        WorkoutDto workout = gson.fromJson(json, WorkoutDto.class);
        this.id = workout.id;
        this.title = workout.title;
        this.description = workout.description;
        this.place = workout.place;
        this.imagePath = workout.imagePath;
        this.beginTime = workout.beginTime;
        this.endTime = workout.endTime;
        this.trainer = workout.trainer;
        this.clients = workout.clients;
    }

    public static String toJson(Collection<WorkoutDto> workouts) {
        Type token = new TypeToken<Collection<WorkoutDto>>(){}.getType();
        return gson.toJson(workouts, token);
    }

    public static Collection<WorkoutDto> toDto(Collection<Workout> workouts) {
        return workouts.stream().map(WorkoutDto::new).collect(Collectors.toList());
    }

    public static Collection<Workout> fromDto(Collection<WorkoutDto> workoutDtos) {
        return workoutDtos.stream().map(Workout::new).collect(Collectors.toList());
    }

    public static Collection<WorkoutDto> fromJson(String json) {
        Type token = new TypeToken<Collection<WorkoutDto>>(){}.getType();
        return gson.fromJson(json, token);
    }
}
