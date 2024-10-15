package com.pgmanager.service;

import com.pgmanager.entity.SuperAdmin;

public interface SuperAdminService {

	 public String register(SuperAdmin superAdmin);

	    public String login(String email, String password) ;
	  
	    public SuperAdmin UpdateSuperAdmin(String email,SuperAdmin superAdmin);
}





   

