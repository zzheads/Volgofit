package com.zzheads.volgofit.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

/**
 created by zzheads on 12.02.17
 **/

public class LoggedUser {
    private static String ADMIN = "ROLE_ADMIN";

    private static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static boolean isAdmin() {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return authorities.stream().anyMatch(auth -> auth.getAuthority().equals(ADMIN));
    }

}
