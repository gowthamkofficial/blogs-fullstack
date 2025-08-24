package com.offcl.blogs_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {
	private String firstName;
	private String LastName;
	private String mobileNumber;
	private String emailAddress;
	private String state;
	private String district;
	private String address;
	private String pincode;
	private String profileImageUrl;
}
