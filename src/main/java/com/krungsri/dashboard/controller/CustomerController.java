package com.krungsri.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.krungsri.dashboard.entity.Customer;
import com.krungsri.dashboard.repository.CustomerRepository;

@Controller
public class CustomerController {
	
	  @Autowired
	  CustomerRepository customerRepo;
	
	  
	  @RequestMapping("/create")
	  @ResponseBody
	  public String create(String firstName, String lastName) {
		  String customerId = "";
	    try {
	      Customer customer = new Customer();
	      customer.setFirstName(firstName);
	      customer.setLastName(lastName);
	      customerRepo.save(customer);
	      customerId = String.valueOf(customer.getId());
	    }
	    catch (Exception ex) {
	      return "Error creating the customer: " + ex.toString();
	    }
	    return "customer succesfully created with id = " + customerId;
	  }
	  
	  /**
	   * GET /delete  --> Delete the user having the passed id.
	   */
/*	  @RequestMapping("/delete")
	  @ResponseBody
	  public String delete(long id) {
	    try {
	      User user = new User(id);
	      userDao.delete(user);
	    }
	    catch (Exception ex) {
	      return "Error deleting the user:" + ex.toString();
	    }
	    return "User succesfully deleted!";
	  }
	  
	  *//**
	   * GET /get-by-email  --> Return the id for the user having the passed
	   * email.
	   *//*
	  @RequestMapping("/get-by-email")
	  @ResponseBody
	  public String getByEmail(String email) {
	    String userId = "";
	    try {
	      User user = userDao.findByEmail(email);
	      userId = String.valueOf(user.getId());
	    }
	    catch (Exception ex) {
	      return "User not found";
	    }
	    return "The user id is: " + userId;
	  }
	  
	  *//**
	   * GET /update  --> Update the email and the name for the user in the 
	   * database having the passed id.
	   *//*
	  @RequestMapping("/update")
	  @ResponseBody
	  public String updateUser(long id, String email, String name) {
	    try {
	      User user = userDao.findOne(id);
	      user.setEmail(email);
	      user.setName(name);
	      userDao.save(user);
	    }
	    catch (Exception ex) {
	      return "Error updating the user: " + ex.toString();
	    }
	    return "User succesfully updated!";
	  }
*/
}
