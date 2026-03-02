package com.cgrmanjeera.hotel.controller;

import com.cgrmanjeera.hotel.model.Booking;
import com.cgrmanjeera.hotel.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
@CrossOrigin
public class BookingController {

    private final BookingService bookingService;

    // ✅ Check-in
    @PostMapping("/checkin")
    public Booking checkIn(@RequestBody Booking booking) {
        return bookingService.checkIn(booking);
    }

    // ✅ Check-out with optional discount parameter
    @PostMapping("/checkout/{roomNumber}")
    public Booking checkOut(
            @PathVariable String roomNumber,
            @RequestParam(required = false) Double discountAmount // optional
    ) {
        return bookingService.checkOut(roomNumber, discountAmount);
    }

    // ✅ Get all bookings
    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    // ✅ Get active bookings
    @GetMapping("/active")
    public List<Booking> getActiveBookings() {
        return bookingService.getActiveBookings();
    }

    // ✅ Get booking by room
    @GetMapping("/{roomNumber}")
    public Booking getBookingByRoom(@PathVariable String roomNumber) {
        return bookingService.getBookingByRoom(roomNumber);
    }
}
