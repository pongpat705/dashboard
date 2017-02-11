package com.maoz.dashboard.security;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails{

	private String userName;
	private String password;
	private String token;
	private List<Authorities> authorities;
	
	public CustomUserDetails(String userName, String password, String token,  List<Authorities> authorities) {
		super();
		this.userName = userName;
		this.password = password;
		this.token = token;
		this.authorities = authorities;
	}

	@Override
	public List<Authorities> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.userName;
	}

	public String getToken() {
		return token;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
