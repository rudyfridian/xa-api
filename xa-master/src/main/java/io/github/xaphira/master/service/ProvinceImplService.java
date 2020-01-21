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
import io.github.xaphira.master.dao.ProvinceRepo;
import io.github.xaphira.master.dao.specification.ProvinceSpecification;
import io.github.xaphira.master.entity.ProvinceEntity;

@Service("provinceService")
public class ProvinceImplService extends CommonService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProvinceRepo provinceRepo;

	public SelectResponseDto getSelectProvince(FilterDto filter) throws Exception {
		Page<ProvinceEntity> province = provinceRepo.findAll(ProvinceSpecification.getSelect(filter.getKeyword()), page(filter.getOrder(), filter.getOffset(), filter.getLimit()));
		SelectResponseDto response = new SelectResponseDto();
		response.setTotalFiltered(new Long(province.getContent().size()));
		response.setTotalRecord(provinceRepo.count(ProvinceSpecification.getSelect(filter.getKeyword())));
		province.getContent().forEach(value -> {
			response.getData().add(new SelectDto(value.getProvinceName(), value.getProvinceCode(), !value.isActive(), null));
		});
		return response;
	}

}
