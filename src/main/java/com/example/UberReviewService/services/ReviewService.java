package com.example.UberReviewService.services;

import com.example.UberReviewService.models.Review;
import com.example.UberReviewService.repositories.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReviewService implements CommandLineRunner {

    final private ReviewRepository reviewRepository;
    public ReviewService(ReviewRepository reviewRepository)
    {
        this.reviewRepository=reviewRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        System.out.println("************");
//        Review r=Review.builder().content("amzing ride")
//                .createdAt(new Date())
//                        .updatedAt(new Date()).
//        rating(5.0).build();
//        Review r1=Review.builder().content("amazing ride good")
//                .createdAt(new Date())
//                .updatedAt(new Date()).
//                rating(4.0).build();
//        this.reviewRepository.save(r);
//        this.reviewRepository.save(r1);

        List<Review> reviews=reviewRepository.findAll();

       reviewRepository.deleteById(2L);

    }
}
