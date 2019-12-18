package io.github.xaphira.security.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.github.xaphira.common.aspect.ResponseSuccess;
import io.github.xaphira.common.exceptions.BaseControllerException;
import io.github.xaphira.common.http.ApiBaseResponse;
import io.github.xaphira.common.utils.SuccessCode;
import io.github.xaphira.feign.dto.security.ChangePasswordDto;
import io.github.xaphira.security.entity.UserEntity;
import io.github.xaphira.security.service.ChangePasswordImplService;

@RestController
@RequestMapping("/api/master")
public class ChangePasswordController extends BaseControllerException {

	@Autowired
	private ChangePasswordImplService changePasswordService;
	
	@ResponseSuccess(SuccessCode.OK_SCR001)
	@RequestMapping(value = "/trx/auth/change-password/v.1", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiBaseResponse> putChangePassword(Authentication authentication,
			@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) String locale,
			@RequestBody(required = true) ChangePasswordDto p_dto) throws Exception {
		UserEntity user = (UserEntity) authentication.getPrincipal();
		return new ResponseEntity<ApiBaseResponse>(changePasswordService.doChangePassword(p_dto, user, locale), HttpStatus.OK);
	}
	
}
