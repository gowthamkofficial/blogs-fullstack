package com.offcl.blogs_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offcl.blogs_service.common.enums.ResponseStatus;
import com.offcl.blogs_service.common.response.ApiResponse;
import com.offcl.blogs_service.dto.UserLoginRequestDto;
import com.offcl.blogs_service.dto.UserRegisterRequestDto;
import com.offcl.blogs_service.dto.UserResponseDto;
import com.offcl.blogs_service.entity.User;
import com.offcl.blogs_service.exception.AlreadyExistsException;
import com.offcl.blogs_service.exception.InvalidCredentialException;
import com.offcl.blogs_service.exception.ResourceNotFoundException;
import com.offcl.blogs_service.mappers.UserMapper;
import com.offcl.blogs_service.repository.UserRepository;

@Service
public class SessionService {
	
	
	@Autowired
	private UserRepository userRepo;
	
	
	
	
	public ApiResponse<UserResponseDto> registerNewUser(UserRegisterRequestDto dto) throws AlreadyExistsException{

		 boolean emailAlreadyExists = this.userRepo.existsByEmailAddress(dto.getEmailAddress());
		 
		 boolean mobileNumberAlreadyExists = this.userRepo.existsByMobileNumber(dto.getMobileNumber());

		 if(emailAlreadyExists) {
			 throw new AlreadyExistsException(String.format("Email already exists %s", dto.getEmailAddress()));
		 }
		 
		 if(mobileNumberAlreadyExists) {
			 throw new AlreadyExistsException(String.format("Mobile number already exists %s", dto.getMobileNumber()));
		 }
		 User user = User.builder()
				 .firstName(dto.getFirstName())
				 .lastName(dto.getLastName())
				 .mobileNumber(dto.getMobileNumber())
				 .emailAddress(dto.getEmailAddress())
				 .state(dto.getState())
				 .district(dto.getDistrict())
				 .address(dto.getAddress())
				 .pincode(dto.getPincode())
				 .password(dto.getPassword())
				 .build();
		 
		 User savedUser = this.userRepo.save(user);
		 
		 return ApiResponse.<UserResponseDto>builder().status(ResponseStatus.Success).message("Registration successfull").data(UserMapper.mapUserResponse(savedUser)).build();
	
	}
	
	public ApiResponse<UserResponseDto> signIn(UserLoginRequestDto user) throws ResourceNotFoundException,InvalidCredentialException{
		
		User existingUser = this.userRepo.findByEmailAddress(user.getEmail()).orElseThrow(()->new ResourceNotFoundException(String.format("User not found with email: (%s) you have provided!", user.getEmail())));
		
		   if(!existingUser.getPassword().equals(user.getPassword())) {
			 throw new InvalidCredentialException("Invalid username or password");  
		   }
		
		return ApiResponse.<UserResponseDto>builder()
				.status(ResponseStatus.Success)
				.message("Login successfull")
				.data(UserMapper.mapUserResponse(existingUser))
				.build();
		
	}

}
