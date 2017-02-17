package com.zzheads.volgofit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

import static com.zzheads.volgofit.model.Imageable.Imageable.DIR_NAME;

//
//  created by zzheads on 09.02.17
//

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        File imagesDir = new File(DIR_NAME);
        if (!imagesDir.exists()) {
            imagesDir.mkdir();
        }
        SpringApplication.run(Application.class, args);
    }


}
