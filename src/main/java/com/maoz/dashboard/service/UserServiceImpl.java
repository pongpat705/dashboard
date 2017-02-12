package com.maoz.dashboard.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maoz.dashboard.entity.JDBCUser;
import com.maoz.dashboard.repository.JDBCUserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired JDBCUserRepository usersRepository;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());	
	
	public String userToString(String userName){
    	JDBCUser user = usersRepository.findByUserName(userName);
    	ObjectMapper mapper = new ObjectMapper();
    	String tramformed = "";
		try {
			tramformed = mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
    	
		return tramformed;
    }
	
	public JDBCUser getUser(String userName){
		log.info("get userBean of "+userName);
		JDBCUser user = usersRepository.findByUserName(userName);
		return user;
	}
    
    public JDBCUser stringToUsers(String usersString) {
    	ObjectMapper mapper = new ObjectMapper();
    	JDBCUser users = null;
		try {
			users = mapper.readValue(usersString, JDBCUser.class);
		} catch (JsonParseException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} catch (JsonMappingException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return users;
    }

}
