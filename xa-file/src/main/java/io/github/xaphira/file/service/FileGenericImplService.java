package io.github.xaphira.file.service;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import io.github.xaphira.common.exceptions.SystemErrorException;
import io.github.xaphira.common.utils.ErrorCode;
import io.github.xaphira.feign.dto.file.FileMetadataDto;
import io.github.xaphira.file.dao.FileMetadataRepo;
import io.github.xaphira.file.entity.FileMetadataEntity;
import io.github.xaphira.file.utils.FileUtils;

@Service("fileGenericService")
public class FileGenericImplService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Qualifier("fileMetadataRepo")
	private FileMetadataRepo fileMetadataRepo;

	@Autowired
	private FileUtils fileUtils;
	
    @Value("${xa.file.path.tmp:D:\\Temps\\}")
    protected String filePath;

	public FileMetadataDto putFile(String filePath, String filename, byte[] fileContent) throws Exception {
		if (filePath.isEmpty()) filePath = this.filePath;
		FileMetadataDto fileMetadataDto = fileUtils.writeFile(filePath, filename, fileContent);
		FileMetadataEntity fileMetadata = new FileMetadataEntity();
		fileMetadata.setChecksum(fileMetadataDto.getChecksum());
		fileMetadata.setExtension(fileMetadataDto.getExtension());
		fileMetadata.setFileDate(fileMetadataDto.getFileDate());
		fileMetadata.setFileType(fileMetadataDto.getFileType());
		fileMetadata.setFullname(fileMetadataDto.getFullname());
		fileMetadata.setFullPath(fileMetadataDto.getFullPath());
		fileMetadata.setLocation(fileMetadataDto.getLocation());
		fileMetadata.setShortname(fileMetadataDto.getShortname());
		fileMetadata.setSize(fileMetadataDto.getSize());
		try {
			fileMetadataRepo.save(fileMetadata);
		} catch (DataIntegrityViolationException e) {
			LOGGER.warn(e.getMessage());
		}
		return fileMetadataDto;
	}
	
	public Resource getFile(String checksum, String path) throws Exception {
		try {
			Path file = Paths.get(path).resolve(checksum);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				LOGGER.error("Fail to load file {} or file does not exist", checksum);
				throw new SystemErrorException(ErrorCode.ERR_SYS0404);
			}
		} catch (MalformedURLException e) {
			LOGGER.error("Fail to load file {}", checksum);
			throw new SystemErrorException(ErrorCode.ERR_SYS0001);
		}
	}
	
	public void removeFile(String path) throws Exception {
		try {
			String[] files = new File(path).list();
			for(String s: files){
			    File currentFile = new File(path,s);
			    currentFile.delete();
			}			
		} catch (NullPointerException e) {}
	}
	
}