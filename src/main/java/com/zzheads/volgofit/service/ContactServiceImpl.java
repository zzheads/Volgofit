package com.zzheads.volgofit.service;//

import com.zzheads.volgofit.dao.ContactDao;
import com.zzheads.volgofit.model.User.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

//  created by zzheads on 21.02.17
//

@Service
public class ContactServiceImpl implements ContactService {
    private final ContactDao contactDao;

    @Autowired
    public ContactServiceImpl(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @Override
    public Collection<Contact> findAll() {
        return (Collection<Contact>) contactDao.findAll();
    }

    @Override
    public Contact findById(Long id) {
        return contactDao.findOne(id);
    }

    @Override
    public Contact save(Contact contact) {
        return contactDao.save(contact);
    }

    @Override
    public void delete(Contact contact) {
        contactDao.delete(contact);
    }
}
