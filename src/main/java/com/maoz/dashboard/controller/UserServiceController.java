package com.maoz.dashboard.controller;

import javax.servlet.http.HttpServletRequest;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiAuthNone;
import org.jsondoc.core.annotation.ApiAuthToken;
import org.jsondoc.core.annotation.ApiVersion;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVisibility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maoz.dashboard.security.AuthenticatedUsers;
import com.maoz.dashboard.security.TokenAuthenticationService;
import com.maoz.dashboard.service.MaoUserDetailService;
import com.maoz.dashboard.service.UserService;

@Controller
@RequestMapping(value = "/service")
@Api(name = "users services", description = "api for users", group = "user", visibility = ApiVisibility.PUBLIC, stage = ApiStage.RC)
@ApiVersion(since = "1.0", until = "1.1")
@ApiAuthNone
public class UserServiceController {
	
	  private final Logger log = LoggerFactory.getLogger(this.getClass());	
	  
	  @Autowired UserService userService;
	  
	  @Autowired MaoUserDetailService userDetailsService;
	  
	  @Autowired TokenAuthenticationService tokenService;
	  
	  @ApiAuthToken(scheme="Bearer")
	  @RequestMapping(value = "/parse", method = RequestMethod.GET)
	  @ResponseBody
	  public AuthenticatedUsers parse(HttpServletRequest request) throws Exception {
		  String token = request.getHeader("maoz-token");
		  log.info("parsing token : "+token);
	    try {
	    	AuthenticatedUsers authenUsers = tokenService.parseToken(request);
		    return authenUsers;
	    }
	    catch (Exception ex) {
	      throw new Exception("Error parsing the token: " + ex.toString());
	    }
	  }
	  
	  @ApiAuthToken(scheme="Bearer", roles="ROLE_ADMIN")
	  @RequestMapping(value = "/xxx", method = RequestMethod.GET)
	  @ResponseBody
	  public String xxx(HttpServletRequest request) throws Exception {
		  return "dddd";
	  }
}
