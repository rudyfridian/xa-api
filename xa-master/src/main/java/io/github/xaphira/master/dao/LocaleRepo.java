package io.github.xaphira.master.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import io.github.xaphira.master.entity.LocaleEntity;

public interface LocaleRepo extends JpaRepository<LocaleEntity, String>, JpaSpecificationExecutor<LocaleEntity> {
	
}