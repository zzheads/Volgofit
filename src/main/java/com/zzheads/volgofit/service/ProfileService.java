package com.zzheads.volgofit.service;//

import com.zzheads.volgofit.model.User.Profile;

import java.util.Collection;

//  created by zzheads on 21.02.17
//
public interface ProfileService {
    Collection<Profile> findAll();
    Profile findById(Long id);
    Profile save(Profile profile);
    void delete(Profile profile);
}
