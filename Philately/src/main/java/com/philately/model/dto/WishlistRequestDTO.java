package com.philately.model.dto;

import jakarta.validation.constraints.NotNull;

public class WishlistRequestDTO {
    @NotNull
    private Long stampId;

    public WishlistRequestDTO() {}

    public Long getStampId() {
        return stampId;
    }

    public void setStampId(Long stampId) {
        this.stampId = stampId;
    }

    public WishlistRequestDTO(Long stampId) {
        this.stampId = stampId;

    }
}
