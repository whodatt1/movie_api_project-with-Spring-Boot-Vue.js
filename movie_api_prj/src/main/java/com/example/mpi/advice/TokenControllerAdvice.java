package com.example.mpi.advice;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.example.mpi.security.jwt.exception.TokenRefreshException;

@RestControllerAdvice
public class TokenControllerAdvice {
	
	  @ExceptionHandler(value = TokenRefreshException.class)
	  public ErrorMessage handleTokenRefreshException(TokenRefreshException ex, WebRequest request) {
	    ErrorMessage errorMessage = new ErrorMessage(
	        HttpStatus.FORBIDDEN.value(),
	        new Date(),
	        ex.getMessage(),
	        request.getDescription(false));
	    
	    return errorMessage;
	  }
}
