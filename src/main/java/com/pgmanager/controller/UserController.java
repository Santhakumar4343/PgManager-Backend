package com.pgmanager.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.multipart.MultipartFile;

import com.pgmanager.entity.SuperAdmin;
import com.pgmanager.entity.User;
import com.pgmanager.repository.UserRepository;
import com.pgmanager.service.OtpService;
import com.pgmanager.serviceImpl.UserServiceImpl;

@RestController
@RequestMapping("/api/users")
public class UserController {

	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private OtpService otpService; 
	
	 private User pendingUser;
	
	 @PostMapping("/register")
	    public ResponseEntity<String> createUser(
	           @RequestBody User user
) {
	        try {
	       
               
	            this.pendingUser=user;
	          

	            otpService.generateOtp(user.getEmail());
	            return  ResponseEntity.ok("OTP generated successfully");
	        } catch (Exception e) {
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	 
	 
	 @PostMapping("/validate-otp")
	    public ResponseEntity<String> validateOtp(@RequestBody Map<String, String> request) throws IOException {
	        String otp = request.get("otp");

	        // Check for email using OTP
	        String email = otpService.findEmailByOtp(otp);
	        if (email == null) {
	            return ResponseEntity.badRequest().body("Invalid OTP or OTP has expired");
	        }

	        boolean isOtpValid = otpService.validateOtp(email, otp);
	        if (!isOtpValid) {
	            return ResponseEntity.badRequest().body("Invalid OTP");
	        }

	        // Clear OTP
	        otpService.clearOtp(email);

	        // Now save the SuperAdmin details to the database
	        User savedUser = userService.createUser(pendingUser);
	        this.pendingUser = null; // Clear pending SuperAdmin after saving

	        return ResponseEntity.ok("User registered successfully!");
	    }
	    
	    @GetMapping("/getAll")
	    public ResponseEntity<List<User>> getAllUsers() {
	        List<User> allUsers = userService.getAllUser();
	        
	        if (allUsers != null && !allUsers.isEmpty()) {
	            return ResponseEntity.ok(allUsers);
	        } else {
	            return ResponseEntity.noContent().build();
	        }
	    }
	    
	    @PutMapping("/update/{id}")
	    public ResponseEntity<User> updateUser(
	            @PathVariable("id") long id,
	            @RequestParam("username") String username,
	            @RequestParam("email") String email,
	            @RequestParam("password") String password,
	            @RequestParam("mobileNumber") String mobileNumber,
	            @RequestParam("panNumber") String panNumber,
	            @RequestParam("aadharNumber") String aadharNumber,
	            @RequestParam("presentAddress") String presentAddress,
	            @RequestParam("permanentAddress") String permanentAddress,
	           
	            @RequestParam(value = "file", required = false) MultipartFile file) {
	        try {
	        	User updatedUser = User.builder()
	                    .username(username)
	                    .email(email)
	                    .password(password)
	                    .mobileNumber(mobileNumber)
	                    .panNumber(panNumber)
	                    .aadharNumber(aadharNumber)
	                    .presentAddress(presentAddress)
	                    .permanetAddress(permanentAddress)
	                    
	                    .build();

	        	User user = userService.updateUser(id, updatedUser, file);
	            return new ResponseEntity<>(user, HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<HttpStatus> deleteAdmin(@PathVariable("id") long id) {
	        try {
	           userService.deleteUser(id);
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	// 
//	    @PostMapping("/login")
//	    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> payload) {
//	        String email = payload.get("email");
//	        String password = payload.get("password");
//	        
//	        String responseMessage = adminService.login(email, password);
//	        Map<String, String> response = new HashMap<>();
//	        response.put("message", responseMessage);
	//
//	        if (responseMessage.equals("Login successful!")) {
//	            return ResponseEntity.ok(response);
//	        } else {
//	            return ResponseEntity.status(401).body(response);
//	        }
//	    }

	    @PostMapping("/login")
	    public ResponseEntity<?> login(@RequestBody Map<String, String> payload) {
	        String email = payload.get("email");
	        String password = payload.get("password");
	        
	        String responseMessage = userService.login(email, password);

	        if (responseMessage.equals("Login successful!")) {
	            // Retrieve the user details after successful login
	            User user = userRepository.findByEmail(email);

	            // Return the user object as the response
	            return ResponseEntity.ok(user);
	        } else {
	            // Return an error response for invalid login
	            Map<String, String> response = new HashMap<>();
	            response.put("message", "Invalid credentials");
	            return ResponseEntity.status(401).body(response);
	        }
	    }

}
