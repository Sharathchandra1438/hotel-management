package com.cgrmanjeera.hotel.service;

import com.cgrmanjeera.hotel.model.Booking;
import com.cgrmanjeera.hotel.model.Room;
import com.cgrmanjeera.hotel.repository.BookingRepository;
import com.cgrmanjeera.hotel.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final TaxEngine taxEngine;

    // ==============================
    // CHECK-IN
    // ==============================
    public Booking checkIn(Booking booking) {

        Room room = roomRepository.findByRoomNumber(booking.getRoomNumber())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        if (Boolean.TRUE.equals(room.getBooked())) {
            throw new RuntimeException("Room already booked");
        }

        booking.setCheckInTime(LocalDateTime.now());
        booking.setCheckOutTime(null);
        booking.setTotalDays(null);
        booking.setBaseAmount(null);
        booking.setFinalAmount(null);
        booking.setAppliedTaxes(null);
        booking.setDiscountAmount(null); // initialize discount
        booking.setActive(true);

        room.setBooked(true);
        roomRepository.save(room);

        return bookingRepository.save(booking);
    }

    // ==============================
    // CHECK-OUT WITH OPTIONAL DISCOUNT
    // ==============================
    public Booking checkOut(String roomNumber, Double discountAmount) {

        Booking booking = bookingRepository
                .findByRoomNumberAndActive(roomNumber, true)
                .orElseThrow(() -> new RuntimeException("Active booking not found"));

        booking.setCheckOutTime(LocalDateTime.now());

        // 🔥 Calculate total days
        long seconds = Duration.between(booking.getCheckInTime(), booking.getCheckOutTime()).getSeconds();
        long days = (seconds + 86399) / 86400;
        if (days <= 0) days = 1;
        booking.setTotalDays(days);

        // Calculate base amount
        double baseAmount = days * booking.getPerDayCost();
        booking.setBaseAmount(baseAmount);

        // Apply dynamic taxes
        taxEngine.applyTaxes(booking); // sets finalAmount = baseAmount + totalTax

        // ✅ Apply optional discount after taxes
        if (discountAmount != null && discountAmount > 0) {
            booking.setDiscountAmount(discountAmount);
            double discountedFinal = booking.getFinalAmount() - discountAmount;
            if (discountedFinal < 0) discountedFinal = 0; // avoid negative final amount
            booking.setFinalAmount(discountedFinal);
        } else {
            booking.setDiscountAmount(0.0);
        }

        booking.setActive(false);

        // Free the room
        Room room = roomRepository.findByRoomNumber(roomNumber)
                .orElseThrow(() -> new RuntimeException("Room not found"));
        room.setBooked(false);
        roomRepository.save(room);

        return bookingRepository.save(booking);
    }

    // ==============================
    // GET ALL BOOKINGS
    // ==============================
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // ==============================
    // GET ACTIVE BOOKINGS
    // ==============================
    public List<Booking> getActiveBookings() {
        return bookingRepository.findByActive(true);
    }

    // ==============================
    // GET BOOKING BY ROOM
    // ==============================
    public Booking getBookingByRoom(String roomNumber) {
        return bookingRepository
                .findByRoomNumberAndActive(roomNumber, true)
                .orElseThrow(() -> new RuntimeException("Active booking not found"));
    }
}
