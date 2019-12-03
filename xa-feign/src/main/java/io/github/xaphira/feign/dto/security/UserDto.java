package io.github.xaphira.feign.dto.security;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {
	
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
	private String districtCode;
	private String phoneNumber;
	private String mobileNumber;
	private String image;
	private String description;

}