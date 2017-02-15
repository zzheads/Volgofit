package com.zzheads.volgofit.dto;//

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zzheads.volgofit.model.Person.Person;
import com.zzheads.volgofit.util.DateConverter;

//  created by zzheads on 15.02.17
//
public class PersonDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String photo;
    private String birthDate;
    private String street;
    private String city;
    private String country;
    private String zipCode;
    private String phone;
    private String email;
    private String[] social;

    public PersonDto(Long id, String firstName, String lastName, String photo, String birthDate, String street, String city, String country, String zipCode, String phone, String email, String[] social) {
        this.id = id;
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

    public PersonDto(Person person) {
        new PersonDto(person.getId(), person.getFirstName(), person.getLastName(), person.getPhoto(), DateConverter.dateToString(person.getBirthDate(), false), person.getStreet(), person.getCity(), person.getCountry(), person.getZipCode(),
                person.getPhone(), person.getEmail(), person.getSocial().toArray(new String[person.getSocial().size()]));
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

    public String getBirthDate() {
        return birthDate;
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

    public String[] getSocial() {
        return social;
    }

    public void setSocial(String[] social) {
        this.social = social;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    private static ExclusionStrategy PersonDtoExclusionStrategy = new ExclusionStrategy() {
        @Override
        public boolean shouldSkipField(FieldAttributes f) {
            return false;
        }

        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return (clazz == Person.class);
        }
    };

    private static Gson gson = new GsonBuilder().setExclusionStrategies(PersonDtoExclusionStrategy).serializeNulls().create();

    public String toJson() {
        return gson.toJson(this, PersonDto.class);
    }

    public PersonDto(String json) {
        PersonDto personDto = gson.fromJson(json, PersonDto.class);
        this.id = personDto.id;
        this.firstName = personDto.firstName;
        this.lastName = personDto.lastName;
        this.photo = personDto.photo;
        this.birthDate = personDto.birthDate;
        this.street = personDto.street;
        this.city = personDto.city;
        this.country = personDto.country;
        this.zipCode = personDto.zipCode;
        this.phone = personDto.phone;
        this.email = personDto.email;
        this.social = personDto.social;
    }
}
