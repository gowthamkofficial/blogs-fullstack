package com.offcl.blogs_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.offcl.blogs_service.entity.Comments;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {

	
	List<Comments> findByBlogId(Long blogId);
}
