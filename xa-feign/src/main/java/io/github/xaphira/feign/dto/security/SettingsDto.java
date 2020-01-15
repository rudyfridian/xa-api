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
public class SettingsDto extends BaseAuditDto {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2624389791249022903L;
	private String locale;
    private String theme;

}
