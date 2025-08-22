package com.offcl.blogs_service.controller;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
public class FileController {
	
	
	@PostMapping(value = "/profileUpload/{userId}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String uploadProfileImage(@PathVariable(name="userId") Long id, @RequestPart("file") MultipartFile file) throws IOException{
		return null;
	}

}
