package io.github.xaphira.feign.configuration;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class BearerRequestInterceptor implements RequestInterceptor {

	@Override
    public void apply(RequestTemplate requestTemplate) {
		OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        requestTemplate.header("Authorization",
				String.format("%s %s",
				(details.getTokenType()!=null?details.getTokenType():""),
				(details.getTokenValue()!=null?details.getTokenValue():"")));
    }

}