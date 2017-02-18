package com.zzheads.volgofit.service;

import com.zzheads.volgofit.model.User.Role;

import java.util.List;

public interface RoleService {
    Role findById(Long id);
    Role findByName(String name);
    List<Role> findAll();
    Role save(Role role);
    void delete(Role role);
}
