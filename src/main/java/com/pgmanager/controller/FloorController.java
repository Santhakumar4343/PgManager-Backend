package com.pgmanager.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pgmanager.entity.Floor;
import com.pgmanager.service.FloorService;

@RestController
@RequestMapping("/api/floors")
public class FloorController {

    @Autowired
    private FloorService floorService;

    @PostMapping("/save/{propertyId}")
    public Floor createFloor(@PathVariable Long propertyId, @RequestBody Floor floor) {
        return floorService.createFloor(propertyId, floor);
    }

    @GetMapping("/getAll")
    public List<Floor> getAllFloors() {
        return floorService.getAllFloors();
    }

    @GetMapping("/get/{id}")
    public Floor getFloorById(@PathVariable Long id) {
        return floorService.getFloorById(id);
    }

    @PutMapping("/update/{id}")
    public Floor updateFloor(@PathVariable Long id, @RequestBody Floor floor) {
        return floorService.updateFloor(id, floor);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFloor(@PathVariable Long id) {
        floorService.deleteFloor(id);
    }
}
