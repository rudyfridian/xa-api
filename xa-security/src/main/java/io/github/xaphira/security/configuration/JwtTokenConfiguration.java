package io.github.xaphira.security.configuration;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
public class JwtTokenConfiguration {
	
    @Value("${security.oauth2.resource.jwt.key-value}")
    private String jwtKey;
    
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        final JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter() {
			@Override
			public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
				OAuth2AccessToken newAccessToken = super.enhance(accessToken, authentication);
				return newAccessToken;
			}

			@Override
			public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
				OAuth2Authentication outh = super.extractAuthentication(map);
				outh.setDetails(map);
				return outh;
			}        
			
			
        };
        jwtAccessTokenConverter.setSigningKey(jwtKey);
        return jwtAccessTokenConverter;
    }
}
