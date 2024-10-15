package com.pgmanager.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgmanager.entity.Bed;
import com.pgmanager.entity.Floor;
import com.pgmanager.entity.Room;
import com.pgmanager.entity.RoomRequest;
import com.pgmanager.repository.BedRepository;
import com.pgmanager.repository.FloorRepository;
import com.pgmanager.repository.RoomRepository;
import com.pgmanager.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private FloorRepository floorRepository;

    @Autowired
    private BedRepository bedRepository;
    
    public Room createRooms(Long floorId, List<RoomRequest> roomRequests) {
        // Retrieve the floor using the floorId, throwing an exception if not found
        Floor floor = floorRepository.findById(floorId)
                .orElseThrow(() -> new RuntimeException("Floor not found"));

        // Determine the prefix for the room number based on the floor name
        String floorPrefix = floor.getFloorName().substring(0, 1).toUpperCase(); // e.g., "G" for Ground, "F" for First

        Room lastSavedRoom = null; // Variable to hold the last saved room

        // Iterate through each RoomRequest to create the specified rooms
        for (RoomRequest roomRequest : roomRequests) {
            String sharingType = roomRequest.getSharingType();
            int numberOfRooms = roomRequest.getBedCount();

            // Validate the number of rooms specified
            if (numberOfRooms <= 0) {
                throw new IllegalArgumentException("Number of rooms must be greater than zero");
            }

            // Create multiple rooms based on the number specified
            for (int roomIndex = 0; roomIndex < numberOfRooms; roomIndex++) {
                // Generate the next room number based on the existing rooms in the floor
                int nextRoomNumber = floor.getRooms().size() + 1;
                String roomNumber = floorPrefix + nextRoomNumber; // E.g., "G1", "G2" for Ground floor rooms

                Room room = new Room();
                room.setRoomNumber(roomNumber);
                room.setSharingType(sharingType);
                room.setFloor(floor);
                
                // Save the room to the repository
                lastSavedRoom = roomRepository.save(room); // Save the room and store the reference

                // Determine the number of beds to create based on the sharing type
                int bedsToCreate = determineBedsToCreate(sharingType);

                // Create beds for this room based on the determined bed count
                for (int i = 1; i <= bedsToCreate; i++) {
                    Bed bed = new Bed();
                    bed.setBedNumber("B" + i); // Bed numbers like "G1-B1", "G1-B2", etc.
                    bed.setRoom(room);
                    bed.setAvailable(true);
                    
                    // Save the bed to the repository
                    bedRepository.save(bed);
                }
            }
        }

        return lastSavedRoom; // Return the last saved room
    }

    // Helper method to determine the number of beds to create based on the sharing type
    private int determineBedsToCreate(String sharingType) {
        switch (sharingType.toLowerCase()) {
            case "single":
                return 1;
            case "double":
                return 2;
            case "triple":
                return 3;
            case "triple plus":
                return 4;
            default:
                throw new IllegalArgumentException("Invalid sharing type: " + sharingType);
        }
    }
//    public Room createRoom(Long floorId, String sharingType, int bedCount) {
//        Floor floor = floorRepository.findById(floorId)
//            .orElseThrow(() -> new RuntimeException("Floor not found"));
//
//        // Determine the prefix for the room number based on the floor name
//        String floorPrefix = floor.getFloorName().substring(0, 1).toUpperCase(); // e.g., "G" for Ground, "F" for First
//        
//        // Generate the next room number based on the existing rooms in the floor
//        int nextRoomNumber = floor.getRooms().size() + 1;
//        String roomNumber = floorPrefix + nextRoomNumber; // E.g., "G1", "G2" for Ground floor rooms
//
//        Room room = new Room();
//        room.setRoomNumber(roomNumber);
//        room.setSharingType(sharingType);
//        room.setFloor(floor);
//        roomRepository.save(room);
//
//        // Determine the number of beds to create based on the sharing type
//        int bedsToCreate = 0;
//        switch (sharingType.toLowerCase()) {
//            case "single":
//                bedsToCreate = 1;
//                break;
//            case "double":
//                bedsToCreate = 2;
//                break;
//            case "triple":
//                bedsToCreate = 3;
//                break;
//            case "triple plus":
//                bedsToCreate = 4;
//                break;
//            default:
//                throw new IllegalArgumentException("Invalid sharing type: " + sharingType);
//        }
//
//        // Create beds for this room based on the determined bed count
//        for (int i = 1; i <= bedsToCreate; i++) {
//            Bed bed = new Bed();
//            bed.setBedNumber("B" + i); // Bed numbers like "B1", "B2", etc.
//            bed.setRoom(room);
//           bed.setAvailable(true);
//            bedRepository.save(bed);
//        }
//
//        return room;
//    }

//    @Override
//    public Room createRoom(Long floorId, Room room) {
//        Floor floor = floorRepository.findById(floorId).orElse(null);
//        if (floor != null) {
//            room.setFloor(floor); // Set the floor for the room
//            return roomRepository.save(room); // Save the room and return it
//        }
//        return null; // Return null if the floor doesn't exist
//    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    @Override
    public Room updateRoom(Long id, Room room) {
//        Room existingRoom = roomRepository.findById(id).orElse(null);
//        
//        if (existingRoom != null) {
//            // Update each room type field
//            existingRoom.setSingleShare(room.getSingleShare());
//            existingRoom.setDoubleShare(room.getDoubleShare());
//            existingRoom.setTripleShare(room.getTripleShare());
//            existingRoom.setTriplePlus(room.getTriplePlus());
//            
//            return roomRepository.save(existingRoom); // Save updated room
//        }
       return null; // Return null if the room was not found
    }


    @Override
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }
}

