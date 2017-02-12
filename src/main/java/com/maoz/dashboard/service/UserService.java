package com.maoz.dashboard.service;

import com.maoz.dashboard.entity.JDBCUser;

public interface UserService {

	public String userToString(String userName);
	
	public JDBCUser stringToUsers(String usersString);
	
	public JDBCUser getUser(String userName);
}
