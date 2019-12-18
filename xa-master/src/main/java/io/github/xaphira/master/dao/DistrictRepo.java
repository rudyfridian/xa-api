package io.github.xaphira.master.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import io.github.xaphira.master.entity.DistrictEntity;

public interface DistrictRepo extends JpaRepository<DistrictEntity, String>, JpaSpecificationExecutor<DistrictEntity> {
	
}