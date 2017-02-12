package com.maoz.dashboard.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.maoz.dashboard.entity.JDBCUserRole;

@Transactional
public interface JDBCUserRoleRepository extends CrudRepository<JDBCUserRole, Long> {

}
