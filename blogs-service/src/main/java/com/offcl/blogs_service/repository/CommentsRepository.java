package com.offcl.blogs_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.offcl.blogs_service.entity.Comments;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {

}
