package com.example.cms.service;

import org.springframework.http.ResponseEntity;

import com.example.cms.requestdto.UserRequest;
import com.example.cms.responsedto.UserResponse;
import com.example.cms.utility.ResponseStructure;

public interface UserService {

	public ResponseEntity<ResponseStructure<UserResponse>> userRegister(UserRequest userRequest);

	public ResponseEntity<ResponseStructure<UserResponse>> softDeleteUserById(int userId);

	public ResponseEntity<ResponseStructure<UserResponse>> findUserById(int userId);

}
