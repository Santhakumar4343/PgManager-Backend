package com.pgmanager.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.pgmanager.entity.Floor;

public interface FloorRepository extends JpaRepository<Floor, Long> {
}
