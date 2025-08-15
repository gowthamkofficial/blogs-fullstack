package com.offcl.blogs_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.offcl.blogs_service.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	
	boolean existsByEmailAddress(String emailAddress);

	boolean existsByMobileNumber(String mobileNumber);

	boolean existsByMobileNumberAndIdNot(String mobileNumber, Long id);

	boolean existsByEmailAddressAndIdNot(String emailAddress, Long id);
	
	Optional<User> findByEmailAddress(String emailAddress);

}
