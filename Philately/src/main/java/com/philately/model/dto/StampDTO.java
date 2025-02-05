package com.philately.model.dto;

import com.philately.model.entity.PaperType;

public class StampDTO {
    private Long id;
    private String name;
    private String description;
    private PaperType paper;
    private Integer price;
    private String imageUrl;

    private Long addedBy;

    private boolean wished;

    public Long getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(Long addedBy) {
        this.addedBy = addedBy;
    }

    public boolean isWished() {
        return wished;
    }

    public void setWished(boolean wished) {
        this.wished = wished;
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

    public PaperType getPaper() {
        return paper;
    }

    public void setPaper(PaperType paper) {
        this.paper = paper;
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


}
