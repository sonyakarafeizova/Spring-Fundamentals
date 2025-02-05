package com.philately.model.dto;

import com.philately.model.entity.Stamp;

import java.util.HashSet;
import java.util.Set;

public class PaperDTO {
    private String paperName;
    private String description;
    private Set<Stamp> stamps;

    public PaperDTO( String paperName, String description, Set<Stamp> stamps) {

        this.paperName = paperName;
        this.description = description;
        this.stamps = new HashSet<>();
    }



    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Stamp> getStamps() {
        return stamps;
    }

    public void setStamps(Set<Stamp> stamps) {
        this.stamps = stamps;
    }
}
