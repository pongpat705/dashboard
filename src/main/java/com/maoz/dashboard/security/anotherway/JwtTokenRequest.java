package com.maoz.dashboard.security.anotherway;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

@ApiObject(description="using for get token")
public class JwtTokenRequest {
	@ApiObjectField
	private String username;
	@ApiObjectField
    private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
