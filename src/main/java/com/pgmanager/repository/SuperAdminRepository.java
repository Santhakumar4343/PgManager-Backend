package com.pgmanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pgmanager.entity.SuperAdmin;

public interface SuperAdminRepository extends JpaRepository<SuperAdmin, Long> {
    Optional<SuperAdmin> findByEmailAndPassword(String email, String password);
    Optional<SuperAdmin> findByEmail(String email);
    Optional<SuperAdmin> findByUsername(String username);
}
