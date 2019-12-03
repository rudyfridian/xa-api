package io.github.xaphira.feign.service.file;

import java.io.File;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;

import io.github.xaphira.common.utils.RibbonContext;
import io.github.xaphira.feign.dto.file.FileMetadataDto;

@FeignClient(value = RibbonContext.FILE, fallback = PhotoProfileFeign.PhotoProfileFeignFallback.class)
@RequestMapping(RibbonContext.API_FILE)
public interface PhotoProfileFeign {
	
	@RequestMapping(value = "/trx/auth/photo-profile/v.1", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<FileMetadataDto> putPhotoProfile(
			@RequestPart("photo") @Valid File photo,
			@RequestHeader(name = "Accept-Language", required = false) String locale) throws Exception;
	
	static class PhotoProfileFeignFallback implements PhotoProfileFeign {

		@Override
		public ResponseEntity<FileMetadataDto> putPhotoProfile(File photo, String locale) throws Exception {
			return null;
		}
		
	}
	
}
