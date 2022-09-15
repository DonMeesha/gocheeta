package com.gocheeta.api.service;

import com.gocheeta.api.entity.Booking;
import com.gocheeta.api.entity.enums.BookingStatus;

import java.util.List;

public interface BookingService {
    Booking saveAndUpdate(Booking newBooking);
    Booking getBookingById(Long id);
    void delete(Long id);
    List<Booking> getAllBookings();
    List<Booking> getAllBookingsForGivenBranchId(Long id);
    List<Booking> getAllCustomerBookings(Long id);
    List<Booking> getAllBookingsByStatus(BookingStatus status);
    List<Booking> getAllBookingsByRate(int rate);
    List<Booking> getAllBookingsByDriver(Long id);
    List<Booking> getAllBookingsBetweenTimestamps(double startTimeStamp, double endTimeStamp);
    double getTotalProfit();
    double getTotalProfitOfGivenBranch(long id);
    double getTotalProfitOfGivenPeriod(double startTimeStamp, double endTimeStamp);
    double getTotalProfitOfGivenPeriodOfBranch(double startTimeStamp, double endTimeStamp, long id);
}
