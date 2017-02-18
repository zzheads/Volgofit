package com.zzheads.volgofit.model.Imageable;//

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;

import static com.zzheads.volgofit.model.Imageable.Imageable.DIR_NAME;

//  created by zzheads on 18.02.17
//
public class ImagesDirectory {
    private File directory;
    private String absolutePath;
    private String[] fileNames;
    private int count;

    public ImagesDirectory() {
        this.directory = new File(DIR_NAME);
        this.absolutePath = this.directory.getAbsolutePath();
        this.fileNames = this.directory.list();
        if (this.fileNames != null)
            this.count = this.fileNames.length;
        else
            this.count = 0;
    }

    public void createIfNeeded(@SuppressWarnings("SameParameterValue") boolean withDebugInfo) {
        if (!directory.exists()) {
            boolean result = directory.mkdir();
            if (withDebugInfo) {
                if (result)
                    System.out.printf("Directory %s created.", directory.getAbsolutePath());
                else
                    System.out.printf("Can not create directory %s.", directory.getAbsolutePath());
            }
        }
    }

    public File getDirectory() {
        return directory;
    }

    public void setDirectory(File directory) {
        this.directory = directory;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public String[] getFileNames() {
        return fileNames;
    }

    public void setFileNames(String[] fileNames) {
        this.fileNames = fileNames;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String toJson() {
        final Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.toJson(this, ImagesDirectory.class);
    }
}
