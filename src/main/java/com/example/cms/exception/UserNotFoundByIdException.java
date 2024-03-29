package com.example.cms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserNotFoundByIdException extends RuntimeException {

	private String message; 
}
