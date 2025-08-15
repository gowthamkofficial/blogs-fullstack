package com.offcl.blogs_service.mappers;

import com.offcl.blogs_service.dto.UserRequestDto;
import com.offcl.blogs_service.dto.UserResponseDto;
import com.offcl.blogs_service.entity.User;

public class UserMapper {
	
	
	public static UserResponseDto mapUserResponse(User user) {
		
		return UserResponseDto.builder()
				.firstName(user.getFirstName())
				.LastName(user.getLastName())
				.emailAddress(user.getEmailAddress())
				.mobileNumber(user.getMobileNumber())
				.state(user.getState())
				.district(user.getDistrict())
				.address(user.getAddress())
				.pincode(user.getPincode())
			.build();
	}

}
