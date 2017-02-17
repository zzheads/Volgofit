package com.zzheads.volgofit.dao;

import com.google.gson.Gson;
import com.zzheads.volgofit.model.News.News;
import com.zzheads.volgofit.model.Person.Client;
import com.zzheads.volgofit.model.Person.Person;
import com.zzheads.volgofit.model.Person.Trainer;
import com.zzheads.volgofit.model.Workout.Workout;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

//
// Project volgofit
// Package ${PACKAGE_NAME}
// Created by zzheads on 14.02.17.
//

@Repository
public class RootRepository {
    private static Gson gson = new Gson();

    private class Info {
        private String artifact = "VolgaFit API";
        private String version = "1.9.0";
        private String lastModified = "17/02/2017";
        private String createdBy = "Alexey Papin";
        private String poweredBy[] = {"JavaSpring", "MySQL"};
    }

    private class Model {
        private Map<String, String> endpoints = new HashMap<String, String>() {{
            put("/api/info", "Информация о сервисе");
            put("/api/model", "Информация о ресурсах сервиса");
            put("/api/news", "Новости");
            put("/api/workout", "Тренировки");
            put("/api/trainer", "Тренеры");
            put("/api/client", "Клиенты");
            put("/api/image/{className}.{id}", "Изображения в формате JPEG");
        }};
    }

    public String getInfo() {
        return gson.toJson(new Info(), Info.class);
    }

    public String getModel() {
        return gson.toJson(new Model(), Model.class);
    }

    public String getModel(String className) {
        switch (className) {
            case "news": return gson.toJson(News.class.getFields());
            case "person": return gson.toJson(Person.class.getFields());
            case "workout": return gson.toJson(Workout.class.getFields());
            case "trainer": return gson.toJson(Trainer.class.getFields());
            case "client": return gson.toJson(Client.class.getFields());
        }
        return null;
    }
}
