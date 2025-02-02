package com.philately.model.dto;

import java.util.List;

public class WishlistDTO {
    private Long userId;
    private List<StampDTO> wishlistItems;

    public WishlistDTO(Long userId, List<StampDTO> wishlistItems) {
        this.userId = userId;
        this.wishlistItems = wishlistItems;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<StampDTO> getWishlistItems() {
        return wishlistItems;
    }

    public void setWishlistItems(List<StampDTO> wishlistItems) {
        this.wishlistItems = wishlistItems;
    }
}
