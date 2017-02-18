package com.zzheads.volgofit.dto;//

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//  created by zzheads on 18.02.17
//
public class ApiResult {
    private static final Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

    private boolean result;
    private String message;

    public String toJson() {
        return gson.toJson(this, ApiResult.class);
    }

    public ApiResult(boolean result, String message) {
        this.result = result;
        this.message = message;
    }

    public ApiResult(boolean result) {
        this.result = result;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
