package com.pgmanager.service;



import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OtpService {

    private final HashMap<String, String> otpStorage = new HashMap<>();
    @Autowired
    private EmailService emailService;
    public String generateOtp(String email) {
        // Generate a random 6-digit OTP
        Random random = new Random();
        String otp = String.format("%06d", random.nextInt(999999));
        
        // Store OTP in the map with email as the key
        otpStorage.put(email, otp);
        emailService.sendOtpEmail(email, otp);
        // Simulate sending OTP (here we just print it for demonstration)
        System.out.println("Generated OTP for " + email + ": " + otp);
        
        return otp;
    }

    public boolean validateOtp(String email, String otp) {
        // Check if OTP is valid for the given email
        return otpStorage.containsKey(email) && otpStorage.get(email).equals(otp);
    }

    public void clearOtp(String email) {
        // Remove OTP after validation
        otpStorage.remove(email);
    }
    public String findEmailByOtp(String otp) {
        // Iterate over the otpStorage to find the corresponding email for the OTP
        for (Map.Entry<String, String> entry : otpStorage.entrySet()) {
            if (entry.getValue().equals(otp)) {
                return entry.getKey(); // Return email if OTP matches
            }
        }
        return null; // Return null if no match is found
    }
}
