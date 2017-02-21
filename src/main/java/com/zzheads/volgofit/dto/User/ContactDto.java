package com.zzheads.volgofit.dto.User;//

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.zzheads.volgofit.model.User.Contact;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.stream.Collectors;

//  created by zzheads on 21.02.17
//
public class ContactDto {
    private static final Type collectionType = new TypeToken<Collection<ContactDto>>(){}.getType();
    private static final Gson gson = new GsonBuilder().serializeNulls().create();

    private Long id;
    private UserDto user;
    private String street;
    private String city;
    private String country;
    private String zipCode;
    private String phone;
    private String[] social;

    public ContactDto(Long id, UserDto user, String street, String city, String country, String zipCode, String phone, String[] social) {
        this.id = id;
        this.user = user;
        this.street = street;
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
        this.phone = phone;
        this.social = social;
    }

    public ContactDto(Contact contact) {
        if (contact == null) return;
        this.id = contact.getId();
        this.user = new UserDto(contact.getUser());
        this.street = contact.getStreet();
        this.city = contact.getCity();
        this.country = contact.getCountry();
        this.zipCode = contact.getZipCode();
        this.phone = contact.getPhone();
        this.social = contact.getSocial().toArray(new String[contact.getSocial().size()]);
    }

    public ContactDto(String json) {
        ContactDto contactDto = gson.fromJson(json, ContactDto.class);
        this.id = contactDto.id;
        this.user = contactDto.user;
        this.street = contactDto.street;
        this.city = contactDto.city;
        this.country = contactDto.country;
        this.zipCode = contactDto.zipCode;
        this.phone = contactDto.phone;
        this.social = contactDto.social;
    }

    public String toJson() {
        return gson.toJson(this, ContactDto.class);
    }

    public static Collection<ContactDto> toDto(Collection<Contact> contacts) {
        return contacts.stream().map(ContactDto::new).collect(Collectors.toList());
    }

    public static Collection<Contact> fromDto(Collection<ContactDto> contactDtos) {
        return contactDtos.stream().map(Contact::new).collect(Collectors.toList());
    }

    public static String toJson(Collection<ContactDto> contactDtos) {
        return gson.toJson(contactDtos, collectionType);
    }

    public static Collection<ContactDto> fromJson(String json) {
        return gson.fromJson(json, collectionType);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
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

    public String[] getSocial() {
        return social;
    }

    public void setSocial(String[] social) {
        this.social = social;
    }
}
