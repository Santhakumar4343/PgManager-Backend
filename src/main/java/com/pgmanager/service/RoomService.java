package com.pgmanager.service;

import java.util.List;

import com.pgmanager.entity.Room;
import com.pgmanager.entity.RoomRequest;

public interface RoomService {

	
	 
    List<Room> getAllRooms();
    Room getRoomById(Long id);
    Room updateRoom(Long id, Room room);
    void deleteRoom(Long id);
	
}
