package com.zzheads.volgofit.service;//

import com.zzheads.volgofit.model.Imageable.ImagesDirectory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

//  created by zzheads on 16.02.17
//

@Service
public class ImageServiceImpl implements ImageService {

    @Override
    public String getFileNames() {
        return new ImagesDirectory().toJson();
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
        if (file.delete())
            return String.format("File %s deleted.", file.getAbsolutePath());
        else
            return String.format("Can not delete %s file.", file.getAbsolutePath());
    }
}
