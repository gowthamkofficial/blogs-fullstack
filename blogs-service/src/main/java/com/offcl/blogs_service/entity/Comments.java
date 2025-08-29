package com.offcl.blogs_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comments  extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "comment", columnDefinition = "TEXT")
	private String comment;
	
	
	@ManyToOne()
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;
	
	@ManyToOne()
	@JoinColumn(name = "blog_id")
	@JsonIgnore
	private Blogs blog;
}
