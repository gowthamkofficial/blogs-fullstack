package com.offcl.blogs_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentRequestDto {

	@NotNull(message="User id is required")
	private Long userId;
	@NotNull(message="Blog id is required")
	private Long blogId;
	@NotBlank(message="Comment is required")
	private String commentText;

}
