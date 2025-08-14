package com.offcl.blogs_service.common.response;

import com.offcl.blogs_service.common.enums.ResponseStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse <T> {
	private ResponseStatus status;
	private String message;
	private T data;
}
