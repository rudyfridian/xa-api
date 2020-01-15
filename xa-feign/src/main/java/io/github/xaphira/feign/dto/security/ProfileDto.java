package io.github.xaphira.feign.dto.security;

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
public class ProfileDto extends BaseAuditDto {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1742415621743889509L;
	private String username;
	private String name;
	private String email;
	private String address;
	private String city;
	private String province;
	private String district;
	private String subDistrict;
	private String phoneNumber;
	private String mobileNumber;
	private String image;
	private String description;

}