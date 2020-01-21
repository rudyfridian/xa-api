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
import io.github.xaphira.master.dao.DistrictRepo;
import io.github.xaphira.master.dao.specification.DistrictSpecification;
import io.github.xaphira.master.entity.DistrictEntity;

@Service("districtService")
public class DistrictImplService extends CommonService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DistrictRepo districtRepo;

	public SelectResponseDto getSelectDistrict(FilterDto filter) throws Exception {
		Page<DistrictEntity> district = districtRepo.findAll(DistrictSpecification.getSelect(filter.getKeyword()), page(filter.getOrder(), filter.getOffset(), filter.getLimit()));
		SelectResponseDto response = new SelectResponseDto();
		response.setTotalFiltered(new Long(district.getContent().size()));
		response.setTotalRecord(districtRepo.count(DistrictSpecification.getSelect(filter.getKeyword())));
		district.getContent().forEach(value -> {
			response.getData().add(new SelectDto(value.getDistrictName(), value.getDistrictCode(), !value.isActive(), null));
		});
		return response;
	}

}
