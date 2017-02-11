package com.maoz.dashboard.security.anotherway;
//package com.krungsri.dashboard.security.anotherway;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import com.krungsri.dashboard.security.CustomUserDetails;
//import com.krungsri.dashboard.security.TokenAuthenticationService;
//
//@Component
//public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider{
//
//	@Autowired
//    private TokenAuthenticationService tokenService;
//	
//	@Override
//	protected void additionalAuthenticationChecks(UserDetails userDetails,
//			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public boolean supports(Class<?> authentication) {
//		// TODO Auto-generated method stub
//		return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
//	}
//
//	@Override
//	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
//			throws AuthenticationException {
//        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
//        String token = jwtAuthenticationToken.getToken();
//
//        CustomUserDetails parsedUser = tokenService.parseToken(token);
//
//        if (parsedUser == null) {
//            throw new JwtTokenMalformedException("JWT token is not valid");
//        }
//		return parsedUser;
//	}
//
//}
