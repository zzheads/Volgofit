package com.zzheads.volgofit.dao;

import com.zzheads.volgofit.model.User.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends CrudRepository<Role, Long> {
}
