package com.philately.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "email", nullable = false, unique = true)
    @Email
    private String email;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Stamp> addedStamps = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Stamp> wishedStamps;

    @OneToMany(fetch = FetchType.EAGER)

    private Set<Stamp> purchasedStamps;

    public User() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Stamp> getAddedStamps() {
        return addedStamps;
    }

    public void setAddedStamps(Set<Stamp> addedStamps) {
        this.addedStamps = addedStamps;
    }

    public Set<Stamp> getWishedStamps() {
        return wishedStamps;
    }

    public void setWishedStamps(Set<Stamp> wishedStamps) {
        this.wishedStamps = wishedStamps;
    }

    public Set<Stamp> getPurchasedStamps() {
        return purchasedStamps;
    }

    public void setPurchasedStamps(Set<Stamp> purchasedStamps) {
        this.purchasedStamps = purchasedStamps;
    }
}
