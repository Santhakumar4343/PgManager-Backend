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

import com.pgmanager.entity.Property;
import com.pgmanager.serviceImpl.PropertyServiceImpl;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {
    @Autowired
    private PropertyServiceImpl propertyService;

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
    
}




