package io.github.xaphira.master.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.xaphira.common.exceptions.BaseControllerException;
import io.github.xaphira.feign.dto.select.SelectResponseDto;
import io.github.xaphira.master.service.CountryImplService;

@RestController
@RequestMapping("/api/master")
public class CountryController extends BaseControllerException {

	@Autowired
	private CountryImplService countryService;

	@RequestMapping(value = "/vw/param/select/country/v.1", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SelectResponseDto> getSelectCountry(Authentication authentication,
			@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) String locale,
			@RequestParam(name = "offset") Integer offset,
			@RequestParam(name = "limit", required = true) Integer limit,
			@RequestParam(name = "keyword") String keyword,
			@RequestParam(name = "order") String order,
			@RequestParam(name = "sortBy") String sortBy) throws Exception {
		return new ResponseEntity<SelectResponseDto>(countryService.getSelectCountry(offset, limit, keyword, order, sortBy), HttpStatus.OK);
	}
	
}
