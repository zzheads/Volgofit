package com.zzheads.volgofit.exceptions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpStatus;

public class ApiError extends RuntimeException {
    private int status;
    private String message;
    private String path;

    public ApiError(HttpStatus code) {
        super(code.getReasonPhrase());
        this.status = code.value();
        this.message = code.getReasonPhrase();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private static Gson gson = new GsonBuilder().serializeNulls().create();

    public String toJson() {
        return gson.toJson(this, ApiError.class);
    }
}
