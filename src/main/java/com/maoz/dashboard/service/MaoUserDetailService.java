package com.maoz.dashboard.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.maoz.dashboard.entity.JDBCUser;
import com.maoz.dashboard.entity.JDBCUserRole;
import com.maoz.dashboard.repository.JDBCUserRepository;
import com.maoz.dashboard.security.Authorities;
import com.maoz.dashboard.security.CustomUserDetails;
import com.maoz.dashboard.security.anotherway.JwtTokenRequest;

@Service
public class MaoUserDetailService implements UserDetailsService {

	@Autowired JDBCUserRepository usersRepository;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());	
	
	@Override
	public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		JDBCUser user = usersRepository.findByUserName(username);
		if (null == user){
			log.debug("user: "+username+" not found!");
			throw new UsernameNotFoundException("user: "+username+" not found!");
		}
		List<Authorities> gant = new ArrayList<Authorities>();
		
		if (user.getUsersRoles().isEmpty()){
			throw new UsernameNotFoundException("user: "+username+" roles not found!");
		}
		
		for (JDBCUserRole ur : user.getUsersRoles()) {
			Authorities ga = new Authorities(ur.getRole());
			gant.add(ga);
		}
		
		CustomUserDetails xx = new CustomUserDetails(user.getUserName(), user.getPassword(), null, gant);
		return xx;
	}
	
}
