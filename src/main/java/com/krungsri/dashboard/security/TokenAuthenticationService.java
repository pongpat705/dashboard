package com.krungsri.dashboard.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenAuthenticationService {

	private final long EXPIRATIONTIME = 1000*60*1;
	
	private final String secret = "MaozcaptionitSuperSecret";
	
	private final String tokenPrefix = "Bearer";
	
	private final String headerString = "maoz-token";
	
	
    public void addAuthentication(HttpServletResponse response, String username) {
        // We generate a token now.
        String JWT = Jwts.builder()
            .setSubject(username)
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();
        response.addHeader(headerString, tokenPrefix + " " + JWT);
    }
    
    public Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(headerString);
        if (null != token && !StringUtils.isEmpty(token)){
        	token = token.replace("Bearer ", "");
        }
        if (token != null) {
            // parse the token.
            String user = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
            if (user != null) // we managed to retrieve a user
            {
                return new AuthenticatedUser(user, token);
            }
        }
        return null;
    }
}
