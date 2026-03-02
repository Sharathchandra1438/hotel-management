package com.cgrmanjeera.hotel.repository;

import com.cgrmanjeera.hotel.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends MongoRepository<Booking, String> {

    Optional<Booking> findByRoomNumberAndActive(String roomNumber, Boolean active);

    List<Booking> findByActive(Boolean active);
}
