package io.github.xaphira.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.xaphira.common.exceptions.SystemErrorException;
import io.github.xaphira.common.http.ApiBaseResponse;
import io.github.xaphira.common.utils.ErrorCode;
import io.github.xaphira.feign.dto.security.SettingsDto;
import io.github.xaphira.security.dao.UserRepo;
import io.github.xaphira.security.entity.UserEntity;

@Service("settingsService")
public class SettingsImplService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepo userRepo;

	public ApiBaseResponse doUpdateSettings(SettingsDto p_dto, UserEntity p_user, String p_locale) throws Exception {
		if (p_user.getId() != null) {
			p_user.setTheme(p_dto.getTheme());
			p_user.setLocale(p_dto.getLocale());
			userRepo.save(p_user);
			return null;	
		} else
			throw new SystemErrorException(ErrorCode.ERR_SYS0404);
	}

}
