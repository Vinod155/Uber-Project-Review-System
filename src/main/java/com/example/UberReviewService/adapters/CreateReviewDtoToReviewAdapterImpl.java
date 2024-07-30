package com.example.UberReviewService.adapters;

import com.example.UberReviewService.dtos.CreateReviewDTO;
import com.example.UberReviewService.models.Booking;
import com.example.UberReviewService.models.Review;
import com.example.UberReviewService.repositories.BookingRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CreateReviewDtoToReviewAdapterImpl implements CreateReviewDtoToReviewAdapter {

     private final BookingRepository bookingRepository;

    public CreateReviewDtoToReviewAdapterImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }


    @Override
    public Review convertDto(CreateReviewDTO createReviewDTO) {
        Optional<Booking> booking=this.bookingRepository.findById(createReviewDTO.getBookingId());
        if(booking.isEmpty())
        {
            return null;
        }
        return Review.builder()
                .rating(createReviewDTO.getRating())
                .booking(booking.get())
                .content(createReviewDTO.getContent())
                .build();
    }
}
