package com.zzheads.volgofit.service;//

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;

//  created by zzheads on 16.02.17
//

@Service
public class ImageServiceImpl implements ImageService {
    @Override
    public String save(String fileName, MultipartFile imageFile) throws IOException {
        File file = new File(fileName);
        imageFile.transferTo(file);
        return file.getAbsolutePath();
    }

    @Override
    public Data load(String fileName) {
        return null;
    }
}
