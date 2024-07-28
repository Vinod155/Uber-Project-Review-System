package com.example.UberReviewService.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking  extends BaseModel{

    @OneToOne(cascade = {CascadeType.PERSIST ,CascadeType.REMOVE},fetch = FetchType.LAZY)
    private Review review;

    @Enumerated(value = EnumType.STRING)
    private BookingStatus bookingStatus;

    @Temporal(value= TemporalType.TIMESTAMP)
    private Date startTime;

    @Temporal(value= TemporalType.TIMESTAMP)
    private Date endTime;

    private Long totalDistance;

    @ManyToOne  // a booking has one driver
    private Driver driver;

    @ManyToOne
    private Passenger passenger;

}
