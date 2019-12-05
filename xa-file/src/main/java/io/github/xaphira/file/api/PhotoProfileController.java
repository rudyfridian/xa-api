package io.github.xaphira.file.api;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.github.xaphira.common.exceptions.BaseControllerException;
import io.github.xaphira.feign.dto.file.FileMetadataDto;
import io.github.xaphira.feign.service.ProfileFeignService;
import io.github.xaphira.file.service.FileGenericImplService;

@RestController
@RequestMapping("/api/file")
public class PhotoProfileController extends BaseControllerException {

	@Autowired
	private FileGenericImplService fileGenericService;

	@Autowired
	private ProfileFeignService profileFeignService;
	
    @Value("${xa.file.path.image.profile}")
    protected String path;
	
	@RequestMapping(value = "/trx/auth/photo-profile/v.1", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<FileMetadataDto> putPhotoProfile(Authentication authentication,
			@RequestPart @Valid MultipartFile photo,
			@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) String locale) throws Exception {
		String path = this.path.concat(authentication.getName());
		this.fileGenericService.removeFile(path);
		FileMetadataDto fileMetaData = this.fileGenericService.putFile(path, photo.getOriginalFilename(), photo.getBytes());
		Map<String, String> url = new HashMap<String, String>();
		url.put("url", fileMetaData.getChecksum());
		this.profileFeignService.putPhotoProfile(url, locale);
		return new ResponseEntity<FileMetadataDto>(fileMetaData, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/vw/get/photo-profile/v.1/{checksum}", method = RequestMethod.GET, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<Resource> downlaodPhotoProfile(Authentication authentication,
			@PathVariable(required = true) String checksum,
			@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) String locale) throws Exception {
		String path = this.path.concat(authentication.getName());
		return new ResponseEntity<Resource>(this.fileGenericService.getFile(checksum, path), HttpStatus.OK);
	}
	
}
