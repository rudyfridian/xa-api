package io.github.xaphira.security.configuration;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import io.github.xaphira.security.service.JdbcOauth2ClientDetailsService;
import io.github.xaphira.security.service.JdbcOauth2TokenStore;
import io.github.xaphira.security.service.UserImplService;

@SuppressWarnings("deprecation")
@Configuration
@EnableAuthorizationServer
public class OAuth2ServerConfiguration extends AuthorizationServerConfigurerAdapter{

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private UserImplService userDetailsService;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {		
		super.configure(security);
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()").passwordEncoder(NoOpPasswordEncoder.getInstance());
	}

	@Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // @formatter:off
	    TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
	    tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), jwtAccessTokenConverter));
		
        endpoints
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .tokenEnhancer(tokenEnhancerChain)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .tokenStore(tokenStore())
                .setClientDetailsService(jdbcClientDetailService());;
        // @formatter:on
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // @formatter:off
    	clients.withClientDetails(jdbcClientDetailService());
        // @formatter:on
    }
    
    @Bean
    public TokenStore tokenStore() {
        return new JdbcOauth2TokenStore(dataSource);
    }
    
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new SecurityTokenEnhancer();
    }
    
    @Bean
    public JdbcClientDetailsService jdbcClientDetailService() {
    	return new JdbcOauth2ClientDetailsService(dataSource);
    }
}
