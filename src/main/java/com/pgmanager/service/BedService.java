package com.pgmanager.service;

import com.pgmanager.entity.Bed;

public interface BedService {

	public Bed assignUserToBed(Long bedId, Long userId);
}
