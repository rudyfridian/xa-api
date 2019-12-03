package io.github.xaphira.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.xaphira.common.exceptions.SystemErrorException;
import io.github.xaphira.common.http.ApiBaseResponse;
import io.github.xaphira.common.pattern.PatternGlobal;
import io.github.xaphira.common.utils.ErrorCode;
import io.github.xaphira.feign.dto.security.UserDto;
import io.github.xaphira.security.dao.UserRepo;
import io.github.xaphira.security.entity.UserEntity;

@Service("profileService")
public class ProfileImplService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepo userRepo;

	public ApiBaseResponse doUpdateProfile(UserDto p_dto, UserEntity p_user, String p_locale) throws Exception {
		if (p_user.getId() != null) {
			p_user.setAddress(p_dto.getAddress());
			p_user.setCity(p_dto.getCity());
			p_user.setProvince(p_dto.getProvince());
			p_user.setDistrictCode(p_dto.getDistrictCode());
			p_user.setDescription(p_dto.getDescription());
			if (p_dto.getName() != null)
				p_user.setName(p_dto.getName());
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
			if (p_dto.getEmail() != null) {
				if (p_dto.getEmail().matches(PatternGlobal.EMAIL.getRegex())) {
					p_user.setEmail(p_dto.getEmail());	
				} else
					throw new SystemErrorException(ErrorCode.ERR_SCR0008);
			}
			userRepo.save(p_user);
			return null;
		} else
			throw new SystemErrorException(ErrorCode.ERR_SYS0404);
	}

}
