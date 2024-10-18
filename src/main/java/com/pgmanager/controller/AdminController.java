package com.pgmanager.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pgmanager.entity.Admin;
import com.pgmanager.service.AdminService;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

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
                    .build();

          
            Admin savedAdmin = adminService.createAdmin(admin, file);
            return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

