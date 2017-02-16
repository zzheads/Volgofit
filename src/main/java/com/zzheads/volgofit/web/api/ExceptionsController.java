package com.zzheads.volgofit.web.api;

import com.google.gson.Gson;
import com.zzheads.volgofit.exceptions.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 created by zzheads on 13.02.17
 **/

@RestControllerAdvice
public class ExceptionsController {

    @ExceptionHandler(ApiError.class)
    public @ResponseBody String handleApiError(HttpServletRequest req, ApiError apiError) {
        if (req.getPathInfo()!=null)
            apiError.setPath(req.getPathInfo());
        else
            apiError.setPath(req.getServletPath());
        return apiError.toJson();
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody String handleIOError(HttpServletRequest req, IOException exc) {
        Gson gson = new Gson();
        return gson.toJson(exc, IOException.class);
    }

}
