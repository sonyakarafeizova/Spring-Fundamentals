package com.philately.model.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "papers")
public class Paper extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private PaperType paperName;

    @Column(columnDefinition = "TEXT")
    private String description;
    @OneToMany(mappedBy = "paper")
    private Set<Stamp> stamps;

    public PaperType getPaperName() {
        return paperName;
    }

    public void setPaperName(PaperType paperName) {
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
