package com.example.mpi.security.jwt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class TokenRefreshException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public TokenRefreshException(String token, String message) {
		super(String.format("실패 요인 [%s]: %s", token, message));
	}
}
