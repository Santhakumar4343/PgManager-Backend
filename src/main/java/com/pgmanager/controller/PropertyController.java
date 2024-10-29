package com.pgmanager.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pgmanager.entity.Property;
import com.pgmanager.repository.PropertyRepository;
import com.pgmanager.serviceImpl.PropertyServiceImpl;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {
    @Autowired
    private PropertyServiceImpl propertyService;

    @Autowired
    private PropertyRepository propertyRepository;
    @PostMapping("/save")
    public Property createProperty(@RequestBody Property property) {
        return propertyService.addProperty(property);
    }

    @GetMapping("/getAll")
    public List<Property> getProperties() {
        return propertyService.getAllProperties();
    }
    
    @GetMapping("/get/{id}")
    public Property getPropertyById(@PathVariable Long id) {
        return propertyService.getPropertyById(id);
    }

    @PutMapping("/update/{id}")
    public Property updateProperty(@PathVariable Long id, @RequestBody Property property) {
        return propertyService.updateProperty(id, property);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
    }
    
    @PutMapping("/assignAdmin/{propertyId}")
    public ResponseEntity<Property> assignAdminToProperty(
            @PathVariable Long propertyId, @RequestParam Long adminId) {
        Property updatedProperty = propertyService.assignAdmin(propertyId, adminId);
        return ResponseEntity.ok(updatedProperty);
    }
    
    @GetMapping("/getProperty/{owneremail}")
    
    public ResponseEntity<List<Property>> getPropertyBasedOnEmail(@PathVariable String owneremail){
    	
    	List<Property> getProperties=propertyService.getPropertyBasedOnEmail(owneremail);
    	
    	if(getProperties!=null&& !getProperties.isEmpty()) {
    		return ResponseEntity.ok(getProperties);
    	}
    	else {
    		return ResponseEntity.noContent().build();
    	}
    	
    }
    
    
    @GetMapping("/getPropertyForAdmin/{adminId}")
    public ResponseEntity<List<Property>> getPropertyForAdmin(@PathVariable Long adminId) {
        List<Property> properties = propertyRepository.findByAdminId(adminId);

        if (!properties.isEmpty()) {
            return ResponseEntity.ok(properties);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}




