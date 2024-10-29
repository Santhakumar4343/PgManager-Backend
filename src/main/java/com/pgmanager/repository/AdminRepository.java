package com.pgmanager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pgmanager.entity.Admin;

public interface AdminRepository  extends JpaRepository<Admin, Long>{

	 Optional<Admin> findByEmailAndPassword(String email,String password);
	 List<Admin>  findByOwnerEmail(String email);
	 Admin findByEmail(String email);
}
