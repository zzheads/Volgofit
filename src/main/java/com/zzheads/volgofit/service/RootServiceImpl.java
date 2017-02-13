package com.zzheads.volgofit.service;

import com.zzheads.volgofit.dao.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zzheads on 14.02.17.
 */

@Service
public class RootServiceImpl implements RootService {
    @Autowired
    private RootRepository rootRepository;

    @Override
    public String getInfo() {
        return rootRepository.toJson();
    }
}
