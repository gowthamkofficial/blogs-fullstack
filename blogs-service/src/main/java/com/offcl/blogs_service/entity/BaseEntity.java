package com.offcl.blogs_service.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
public abstract class BaseEntity {
	 @CreationTimestamp
	    @Column(updatable = false)
	    private LocalDateTime createdOn;

	    @UpdateTimestamp
	    private LocalDateTime updatedOn;
}
