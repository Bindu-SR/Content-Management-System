package com.example.cms.serviceimpl;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.cms.entity.Blog;
import com.example.cms.entity.User;
import com.example.cms.exception.BlogAlreadyExistsByTitleException;
import com.example.cms.exception.BlogNotFoundByIdException;
import com.example.cms.exception.TitleNotAvailableException;
import com.example.cms.exception.UserNotFoundByIdException;
import com.example.cms.repository.BlogRepository;
import com.example.cms.repository.UserRepository;
import com.example.cms.requestdto.BlogRequest;
import com.example.cms.requestdto.UserRequest;
import com.example.cms.responsedto.BlogResponse;
import com.example.cms.responsedto.UserResponse;
import com.example.cms.service.BlogService;
import com.example.cms.utility.ResponseStructure;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BlogServiceImpl implements BlogService {

	private BlogRepository blogRepo;
	private UserRepository userRepo;
	private ResponseStructure<BlogResponse> structure;

	@Override
	public ResponseEntity<ResponseStructure<BlogResponse>> createBlog(BlogRequest blogRequest, int userId) {

		return userRepo.findById(userId).map(user->{

			if(blogRepo.existsByTitle(blogRequest.getTitle()))
				throw new BlogAlreadyExistsByTitleException("Title already Exits");

			if(blogRequest.getTopics().length < 1)
				throw new TitleNotAvailableException("Failed to create blog");

			Blog blog = mapToBlogEntity(blogRequest, new Blog());
			blog.setUserList(Arrays.asList(user));
			blogRepo.save(blog);

			return ResponseEntity.ok(structure.setStatusCode(HttpStatus.OK.value())
					.setMessage("Blog created succesfully")
					.setBody(mapToBlogResponse(blog)));
		}).orElseThrow(()-> new UserNotFoundByIdException("User with given Id not exists"));

	}

	@Override
	public ResponseEntity<ResponseStructure<BlogResponse>> findBlogById(int blogId) {
		return blogRepo.findById(blogId).map(blog->{
			return ResponseEntity.ok(structure.setStatusCode(HttpStatus.OK.value())
					.setMessage("Blog Found Successfully")
					.setBody(mapToBlogResponse(blog)));
		}).orElseThrow(()-> new BlogNotFoundByIdException("Invalid Blog Id"));
	}
	
	@Override
	public ResponseEntity<ResponseStructure<BlogResponse>> updateBlogById(BlogRequest blogRequest, int blogId) {
		return blogRepo.findById(blogId).map(blog->{
			
			blog.setTitle(blogRequest.getTitle());
			blog.setTopics(blogRequest.getTopics());
			blog.setAbout(blogRequest.getAbout());
			
			blogRepo.save(blog);
			
			return ResponseEntity.ok(structure.setStatusCode(HttpStatus.OK.value())
					                    .setMessage("Blog updated Succesfully")
					                    .setBody(mapToBlogResponse(blog)));
		}).orElseThrow(()-> new BlogNotFoundByIdException("Invalid Blog Id"));
	}
	
	@Override
	public ResponseEntity<Boolean> checkBlogTitleAvailability(String blogTitle) {
	     return new ResponseEntity<Boolean> (blogRepo.existsByTitle(blogTitle),HttpStatus.OK);
	}
	
	private BlogResponse mapToBlogResponse(Blog blog) {
		return BlogResponse.builder()
				.blogId(blog.getBlogId())
				.title(blog.getTitle())
				.topics(blog.getTopics())
				.about(blog.getAbout())
				.build();
	}

	private Blog mapToBlogEntity(BlogRequest blogRequest, Blog blog){

		blog.setTitle(blogRequest.getTitle());
		blog.setTopics(blogRequest.getTopics());
		blog.setAbout(blogRequest.getAbout());
		blog.setUserList(blogRequest.getUserList());
		return blog;

	}

}
