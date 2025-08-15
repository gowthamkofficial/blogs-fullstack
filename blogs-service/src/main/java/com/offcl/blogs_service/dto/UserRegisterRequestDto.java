package com.offcl.blogs_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserRegisterRequestDto extends UserRequestDto {
	
	
	@NotBlank(message = "Password is required")
	@Size(min=8,max=15,message="Password should have 8  to 15 characters")
	private  String password;

}
