package com.zzheads.volgofit.dto.Person;//

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.zzheads.volgofit.model.Person.Client;
import com.zzheads.volgofit.model.Person.Trainer;
import com.zzheads.volgofit.model.Workout.Workout;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.stream.Collectors;

//  created by zzheads on 15.02.17
//
public class TrainerDto extends PersonDto {
    private String speciality;
    private String resume;
    private Workout[] workouts;

    public TrainerDto(Long id, String firstName, String lastName, String photo, String birthDate, String street, String city, String country, String zipCode, String phone, String email, String[] social, String speciality, String resume, Workout[] workouts) {
        super(id, firstName, lastName, photo, birthDate, street, city, country, zipCode, phone, email, social);
        this.speciality = speciality;
        this.resume = resume;
        this.workouts = workouts;
    }

    public TrainerDto(Trainer trainer) {
        super(trainer);
        this.speciality = trainer.getSpeciality();
        this.resume = trainer.getResume();
        this.workouts = trainer.getWorkouts().toArray(new Workout[trainer.getWorkouts().size()]);
    }

    public TrainerDto(String json) {
        super(json);
        TrainerDto trainerDto = gson.fromJson(json, TrainerDto.class);
        this.speciality = trainerDto.speciality;
        this.resume = trainerDto.resume;
        this.workouts = trainerDto.workouts;
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

    public Workout[] getWorkouts() {
        return workouts;
    }

    public void setWorkouts(Workout[] workouts) {
        this.workouts = workouts;
    }

    private static ExclusionStrategy TrainerDtoExclusionStrategy = new ExclusionStrategy() {
        @Override
        public boolean shouldSkipField(FieldAttributes f) {
            return (f.getName().equals("trainer") && f.getDeclaringClass().equals(Workout.class))
                    || (f.getName().equals("workouts") && f.getDeclaringClass().equals(Client.class));
        }

        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return false;
        }
    };

    private static Gson gson = new GsonBuilder().setExclusionStrategies(TrainerDtoExclusionStrategy).serializeNulls().create();

    public String toJson() {
        return gson.toJson(this, TrainerDto.class);
    }

    public static String toJson(Collection<TrainerDto> trainers) {
        Type token = new TypeToken<Collection<TrainerDto>>(){}.getType();
        return gson.toJson(trainers, token);
    }

    public static Collection<TrainerDto> fromJson(String json) {
        Type token = new TypeToken<Collection<TrainerDto>>(){}.getType();
        return gson.fromJson(json, token);
    }

    public static Collection<TrainerDto> toDto(Collection<Trainer> trainers) {
        return trainers.stream().map(TrainerDto::new).collect(Collectors.toList());
    }

    public static Collection<Trainer> fromDto(Collection<TrainerDto> trainerDtos) {
        return trainerDtos.stream().map(Trainer::new).collect(Collectors.toList());
    }
}
