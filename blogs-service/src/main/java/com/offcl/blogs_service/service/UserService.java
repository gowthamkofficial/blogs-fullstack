package com.offcl.blogs_service.service;

import java.lang.reflect.Field;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offcl.blogs_service.common.enums.ResponseStatus;
import com.offcl.blogs_service.common.response.ApiResponse;
import com.offcl.blogs_service.dto.UserRequestDto;
import com.offcl.blogs_service.dto.UserResponseDto;
import com.offcl.blogs_service.entity.User;
import com.offcl.blogs_service.exception.ResourceNotFoundException;
import com.offcl.blogs_service.mappers.UserMapper;
import com.offcl.blogs_service.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	
	
	public ApiResponse<UserResponseDto> updateUser(Long id,UserRequestDto dto){
		
		User existingUser = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.format("User not found with id %d", id)));
		 existingUser.setFirstName(dto.getFirstName());
		    existingUser.setLastName(dto.getLastName());
		    existingUser.setMobileNumber(dto.getMobileNumber());
		    existingUser.setEmailAddress(dto.getEmailAddress());
		    existingUser.setState(dto.getState());
		    existingUser.setDistrict(dto.getDistrict());
		    existingUser.setPincode(dto.getPincode());
		    existingUser.setAddress(dto.getAddress());
		    
		    User savedUser = this.userRepo.save(existingUser);
		
		return ApiResponse.<UserResponseDto>builder().status(ResponseStatus.Success)
				.message("Updated user successfully")
				.data(UserMapper.mapUserResponse(savedUser)).build();
		
	}
	
	public ApiResponse<UserResponseDto> viewUser(Long id){
		User existingUser = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.format("User not found with id %d", id)));	
		return ApiResponse.<UserResponseDto>builder().status(ResponseStatus.Success)
				.message("Updated user successfully")
				.data(UserMapper.mapUserResponse(existingUser)).build();
	}
	

}
