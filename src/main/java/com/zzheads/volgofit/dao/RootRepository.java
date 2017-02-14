package com.zzheads.volgofit.dao;

import com.google.gson.Gson;
import org.springframework.stereotype.Repository;

/**
 * Created by zzheads on 14.02.17.
 */

@Repository
public class RootRepository {
    private String artifact = "VolgoFit API";
    private String version = "1.0.0";
    private String lastModified = "13/02/2017";
    private String createdBy = "Alexey Papin";
    private String[] poweredBy = {"JavaSpring", "MySQL"};

    private static Gson gson = new Gson();

    public String toJson() {
        return gson.toJson(this, RootRepository.class);
    }
}
