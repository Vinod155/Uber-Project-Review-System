package com.example.UberReviewService.services;

import com.example.UberReviewService.models.Review;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewService {
    public Optional<Review> findReviewById(Long Id);

    public List<Review> findAllReviews();

    public boolean deleteReviewById(Long Id);
}
