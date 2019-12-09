package io.github.xaphira.security.api;

import java.util.Map;

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
import io.github.xaphira.security.entity.UserEntity;
import io.github.xaphira.security.service.DeactivatedAccountImplService;

@RestController
@RequestMapping("/api/profile")
public class DeactivatedAccountController extends BaseControllerException {

	@Autowired
	private DeactivatedAccountImplService deactivatedAccountService;
	
	@ResponseSuccess(SuccessCode.OK_SCR003)
	@RequestMapping(value = "/trx/auth/deactivated/v.1", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiBaseResponse> putSettings(Authentication authentication,
			@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) String locale,
			@RequestBody(required = true) Map<String, String> dto) throws Exception {
		UserEntity user = (UserEntity) authentication.getPrincipal();
		return new ResponseEntity<ApiBaseResponse>(deactivatedAccountService.doDeactivate(dto, user, locale), HttpStatus.OK);
	}
	
}
