package io.github.xaphira.feign.dto.security;

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
@EqualsAndHashCode(exclude={"children"})
@ToString(exclude={"children"})
public class MenuDto implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2624389791249022903L;
	private String title;
    private String icon;
    private String link;
    private String access;
    private Boolean home = false;
    private Boolean group = false;
    private List<MenuDto> children = new ArrayList<MenuDto>();

}
