package com.krungsri.dashboard.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class Users {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	private String userName;
	
	private String password;
	
	private String enabled;
	
	@OneToMany(mappedBy="users", fetch=FetchType.EAGER)
	private Set<UsersRole> usersRoles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public Set<UsersRole> getUsersRoles() {
		return usersRoles;
	}

	public void setUsersRoles(Set<UsersRole> usersRoles) {
		this.usersRoles = usersRoles;
	}
	
}
