package com.pgmanager.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgmanager.entity.Bed;
import com.pgmanager.entity.User;
import com.pgmanager.repository.BedRepository;
import com.pgmanager.repository.UserRepository;
import com.pgmanager.service.BedService;

@Service
public class BedServiceImpl implements BedService {

	@Autowired BedRepository bedRepository;
	 @Autowired
	    private UserRepository userRepository;
     
	 @Override
	    public Bed assignUserToBed(Long bedId, Long userId) {
	        // Fetch bed by ID
	        Bed bed = bedRepository.findById(bedId)
	                .orElseThrow(() -> new RuntimeException("Bed not found"));

	        // Fetch user by ID
	        User user = userRepository.findById(userId)
	                .orElseThrow(() -> new RuntimeException("User not found"));

	        // Assign the user to the bed
	        bed.setAssignedUser(user);
	        bed.setAvailable(false); // Set the bed as occupied

	        // Save the updated bed
	        return bedRepository.save(bed);
	    }
}
