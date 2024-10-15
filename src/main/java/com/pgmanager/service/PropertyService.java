package com.pgmanager.service;

import java.util.List;

import com.pgmanager.entity.Property;

public interface PropertyService {

	    public Property addProperty(Property property) ;

	    public List<Property> getAllProperties();
	  
	    Property getPropertyById(Long id);
	    Property updateProperty(Long id, Property property);
	    void deleteProperty(Long id);
	}


