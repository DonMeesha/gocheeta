package com.gocheeta.api.repository;

import com.gocheeta.api.entity.Booking;
import com.gocheeta.api.entity.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByStatus(BookingStatus status);
    List<Booking> findByClientRate(int rate);

    @Query(value = "SELECT * FROM Booking WHERE customer_details_id = ?1", nativeQuery = true)
    List<Booking> getBookingOfGivenClientId(Long id);

    @Query(value = "SELECT booking(*) " +
            "FROM Booking booking, Branch branch, StreetAddress address " +
            "WHERE address.branch_details_id = branch.id AND booking.street_details_id = address.id branch.id= ?1", nativeQuery = true)
    List<Booking> getBookingOfGivenBranchId(Long id);

    @Query(value = "SELECT * FROM Booking WHERE driver_details_id = ?1", nativeQuery = true)
    List<Booking> getBookingsByDriverId(Long id);

    @Query(value = "SELECT * FROM Booking WHERE end_time_stamp BETWEEN ?1 AND ?2", nativeQuery = true)
    List<Booking> getBookingForGivenPeriod(double startTimeStamp, double endTimeStamp);

    @Query(value = "SELECT SUM(price) FROM Booking", nativeQuery = true)
    double getTotalIncome();

    @Query(value = "SELECT SUM(booking.price) " +
            "FROM Booking booking, Branch branch, StreetAddress address " +
            "WHERE address.branch_details_id = branch.id AND booking.street_details_id = address.id branch.id= ?1", nativeQuery = true)
    double getTotalIncomeFromBranch(Long id);

    @Query(value = "SELECT SUM(price) FROM Booking WHERE end_time_stamp BETWEEN ?1 AND ?2", nativeQuery = true)
    double getTotalIncomeForGivenPeriod(double startTimeStamp, double endTimeStamp);

    @Query(value = "SELECT SUM(booking.price) " +
            "FROM Booking booking, Branch branch, StreetAddress address " +
            "WHERE address.branch_details_id = branch.id AND booking.street_details_id = address.id branch.id= ?1 AND end_time_stamp BETWEEN ?2 AND ?3", nativeQuery = true)
    double getTotalIncomeFromBranchForGivenPeriod(Long id, double startTimeStamp, double endTimeStamp);

}
