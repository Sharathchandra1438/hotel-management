package com.cgrmanjeera.hotel.service;

import com.cgrmanjeera.hotel.model.Room;
import com.cgrmanjeera.hotel.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    // Add new room
    public Room addRoom(Room room) {

        if (roomRepository.findByRoomNumber(room.getRoomNumber()).isPresent()) {
            throw new RuntimeException("Room already exists");
        }

        room.setBooked(false);
        return roomRepository.save(room);
    }

    // Get all rooms
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // Get available rooms
    public List<Room> getAvailableRooms() {
        return roomRepository.findByBooked(false);
    }

    // Get booked rooms
    public List<Room> getBookedRooms() {
        return roomRepository.findByBooked(true);
    }

    // Get room by number
    public Room getRoomByNumber(String roomNumber) {
        return roomRepository.findByRoomNumber(roomNumber)
                .orElseThrow(() -> new RuntimeException("Room not found"));
    }

    // Delete room
    public void deleteRoom(String roomNumber) {
        Room room = getRoomByNumber(roomNumber);
        roomRepository.delete(room);
    }
}
