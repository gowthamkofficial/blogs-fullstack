package com.offcl.blogs_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.offcl.blogs_service.entity.Blogs;

@Repository
public interface BlogRepository extends JpaRepository<Blogs, Long> {

}
