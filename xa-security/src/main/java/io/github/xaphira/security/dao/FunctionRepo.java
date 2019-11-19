package io.github.xaphira.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import io.github.xaphira.security.entity.FunctionEntity;

public interface FunctionRepo extends JpaRepository<FunctionEntity, String>, JpaSpecificationExecutor<FunctionEntity> {

}