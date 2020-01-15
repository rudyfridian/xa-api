package io.github.xaphira.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import io.github.xaphira.security.entity.ProfileEntity;

public interface ProfileRepo extends JpaRepository<ProfileEntity, String>, JpaSpecificationExecutor<ProfileEntity> {

	ProfileEntity findByUser_Username(String username);

}