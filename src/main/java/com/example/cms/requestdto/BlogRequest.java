package com.example.cms.requestdto;

import java.util.ArrayList;
import java.util.List;

import com.example.cms.entity.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BlogRequest {

	@NotNull(message="Enter the title")
	@Pattern(regexp = "[A-Z]+[a-z]",message = "Title should contain only alphabets")
	private String title;
	@NotNull(message = "Enter the topic")
	private String[] topics;
	private String about;
	
	private List<User> userList = new ArrayList<>();
}