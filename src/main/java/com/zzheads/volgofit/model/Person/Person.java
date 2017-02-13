package com.zzheads.volgofit.model.Person;

import org.hibernate.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

/**
 * Created by zzheads on 09.02.17.
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String firstName;
    private String lastName;
    private String photo;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "DD/MM/YYYY")
    private Date birthDate;

    private String street;
    private String city;
    private String country;
    private String zipCode;

    private String phone;
    private String email;

    @ElementCollection
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<String> social;

    Person() {
    }

    public Person(String firstName, String lastName, String photo, Date birthDate, String street, String city, String country, String zipCode, String phone, String email, List<String> social) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.photo = photo;
        this.birthDate = birthDate;
        this.street = street;
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
        this.phone = phone;
        this.email = email;
        this.social = social;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getSocial() {
        return social;
    }

    public void setSocial(List<String> social) {
        this.social = social;
    }
}
