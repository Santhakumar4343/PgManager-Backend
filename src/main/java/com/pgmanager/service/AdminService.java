package com.pgmanager.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.pgmanager.entity.Admin;

public interface AdminService {

 	Admin  createAdmin(Admin admin,MultipartFile file) throws IOException;
}
