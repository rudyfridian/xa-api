package io.github.xaphira.master.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import io.github.xaphira.master.entity.ProvinceEntity;

public interface ProvinceRepo extends JpaRepository<ProvinceEntity, String>, JpaSpecificationExecutor<ProvinceEntity> {
	
}