package com.offcl.blogs_service.mappers;

import com.offcl.blogs_service.dto.BlogResponseDto;
import com.offcl.blogs_service.dto.CommentResponseDto;
import com.offcl.blogs_service.dto.LikeResponseDto;
import com.offcl.blogs_service.entity.Blogs;
import com.offcl.blogs_service.entity.Comments;
import com.offcl.blogs_service.entity.Likes;
import com.offcl.blogs_service.entity.User;

public class BlogsMapper {
	
	
	
	public static BlogResponseDto mapBlog(Blogs blog, User user) {
		
		return BlogResponseDto.builder()
				.blogId(blog.getId())
				.title(blog.getTitle())
				.category(blog.getCategory())
				.publishedOn(blog.getPublishedOn())
				.content(blog.getContent())
				.createdOn(blog.getCreatedOn())
				.updatedOn(blog.getUpdatedOn())
				.user(UserMapper.mapUserResponse(user))
				.build();
				
	}
	
	
	public static CommentResponseDto mapComments(Comments comment) {
		return CommentResponseDto.builder()
				.id(comment.getId())
				.commentText(comment.getComment())
				.user(UserMapper.mapUserResponse(comment.getUser()))
				.createdOn(comment.getCreatedOn())
				.build();
		}
	
	public static LikeResponseDto mapLikes(Likes like) {
		return LikeResponseDto.builder()
				.id(like.getId())
				.user(UserMapper.mapUserResponse(like.getUser()))
				.createdOn(like.getCreatedOn())
				.build();
		}

}
