package io.github.xaphira.security.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.xaphira.common.exceptions.SystemErrorException;
import io.github.xaphira.common.http.ApiBaseResponse;
import io.github.xaphira.common.pattern.PatternGlobal;
import io.github.xaphira.common.utils.ErrorCode;
import io.github.xaphira.feign.dto.security.ProfileDto;
import io.github.xaphira.feign.service.ProfileService;
import io.github.xaphira.security.dao.ProfileRepo;
import io.github.xaphira.security.entity.ProfileEntity;
import io.github.xaphira.security.entity.UserEntity;

@Service("profileService")
public class ProfileImplService implements ProfileService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProfileRepo profileRepo;

	@Transactional
	public ApiBaseResponse doUpdateProfile(ProfileDto p_dto, UserEntity p_user, String p_locale) throws Exception {
		if (p_user.getUsername() != null) {
			ProfileEntity profile = this.profileRepo.findByUser_Username(p_user.getUsername());
			profile.setAddress(p_dto.getAddress());
			profile.setCity(p_dto.getCity());
			profile.setProvince(p_dto.getProvince());
			profile.setDistrict(p_dto.getDistrict());
			profile.setSubDistrict(p_dto.getSubDistrict());
			profile.setDescription(p_dto.getDescription());
			if (p_dto.getName() != null)
				profile.setName(p_dto.getName());
			if (p_dto.getEmail() != null) {
				if (p_dto.getEmail().matches(PatternGlobal.EMAIL.getRegex())) {
					p_user.setEmail(p_dto.getEmail());	
				} else
					throw new SystemErrorException(ErrorCode.ERR_SCR0008);
			}
			if (p_dto.getPhoneNumber() != null) {
				if (p_dto.getPhoneNumber().matches(PatternGlobal.PHONE_NUMBER.getRegex())) {
					profile.setPhoneNumber(p_dto.getPhoneNumber());	
				} else
					throw new SystemErrorException(ErrorCode.ERR_SCR0007A);
			}
			if (p_dto.getMobileNumber() != null) {
				if (p_dto.getMobileNumber().matches(PatternGlobal.PHONE_NUMBER.getRegex())) {
					profile.setMobileNumber(p_dto.getMobileNumber());	
				} else
					throw new SystemErrorException(ErrorCode.ERR_SCR0007B);
			}
			this.profileRepo.save(profile);
			return null;
		} else
			throw new SystemErrorException(ErrorCode.ERR_SYS0404);
	}
	
	public ProfileDto getProfile(UserEntity p_user, String p_locale) throws Exception {
		if (p_user.getUsername() != null) {
			ProfileDto dto = new ProfileDto();
			ProfileEntity profile = this.profileRepo.findByUser_Username(p_user.getUsername());
			dto.setUsername(p_user.getUsername());
			dto.setName(profile.getName());
			dto.setEmail(p_user.getEmail());
			dto.setAddress(profile.getAddress());
			dto.setCity(profile.getCity());
			dto.setProvince(profile.getProvince());
			dto.setDistrict(profile.getDistrict());
			dto.setSubDistrict(profile.getSubDistrict());
			dto.setImage(profile.getImage());
			dto.setMobileNumber(profile.getMobileNumber());
			dto.setPhoneNumber(profile.getPhoneNumber());
			dto.setDescription(profile.getDescription());
			return dto;
		} else
			throw new SystemErrorException(ErrorCode.ERR_SYS0404);
	}

	@Transactional
	@Override
	public void doUpdatePhoto(Map<String, String> url, Authentication authentication, String locale) throws Exception {
		UserEntity user = (UserEntity) authentication.getPrincipal();
		if (user.getUsername() != null && url != null) {
			ProfileEntity profile = this.profileRepo.findByUser_Username(user.getUsername());
			profile.setImage(url.get("url"));
			this.profileRepo.save(profile);
		} else
			throw new SystemErrorException(ErrorCode.ERR_SYS0404);
	}

}
