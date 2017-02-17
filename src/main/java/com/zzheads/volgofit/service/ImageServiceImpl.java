package com.zzheads.volgofit.service;//

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;

import static com.zzheads.volgofit.model.Imageable.Imageable.DIR_NAME;

//  created by zzheads on 16.02.17
//

@Service
public class ImageServiceImpl implements ImageService {
    transient private static Gson gson = new GsonBuilder().serializeNulls().create();

    @Override
    public String getFileNames() {
        class Directory {
            private File directory;
            private ArrayList<String> fileNames;

            private Directory() {
                this.directory = new File(DIR_NAME);
                this.fileNames = new ArrayList<>(Arrays.asList(this.directory.list()));
            }
        }
        Directory dir = new Directory();
        return gson.toJson(dir, Directory.class);
    }

    @Override
    public String save(String fileName, byte[] image) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        fos.write(image);
        fos.close();
        return String.format("File %s saved.", new File(fileName).getAbsolutePath());
    }

    @Override
    public byte[] load(String fileName) throws IOException {
        RandomAccessFile file = new RandomAccessFile(fileName, "r");
        byte[] buffer = new byte[(int)file.length()];
        file.readFully(buffer);
        return buffer;
    }

    @Override
    public String delete(String fileName) {
        File file = new File(fileName);
        file.delete();
        return String.format("File %s deleted.", file.getAbsolutePath());
    }
}
