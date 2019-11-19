package io.github.xaphira.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.github.xaphira.feign.dto.security.MenuDto;
import io.github.xaphira.security.dao.MenuRepo;
import io.github.xaphira.security.entity.MenuEntity;

@Service("menuService")
public class MenuImplService {

	protected final Log LOGGER = LogFactory.getLog(getClass());

	@Autowired
	@Qualifier("menuRepo")
	private MenuRepo menuRepo;
	
	@Value("${xa.locale}")
	private String locale;
	
	public List<MenuDto> loadMenuByRole(String role) throws Exception {
		return constructMenu(this.menuRepo.loadAllMenuByRole(role), false);
	}
	
	public List<MenuDto> loadMenuByRole(String role, Locale locale) throws Exception {
		return loadMenuByRole(role, locale.toLanguageTag());
	}
	
	public List<MenuDto> loadMenuByRole(String role, String locale) throws Exception {
		if(locale == null)
			return loadMenuByRole(role);
		try {
			locale = locale.split(",")[0];	
		} catch (Exception e) {
			return loadMenuByRole(role);
		}
		if(locale.equals(this.locale))
			return loadMenuByRole(role);
		return constructMenu(this.menuRepo.loadAllMenuByRoleI18n(role, locale), true);
	}
	
	private List<MenuDto> constructMenu(List<MenuEntity> menus, boolean i18n) {
		List<MenuDto> menuDtos = new ArrayList<MenuDto>();
		menus.forEach(menu->{
			if(menu.getLevel() == 0) {
				if(i18n)
					menuDtos.add(menu.toObjectI18n());
				else
					menuDtos.add(menu.toObject());
			}			
		});
		return menuDtos;
	}
	
}