package io.github.xaphira.feign.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.xaphira.feign.dto.file.FileMetadataDto;
import io.github.xaphira.feign.service.file.PhotoProfileFeign;

@Service
public class FileFeignService {
	
	@Autowired
	private PhotoProfileFeign photoProfileFeign;
	
	public FileMetadataDto putPhotoProfile(File photo, String path, String locale) throws Exception {
		return photoProfileFeign.putPhotoProfile(photo, locale).getBody();
	}

}
