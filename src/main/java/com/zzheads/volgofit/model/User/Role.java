package com.zzheads.volgofit.model.User;

import com.zzheads.volgofit.dto.User.RoleDto;

import javax.persistence.*;

@Entity(name = "role")
public class Role {
    public static final String USER_ROLE = "USER";
    public static final String ADMIN_ROLE = "ADMIN";

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Role() {}

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role(RoleDto roleDto) {
        if (roleDto != null) {
            this.id = roleDto.getId();
            this.name = roleDto.getName();
        }
    }

    public Role(String json) {
        RoleDto roleDto = new RoleDto(json);
        this.id = roleDto.getId();
        this.name = roleDto.getName();
    }

    public String toJson() {
        return new RoleDto(this).toJson();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return getName().equals(role.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}
