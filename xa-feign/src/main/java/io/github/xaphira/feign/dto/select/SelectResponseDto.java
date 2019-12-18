package io.github.xaphira.feign.dto.select;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SelectResponseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8158078360152299884L;
	private Long totalFiltered;
	private Long totalRecord;
	private List<SelectDto> data = new ArrayList<SelectDto>();

}
