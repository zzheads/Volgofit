package com.zzheads.volgofit.dao;

import com.google.gson.Gson;
import com.zzheads.volgofit.model.News.News;
import com.zzheads.volgofit.model.User.User;
import com.zzheads.volgofit.model.Workout.Workout;
import com.zzheads.volgofit.util.DateConverter;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
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
        Map<String, String> model = new HashMap<>();
        switch (className) {
            case "news": for (Field field : News.class.getDeclaredFields()) {
                model.put(field.getName(), DateConverter.getWordAfterLastPoint(field.getType().getName()));
            }
            break;

            case "user": for (Field field : User.class.getDeclaredFields()) {
                model.put(field.getName(), DateConverter.getWordAfterLastPoint(field.getType().getName()));
            }
            break;

            case "workout": for (Field field : Workout.class.getDeclaredFields()) {
                model.put(field.getName(), DateConverter.getWordAfterLastPoint(field.getType().getName()));
            }
            break;

            default: return null;
        }
        return gson.toJson(model);
    }
}
