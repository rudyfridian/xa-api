package io.github.xaphira.security.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import io.github.xaphira.security.service.UserImplService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserImplService userDetailsService;

    @Autowired
    private AccountAuthenticationProvider accountAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.eraseCredentials(false);
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        auth.authenticationProvider(accountAuthenticationProvider);
    }

	@Bean(name = "passwordEncoder")
    public PasswordEncoder passwordEncoder() {
    	Map<String, PasswordEncoder> encoders = new HashMap<String, PasswordEncoder>();
    	encoders.put("bcrypt", new BCryptPasswordEncoder(13));
    	encoders.put("noop", NoOpPasswordEncoder.getInstance());
    	encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
    	encoders.put("scrypt", new SCryptPasswordEncoder());
    	encoders.put("sha256", new StandardPasswordEncoder());
    	DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("bcrypt", encoders);
    	passwordEncoder.setDefaultPasswordEncoderForMatches(new BCryptPasswordEncoder(13));
    	return passwordEncoder;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
