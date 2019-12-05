package io.github.xaphira.feign.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.github.xaphira.common.exceptions.FeignThrowException;
import io.github.xaphira.common.http.ApiBaseResponse;
import io.github.xaphira.feign.service.profile.PhotoProfileFeign;

@Service
public class ProfileFeignService {
	
	@Autowired
	private PhotoProfileFeign photoProfileFeign;
	
	public ApiBaseResponse putPhotoProfile(Map<String, String> url, String locale) throws Exception {
		ResponseEntity<ApiBaseResponse> response = photoProfileFeign.putPhotoProfile(url, locale);
		if(response.getStatusCode().is2xxSuccessful())
			return response.getBody();
		else
			throw new FeignThrowException(response.getStatusCode());
		
	}

}
