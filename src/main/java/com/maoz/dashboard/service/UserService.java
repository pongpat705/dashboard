package com.maoz.dashboard.service;

import com.maoz.dashboard.entity.Users;

public interface UserService {

	public String userToString(String userName);
	
	public Users stringToUsers(String usersString);
	
	public Users getUser(String userName);
}
