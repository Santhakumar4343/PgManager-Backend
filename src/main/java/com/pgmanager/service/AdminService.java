package com.pgmanager.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.pgmanager.entity.Admin;
import com.pgmanager.entity.SuperAdmin;

public interface AdminService {

 	Admin  createAdmin(Admin admin,MultipartFile file) throws IOException;
 	
 	List<Admin> getAllAdmins();
 	
 	 Optional<Admin> getAdminById(long id);
     Admin updateAdmin(long id, Admin updatedAdmin, MultipartFile file);
     void deleteAdmin(long id);
     public String login(String email, String password) ;
     public List<Admin> getAdminsByOwnerEmail(String email);
}
