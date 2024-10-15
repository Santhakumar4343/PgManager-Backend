package com.pgmanager.controller;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pgmanager.entity.SuperAdmin;
import com.pgmanager.service.OtpService;
import com.pgmanager.serviceImpl.SuperAdminServiceImpl;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/superadmin")
public class SuperAdminController {

    @Autowired
    private SuperAdminServiceImpl superAdminService;
    @Autowired
    private OtpService otpService; 
 
 // Store SuperAdmin temporarily until OTP verification
    private SuperAdmin pendingSuperAdmin;

    // Save the temporary SuperAdmin details
    @PostMapping("/register")
    public ResponseEntity<String> saveSuperAdmin(@RequestBody SuperAdmin superAdmin) {
        // Save the SuperAdmin details temporarily
        this.pendingSuperAdmin = superAdmin;
        // Send OTP logic here...
        otpService.generateOtp(superAdmin.getEmail());
        return ResponseEntity.ok("OTP sent to your email");
    }

    // Validate OTP and register user
    @PostMapping("/validate-otp")
    public ResponseEntity<String> validateOtp(@RequestBody Map<String, String> request) {
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
        SuperAdmin savedSuperAdmin = superAdminService.saveSuperAdminByEmail(pendingSuperAdmin);
        this.pendingSuperAdmin = null; // Clear pending SuperAdmin after saving

        return ResponseEntity.ok("User registered successfully!");
    }
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        String password = payload.get("password");
        
        String responseMessage = superAdminService.login(email, password);
        Map<String, String> response = new HashMap<>();
        response.put("message", responseMessage);

        if (responseMessage.equals("Login successful!")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body(response);
        }
    }

    
   
    @GetMapping("/get/{email}")
    public ResponseEntity<SuperAdmin> getSuperAdmin(@PathVariable String email) {
        Optional<SuperAdmin> superAdmin = superAdminService.getSuperAdmin(email);
        
        // Check if the superAdmin exists and return it, otherwise return 404 Not Found
        if (superAdmin.isPresent()) {
            return new ResponseEntity<>(superAdmin.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/update/{email}")
    public ResponseEntity<SuperAdmin> updateSuperAdmin(
            @PathVariable String email, 
            @RequestBody SuperAdmin superAdmin) {
        try {
            // Call the service to update the SuperAdmin
            SuperAdmin updatedAdmin = superAdminService.UpdateSuperAdmin(email, superAdmin);
            return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
        } catch (Exception e) {
            // Return 404 if the SuperAdmin was not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
}

