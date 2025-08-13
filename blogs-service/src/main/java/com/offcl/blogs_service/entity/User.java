package com.offcl.blogs_service.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User  extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstName;
	private String lastName;
	
	private String mobileNumber;
	private String emailAddress;
	
	private String state;
	private String district;
	
	private String pincode;
	
	private String address;
	
	private String password;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "file_id")
	private FileEntity profile;
	
	@OneToMany(mappedBy = "user",cascade =  CascadeType.ALL)
	private List<Blogs> blogs;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<Comments> comments;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<Likes> likes;

}
