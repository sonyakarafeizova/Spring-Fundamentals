package com.philately.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

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
    @ManyToMany
    @JoinTable(
            name = "wishlist",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "stamp_id")
    )
    private Set<Stamp> wishList = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "buyer_id")
    private Set<Stamp> purchasedStamps = new HashSet<>();

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

    public Set<Stamp> getWishList() {
        return wishList;
    }

    public void setWishList(Set<Stamp> wishList) {
        this.wishList = wishList;
    }

    public Set<Stamp> getPurchasedStamps() {
        return purchasedStamps;
    }

    public void setPurchasedStamps(Set<Stamp> purchasedStamps) {
        this.purchasedStamps = purchasedStamps;
    }
}
