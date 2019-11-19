package io.github.xaphira.master.configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import io.github.xaphira.common.utils.ResourceCode;

public class ResourceServerMasterAdapter extends ResourceServerConfigurerAdapter {

    private TokenStore tokenStore;
    
    private String resourceId = ResourceCode.MASTER.getResourceId();
    
    public ResourceServerMasterAdapter() {}
    public ResourceServerMasterAdapter(TokenStore tokenStore) {
		this.tokenStore = tokenStore;
	}

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        // @formatter:off
        resources.resourceId(resourceId).tokenStore(tokenStore);
        // @formatter:on
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .csrf().disable()
        .requestMatchers()
    		.antMatchers("/api/"				+resourceId+ "/**").and()
        .authorizeRequests()
        .antMatchers(HttpMethod.GET,"/api/" 	+resourceId+ "/vw/get/**")
    		.access("#oauth2.hasScope('read')")
        .antMatchers(HttpMethod.GET,"/api/" 	+resourceId+ "/vw/param/**")
    		.access("#oauth2.hasScope('read')")
        .antMatchers(HttpMethod.POST,"/api/"	+resourceId+ "/vw/post/**")
        	.access("#oauth2.hasScope('read')")
        .antMatchers(HttpMethod.POST,"/api/"	+resourceId+ "/vw/param/**")
        	.access("#oauth2.hasScope('read')")
        .antMatchers(HttpMethod.POST,"/api/"	+resourceId+ "/trx/add/**")
        	.access("#oauth2.hasScope('write')")
        .antMatchers(HttpMethod.POST,"/api/"	+resourceId+ "/trx/post/**")
        	.access("#oauth2.hasScope('write')")
        .antMatchers(HttpMethod.PUT,"/api/"		+resourceId+ "/trx/put/**")
        	.access("#oauth2.hasScope('write')")
        .antMatchers(HttpMethod.GET,"/api/" 	+resourceId+ "/vw/auth/**")
        	.access("#oauth2.hasScope('trust') and not(hasRole('END'))")
        .antMatchers(HttpMethod.POST,"/api/"	+resourceId+ "/vw/auth/**")
        	.access("#oauth2.hasScope('trust') and not(hasRole('END'))")
        .antMatchers(HttpMethod.POST,"/api/"	+resourceId+ "/trx/auth/**")
        	.access("#oauth2.hasScope('trust') and not(hasRole('END'))")
        .antMatchers(HttpMethod.DELETE,"/api/"	+resourceId+ "/trx/auth/**")
        	.access("#oauth2.hasScope('trust') and not(hasRole('END'))")
        .anyRequest().denyAll();
        // @formatter:on
    }
}
