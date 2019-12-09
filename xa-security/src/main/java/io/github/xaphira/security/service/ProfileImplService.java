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
import io.github.xaphira.feign.dto.security.UserDto;
import io.github.xaphira.feign.service.ProfileService;
import io.github.xaphira.security.dao.UserRepo;
import io.github.xaphira.security.entity.UserEntity;

@Service("profileService")
public class ProfileImplService implements ProfileService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepo userRepo;

	@Transactional
	public ApiBaseResponse doUpdateProfile(UserDto p_dto, UserEntity p_user, String p_locale) throws Exception {
		if (p_user.getUsername() != null) {
			p_user = this.userRepo.findByUsername(p_user.getUsername());
			p_user.setAddress(p_dto.getAddress());
			p_user.setCity(p_dto.getCity());
			p_user.setProvince(p_dto.getProvince());
			p_user.setDistrictCode(p_dto.getDistrictCode());
			p_user.setDescription(p_dto.getDescription());
			if (p_dto.getName() != null)
				p_user.setName(p_dto.getName());
			if (p_dto.getEmail() != null) {
				if (p_dto.getEmail().matches(PatternGlobal.EMAIL.getRegex())) {
					p_user.setEmail(p_dto.getEmail());	
				} else
					throw new SystemErrorException(ErrorCode.ERR_SCR0008);
			}
			if (p_dto.getPhoneNumber() != null) {
				if (p_dto.getPhoneNumber().matches(PatternGlobal.PHONE_NUMBER.getRegex())) {
					p_user.setPhoneNumber(p_dto.getPhoneNumber());	
				} else
					throw new SystemErrorException(ErrorCode.ERR_SCR0007);
			}
			if (p_dto.getMobileNumber() != null) {
				if (p_dto.getMobileNumber().matches(PatternGlobal.PHONE_NUMBER.getRegex())) {
					p_user.setMobileNumber(p_dto.getMobileNumber());	
				} else
					throw new SystemErrorException(ErrorCode.ERR_SCR0007);
			}
			userRepo.save(p_user);
			return null;
		} else
			throw new SystemErrorException(ErrorCode.ERR_SYS0404);
	}

	@Override
	public void doUpdatePhoto(Map<String, String> url, Authentication authentication, String locale) throws Exception {
		UserEntity user = (UserEntity) authentication.getPrincipal();
		if (user.getUsername() != null && url != null) {
			user = userRepo.findByUsername(user.getUsername());
			user.setImage(url.get("url"));
			userRepo.save(user);
		} else
			throw new SystemErrorException(ErrorCode.ERR_SYS0404);
	}

}
