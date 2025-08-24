package com.offcl.blogs_service.service;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.offcl.blogs_service.common.enums.ResponseStatus;
import com.offcl.blogs_service.common.response.ApiResponse;
import com.offcl.blogs_service.common.response.PagedResponse;
import com.offcl.blogs_service.dto.BlogResponseDto;
import com.offcl.blogs_service.dto.CommentRequestDto;
import com.offcl.blogs_service.dto.CommentResponseDto;
import com.offcl.blogs_service.dto.LikeResponseDto;
import com.offcl.blogs_service.entity.Blogs;
import com.offcl.blogs_service.entity.Comments;
import com.offcl.blogs_service.entity.Likes;
import com.offcl.blogs_service.entity.User;
import com.offcl.blogs_service.exception.AlreadyExistsException;
import com.offcl.blogs_service.exception.ResourceNotFoundException;
import com.offcl.blogs_service.mappers.BlogsMapper;
import com.offcl.blogs_service.repository.BlogRepository;
import com.offcl.blogs_service.repository.CommentsRepository;
import com.offcl.blogs_service.repository.FileRepository;
import com.offcl.blogs_service.repository.LikesRepository;
import com.offcl.blogs_service.repository.UserRepository;

@Service
public class BlogService {
	
	@Autowired
	private BlogRepository blogRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CommentsRepository commentRepo;
	
	@Autowired
	private LikesRepository  likeRepo;
	
	
	@Autowired
	private FileRepository fileRepo;
	
	
	
	public ApiResponse<PagedResponse<BlogResponseDto>> getAllBlogs(Pageable pageable){
		
	    LocalDateTime today = LocalDateTime.now();
	    
	    Page<Blogs> pagedBlogs = this.blogRepo.findByPublishedOnBefore(today, pageable);
	    
	     PagedResponse<BlogResponseDto> pagedResponse =  PagedResponse.<BlogResponseDto>builder()
	    		 				.content(pagedBlogs.getContent().stream().map(blog->BlogsMapper.mapBlog(blog, blog.getUser())).toList())
	    		 				.page(pagedBlogs.getNumber())
	    		 		        .size(pagedBlogs.getSize())
	    		 		        .totalElements(pagedBlogs.getTotalElements())
	    		 		        .totalPages(pagedBlogs.getTotalPages())
	    		 		        .last(pagedBlogs.isLast())
	    		 				.build();
		
		return new ApiResponse<PagedResponse<BlogResponseDto>>(ResponseStatus.Success,"Fetched blogs successfully",pagedResponse);
	}
	
	
	
	public ApiResponse<BlogResponseDto> getBlog(Long blogId){
		Blogs existingBlog = this.blogRepo.findById(blogId).orElseThrow(()->new ResourceNotFoundException("Blog not found with blogId "+blogId));
		return new ApiResponse<BlogResponseDto>(ResponseStatus.Success,"Fetched blog successfully",BlogsMapper.mapBlog(existingBlog, existingBlog.getUser()));
	}
	
	
	
	
	public ApiResponse<List<CommentResponseDto>> getAllComments(Long blogId){
		
		boolean blogExists =  this.blogRepo.existsById(blogId);
		
		if(!blogExists) {
			throw new ResourceNotFoundException("Blog not found with blogId "+blogId);
		}
		
		
		List<CommentResponseDto> response = this.commentRepo.findByBlogId(blogId).stream().map(c->BlogsMapper.mapComments(c)).toList();
		
		
		return new ApiResponse<List<CommentResponseDto>>(ResponseStatus.Success,"Fetched comments successfully",response);
	}
	
	
	
	public ApiResponse<List<LikeResponseDto>> getAllLikes(Long blogId){
		
		boolean blogExists =  this.blogRepo.existsById(blogId);
		
		if(!blogExists) {
			throw new ResourceNotFoundException("Blog not found with blogId "+blogId);
		}
		
		
		List<LikeResponseDto> response = this.likeRepo.findByBlogId(blogId).stream().map(l->BlogsMapper.mapLikes(l)).toList();
		
		
		return new ApiResponse<List<LikeResponseDto>>(ResponseStatus.Success,"Fetched likes successfully",response);
	}
	
	
	
	
	public ApiResponse<CommentResponseDto> addComment(CommentRequestDto comment){
		User existingUser = this.userRepo.findById(comment.getUserId())
				.orElseThrow(()-> new ResourceNotFoundException("User not found with id "+comment.getUserId()));
		Blogs existingBlog = this.blogRepo.findById(comment.getBlogId())
				.orElseThrow(()-> new ResourceNotFoundException("Blog not found with id "+comment.getBlogId()));
		
		Comments commentEntity = Comments.builder().user(existingUser)
				.blog(existingBlog)
				.comment(comment.getCommentText())
				.build();
		
		var saved = BlogsMapper.mapComments(this.commentRepo.save(commentEntity));
		
		return new ApiResponse<CommentResponseDto>(ResponseStatus.Success,"You have commented successfully",saved);
	}
	
	
	public ApiResponse<String> removeComment(Long id){
		
		Comments existingComment =  this.commentRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Comment not found with id "+id));
		
		this.commentRepo.deleteById(id);
		
		return new ApiResponse<String>(ResponseStatus.Success,"Deleted comment successfully",null);
		
	}
	
	
	public ApiResponse<LikeResponseDto> addLike(Long userId,Long blogId){
		User existingUser = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User not found with id "+ userId));
		Blogs existingBlog = this.blogRepo.findById(blogId)
				.orElseThrow(()-> new ResourceNotFoundException("Blog not found with id "+blogId));
		
		if(this.likeRepo.existsByUserIdAndBlogId(userId, blogId)) {
			throw new AlreadyExistsException("You have already liked this blog!");
		}
		
		Likes likeEntity = Likes.builder().user(existingUser)
				.blog(existingBlog)
				.build();
		
		var saved = BlogsMapper.mapLikes(this.likeRepo.save(likeEntity));
		
		return new ApiResponse<LikeResponseDto>(ResponseStatus.Success,"You have liked successfully",saved);
	}
	
	
	public ApiResponse<String> removeLike(Long id){
		
		Likes existingLike =  this.likeRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Like not found with id "+id));
		
		this.likeRepo.deleteById(id);
		
		return new ApiResponse<String>(ResponseStatus.Success,"Unliked blog successfully",null);
		
	}
	
	
	

}
