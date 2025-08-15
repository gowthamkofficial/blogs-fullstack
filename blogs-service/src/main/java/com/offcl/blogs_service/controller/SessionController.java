package com.offcl.blogs_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.offcl.blogs_service.common.response.ApiResponse;
import com.offcl.blogs_service.dto.UserLoginRequestDto;
import com.offcl.blogs_service.dto.UserRegisterRequestDto;
import com.offcl.blogs_service.dto.UserResponseDto;
import com.offcl.blogs_service.service.SessionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/session")
public class SessionController {
	
	
	@Autowired
	private SessionService sessionService;

	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse<UserResponseDto>> userRegister(@Valid @RequestBody UserRegisterRequestDto dto){
	 return ResponseEntity.<ApiResponse<UserResponseDto>>status(HttpStatus.CREATED).body(this.sessionService.registerNewUser(dto));
	}
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<UserResponseDto>> userLogin(@Valid @RequestBody UserLoginRequestDto user){
		
		return  ResponseEntity.<ApiResponse<UserResponseDto>>status(HttpStatus.OK).body(this.sessionService.signIn(user));
	}

}
