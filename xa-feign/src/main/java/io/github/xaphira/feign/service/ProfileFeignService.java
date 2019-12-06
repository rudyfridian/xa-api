package io.github.xaphira.feign.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.github.xaphira.common.http.ApiBaseResponse;
import io.github.xaphira.feign.service.profile.PhotoProfileFeign;

@Service
public class ProfileFeignService extends FeignCommonService<ApiBaseResponse> {
	
	@Autowired
	private PhotoProfileFeign photoProfileFeign;
	
	public ApiBaseResponse putPhotoProfile(Map<String, String> url, String locale) throws Exception {
		ResponseEntity<ApiBaseResponse> response = photoProfileFeign.putPhotoProfile(url, locale);
		return this.recreate(response);
	}

}
