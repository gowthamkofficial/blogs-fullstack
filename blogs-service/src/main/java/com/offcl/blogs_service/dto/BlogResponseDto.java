package com.offcl.blogs_service.dto;


import java.time.LocalDateTime;
import java.util.List;

import com.offcl.blogs_service.entity.Likes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogResponseDto {
	
	
	private Long blogId;
	
	private String title;
	
	private String category;
	
	private LocalDateTime publishedOn;
	
	private String content;
	

	
	private  LocalDateTime createdOn;
	
	private LocalDateTime updatedOn;
	
	private UserResponseDto  user;
	
	private  Long likesCount ;
	
	private Long commentsCount;
	
	private List<Long> likedUsers;
	
}
