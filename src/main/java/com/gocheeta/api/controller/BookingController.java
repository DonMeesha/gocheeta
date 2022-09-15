package com.gocheeta.api.controller;

import com.gocheeta.api.entity.Booking;
import com.gocheeta.api.entity.enums.BookingStatus;
import com.gocheeta.api.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping()
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        return new ResponseEntity<Booking>(bookingService.saveAndUpdate(booking), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable("id") long id, @RequestBody Booking booking) {
        return new ResponseEntity<Booking>(bookingService.saveAndUpdate(booking), HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/single/{id}")
    public Booking getBookingById(@PathVariable("id") long id) {
        return bookingService.getBookingById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map> deleteBookingById(@PathVariable("id") long id) {
        try {
            bookingService.delete(id);
            Map response = new HashMap();
            response.put("status", HttpStatus.OK);
            response.put("message", "Successfully Deleted");
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception exception) {
            throw exception;
        }
    }

    @GetMapping("/byBranch")
    public List<Booking> getAllBookingsForGivenBranchId(@RequestParam("branchId") long branchId) {
        return bookingService.getAllBookingsForGivenBranchId(branchId);
    }

    @GetMapping("/byCustomer")
    public List<Booking> getAllBookingByCustomerId(@RequestParam("customerId") long customerId) {
        return bookingService.getAllCustomerBookings(customerId);
    }

    @GetMapping("/byStatus")
    public List<Booking> getAllBookingByStatus(@RequestParam("status")BookingStatus status) {
        return bookingService.getAllBookingsByStatus(status);
    }

    @GetMapping("/byRate")
    public List<Booking> getAllBookingByRate(@RequestParam("rate") int rate) {
        return bookingService.getAllBookingsByRate(rate);
    }

    @GetMapping("/byDriver")
    public List<Booking> getAllBookingByDriverId(@RequestParam("driverId") long driverId) {
        return bookingService.getAllBookingsByDriver(driverId);
    }

    @GetMapping("/byTimeStamp")
    public List<Booking> getAllBookingsBetweenTimestamps(@RequestParam("startTimeStamp") double startTimeStamp, @RequestParam("endTimeStamp") double endTimeStamp) {
        return bookingService.getAllBookingsBetweenTimestamps(startTimeStamp, endTimeStamp);
    }

    @GetMapping("/totalProfit")
    public ResponseEntity<Map<String, Double>> getTotalProfit() {
        Double totalProfit = bookingService.getTotalProfit();
        HashMap<String, Double> response = new HashMap<String, Double>();
        response.put("total", totalProfit);
        return new ResponseEntity<Map<String, Double>>(response, HttpStatus.OK);
    }

    @GetMapping("/totalProfit/byBranch/{id}")
    public ResponseEntity<Map<String, Double>> getTotalProfitOfGivenBranch(@PathVariable("id") long branchId) {
        Double totalProfit = bookingService.getTotalProfitOfGivenBranch(branchId);
        HashMap<String, Double> response = new HashMap<String, Double>();
        response.put("total", totalProfit);
        return new ResponseEntity<Map<String, Double>>(response, HttpStatus.OK);
    }

    @GetMapping("/totalProfit/givenPeriod")
    public ResponseEntity<Map<String, Double>> getTotalProfitForGivenPeriod(@RequestParam("startTime") double startTimeStamp, @RequestParam("endTime") double endTimeStamp) {
        Double totalProfit = bookingService.getTotalProfitOfGivenPeriod(startTimeStamp, endTimeStamp);
        HashMap<String, Double> response = new HashMap<String, Double>();
        response.put("total", totalProfit);
        return new ResponseEntity<Map<String, Double>>(response, HttpStatus.OK);
    }

    @GetMapping("/totalProfit/givenPeriod/{id}")
    public ResponseEntity<Map<String, Double>> getTotalProfitOfGivenPeriodOfBranch(@RequestParam("startTime") double startTimeStamp, @RequestParam("endTime") double endTimeStamp, @PathVariable("id") long branchId) {
        Double totalProfit = bookingService.getTotalProfitOfGivenPeriodOfBranch(startTimeStamp, endTimeStamp, branchId);
        HashMap<String, Double> response = new HashMap<String, Double>();
        response.put("total", totalProfit);
        return new ResponseEntity<Map<String, Double>>(response, HttpStatus.OK);
    }
}
