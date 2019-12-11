package io.github.xaphira.security.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.xaphira.common.exceptions.SystemErrorException;
import io.github.xaphira.common.http.ApiBaseResponse;
import io.github.xaphira.common.security.AESEncrypt;
import io.github.xaphira.common.utils.ErrorCode;
import io.github.xaphira.security.dao.UserRepo;
import io.github.xaphira.security.entity.UserEntity;

@Service("deactivatedAccountService")
public class DeactivatedAccountImplService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Value("${xa.signature.aes.secret-key}")
	private String secretKey;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public ApiBaseResponse doDeactivate(Map<String, String> dto, UserEntity p_user, String p_locale) throws Exception {
		if (p_user.getUsername() != null && dto != null) {
			p_user = this.userRepo.findByUsername(p_user.getUsername());
			String password = AESEncrypt.decrypt(this.secretKey, dto.get("password"));
			if (this.passwordEncoder.matches(password, p_user.getPassword())) {
				p_user.setActive(false);
				p_user.setEnabled(false);
				userRepo.save(p_user);
				return null;
			} else {
				throw new SystemErrorException(ErrorCode.ERR_SCR0002);
			}			
		} else
			throw new SystemErrorException(ErrorCode.ERR_SYS0404);
	}

}
