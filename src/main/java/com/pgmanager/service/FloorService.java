package com.pgmanager.service;

import java.util.List;

import com.pgmanager.entity.Floor;

public interface FloorService {

	 Floor createFloor(Long propertyId, Floor floor);
	    List<Floor> getAllFloors();
	    Floor getFloorById(Long id);
	    Floor updateFloor(Long id, Floor floor);
	    void deleteFloor(Long id);
}
