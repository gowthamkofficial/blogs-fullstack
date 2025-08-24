package com.offcl.blogs_service.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentResponseDto {

	
	private Long id;
	
	private String commentText;
	
	private UserResponseDto user;
	
	private LocalDateTime createdOn;

	
}
