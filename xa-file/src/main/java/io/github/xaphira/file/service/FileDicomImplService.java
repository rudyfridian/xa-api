package io.github.xaphira.file.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.github.xaphira.feign.dto.file.FileMetadataDto;
import io.github.xaphira.feign.service.file.FileDicomService;
import io.github.xaphira.file.dao.FileMetadataRepo;
import io.github.xaphira.file.entity.FileMetadataEntity;
import io.github.xaphira.file.utils.FileUtils;

@Service("fileDicomService")
public class FileDicomImplService implements FileDicomService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Qualifier("fileMetadataRepo")
	private FileMetadataRepo fileMetadataRepo;

	@Autowired
	private FileUtils fileUtils;

	@Override
	public FileMetadataDto putFileDicomDcm(String filePath, String filename, byte[] fileContent) throws Exception {
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

	@Override
	public List<FileMetadataDto> putFileDicomZip(MultipartFile zip, String path) throws Exception {
		List<FileMetadataDto> fileMetadataDtos = fileUtils.extract(path, zip);
		List<FileMetadataEntity> fileMetadatas = new ArrayList<FileMetadataEntity>();
		fileMetadataDtos.forEach(fileMetadataDto->{
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
			fileMetadatas.add(fileMetadata);
		});
		try {
			fileMetadataRepo.saveAll(fileMetadatas);
		} catch (DataIntegrityViolationException e) {
			LOGGER.warn(e.getMessage());
		}
		return fileMetadataDtos;
	}
	
}