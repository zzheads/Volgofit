package com.zzheads.volgofit.web.api;

import com.zzheads.volgofit.exceptions.ServerError;
import com.zzheads.volgofit.service.RootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 created by zzheads on 14.02.17
 **/

@RestController
@RequestMapping(value = "/api")
public class RootController {
    private final RootService rootService;

    @Autowired
    public RootController(RootService rootService) {
        this.rootService = rootService;
    }

    @RequestMapping(value = "/info",method = GET, produces = {APPLICATION_JSON_UTF8_VALUE}, consumes = {APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(OK)
    public @ResponseBody String getInfo() {
        return rootService.getInfo();
    }

    @RequestMapping(value = "/model",method = GET, produces = {APPLICATION_JSON_UTF8_VALUE}, consumes = {APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(OK)
    public @ResponseBody String getModel() {
        return rootService.getModel();
    }

    @RequestMapping(value = "/model/{className}",method = GET, produces = {APPLICATION_JSON_UTF8_VALUE}, consumes = {APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(OK)
    public @ResponseBody String getModel(@PathVariable String className) {
        String answer = rootService.getModel(className);
        if (answer == null) {
            throw new ServerError(String.format("Can not find %s class.", className), null);
        }
        return answer;
    }
}
