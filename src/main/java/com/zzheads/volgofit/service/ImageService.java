package com.zzheads.volgofit.service;//

import java.io.IOException;

//  created by zzheads on 16.02.17
//

public interface ImageService {
    String save(String fileName, byte[] image) throws IOException;
    byte[] load(String fileName) throws IOException;
    String delete(String fileName);
}
