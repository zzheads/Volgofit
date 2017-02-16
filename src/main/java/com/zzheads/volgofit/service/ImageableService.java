package com.zzheads.volgofit.service;//

import com.zzheads.volgofit.model.Imageable;

//  created by zzheads on 17.02.17
//
public interface ImageableService {
    Imageable findById(String className, Long id);
    Imageable save(String className, Imageable imageable);
    void delete(String className, Imageable imageable);
}
