package com.krungsri.dashboard.service;

import com.krungsri.dashboard.entity.Users;

public interface UserService {

	public String userToString(String userName);
	
	public Users stringToUsers(String usersString);
	
	public Users getUser(String userName);
}
