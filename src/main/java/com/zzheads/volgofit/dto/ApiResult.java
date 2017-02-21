package com.zzheads.volgofit.dto;//

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//  created by zzheads on 18.02.17
//
public class ApiResult {
    private static final Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

    private boolean success;
    private String message;
    private Object result;

    public String toJson() {
        return gson.toJson(this, ApiResult.class);
    }

    public ApiResult(boolean success, String message, Object result) {
        this.success = success;
        this.message = message;
        this.result = result;
    }

    public ApiResult(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.result = null;
    }

    public ApiResult(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
