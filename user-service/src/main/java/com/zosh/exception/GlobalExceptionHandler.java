package com.zosh.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.function.EntityResponse;

import com.zoshpayload.response.ExceptionResponse;

@ControllerAdvice
public interface GlobalExceptionHandler {

	
	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public static  ResponseEntity<ExceptionResponse> ExceptionHandler(Exception ex, WebRequest req){
		ExceptionResponse response = new ExceptionResponse(
				ex.getMessage(),
				req.getDescription(false), 
				LocalDateTime.now()
				);
		
			return ResponseEntity.ok(response);
			
	
		//return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		
	}
	
	
	/*
	 * @ExceptionHandler(ReviewException.class) public
	 * ResponseEntity<ExceptionResponse> ReviewExistExceptionHandler(
	 * ReviewException ex, WebRequest req) { ExceptionResponse response = new
	 * ExceptionResponse( ex.getMessage(), req.getDescription(false),
	 * LocalDateTime.now()); return new ResponseEntity<>(response,
	 * HttpStatus.BAD_REQUEST); }
	 * 
	 * @ExceptionHandler(UserException.class) public
	 * ResponseEntity<ExceptionResponse> UserExceptionHandler( UserException ex,
	 * WebRequest req) { ExceptionResponse response = new ExceptionResponse(
	 * ex.getMessage(), req.getDescription(false), LocalDateTime.now()); return new
	 * ResponseEntity<>(response, HttpStatus.BAD_REQUEST); }
	 * 
	 * @ExceptionHandler(Exception.class) public static
	 * ResponseEntity<EntityResponse> ExceptionHandler(Exception ex, WebRequest req)
	 * { ExceptionResponse response = new ExceptionResponse( ex.getMessage(),
	 * req.getDescription(false), LocalDateTime.now()); return new
	 * ResponseEntity<>(response, HttpStatus.BAD_REQUEST); }
	 */

}
