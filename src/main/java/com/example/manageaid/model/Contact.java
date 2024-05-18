package com.example.manageaid.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Size(max = 30)
    private String name;

    @Size(max = 50)
    @Email
    private String email;

    @Size(max = 12)
    private String phoneNumber;

    @Size(max = 100)
    private String address;

    public @Size(max = 30) String getCompany() {
        return company;
    }

    public void setCompany(@Size(max = 30) String company) {
        this.company = company;
    }

    @Size(max = 30)
    private String company;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank @Size(max = 30) String getName() {
        return name;
    }

    public void setName(@NotBlank @Size(max = 30) String name) {
        this.name = name;
    }

    public @NotBlank @Size(max = 50) @Email String getEmail() {
        return email;
    }

    public void setPhoneNumber(@Size(max = 12) String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public @Size(max = 100) String getAddress() {
        return address;
    }

    public void setAddress(@Size(max = 100) String address) {
        this.address = address;
    }


    public void setEmail(@Size(max = 50) @Email String email) {
        this.email = email;
    }

    public @Size(max = 12) String getPhoneNumber() {
        return phoneNumber;
    }
}
