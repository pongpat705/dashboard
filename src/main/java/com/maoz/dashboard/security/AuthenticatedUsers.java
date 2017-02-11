package com.maoz.dashboard.security;

import java.util.List;

import org.springframework.security.core.Authentication;

public class AuthenticatedUsers implements Authentication{
	
	private String name;
	private String credentials;
	private List<Authorities> authorities;
	private boolean authenticated = true;
	private String details;
	private String principal;

	public AuthenticatedUsers() {
		super();
	}

	public AuthenticatedUsers(String name, String credentials, List<Authorities> authorities, boolean authenticated) {
		this.name = name;
		this.credentials = credentials;
		this.authorities = authorities;
		this.authenticated = authenticated;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public List<Authorities> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return this.credentials;
	}

	@Override
	public Object getDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAuthenticated() {
		// TODO Auto-generated method stub
		return this.authenticated;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		this.authenticated = isAuthenticated;
		
	}

}
