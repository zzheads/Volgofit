package com.zzheads.volgofit.service;//

import com.zzheads.volgofit.dao.ProfileDao;
import com.zzheads.volgofit.model.User.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

//  created by zzheads on 21.02.17
//

@Service
public class ProfileServiceImpl implements ProfileService {
    private final ProfileDao profileDao;

    @Autowired
    public ProfileServiceImpl(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    @Override
    public Collection<Profile> findAll() {
        return (Collection<Profile>) profileDao.findAll();
    }

    @Override
    public Profile findById(Long id) {
        return profileDao.findOne(id);
    }

    @Override
    public Profile save(Profile profile) {
        return profileDao.save(profile);
    }

    @Override
    public void delete(Profile profile) {
        profileDao.delete(profile);
    }
}
