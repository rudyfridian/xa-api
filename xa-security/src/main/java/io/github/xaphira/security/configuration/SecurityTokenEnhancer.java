package io.github.xaphira.security.configuration;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import io.github.xaphira.common.utils.DateUtil;
import io.github.xaphira.security.entity.UserEntity;
import io.github.xaphira.security.service.MenuImplService;

public class SecurityTokenEnhancer implements TokenEnhancer {
	
	@Value("${xa.client-id.web}")
	private String clientIdWeb;
	
	@Autowired
	@Qualifier("menuService")
	private MenuImplService menuService;
	
	@Value("${xa.signature.public-key}")
	private String publicKey;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		if (authentication.getPrincipal() instanceof UserEntity) {
	        UserEntity user = (UserEntity) authentication.getPrincipal();
	        Map<String, Object> additionalInfo = new TreeMap<String, Object>();	
			if(authentication.getOAuth2Request().getClientId().equals(clientIdWeb) && user.getRaw() == null) {
				try {
					additionalInfo.put("menus", menuService.loadMenuByRole(user.getAuthorityDefault(), user.getLocale()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	        additionalInfo.put("authority", user.getAuthorityDefault());
	        additionalInfo.put("email", user.getEmail());
	        additionalInfo.put("name", user.getName());
	        additionalInfo.put("locale", user.getLocale());
	        additionalInfo.put("server_date", DateUtil.DATE_NOW);
	        additionalInfo.put("xrkey", publicKey);
	        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
		}
        return accessToken;
	}

}
