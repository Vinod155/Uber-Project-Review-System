package com.example.UberReviewService.adapters;

import com.example.UberReviewService.dtos.CreateReviewDTO;
import com.example.UberReviewService.models.Review;

public interface CreateReviewDtoToReviewAdapter {

    public Review convertDto(CreateReviewDTO createReviewDTO);
}
