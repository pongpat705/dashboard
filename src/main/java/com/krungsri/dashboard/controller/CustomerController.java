package com.krungsri.dashboard.controller;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiAuthNone;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.annotation.ApiQueryParam;
import org.jsondoc.core.annotation.ApiResponseObject;
import org.jsondoc.core.annotation.ApiVersion;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVisibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.krungsri.dashboard.entity.Customer;
import com.krungsri.dashboard.repository.CustomerRepository;

@Controller
@RequestMapping(value = "/customer")
@Api(name = "Customer services", description = "Methods for managing customer", group = "Sales", visibility = ApiVisibility.PUBLIC, stage = ApiStage.RC)
@ApiVersion(since = "1.0", until = "2.12")
@ApiAuthNone
public class CustomerController {
	
	  @Autowired
	  CustomerRepository customerRepo;
	
	  
	  @RequestMapping(value = "/create", method = RequestMethod.GET)
	  @ResponseBody
	  public String create(	@ApiQueryParam(description = "name of customer", name="firstName") @RequestParam(value="firstName") String firstName, 
			  				@ApiQueryParam(description = "lastName of customer", name="lastName") @RequestParam(value="lastName") String lastName) {
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
