package com.maoz.dashboard.security.anotherway;

public class JwtTokenMalformedException extends RuntimeException {
	
    public JwtTokenMalformedException(String message) {
        super(message);
    }
}
