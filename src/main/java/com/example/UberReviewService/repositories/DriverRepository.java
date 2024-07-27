package com.example.UberReviewService.repositories;

import com.example.UberReviewService.models.Booking;
import com.example.UberReviewService.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository  extends JpaRepository<Driver, Long> {

    @Query(nativeQuery = true,value="SELECT * FROM Driver WHERE id=:id AND license_number=:license")
    Optional<Driver> findByIdAndLicenseNumber(Long id, String license);

    @Query("select d from Driver as d where d.id=:id and d.licenseNumber=:ln")
    Optional<Driver> rawFindByIdAndName(Long id, String ln);
}
