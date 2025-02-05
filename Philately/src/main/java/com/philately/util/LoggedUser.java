package com.philately.util;

import com.philately.model.entity.Stamp;
import com.philately.service.StampService;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Set;

@Component
@SessionScope
public class LoggedUser {
    private final StampService stampService;
    private Long id;
    private String username;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Stamp> wishedStamps;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Stamp> boughtStamps;

    public LoggedUser(StampService stampService) {
        this.stampService = stampService;
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

    public boolean isLogged() {
        return this.username != null && this.id != null;
    }

    public StampService getStampService() {
        return stampService;
    }

    public Set<Stamp> getWishedStamps() {
        return wishedStamps;
    }

    public void setWishedStamps(Set<Stamp> wishedStamps) {
        this.wishedStamps = wishedStamps;
    }

    public Set<Stamp> getBoughtStamps() {
        return boughtStamps;
    }

    public void setBoughtStamps(Set<Stamp> boughtStamps) {
        this.boughtStamps = boughtStamps;
    }
}
