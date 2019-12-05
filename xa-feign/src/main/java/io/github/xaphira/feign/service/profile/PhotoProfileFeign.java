package io.github.xaphira.feign.service.profile;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import feign.FeignException;
import feign.hystrix.FallbackFactory;
import io.github.xaphira.common.http.ApiBaseResponse;
import io.github.xaphira.common.utils.RibbonContext;

@FeignClient(value = RibbonContext.PROFILE_APP, fallbackFactory = PhotoProfileFeignFallbackFactory.class)
public interface PhotoProfileFeign {
	
	@RequestMapping(value = RibbonContext.API_PROFILE + "/trx/auth/photo-profile/v.1", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<ApiBaseResponse> putPhotoProfile(
			@RequestBody Map<String, String> url,
			@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) String locale) throws Exception;
}

@Component
class PhotoProfileFeignFallbackFactory implements FallbackFactory<PhotoProfileFeign> {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Override
	public PhotoProfileFeign create(Throwable cause) {
		return new PhotoProfileFeign() {
			@Override
			public ResponseEntity<ApiBaseResponse> putPhotoProfile(Map<String, String> url, String locale) throws Exception {
				if (cause instanceof FeignException) {
					FeignException fe = ((FeignException)cause);
					LOGGER.error("Error took place when using Feign client to send HTTP Request. Status code : {} , methodKey : {}", fe.status(), fe.getMessage());
					return new ResponseEntity<ApiBaseResponse>(HttpStatus.valueOf(fe.status()));
				}
				return new ResponseEntity<ApiBaseResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		};
	}		
}