package com.offcl.blogs_service.repository;


import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.offcl.blogs_service.entity.Blogs;

@Repository
public interface BlogRepository extends JpaRepository<Blogs, Long> {
	
	
	
	Page<Blogs> findByUserId(Long userId,Pageable pageable);   
	
	Page<Blogs> findByPublishedOnBefore(LocalDateTime publishedOn, Pageable pageable);


}
