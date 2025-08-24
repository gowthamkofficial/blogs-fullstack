package com.offcl.blogs_service.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offcl.blogs_service.common.enums.ResponseStatus;
import com.offcl.blogs_service.common.response.ApiResponse;
import com.offcl.blogs_service.common.response.PagedResponse;
import com.offcl.blogs_service.configs.DateUtil;
import com.offcl.blogs_service.dto.BlogRequestDto;
import com.offcl.blogs_service.dto.BlogResponseDto;
import com.offcl.blogs_service.dto.UserRequestDto;
import com.offcl.blogs_service.dto.UserResponseDto;
import com.offcl.blogs_service.entity.Blogs;
import com.offcl.blogs_service.entity.User;
import com.offcl.blogs_service.exception.ResourceNotFoundException;
import com.offcl.blogs_service.mappers.BlogsMapper;
import com.offcl.blogs_service.mappers.UserMapper;
import com.offcl.blogs_service.repository.BlogRepository;
import com.offcl.blogs_service.repository.CommentsRepository;
import com.offcl.blogs_service.repository.FileRepository;
import com.offcl.blogs_service.repository.LikesRepository;
import com.offcl.blogs_service.repository.UserRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class UserService {

	@Autowired
	private BlogRepository blogRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private CommentsRepository commentRepo;

	@Autowired
	private LikesRepository likeRepo;

	@Autowired
	private FileRepository fileRepo;

	
	
	
	public ApiResponse<PagedResponse<BlogResponseDto>> getAllBlogs(Long id, Pageable pageable) {
	    
	    Page<Blogs> pagedBlogs = this.blogRepo.findByUserId(id, pageable);

	    List<BlogResponseDto> blogDtos = pagedBlogs.getContent()
	            .stream()
	            .map(blog -> BlogsMapper.mapBlog(blog, blog.getUser()))
	            .toList();

	    PagedResponse<BlogResponseDto> pagedResponse = new PagedResponse<>(
	            blogDtos,
	            pagedBlogs.getNumber(),
	            pagedBlogs.getSize(),
	            pagedBlogs.getTotalElements(),
	            pagedBlogs.getTotalPages(),
	            pagedBlogs.isLast()
	    );

	    return new ApiResponse<>(ResponseStatus.Success, "Listed blogs successfully", pagedResponse);
	}

	

	public ApiResponse<BlogResponseDto> createBlog(BlogRequestDto dto) {
		
		System.out.println(dto+"---------------->");

		User existingUser = this.userRepo.findById(dto.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id " + dto.getUserId()));

		Blogs blogsEntity = Blogs.builder().user(existingUser).category(dto.getCategory()).content(dto.getContent())
				.title(dto.getTitle()).publishedOn(DateUtil.toDateTime(dto.getPublishedOn())).comments(Collections.emptyList())
				.likes(new ArrayList<>()).build();

		Blogs saved = this.blogRepo.save(blogsEntity);
	
		
		BlogResponseDto response =  BlogsMapper.mapBlog(blogsEntity, existingUser);
		

		return new ApiResponse<BlogResponseDto>(ResponseStatus.Success,"Created blog successfully",response);
	}
	
	
	public ApiResponse<BlogResponseDto> updateBlog(Long id, BlogRequestDto dto){
		
		User existingUser = this.userRepo.findById(dto.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id " + dto.getUserId()));
		
		Blogs existingBlog = this.blogRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Blog not found with id "+id));
		
		existingBlog.setUser(existingUser);
		existingBlog.setCategory(dto.getCategory());
		existingBlog.setContent(dto.getContent());
		existingBlog.setTitle(dto.getTitle());
		existingBlog.setPublishedOn(DateUtil.toDateTime(dto.getPublishedOn()));

	     
	Blogs saved = this.blogRepo.save(existingBlog);
	
		
		BlogResponseDto response =  BlogsMapper.mapBlog(saved, existingUser);
		
		return new ApiResponse<BlogResponseDto>(ResponseStatus.Success,"Updated blog successfully",response);
		
	}
	

	public ApiResponse<UserResponseDto> updateUser(Long id, UserRequestDto dto) {

		User existingUser = userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(String.format("User not found with id %d", id)));
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
				.message("Updated user successfully").data(UserMapper.mapUserResponse(savedUser)).build();

	}

	public ApiResponse<UserResponseDto> viewUser(Long id) {
		User existingUser = userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(String.format("User not found with id %d", id)));
		return ApiResponse.<UserResponseDto>builder().status(ResponseStatus.Success)
				.message("Updated user successfully").data(UserMapper.mapUserResponse(existingUser)).build();
	}

}
