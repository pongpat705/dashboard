package com.maoz.dashboard.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "USERS_ROLE")
public class JDBCUserRole {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	private String role;
	
	private String enabled;
	
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private JDBCUser users;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public JDBCUser getUsers() {
		return users;
	}

	public void setUsers(JDBCUser users) {
		this.users = users;
	}

}
