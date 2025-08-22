package com.offcl.blogs_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileResponseDto {
private Long fileId;
	private String fileName;
	private String fileType;
	private Long fileSize;
	private String fileUrl;
	private String userId;
	
}
