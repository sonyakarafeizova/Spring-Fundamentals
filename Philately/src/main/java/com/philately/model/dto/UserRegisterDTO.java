package com.philately.model.dto;

import com.philately.validation.annotation.UniqueEmail;
import com.philately.validation.annotation.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRegisterDTO {
    private Long id;

    @UniqueUsername
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!")
    @NotNull
    private String username;

    @UniqueEmail
    @Email(message = "Enter valid email!")
    @NotBlank(message = "Email cannot be empty!")
    private String email;

    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    @NotNull
    private String password;

    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    @NotNull
    private String confirmPassword;


    public UserRegisterDTO() {}

    public UserRegisterDTO(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
