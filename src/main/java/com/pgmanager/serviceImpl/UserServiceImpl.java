package com.pgmanager.serviceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pgmanager.entity.User;
import com.pgmanager.repository.UserRepository;
import com.pgmanager.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	
	@Autowired 
	private UserRepository userRepository;
	@Override
	public User createUser(User user) throws IOException {
		
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		
		return userRepository.findAll();
	}

	@Override
	public Optional<User> getUserById(long id) {
		
		Optional<User> optionalUser=userRepository.findById(id);
		
		return optionalUser;
	}

	@Override
	public User updateUser(long id, User updatedUser, MultipartFile file) {
	
		 return userRepository.findById(id).map(user -> {
	            user.setUsername(updatedUser.getUsername());
	            user.setEmail(updatedUser.getEmail());
	            user.setPassword(updatedUser.getPassword());
	            user.setMobileNumber(updatedUser.getMobileNumber());
	            user.setPanNumber(updatedUser.getPanNumber());
	            user.setAadharNumber(updatedUser.getAadharNumber());
	            user.setPresentAddress(updatedUser.getPresentAddress());
	            user.setPermanetAddress(updatedUser.getPermanetAddress());
	            try {
	                if (file != null && !file.isEmpty()) {
	                    user.setAadharcard(file.getBytes());
	                }
	            } catch (IOException e) {
	                throw new RuntimeException("Failed to upload file", e);
	            }
	            return userRepository.save(user);
	        }).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
	}

	@Override
	public void deleteUser(long id) {
		
		userRepository.deleteById(id);
	}

	@Override
	public String login(String email, String password) {
	
	    Optional<User>   user = userRepository.findByEmailAndPassword(email, password);
        return user.isPresent() ? "Login successful!" : "Invalid email or password!";
	}

}
