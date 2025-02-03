package com.philately.controller;

import com.philately.service.WishlistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping("/add/{userId}/{stampId}")
    public ResponseEntity<String> addToWishlist(@PathVariable Long userId, @PathVariable Long stampId) {
        wishlistService.addToWishlist(userId, stampId);
        return ResponseEntity.ok("Stamp added to wishlist.");
    }

    @PostMapping("/purchase/{userId}/{stampId}")
    public ResponseEntity<String> purchaseStamp(@PathVariable Long userId, @PathVariable Long stampId) {
        wishlistService.purchaseStamp(userId, stampId);
        return ResponseEntity.ok("Stamp purchased successfully.");
    }
}

