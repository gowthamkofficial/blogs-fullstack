package com.offcl.blogs_service.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.offcl.blogs_service.common.response.ApiResponse;
import com.offcl.blogs_service.dto.FileResponseDto;
import com.offcl.blogs_service.service.FileService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/files")
public class FileController {
	
	@Autowired
	private FileService fileService;
	

	@PostMapping(value = "/profileUpload/{userId}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ApiResponse<FileResponseDto>> uploadProfileImage(@PathVariable(name="userId") Long id, @RequestPart("file") MultipartFile file) throws IOException{
		return ResponseEntity.<ApiResponse<FileResponseDto>>status(HttpStatus.OK).body(this.fileService.uploadFile(id, file));
	}
	
	  @Operation(summary = "Download file by ID")
	  @GetMapping("/{id}")
	  public ResponseEntity<?> getFile(@PathVariable Long id) throws IOException {
	      return this.fileService.getFile(id);
	  }
	  
	  @Operation(summary = "Download file by ID")
	  @GetMapping("/download/{id}")
	  public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long id) throws IOException {
	      return fileService.downloadFile(id);
	  }



}
