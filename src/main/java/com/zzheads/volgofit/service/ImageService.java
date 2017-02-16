package com.zzheads.volgofit.service;//

import org.springframework.web.multipart.MultipartFile;

import javax.xml.crypto.Data;
import java.io.IOException;

//  created by zzheads on 16.02.17
//
public interface ImageService {
    String save(String fileName, MultipartFile imageFile) throws IOException;
    Data load(String fileName);
}
