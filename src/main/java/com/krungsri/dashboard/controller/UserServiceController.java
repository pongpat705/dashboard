package com.krungsri.dashboard.controller;

import javax.servlet.http.HttpServletRequest;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiAuthNone;
import org.jsondoc.core.annotation.ApiVersion;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVisibility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.krungsri.dashboard.entity.Users;
import com.krungsri.dashboard.service.UserService;

@Controller
@RequestMapping(value = "/service")
@Api(name = "users services", description = "api for users", group = "user", visibility = ApiVisibility.PUBLIC, stage = ApiStage.RC)
@ApiVersion(since = "1.0", until = "1.1")
@ApiAuthNone
public class UserServiceController {
	
	  private final Logger log = LoggerFactory.getLogger(this.getClass());	
	  
	  @Autowired UserService userService;
	  
	  @RequestMapping(value = "/parse", method = RequestMethod.GET)
	  @ResponseBody
	  public Users create(HttpServletRequest request) throws Exception {
		  String token = request.getHeader("maoz-token");
		  log.info("parsing token : "+token);
	    try {
	    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    	log.info("user : "+auth.getName());
	    	Users user = userService.getUser(auth.getName());
		    return user;
	    }
	    catch (Exception ex) {
	      throw new Exception("Error parsing the token: " + ex.toString());
	    }
	  }
}
