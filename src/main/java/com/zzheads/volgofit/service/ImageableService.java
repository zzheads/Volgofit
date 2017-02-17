package com.zzheads.volgofit.service;//

import com.zzheads.volgofit.model.Imageable.Imageable;

import java.util.Collection;

//  created by zzheads on 17.02.17
//
public interface ImageableService {
    Collection<? extends Imageable> findAll(String className);
    Imageable findById(String className, Long id);
    Imageable save(String className, Long id);
    void delete(String className, Long id);
}
