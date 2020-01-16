package io.github.xaphira.master.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.github.xaphira.common.exceptions.BaseControllerException;
import io.github.xaphira.feign.dto.common.FilterDto;
import io.github.xaphira.feign.dto.select.SelectResponseDto;
import io.github.xaphira.master.service.LocaleImplService;

@RestController
@RequestMapping("/api/master")
public class LocaleController extends BaseControllerException {

	@Autowired
	private LocaleImplService localeService;

	@RequestMapping(value = "/vw/post/select/locale/v.1", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SelectResponseDto> getSelectLocale(Authentication authentication,
			@RequestBody(required = true) FilterDto filter) throws Exception {
		return new ResponseEntity<SelectResponseDto>(localeService.getSelectLocale(filter), HttpStatus.OK);
	}

	@RequestMapping(value = "/vw/get/select/all/locale/v.1", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SelectResponseDto> getSelectAllLocale(Authentication authentication) throws Exception {
		return new ResponseEntity<SelectResponseDto>(localeService.getSelectAllLocale(), HttpStatus.OK);
	}
	
}
