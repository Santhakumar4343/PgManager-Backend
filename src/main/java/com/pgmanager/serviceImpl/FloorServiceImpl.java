package com.pgmanager.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgmanager.entity.Floor;
import com.pgmanager.entity.Property;
import com.pgmanager.repository.FloorRepository;
import com.pgmanager.repository.PropertyRepository;
import com.pgmanager.service.FloorService;

@Service
public class FloorServiceImpl implements FloorService {

    @Autowired
    private FloorRepository floorRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public Floor createFloor(Long propertyId, Floor floor) {
        Property property = propertyRepository.findById(propertyId).orElse(null);
        if (property != null) {
            floor.setProperty(property);
            return floorRepository.save(floor);
        }
        return null;
    }

    @Override
    public List<Floor> getAllFloors() {
        return floorRepository.findAll();
    }

    @Override
    public Floor getFloorById(Long id) {
        return floorRepository.findById(id).orElse(null);
    }

    @Override
    public Floor updateFloor(Long id, Floor floor) {
        Floor existingFloor = floorRepository.findById(id).orElse(null);
        if (existingFloor != null) {
            existingFloor.setFloorName(floor.getFloorName());
            return floorRepository.save(existingFloor);
        }
        return null;
    }

    @Override
    public void deleteFloor(Long id) {
        floorRepository.deleteById(id);
    }
}
