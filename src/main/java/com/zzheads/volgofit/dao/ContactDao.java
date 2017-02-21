package com.zzheads.volgofit.dao;//

import com.zzheads.volgofit.model.User.Contact;
import org.springframework.data.repository.CrudRepository;

//  created by zzheads on 21.02.17
//
public interface ContactDao extends CrudRepository<Contact, Long> {
}
