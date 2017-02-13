package com.zzheads.volgofit.web.api;

import com.zzheads.volgofit.service.RootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by zzheads on 14.02.17.
 */

@RestController
@RequestMapping(value = "/api")
public class RootController {
    private final RootService rootService;

    @Autowired
    public RootController(RootService rootService) {
        this.rootService = rootService;
    }

    @RequestMapping(value = "/info",method = GET, produces = {"application/json"}, consumes = {"application/json"})
    @ResponseStatus(OK)
    public @ResponseBody String getInfo() {
        return rootService.getInfo();
    }
}
