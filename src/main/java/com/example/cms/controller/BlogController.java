package com.example.cms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.cms.requestdto.BlogRequest;
import com.example.cms.responsedto.BlogResponse;
import com.example.cms.service.BlogService;
import com.example.cms.utility.ResponseStructure;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class BlogController {

	private BlogService service;
	
	@PostMapping(value = "/users/{userId}/blogs")
	public ResponseEntity<ResponseStructure<BlogResponse>> createBlog(@RequestBody BlogRequest blogRequest
			                                                                   ,@RequestParam int userId){
		return service.createBlog(blogRequest,userId);
	}
	
	@GetMapping(value = "/blogs/{blogId}")
	public ResponseEntity<ResponseStructure<BlogResponse>> findBlogById(@PathVariable int blogId){
		return service.findBlogById(blogId);
	}
	
	@PutMapping(value = "/blogs/{blogId}")
	public ResponseEntity<ResponseStructure<BlogResponse>> updateBlogById(@RequestBody BlogRequest blogRequest,
			                                                                      @RequestParam int blogId){
		return service.updateBlogById(blogRequest, blogId);
	}
	
	@GetMapping(value = "/titles/{title}/blogs")
	public ResponseEntity<Boolean> checkBlogTitleAvailability(@PathVariable String blogTitle){
		return service.checkBlogTitleAvailability(blogTitle);
	}
	
}
