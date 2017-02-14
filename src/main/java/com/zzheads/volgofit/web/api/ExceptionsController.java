package com.zzheads.volgofit.web.api;

import com.zzheads.volgofit.exceptions.ApiError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 created by zzheads on 13.02.17
 **/

@RestControllerAdvice
public class ExceptionsController {
    @ExceptionHandler(ApiError.class)
    public @ResponseBody String handleNotFound(HttpServletRequest req, ApiError apiError) {
        if (req.getPathInfo()!=null)
            apiError.setPath(req.getPathInfo());
        else
            apiError.setPath(req.getServletPath());
        return apiError.toJson();
    }
}
