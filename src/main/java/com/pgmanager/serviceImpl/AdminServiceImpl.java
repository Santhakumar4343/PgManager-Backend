package com.pgmanager.serviceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pgmanager.entity.Admin;
import com.pgmanager.entity.SuperAdmin;
import com.pgmanager.repository.AdminRepository;
import com.pgmanager.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminRepository adminRepository;

	@Override
    public Admin createAdmin(Admin admin, MultipartFile file) throws IOException {
        
        admin.setAadharcard(file.getBytes());
        
        return adminRepository.save(admin);
    }

	@Override
	public List<Admin> getAllAdmins() {
		
		return adminRepository.findAll();
	}
	
	
	@Override
    public Optional<Admin> getAdminById(long id) {
        return adminRepository.findById(id);
    }

    @Override
    public Admin updateAdmin(long id, Admin updatedAdmin, MultipartFile file) {
        return adminRepository.findById(id).map(admin -> {
            admin.setUsername(updatedAdmin.getUsername());
            admin.setEmail(updatedAdmin.getEmail());
            admin.setPassword(updatedAdmin.getPassword());
            admin.setMobileNumber(updatedAdmin.getMobileNumber());
            admin.setPanNumber(updatedAdmin.getPanNumber());
            admin.setAadharNumber(updatedAdmin.getAadharNumber());
            admin.setPresentAddress(updatedAdmin.getPresentAddress());
            admin.setPermanetAddress(updatedAdmin.getPermanetAddress());
            try {
                if (file != null && !file.isEmpty()) {
                    admin.setAadharcard(file.getBytes());
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed to upload file", e);
            }
            return adminRepository.save(admin);
        }).orElseThrow(() -> new RuntimeException("Admin not found with id: " + id));
    }

    @Override
    public void deleteAdmin(long id) {
        adminRepository.deleteById(id);
    }
    @Override
    public String login(String email, String password) {
        Optional<Admin>   admin = adminRepository.findByEmailAndPassword(email, password);
        return admin.isPresent() ? "Login successful!" : "Invalid email or password!";
    }

	@Override
	public List<Admin> getAdminsByOwnerEmail(String email) {
		
		return adminRepository.findByOwnerEmail(email);
	}
	
	
}
