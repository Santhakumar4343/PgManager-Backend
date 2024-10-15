package com.pgmanager.controller;




import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pgmanager.entity.Room;
import com.pgmanager.entity.RoomRequest;
import com.pgmanager.serviceImpl.RoomServiceImpl;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomServiceImpl roomService;

//    @PostMapping("/save/{floorId}")
//    public ResponseEntity<Room> createRoom(@PathVariable Long floorId, @RequestBody Room roomToSave) {
//        // Check if the request body is null
//        if (roomToSave == null) {
//            return ResponseEntity.badRequest().body(null); // Return 400 Bad Request if no room is provided
//        }
//
//        Room savedRoom = roomService.createRoom(floorId, roomToSave); // Call to save the room
//
//        if (savedRoom != null) {
//            return ResponseEntity.status(HttpStatus.CREATED).body(savedRoom); // Return 201 Created with the saved room
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Return 404 Not Found if the floor doesn't exist
//        }
//    }

    @PostMapping("/save/{floorId}")
    public ResponseEntity<List<Room>> createRoom(
            @PathVariable Long floorId,
            @RequestBody List<RoomRequest> roomRequests) { // Changed to accept a list of RoomRequest

        // Validate that the roomRequests list is not null or empty
        if (roomRequests == null || roomRequests.isEmpty()) {
            return ResponseEntity.badRequest().body(null); // Return 400 Bad Request if the list is invalid
        }

        // Call the service method to create the rooms
        List<Room> savedRooms = new ArrayList<>(); // Use a list to hold saved rooms
        for (RoomRequest roomRequest : roomRequests) {
            // Extract and validate values from the roomRequest
            String sharingType = roomRequest.getSharingType();
            Integer bedCount = roomRequest.getBedCount();

            // Validate that the required fields are not null
            if (sharingType == null || bedCount == null || bedCount <= 0) {
                return ResponseEntity.badRequest().body(null); // Return 400 Bad Request if invalid data is provided
            }

            // Create room using the service method
            Room savedRoom = roomService.createRooms(floorId, roomRequests); // Call with only floorId and roomRequests

            if (savedRoom == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Return 404 Not Found if the floor doesn't exist
            }

            savedRooms.add(savedRoom); // Add the created room to the list
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(savedRooms); // Return all created rooms
    }



    @GetMapping("/getAll")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/get/{id}")
    public Room getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id);
    }

    @PutMapping("/update/{id}")
    public Room updateRoom(@PathVariable Long id, @RequestBody Room room) {
        return roomService.updateRoom(id, room);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
    }
}

