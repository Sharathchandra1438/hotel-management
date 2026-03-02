package com.cgrmanjeera.hotel.repository;

import com.cgrmanjeera.hotel.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.List;

public interface RoomRepository extends MongoRepository<Room, String> {

    Optional<Room> findByRoomNumber(String roomNumber);

    List<Room> findByBooked(boolean booked);
}
