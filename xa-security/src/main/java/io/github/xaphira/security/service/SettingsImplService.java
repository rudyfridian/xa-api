package io.github.xaphira.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.xaphira.common.exceptions.SystemErrorException;
import io.github.xaphira.common.http.ApiBaseResponse;
import io.github.xaphira.common.utils.ErrorCode;
import io.github.xaphira.feign.dto.security.SettingsDto;
import io.github.xaphira.security.dao.SettingsRepo;
import io.github.xaphira.security.entity.SettingsEntity;
import io.github.xaphira.security.entity.UserEntity;

@Service("settingsService")
public class SettingsImplService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SettingsRepo settingsRepo;

	@Transactional
	public ApiBaseResponse doUpdateSettings(SettingsDto p_dto, UserEntity p_user, String p_locale) throws Exception {
		if (p_user.getUsername() != null) {
			SettingsEntity settings = this.settingsRepo.findByUser_Username(p_user.getUsername());
			settings.setTheme(p_dto.getTheme());
			if (p_dto.getLocaleCode() != null)
				settings.setLocaleCode(p_dto.getLocaleCode());
			if (p_dto.getLocaleIdentifier() != null)
				settings.setLocaleIdentifier(p_dto.getLocaleIdentifier());
			if (p_dto.getLocaleIcon() != null)
				settings.setLocaleIcon(p_dto.getLocaleIcon());
			this.settingsRepo.save(settings);
			return null;	
		} else
			throw new SystemErrorException(ErrorCode.ERR_SYS0404);
	}
	
	public SettingsDto getSettings(UserEntity p_user, String p_locale) throws Exception {
		if (p_user.getUsername() != null) {
			SettingsDto dto = new SettingsDto();
			SettingsEntity settings = this.settingsRepo.findByUser_Username(p_user.getUsername());
			dto.setLocaleCode(settings.getLocaleCode());
			dto.setLocaleIdentifier(settings.getLocaleIdentifier());
			dto.setLocaleIcon(settings.getLocaleIcon());
			dto.setTheme(settings.getTheme());
			return dto;
		} else
			throw new SystemErrorException(ErrorCode.ERR_SYS0404);
	}

}
