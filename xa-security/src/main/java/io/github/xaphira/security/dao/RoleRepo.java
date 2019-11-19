package io.github.xaphira.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import io.github.xaphira.security.entity.RoleEntity;

public interface RoleRepo extends JpaRepository<RoleEntity, String>, JpaSpecificationExecutor<RoleEntity> {
	
}