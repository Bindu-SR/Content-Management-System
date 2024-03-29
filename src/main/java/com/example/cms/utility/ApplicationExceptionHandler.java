package com.example.cms.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.example.cms.exception.BlogAlreadyExistsByTitleException;
import com.example.cms.exception.BlogNotFoundByIdException;
import com.example.cms.exception.TitleNotAvailableException;
import com.example.cms.exception.UserAlreadyExitsByEmailException;
import com.example.cms.exception.UserNotFoundByIdException;

import lombok.AllArgsConstructor;

@RestControllerAdvice
@AllArgsConstructor
public class ApplicationExceptionHandler {

	private ErrorStructure<String> structure;
	
	private ResponseEntity<ErrorStructure<String>> errorResponse
	(HttpStatus status, String message, String rootCause){
		
		return new ResponseEntity<ErrorStructure<String>>(structure
				   .setStatusCode(status.value())
				   .setErrorMessage(message)
				   .setRootCause(rootCause),status);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleUserAlreadyExistByEmail
	                         (UserAlreadyExitsByEmailException ex){
		
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), "User already exits with the given email ID");
		
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleUserNotFoundById(UserNotFoundByIdException ex){
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), "User with given Id not exists");
	}
	

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleBlogAlreadyExistsByTitleException
	                          (BlogAlreadyExistsByTitleException ex){
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), "Blog with given title already exists");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleTitleNotAvailableException
	                          (TitleNotAvailableException ex){
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), "Title is less than 1");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleBlogNotFoundByIdException
	                          (BlogNotFoundByIdException ex){
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), "Blog with given Id not exits");
	}

	
}
