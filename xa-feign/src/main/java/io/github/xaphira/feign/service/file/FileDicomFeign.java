package io.github.xaphira.feign.service.file;

import java.io.File;
import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

import io.github.xaphira.common.utils.RibbonContext;
import io.github.xaphira.feign.dto.file.FileMetadataDto;

@FeignClient(value = RibbonContext.MASTER, fallback = FileDicomFeign.FileDicomFeignFallback.class)
@RequestMapping(RibbonContext.PATH_MASTER+"/api/file")
public interface FileDicomFeign {
	
	@RequestMapping(value = "/trx/put/dicom-file/v.1", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<FileMetadataDto> putFileDcm(
			@RequestPart("dicom") @Valid File dicom,
			@RequestParam("path") @Valid String path,
			@RequestHeader(name = "Accept-Language", required = false) String locale) throws Exception;
	
	@RequestMapping(value = "/trx/put/dicom-zip/v.1", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FileMetadataDto>> putFileZip(
			@RequestPart("zip") @Valid File zip,
			@RequestParam("path") @Valid String path,
			@RequestHeader(name = "Accept-Language", required = false) String locale) throws Exception;
	
	static class FileDicomFeignFallback implements FileDicomFeign {

		@Override
		public ResponseEntity<FileMetadataDto> putFileDcm(File dicom, String path, String locale) throws Exception {
			return null;
		}

		@Override
		public ResponseEntity<List<FileMetadataDto>> putFileZip(File zip, String path, String locale) throws Exception {
			return null;
		}
		
	}
	
}
