package io.github.xaphira.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import io.github.xaphira.security.entity.SettingsEntity;

public interface SettingsRepo extends JpaRepository<SettingsEntity, String>, JpaSpecificationExecutor<SettingsEntity> {

	SettingsEntity findByUser_Username(String username);

}