package com.zzheads.volgofit.model.Person;

import com.zzheads.volgofit.dto.Person.PersonDto;
import com.zzheads.volgofit.model.Imageable;
import com.zzheads.volgofit.util.DateConverter;
import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 /*
 /* Project: <volgofit>
 /*
 /* created by zzheads on 09.02.17
 /*
 **/

@Entity(name = "person")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person extends Imageable {
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String street;
    private String city;
    private String country;
    private String zipCode;
    private String phone;
    private String email;
    private List<String> social;

    Person() {
    }

    public Person(String firstName, String lastName, String photo, Date birthDate, String street, String city, String country, String zipCode, String phone, String email, List<String> social) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.street = street;
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
        this.phone = phone;
        this.email = email;
        this.social = social;
    }

    public Person(PersonDto personDto) {
        super.setId(personDto.getId());
        this.firstName = personDto.getFirstName();
        this.lastName = personDto.getLastName();
        this.birthDate = DateConverter.stringToDate(personDto.getBirthDate(), false);
        this.street = personDto.getStreet();
        this.city = personDto.getCity();
        this.country = personDto.getCountry();
        this.zipCode = personDto.getZipCode();
        this.phone = personDto.getPhone();
        this.email = personDto.getEmail();
        this.social = Arrays.stream(personDto.getSocial()).collect(Collectors.toList());
    }

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "person_id")
    public Long getId() {
        return super.getId();
    }

    public String getFirstName() {
        return firstName;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "DD/MM/YYYY")
    public Date getBirthDate() {
        return birthDate;
    }

    void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getStreet() {
        return street;
    }

    void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhone() {
        return phone;
    }

    void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
    }

    @ElementCollection
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    public List<String> getSocial() {
        return social;
    }

    void setSocial(List<String> social) {
        this.social = social;
    }

    public String toJson() {
        return (new PersonDto(this)).toJson();
    }

    public Person(String json) {
        PersonDto personDto = new PersonDto(json);
        super.setId(personDto.getId());
        this.firstName = personDto.getFirstName();
        this.lastName = personDto.getLastName();
        this.birthDate = DateConverter.stringToDate(personDto.getBirthDate(), false);
        this.street = personDto.getStreet();
        this.city = personDto.getCity();
        this.country = personDto.getCountry();
        this.zipCode = personDto.getZipCode();
        this.phone = personDto.getPhone();
        this.email = personDto.getEmail();
        this.social = Arrays.stream(personDto.getSocial()).collect(Collectors.toList());
    }
}
