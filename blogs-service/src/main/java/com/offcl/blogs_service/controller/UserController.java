package com.offcl.blogs_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.offcl.blogs_service.common.response.ApiResponse;
import com.offcl.blogs_service.common.response.PagedResponse;
import com.offcl.blogs_service.dto.BlogRequestDto;
import com.offcl.blogs_service.dto.BlogResponseDto;
import com.offcl.blogs_service.dto.UserRequestDto;
import com.offcl.blogs_service.dto.UserResponseDto;
import com.offcl.blogs_service.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/blogs/{userId}")
	public ResponseEntity<ApiResponse<PagedResponse<BlogResponseDto>>> getBlogs(
			@PathVariable(required = true) Long userId,
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "10") int size,
	        @RequestParam(defaultValue = "publishedOn") String sortBy,
	        @RequestParam(defaultValue = "desc") String sortDir) {
		
		
	    Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) 
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
	    
	    Pageable pageable = PageRequest.of(page, size, sort);
		

	    return ResponseEntity.status(HttpStatus.OK).body(userService.getAllBlogs(userId,pageable));
	}

	
	
	@PostMapping("/createBlog")
	public ResponseEntity<ApiResponse<BlogResponseDto>> createBlog( @RequestBody BlogRequestDto dto){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.createBlog(dto));
	}
	
	@PutMapping("/updateBlog/{blogId}")
	public ResponseEntity<ApiResponse<BlogResponseDto>> updateBlog( @PathVariable(required = true) Long blogId,@RequestBody BlogRequestDto dto){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.updateBlog(blogId,dto));
	}
	
	
	@PutMapping("/updateProfileDetails/{userId}")
	public ResponseEntity<ApiResponse<UserResponseDto>>  updateProfileDetails(@PathVariable(required = true) Long userId,@RequestBody UserRequestDto dto){
		
		return ResponseEntity.status(HttpStatus.OK).body(this.userService.updateUser(userId, dto));
	}
	
	
	
	

}
