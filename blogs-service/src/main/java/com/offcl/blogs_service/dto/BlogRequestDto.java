package com.offcl.blogs_service.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogRequestDto {
	
	@NotNull(message = "User id is required")
    private Long userId;
	
	@NotBlank(message = "Blog title is required")
	private String title;
	
	@NotBlank(message = "Blog category is required")
	private String category;
	

	@NotNull(message = "Published on date is required")
	@JsonFormat( pattern = "yyyy-MM-dd")
	private LocalDate publishedOn;
	
	@NotBlank(message = "Blog content is required")
	@Size(min = 1000, message = "Blog content should have at least 1000 characters.")
	private String content;
}
