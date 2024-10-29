package com.pgmanager.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.pgmanager.entity.Admin;
import com.pgmanager.entity.User;

public interface UserService {

	
 	User  createUser(User user) throws IOException;
 	
 	List<User> getAllUser();
 	
 	 Optional<User> getUserById(long id);
     User updateUser(long id, User updatedUser, MultipartFile file);
     void deleteUser(long id);
     public String login(String email, String password) ;
}
