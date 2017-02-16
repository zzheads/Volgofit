package com.zzheads.volgofit.web.api;//

//  created by zzheads on 16.02.17
//

import com.zzheads.volgofit.exceptions.ApiError;
import com.zzheads.volgofit.model.Imageable;
import com.zzheads.volgofit.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.MultipartConfigElement;
import java.io.IOException;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(value = "/api/image")
public class ImageController {
    private final ImageService imageService;
    private final ImageableService imageableService;

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

    @RequestMapping(value = "/{className}.{id}", method = POST, produces = {APPLICATION_JSON_UTF8_VALUE}, consumes = {MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(OK)
    public void saveImage(@RequestParam("imageFile") MultipartFile imageFile, @PathVariable String className, @PathVariable Long id) throws IOException {
        Imageable imageable = imageableService.findById(className, id);
        if (imageable == null) {
            throw new ApiError(NOT_FOUND);
        }
        imageable.initImagePath();
        imageableService.save(className, imageable);
        imageService.save(imageable.getImagePath(), imageFile.getBytes());
    }

    @RequestMapping(value = "/{className}.{id}", method = GET, consumes = {APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(OK)
    public byte[] loadImage(@PathVariable String className, @PathVariable Long id) throws IOException {
        Imageable imageable = imageableService.findById(className, id);
        if (imageable == null || imageable.getImagePath() == null) {
            throw new ApiError(NOT_FOUND);
        }
        return imageService.load(imageable.getImagePath());
    }

    @RequestMapping(value = "/{className}.{id}", method = DELETE, produces = {APPLICATION_JSON_UTF8_VALUE}, consumes = {APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(NO_CONTENT)
    public void deleteImage(@PathVariable String className, @PathVariable Long id) {
        Imageable imageable = imageableService.findById(className, id);
        if (imageable == null || imageable.getImagePath() == null) {
            throw new ApiError(NOT_FOUND);
        }
        imageService.delete(imageable.getImagePath());
        imageable.setImagePath(null);
        imageableService.save(className, imageable);
    }
}
