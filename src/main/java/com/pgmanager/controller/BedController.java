package com.pgmanager.controller;



import com.pgmanager.entity.Bed;
import com.pgmanager.serviceImpl.BedServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/beds")
public class BedController {

    @Autowired
    private BedServiceImpl bedService;

    @PostMapping("/{bedId}/assign-user/{userId}")
    public ResponseEntity<Bed> assignUserToBed(@PathVariable Long bedId, @PathVariable Long userId) {
        Bed updatedBed = bedService.assignUserToBed(bedId, userId);
        return ResponseEntity.ok(updatedBed);
    }
}
