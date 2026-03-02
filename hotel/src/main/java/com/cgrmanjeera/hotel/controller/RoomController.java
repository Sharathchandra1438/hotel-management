package com.cgrmanjeera.hotel.controller;

import com.cgrmanjeera.hotel.model.Room;
import com.cgrmanjeera.hotel.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
@CrossOrigin
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public Room addRoom(@RequestBody Room room) {
        return roomService.addRoom(room);
    }

    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/available")
    public List<Room> getAvailableRooms() {
        return roomService.getAvailableRooms();
    }

    @GetMapping("/booked")
    public List<Room> getBookedRooms() {
        return roomService.getBookedRooms();
    }

    @DeleteMapping("/{roomNumber}")
    public String deleteRoom(@PathVariable String roomNumber) {
        roomService.deleteRoom(roomNumber);
        return "Room deleted successfully";
    }
}
