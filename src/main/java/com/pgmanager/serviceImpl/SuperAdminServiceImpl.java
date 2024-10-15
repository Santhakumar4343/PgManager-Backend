package com.pgmanager.serviceImpl;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgmanager.entity.SuperAdmin;
import com.pgmanager.repository.SuperAdminRepository;
import com.pgmanager.service.SuperAdminService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SuperAdminServiceImpl implements SuperAdminService{

    @Autowired
    private SuperAdminRepository superAdminRepository;

    public String register(SuperAdmin superAdmin) {
        // Check if email already exists
        Optional<SuperAdmin> existingByEmail = superAdminRepository.findByEmail(superAdmin.getEmail());
        if (existingByEmail.isPresent()) {
            return "Email already in use!";
        }

        // Check if username already exists
        Optional<SuperAdmin> existingByUsername = superAdminRepository.findByUsername(superAdmin.getUsername());
        if (existingByUsername.isPresent()) {
            return "Username already in use!";
        }

        // Save the SuperAdmin
        superAdminRepository.save(superAdmin);
        return "SuperAdmin registered successfully!";
    }
    public String checkForDuplicate(SuperAdmin superAdmin) {
        Optional<SuperAdmin> existingByEmail = superAdminRepository.findByEmail(superAdmin.getEmail());
        if (existingByEmail.isPresent()) {
            return "Email already in use!";
        }

        Optional<SuperAdmin> existingByUsername = superAdminRepository.findByUsername(superAdmin.getUsername());
        if (existingByUsername.isPresent()) {
            return "Username already in use!";
        }

        return "Valid";
    }

    public SuperAdmin saveSuperAdminByEmail(SuperAdmin superAdmin) {
    	
    	return superAdminRepository.save(superAdmin);
    }
    public Optional<SuperAdmin> getSuperAdmin(String email) {
    	Optional<SuperAdmin> superAdmin=superAdminRepository.findByEmail(email);
    	return superAdmin;
    }

    public String login(String email, String password) {
        Optional<SuperAdmin> superAdmin = superAdminRepository.findByEmailAndPassword(email, password);
        return superAdmin.isPresent() ? "Login successful!" : "Invalid email or password!";
    }
	
    @Override
    public SuperAdmin UpdateSuperAdmin(String email, SuperAdmin superAdmin) {
        // Fetch the existing SuperAdmin using the provided email
        Optional<SuperAdmin> existingAdminOpt = superAdminRepository.findByEmail(email);

        // Check if the SuperAdmin exists
        if (existingAdminOpt.isPresent()) {
            SuperAdmin existingAdmin = existingAdminOpt.get();

            // Update the fields with the new values
            existingAdmin.setMobileNumber(superAdmin.getMobileNumber());
            existingAdmin.setUsername(superAdmin.getUsername());
            existingAdmin.setPassword(superAdmin.getPassword()); // Consider hashing the password before saving

            // Save and return the updated SuperAdmin
            return superAdminRepository.save(existingAdmin);
        } else {
            throw new EntityNotFoundException("SuperAdmin with email " + email + " not found.");
        }
    }

}

