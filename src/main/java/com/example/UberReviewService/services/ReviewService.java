package com.example.UberReviewService.services;

import com.example.UberReviewService.models.Booking;
import com.example.UberReviewService.models.CustomDriver;
import com.example.UberReviewService.models.Driver;
import com.example.UberReviewService.models.Review;
import com.example.UberReviewService.repositories.BookingRepository;
import com.example.UberReviewService.repositories.DriverRepository;
import com.example.UberReviewService.repositories.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.*;

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
    @Transactional
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

//        Optional<Driver> driver=driverRepository.findByIdAndLicenseNumber(1L,"abcdef");
//
//        CustomDriver d=driverRepository.rawFindByIdAndName(1L,"abcdef");
//        System.out.println(d.getName());

        List<Long> driverIds=new ArrayList<>(Arrays.asList(1L,2L,3L,5L));

        List<Driver> drivers=driverRepository.findAllByIdIn(driverIds);

        for(Driver driver:drivers)
        {
            List<Booking> bookings=driver.getBookings();
            bookings.forEach(booking-> System.out.println(booking.getId()));
        }
    }
}
