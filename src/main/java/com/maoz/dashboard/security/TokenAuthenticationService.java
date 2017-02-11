package com.maoz.dashboard.security;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maoz.dashboard.config.JwtProperties;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenAuthenticationService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());	
	
    private JwtProperties jwtProperties = new JwtProperties();
	
    public void generateToken(HttpServletResponse response, String username, Authentication auth) {
        // We generate a token now.
    	AuthenticatedUsers tranformed = new AuthenticatedUsers(auth.getName(), null, (List<Authorities>) auth.getAuthorities(), true);
    	String build = authToString(tranformed);
    	log.info("gen username : "+username);
        String JWT = Jwts.builder()
            .setSubject(build)
            .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getJwtExpirationTime()))
            .signWith(SignatureAlgorithm.HS512, jwtProperties.getJwtSecret())
            .compact();
        response.addHeader(jwtProperties.getJwtHeader(), jwtProperties.getJwtSchema() + " " + JWT);
    }
    
    public AuthenticatedUsers parseToken(HttpServletRequest request) {
    	String token = request.getHeader(jwtProperties.getJwtHeader());
    	if (null != token) {
    		token = token.replace(jwtProperties.getJwtSchema(), "");
    	}
        if (token != null) {
            // parse the token.
            Claims claims = Jwts.parser()
                .setSigningKey(jwtProperties.getJwtSecret())
                .parseClaimsJws(token)
                .getBody();
            if (claims != null) // we managed to retrieve a user
            {
            	AuthenticatedUsers auth = stringToAuth(claims.getSubject());
            	log.info("parse username : "+auth.getName());
                return auth;
            }
        }
        return null;
    }
    
    public AuthenticatedUsers parseToken(String token) {
        if (token != null) {
            // parse the token.
            Claims claims = Jwts.parser()
                .setSigningKey(jwtProperties.getJwtSecret())
                .parseClaimsJws(token)
                .getBody();
            if (claims != null) // we managed to retrieve a user
            {
            	log.info("username : "+claims.getSubject());
            	return new AuthenticatedUsers(claims.getSubject(), token, null, true);
            }
        }
        return null;
    }
    
    public String authToString(AuthenticatedUsers xx) {
    	ObjectMapper mapper = new ObjectMapper();
    	String auth = null;
		try {
			auth = mapper.writeValueAsString(xx);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return auth;
    	
    }
    
    public AuthenticatedUsers stringToAuth(String xx) {
    	ObjectMapper mapper = new ObjectMapper();
    	AuthenticatedUsers auth = null;
    	try {
			auth = mapper.readValue(xx, AuthenticatedUsers.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return auth;
    }
}
