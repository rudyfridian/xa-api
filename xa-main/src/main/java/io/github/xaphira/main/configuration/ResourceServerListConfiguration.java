package io.github.xaphira.main.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import io.github.xaphira.file.configuration.ResourceServerFileAdapter;
import io.github.xaphira.general.configuration.ResourceServerGeneralAdapter;
import io.github.xaphira.master.configuration.ResourceServerMasterAdapter;
import io.github.xaphira.notification.configuration.ResourceServerNotificationAdapter;
import io.github.xaphira.security.configuration.ResourceServerProfileAdapter;
import io.github.xaphira.security.configuration.ResourceServerSecurityAdapter;

@Configuration
public class ResourceServerListConfiguration {
	
    @Autowired
    private TokenStore tokenStore;

	@Bean
	protected ResourceServerConfiguration securityResources() {
		ResourceServerConfiguration resource = new ResourceServerConfiguration() {
			public void setConfigurers(List<ResourceServerConfigurer> configurers) {
				super.setConfigurers(configurers);
			}
		};
		resource.setConfigurers(Arrays.<ResourceServerConfigurer> asList(new ResourceServerSecurityAdapter(tokenStore)));
		resource.setOrder(-13);
		return resource;
	}

	@Bean
	protected ResourceServerConfiguration profileResources() {
		ResourceServerConfiguration resource = new ResourceServerConfiguration() {
			public void setConfigurers(List<ResourceServerConfigurer> configurers) {
				super.setConfigurers(configurers);
			}
		};
		resource.setConfigurers(Arrays.<ResourceServerConfigurer> asList(new ResourceServerProfileAdapter(tokenStore)));
		resource.setOrder(-14);
		return resource;
	}

	@Bean
	protected ResourceServerConfiguration masterResources() {
		ResourceServerConfiguration resource = new ResourceServerConfiguration() {
			public void setConfigurers(List<ResourceServerConfigurer> configurers) {
				super.setConfigurers(configurers);
			}
		};
		resource.setConfigurers(Arrays.<ResourceServerConfigurer> asList(new ResourceServerMasterAdapter(tokenStore)));
		resource.setOrder(-15);
		return resource;
	}

	@Bean
	protected ResourceServerConfiguration notificationResources() {
		ResourceServerConfiguration resource = new ResourceServerConfiguration() {
			public void setConfigurers(List<ResourceServerConfigurer> configurers) {
				super.setConfigurers(configurers);
			}
		};
		resource.setConfigurers(Arrays.<ResourceServerConfigurer> asList(new ResourceServerNotificationAdapter(tokenStore)));
		resource.setOrder(-16);
		return resource;
	}

	@Bean
	protected ResourceServerConfiguration generalResources() {
		ResourceServerConfiguration resource = new ResourceServerConfiguration() {
			public void setConfigurers(List<ResourceServerConfigurer> configurers) {
				super.setConfigurers(configurers);
			}
		};
		resource.setConfigurers(Arrays.<ResourceServerConfigurer> asList(new ResourceServerGeneralAdapter(tokenStore)));
		resource.setOrder(-17);
		return resource;
	}

	@Bean
	protected ResourceServerConfiguration fileResources() {
		ResourceServerConfiguration resource = new ResourceServerConfiguration() {
			public void setConfigurers(List<ResourceServerConfigurer> configurers) {
				super.setConfigurers(configurers);
			}
		};
		resource.setConfigurers(Arrays.<ResourceServerConfigurer> asList(new ResourceServerFileAdapter(tokenStore)));
		resource.setOrder(-18);
		return resource;
	}
	
}
