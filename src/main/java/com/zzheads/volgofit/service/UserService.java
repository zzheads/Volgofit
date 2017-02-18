package com.zzheads.volgofit.service;

import com.zzheads.volgofit.model.User.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.List;

public interface UserService extends UserDetailsService {
    Collection<User> findAll();
    User findById(Long id);
    User save(User user);
    void delete(User user);
    List<String> getRegisteredUsernames();
    User findByName(String name);
}
