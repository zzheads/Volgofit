package com.zzheads.volgofit.service;

import com.zzheads.volgofit.dto.ApiResult;
import com.zzheads.volgofit.model.User.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;

public interface UserService extends UserDetailsService {
    Collection<User> findAll();
    User findById(Long id);
    User save(User user);
    void delete(User user);

    Collection<String> getRegisteredUsernames();
    User findByName(String name);
    void encodePassword(User user);
    boolean match(String raw, String encoded);
    ApiResult checkUserCreds(User user);

    String getRawToken(User user);
    String getEncodedToken(User user);
    ApiResult sendMail(String to, String from, String body, String subject);
}
