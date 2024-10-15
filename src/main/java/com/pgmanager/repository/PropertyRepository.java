package com.pgmanager.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.pgmanager.entity.Property;

public interface PropertyRepository extends JpaRepository<Property, Long> {
}
