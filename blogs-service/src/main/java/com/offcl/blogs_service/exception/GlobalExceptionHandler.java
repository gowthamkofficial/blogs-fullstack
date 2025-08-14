package com.offcl.blogs_service.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.offcl.blogs_service.common.enums.ResponseStatus;
import com.offcl.blogs_service.common.response.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	
	@ExceptionHandler(Exception.class )
	public ResponseEntity<?> handleGeneralExceptions(Exception ex){
		
		ApiResponse<?> response = ApiResponse.builder().status(ResponseStatus.Failure).message(ex.getMessage()).data(null).build();
	
		return ResponseEntity.internalServerError().body(response);
	}

}
