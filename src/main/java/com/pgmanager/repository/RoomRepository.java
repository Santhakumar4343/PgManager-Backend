package com.pgmanager.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.pgmanager.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
	
}
