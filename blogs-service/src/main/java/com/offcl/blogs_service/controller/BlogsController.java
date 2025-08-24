package com.offcl.blogs_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.offcl.blogs_service.dto.CommentRequestDto;
import com.offcl.blogs_service.service.BlogService;

@RestController
@RequestMapping("/api")
public class BlogsController {
	
	@Autowired
	private BlogService blogService;
	

	@GetMapping("/getAllBlogs")
	public ResponseEntity<?> getAllBlogs(@RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "10") int size,
	        @RequestParam(defaultValue = "publishedOn") String sortBy,
	        @RequestParam(defaultValue = "desc") String sortDir){
		
		
		  Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) 
	                ? Sort.by(sortBy).ascending()
	                : Sort.by(sortBy).descending();
		    
		    Pageable pageable = PageRequest.of(page, size, sort);
		
		return ResponseEntity.status(HttpStatus.OK).body(this.blogService.getAllBlogs(pageable));
		
	}
	
	
	@GetMapping("/getBlog/{blogId}")
	public ResponseEntity<?> getBlogById(@PathVariable(required=true) Long blogId){
		
		return ResponseEntity.status(HttpStatus.OK).body(this.blogService.getBlog(blogId));
	}
	
	
	@GetMapping("/getAllComments")
public ResponseEntity<?> getAllComments(@PathVariable(required=true) Long blogId){
		
		return ResponseEntity.status(HttpStatus.OK).body(this.blogService.getAllComments(blogId));
	}
	
	@GetMapping("/getAllLikes/{blogId}")
public ResponseEntity<?> getAllLikes(@PathVariable(required=true) Long blogId){
		
		return ResponseEntity.status(HttpStatus.OK).body(this.blogService.getAllLikes(blogId));
	}
	
	@PostMapping("/addComment")
	public ResponseEntity<?> addComment(@RequestBody CommentRequestDto dto){
		return ResponseEntity.status(HttpStatus.OK).body(this.blogService.addComment(dto));
	}
	
	
	@PostMapping("/addLike/{userId}/{blogId}")
	public ResponseEntity<?> addLike(@PathVariable(required=true) Long userId,@PathVariable(required=true) Long blogId){
		return ResponseEntity.status(HttpStatus.OK).body(this.blogService.addLike(userId, blogId));
	}
	
	
	
	@DeleteMapping("/removeComment/{commentId}")
	public ResponseEntity<?> removeComment(@PathVariable(required=true) Long commentId){
		return ResponseEntity.status(HttpStatus.OK).body(this.blogService.removeComment(commentId));
	}
	
	@DeleteMapping("/removeLike/{likeId}")
	public ResponseEntity<?> removeLike(@PathVariable(required=true) Long likeId){
	return  ResponseEntity.status(HttpStatus.OK).body(this.blogService.removeLike(likeId));	
	}
	
	
	
	
	

}
