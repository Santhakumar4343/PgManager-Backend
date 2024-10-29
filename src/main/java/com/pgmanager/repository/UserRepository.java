package com.pgmanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pgmanager.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
	 Optional<User> findByEmailAndPassword(String email,String password);

	 User findByEmail(String email);;
}
