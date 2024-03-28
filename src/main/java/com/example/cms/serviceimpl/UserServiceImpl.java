package com.example.cms.serviceimpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cms.entity.User;
import com.example.cms.exception.UserAlreadyExitsByEmailException;
import com.example.cms.exception.UserNotFoundByIdException;
import com.example.cms.repository.UserRepository;
import com.example.cms.requestdto.UserRequest;
import com.example.cms.responsedto.UserResponse;
import com.example.cms.service.UserService;
import com.example.cms.utility.ResponseStructure;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private UserRepository userRepo;
	private ResponseStructure<UserResponse> structure;
	private PasswordEncoder passwordEncoder;
	
	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> userRegister(UserRequest userRequest) {
	    
		if(userRepo.existsByEmail(userRequest.getEmail()))
			throw new UserAlreadyExitsByEmailException("Failed to register User");
		
		User user = userRepo.save(mapToUserEntity(userRequest, new User()));
		
		return ResponseEntity.ok(structure.setStatusCode(HttpStatus.OK.value())
				                          .setMessage("User registered successfully")
				                          .setBody(mapToUserResponse(user)));
	}
	
	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> softDeleteUserById(int userId) {
		
		return userRepo.findById(userId).map(user->{
			user.setDeleted(true);
			userRepo.save(user);
			return ResponseEntity.ok(structure.setStatusCode(HttpStatus.OK.value())
					                          .setMessage("User Deleted")
					                          .setBody(mapToUserResponse(user)));
		}).orElseThrow(()-> new UserNotFoundByIdException("User Id not found"));
		
	}
	
	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> findUserById(int userId) {
		return userRepo.findById(userId).map(user->{
			if(!user.isDeleted()) 
			return ResponseEntity.ok(structure.setStatusCode(HttpStatus.OK.value())
                    .setMessage("User Found")
                    .setBody(mapToUserResponse(user)));
			throw new UserNotFoundByIdException("User Id Already deleted");
			
         }).orElseThrow(()-> new UserNotFoundByIdException("User Id not found"));
	}
	
	
	private UserResponse mapToUserResponse(User user) {
		return UserResponse.builder()
				.userId(user.getUserId())
				.userName(user.getUserName())
				.email(user.getEmail())
				.createdAt(user.getCreatedAt())
			    .lastModifiedAt(user.getLastModifiedAt())
			    .build();
	}

	private User mapToUserEntity(UserRequest userRequest, User user){
		
		user.setUserName(userRequest.getUserName());
		user.setEmail(userRequest.getEmail());
		//user.setPassword(userRequest.getPassword());
		user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		return user;
	}

}
