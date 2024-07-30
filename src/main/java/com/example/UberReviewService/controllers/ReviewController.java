package com.example.UberReviewService.controllers;

import com.example.UberReviewService.adapters.CreateReviewDtoToReviewAdapter;
import com.example.UberReviewService.dtos.CreateReviewDTO;
import com.example.UberReviewService.dtos.ReviewDTO;
import com.example.UberReviewService.models.Review;
import com.example.UberReviewService.services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final CreateReviewDtoToReviewAdapter createReviewDtoToReviewAdapter;
    public ReviewController(ReviewService reviewService, CreateReviewDtoToReviewAdapter createReviewDtoToReviewAdapter)
    {
        this.reviewService=reviewService;
        this.createReviewDtoToReviewAdapter = createReviewDtoToReviewAdapter;
    }

    @PostMapping
    public ResponseEntity<?> publishReview(@Validated @RequestBody CreateReviewDTO request) {
           Review incomingReview =this.createReviewDtoToReviewAdapter.convertDto(request);
           if(incomingReview==null)
           {
               return new ResponseEntity<>("invalid argument",HttpStatus.BAD_REQUEST);
           }
           Review review=this.reviewService.publishReview(incomingReview);
        ReviewDTO response = ReviewDTO.builder()
                .id(review.getId())
                .content(review.getContent())
                .booking(review.getBooking().getId())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build();
        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<?> getAllReviews(){
        List<Review> reviews = this.reviewService.findAllReviews();
        List<ReviewDTO> reviewDTOs=new ArrayList<>();
          for(Review review:reviews)
          {
              ReviewDTO response=ReviewDTO.builder()
                      .id(review.getId())
                      .content(review.getContent())
                      .rating(review.getRating())
                      .booking(review.getBooking().getId())
                      .createdAt(new Date())
                      .updatedAt(new Date())
                      .build();
              reviewDTOs.add(response);
          }
        return new ResponseEntity<>(reviewDTOs, HttpStatus.OK);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<?> findReviewById(@PathVariable Long reviewId) {
        try {
            Optional<Review> review = this.reviewService.findReviewById(reviewId);
            if(review.isEmpty())
            {
                return new ResponseEntity<>("No review found with the id:"+reviewId,HttpStatus.BAD_REQUEST);
            }
            ReviewDTO response=ReviewDTO.builder()
                    .id(review.get().getId())
                    .content(review.get().getContent())
                    .rating(review.get().getRating())
                    .booking(review.get().getBooking().getId())
                    .createdAt(new Date())
                    .updatedAt(new Date())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReviewById(@PathVariable Long reviewId) {
        try {
            boolean isDeleted = this.reviewService.deleteReviewById(reviewId);
            if(!isDeleted) return new ResponseEntity<>("Unable to delete Review", HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<?> updateReview(@PathVariable Long reviewId, @RequestBody Review request){
        try {
            Review review = this.reviewService.updateReview(reviewId, request);
            ReviewDTO response=ReviewDTO.builder()
                    .id(review.getId())
                    .content(review.getContent())
                    .rating(review.getRating())
                    .booking(review.getBooking().getId())
                    .createdAt(new Date())
                    .updatedAt(new Date())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
