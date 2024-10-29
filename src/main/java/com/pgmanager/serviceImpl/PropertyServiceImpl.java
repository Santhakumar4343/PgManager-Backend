package com.pgmanager.serviceImpl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgmanager.entity.Admin;
import com.pgmanager.entity.Property;
import com.pgmanager.repository.AdminRepository;
import com.pgmanager.repository.PropertyRepository;
import com.pgmanager.service.PropertyService;


@Service
public class PropertyServiceImpl implements PropertyService{
    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private AdminRepository adminRepository;
    @Override
    public Property addProperty(Property property) {
        return propertyRepository.save(property);
    }

    @Override
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    @Override
    public Property getPropertyById(Long id) {
        return propertyRepository.findById(id).orElse(null);
    }

    @Override
    public Property updateProperty(Long id, Property property) {
        Property existingProperty = propertyRepository.findById(id).orElse(null);
        if (existingProperty != null) {
            existingProperty.setName(property.getName());
            existingProperty.setOwnerName(property.getOwnerName());
            existingProperty.setOwnerPhoneNumber(property.getOwnerPhoneNumber());
            existingProperty.setOwneremail(property.getOwneremail());
            existingProperty.setPincode(property.getPincode());
            existingProperty.setPropertyType(property.getPropertyType());
            existingProperty.setAddress(property.getAddress());
            return propertyRepository.save(existingProperty);
        }
        return null;
    }

    @Override
    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }

	@Override
	public Property assignAdmin(long id,Property property) {
		Property savedProperty= propertyRepository.findById(id);
		if(savedProperty!=null) {
			savedProperty.setAdmin(property.getAdmin());
		}
		return propertyRepository.save(savedProperty);
	}
	
	public Property assignAdmin(Long propertyId, Long adminId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new  RuntimeException("Property not found"));

        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        property.setAdmin(admin); // Assign the admin to the property

        return propertyRepository.save(property); // Save and return the updated property
    }

	@Override
	public List<Property> getPropertyBasedOnEmail(String owneremail) {
		
		return propertyRepository.findByOwneremail(owneremail);
	}
	
	
	
}

