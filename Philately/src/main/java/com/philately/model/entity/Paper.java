package com.philately.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "papers")
public class Paper extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private PaperType paperName;

    @Column(nullable = false)
    private String description;

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

}
