package com.philately.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class UserSession {

    private Long userId;
    private boolean loggedIn;

    public boolean isUserLoggedIn() {
        return loggedIn;
    }

    public void login(Long userId) {
        this.userId = userId;
        this.loggedIn = true;
    }

    public void logout() {
        this.userId = null;
        this.loggedIn = false;
    }

    public Long getUserId() {
        return userId;
    }
}