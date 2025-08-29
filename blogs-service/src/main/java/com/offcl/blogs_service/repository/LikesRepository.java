package com.offcl.blogs_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.offcl.blogs_service.entity.Likes;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {

	List<Likes> findByBlogId(Long blogId);
	
	Optional<Likes> findByUserIdAndBlogId(Long userId,Long blogId);
	
	boolean existsByUserIdAndBlogId(Long userId, Long blogId);
}
