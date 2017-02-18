package com.zzheads.volgofit.model.User;

import com.zzheads.volgofit.dto.User.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity (name = "_user")
public class User implements UserDetails {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(length = 100)
    private String password;

    @Column(nullable = false)
    private Boolean enabled;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "role_id")
    private Role role;

    public User() {
        this.enabled = false;
    }

    public User(String username, String password, Boolean enabled, Role role) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.role = role;
    }

    public User(UserDto userDto) {
        if (userDto != null) {
            this.id = userDto.getId();
            this.username = userDto.getUsername();
            this.password = userDto.getPassword();
            this.enabled = userDto.isEnabled();
            this.role = new Role(userDto.getRole());
        }
    }

    public User(String json) {
        UserDto userDto = new UserDto(json);
        this.id = userDto.getId();
        this.username = userDto.getUsername();
        this.password = userDto.getPassword();
        this.enabled = userDto.isEnabled();
        if (userDto.getRole() != null)
            this.role = new Role(userDto.getRole());
        else
            this.role = null;
    }

    public String toJson() {
        return new UserDto(this).toJson();
    }

    public static String toJson(Collection<User> users) {
        return UserDto.toJson(UserDto.toDto(users));
    }

    public static Collection<User> fromJson(String json) {
        return UserDto.fromDto(UserDto.fromJson(json));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getEnabled() {return this.enabled;}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getName()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
