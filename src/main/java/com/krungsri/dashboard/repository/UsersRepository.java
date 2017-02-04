package com.krungsri.dashboard.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.krungsri.dashboard.entity.Users;

@Transactional
public interface UsersRepository extends CrudRepository<Users, Long> {
	
	Users findByUserName(String userName);
	
}
