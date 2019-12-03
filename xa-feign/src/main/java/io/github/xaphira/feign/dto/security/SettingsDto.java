package io.github.xaphira.feign.dto.security;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SettingsDto implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2624389791249022903L;
	private String locale;
    private String theme;

}
