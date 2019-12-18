package io.github.xaphira.master.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import io.github.xaphira.master.entity.CityEntity;

public interface CityRepo extends JpaRepository<CityEntity, String>, JpaSpecificationExecutor<CityEntity> {
	
}