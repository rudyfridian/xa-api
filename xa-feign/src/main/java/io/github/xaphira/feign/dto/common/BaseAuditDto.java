package io.github.xaphira.feign.dto.common;

import java.io.Serializable;
import java.util.Date;

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
public class BaseAuditDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1183080114772374130L;
	protected int version;
	protected String active;
	protected Date createdDate;
	protected String createdBy;
	protected Date modifiedDate;
	protected String modifiedBy;

	public String isActive(boolean active) {
		if(active) return "Active";
		return "Deactivated";
	}

}
