package com.offcl.blogs_service.exception;

public class ResourceNotFoundException extends RuntimeException {
	
	public ResourceNotFoundException(String message){
		super(message);
	}

}
