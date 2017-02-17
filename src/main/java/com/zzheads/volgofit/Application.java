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
            if (imagesDir.mkdir())
                System.out.printf("Directory %s created.", imagesDir.getAbsolutePath());
            else
                System.out.printf("Can not create directory %s.", imagesDir.getAbsolutePath());
        }
        SpringApplication.run(Application.class, args);
    }


}
