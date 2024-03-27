package com.example.cms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.cms.requestdto.UserRequest;
import com.example.cms.responsedto.UserResponse;
import com.example.cms.service.UserService;
import com.example.cms.utility.ResponseStructure;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {

	private UserService service;
	
	@PostMapping(value = "/users/register")
    public ResponseEntity<ResponseStructure<UserResponse>> userRegister(@RequestBody UserRequest userRequest){
		return service.userRegister(userRequest);
	}
	
	@GetMapping(value="/test")
	public String test() {
		return "Hello from CMS";
	}
}
