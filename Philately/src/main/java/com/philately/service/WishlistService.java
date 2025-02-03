package com.philately.service;

import com.philately.model.entity.Stamp;
import com.philately.model.entity.User;
import com.philately.repository.StampRepository;
import com.philately.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {

    private final UserRepository userRepository;
    private final StampRepository stampRepository;

    public WishlistService(UserRepository userRepository, StampRepository stampRepository) {
        this.userRepository = userRepository;
        this.stampRepository = stampRepository;
    }

    public void addToWishlist(Long userId, Long stampId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Stamp stamp = stampRepository.findById(stampId)
                .orElseThrow(() -> new IllegalArgumentException("Stamp not found"));

        if (user.getWishList().contains(stamp)) {
            throw new IllegalArgumentException("Stamp already in wishlist!");
        }

        user.getWishList().add(stamp);
        userRepository.save(user);
    }

    public void purchaseStamp(Long userId, Long stampId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Stamp stamp = stampRepository.findById(stampId)
                .orElseThrow(() -> new IllegalArgumentException("Stamp not found"));

        if (!user.getWishList().contains(stamp)) {
            throw new IllegalArgumentException("Stamp must be in wishlist before purchase!");
        }

        user.getWishList().remove(stamp);
        user.getPurchasedStamps().add(stamp);

        userRepository.save(user);
    }
}
