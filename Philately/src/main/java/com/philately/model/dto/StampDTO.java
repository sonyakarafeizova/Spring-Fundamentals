package com.philately.model.dto;

public class StampDTO {
    private Long id;
    private String name;
    private String description;
    private String paperType;
    private Integer price;
    private String imageUrl;
    private String ownerUsername;

    public StampDTO(Long id, String name, String description, String paperType, Integer price, String imageUrl, String ownerUsername) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.paperType = paperType;
        this.price = price;
        this.imageUrl = imageUrl;
        this.ownerUsername = ownerUsername;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPaperType() {
        return paperType;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }
}
