package com.zzheads.volgofit.web.api;//

//  created by zzheads on 16.02.17
//

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zzheads.volgofit.exceptions.ApiError;
import com.zzheads.volgofit.exceptions.ServerError;
import com.zzheads.volgofit.model.Imageable.Imageable;
import com.zzheads.volgofit.service.ImageService;
import com.zzheads.volgofit.service.ImageableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.MultipartConfigElement;
import java.io.IOException;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(value = "/api/image")
public class ImageController {
    private final ImageService imageService;
    private final ImageableService imageableService;
    private final Gson gson = new GsonBuilder().serializeNulls().create();

    @Autowired
    public ImageController(ImageService imageService, ImageableService imageableService) {
        this.imageService = imageService;
        this.imageableService = imageableService;
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("512KB");
        factory.setMaxRequestSize("512KB");
        return factory.createMultipartConfig();
    }

    @RequestMapping(method = GET, produces = {APPLICATION_JSON_UTF8_VALUE}, consumes = {APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(OK)
    public String getImages() {
        return imageService.getFileNames();
    }

    @RequestMapping(value = "/{className}.{id}", method = POST, produces = {APPLICATION_JSON_UTF8_VALUE}, consumes = {MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(OK)
    public String saveImage(@RequestParam("imageFile") MultipartFile imageFile, @PathVariable String className, @PathVariable Long id) {
        Imageable imageable = imageableService.findById(className, id);
        if (imageable == null) { throw new ApiError(NOT_FOUND); }
        imageable.initImagePath();
        imageableService.save(className, imageable.getId());
        try {
            String msg = imageService.save(imageable.getImagePath(), imageFile.getBytes());
            return gson.toJson(msg);
        } catch (IOException exc) {
            throw new ServerError(exc);
        }
    }

    @RequestMapping(value = "/{className}.{id}", method = GET, consumes = {APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(OK)
    public byte[] loadImage(@PathVariable String className, @PathVariable Long id) {
        Imageable imageable = imageableService.findById(className, id);
        if (imageable == null || imageable.getImagePath() == null) {
            throw new ApiError(NOT_FOUND);
        }
        try {
            return imageService.load(imageable.getImagePath());
        } catch (IOException exc) {
            throw new ServerError(exc);
        }
    }

    @RequestMapping(value = "/{className}.{id}", method = DELETE, produces = {APPLICATION_JSON_UTF8_VALUE}, consumes = {APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(NO_CONTENT)
    public String deleteImage(@PathVariable String className, @PathVariable Long id) {
        Imageable imageable = imageableService.findById(className, id);
        if (imageable == null || imageable.getImagePath() == null) {
            throw new ApiError(NOT_FOUND);
        }
        String msg = imageService.delete(imageable.getImagePath());
        imageable.setImagePath(null);
        imageableService.save(className, imageable.getId());
        return gson.toJson(msg);
    }
}
