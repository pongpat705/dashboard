package com.krungsri.dashboard.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

@Entity
@Table(name = "CUSTOMER")
@ApiObject
public class Customer {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@ApiObjectField
    private Long id;
	@ApiObjectField(description = "first name of customer")
    private String firstName;
	@ApiObjectField(description = "last name of customer")
    private String lastName;
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("Customer[id=%d, firstName='%s', lastName='%s']", id, firstName, lastName);
	}
	
    
    
}
