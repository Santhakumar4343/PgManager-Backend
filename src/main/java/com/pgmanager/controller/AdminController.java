package com.pgmanager.controller;
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

import com.pgmanager.entity.Admin;
import com.pgmanager.repository.AdminRepository;
import com.pgmanager.service.AdminService;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;
    
    @Autowired
    private AdminRepository adminRepository;

    @PostMapping("/save")
    public ResponseEntity<Admin> createAdmin(
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("mobileNumber") String mobileNumber,
            @RequestParam("panNumber") String panNumber,
            @RequestParam("aadharNumber") String aadharNumber,
            @RequestParam("presentAddress") String presentAddress,
            @RequestParam("permanentAddress") String permanentAddress,
            @RequestParam("ownerEmail") String ownerEmail,
            @RequestParam("file") MultipartFile file) {
        try {
       
            Admin admin = Admin.builder()
                    .username(username)
                    .email(email)
                    .password(password)
                    .mobileNumber(mobileNumber)
                    .panNumber(panNumber)
                    .aadharNumber(aadharNumber)
                    .presentAddress(presentAddress)
                    .permanetAddress(permanentAddress)
                    .ownerEmail(ownerEmail)
                    .build();

          
            Admin savedAdmin = adminService.createAdmin(admin, file);
            return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/getAll")
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> allAdmins = adminService.getAllAdmins();
        
        if (allAdmins != null && !allAdmins.isEmpty()) {
            return ResponseEntity.ok(allAdmins);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<Admin> updateAdmin(
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
            Admin updatedAdmin = Admin.builder()
                    .username(username)
                    .email(email)
                    .password(password)
                    .mobileNumber(mobileNumber)
                    .panNumber(panNumber)
                    .aadharNumber(aadharNumber)
                    .presentAddress(presentAddress)
                    .permanetAddress(permanentAddress)
                    
                    .build();

            Admin admin = adminService.updateAdmin(id, updatedAdmin, file);
            return new ResponseEntity<>(admin, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteAdmin(@PathVariable("id") long id) {
        try {
            adminService.deleteAdmin(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//
//    @PostMapping("/login")
//    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> payload) {
//        String email = payload.get("email");
//        String password = payload.get("password");
//        
//        String responseMessage = adminService.login(email, password);
//        Map<String, String> response = new HashMap<>();
//        response.put("message", responseMessage);
//
//        if (responseMessage.equals("Login successful!")) {
//            return ResponseEntity.ok(response);
//        } else {
//            return ResponseEntity.status(401).body(response);
//        }
//    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        String password = payload.get("password");
        
        String responseMessage = adminService.login(email, password);

        if (responseMessage.equals("Login successful!")) {
            // Retrieve the user details after successful login
            Admin user = adminRepository.findByEmail(email);

            // Return the user object as the response
            return ResponseEntity.ok(user);
        } else {
            // Return an error response for invalid login
            Map<String, String> response = new HashMap<>();
            response.put("message", "Invalid credentials");
            return ResponseEntity.status(401).body(response);
        }
    }

    
    @GetMapping("/getAdminsByOwnerEmail/{ownerEmail}")
    	public ResponseEntity<List<Admin>> getAdminsByOwnerEmail(@PathVariable String ownerEmail){
    	
    	List<Admin> getAdmins=adminService.getAdminsByOwnerEmail(ownerEmail);
    	
    	if(getAdmins!=null&& !getAdmins.isEmpty()) {
    		return ResponseEntity.ok(getAdmins);
    		
    	}
    	else {
    		return ResponseEntity.noContent().build();
    	}
    }
    
}

