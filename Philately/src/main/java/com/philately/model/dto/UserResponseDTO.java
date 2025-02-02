package com.philately.model.dto;

import java.util.List;

public class UserResponseDTO {
    private Long id;
    private String username;
    private String email;
    private List<StampDTO> purchasedStamps;

    public UserResponseDTO(Long id, String username, String email, List<StampDTO> purchasedStamps) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.purchasedStamps = purchasedStamps;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<StampDTO> getPurchasedStamps() {
        return purchasedStamps;
    }

    public void setPurchasedStamps(List<StampDTO> purchasedStamps) {
        this.purchasedStamps = purchasedStamps;
    }
}
