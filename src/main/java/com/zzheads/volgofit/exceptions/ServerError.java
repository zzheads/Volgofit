package com.zzheads.volgofit.exceptions;//

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

//  created by zzheads on 17.02.17
//
public class ServerError extends RuntimeException {
    private static final int status = 500;
    private String message;
    private String path;

//    "detailMessage"
//            "stackTrace"
//            "suppressedExceptions"

    public ServerError(String detailMessage, StackTraceElement[] stackTrace, Throwable[] suppressedExceptions, String message) {
        super(detailMessage);
        super.setStackTrace(stackTrace);
        for (Throwable exc : suppressedExceptions) super.addSuppressed(exc);
        this.message = message;
    }

    public ServerError(String message, String path) {
        this.message = message;
        this.path = path;
    }

    public ServerError(IOException exc) {
        super(exc.getMessage());
        super.setStackTrace(exc.getStackTrace());
        for (Throwable e : exc.getSuppressed()) super.addSuppressed(e);
        this.message = exc.getMessage();
    }

    public static int getStatus() {
        return status;
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

    public String toJson() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.toJson(this, ServerError.class);
    }
}
