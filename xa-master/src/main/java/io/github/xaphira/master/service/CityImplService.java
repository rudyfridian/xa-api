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
import io.github.xaphira.master.dao.CityRepo;
import io.github.xaphira.master.dao.specification.CitySpecification;
import io.github.xaphira.master.entity.CityEntity;

@Service("cityService")
public class CityImplService extends CommonService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CityRepo cityRepo;

	public SelectResponseDto getSelectCity(FilterDto filter) throws Exception {
		Page<CityEntity> city = cityRepo.findAll(CitySpecification.getSelect(filter.getKeyword()), page(filter.getOrder(), filter.getOffset(), filter.getLimit()));
		SelectResponseDto response = new SelectResponseDto();
		response.setTotalFiltered(new Long(city.getContent().size()));
		response.setTotalRecord(cityRepo.count(CitySpecification.getSelect(filter.getKeyword())));
		city.getContent().forEach(value -> {
			response.getData().add(new SelectDto(value.getCityName(), value.getCityCode(), !value.isActive()));
		});
		return response;
	}

}
