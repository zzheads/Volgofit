package com.zzheads.volgofit.service;//

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
    public void save(String fileName, byte[] image) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        fos.write(image);
        fos.close();
    }

    @Override
    public byte[] load(String fileName) throws IOException {
        RandomAccessFile file = new RandomAccessFile(fileName, "r");
        byte[] buffer = new byte[(int)file.length()];
        file.readFully(buffer);
        return buffer;
    }

    @Override
    public void delete(String fileName) {
        File file = new File(fileName);
        file.delete();
    }
}
