package com.maoz.dashboard.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.maoz.dashboard.entity.JDBCUser;

@Transactional
public interface JDBCUserRepository extends CrudRepository<JDBCUser, Long> {
	
	JDBCUser findByUserName(@Param(value = "userName") String userName);
	
	JDBCUser findByUserNameAndPassword(@Param(value = "userName") String userName, @Param(value = "password") String password);
	
}
