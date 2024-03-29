package com.example.cms.responsedto;

import java.util.ArrayList;
import java.util.List;
import com.example.cms.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogResponse {

	private int blogId;
	private String title;
	private String[] topics;
	private String about;
	
}
