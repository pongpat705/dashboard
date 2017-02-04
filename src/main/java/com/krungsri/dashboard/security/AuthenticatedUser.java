package com.krungsri.dashboard.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.krungsri.dashboard.entity.Users;

public class AuthenticatedUser implements Authentication{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private boolean authenticated = true;
	private String token;
	    
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}
	
    AuthenticatedUser(String name, String token){
        this.name = name;
        this.token = token;
    } 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getCredentials() {
		return this.token;
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
	public void setAuthenticated(boolean b) throws IllegalArgumentException {
		this.authenticated = b;
	}
	
}
