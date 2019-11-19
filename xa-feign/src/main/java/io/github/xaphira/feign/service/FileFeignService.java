package io.github.xaphira.feign.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.xaphira.feign.dto.file.FileMetadataDto;
import io.github.xaphira.feign.service.file.FileDicomFeign;

@Service
public class FileFeignService {
	
	@Autowired
	private FileDicomFeign fileDicomFeign;
	
	public FileMetadataDto putFileDicomDcm(File dicom, String path, String locale) throws Exception {
		return fileDicomFeign.putFileDcm(dicom, path, locale).getBody();
	}

	public List<FileMetadataDto> putFileDicomZip(File zip, String path, String locale) throws Exception {
		return fileDicomFeign.putFileZip(zip, path, locale).getBody();
	}

}
