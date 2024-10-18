package com.pgmanager.serviceImpl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pgmanager.entity.Admin;
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
	
	
}
