package com.zzheads.volgofit.service;//

import com.zzheads.volgofit.model.User.Contact;

import java.util.Collection;

//  created by zzheads on 21.02.17
//
public interface ContactService {
    Collection<Contact> findAll();
    Contact findById(Long id);
    Contact save(Contact contact);
    void delete(Contact contact);
}
