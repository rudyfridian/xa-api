package io.github.xaphira.feign.service.file;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import io.github.xaphira.feign.dto.file.FileMetadataDto;

public interface FileDicomService {
	
	public FileMetadataDto putFileDicomDcm(String filePath, String filename, byte[] fileContent) throws Exception;
	
	public List<FileMetadataDto> putFileDicomZip(MultipartFile zip, String path) throws Exception;

}
