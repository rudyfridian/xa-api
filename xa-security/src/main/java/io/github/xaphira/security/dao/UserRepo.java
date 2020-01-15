package io.github.xaphira.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.xaphira.security.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, String>, JpaSpecificationExecutor<UserEntity> {

	@Query("SELECT u FROM UserEntity u JOIN FETCH u.roles r JOIN FETCH u.profile p JOIN FETCH u.settings s WHERE LOWER(u.username) = :username OR LOWER(u.email) = :username")
	UserEntity loadByUsername(@Param("username") String username);
	
	UserEntity findByUsername(String username);

}