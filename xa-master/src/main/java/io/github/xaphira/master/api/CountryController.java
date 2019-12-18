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
import io.github.xaphira.master.service.CountryImplService;

@RestController
@RequestMapping("/api/master")
public class CountryController extends BaseControllerException {

	@Autowired
	private CountryImplService countryService;

	@RequestMapping(value = "/vw/post/select/country/v.1", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SelectResponseDto> getSelectCountry(Authentication authentication,
			@RequestBody(required = true) FilterDto filter) throws Exception {
		return new ResponseEntity<SelectResponseDto>(countryService.getSelectCountry(filter), HttpStatus.OK);
	}
	
}
