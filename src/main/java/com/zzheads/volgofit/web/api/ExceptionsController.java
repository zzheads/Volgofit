package com.zzheads.volgofit.web.api;

import com.zzheads.volgofit.exceptions.ApiError;
import com.zzheads.volgofit.exceptions.ServerError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 created by zzheads on 13.02.17
 **/

@RestControllerAdvice
public class ExceptionsController {

    @ExceptionHandler(ApiError.class)
    public @ResponseBody String handleApiError(HttpServletRequest req, HttpServletResponse res, ApiError apiError) {
        if (req.getPathInfo()!=null)
            apiError.setPath(req.getPathInfo());
        else
            apiError.setPath(req.getServletPath());
        res.setStatus(apiError.getStatus());
        return apiError.toJson();
    }

    @ExceptionHandler(ServerError.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody String handleIOError(HttpServletRequest req, ServerError error) {
        if (req.getPathInfo()!=null)
            error.setPath(req.getPathInfo());
        else
            error.setPath(req.getServletPath());
        return error.toJson();
    }

}
