package com.offcl.blogs_service.controller;

import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.offcl.blogs_service.common.response.ApiResponse;
import com.offcl.blogs_service.dto.UserRegisterRequestDto;
import com.offcl.blogs_service.dto.UserRequestDto;
import com.offcl.blogs_service.dto.UserResponseDto;
import com.offcl.blogs_service.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/test")
	public ApiResponse<UserResponseDto> getUserById(@RequestBody UserRequestDto dto){
		
	
		
		return this.userService.updateUser(1L, dto);
	}
	

}
