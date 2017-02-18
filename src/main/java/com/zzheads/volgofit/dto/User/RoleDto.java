package com.zzheads.volgofit.dto.User;//

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zzheads.volgofit.model.User.Role;

//  created by zzheads on 18.02.17
//
public class RoleDto {
    private static Gson gson = new GsonBuilder().serializeNulls().create();

    private Long id;
    private String name;

    public RoleDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoleDto(Role role) {
        if (role != null) {
            this.id = role.getId();
            this.name = role.getName();
        }
    }

    public RoleDto(String json) {
        RoleDto roleDto = gson.fromJson(json, RoleDto.class);
        if (roleDto != null) {
            this.id = roleDto.id;
            this.name = roleDto.name;
        }
    }

    public String toJson() {
        return gson.toJson(this, RoleDto.class);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
