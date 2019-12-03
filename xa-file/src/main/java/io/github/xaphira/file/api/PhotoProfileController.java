package io.github.xaphira.file.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.github.xaphira.common.exceptions.BaseControllerException;
import io.github.xaphira.feign.dto.file.FileMetadataDto;
import io.github.xaphira.file.service.FileGenericImplService;

@RestController
@RequestMapping("/api/file")
public class PhotoProfileController extends BaseControllerException {

	@Autowired
	private FileGenericImplService fileGenericService;
	
    @Value("${xa.file.path.image.profile}")
    protected String filePath;
	
	@RequestMapping(value = "/trx/auth/photo-profile/v.1", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<FileMetadataDto> putPhotoProfile(Authentication authentication,
			@RequestPart @Valid MultipartFile photo,
			@RequestHeader(name = "Accept-Language", required = false) String locale) throws Exception {
		this.filePath = this.filePath.concat(authentication.getName());
		return new ResponseEntity<FileMetadataDto>(fileGenericService.putFile(this.filePath, photo.getOriginalFilename(), photo.getBytes()), HttpStatus.OK);
	}
	
}
