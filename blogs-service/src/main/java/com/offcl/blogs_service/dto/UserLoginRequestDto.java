package com.offcl.blogs_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginRequestDto {

	@NotBlank(message="Email is required")
	@Email(message="Provide a valid email address")
	private String email;
	
	@NotBlank(message="Password is required")
	private String password;
}
