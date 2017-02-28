package com.maoz.dashboard.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.maoz.dashboard.security.JWTAuthenticationFilter;
import com.maoz.dashboard.security.JWTLoginFilter;
import com.maoz.dashboard.service.MaoUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	MaoUserDetailService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/fonts/**").permitAll()
			.antMatchers("/images/**").permitAll()
			.antMatchers("/scripts/**").permitAll()
			.antMatchers("/styles/**").permitAll()
			.antMatchers("/vendor/**").permitAll()
			.antMatchers("/views/**").permitAll()
			.antMatchers("/api/browser/**").permitAll()
			.antMatchers("/jsondoc").permitAll()
			.antMatchers("/jsondoc-ui.html/**").permitAll()
			.antMatchers("/webjars/**").permitAll()
			.antMatchers(HttpMethod.POST, "/login").permitAll()
			.antMatchers("/service/xxx").hasRole("ADMIN")
			.antMatchers("/api/**").authenticated()
			.anyRequest().authenticated()
			.and()
			.addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
			;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}

}
