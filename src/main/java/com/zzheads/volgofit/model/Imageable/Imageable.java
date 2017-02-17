package com.zzheads.volgofit.model.Imageable;//

//  created by zzheads on 17.02.17
//
public class Imageable implements ImageableInterface {
    transient private static final String DIR_NAME = "";
    transient private static final String EXT_NAME = ".jpg";
    transient private static final String DIVIDER = "_";

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public String getImagePath() {
        return null;
    }

    @Override
    public void setImagePath(String imagePath) {

    }

    @Override
    public void initImagePath() {
        setImagePath(DIR_NAME + getClass().getName() + DIVIDER + getId().toString() + EXT_NAME);
    }
}
