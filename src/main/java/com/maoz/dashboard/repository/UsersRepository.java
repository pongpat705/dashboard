package com.maoz.dashboard.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.maoz.dashboard.entity.Users;

@Transactional
public interface UsersRepository extends CrudRepository<Users, Long> {
	
	Users findByUserName(String userName);
	
	Users findByUserNameAndPassword(String userName, String password);
	
}
