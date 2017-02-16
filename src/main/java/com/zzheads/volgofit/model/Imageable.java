package com.zzheads.volgofit.model;//

import javax.persistence.*;

//  created by zzheads on 16.02.17
//
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Imageable {
    public static String IMAGE_DIRECTORY_NAME = "images/";
    public static String IMAGE_EXTENSION = ".jpg";

    private Long id;
    private String imagePath;

    public Imageable() {
    }

    public Imageable(Long id, String imagePath) {
        this.id = id;
        initImagePath();
    }

    public void initImagePath() {
        String className = getClass().getName();
        this.imagePath = IMAGE_DIRECTORY_NAME + className + "-" + getId().toString() + IMAGE_EXTENSION;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
