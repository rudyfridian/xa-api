package io.github.xaphira.master.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import io.github.xaphira.common.service.CommonService;
import io.github.xaphira.feign.dto.common.FilterDto;
import io.github.xaphira.feign.dto.select.SelectDto;
import io.github.xaphira.feign.dto.select.SelectResponseDto;
import io.github.xaphira.master.dao.CountryRepo;
import io.github.xaphira.master.dao.specification.CountrySpecification;
import io.github.xaphira.master.entity.CountryEntity;

@Service("countryService")
public class CountryImplService extends CommonService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CountryRepo countryRepo;

	public SelectResponseDto getSelectCountry(FilterDto filter) throws Exception {
		Page<CountryEntity> country = countryRepo.findAll(CountrySpecification.getSelect(filter.getKeyword()), page(filter.getOrder(), filter.getOffset(), filter.getLimit()));
		SelectResponseDto response = new SelectResponseDto();
		response.setTotalFiltered(new Long(country.getContent().size()));
		response.setTotalRecord(countryRepo.count(CountrySpecification.getSelect(filter.getKeyword())));
		country.getContent().forEach(value -> {
			response.getData().add(new SelectDto(value.getCountryName(), value.getCountryCode(), !value.isActive(), null));
		});
		return response;
	}

}
