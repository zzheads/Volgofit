package com.zzheads.volgofit.dto.User;//

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.zzheads.volgofit.dto.Workout.WorkoutDto;
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
    private String email;
    private Boolean enabled;
    private RoleDto role;
    private ProfileDto profile;
    private ContactDto contact;
    private WorkoutDto[] trainerOf;
    private WorkoutDto[] clientOf;
    private String imagePath;

    public UserDto(Long id, String username, String password, String email, Boolean enabled, RoleDto role, ProfileDto profile, ContactDto contact, WorkoutDto[] trainerOf, WorkoutDto[] clientOf, String imagePath) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.role = role;
        this.profile = profile;
        this.contact = contact;
        this.trainerOf = trainerOf;
        this.clientOf = clientOf;
        this.imagePath = imagePath;
    }

    public UserDto(User user) {
        if (user != null) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.password = user.getPassword();
            this.email = user.getEmail();
            this.enabled = user.isEnabled();
            if (user.getRole() != null) {
                this.role = new RoleDto(user.getRole());
            }
            if (user.getProfile() != null) {
                this.profile = new ProfileDto(user.getProfile());
            }
            if (user.getContact() != null) {
                this.contact = new ContactDto(user.getContact());
            }
            if (user.getTrainerOf() != null) {
                this.trainerOf = user.getTrainerOf().stream().map(WorkoutDto::new).collect(Collectors.toList()).toArray(new WorkoutDto[user.getTrainerOf().size()]);
            }
            if (user.getClientOf() != null) {
                this.clientOf = user.getClientOf().stream().map(WorkoutDto::new).collect(Collectors.toList()).toArray(new WorkoutDto[user.getClientOf().size()]);
            }
            this.imagePath = user.getImagePath();
        }
    }

    public UserDto(String json) {
        UserDto userDto = gson.fromJson(json, UserDto.class);
        if (userDto != null) {
            this.id = userDto.id;
            this.username = userDto.username;
            this.password = userDto.password;
            this.email = userDto.email;
            this.enabled = userDto.enabled;
            this.role = userDto.role;
            this.profile = userDto.profile;
            this.contact = userDto.contact;
            this.trainerOf = userDto.trainerOf;
            this.clientOf = userDto.clientOf;
            this.imagePath = userDto.imagePath;
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

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public RoleDto getRole() {
        return role;
    }

    public void setRole(RoleDto role) {
        this.role = role;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public ProfileDto getProfile() {
        return profile;
    }

    public void setProfile(ProfileDto profile) {
        this.profile = profile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ContactDto getContact() {
        return contact;
    }

    public void setContact(ContactDto contact) {
        this.contact = contact;
    }

    public WorkoutDto[] getTrainerOf() {
        return trainerOf;
    }

    public void setTrainerOf(WorkoutDto[] trainerOf) {
        this.trainerOf = trainerOf;
    }

    public WorkoutDto[] getClientOf() {
        return clientOf;
    }

    public void setClientOf(WorkoutDto[] clientOf) {
        this.clientOf = clientOf;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
