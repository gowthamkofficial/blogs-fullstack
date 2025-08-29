package com.offcl.blogs_service.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.offcl.blogs_service.common.enums.ResponseStatus;
import com.offcl.blogs_service.common.response.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 🔹 Catch-all exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGeneralExceptions(Exception ex) {
        ApiResponse<?> response = ApiResponse.builder()
                .status(ResponseStatus.Failure)
                .message(ex.getMessage())
                .data(null)
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
    
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ApiResponse<?>> handleMaxUploadSizeExceeded(MaxUploadSizeExceededException ex) {
        ApiResponse<?> response = ApiResponse.builder()
                .status(ResponseStatus.Failure)
                .message("File size exceeds the allowed limit!")
                .data(null)
                .build();

        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(response); // 413
    }

    // 🔹 Validation errors (400)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, Object>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new LinkedHashMap<>();

        ex.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), err.getDefaultMessage());
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(ResponseStatus.Failure, "Validation Failed", errors));
    }

    // 🔹 Already exists (409 Conflict)
    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ApiResponse<?>> handleAlreadyExists(AlreadyExistsException ex) {
        ApiResponse<Object> response = ApiResponse.builder()
                .status(ResponseStatus.Failure)
                .message(ex.getMessage())
                .data(null)
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    // 🔹 Resource not found (404)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleResourceNotFound(ResourceNotFoundException ex) {
        ApiResponse<Object> response = ApiResponse.builder()
                .status(ResponseStatus.Failure)
                .message(ex.getMessage())
                .data(null)
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // 🔹 Invalid credentials (401 Unauthorized)
    @ExceptionHandler(InvalidCredentialException.class)
    public ResponseEntity<ApiResponse<Object>> handleInvalidCredential(InvalidCredentialException ex) {
        ApiResponse<Object> response = ApiResponse.builder()
                .status(ResponseStatus.Failure)
                .message(ex.getMessage())
                .data(null)
                .build();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}
