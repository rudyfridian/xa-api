package io.github.xaphira.feign.dto.master;

import io.github.xaphira.feign.dto.common.BaseAuditDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class CityDto extends BaseAuditDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8125190677227153892L;
	private String cityCode;
	private String cityName;
	private String provinceName;

}