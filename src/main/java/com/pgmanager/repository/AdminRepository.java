package com.pgmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pgmanager.entity.Admin;

public interface AdminRepository  extends JpaRepository<Admin, Long>{

}
