package com.example.UberReviewService.services;

import com.example.UberReviewService.models.Booking;
import com.example.UberReviewService.models.Driver;
import com.example.UberReviewService.models.Review;
import com.example.UberReviewService.repositories.BookingRepository;
import com.example.UberReviewService.repositories.DriverRepository;
import com.example.UberReviewService.repositories.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService implements CommandLineRunner {

    final private ReviewRepository reviewRepository;
    final private BookingRepository bookingRepository;
    final private DriverRepository driverRepository;

    public ReviewService(ReviewRepository reviewRepository, BookingRepository bookingRepository, DriverRepository driverRepository)
    {
        this.reviewRepository=reviewRepository;
        this.bookingRepository = bookingRepository;
        this.driverRepository = driverRepository;
    }
    @Override
    public void run(String... args) throws Exception {

//        System.out.println("************");
//       Review r=Review.builder().content("nice ride").
//        rating(5.0).build();
//       Booking r1=Booking.builder()
//               .review(r)
//               .endTime(new Date())
//               .build();
//       this.bookingRepository.save(r1);
//       //this.reviewRepository.save(r);

        Optional<Driver> driver=driverRepository.findByIdAndLicenseNumber(1L,"abcdef");

        Optional<Driver> d=driverRepository.rawFindByIdAndName(1L,"abcdef");

    }
}
