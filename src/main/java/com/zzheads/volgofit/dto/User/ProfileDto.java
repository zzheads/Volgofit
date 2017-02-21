package com.zzheads.volgofit.dto.User;//

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.zzheads.volgofit.model.User.Profile;
import com.zzheads.volgofit.util.DateConverter;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.stream.Collectors;

//  created by zzheads on 20.02.17
//
public class ProfileDto {
    private static final Type collectionType = new TypeToken<Collection<ProfileDto>>(){}.getType();
    private static final Gson gson = new GsonBuilder().serializeNulls().create();

    private Long id;
    private UserDto user;
    private String firstName;
    private String lastName;
    private String sex;
    private Double height;
    private Double weight;
    private String birthDate;

    private ProfileDto(Long id, UserDto user, String firstName, String lastName, String sex, Double height, Double weight, String birthDate) {
        this.id = id;
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.birthDate = birthDate;
    }

    public ProfileDto(Profile profile) {
        if (profile == null) return;
        this.id = profile.getId();
        this.user = new UserDto(profile.getUser());
        this.firstName = profile.getFirstName();
        this.lastName = profile.getLastName();
        this.setSex(profile.getSex());
        this.height = profile.getHeight();
        this.weight = profile.getWeight();
        this.birthDate = DateConverter.dateToString(profile.getBirthDate(), false);
    }

    public ProfileDto(String json) {
        ProfileDto profileDto = gson.fromJson(json, ProfileDto.class);
        this.id = profileDto.id;
        this.user = profileDto.user;
        this.firstName = profileDto.firstName;
        this.lastName = profileDto.lastName;
        this.sex = profileDto.sex;
        this.height = profileDto.height;
        this.weight = profileDto.weight;
        this.birthDate = profileDto.birthDate;
    }

    public String toJson() {
        return gson.toJson(this, ProfileDto.class);
    }

    public static Collection<ProfileDto> toDto(Collection<Profile> profiles) {
        return profiles.stream().map(ProfileDto::new).collect(Collectors.toList());
    }

    public static Collection<Profile> fromDto(Collection<ProfileDto> profileDtos) {
        return profileDtos.stream().map(Profile::new).collect(Collectors.toList());
    }

    public static String toJson(Collection<ProfileDto> profileDtos) {
        return gson.toJson(profileDtos, collectionType);
    }

    public static Collection<ProfileDto> fromJson(String json) {
        return gson.fromJson(json, collectionType);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
