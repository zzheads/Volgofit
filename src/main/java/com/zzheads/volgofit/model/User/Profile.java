package com.zzheads.volgofit.model.User;

import com.zzheads.volgofit.dto.User.ProfileDto;
import com.zzheads.volgofit.util.DateConverter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private User user;

    private String firstName;
    private String lastName;
    private String sex;
    private Double height;
    private Double weight;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "DD/MM/YYYY")
    private Date birthDate;

    public Profile() {}

    public Profile(Long id, User user, String firstName, String lastName, String sex, Double height, Double weight, String photo, Date birthDate) {
        this.id = id;
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.birthDate = birthDate;
    }

    public Profile(ProfileDto profileDto) {
        if (profileDto == null) return;
        this.id = profileDto.getId();
        this.user = new User(profileDto.getUser());
        this.firstName = profileDto.getFirstName();
        this.lastName = profileDto.getLastName();
        this.sex = profileDto.getSex();
        this.height = profileDto.getHeight();
        this.weight = profileDto.getWeight();
        this.birthDate = DateConverter.stringToDate(profileDto.getBirthDate(), false);
    }

    public Profile(String json) {
        ProfileDto profileDto = new ProfileDto(json);
        this.id = profileDto.getId();
        this.user = new User(profileDto.getUser());
        this.firstName = profileDto.getFirstName();
        this.lastName = profileDto.getLastName();
        this.sex = profileDto.getSex();
        this.height = profileDto.getHeight();
        this.weight = profileDto.getWeight();
        this.birthDate = DateConverter.stringToDate(profileDto.getBirthDate(), false);
    }

    public String toJson() {
        return new ProfileDto(this).toJson();
    }

    public static Collection<Profile> fromJson(String json) {
        return ProfileDto.fromDto(ProfileDto.fromJson(json));
    }

    public static String toJson(Collection<Profile> profiles) {
        return ProfileDto.toJson(ProfileDto.toDto(profiles));
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
