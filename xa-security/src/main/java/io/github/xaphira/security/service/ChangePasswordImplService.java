package io.github.xaphira.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.xaphira.common.exceptions.SystemErrorException;
import io.github.xaphira.common.http.ApiBaseResponse;
import io.github.xaphira.common.security.AESEncrypt;
import io.github.xaphira.common.utils.ErrorCode;
import io.github.xaphira.feign.dto.security.ChangePasswordDto;
import io.github.xaphira.security.dao.UserRepo;
import io.github.xaphira.security.entity.UserEntity;

@Service("changePasswordService")
public class ChangePasswordImplService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Value("${xa.signature.aes.secret-key}")
	private String secretKey;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public ApiBaseResponse doChangePassword(ChangePasswordDto p_dto, UserEntity p_user, String p_locale) throws Exception {
		String password = AESEncrypt.decrypt(this.secretKey, p_dto.getPassword());
		String newPassword = AESEncrypt.decrypt(this.secretKey, p_dto.getPassword());
		String confirmPassword = AESEncrypt.decrypt(this.secretKey, p_dto.getPassword());
		if (this.passwordEncoder.matches((String)password, p_user.getPassword())) {
			if (newPassword.equals(confirmPassword)) {
				p_user.setPassword(this.passwordEncoder.encode(newPassword));
				userRepo.save(p_user);
				return null;
			} else {
				throw new SystemErrorException(ErrorCode.ERR_SCR0003);
			}
		} else {
			throw new SystemErrorException(ErrorCode.ERR_SCR0002);
		}
	}

}
