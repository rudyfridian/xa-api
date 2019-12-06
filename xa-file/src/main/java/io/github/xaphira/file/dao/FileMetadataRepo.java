package io.github.xaphira.file.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import io.github.xaphira.file.entity.FileMetadataEntity;

public interface FileMetadataRepo extends JpaRepository<FileMetadataEntity, String>, JpaSpecificationExecutor<FileMetadataEntity> {

	FileMetadataEntity findByChecksum(String checksum);

	int deleteByChecksum(String checksum);
	
}