package com.offcl.blogs_service.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.offcl.blogs_service.common.enums.ResponseStatus;
import com.offcl.blogs_service.common.response.ApiResponse;
import com.offcl.blogs_service.dto.FileResponseDto;
import com.offcl.blogs_service.entity.FileEntity;
import com.offcl.blogs_service.entity.User;
import com.offcl.blogs_service.exception.ResourceNotFoundException;
import com.offcl.blogs_service.mappers.FileMapper;
import com.offcl.blogs_service.repository.FileRepository;
import com.offcl.blogs_service.repository.UserRepository;

import jakarta.annotation.Resource;

@Service
public class FileService {
	
	
	@Autowired
	private FileRepository fileRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	
	@Value("${file.upload-dir}")
	private String uploadDir;
	
	@Value("${app.base-url}")
	private String baseUrl;
	
	
	public ApiResponse<FileResponseDto> uploadFile(Long userId,MultipartFile file) throws IOException{
		
		User existingUser = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found with id "+ userId));
		
//		Start the upload here;
		
		Path uploadPath = Paths.get(this.uploadDir);
		
		if(!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		
		String fileName = System.currentTimeMillis()+"_"+file.getOriginalFilename();
		Path filePath = uploadPath.resolve(fileName);
		Files.copy(file.getInputStream(),filePath,StandardCopyOption.REPLACE_EXISTING);
		
		
		FileEntity fileEntity = FileEntity.builder()
				.fileName(fileName)
				.filePath(filePath.toString())
				.fileType(file.getContentType())
				.fileSize(file.getSize())
				.build();
		
		FileEntity savedFile = this.fileRepo.save(fileEntity);
		
		existingUser.setProfile(savedFile);
		
		this.userRepo.save(existingUser);
		
		
		return ApiResponse.<FileResponseDto>builder().status(ResponseStatus.Success).message("Uploaded file successfully").data(FileMapper.mapFileResponse(savedFile, this.baseUrl)).build();
		
		
	}
	
	public ResponseEntity<ByteArrayResource> getFile(Long id) throws IOException {
	    FileEntity fileEntity = fileRepo.findById(id)
	            .orElseThrow(() -> new RuntimeException("File not found"));

	    Path path = Paths.get(fileEntity.getFilePath());
	    byte[] fileData = Files.readAllBytes(path);

	    return ResponseEntity.ok()
	            .contentType(MediaType.parseMediaType(fileEntity.getFileType()))
	            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileEntity.getFileName() + "\"")
	            .body(new ByteArrayResource(fileData));
	}
	
	
	public ResponseEntity<ByteArrayResource> downloadFile(Long id) throws IOException {
	    FileEntity fileEntity = fileRepo.findById(id)
	            .orElseThrow(() -> new RuntimeException("File not found"));

	    Path path = Paths.get(fileEntity.getFilePath());
	    byte[] fileData = Files.readAllBytes(path);

	    return ResponseEntity.ok()
	            .contentType(MediaType.parseMediaType(fileEntity.getFileType()))
	            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getFileName() + "\"")
	            .body(new ByteArrayResource(fileData));
	}

	
	

}
