package com.zzheads.volgofit.dto.User;//

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.zzheads.volgofit.model.User.User;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.stream.Collectors;

//  created by zzheads on 18.02.17
//
public class UserDto {
    private static final Gson gson = new GsonBuilder().serializeNulls().create();
    private static final Type collectionToken = new TypeToken<Collection<UserDto>>(){}.getType();

    private Long id;
    private String username;
    private String password;
    private boolean enabled;
    private RoleDto role;

    public UserDto(User user) {
        if (user != null) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.password = user.getPassword();
            this.enabled = user.isEnabled();
            this.role = new RoleDto(user.getRole());
        }
    }

    public UserDto(Long id, String username, String password, boolean enabled, RoleDto role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.role = role;
    }

    public UserDto(String json) {
        UserDto userDto = gson.fromJson(json, UserDto.class);
        if (userDto != null) {
            this.id = userDto.id;
            this.username = userDto.username;
            this.password = userDto.password;
            this.enabled = userDto.enabled;
            this.role = userDto.role;
        }
    }

    public String toJson() {
        return gson.toJson(this, UserDto.class);
    }

    public static Collection<UserDto> toDto(Collection<User> users) {
        return users.stream().map(UserDto::new).collect(Collectors.toList());
    }

    public static Collection<User> fromDto(Collection<UserDto> users) {
        return users.stream().map(User::new).collect(Collectors.toList());
    }

    public static String toJson(Collection<UserDto> userDtos) {
        return gson.toJson(userDtos, collectionToken);
    }

    public static Collection<UserDto> fromJson(String json) {
        return gson.fromJson(json, collectionToken);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public RoleDto getRole() {
        return role;
    }

    public void setRole(RoleDto role) {
        this.role = role;
    }
}
