package com.zzheads.volgofit.service;

import com.zzheads.volgofit.dao.RoleDao;
import com.zzheads.volgofit.model.User.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role findById(Long id) {
        return roleDao.findOne(id);
    }

    @Override
    public Role findByName(String name) {
        for (Role role : roleDao.findAll()) {
            if (role.getName()!=null && role.getName().equals(name))
                return role;
        }
        return null;
    }

    @Override
    public List<Role> findAll() {
        return (List<Role>) roleDao.findAll();
    }

    @Override
    public Role save(Role role) {
        return roleDao.save(role);
    }

    @Override
    public void delete(Role role) {
        roleDao.delete(role);
    }
}
