package io.github.xaphira.master.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import io.github.xaphira.master.entity.CountryEntity;

public interface CountryRepo extends JpaRepository<CountryEntity, String>, JpaSpecificationExecutor<CountryEntity> {
	
}