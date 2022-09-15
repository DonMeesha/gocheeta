package com.gocheeta.api.service.impl;

import com.gocheeta.api.config.exception.BusinessException;
import com.gocheeta.api.entity.Booking;
import com.gocheeta.api.entity.enums.BookingStatus;
import com.gocheeta.api.repository.BookingRepository;
import com.gocheeta.api.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IBookingService implements BookingService {

    @Autowired
    private BookingRepository repository;

    @Override
    public Booking saveAndUpdate(Booking newBooking) {
        try {
            return repository.save(newBooking);
        } catch (Exception exception) {
            throw exception;
        }
    }

    @Override
    public Booking getBookingById(Long id) {
        try {
            return repository.findById(id).get();
        } catch(Exception exception) {
            throw exception;
        }
    }

    @Override
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception exception) {
            throw exception;
        }
    }

    @Override
    public List<Booking> getAllBookings() {
        try {
            return repository.findAll();
        } catch (Exception exception) {
            throw exception;
        }
    }

    @Override
    public List<Booking> getAllBookingsForGivenBranchId(Long id) {
        try {
            return repository.getBookingOfGivenBranchId(id);
        } catch(Exception exception) {
            throw exception;
        }
    }

    @Override
    public List<Booking> getAllCustomerBookings(Long id) {
        try {
            return repository.getBookingOfGivenClientId(id);
        } catch(Exception exception) {
            throw exception;
        }
    }

    @Override
    public List<Booking> getAllBookingsByStatus(BookingStatus status) {
        try {
            return repository.findByStatus(status);
        } catch(Exception exception) {
            throw exception;
        }
    }

    @Override
    public List<Booking> getAllBookingsByRate(int rate) {
        try {
            return repository.findByClientRate(rate);
        } catch(Exception exception) {
            throw exception;
        }
    }

    @Override
    public List<Booking> getAllBookingsByDriver(Long id) {
        try {
            return repository.getBookingsByDriverId(id);
        } catch (Exception exception) {
            throw exception;
        }
    }

    @Override
    public List<Booking> getAllBookingsBetweenTimestamps(double startTimeStamp, double endTimeStamp) {
        try {
            return repository.getBookingForGivenPeriod(startTimeStamp, endTimeStamp);
        } catch(Exception exception) {
            throw exception;
        }
    }

    @Override
    public double getTotalProfit() {
        try {
            return repository.getTotalIncome();
        } catch(Exception exception) {
            throw exception;
        }
    }

    @Override
    public double getTotalProfitOfGivenBranch(long id) {
        try {
            return repository.getTotalIncomeFromBranch(id);
        } catch(Exception exception) {
            throw exception;
        }
    }

    @Override
    public double getTotalProfitOfGivenPeriod(double startTimeStamp, double endTimeStamp) {
        try {
            return repository.getTotalIncomeForGivenPeriod(startTimeStamp, endTimeStamp);
        } catch(Exception exception) {
            throw exception;
        }
    }

    @Override
    public double getTotalProfitOfGivenPeriodOfBranch(double startTimeStamp, double endTimeStamp, long id) {
        try {
            return repository.getTotalIncomeFromBranchForGivenPeriod(id, startTimeStamp, endTimeStamp);
        } catch(Exception exception) {
            throw exception;
        }
    }
}
