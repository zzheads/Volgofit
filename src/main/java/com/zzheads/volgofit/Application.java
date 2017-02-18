package com.zzheads.volgofit;

import com.zzheads.volgofit.model.Imageable.ImagesDirectory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//
//  created by zzheads on 09.02.17
//

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ImagesDirectory directory = new ImagesDirectory();
        directory.createIfNeeded(true);
        SpringApplication.run(Application.class, args);
    }
}
