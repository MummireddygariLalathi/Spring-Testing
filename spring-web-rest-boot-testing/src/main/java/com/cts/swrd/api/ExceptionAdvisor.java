package com.cts.swrd.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cts.swrd.exception.EmployeeException;

@RestControllerAdvice
public class ExceptionAdvisor {
	
	@ExceptionHandler(EmployeeException.class)
	public ResponseEntity<String> handleEmployeeExceptionAction(EmployeeException exp){
		return new ResponseEntity<String>(exp.getMessage(),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleExceptionAction(Exception exp){
		return new ResponseEntity<String>(exp.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
