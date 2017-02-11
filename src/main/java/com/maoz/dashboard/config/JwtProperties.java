package com.maoz.dashboard.config;

import org.springframework.stereotype.Component;

@Component
public class JwtProperties {
	
	private final long jwtExpirationTime = 1000*60*5;
	
	private final String jwtSecret = "MaozcaptionitSuperSecret";
	
	private final String jwtSchema = "Bearer";
	
	private final String jwtHeader = "maoz-token";
	
	public long getJwtExpirationTime() {
		return jwtExpirationTime;
	}

	public String getJwtSecret() {
		return jwtSecret;
	}

	public String getJwtSchema() {
		return jwtSchema;
	}

	public String getJwtHeader() {
		return jwtHeader;
	}
}

