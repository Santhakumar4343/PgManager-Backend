package com.pgmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pgmanager.entity.Bed;

public interface BedRepository extends JpaRepository<Bed, Long> {
    List<Bed> findByRoomIdAndAvailable(Long roomId, boolean available);
}

