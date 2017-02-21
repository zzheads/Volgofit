package com.zzheads.volgofit.dao;//

import com.zzheads.volgofit.model.User.Profile;
import org.springframework.data.repository.CrudRepository;

//  created by zzheads on 20.02.17
//
public interface ProfileDao extends CrudRepository<Profile, Long> {
}
