package com.offcl.blogs_service.mappers;

import com.offcl.blogs_service.dto.FileResponseDto;
import com.offcl.blogs_service.entity.FileEntity;

public class FileMapper {
	
	
	public static FileResponseDto mapFileResponse(FileEntity file) {
		
		return FileResponseDto.builder()
				.fileId(file.getId())
				.fileName(file.getFileName())
				.fileSize(file.getFileSize())
				.fileType(file.getFileType())
				.fileUrl()
	}

}
