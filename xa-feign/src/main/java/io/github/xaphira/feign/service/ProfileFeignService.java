package io.github.xaphira.feign.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.xaphira.common.http.ApiBaseResponse;
import io.github.xaphira.feign.service.profile.PhotoProfileFeign;

@Service
public class ProfileFeignService {
	
	@Autowired
	private PhotoProfileFeign photoProfileFeign;
	
	public ApiBaseResponse putPhotoProfile(Map<String, String> url, String locale) throws Exception {
		return photoProfileFeign.putPhotoProfile(url, locale).getBody();
	}

}
