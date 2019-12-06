package io.github.xaphira.file.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import io.github.xaphira.common.exceptions.FeignThrowException;
import io.github.xaphira.feign.dto.file.FileMetadataDto;
import io.github.xaphira.feign.service.ProfileFeignService;
import io.github.xaphira.file.dao.FileMetadataRepo;
import io.github.xaphira.file.entity.FileMetadataEntity;
import io.github.xaphira.file.utils.FileUtils;

@Service("photoProfileService")
public class PhotoProfileImplService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Qualifier("fileMetadataRepo")
	private FileMetadataRepo fileMetadataRepo;

	@Autowired
	private FileGenericImplService fileGenericService;

	@Autowired
	private ProfileFeignService profileFeignService;

	@Transactional
	public FileMetadataDto putFile(String filePath, String filename, byte[] fileContent, String locale) throws Exception {
		String checksum = FileUtils.fileChecksum("MD5", fileContent);
		FileMetadataEntity fileExist = fileMetadataRepo.findByChecksum(checksum);
		FileMetadataDto fileMetadataDto = null; 
		if (fileExist != null) {
			System.err.println("exists");
			fileMetadataRepo.deleteByChecksum(checksum);
		}
		fileMetadataDto = fileGenericService.putFile(filePath, filename, fileContent);
		Map<String, String> url = new HashMap<String, String>();
		url.put("url", checksum);
		try {
			this.profileFeignService.putPhotoProfile(url, locale);
		} catch (FeignThrowException e) {
			throw e;
		}
		if (fileExist != null) {
		    File currentFile = new File(filePath, fileExist.getChecksum());
		    currentFile.delete();
		}
		return fileMetadataDto;
	}
	
}