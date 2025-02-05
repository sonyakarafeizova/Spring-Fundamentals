package com.philately.model.entity;

import jakarta.persistence.*;

import java.util.Optional;

@Entity
@Table(name = "stamps")
public class Stamp extends BaseEntity {
    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 25)
    private String description;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "paper_id", nullable = false)
    private Paper paper;

    @Column(nullable = false)
    private int price;

    @Column
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User addedBy;
    @Column
    private boolean wished;

    public boolean isWished() {
        return wished;
    }

    public void setWished(boolean wished) {
        this.wished = wished;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }
}