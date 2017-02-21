package com.zzheads.volgofit.model.User;//

import com.zzheads.volgofit.dto.User.ContactDto;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

//  created by zzheads on 21.02.17
//

@Entity(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private User user;

    private String street;
    private String city;
    private String country;
    private String zipCode;
    private String phone;

    @ElementCollection
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<String> social;

    public Contact(){}

    public Contact(Long id, User user, String street, String city, String country, String zipCode, String phone, List<String> social) {
        this.id = id;
        this.user = user;
        this.street = street;
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
        this.phone = phone;
        this.social = social;
    }

    public Contact(ContactDto contactDto) {
        if (contactDto == null) return;
        this.id = contactDto.getId();
        if (contactDto.getUser() != null) {
            this.user = new User(contactDto.getUser());
        }
        this.street = contactDto.getStreet();
        this.city = contactDto.getCity();
        this.country = contactDto.getCountry();
        this.zipCode = contactDto.getZipCode();
        this.phone = contactDto.getPhone();
        if (contactDto.getSocial() != null) {
            this.social = Arrays.stream(contactDto.getSocial()).collect(Collectors.toList());
        }
    }

    public Contact(String json) {
        ContactDto contactDto = new ContactDto(json);
        this.id = contactDto.getId();
        if (contactDto.getUser() != null) {
            this.user = new User(contactDto.getUser());
        }
        this.street = contactDto.getStreet();
        this.city = contactDto.getCity();
        this.country = contactDto.getCountry();
        this.zipCode = contactDto.getZipCode();
        this.phone = contactDto.getPhone();
        if (contactDto.getSocial() != null) {
            this.social = Arrays.stream(contactDto.getSocial()).collect(Collectors.toList());
        }
    }

    public String toJson() {
        return new ContactDto(this).toJson();
    }

    public static String toJson(Collection<Contact> contacts) {
        return ContactDto.toJson(ContactDto.toDto(contacts));
    }

    public static Collection<Contact> fromJson(String json) {
        return ContactDto.fromDto(ContactDto.fromJson(json));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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

    public List<String> getSocial() {
        return social;
    }

    public void setSocial(List<String> social) {
        this.social = social;
    }
}
