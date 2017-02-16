package com.zzheads.volgofit.web.api;//

//  created by zzheads on 16.02.17
//

import com.zzheads.volgofit.exceptions.ApiError;
import com.zzheads.volgofit.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/api/image")
public class ImageController {
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("512KB");
        factory.setMaxRequestSize("512KB");
        return factory.createMultipartConfig();
    }

    @RequestMapping(method = POST, produces = {APPLICATION_JSON_UTF8_VALUE}, consumes = {MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(OK)
    public @ResponseBody String saveImage(@RequestParam("imageFile") MultipartFile imageFile, HttpServletRequest request) {
        String uploadsDir = "/uploads/";
        String realPathtoUploads =  request.getServletContext().getRealPath(uploadsDir);
        if(! new File(realPathtoUploads).exists())
        {
            new File(realPathtoUploads).mkdir();
        }

        String orgName = imageFile.getOriginalFilename();
        String filePath = realPathtoUploads + orgName;
        File dest = new File(filePath);

        try {
            imageFile.transferTo(dest);
            return dest.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            ApiError error = new ApiError(INTERNAL_SERVER_ERROR);
            error.setMessage(e.getMessage());
            throw error;
        }
    }

    @RequestMapping(value = "/{fileName}", method = GET, produces = {IMAGE_JPEG_VALUE}, consumes = {APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(OK)
    public @ResponseBody byte[] loadImage(@PathVariable String fileName) {
        RandomAccessFile file = null;
        try {
            file = new RandomAccessFile(fileName, "r");
            byte[] buffer = new byte[(int)file.length()];
            file.readFully(buffer);
            return buffer;
        } catch (IOException e) {
            e.printStackTrace();
            ApiError error = new ApiError(INTERNAL_SERVER_ERROR);
            error.setMessage(e.getMessage());
            throw error;
        }
    }
}
