package com.zzheads.volgofit.dao;

import com.google.gson.Gson;
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
        private String artifact = "VolgoFit API";
        private String version = "1.0.0";
        private String lastModified = "13/02/2017";
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
        }};
    }

    public String getInfo() {
        return gson.toJson(new Info(), Info.class);
    }

    public String getModel() {
        return gson.toJson(new Model(), Model.class);
    }
}
