package io.github.xaphira.master.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.xaphira.feign.dto.select.SelectResponseDto;
import io.github.xaphira.master.dao.CountryRepo;

@Service("countryService")
public class CountryImplService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CountryRepo countryRepo;

	public SelectResponseDto getSelectCountry(Integer offset, Integer limit, String keyword, String order, String sortBy) throws Exception {
		return null;
	}

}
