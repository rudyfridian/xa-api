package io.github.xaphira.feign.service.profile;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.github.xaphira.common.http.ApiBaseResponse;
import io.github.xaphira.common.utils.RibbonContext;

@FeignClient(value = RibbonContext.PROFILE_APP)
public interface PhotoProfileFeign {
	
	@RequestMapping(value = RibbonContext.API_PROFILE + "/trx/auth/photo-profile/v.1", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<ApiBaseResponse> putPhotoProfile(
			@RequestBody Map<String, String> url,
			@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) String locale) throws Exception;
}
