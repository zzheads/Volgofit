package com.zzheads.volgofit.model.User;

import com.zzheads.volgofit.dto.User.UserDto;
import com.zzheads.volgofit.model.Imageable.Imageable;
import com.zzheads.volgofit.model.Workout.Workout;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity (name = "_user")
public class User extends Imageable implements UserDetails {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    @Column(length = 100)
    private String password;

    @Column(nullable = false)
    private Boolean enabled;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Profile profile;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Contact contact;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trainer")
    @Column(name = "trainerOf")
    private List<Workout> trainerOf;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @Column(name = "workouts")
    @JoinTable(name = "workouts_clients", joinColumns = { @JoinColumn(name = "client_id") }, inverseJoinColumns = { @JoinColumn(name = "workout_id") })
    private List<Workout> clientOf;

    private String imagePath;

    public User() {
        this.enabled = false;
    }

    public User(String username, String email, String password, Boolean enabled, Role role, Profile profile, Contact contact, List<Workout> trainerOf, List<Workout> clientOf, String imagePath) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.role = role;
        this.profile = profile;
        this.contact = contact;
        this.trainerOf = trainerOf;
        this.clientOf = clientOf;
        this.imagePath = imagePath;
    }

    public User(UserDto userDto) {
        if (userDto != null) {
            this.id = userDto.getId();
            this.username = userDto.getUsername();
            this.password = userDto.getPassword();
            this.email = userDto.getEmail();
            this.enabled = userDto.isEnabled();
            if (userDto.getRole() != null) {
                this.role = new Role(userDto.getRole());
            }
            if (userDto.getProfile() != null) {
                this.profile = new Profile(userDto.getProfile());
            }
            if (userDto.getContact() != null) {
                this.contact = new Contact(userDto.getContact());
            }
            if (userDto.getTrainerOf() != null) {
                this.trainerOf = Arrays.stream(userDto.getTrainerOf()).map(Workout::new).collect(Collectors.toList());
            }
            if (userDto.getClientOf() != null) {
                this.clientOf = Arrays.stream(userDto.getClientOf()).map(Workout::new).collect(Collectors.toList());
            }
            this.imagePath = userDto.getImagePath();
        }
    }

    public User(String json) {
        UserDto userDto = new UserDto(json);
        this.id = userDto.getId();
        this.username = userDto.getUsername();
        this.password = userDto.getPassword();
        this.email = userDto.getEmail();
        this.enabled = userDto.isEnabled();
        if (userDto.getRole() != null) {
            this.role = new Role(userDto.getRole());
        }
        if (userDto.getProfile() != null) {
            this.profile = new Profile(userDto.getProfile());
        }
        if (userDto.getContact() != null) {
            this.contact = new Contact(userDto.getContact());
        }
        if (userDto.getTrainerOf() != null) {
            this.trainerOf = Arrays.stream(userDto.getTrainerOf()).map(Workout::new).collect(Collectors.toList());
        }
        if (userDto.getClientOf() != null) {
            this.clientOf = Arrays.stream(userDto.getClientOf()).map(Workout::new).collect(Collectors.toList());
        }
        this.imagePath = userDto.getImagePath();
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

    public List<Workout> getTrainerOf() {
        return trainerOf;
    }

    public void setTrainerOf(List<Workout> trainerOf) {
        this.trainerOf = trainerOf;
    }

    public List<Workout> getClientOf() {
        return clientOf;
    }

    public void setClientOf(List<Workout> clientOf) {
        this.clientOf = clientOf;
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

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String getImagePath() {
        return this.imagePath;
    }

    @Override
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

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
