package com.maoz.dashboard.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import com.maoz.dashboard.entity.Station;

@Configuration
public class RepositoryConfiguration extends RepositoryRestConfigurerAdapter{

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Station.class);
	}

}
