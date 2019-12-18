package io.github.xaphira.master.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import io.github.xaphira.master.entity.SubDistrictEntity;

public interface SubDistrictRepo extends JpaRepository<SubDistrictEntity, String>, JpaSpecificationExecutor<SubDistrictEntity> {
	
}