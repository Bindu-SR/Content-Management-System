package com.example.cms.service;

import org.springframework.http.ResponseEntity;
import com.example.cms.entity.Blog;
import com.example.cms.requestdto.BlogRequest;
import com.example.cms.responsedto.BlogResponse;
import com.example.cms.utility.ResponseStructure;

public interface BlogService {

	public ResponseEntity<ResponseStructure<BlogResponse>> createBlog(BlogRequest blogRequest, int userId);

	public ResponseEntity<ResponseStructure<BlogResponse>> findBlogById(int blogId);

	public ResponseEntity<ResponseStructure<BlogResponse>> updateBlogById(BlogRequest blogRequest, int blogId);

	public ResponseEntity<Boolean> checkBlogTitleAvailability(String blogTitle);
		
}
