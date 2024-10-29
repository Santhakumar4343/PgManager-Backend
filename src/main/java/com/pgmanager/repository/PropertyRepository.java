package com.pgmanager.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pgmanager.entity.Property;

public interface PropertyRepository extends JpaRepository<Property, Long> {
	
	Property findById(long id);
	
	List<Property> findByOwneremail(String email);
	
	List<Property> findByAdminId(Long adminId);
	
}
